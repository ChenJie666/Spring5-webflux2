package com.cj.webflux2.dao;

import com.cj.webflux2.entity.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author CJ
 * @date 2021/7/5 12:04
 */
@Component
public class UserDaoImpl implements UserDao {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Override
    public Mono<User> getUserById(int id) {
//        String sql = "select * from t_user where id=?";
//        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        User user = new User(11, "11", "11",11 );
        return Mono.justOrEmpty(user);
    }

    @Override
    public Flux<User> getAllUser() {
//        String sql = "select * from t_user";
//        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println("dao - getAllUser");
        User user = new User(11, "11", "11",11 );
        List<User> users = Arrays.asList(user);
        return Flux.fromIterable(users);
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        String sql = "insert into t_user(name,gender,age) values(?,?,?)";
        return userMono.doOnNext(user -> {
//            jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge());
            System.out.println(user);
        }).thenEmpty(Mono.empty());
    }

}
