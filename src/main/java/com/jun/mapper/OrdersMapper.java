package com.jun.mapper;

import com.jun.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    public void clearDefaultAddress(Integer userId);
}
