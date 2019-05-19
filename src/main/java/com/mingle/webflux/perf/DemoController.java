package com.mingle.webflux.perf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@RestController
@Slf4j
public class DemoController {
    
    @RequestMapping("/recomm")
    public Mono<Result> recommtest(Long latency) {
        log.info("recomm");
        Result result = new Result();
        result.setCode(1);
        result.setMsg("this is recomm");
        log.info("recomm result: {}", result);
        
        latency = latency != null ? latency : 0L;
        return Mono.just(result).delayElement(Duration.ofMillis(latency));
    }
    
    @RequestMapping("/recomm/abc")
    public Mono<Result> recomm(Long latency) {
        log.info("recomm");
        Result result = new Result();
        result.setCode(1);
        result.setMsg("this is recomm");
        log.info("recomm result: {}", result);
        
        latency = latency != null ? latency : 0L;
        return Mono.just(result).delayElement(Duration.ofMillis(latency));
    }
    
    @RequestMapping("/etl/abc")
    public Mono<Result> etl(Long latency) {
        
        Result result = new Result();
        result.setCode(1);
        result.setMsg("this is etl");
        log.info("etl result: {}", result);
        
        latency = latency != null ? latency : 0L;
        return Mono.just(result).delayElement(Duration.ofMillis(latency));
    }
    
    @RequestMapping("/etl/timeout")
    @ResponseBody
    public Result etlTimeout(String name) {
        if (name != null) {
            log.info("etl length: {}", name.length());
        }
        try {
            Thread.sleep(3500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        result.setCode(1);
        result.setMsg("this is timeout");
        return result;
    }
}