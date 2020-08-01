package cn.itcast.service.controller;

import cn.itcast.service.client.UserClient;
import cn.itcast.service.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author kpwang
 * @create 2020-07-31 1:35
 */
@RestController
@RequestMapping("consumer/user")
public class UserController {

    @Autowired
    private UserClient userClient;
    /*@Autowired
    private RestTemplate template;
    使用feign省略
    */

   /* @Autowired
    private DiscoveryClient discoveryClient;*///包含了拉取的所有服务信息
    @GetMapping("query")
    public String queryUserById(@RequestParam("id") Long id){
        /*List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance instance = instances.get(0);
        String host = instance.getHost();
        int port = instance.getPort();*/
        //String url= "http://service-provider/user/"+id;

        //return this.template.getForObject(url,String.class);
        return this.userClient.queryUserById(id).toString();
    }

    public String queryUserByIdFallback(Long id){
        return "服务器正忙，请稍后再试！";

    }
}
