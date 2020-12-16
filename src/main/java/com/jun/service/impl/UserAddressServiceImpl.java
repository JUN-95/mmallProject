package com.jun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.entity.User;
import com.jun.entity.UserAddress;
import com.jun.mapper.UserAddressMapper;
import com.jun.service.UserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getUserAddress(HttpSession httpSession) {

        QueryWrapper queryWrapper = new QueryWrapper();
        User user = (User) httpSession.getAttribute("userInfo");
        queryWrapper.eq("user_id", user.getId());
        List<UserAddress> userAddressList = userAddressMapper.selectList(queryWrapper);
        return userAddressList;
    }
}
