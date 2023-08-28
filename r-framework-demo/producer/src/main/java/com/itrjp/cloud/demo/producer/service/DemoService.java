package com.itrjp.cloud.demo.producer.service;

import com.itrjp.cloud.demo.common.pojo.vo.DemoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemoService {
    public DemoVo demo(){
        return new DemoVo();
    }
}
