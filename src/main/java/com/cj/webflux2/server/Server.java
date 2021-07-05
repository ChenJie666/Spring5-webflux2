package com.cj.webflux2.server;

import com.cj.webflux2.handler.UserHandler;
import com.cj.webflux2.service.impl.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.*;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;


/**
 * @author CJ
 * @date 2021/7/5 17:59
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    // 1.创建Router路由
    public RouterFunction<ServerResponse> routingFunction() {
        UserServiceImpl userService = new UserServiceImpl();
        UserHandler handler = new UserHandler(userService);

        // 配置访问路径与调用方法的映射
        RequestPredicate getUserPredicate = RequestPredicates
                .GET("/getUser/{id}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        RequestPredicate getAllUserPredicate = RequestPredicates
                .GET("/getAllUser")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        RequestPredicate saveUserPredicate = RequestPredicates
                .GET("/saveUser")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        return RouterFunctions.route(getUserPredicate, handler::getUserById)
                .andRoute(getAllUserPredicate, handler::getAllUser)
                .andRoute(saveUserPredicate, handler::saveUser);
    }

    // 2.创建服务器，完成适配
    public void createReactorServer() {
        // 获得路由
        RouterFunction<ServerResponse> route = routingFunction();
        // 获得handler的适配
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        // 创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();

    }

}
