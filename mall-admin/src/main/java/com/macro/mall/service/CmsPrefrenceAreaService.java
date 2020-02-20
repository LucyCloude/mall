package com.macro.mall.service;

import com.macro.mall.model.CmsPrefrenceArea;

import java.util.List;

/**
 * 商品优选业务接口
 */

public interface CmsPrefrenceAreaService {
    /**
     * 查询所有的商品优选
     * @return
     */
    List<CmsPrefrenceArea> getList();
}
