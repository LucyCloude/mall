package com.macro.mall.component;

import com.macro.mall.common.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;

/**
 * HibernateValidator错误结果处理切面类
 */
@Aspect
@Component
@Order(1)
public class BindingResultAspect {
    @Pointcut("execution(public * com.macro.mall.controller.*.*(..))")
    public  void BindingResult(){
    }
    @Around("BindingResult()")
    public  Object bindingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();//得到请求方法中的所有参数
        for (Object obj :args) {//遍历参数
            if (obj instanceof BindingResult){//是否有BindingResult类型的参数
                BindingResult bindingResult= (BindingResult) obj;//强转BindingResult
                if (bindingResult.hasErrors()){//是否包含错误信息
                    FieldError fieldError = bindingResult.getFieldError();//得到验证错误的字段
                    return CommonResult.validateFailed(fieldError.getDefaultMessage());//得到验证字段错误信息
                }
            }
        }
        return  joinPoint.proceed();
    }
}
