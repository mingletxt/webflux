package com.mingle.webflux.demo;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 20:11 Desc 文件描述
 */
public class ReduceTest {
    
    public static void main(String[] args) {
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);
        Flux.just("hello", "world", "mingle").reduce((x,y) -> x + "-" + y).subscribe(System.out::println);
    }
}
