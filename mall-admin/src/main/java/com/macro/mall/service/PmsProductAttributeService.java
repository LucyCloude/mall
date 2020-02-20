package com.macro.mall.service;

import com.macro.mall.common.CommonResult;
import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.query.PmsProductAttributeParam;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 商品属性业务接口
 */
public interface PmsProductAttributeService {

    /**
     *根据id查询商品属性
     * @param id
     * @return
     */
    PmsProductAttribute getProAttri(Long id);

    /**
     * 保存商品属性
     * @param pmsProductAttributeParam 保存的商品属性
     * @return
     */
    Integer create(PmsProductAttributeParam pmsProductAttributeParam);

    /**
     * 修改商品属性
     * @param id 商品属性
     * @param pmsProductAttributeParam 需要修改的商品属性
     * @return
     */
    Integer update(Long id, PmsProductAttributeParam pmsProductAttributeParam);


    /**
     * 批量删除商品属性
     * @param dis
     * @return
     */
    Integer delete(List<Long> ids);


    /**
     * 分页查询商品属性
     * @param cid 商品属性分类表的id
     * @param type 0:表示规格  1:表示参数
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return
     */
    List<PmsProductAttribute> getList(Long cid,Integer type,Integer pageNum,Integer pageSize);

    /**
     * 根据商品分类的id查询出商品属性分类和商品属性的id
     * @param id 商品分类id
     * @return
     */
    List<Map<String,Long>> getAttriIdAndCateId(Long id);




}
