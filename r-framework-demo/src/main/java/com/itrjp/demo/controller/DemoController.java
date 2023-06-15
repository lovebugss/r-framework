package com.itrjp.demo.controller;

import com.itrjp.core.result.Result;
import com.itrjp.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO demo controller
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 16:10
 */

@Slf4j
@RestController
@RequestMapping("demo")
public class DemoController {
    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/")
    public Result<Void> index() {
        log.info("index");
        return Result.success();
    }

    @GetMapping("/async")
    public Result<Void> async() {
        log.info("async");
        demoService.async();
        return Result.success();
    }
}
