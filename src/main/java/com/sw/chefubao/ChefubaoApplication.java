package com.sw.chefubao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sw.chefubao.mapper")
public class ChefubaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChefubaoApplication.class, args);
    }

}
