package com.jun.service.impl;

import com.jun.service.ProductCategoryService;
import com.jun.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    void getProductCategoryVO() {
        productCategoryService.getProductCategoryVO();
    }


}