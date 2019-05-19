package com.mingle.webflux.perf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


/**
 * Created by mingle. Time 2019-05-04 23:02 Desc 文件描述
 */
@RestController
@Slf4j
@RequestMapping("wbc-auth/oauth2")
public class AuthController {
    
    @RequestMapping("validate")
    public Mono<AuthValidateResp> validate(String appId, String sign) {
        log.info("auth webankAppId:{} - sign:{}", appId, sign);
        /*try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        AuthValidateResp resp = new AuthValidateResp();
        resp.setCode("0");
        resp.setResult(true);
        resp.setMsg("成功 by demo");
        resp.setTransactionTime(System.currentTimeMillis());
        log.info("resp: {}", resp);
        return Mono.just(resp).delayElement(Duration.ofMillis(5L));
    }
    
    @RequestMapping("access_token")
    public Mono<AuthTokenResp> token(String webankAppId, String sign) {
        log.info("auth webankAppId:{} - sign:{}", webankAppId, sign);
        AuthTokenResp resp = new AuthTokenResp();
        resp.setCode("0");
        resp.setAccessToken("token" + System.currentTimeMillis());
        return Mono.just(resp);
    }
    
    @RequestMapping("api_ticket")
    public Mono<AuthTicketResp> ticket(String webankAppId, String sign) {
        log.info("auth webankAppId:{} - sign:{}", webankAppId, sign);
        AuthTicketResp resp = new AuthTicketResp();
        resp.setCode("0");
        resp.setTickets("ticket" + System.currentTimeMillis());
        return Mono.just(resp);
    }
}
