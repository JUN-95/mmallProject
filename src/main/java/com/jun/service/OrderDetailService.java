package com.jun.service;

import com.jun.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
public interface OrderDetailService extends IService<OrderDetail> {
    public List<List<OrderDetail>> getOrderDetailByOrderId(Integer userId);
}
