package com.hut.jsj.config;

import com.hut.jsj.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //标识这是一个配置类
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，验证码后面的是为了不让swagger被拦截
        registry.addInterceptor(jwtInterceptor()).addPathPatterns()
                .excludePathPatterns("/user/login","/user/verifycode","/admin/login","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html");
    }

    //映射，当地址为/file/xxx,会向/usr/local/java/文件下找文件，注意java后面要添加/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       // registry.addResourceHandler("/file/**").addResourceLocations("file:/usr/local/java/");
        //registry.addResourceHandler("/pdf/**").addResourceLocations("file:/usr/local/java/pdf/");
        registry.addResourceHandler("/file/**").addResourceLocations("file:D:/project/");
        registry.addResourceHandler("/pdf/**").addResourceLocations("file:D:/project/pdf/");
    }
}
