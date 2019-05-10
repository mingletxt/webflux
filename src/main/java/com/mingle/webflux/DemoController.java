package com.mingle.webflux;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Date;

import javax.annotation.Resource;

import reactor.core.publisher.Mono;


/**
 * Created by mingle. Time 2019-04-23 16:57 Desc 文件描述
 */
@RequestMapping("/demo")
@RestController
public class DemoController {
    
    @Resource
    private HttpClient httpClient;
    
    @RequestMapping(value = "/foobar")
    public Mono<String> foobar(String name) {
        System.out.println("get name: " + name);
        Mono<String> mono = WebClient.create().method(HttpMethod.GET).uri("http://127.0.0.1:9999/etl").retrieve().bodyToMono(String.class);
        mono.subscribe(System.out::println);
        return mono;
    }
    
    @RequestMapping(value = "/baidu")
    @ResponseBody
    public Mono<String> baidu() {
       return httpClient.baidu();
    }
    
    @RequestMapping(value = "/response")
    public void response(ServerHttpRequest request, ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set("WWW-Authenticate", "Basic realm=\"Reactive mingle\"");
        response.writeWith(Mono.just(response.bufferFactory().wrap("{\"msg\":\"no token\"}".getBytes())));// TODO: 2019-04-24 https://www.programcreek.com/java-api-examples/index.php?api=org.springframework.http.server.reactive.ServerHttpResponse
    }
}
