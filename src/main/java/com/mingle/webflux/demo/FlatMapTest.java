package com.mingle.webflux.demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 15:56 Desc 文件描述
 */
public class FlatMapTest {
    
    public static void main(String[] args) throws InterruptedException {
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i * 2;
                })
                .subscribe(e -> System.out.println("map get " + e));
    
        System.out.println("---");
        
        Flux.just(1,2,3,4)
                .log()
                .flatMap(e -> {
                    return Flux.just("hello " + e*2).delayElements(Duration.ofSeconds(1));
                })
                .subscribe(e -> System.out.println(("flatmap get " + e.getClass() + " " + e)));
    
        System.out.println("---");
        Flux.just(5, 10)
                .flatMap(x -> Flux.interval(Duration.ofMillis(x * 10), Duration.ofMillis(100)).take(x))
                .toStream()
                .forEach(System.out::println);
    }
}
