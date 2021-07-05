package com.cj.webflux2.handler;

import com.cj.webflux2.entity.User;
import com.cj.webflux2.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * @author CJ
 * @date 2021/7/5 16:43
 */
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据id查询user
     *
     * @param request
     * @return Mono<ServerResponse>
     */
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<User> userMono = userService.getUserById(Integer.parseInt(id));

        return userMono.flatMap(user ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(user)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * 查询所有
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        Flux<User> users = userService.getAllUser();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(users,User.class);
    }

    /**
     * 添加
     */
    public Mono<ServerResponse> saveUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(this.userService.saveUserInfo(userMono));  // build方法进行订阅，如果发生变化则触发添加方法
    }

}
