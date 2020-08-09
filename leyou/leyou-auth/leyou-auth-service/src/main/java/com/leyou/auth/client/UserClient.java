package com.leyou.auth.client;

import com.leyou.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author kpwang
 * @create 2020-08-09 2:11
 */
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
