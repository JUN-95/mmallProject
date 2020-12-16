package com.jun.controller;


import com.jun.entity.Product;
import com.jun.entity.ProductCategory;
import com.jun.entity.User;
import com.jun.service.CartService;
import com.jun.service.ProductCategoryService;
import com.jun.vo.CartVO;
import com.jun.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("//productCategory")
public class ProductCategoryController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/listTest")   // 前端展示json数据
    @ResponseBody
    public List<ProductCategoryVO> listTest() {
        List<ProductCategoryVO> getProductCategoryVO = productCategoryService.getProductCategoryVO();

        return getProductCategoryVO;
    }


    @GetMapping("/list")
    public ModelAndView list(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        List<ProductCategoryVO> productCategoryVO = productCategoryService.getProductCategoryVO();
        modelAndView.addObject("productCategoryVO", productCategoryVO);
        User userInfo = (User) httpSession.getAttribute("userInfo");
        List<CartVO> cartVOList = new ArrayList<>();

        if (userInfo!=null){   // 如果用户登录信息为空，在common.html中则获取不到购物车信息，会报错
            cartVOList = cartService.findByCartVO(userInfo.getId());
        }

        modelAndView.addObject("cartVOList", cartVOList);// 在主页也收到显示购物车的数据信息
//        modelAndView.addObject("cartVOList", cartService.findByCartVO(userInfo.getId()));
        modelAndView.setViewName("main");
        return modelAndView;  // 这里main主页会和common进行映射
    }

}

