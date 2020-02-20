package com.macro.mall.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("商品分页查询封装")
@Getter
@Setter
public class PmsProductQueryParam {
    @ApiModelProperty("商品名称")
    private String keyword;
    @ApiModelProperty("商品货号")
    private String productSn;
    @ApiModelProperty("商品分类")
    private Long productCategoryId;
    @ApiModelProperty("商品品牌")
    private Long brandId;
    @ApiModelProperty("上架状态 0:下架 1:上架")
    private Integer publishStatus;
    @ApiModelProperty("审核状态 0:未审核 1:已审核")
    private Integer verifyStatus;
}
