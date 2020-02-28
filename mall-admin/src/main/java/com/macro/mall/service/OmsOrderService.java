package com.macro.mall.service;

import com.macro.mall.model.OmsOrder;
import com.macro.mall.query.OmsOrderDetails;
import com.macro.mall.query.OmsOrderQueryParam;
import com.macro.mall.query.OmsReceiverInfoParam;

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

    /**
     * 查询订单详细信息
     * @param id 订单id
     * @return 订单详细详细
     */
    OmsOrderDetails getOmsOderDetails(Long id);

    /**
     * 修改订单备注
     * @param id 订单id
     * @param node 备注信息
     * @param status 订单状态
     * @return
     */
    Integer updateNote(Long id,String note,Integer status,String adminName);

    /**
     * 修改订单收货人信息
     * @param omsReceiverInfoParam 收货人信息
     * @return
     */
    Integer updateReceiverInfo(OmsReceiverInfoParam omsReceiverInfoParam,String adminName);
}
