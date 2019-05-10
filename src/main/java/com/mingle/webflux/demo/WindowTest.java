package com.mingle.webflux.demo;

import java.time.Duration;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 20:02 Desc 文件描述
 */
public class WindowTest {
    
    public static void main(String[] args) {
        Flux.range(1, 100).window(20).subscribe(System.out::println);
        Flux.interval(Duration.ofMillis(100)).window(Duration.ofMillis(1001)).take(2).toStream().forEach(System.out::println);
    
    }
}
