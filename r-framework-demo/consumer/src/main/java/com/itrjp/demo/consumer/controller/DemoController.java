package com.itrjp.demo.consumer.controller;

import com.itrjp.core.result.Result;
import com.itrjp.demo.common.pojo.vo.DemoVo;
import com.itrjp.demo.consumer.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/consumer")
@RequiredArgsConstructor
@Slf4j
public class DemoController {
    private final DemoService demoService;


    @GetMapping
    public Result<DemoVo> demo() {
        return Result.success(demoService.demo());
    }

    @GetMapping("{id}")
    public Result<DemoVo> demo(@PathVariable String id) {
        DemoVo async = demoService.async(id);
        log.info("async: ", async);
        return Result.success(async);
    }

    @GetMapping("/1/{id}")
    public Result<DemoVo> dem1o(@PathVariable String id) {
        log.info("dem1o: ", id);
        return Result.success(new DemoVo(id));
    }

}
