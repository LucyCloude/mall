package com.macro.mall.query;

import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderItem;
import com.macro.mall.model.OmsOrderOperateHistory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("订单详细信息")
@Setter
@Getter
public class OmsOrderDetails extends OmsOrder {
    @ApiModelProperty("订单中的商品")
    private List<OmsOrderItem> orderItemList;
    @ApiModelProperty("订单操作历史记录")
    private List<OmsOrderOperateHistory> historyList;
}
