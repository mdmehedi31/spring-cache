package com.spc;

import com.spc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class SpringCacheApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

      /* System.out.println(userService.getUserEntityById(1));
        System.out.println(userService.getUserEntityById(1));
        System.out.println(userService.getUserEntityById(2));
        System.out.println(userService.getUserEntityById(1));*/
    }
}
