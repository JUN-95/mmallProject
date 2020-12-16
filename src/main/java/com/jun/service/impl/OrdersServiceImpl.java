package com.jun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.entity.*;
import com.jun.mapper.CartMapper;
import com.jun.mapper.OrderDetailMapper;
import com.jun.mapper.OrdersMapper;
import com.jun.mapper.UserAddressMapper;
import com.jun.service.CartService;
import com.jun.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;


    @Override
    public List<Orders> getOrderList(Integer userId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        List<Orders> ordersList = ordersMapper.selectList(queryWrapper);
        return ordersList;
    }


    @Override
    public String getSerialnumber(){
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<32;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seriaNumber;
    }


    @Override
    public Orders createOrder(String selectAddress, float cost, User user, String address, String remark){
        Orders orders = new Orders();
        UserAddress userAddress = null;

        // 当用户选择添加新地址时，向订单提交新地址，然后向用户地址信息添加新地址
        if (selectAddress.equals("newAddress")){  // <input type="radio" name="selectAddress" value="newAddress"> 相当于key，value
            userAddress = new UserAddress();
            orders.setUserAddress(address);
            userAddress.setRemark(remark);
            userAddress.setAddress(address);
            userAddress.setUserId(user.getId());
            ordersMapper.clearDefaultAddress(user.getId());  // 清除之前的默认地址
            userAddress.setIsdefault(1);  // 设置默认地址
            userAddressMapper.insert(userAddress);
        }else{
            orders.setUserAddress(selectAddress);
        }
        orders.setCost(cost);
        orders.setLoginName(user.getLoginName());
        orders.setSerialnumber(getSerialnumber());
        orders.setUserId(user.getId());
        ordersMapper.insert(orders);

        OrderDetail orderDetail = null;

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", user.getId());
        List<Cart> cartList = cartMapper.selectList(queryWrapper);
        for (Cart cart : cartList) {
            orderDetail = new OrderDetail();
            orderDetail.setOrderId(orders.getId());
            orderDetail.setCost(cart.getCost());
            orderDetail.setProductId(cart.getProductId());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetailMapper.insert(orderDetail);
        }
        // 提交订单后，清空购物车
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("user_id", user.getId());
        cartMapper.delete(queryWrapper1);

        return orders;
    }
}
