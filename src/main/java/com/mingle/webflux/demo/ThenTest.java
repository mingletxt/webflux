package com.mingle.webflux.demo;

import reactor.core.publisher.Mono;


/**
 * Created by mingle. Time 2019-05-05 17:45 Desc 文件描述
 */
public class ThenTest {
    
    public static void main(String[] args) {
        Mono<String> m=Mono.just("test");
        Mono<Void> v=m.then();
        v.doOnNext(x->System.out.println(x)).subscribe();
    
        Mono<String> m1=Mono.just("test1");
        Mono<Void> v1=m1.then();
        v1.doOnSuccess(x->System.out.println(x)).subscribe();
    }
}
