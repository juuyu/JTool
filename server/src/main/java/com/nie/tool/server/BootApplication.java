package com.nie.tool.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * @author njy
 * @since 2024/6/25 16:10
 */
@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(BootApplication.class, args);
    }
}
