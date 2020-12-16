package com.jun.vo;

import com.jun.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {    // 当想在前端显示的属性没有，就可以使用vo
    private Integer id;
    private String name;
    private List<ProductCategoryVO> children;

     private String banner;
     private String top;
     private List<Product> productList;   // 展示每个一级id的所有商品
}
