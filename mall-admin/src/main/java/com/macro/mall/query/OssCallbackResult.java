package com.macro.mall.query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("回调封装的图片相关属性")
@Setter
@Getter
public class OssCallbackResult {
    @ApiModelProperty("上传的文件名")
    private String filename;
    @ApiModelProperty("文件大小")
    private String size;
    @ApiModelProperty("文件的类型")
    private String mimeType;
    @ApiModelProperty("文件的宽度")
    private String width;
    @ApiModelProperty("文件的高度")
    private String height;
}
