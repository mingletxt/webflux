package com.mingle.webflux.demo;

import java.time.Duration;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 21:06 Desc 文件描述
 */
public class HotTest {
    
    public static void main(String[] args) throws InterruptedException {
        final Flux<Long> source = Flux.interval(Duration.ofMillis(1000))
                .take(10)
                .publish()
                .autoConnect();
        source.subscribe(System.out::println);
        Thread.sleep(5000);
        source.subscribe(System.out::println);
        source.toStream().forEach(System.out::println);
    }
}
