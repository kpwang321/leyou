package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author kpwang
 * @create 2020-08-02 13:32
 */
public interface BrandMapper extends Mapper<Brand> {
    @Insert("insert into tb_category_brand (category_id,brand_id) values (#{cid},#{bid})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);
    @Select("select t.id,t.name,t.image,t.letter from tb_brand t,tb_category_brand tt where t.id=tt.brand_id and tt.category_id=#{cid}")
    List<Brand> selectBrandsByCid(Long cid);
}
