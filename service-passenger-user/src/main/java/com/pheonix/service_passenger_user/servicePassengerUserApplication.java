package com.pheonix.service_passenger_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.pheonix.service_passenger_user.mapper")
@SpringBootApplication
public class servicePassengerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(servicePassengerUserApplication.class,args);
    }
}
