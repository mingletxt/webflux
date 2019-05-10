package com.mingle.webflux;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.Resource;

import reactor.core.publisher.Mono;


/**
 * Created by mingle. Time 2019-05-06 15:36 Desc 文件描述
 */
public class UserControllerTest {
    private final WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8888").build();
    
    @Test
    public void testCreateUser() throws Exception {
        final User user = new User();
        user.setId("123");
        user.setName("Test");
        user.setEmail("test@example.org");
        client.post().uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> System.out.println(new String(response.getResponseBody())))
                .jsonPath("name").isEqualTo("Test");
    }
}
