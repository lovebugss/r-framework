package com.itrjp.log.config;

import com.itrjp.log.aspect.ScheduledAspect;
import com.itrjp.log.properties.LogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.SchedulingConfiguration;

import javax.annotation.PostConstruct;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 17:55
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfigure {
    private final LogProperties properties;

    public LogAutoConfigure(LogProperties properties) {
        this.properties = properties;
    }


    @Bean
    ScheduledAspect scheduledAspect() {
        return new ScheduledAspect();
    }
}
