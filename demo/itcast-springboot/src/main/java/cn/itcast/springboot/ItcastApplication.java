package cn.itcast.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author kpwang
 * @create 2020-07-30 20:32
 *     @EnableAutoConfiguration
       @ComponentScan/*类似于<context:component-scan base-package=""/>
       @SpringBootConfiguration

 @SpringBootApplication相当于上述三个注解的组合

 */
@SpringBootApplication
public class ItcastApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItcastApplication.class,args);
    }
}
