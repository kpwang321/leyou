package com.leyou.item.api;


import com.leyou.item.pojo.SpecParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author kpwang
 * @create 2020-08-03 12:22
 */
@RequestMapping("spec")
public interface SpecificationApi {

    @GetMapping("params")
    public List<SpecParam> queryParams(@RequestParam(value = "gid",required = false) Long gid,
                                                       @RequestParam(value = "cid",required = false) Long cid,
                                                       @RequestParam(value = "generic",required = false) Boolean generic,
                                                       @RequestParam(value = "searching",required = false) Boolean searching);
}
