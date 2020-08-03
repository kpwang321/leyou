package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author kpwang
 * @create 2020-08-03 15:07
 */
@Controller
@RequestMapping("spu")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "saleable" ,required = false) Boolean saleable,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows){
       PageResult<SpuBo> result= this.goodsService.querySpuByPage(key,saleable,page,rows);
       System.out.println(result+"------------------------");
       if (result==null|| CollectionUtils.isEmpty(result.getItems())){
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok(result);
    }

}
