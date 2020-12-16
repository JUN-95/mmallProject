package com.jun.configuration;

import com.jun.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration    // 相当于xml文件
public class FilterConfiguration {

    @Bean  // 相当于xml文件中一个一个的bean标签
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new UserFilter());
        filterRegistrationBean.addUrlPatterns("/cart/*");
        filterRegistrationBean.addUrlPatterns("/user/list");
        filterRegistrationBean.addUrlPatterns("/user/logout");
        filterRegistrationBean.addUrlPatterns("/user/goToUserInfoPage");
        filterRegistrationBean.addUrlPatterns("/orders/*");
//        filterRegistrationBean.addUrlPatterns("/productCategory/list");
        return filterRegistrationBean;
    }
}
