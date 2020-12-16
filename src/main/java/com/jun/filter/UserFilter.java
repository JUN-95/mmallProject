package com.jun.filter;

import com.jun.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 判断session中是否有用户登录信息
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        User user = (User)httpServletRequest.getSession().getAttribute("userInfo");
        if(user!=null){
            chain.doFilter(request, response);
        }else{
            httpServletResponse.sendRedirect("/user/login");
        }
    }
}
