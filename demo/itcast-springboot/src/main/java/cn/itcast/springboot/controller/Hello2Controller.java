package cn.itcast.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kpwang
 * @create 2020-07-30 20:28
 */
@RestController
@RequestMapping("hello2")
public class Hello2Controller {
    @GetMapping("show")
    public String test(){
        return "hello springboot 2";
    }
}
