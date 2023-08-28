package com.itrjp.cloud.demo.consumer.service;

import com.itrjp.cloud.demo.common.api.DemoApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("producer")
public interface DemoService extends DemoApi {
}
