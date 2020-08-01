package cn.itcast.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author kpwang
 * @create 2020-07-30 20:28
 */
@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private DataSource dataSource;
    @GetMapping("show")
    public String test(){
        System.out.println(dataSource);
        return "hello springboot 1";
    }
}
