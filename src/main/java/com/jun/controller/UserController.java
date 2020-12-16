package com.jun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.enmus.GenderEnum;
import com.jun.enmus.ResultEnum;
import com.jun.entity.User;
import com.jun.exception.MMallException;
import com.jun.service.CartService;
import com.jun.service.UserService;
import com.jun.service.impl.UserServiceImpl;
import com.jun.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
@Controller
@RequestMapping("//user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) { // 前端表单post传过来对象

        if (user == null) {
            throw new MMallException(ResultEnum.NO_USER_EXCEPTION);
        }
        // 将业务代码写在service中

/*
        if (user.getSex() == 0) {
            user.setGender(GenderEnum.MALE);
        } else if (user.getSex() == 1) {
            user.setGender(GenderEnum.FEMALE);
        } else {
            throw new MMallException(ResultEnum.GENDER_VALUE_ERROR);
        }*/

        boolean save = this.userService.register(user, userService);
        if (!save) {
            throw new MMallException(ResultEnum.USER_SAVE_FAILD);
        }
        return "login";  // 注册成功后，返回登录页面
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession httpSession) {
        if (user == null) {
            throw new MMallException(ResultEnum.NO_USER_EXCEPTION);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        String view = userService.login(user, queryWrapper,userService, httpSession);
        return view;
    }

    // 测试
    @GetMapping("/list")
    public ModelAndView list(HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("list", this.userService.list());
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("/goToUserInfoPage")
    public ModelAndView goToUserInfoPage(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();

        User userInfo = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = new ArrayList<>();

        if (userInfo != null) {   // 如果用户登录信息为空，在common.html中则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
        }
        modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息
        modelAndView.setViewName("userInfo");
        return modelAndView;
    }
}

