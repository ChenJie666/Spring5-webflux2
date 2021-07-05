package com.cj.webflux2.dao;

import com.cj.webflux2.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author CJ
 * @date 2021/7/5 11:54
 */
public interface UserDao {
    Mono<User> getUserById(int id);
    Flux<User> getAllUser();
    Mono<Void> saveUserInfo(Mono<User> user);
}
