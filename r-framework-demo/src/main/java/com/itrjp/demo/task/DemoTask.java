package com.itrjp.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * DemoTask
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 18:01
 */
@Slf4j
@Component
public class DemoTask {

    @Scheduled(cron = "0/10 * * * * *")
    public void demo() {
        log.info("demo start..");
    }
}
