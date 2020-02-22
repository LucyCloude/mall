package com.macro.mall.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;



@ApiModel("添加和修改商品品牌封装类")
@Setter
@Getter
public class PmsBrandParam {
    @ApiModelProperty(value = "品牌名称",required = true)
    @NotBlank(message = "品牌名称不能为空!")
    private String name;
    @ApiModelProperty(value = "品牌首字母")
    private String firstLetter;
    @ApiModelProperty(value = "排序字段")
    @Min(value = 0,message = "排序不能小于0")
    private Integer sort;
    @ApiModelProperty(value = "是否为厂家制造商")
    @Max(value = 1,message = "厂家制造商必须是0或1")
    private Integer factoryStatus;
    @ApiModelProperty(value = "是否进行显示")
    private Integer showStatus;
    @ApiModelProperty(value = "品牌logo",required = true)
    @NotBlank(message = "品牌logo不能为空!")
    private String logo;
    @ApiModelProperty(value = "品牌大图")
    private String bigPic;
    @ApiModelProperty(value = "品牌故事")
    private String brandStory;
}
