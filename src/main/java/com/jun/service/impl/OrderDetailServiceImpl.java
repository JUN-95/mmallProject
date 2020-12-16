package com.jun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.entity.OrderDetail;
import com.jun.entity.Orders;
import com.jun.entity.User;
import com.jun.mapper.OrderDetailMapper;
import com.jun.mapper.OrdersMapper;
import com.jun.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.service.OrdersService;
import com.jun.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrdersService ordersService;


/*    public void getOrderDetail(HttpSession httpSession){
        QueryWrapper queryWrapper = new QueryWrapper<>();

        User user = (User) httpSession.getAttribute("userInfo");

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("user_id", user.getId());
        List<Orders> list = ordersMapper.selectList(queryWrapper1);
        OrderDetail orderDetail = null;
        for (Orders orders : list) {
            orderDetail = new OrderDetail();
            orderDetail.setOrderId(orders.getId());

            orderDetail.setCost(ord);
        }
    }*/

    @Override
    public List<List<OrderDetail>> getOrderDetailByOrderId(Integer userId){
        List<List<OrderDetail>> orderDetailsList = new ArrayList<>();

        List<Orders> ordersList = ordersService.getOrderList(userId);
        QueryWrapper queryWrapper = null;
        for (Orders orders : ordersList) {
            queryWrapper = new QueryWrapper();
            queryWrapper.eq("order_id", orders.getId());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectList(queryWrapper);
            orderDetailsList.add(orderDetailList);
        }
        return orderDetailsList;
    }
}
