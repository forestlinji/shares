package com.icecream.shares;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SharesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharesApplication.class, args);
    }

}
