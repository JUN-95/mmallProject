package com.jun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.entity.ProductCategory;
import com.jun.mapper.ProductCategoryMapper;
import com.jun.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.service.ProductService;
import com.jun.vo.ProductCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductService productService;

    @Override
    public List<ProductCategoryVO> getProductCategoryVO() {


        List<ProductCategory> lpcs1 = getProductCategoryLevel(1, 0);
        List<ProductCategory> lpcs2;
        List<ProductCategory> lpcs3;

        List<ProductCategoryVO> res = new ArrayList<>();
        List<ProductCategoryVO> res2;
        List<ProductCategoryVO> res3;

        ProductCategoryVO productCategoryVO1;
        ProductCategoryVO productCategoryVO2;
        ProductCategoryVO productCategoryVO3;
        int i = 0;

        for (ProductCategory lpc1 : lpcs1) {   // 先获取到所有一级的商品，遍历
            productCategoryVO1 = new ProductCategoryVO();    // 一个商品对应创建一个vo对象，vo负责装载id和name
            productCategoryVO1.setBanner("banner" + i + ".png");
            productCategoryVO1.setTop("top" + i + ".png");
            i++;
            productCategoryVO1.setProductList(productService.getLevelProduct(1, lpc1.getId()));
            BeanUtils.copyProperties(lpc1, productCategoryVO1);  // 利用BeanUtils将一级商品的属性，和vo属性相同的自动赋值
//            res.add(productCategoryVO1);

            lpcs2 = getProductCategoryLevel(2, productCategoryVO1.getId());  // 对级别为2，同时parentId为一级的id的sql进行筛选
            res2 = new ArrayList<>();  // 每个列表装载同个parentId的商品
            for (ProductCategory lpc2 : lpcs2) {    // 遍历二级所有的parentId， 意味着以下针对同个parentId进行操作，每个二级的相同parentId
                productCategoryVO2 = new ProductCategoryVO();
                BeanUtils.copyProperties(lpc2, productCategoryVO2);
                res2.add(productCategoryVO2);
                lpcs3 = getProductCategoryLevel(3, productCategoryVO2.getId());
                res3 = new ArrayList<>();    // 每个列表装载同个parentId的商品
                for (ProductCategory lpc3 : lpcs3) {
                    productCategoryVO3 = new ProductCategoryVO();
                    BeanUtils.copyProperties(lpc3, productCategoryVO3);
                    res3.add(productCategoryVO3);
                }
                productCategoryVO2.setChildren(res3);   // 将每个二级的相同parentId的children设为二级id与三级的parentId相同的商品
            }
            productCategoryVO1.setChildren(res2);
            res.add(productCategoryVO1);
        }
        return res;
    }

    @Override
    public List<ProductCategory> getProductCategoryLevel(Integer level, Integer parent) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type", level);
        queryWrapper.eq("parent_id", parent);
        return productCategoryMapper.selectList(queryWrapper);
    }

}
