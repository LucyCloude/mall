package com.macro.mall.service;

import com.macro.mall.model.OmsOrderReturnApply;
import com.macro.mall.query.OmsOrderReturnApplyResult;
import com.macro.mall.query.OmsReturnApplyQueryParam;
import com.macro.mall.query.OmsUpdateStatusParam;

import java.util.List;

/**
 * 退货处理业务接口
 */
public interface OmsOrderReturnApplyService {
    /**
     * 查询退货订单
     * @param omsReturnApplyQueryParam
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<OmsOrderReturnApply> getList(OmsReturnApplyQueryParam omsReturnApplyQueryParam,Integer pageNum,Integer pageSize);

    /**
     * 查询单个退货订单详细信息
     * @param id
     * @return
     */
    OmsOrderReturnApplyResult getReturnApply(Long id);

    /**
     * 订单退货状态修改
     * @param id 订单id
     * @param updateStatusParam 订单状态的信息
     * @return
     */
    Integer updateStatus(Long id,OmsUpdateStatusParam updateStatusParam);
}
