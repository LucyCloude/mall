package com.macro.mall.service;

import com.macro.mall.domain.PmsProductAttributeCategoryItem;
import com.macro.mall.model.PmsProductAttributeCategory;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 商品属性分类业务接口
 */
public interface PmsProductAttributeCategoryService {


    /**
     * 保存商品属性分类
     * @param name
     * @return
     */
    Integer create(String name);



    /**
     * 修改商品属性分类的名称
     * @param id 商品属性分类的id
     * @param name 商品属性分类的名称
     * @return
     */
    Integer update(Long id,String name);


    /**
     * 删除商品属性分类
     * @param id
     * @return
     */
    Integer delete(Long id);

    /**
     * 分页查询商品属性分类
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductAttributeCategory> getList(Integer pageNum,Integer pageSize);

    /**
     * 查询商品属性分类及商品属性
     * @return
     */
    List<PmsProductAttributeCategoryItem> getAttriItem();


}
