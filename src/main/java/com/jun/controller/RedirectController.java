package com.jun.controller;

import com.jun.entity.User;
import com.jun.service.CartService;
import com.jun.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RedirectController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/{url}")
    public String redirectUrl(@PathVariable("url") String url){
        return url;   // 通过url地址的拼接获取访问的资源，进行解析，比如对thymeleaf进行解析成html的代码
    }

    @GetMapping("/")
    public String index(){
        // 重定向到主页
        return "redirect:/productCategory/list";
    }
}
