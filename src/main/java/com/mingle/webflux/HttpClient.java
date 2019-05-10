package com.mingle.webflux;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


/**
 * Created by mingle. Time 2019-04-23 21:12 Desc 文件描述
 */
@Service
public class HttpClient {
    
    public Mono<String> baidu() {
         Mono<String> resp = WebClient.create().method(HttpMethod.GET).uri("http://127.0.0.1:9999/etl").retrieve().bodyToMono(String.class);
         resp.subscribe(System.out::println);
         resp.block();
         return resp;
    }
}
