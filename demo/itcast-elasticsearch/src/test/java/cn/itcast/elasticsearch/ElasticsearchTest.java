package cn.itcast.elasticsearch;

import cn.itcast.elasticsearch.pojo.Item;
import cn.itcast.elasticsearch.respository.ItemRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author kpwang
 * @create 2020-08-05 18:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ItemRepository itemRepository;
    @Test
    public void testIndex(){
        this.elasticsearchTemplate.createIndex(Item.class);
        this.elasticsearchTemplate.putMapping(Item.class);
    }

    /**
     * 如果id存在就是更新方法，如果id不存在则为新增方法
     */
    @Test
    public void testCreate(){
        /*Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        this.itemRepository.save(item);*/


        List<Item> list = new ArrayList<>();
        list.add(new Item(6L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(7L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }
    @Test
    public void testFind(){
        Optional<Item> optionalItem = this.itemRepository.findById(1L);
        System.out.println(optionalItem);
        Item item = optionalItem.get();
        System.out.println(item);
    }
    @Test
    public void testFind1(){

        Iterable<Item> items = this.itemRepository.findAll(Sort.by("price").descending());
        items.forEach(System.out::println);
    }
    @Test
    public void findFindByTitle(){
        List<Item> items = this.itemRepository.findByTitle("手机");
        System.out.println(items);
        List<Item> items1 = this.itemRepository.findByPriceBetween(3500d,4500d);
        System.out.println(items1);
    }
    @Test
    public void testSearch(){
        //通过查询构建器工具构建查询条件
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "手机");
        Iterable<Item> items = this.itemRepository.search(queryBuilder);
        for (Item item : items) {
            System.out.println(item);
        }
    }
    @Test
    public void testNative(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "手机"));
        Page<Item> itemPage = this.itemRepository.search(queryBuilder.build());
        System.out.println(itemPage.getTotalPages());
        System.out.println(itemPage.getTotalElements());
        System.out.println(itemPage.getContent());
    }
    @Test
    public void testPage(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));
        //添加分页条件，页码从0开始
        //queryBuilder.withPageable(PageRequest.of(1,2));
        //添加排序条件
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        Page<Item> itemPage = this.itemRepository.search(queryBuilder.build());
        System.out.println(itemPage.getTotalPages());
        System.out.println(itemPage.getTotalElements());
        List<Item> content = itemPage.getContent();
        for (Item item : content) {
            System.out.println(item);
        }
    }

    /**
     * 聚合为桶
     */
    @Test
    public void testAggs(){
        //初始化自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand"));
        //添加结果集过滤，不包括任何字段
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{},null));
        //执行聚合查询
        AggregatedPage<Item> itemPage =(AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        //解析聚合结果集，根据聚合的类型以及字段类型要进行强转，brand-是字符串类型的，聚合类型-词条聚合，brandAgg-通过聚合名称获取聚合对象
        StringTerms brandAgg =(StringTerms) itemPage.getAggregation("brandAgg");
        //通过通的集合
        List<StringTerms.Bucket> buckets = brandAgg.getBuckets();
        buckets.forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
        });
    }
    @Test
    public void testSubAggs(){
        //初始化自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand")
                .subAggregation(AggregationBuilders.avg("price_avg").field("price")));
        //添加结果集过滤，不包括任何字段
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{},null));
        //执行聚合查询
        AggregatedPage<Item> itemPage =(AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        //解析聚合结果集，根据聚合的类型以及字段类型要进行强转，brand-是字符串类型的，聚合类型-词条聚合，brandAgg-通过聚合名称获取聚合对象
        StringTerms brandAgg =(StringTerms) itemPage.getAggregation("brandAgg");
        //通过通的集合
        List<StringTerms.Bucket> buckets = brandAgg.getBuckets();
        buckets.forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
            //获取子聚合的map集合：key聚合名称，value对应子聚合对象
            Map<String, Aggregation> stringAggregationMap = bucket.getAggregations().asMap();
            InternalAvg price_avg = (InternalAvg)stringAggregationMap.get("price_avg");
            System.out.println(price_avg.value());
            System.out.println(price_avg.getValue());
        });
    }
}
