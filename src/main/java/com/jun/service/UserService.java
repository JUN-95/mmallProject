package com.jun.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.vo.ProductCategoryVO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
public interface UserService extends IService<User> {
    public boolean register(User user, UserService userService);

    public String login(User user, QueryWrapper queryWrapper, UserService userService, HttpSession httpSession);

}
