package com.itrjp.log.config;

import com.itrjp.log.aspect.ScheduledAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * ScheduledTraceAutoConfiguration
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/4 14:01
 */
@Configuration
@ConditionalOnBean(annotation = {EnableScheduling.class})
public class ScheduledTraceAutoConfiguration {
    @Bean
    ScheduledAspect scheduledAspect() {
        System.out.println("xxxxxxxxxxxxxxx");
        return new ScheduledAspect();
    }
}
