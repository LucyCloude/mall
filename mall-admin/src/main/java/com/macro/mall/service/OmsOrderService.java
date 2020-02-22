package com.macro.mall.service;

import com.macro.mall.model.OmsOrder;
import com.macro.mall.query.OmsOrderQueryParam;

import java.util.List;

/**
 * 订单业务接口
 */
public interface OmsOrderService {
    /**
     * 分页查询订单
     * @param omsOrderQueryParam 查询条件
     * @param pageNum 当前页
     * @param pageSize 页大小
     * @return
     */
    List<OmsOrder> getList(OmsOrderQueryParam omsOrderQueryParam,Integer pageNum,Integer pageSize);
}
