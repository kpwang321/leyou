package com.leyou.cart.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author kpwang
 * @create 2020-08-10 19:21
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
