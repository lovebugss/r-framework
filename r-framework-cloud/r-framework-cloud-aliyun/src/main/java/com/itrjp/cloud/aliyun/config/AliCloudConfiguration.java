package com.itrjp.cloud.aliyun.config;

import com.itrjp.cloud.aliyun.AliyunFactory;
import com.itrjp.cloud.aliyun.properties.AliyunProperties;
import com.itrjp.cloud.aliyun.storage.AliyunOSSAdapter;
import com.itrjp.cloud.config.CloudAutoConfiguration;
import com.itrjp.cloud.storage.ObjectStorageService;
import org.springframework.context.annotation.Bean;

public class AliCloudConfiguration extends CloudAutoConfiguration {
    private final AliyunProperties properties;
    private final AliyunFactory aliyunFactory;

    public AliCloudConfiguration(AliyunProperties properties) {
        this.properties = properties;
        aliyunFactory = new AliyunFactory(properties);
    }

    @Bean
    ObjectStorageService aliOssService() {
        System.out.printf("aliOssService........");
        return aliyunFactory.createObjectStorage();
    }
}


