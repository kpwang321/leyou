package cn.itcast.elasticsearch.respository;

import cn.itcast.elasticsearch.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author kpwang
 * @create 2020-08-05 18:16
 */
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    List<Item> findByTitle(String title);
    List<Item> findByPriceBetween(Double d1,Double d2);
}
