package com.spc.service;

import com.spc.entity.UserEntity;
import com.spc.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;


@Service
@EnableCaching
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Cacheable(cacheNames = "user")
    public UserEntity getUserEntityById(Integer userId){
        return this.userRepository.findById(userId).orElse(null);
    }
}
