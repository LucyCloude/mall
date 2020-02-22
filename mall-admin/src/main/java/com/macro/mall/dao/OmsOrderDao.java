package com.macro.mall.dao;

import com.macro.mall.model.OmsOrder;
import com.macro.mall.query.OmsOrderQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单dao
 */
public interface OmsOrderDao {
    /**
     * 条件查询订单
     * @param omsOrderQueryParam
     * @return
     */
    List<OmsOrder> getList(@Param("order") OmsOrderQueryParam omsOrderQueryParam);
}
