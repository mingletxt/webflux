package com.mingle.webflux.demo;

import java.time.Duration;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 20:15 Desc 文件描述
 */
public class MergeTest {
    
    public static void main(String[] args) {
        Flux.merge(Flux.interval(Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);
        System.out.println("---");
        Flux.mergeSequential(Flux.interval(Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);
    }
}
