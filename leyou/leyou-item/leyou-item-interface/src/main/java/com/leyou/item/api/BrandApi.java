package com.leyou.item.api;


import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kpwang
 * @create 2020-08-02 13:33
 */

@RequestMapping("brand")
public interface BrandApi {
    @GetMapping("{id}")
    public Brand queryBrandById(@PathVariable("id") Long id);
}
