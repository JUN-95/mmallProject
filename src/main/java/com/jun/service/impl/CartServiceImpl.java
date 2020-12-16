package com.jun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.entity.Cart;
import com.jun.entity.Product;
import com.jun.mapper.CartMapper;
import com.jun.mapper.ProductMapper;
import com.jun.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.service.ProductService;
import com.jun.vo.CartVO;
import org.apache.ibatis.annotations.Param;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;


    /**
     * 将vo所要展示给前端的属性，用vo进行封装数据，可以理解为javabean，而vo中的数据则通过service进行提供
     * <p>
     * 一张数据库表对应一个mapper，所以我们要得到cart购物车的数据要从表中去拿，
     * 因为购物车时属于一个人的，所以通过userId去获取这个人的购物车的商品列表
     *
     * @return
     */
    @Override
    public List<CartVO> findByCartVO(Integer userId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        List<Cart> cartList = cartMapper.selectList(queryWrapper);

        List<CartVO> cartVOList = new ArrayList<>();
        CartVO cartVO = null;
        for (Cart cart : cartList) {
            cartVO = new CartVO();
            cartVO.setId(cart.getId());   // 购物车的一条商品信息的id
            cartVO.setCost(cart.getCost());
            cartVO.setQuantity(cart.getQuantity());
            cartVO.setProductId(cart.getProductId());  // 在购物车中的商品的id

            Product product = productMapper.selectById(cart.getProductId());
            cartVO.setStock(product.getStock());
            cartVO.setFileName(product.getFileName());
            cartVO.setName(product.getName());
            cartVO.setPrice(product.getPrice());
            cartVOList.add(cartVO);
        }

        return cartVOList;
    }

    /**
     * // 修改购物车数量
     * // 修改库存
     *
     * @param type
     * @param id
     * @param quantity
     * @param cost
     * @param productId
     * @return
     */
    @Override
    public boolean updateCart(String type, Integer id, Integer quantity, float cost, Integer productId) {
        Cart cart = cartMapper.selectById(id);
        Product product = productMapper.selectById(productId);
        Integer rawQuantity = cart.getQuantity();  // 原来购物车的数量
        Integer subVariableQuantity = rawQuantity - quantity;
        Integer addVariableQuantity = quantity - rawQuantity;
        Integer rawStock = product.getStock();
        cart.setQuantity(quantity);   // quantity在传过来时，已经变更好，直接用就行，是现在数量更改后的购物车的数量，
        cart.setCost(cost);
        switch (type) {
            case "sub":
                product.setStock(product.getStock() + subVariableQuantity);  // 现在的库存等于购物车数量变化数加原来库存
                break;
            case "add":
                product.setStock(rawStock - addVariableQuantity);
                break;
        }

        int c = cartMapper.updateById(cart); // 上面的setXX 只是更新到JavaBean中， 并没有持久化同步到数据库
        int p = productMapper.updateById(product);
        if (c == 1 && p == 1) {
            return true;
        }
        return false;
    }


    @Override
    public synchronized boolean removeCart(@Param("id") Integer cartId) {
        if (cartId != null) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id", cartId);
            Cart cart = cartMapper.selectOne(queryWrapper);
            Integer productId = cart.getProductId();

            Product product = productService.getProductOne(productId);
            Integer stock = product.getStock();
            Integer quantity = cart.getQuantity();
            cartMapper.deleteById(cartId);
            product.setStock(stock + quantity);
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("id", productId);
            productService.update(product, queryWrapper1);
            return true;
        }
        return false;
    }
}
