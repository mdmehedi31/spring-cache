package com.spc.service;

import com.spc.cachekey.MyKey;
import com.spc.entity.UserEntity;
import com.spc.entity.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
class cacheLogic{

    private String userType;
    public boolean isCacheable(){

        if(userType.equalsIgnoreCase("admin")){
            return false;
        }else{
            return true;
        }
    }

    public void setUserType(String userType){
        this.userType = userType;
    }
}

@Service
@EnableCaching
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private cacheLogic cacheLogic;

    // Key is SpEL
    @Cacheable(cacheNames = "user", /*key = "#userId",*/ keyGenerator = "customerKeyGeneratorConfig")
    public UserEntity getUserEntityById(Integer userId, boolean cacheable) {
        return this.userRepository.findById(userId).orElse(null);
    }


    @Cacheable(cacheNames = "userEmailCh", key="#email" ,cacheManager = "caffeineCacheManager"/* keyGenerator = "customerKeyGeneratorConfig"*/)
    public UserEntity getUserEntityByEmail(String  email) {
        log.info("Get user by email: " + email);

        return this.userRepository.findByEmail(email);
    }

    @Cacheable(cacheNames = "user", key = "#emil",cacheManager = "redisCacheManager")
    public UserEntity fetchUserByEmail(String emil) {
        log.info("Fetch user by email: " + emil);
        return this.userRepository.findByEmail(emil);
    }

    public UserEntity getByEmail(String email) {
        return null; //this.getUserEntityByEmail(new MyKey(email));
    }
    /* If we use the parameter of then the condition format should be like --> #parameterValue, 1st method below .
       But if we generate the cache login by using the condition and user different class
       then we need to use this format @className.methodName 2nd method below
    */

    @Cacheable(cacheNames = "allUser", condition ="#cacheable==true" )
    public List<UserEntity> getAllUserEntity(boolean cacheable) {
        return this.userRepository.findAll();
    }


    @Cacheable(cacheNames = "conBasedUser", condition = "@cacheLogic.isCacheable")
    public List<UserEntity> getAllUserEntity() {
        return this.userRepository.findAll();
    }

    public void setUserEntity(String userType) {
        this.cacheLogic.setUserType(userType);
    }

/*    @Cacheable
    public List<UserEntity> getUsers(Boolean cacheable,String userType) {

        if(userType!=null && userType.equalsIgnoreCase("admin")){
            this.cacheLogic.setUserType(userType);
            return getAllUserEntity();
        }
        else if(cacheable!=null) {
         return   getAllUserEntity(cacheable);
        }
        return new ArrayList<UserEntity>();
    }*/

}
