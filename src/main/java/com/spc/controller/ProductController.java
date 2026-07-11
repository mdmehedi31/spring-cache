package com.spc.controller;

import com.spc.entity.ProductEntity;
import com.spc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Integer id) {
        ProductEntity productEntity = this.productService.getProductById(id);
        return ResponseEntity.ok(productEntity);
    }
}
