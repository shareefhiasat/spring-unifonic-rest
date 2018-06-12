package com.unifonic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * START : You can try run or debug from here using spring boot
 */
@SpringBootApplication
@EnableCaching
public class UnifonicApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UnifonicApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UnifonicApplication.class, args);
    }
}
