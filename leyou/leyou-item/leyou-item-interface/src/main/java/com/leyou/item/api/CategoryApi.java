package com.leyou.item.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author kpwang
 * @create 2020-08-02 3:29
 */
@RequestMapping("category")
public interface CategoryApi {


    @GetMapping
    public List<String> queryNamesByIds(@RequestParam("ids") List<Long> ids);

}
