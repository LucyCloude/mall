package com.macro.mall.common;

/**
 * 封装API错误码
 */
public interface IErrorCode {

    long getCode();//状态码

    String getMessage();//提示信息
}
