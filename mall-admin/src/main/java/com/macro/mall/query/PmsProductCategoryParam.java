package com.macro.mall.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@ApiModel("修改商品分类封装的类")
@Setter
@Getter
public class PmsProductCategoryParam {
    @ApiModelProperty("父分类的编号")
    private Long parentId;
    @ApiModelProperty(value = "商品分类名称")
    @NotBlank(message = "商品分类名称不能为空!")
    private String name;
    @ApiModelProperty("分类单位")
    private String productUnit;
    @ApiModelProperty("是否在导航栏显示")
    @Max(value = 1,message = "导航栏必须是0或1")
    //@Pattern(regexp ="[0|1]",message ="导航栏必须是0或1")
    private Integer navStatus;
    @ApiModelProperty("是否进行显示")
    @Max(value = 1,message = "显示必须是0或1")
    //@Pattern(regexp ="[0|1]",message = "显示必须是0或1")
    private Integer showStatus;
    @ApiModelProperty("排序")
    @Min(value = 0,message = "排序不能小于0")
    private Integer sort;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("关键字")
    private String keywords;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("产品相关筛选属性集合")
    private List<Long> productAttributeIdList;
}
