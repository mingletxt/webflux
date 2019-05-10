package com.mingle.webflux.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Created by mingle. Time 2019-05-03 15:36 Desc 文件描述
 */
public class Log {
    
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1,2,3,4,5,6,7).log("Range");
        flux.subscribe(
                i -> System.out.println("flux: " + i),
                Throwable::printStackTrace,
                () -> System.out.println("done")
        );
        System.out.println("----");
        Mono.just(new Model("mingle", 30)).log("range mono").subscribe(System.out::println);
    
        System.out.println("----");
        Flux.just(1,3,5,6,7).map(i -> i *i).log().flatMap(i -> Mono.just("mingle " + i)).log().subscribe(System.out::println);
    }
}
