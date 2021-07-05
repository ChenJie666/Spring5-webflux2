package com.cj.webflux2;

import com.cj.webflux2.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author CJ
 * @date 2021/7/5 19:49
 */
public class Client {
    public static void main(String[] args) {
        // 调用服务器地址
        WebClient client = WebClient.create("http://127.0.0.1:9343");

        String id = "1";
        // 根据di查询
        User user = client.get().uri("/getUser/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(User.class)
                .block();

        System.out.println(user);

        // 查询所有
        Flux<User> userFlux = client.get().uri("/getAllUser")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(User.class);

        userFlux.map(User::getName).buffer()
                .doOnNext(System.out::println)
                .blockFirst();

    }

}
