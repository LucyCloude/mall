package com.macro.mall.service;

import com.macro.mall.query.OssCallbackResult;
import com.macro.mall.query.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * OSS文件上传业务接口
 */
public interface OssService {
    /**
     * oss生成签名
     * @return 签名信息封装
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调业务接口
     * @param request
     * @return
     */
    OssCallbackResult callback(HttpServletRequest request);
}
