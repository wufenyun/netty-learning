package com.learning.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.learning.es")
@EnableWebMvc
public class EsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class,args);
    }
}
