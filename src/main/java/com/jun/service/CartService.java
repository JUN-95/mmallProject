package com.jun.service;

import com.jun.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
public interface CartService extends IService<Cart> {

    public List<CartVO> findByCartVO(Integer userId);

    public boolean updateCart(String type, Integer id, Integer quantity, float cost, Integer productId);

    public boolean removeCart(Integer productId);

}
