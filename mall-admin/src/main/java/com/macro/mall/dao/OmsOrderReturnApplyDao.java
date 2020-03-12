package com.macro.mall.dao;

import com.macro.mall.model.OmsOrderReturnApply;
import com.macro.mall.query.OmsOrderReturnApplyResult;
import com.macro.mall.query.OmsReturnApplyQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单退货申请Dao
 */
public interface OmsOrderReturnApplyDao {
    /**
     * 分页查询订单退货信息
     * @param omsReturnApplyQueryParam
     * @return
     */
    List<OmsOrderReturnApply> getList(@Param("apply") OmsReturnApplyQueryParam omsReturnApplyQueryParam);

    /**
     * 查询单个订单退货信息
     * @param id
     * @return
     */
    OmsOrderReturnApplyResult getReturnApply(Long id);
}
