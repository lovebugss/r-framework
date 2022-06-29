package com.itrjp.web.config;


import com.itrjp.web.filter.RequestLogFilter;
import com.itrjp.web.properties.RequestLogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * RequestLogAutoConfigure
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/23 17:37
 */
@EnableConfigurationProperties({RequestLogProperties.class})
public class RequestLogAutoConfigure {

    @Bean
    @ConditionalOnBean
    FilterRegistrationBean<RequestLogFilter> requestLogFilterRegistrationBean() {
        FilterRegistrationBean<RequestLogFilter> filterRegistrationBean = new FilterRegistrationBean<>(new RequestLogFilter());
        filterRegistrationBean.setOrder(10);
        filterRegistrationBean.addUrlPatterns("/**");
        filterRegistrationBean.setName("requestLogFilter");
        return filterRegistrationBean;
    }

}
