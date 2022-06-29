package com.itrjp.demo.controller;

import com.itrjp.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODOdemo controller
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 16:10
 */

@Slf4j
@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("/")
    public Result<Void> demo() {
        log.info("demo");
        return Result.success();
    }
}
