package com.macro.mall.service;

import com.macro.mall.model.OmsOrderReturnReason;

import java.util.List;

/**
 * 退货原因设置业务接口
 */
public interface OmsOrderReturnReasonService {

    /**
     * 分页查询退货原因参数
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<OmsOrderReturnReason> list(Integer pageNum,Integer pageSize);

    /**
     * 增加退货原因设置
     * @return
     */
    int create(OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 查询指定处指定的退货原因设置
     * @param id
     * @return
     */
    OmsOrderReturnReason getReturnReason(Long id);

    /**
     * 修改指定退货原因设置
     * @param id
     * @param omsOrderReturnReason
     * @return
     */
    int updateReturnReason(Long id,OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 批量删除退货原因设置
     * @param ids
     * @return
     */
    int deleteReturnReason(List<Long> ids);
}
