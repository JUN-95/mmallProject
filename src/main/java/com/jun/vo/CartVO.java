package com.jun.vo;

import lombok.Data;

@Data
public class CartVO {
    private Integer id;
    private String name;
    private String fileName;
    private Integer quantity;
    private float cost;
    private Integer stock;
    private Float price;
    private Integer productId;

}
