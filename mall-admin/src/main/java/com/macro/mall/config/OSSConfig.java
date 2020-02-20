package com.macro.mall.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSSConfig {
    //外网地址 oss-cn-beijing.aliyuncs.com
    @Value("${aliyun.oss.endpoint}")
    private  String OSS_ENDPOINT;
    //OSS的keyid  LTAI4FeiEuYzTUjp7AuNKEo2
    @Value("${aliyun.oss.accessKeyId}")
    private String OSS_ACCESSKEYID;
    //OSS的value  XUEyNCZVWd7jTpKdlF1RNdO0cuuijc
    @Value("${aliyun.oss.accessKeySecret}")
    private String OSS_ACCESS_KEY_SECRET;
    @Bean
    public OSSClient ossClient(){
        return new OSSClient(OSS_ENDPOINT,OSS_ACCESSKEYID,OSS_ACCESS_KEY_SECRET);
    }
}
