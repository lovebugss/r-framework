package com.itrjp.cloud.config;

import com.itrjp.cloud.aliyun.AliyunFactory;
import com.itrjp.cloud.aws.AmazonFactory;
import com.itrjp.cloud.base.CloudFactory;
import com.itrjp.cloud.base.storage.ObjectStorageService;
import com.itrjp.cloud.consts.CloudType;
import com.itrjp.cloud.properties.CloudProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cloud 自动配置类
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 15:30
 */
@EnableConfigurationProperties({CloudProperties.class})
public class CloudAutoConfiguration {
    private final CloudProperties cloudProperties;

    public CloudAutoConfiguration(CloudProperties cloudProperties) {
        this.cloudProperties = cloudProperties;
    }


    @Bean
    @ConditionalOnBean(CloudFactory.class)
    CloudFactory cloudFactory() {
        CloudType defaultType = cloudProperties.getDefaultType();
        switch (defaultType) {
            case Amazon:
                return new AmazonFactory(cloudProperties.getAmazon());
            case Aliyun:
                return new AliyunFactory(cloudProperties.getAliyun());
            default:
                throw new IllegalArgumentException("无效的云类型");
        }
    }

    @Bean
    @ConditionalOnBean(ObjectStorageService.class)
    ObjectStorageService objectStorageService(CloudFactory cloudFactory) {
        return cloudFactory.createObjectStorage();
    }
}
