package com.itrjp.cloud.demo.producer.controller;

import com.itrjp.cloud.core.result.Result;
import com.itrjp.cloud.demo.common.pojo.vo.DemoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DemoController {
    @GetMapping("producer/demo")
    public Result<DemoVo> get() {

        return Result.success(new DemoVo());
    }
}
