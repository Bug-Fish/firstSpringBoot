package com.example.demo0001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Demo0001Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo0001Application.class, args);
    }

}
