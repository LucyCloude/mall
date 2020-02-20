package com.macro.mall.domain;

import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.model.PmsProductAttributeCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("商品属性分类表及关联的属性封装")
@Getter
@Setter
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    @ApiModelProperty("商品属性")
    private List<PmsProductAttribute> productAttributeList;

}
