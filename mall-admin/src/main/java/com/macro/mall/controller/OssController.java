package com.macro.mall.controller;

import com.macro.mall.common.CommonResult;
import com.macro.mall.query.OssCallbackResult;
import com.macro.mall.query.OssPolicyResult;
import com.macro.mall.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "OSSController",description = "OSS管理")
@Controller
@RequestMapping("/aliyun/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("OSS上传生成签名")
    @GetMapping("/policy")
    @ResponseBody
    public CommonResult<OssPolicyResult> policy(){
        OssPolicyResult policy = ossService.policy();
        return CommonResult.success(policy);
    }

    @ApiModelProperty("oss文件上传成功或回调")
    @PostMapping("/callback")
    @ResponseBody
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request){
        OssCallbackResult callback = ossService.callback(request);
        return CommonResult.success(callback);
    }
}
