package com.jun.controller;


import com.jun.entity.User;
import com.jun.entity.UserAddress;
import com.jun.mapper.OrdersMapper;
import com.jun.service.CartService;
import com.jun.service.UserAddressService;
import com.jun.vo.CartVO;
import com.jun.vo.UserAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private CartService cartService;

    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("/newUserAddress")
    public ModelAndView newUserAddress(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("settlement2");
        return modelAndView;
    }

    @GetMapping("/goToAddressListPage")
    public ModelAndView goToUserInfoPage(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        User userInfo = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = new ArrayList<>();

        if (userInfo != null) {   // 如果用户登录信息为空，在common.html中则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
        }
        modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息
        modelAndView.setViewName("userAddressList");
        return modelAndView;
    }

    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody UserAddressVO userAddressVO){
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userAddressVO.getUserId());
        userAddress.setAddress(userAddressVO.getAddress());
        userAddress.setRemark(userAddressVO.getRemark());
        if (userAddressVO.isIsdefault()){
            ordersMapper.clearDefaultAddress(userAddress.getUserId());
            userAddress.setIsdefault(1);
        }else {
            userAddress.setIsdefault(0);
        }
        boolean save = userAddressService.save(userAddress);
        if (save) {
            return "ok";
        }else{
            return "fail";
        }
    }

    @GetMapping("/getUserAddress")
    @ResponseBody
    public List<UserAddress> getUserAddress(HttpSession httpSession){
        List<UserAddress> userAddress = userAddressService.getUserAddress(httpSession);
        return userAddress;
    }


}

