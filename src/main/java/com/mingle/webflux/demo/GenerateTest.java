package com.mingle.webflux.demo;

import java.util.ArrayList;
import java.util.Random;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 19:39 Desc 文件描述
 */
public class GenerateTest {
    
    public static void main(String[] args) {
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);
    
        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            System.out.println("generate " + value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }
}
