package cn.itcast.service.client;

import cn.itcast.service.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author kpwang
 * @create 2020-07-31 14:44
 */
@FeignClient(value = "service-provider",fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("user/{id}")
    public User queryUserById(@PathVariable("id") Long id);

}
