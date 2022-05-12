package com.hut.jsj.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//跨域问题
@Component
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        //设置哪些网页可以给我们发送请求
        httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("Origin"));
        //设置请求方式
        httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
        //设置请求结果缓存的时间
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        //设置请求头可以携带自定义的头信息
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
        //设置请求头是否可以携带验证信息
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
