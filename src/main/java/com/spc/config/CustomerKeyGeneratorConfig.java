package com.spc.config;

import com.spc.cachekey.MyKey;
import org.jspecify.annotations.Nullable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class CustomerKeyGeneratorConfig implements KeyGenerator {

    private static final Object EMPTY_KEY = new Object();

    @Override
    public Object generate(Object target, Method method, @Nullable Object... params) {

        if(params == null || params.length == 0) {
            return EMPTY_KEY;
        }

        if(params.length == 1) {

            Class<?> type = params[0].getClass();
            if (type== MyKey.class) {


            }

            return params[0];
        }

        return Arrays.deepHashCode(params);
    }
}
