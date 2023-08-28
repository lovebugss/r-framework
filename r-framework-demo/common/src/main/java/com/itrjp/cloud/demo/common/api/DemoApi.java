package com.itrjp.cloud.demo.common.api;

import com.itrjp.cloud.demo.common.pojo.vo.DemoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DemoApi {

    @GetMapping("internal/demo")
    DemoVo demo();

    @GetMapping("internal/demo/{id}")
    DemoVo async(@PathVariable("id") String id);
}
