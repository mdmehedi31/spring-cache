package com.spc.service;


import com.spc.entity.ProductEntity;
import com.spc.entity.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@EnableCaching
@Service
public class ProductService {

    private ProductRepository productRepository;
    private static final Logger log= LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Cacheable(cacheNames = "produtCache")
    public ProductEntity getProductById(int id) {
        log.info("getProductById method called");
       for(int i=0; i<10; i++){
           log.info("DB CALLING : INDEX => "+i);
            this.productRepository.findById(id).get();
       }
       return this.productRepository.findById(id).get();
    }
}
