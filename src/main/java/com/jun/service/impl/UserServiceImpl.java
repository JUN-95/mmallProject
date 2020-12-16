package com.jun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.enmus.GenderEnum;
import com.jun.enmus.ResultEnum;
import com.jun.entity.ProductCategory;
import com.jun.entity.User;
import com.jun.exception.MMallException;
import com.jun.mapper.UserMapper;
import com.jun.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user, UserService userService) {

        if (user.getSex() == 0) {
            user.setGender(GenderEnum.MALE);
        } else if (user.getSex() == 1) {
            user.setGender(GenderEnum.FEMALE);
        } else {
            throw new MMallException(ResultEnum.GENDER_VALUE_ERROR);
        }
        boolean save = userService.save(user);
        return save;
    }

    @Override
    public String login(User user, QueryWrapper queryWrapper, UserService userService, HttpSession httpSession) {
        queryWrapper.eq(true, "login_name", user.getLoginName());
        queryWrapper.eq(true, "password", user.getPassword());
        try {
            User one = userService.getOne(queryWrapper, true);      // 第二个参数为，如果查询出多条数据，抛异常，这里捕获
            if (one == null) {
                return "login";
            } else {
                httpSession.setAttribute("userInfo", one);           // 存入session中，在主页显示是否有数据，验证是否登录成功
                return "redirect:/";
            }
        } catch (Exception e) {
            System.out.println("用户查询出错，请重试！");
        }
        return null;
    }

}
