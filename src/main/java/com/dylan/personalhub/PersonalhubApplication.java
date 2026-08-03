package com.dylan.personalhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.dylan.personalhub.mapper")
@SpringBootApplication
public class PersonalhubApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalhubApplication.class, args);
    }

}
