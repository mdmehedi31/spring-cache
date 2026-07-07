package com.spc.controller;


import com.spc.cachekey.MyKey;
import com.spc.entity.UserEntity;
import com.spc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/gets/{cacheable}")
    public List<UserEntity> getAllUsers(@PathVariable(required = false) Boolean cacheable) {
        return this.userService.getAllUserEntity(cacheable);
    }

    @GetMapping("/gets")
    public List<UserEntity> getAllUsersByType(@RequestParam String userType) {
        this.userService.setUserEntity(userType);
        return this.userService.getAllUserEntity();
    }


    @GetMapping("get-by-email")
    public UserEntity getUserEntityByEmail(@RequestParam String email) {
        log.info(" Controller 1");

        this.userService.getUserEntityByEmail(new MyKey(email));
        log.info(" Controller 2");
        this.userService.getUserEntityByEmail(new MyKey(email));
        log.info(" Controller 3");
        return this.userService.getUserEntityByEmail(new MyKey(email));
    }
}

