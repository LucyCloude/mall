package com.macro.mall.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("订单退货查询封装信息")
@Setter
@Getter
public class OmsReturnApplyQueryParam {
    @ApiModelProperty("服务单号")
    private Long id;
    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;
    @ApiModelProperty(value = "申请时间")
    private String createTime;
    @ApiModelProperty(value = "处理人员")
    private String handleMan;
    @ApiModelProperty(value = "处理时间")
    private String handleTime;
}
