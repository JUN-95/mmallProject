package com.jun.controller;


import com.jun.entity.OrderDetail;
import com.jun.entity.Orders;
import com.jun.entity.User;
import com.jun.service.CartService;
import com.jun.service.OrderDetailService;
import com.jun.service.OrdersService;
import com.jun.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
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
@RequestMapping("//orderDetail")
public class OrderDetailController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderDetailService orderDetailService;
    // 测试
/*
    @GetMapping("/getOrderDetailByOrderId")
//    @ResponseBody
    public List<List<OrderDetail>> getOrderDetailByOrderId(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        User userInfo = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = new ArrayList<>();
        List<List<OrderDetail>> orderDetailByOrderId=null;
        if (userInfo != null) {   // 如果用户登录信息为空，在common.html中则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
            orderDetailByOrderId = orderDetailService.getOrderDetailByOrderId(userInfo.getId());
        }

        modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息
        modelAndView.addObject("orderDetailByOrderId", orderDetailByOrderId);
        modelAndView.setViewName("orderList");
        return orderDetailByOrderId;
}
*/



    @GetMapping("/getOrderDetailByOrderId")
    public ModelAndView getOrderDetailByOrderId(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        User userInfo = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = new ArrayList<>();
        List<List<OrderDetail>> orderDetailByOrderId = null;
        if (userInfo != null) {   // 如果用户登录信息为空，在common.html中则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
            orderDetailByOrderId = orderDetailService.getOrderDetailByOrderId(userInfo.getId());
        }

        modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息
        modelAndView.addObject("orderDetailByOrderId", orderDetailByOrderId);
        modelAndView.setViewName("orderList");
        return modelAndView;

    }


/*    @ResponseBody
    @GetMapping("/test")
    public List<List<Integer>> test(){
        Integer[] strArr = new Integer[3];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = i;
        }
        List<Integer> list = Arrays.asList(strArr);
        ArrayList<List<Integer>> listArrayList = new ArrayList<>();
        listArrayList.add(list);
        return listArrayList;
    }*/
//    @ResponseBody
    @GetMapping("/test")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        Integer[] strArr = new Integer[3];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = i;
        }
        List<Integer> list = Arrays.asList(strArr);
        ArrayList<List<Integer>> listArrayList = new ArrayList<>();
        listArrayList.add(list);
        modelAndView.addObject("listArrayList", listArrayList);
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @GetMapping("/test2")
    public ModelAndView test2(){
        ModelAndView modelAndView = new ModelAndView();
        Integer[] strArr = new Integer[3];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = i;
        }
        List<Integer> list = Arrays.asList(strArr);
        modelAndView.addObject("list", list);
        modelAndView.setViewName("test");
        return modelAndView;
    }
}

