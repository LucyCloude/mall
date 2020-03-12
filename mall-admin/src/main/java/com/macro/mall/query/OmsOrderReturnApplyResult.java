package com.macro.mall.query;

import com.macro.mall.model.OmsCompanyAddress;
import com.macro.mall.model.OmsOrderReturnApply;
import lombok.Getter;
import lombok.Setter;

/**
 * 封装单个退货订单的信息类
 */
@Setter
@Getter
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
    private  OmsCompanyAddress companyAddress;
}
