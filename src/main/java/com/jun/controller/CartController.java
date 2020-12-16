package com.jun.controller;


import com.jun.entity.Cart;
import com.jun.entity.User;
import com.jun.entity.UserAddress;
import com.jun.service.CartService;
import com.jun.service.UserAddressService;
import com.jun.vo.CartVO;
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
@RequestMapping("//cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 功能是加入购物车 ---》 向数据库中添加数据
     * 先查询数据库中需要哪几个字段，这里是购物车的表，需要用户id，总支出，和数量
     * <p>
     * 这里是详情页点击加入购物车时，触发数据库添加操作，
     *
     * @param id       商品iD
     * @param price    单价
     * @param quantity 数量
     * @return
     */
//    @ResponseBody
    @GetMapping("/add/{id}/{price}/{quantity}")
    public String add(@PathVariable("id") Integer id, @PathVariable("price") float price, @PathVariable("quantity") Integer quantity, HttpSession httpSession) {

        Float cost = price * quantity;
        User user = (User) httpSession.getAttribute("userInfo");   // 从login登录时存入session的用户对象信息进行获取
        Integer userId = user.getId();  // 保证获取用户id是为了保证加入购物车时，是登录状态
        Cart cart = new Cart();
        cart.setCost(cost);
        cart.setQuantity(quantity);
        cart.setProductId(id);
        cart.setUserId(userId);
        boolean save = cartService.save(cart);    // 向数据库添加
//        System.out.println(save);
//        return "ok";
        return "redirect:/cart/getCartVOList";
    }

    /**
     * //  写好后找进入的入口哪里，这里是详情页点击加入购物车时，触发数据库添加操作，
     * //  之后重定向到购物车页面，即根据用户id查询购物车的全部商品
     *
     * @param httpSession
     * @return
     */
//    @ResponseBody
    @GetMapping("/getCartVOList")
    public ModelAndView getCartVOList(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        User userInfo = (User) httpSession.getAttribute("userInfo");

        // userId并不是从网址中传进来，而是从session中获取
        List<CartVO> cartVOList = new ArrayList<>();
        if (userInfo!=null){   // 如果用户登录信息为空，则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
        }
        modelAndView.addObject("cartVOListSize", cartVOList.size());

        modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息
        modelAndView.setViewName("settlement1");
        return modelAndView;
    }

    //购物车商品数量进行更改时，先从前端进行数据操作，然后保证数据库中的值先修改，再返回给前端页面的数据进行修改
    @ResponseBody
    @PostMapping("/updateCart/{type}/{id}/{quantity}/{cost}/{productId}")
    public String updateCart(
            @PathVariable("type") String type,
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity,
            @PathVariable("cost") float cost,
            @PathVariable("productId") Integer productId
    ) {

        // 修改购物车数量
        // 修改库存
        boolean res = cartService.updateCart(type, id, quantity, cost, productId);
        if (res) {
            return "success";
        }
        return "faild";
    }

    @GetMapping("/removeCart/{cartId}")
    public String removeCart(@PathVariable("cartId") Integer id){
        cartService.removeCart(id);
        return "redirect:/cart/getCartVOList";
    }

    @GetMapping("/goToSettlement2")
    public ModelAndView goToSettlement2(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = cartService.findByCartVO(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cartVOList", cartVOList);
        List<UserAddress> userAddressList = userAddressService.getUserAddress(httpSession);
        // 因为一起返回的页面是用一个，所以address的信息同上面的购物车信息一起返回到目的页面，就不用重新写一个controller了，这样重写一个也没有地方去调。当做入口
        modelAndView.addObject("userAddressList", userAddressList);
        modelAndView.setViewName("settlement2");

        return modelAndView;
    }

}

