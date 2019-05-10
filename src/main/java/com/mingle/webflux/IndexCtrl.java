package com.mingle.webflux;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


/**
 * Created by mingle. Time 2019-05-09 16:37 Desc 文件描述
 */
@RestController
@Slf4j
public class IndexCtrl {
    
    @RequestMapping("index")
    public Mono<User> index() {
        log.info("index");
        User user = new User();
        user.setId("1");
        user.setName("mingle");
        log.info("result {}", user);
        return Mono.just(user);
    }
}
