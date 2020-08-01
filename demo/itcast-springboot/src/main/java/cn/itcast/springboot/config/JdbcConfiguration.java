package cn.itcast.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author kpwang
 * @create 2020-07-30 20:58
 */
@Configuration//声明一个类是java配置类
//@PropertySource("classpath:jdbc.properties")//读取资源文件
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfiguration {
    /*@Autowired
    private JdbcProperties jdbcProperties;*/
    @Bean//把方法的返回值注入到spring容器中
    public DataSource dataSource(JdbcProperties jdbcProperties){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());
        return dataSource;
    }
}
