package cn.itcast.user.config;

import cn.itcast.user.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kpwang
 * @create 2020-07-30 22:17
 * 配置拦截器
 *  1.声明该类是java配置类
 *  2.实现WebMvcConfigurer接口
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
    @Autowired
    private MyInterceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
