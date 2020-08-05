package com.leyou.item.controller;

import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kpwang
 * @create 2020-08-02 3:29
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点id查询子节点
     * @param pid
     * @return
     */
    @RequestMapping("list")
    public ResponseEntity<List<Category>> queryCategorysByPid(@RequestParam(value = "pid",defaultValue = "0") Long pid) {

        if (pid == null || pid < 0) {
            //400:参数不合法
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.badRequest().build();
        }
        List<Category> list = this.categoryService.queryCategoriesByPid(pid);
        if (CollectionUtils.isEmpty(list)) {
            //404：资源服务器未找到
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.notFound().build();
        }
        //200：查询成功
        return ResponseEntity.ok(list);
    }
    @GetMapping
    public ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids") List<Long> ids){
        System.out.println("------");
        List<String> names = this.categoryService.queryNamesByIds(ids);
        System.out.println("----");
        if (CollectionUtils.isEmpty(names)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(names);
    }

}
