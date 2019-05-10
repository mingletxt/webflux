package com.mingle.webflux;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

import reactor.core.publisher.Mono;


/**
 * Created by mingle. Time 2019-05-10 10:34 Desc 文件描述
 */
@RestController
public class LatencyCtrl {
    
    @RequestMapping("/hello/{latency}")
    public Mono<String> hello(@PathVariable int latency) {
        return Mono.just("Welcome to reactive world ~ latency: " + latency)
                .delayElement(Duration.ofMillis(latency)); // 1
    }
}
