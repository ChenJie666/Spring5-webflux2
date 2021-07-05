package com.cj.webflux2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CJ
 * @date 2021/7/5 11:08
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cj.dao"})
//@ImportResource(locations = {"classpath:bean.xml"})
public class WebfluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }
}
