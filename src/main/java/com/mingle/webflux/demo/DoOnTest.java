package com.mingle.webflux.demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;


/**
 * Created by mingle. Time 2019-05-03 16:32 Desc 文件描述
 */
public class DoOnTest  implements Subscriber<Integer> {
    
    static final Logger LOGGER = LoggerFactory.getLogger(DoOnTest.class);
    
    
    public static void main(String[] args) {
        Flux.range(1, 3).log()
                .doOnSubscribe(e -> LOGGER.info("doOnSubscribe:{}",e))
                .doOnRequest(e -> LOGGER.info("doOnRequest:{}",e))
                .doOnEach(e -> LOGGER.info("doOnEach:{}",e))
                .doOnNext(e -> LOGGER.info("doOnNext:{}",e))
                .doOnError(e -> LOGGER.info("doOnError:{}",e))
                .doOnTerminate(() -> LOGGER.info("doOnTerminate"))
                .doOnCancel(() -> LOGGER.info("doOnCancel"))
                .doOnComplete(() ->  LOGGER.info("doOnComplete"))
                .subscribe(new DoOnTest());
    
    }
    
    
    @Override
    public void onSubscribe(Subscription s) {
        LOGGER.info("request max");
        s.request(Integer.MAX_VALUE);
    }
    
    @Override
    public void onNext(Integer integer) {
        LOGGER.info("get data:{}",integer);
        
    }
    
    @Override
    public void onError(Throwable t) {
    
    }
    
    @Override
    public void onComplete() {
        LOGGER.info("onComplete");
    }
}
