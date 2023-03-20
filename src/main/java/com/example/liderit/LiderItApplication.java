package com.example.liderit;

import com.example.liderit.utils.DBInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.liderit.repository")
public class LiderItApplication {

    public static void main(String[] args) {
        DBInit.createSportDbIfNotExist();
        SpringApplication.run(LiderItApplication.class, args);
    }





}
