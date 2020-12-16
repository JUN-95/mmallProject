package com.jun.controller;


import com.jun.entity.Product;
import com.jun.entity.User;
import com.jun.service.CartService;
import com.jun.service.ProductCategoryService;
import com.jun.service.ProductService;
import com.jun.vo.CartVO;
import com.jun.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张军
 * @since 2020-11-21
 */
@Controller
@RequestMapping("//product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private CartService cartService;
//    @ResponseBody
    @GetMapping("/getProductList/{type}/{id}")
    public ModelAndView getProductList(@PathVariable("type") Integer type, @PathVariable("id") Integer id, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.getLevelProduct(type, id);
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("getProductDetail", this.productService.getProductOne(id));
        // 这里会传到productList页面， 而productList页面已经和common联系（拼接）在一起，所以传过去的对象是公用的
        modelAndView.addObject("productCategoryVO", this.productCategoryService.getProductCategoryVO());
        modelAndView.setViewName("productList");
        User userInfo = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = new ArrayList<>();
        if (userInfo!=null){   // 如果用户登录信息为空，则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
        }
        modelAndView.addObject("cartVOList", cartVOList);
        return modelAndView;
    }

//    @ResponseBody
    @GetMapping("/getProductDetail/{id}")
    public ModelAndView getProductDetail(@PathVariable("id") Integer id, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("getProductDetail", this.productService.getProductOne(id));
        modelAndView.addObject("productCategoryVO", this.productCategoryService.getProductCategoryVO());
        modelAndView.setViewName("productDetail");
        User userInfo = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = new ArrayList<>();
        if (userInfo!=null){   // 如果用户登录信息为空，则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
        }
        modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息
        return modelAndView;
    }

}

