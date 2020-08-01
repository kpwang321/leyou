package cn.itcast.service.client;

import cn.itcast.service.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author kpwang
 * @create 2020-07-31 14:59
 */
@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryUserById(Long id) {
        User user=new User();
        user.setUsername("服务器忙，稍后再试");
        return user;
    }
}
