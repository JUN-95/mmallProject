package com.jun.controller;


import com.jun.entity.Orders;
import com.jun.entity.User;
import com.jun.service.CartService;
import com.jun.service.OrdersService;
import com.jun.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("//orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CartService cartService;

    /*

        @GetMapping("/goToOrderPage")
        public ModelAndView goToOrderPage(HttpSession httpSession){
            ModelAndView modelAndView = new ModelAndView();

            User userInfo = (User) httpSession.getAttribute("userInfo");
            List<CartVO> cartVOList = new ArrayList<>();

            if (userInfo!=null){   // 如果用户登录信息为空，在common.html中则获取不到购物车信息，会报错
                cartVOList = cartService.findByCartVO(userInfo.getId());
            }
            modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息
            modelAndView.setViewName("redirect:/orders/createOrder");
            return modelAndView;
        }
    */
    @GetMapping("/goToOrderPage")
    public ModelAndView goToOrderPage(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        User userInfo = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = new ArrayList<>();

        if (userInfo != null) {   // 如果用户登录信息为空，在common.html中则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
        }

        List<Orders> ordersList = ordersService.getOrderList(userInfo.getId());
        modelAndView.addObject("ordersList", ordersList);
        modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息

        modelAndView.setViewName("orderList");
        return modelAndView;
    }

    @PostMapping("/createOrder")
//    @ResponseBody
    public ModelAndView createOrder(String selectAddress, float cost, HttpSession httpSession, String address, String remark) {

        User user = (User) httpSession.getAttribute("userInfo");
        Orders orders = null;
        if (user != null) {
            orders = ordersService.createOrder(selectAddress, cost, user, address, remark);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("settlement3");
        User userInfo = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = new ArrayList<>();
        if (userInfo != null) {   // 如果用户登录信息为空，在common.html中则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
        }
        modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息

        return modelAndView;
    }
}

