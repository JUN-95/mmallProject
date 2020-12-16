package com.jun.service;

import com.jun.entity.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface UserAddressService extends IService<UserAddress> {
    public List<UserAddress> getUserAddress(HttpSession httpSession);
}
