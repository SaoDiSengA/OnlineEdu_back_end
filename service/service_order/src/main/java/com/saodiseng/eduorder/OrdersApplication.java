package com.saodiseng.eduorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.saodiseng"})
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class,args);
    }
}
