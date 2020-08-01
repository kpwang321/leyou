package com.itheima.test;

import com.itheima.pojo.User;
import com.itheima.pojo.VueUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test(){
        String url = "http://localhost:8080/day01_vuejsdemo/user/findById.do?id=1";
        VueUser user = restTemplate.getForObject(url, VueUser.class);
        System.out.println(user);
    }
}
