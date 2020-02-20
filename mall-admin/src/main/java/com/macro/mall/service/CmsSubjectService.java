package com.macro.mall.service;

import com.macro.mall.model.CmsSubject;

import java.util.List;

/**
 * 商品专题业务接口
 */
public interface CmsSubjectService {
    /**
     * 查询所有商品专题
     * @return
     */
    List<CmsSubject> getList();
}
