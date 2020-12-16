package com.jun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jun.mapper")
public class Springbootproject01Application {

    public static void main(String[] args) {
        SpringApplication.run(Springbootproject01Application.class, args);
    }

}
