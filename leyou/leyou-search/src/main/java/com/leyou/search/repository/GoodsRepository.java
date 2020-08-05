package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author kpwang
 * @create 2020-08-06 1:30
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
