package com.macro.mall.query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("客户端获取OSS上传文件授权返回结果")
@Setter
@Getter
public class OssPolicyResult {
    @ApiModelProperty("用户标识的身份key")
    private String accessKeyId;
    @ApiModelProperty("用户表单上传,经过base64编码过的字符串")
    private String policy;
    @ApiModelProperty("对policy签名后的字符串")
    private String signature;
    @ApiModelProperty("上传文件夹路径前缀")
    private String dir;
    @ApiModelProperty("oss对外服务的访问域名")
    private String host;
    @ApiModelProperty("上传成功后的回调设置")
    private String callback;
}
