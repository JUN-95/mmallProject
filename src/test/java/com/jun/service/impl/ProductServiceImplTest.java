package com.jun.service.impl;

import com.jun.entity.Product;
import com.jun.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {
    
    @Autowired
    private ProductService productService;
    @Test
    void getLevelProduct() {
        List<Product> levelOneProduct = productService.getLevelProduct(1,548);
        for (Product product : levelOneProduct) {
            System.out.println(product);
        }
    }
}