package cn.itcast.user.controller;

import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kpwang
 * @create 2020-07-30 21:52
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    @ResponseBody
    public User queryUserById(@PathVariable(value = "id") Long id){
        return userService.queryUserById(id);
    }
    @GetMapping("all")
    public String toUsers(Model model){
        List<User> list=this.userService.queryUserAll();
        model.addAttribute("users",list);
        return "users";
    }

    @GetMapping("test")
    @ResponseBody
    public String test(){
        System.out.println("-------");
        return "hello user!";
    }
}
