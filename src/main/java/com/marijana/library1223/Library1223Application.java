package com.marijana.library1223;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class Library1223Application {

    public static void main(String[] args) {
        SpringApplication.run(Library1223Application.class, args);
    }

}
