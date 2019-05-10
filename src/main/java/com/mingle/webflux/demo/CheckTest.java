package com.mingle.webflux.demo;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 21:02 Desc 文件描述
 */
public class CheckTest {
    
    public static void main(String[] args) {
        Flux.just(1, 2)
                .map(x -> {
                    System.out.println("x: " + x);
                    return 1 / x;
                })
                .checkpoint("test")
                .subscribe(System.out::println);
    }
}
