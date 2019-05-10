package com.mingle.webflux.demo;

import org.reactivestreams.Publisher;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-05 17:25 Desc 文件描述
 */
public class DeferTest {
    
    public static void main(String[] args) {
        AtomicInteger subscribeTime = new AtomicInteger(1);
        //实现这一的效果，返回的数据流为1~5乘以当前subscribe的次数
        Supplier<? extends Publisher<Integer>> supplier = () -> {
            Integer[] array = {1, 2, 3, 4, 5};
            int currentTime = subscribeTime.getAndIncrement();
            for (int i = 0; i < array.length; i++) {
                array[i] *= currentTime;
            }
            return Flux.fromArray(array);
        };
        
        Flux<Integer> deferedFlux = Flux.defer(supplier);
        
        subscribe(deferedFlux, subscribeTime);
        subscribe(deferedFlux, subscribeTime);
        subscribe(deferedFlux, subscribeTime);
    }
    
    private static void subscribe(Flux<Integer> deferedFlux, AtomicInteger subscribeTime) {
        System.out.println("Subscribe time is "+subscribeTime.get());
        deferedFlux.subscribe(System.out::println);
    }
}
