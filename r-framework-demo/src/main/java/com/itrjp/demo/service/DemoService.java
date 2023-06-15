package com.itrjp.demo.service;

import com.itrjp.log.util.SupplierWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class DemoService {
    public void async() {
        List<CompletableFuture<String>> tasks = Stream.of("a", "b")
                .map(item -> CompletableFuture.supplyAsync(SupplierWrapper.of(() -> doHandler(item))))
                .collect(Collectors.toList());
        List<String> collect = tasks.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        log.info("result: {}", collect);
    }

    String doHandler(String str) {
        log.info("简单的处理....");
        return str + str;
    }
}
