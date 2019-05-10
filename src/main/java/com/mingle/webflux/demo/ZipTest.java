package com.mingle.webflux.demo;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 20:04 Desc 文件描述
 */
public class ZipTest {
    
    public static void main(String[] args) {
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"))
                .subscribe(System.out::println);
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);
    }
}
