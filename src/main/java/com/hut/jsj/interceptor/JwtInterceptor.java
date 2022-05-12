
package com.hut.jsj.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hut.jsj.utils.Jwtutil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

//jwt拦截器
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    Jwtutil jwtutil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        HashMap<String, Object> map = new HashMap<>();
        if(authorization!=null&&authorization.startsWith("Bearer ")){
            String token = authorization.substring(7);
            Claims claims = null;
            try {
                claims = jwtutil.parseJwt(token);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code",403);
                map.put("msg","登录已过期");
                String json= new ObjectMapper().writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(json);
                return false;
            }
            request.setAttribute("claims",claims);
            return true;
        }

        map.put("code",403);
        map.put("msg","用户未登录");
        String json= new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
        return false;
    }
}

