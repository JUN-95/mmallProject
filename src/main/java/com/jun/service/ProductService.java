package com.jun.service;

import com.jun.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
public interface ProductService extends IService<Product> {
    public List<Product> getLevelProduct(Integer type, Integer id);
    public Product getProductOne(Integer id);
}
