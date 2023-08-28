package com.itrjp.cloud.huawei.config;


import com.itrjp.cloud.config.CloudAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean({CloudAutoConfiguration.class})
public class HuaweiCloudConfiguration implements CloudAutoConfiguration {
}
