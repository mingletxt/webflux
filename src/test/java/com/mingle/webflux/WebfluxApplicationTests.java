package com.mingle.webflux;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxApplicationTest.class)
public class WebfluxApplicationTests {
    
    @Test
    public void contextLoads() {
    }
    
    
    @Test
    public void testWebClient() {
        long time = System.currentTimeMillis();
        Mono<String> resp = WebClient.create().method(HttpMethod.GET).uri("http://127.0.0.1:9999//etl/timeout?name=mingle").retrieve().bodyToMono(String.class);
        //resp.subscribe(result -> System.out.println("resp:" + result));
        System.out.println(("result:" + resp.block() + " time: " + (System.currentTimeMillis() - time)));
    }
    
    @Test
    public void testWithCookie(){
        Mono<String> resp = WebClient.create()
                .method(HttpMethod.GET)
                .uri("http://baidu.com")
                .retrieve()
                .bodyToMono(String.class);
        resp.subscribe(System.out::println);
        resp.block();
        //System.out.println(("result:" + resp.block()));
    }
    
    @Test
    public void testPostParam() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("name1","value1");
        formData.add("name2","value2");
        BodyInserters.MultipartInserter multipartInserter = BodyInserters.fromMultipartData(formData);
        
        Mono<String> resp = WebClient.create().post()
                .uri("http://127.0.0.1:8000/demo/etl")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(multipartInserter)
                .retrieve().bodyToMono(String.class);
        
        resp.subscribe(System.out::println);
        resp.block();
    }
    
    @Test
    public void testBindToController() {
        WebTestClient testClient= WebTestClient.bindToController(new UserController()).build();
        final User user = new User();
        user.setId("123");
        user.setName("Test");
        user.setEmail("test@example.org");
        testClient.post().uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> System.out.println(new String(response.getResponseBody())))
                .jsonPath("name").isEqualTo("Test");
    }
}
