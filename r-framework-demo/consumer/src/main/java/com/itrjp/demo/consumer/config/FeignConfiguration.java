package com.itrjp.demo.consumer.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.itrjp"})
public class FeignConfiguration {
}
