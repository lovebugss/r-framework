package com.itrjp.demo.consumer.service;

import com.itrjp.demo.common.api.DemoApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("producer")
public interface DemoService extends DemoApi {
}
