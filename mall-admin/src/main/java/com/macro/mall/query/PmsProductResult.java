package com.macro.mall.query;

import com.macro.mall.query.PmsProductParam;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@ApiModel("查询单个商品的所有关联的属性")
@Setter
@Getter
public class PmsProductResult extends PmsProductParam {
    //商品的id
    private Long cateParentId;
}
