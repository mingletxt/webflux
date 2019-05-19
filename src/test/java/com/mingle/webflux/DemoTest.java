package com.mingle.webflux;

import com.mingle.webflux.perf.DemoController;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.Resource;


/**
 * Created by mingle. Time 2019-05-06 11:43 Desc 文件描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {
    
    @Autowired
    ApplicationContext context;
    
    @Resource
    private DemoController demoController;
    
    private WebTestClient webClient;
    
    
    @Before
    public void setup() {
        webClient = WebTestClient.bindToApplicationContext(context).configureClient().build();
    }
    
    
    @Test
    public void testServer() {
        WebTestClient testClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:8888").build();
        testClient.method(HttpMethod.GET).uri("demo/foobar?name=mingle").exchange().expectStatus().isOk();
        
    }
    
    
    @Test
    public void testRoute() {
        RouterFunction bookRouter = RouterFunctions.route(RequestPredicates.GET("/hello"), request -> ServerResponse.ok().build());
        
        WebTestClient.bindToRouterFunction(bookRouter).build().get().uri("/hello").exchange().expectStatus().isOk()
                //.expectHeader().contentType(MediaType.TEXT_PLAIN)
                .expectBody().consumeWith(response -> {
            String result = new String(response.getResponseBody());
            System.out.println(new String(response.getResponseBody()));
            Assertions.assertThat(result.equals("Hello, City! @reactor-http-nio-2"));
            
        });
        //.expectBody().isEmpty();
        
    }
    
    
    @Test
    public void hello() {
        webClient.get().uri("/hello").attribute("name", "mm").exchange().expectStatus().isOk();
    }
    
    
    @Test
    public void bindToContext() {
        webClient.get().uri("/demo/foobar").attribute("name", "mm").exchange().expectStatus().isOk().expectBody().consumeWith(response -> {
            System.out.println("test:" + new String(response.getResponseBody()));
        });
    }
    
    
    @Test
    public void bindToController() {
        WebTestClient webTestClient = WebTestClient.bindToController(demoController).configureClient().build();
        webTestClient.get().uri("/demo/foobar").attribute("name", "mmm").exchange().expectStatus().isOk().expectBody().consumeWith(response -> {
            System.out.println("test:" + new String(response.getResponseBody()));
        });
    }
}