package com.jun.service;

import com.jun.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
public interface OrdersService extends IService<Orders> {
    public String getSerialnumber();
    public Orders createOrder(String selectAddress, float cost, User user, String address, String remark);
    public List<Orders> getOrderList(Integer userId);
}
