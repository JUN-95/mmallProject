package com.jun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.entity.Product;
import com.jun.mapper.ProductMapper;
import com.jun.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getLevelProduct(Integer type, Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();

        switch (type){
            case 1:
                queryWrapper.eq("categorylevelone_id", id);
                break;
            case 2:
                queryWrapper.eq("categoryleveltwo_id", id);
                break;
            case 3:
                queryWrapper.eq("categorylevelthree_id", id);
                break;
        }
        List<Product> list = productMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Product getProductOne(Integer id) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        Product product = productMapper.selectOne(queryWrapper);
        return product;
    }
}
