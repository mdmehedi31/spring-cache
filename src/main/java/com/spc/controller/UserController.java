package com.spc.controller;


import com.spc.entity.UserEntity;
import com.spc.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

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
}

