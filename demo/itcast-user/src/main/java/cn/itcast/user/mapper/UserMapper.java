package cn.itcast.user.mapper;

import cn.itcast.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kpwang
 * @create 2020-07-30 22:36
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>{
}
