package com.example.liderit;

import com.example.liderit.utils.DBInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;

@SpringBootApplication
@EnableJpaRepositories("com.example.liderit.repository")
public class LiderItApplication {

    public static void main(String[] args) throws SQLException {
        if (args.length != 0)
            DBInit.initSportsDbIfNotExist(args[0], args[1]);
        SpringApplication.run(LiderItApplication.class, args);
    }
}
