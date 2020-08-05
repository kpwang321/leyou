package com.leyou.search.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author kpwang
 * @create 2020-08-06 0:26
 */
@FeignClient("item-service")
public interface CategoryClient extends CategoryApi{
}
