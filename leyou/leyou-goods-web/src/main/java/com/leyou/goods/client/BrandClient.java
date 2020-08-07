package com.leyou.goods.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author kpwang
 * @create 2020-08-06 0:26
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
