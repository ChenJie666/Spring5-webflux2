package com.cj.webflux2.service.impl;

import com.cj.webflux2.dao.UserDao;
import com.cj.webflux2.entity.User;
import com.cj.webflux2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author CJ
 * @date 2021/7/5 11:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Mono<User> getUserById(int id) {
        User user = new User(11, "11", "11",11 );
        return Mono.justOrEmpty(user);
    }

    @Override
    public Flux<User> getAllUser() {
        System.out.println("dao - getAllUser");
        User user = new User(11, "11", "11",11 );
        List<User> users = Arrays.asList(user);
        return Flux.fromIterable(users);
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(user -> {
//            jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge());
            System.out.println(user);
        }).thenEmpty(Mono.empty());
    }
}
