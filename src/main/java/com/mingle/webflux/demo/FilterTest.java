package com.mingle.webflux.demo;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 20:01 Desc 文件描述
 */
public class FilterTest {
    
    public static void main(String[] args) {
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
    
    }
}
