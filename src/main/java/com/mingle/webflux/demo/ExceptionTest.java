package com.mingle.webflux.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Created by mingle. Time 2019-05-03 15:48 Desc 文件描述
 */
public class ExceptionTest {
    
    public static void main(String[] args) {
        Flux.just(1,2,3).map(i -> {
            if (i == 2) {
                throw new RuntimeException("i = 2");
            } else {
                System.out.println("i is " + i);
            }
            return i;
        }).subscribe(i -> System.out.println(i), throwable -> System.err.println(throwable));
    
        Flux<Integer> ints = Flux.range(1, 10)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error: " + error));
    
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println, System.err::println);
    
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(-1)
                .subscribe(System.out::println);
    
        /*Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .switchOnError(Mono.just(0))
                .subscribe(System.out::println);*/
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorResume(e -> {
                    if (e instanceof IllegalStateException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalArgumentException) {
                        return Mono.just(-1);
                    }
                    return Mono.empty();
                })
                .subscribe(System.out::println);
    }
}
