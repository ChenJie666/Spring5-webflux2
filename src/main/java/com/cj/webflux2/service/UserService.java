package com.cj.webflux2.service;

import com.cj.webflux2.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 创建接口定义操作方法
 * @author CJ
 * @date 2021/7/5 11:11
 */
public interface UserService {

    Mono<User> getUserById(int id);  // 返回0或1个，使用Mono

    Flux<User> getAllUser();  // 返回N个元素，使用Flux

    Mono<Void> saveUserInfo(Mono<User> user);

}
