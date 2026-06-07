package com.spc.service;

import com.spc.entity.UserEntity;
import com.spc.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Service;


@Service
@EnableCaching
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Key is SpEL
    @Cacheable(cacheNames = "user", key = "#userId")
    public UserEntity getUserEntityById(Integer userId){
        return this.userRepository.findById(userId).orElse(null);
    }
}
