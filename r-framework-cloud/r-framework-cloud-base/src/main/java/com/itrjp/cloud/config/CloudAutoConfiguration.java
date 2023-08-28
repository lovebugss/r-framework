package com.itrjp.cloud.config;

import com.itrjp.cloud.CloudFactory;
import com.itrjp.cloud.consts.CloudType;
import com.itrjp.cloud.properties.CloudProperties;
import com.itrjp.cloud.storage.ObjectStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Cloud 自动配置类
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 15:30
 */
//@EnableConfigurationProperties({CloudProperties.class})
public class CloudAutoConfiguration {

}
