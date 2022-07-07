package com.itrjp.log.config;

import com.itrjp.log.filter.FeignTraceInterceptor;
import com.itrjp.log.properties.LogProperties;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 17:55
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfiguration {

    private final LogProperties properties;

    public LogAutoConfiguration(LogProperties properties) {
        this.properties = properties;
    }


    @Bean
    @ConditionalOnClass(RequestInterceptor.class)
    FeignTraceInterceptor feignTraceInterceptor() {
        return new FeignTraceInterceptor();
    }

}
