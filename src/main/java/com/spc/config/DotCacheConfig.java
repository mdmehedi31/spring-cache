package com.spc.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotCacheConfig {

    @Bean
    public CacheManager dotCacheManager() {
        return new DotCacheManager();
    }
}
