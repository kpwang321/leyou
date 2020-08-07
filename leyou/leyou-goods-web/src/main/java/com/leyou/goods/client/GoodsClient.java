package com.leyou.goods.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author kpwang
 * @create 2020-08-06 0:06
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi{

}
