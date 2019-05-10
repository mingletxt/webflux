package com.mingle.webflux.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;


/**
 * Created by mingle. Time 2019-05-03 20:07 Desc 文件描述
 */
public class TakeTest {
    
    public static void main(String[] args) {
        Flux.range(1, 1000).take(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);
    }
}
