package com.itrjp.cloud.demo.producer.internal;

import com.itrjp.cloud.demo.common.api.DemoApi;
import com.itrjp.cloud.demo.common.pojo.vo.DemoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DemoApiImpl implements DemoApi {

    @Override
    public DemoVo demo() {
        log.info("demo");
        return new DemoVo("1111");
    }

    @Override
    public DemoVo async(String id) {
        log.info("async");
        return new DemoVo(id);
    }
}
