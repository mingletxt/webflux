package com.mingle.webflux.demo;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


/**
 * Created by mingle. Time 2019-05-03 20:43 Desc 文件描述
 */
public class SchedulersTest {
    
    public static void main(String[] args) {
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> {
                    System.out.println("1 " + Thread.currentThread().getName() + " " + x);
                    return String.format("[%s] %s", Thread.currentThread().getName(), x);
                })
                .publishOn(Schedulers.elastic())
                .map(x -> {
                    System.out.println("2 " + Thread.currentThread().getName() + " " + x);
                    return String.format("[%s] %s", Thread.currentThread().getName(), x);
                })
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
    
    }
}
