package com.hut.jsj.controller;


import com.alibaba.fastjson.JSON;
import com.hut.jsj.dto.UserLogin;
import com.hut.jsj.utils.Iputil;
import com.hut.jsj.vo.Admin;
import com.hut.jsj.vo.User;
import com.hut.jsj.service.impl.UserServiceImpl;
import com.hut.jsj.utils.Jwtutil;
import com.hut.jsj.utils.RedisUtil;
import com.hut.jsj.utils.VerifyCode;
import com.hut.jsj.vo.ResponseBean;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Random;

@RestController
@Slf4j
@Api(tags = "用户管理实体类")
@CrossOrigin()
@RequestMapping("/user")  //定义一个请求地址
public class Usercontroller {

    String id;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    VerifyCode verifyCode;

    @Autowired
    Jwtutil jwtutil;

    @PostMapping("/login")
    @ApiOperation("普通用户登录")
    public ResponseBean login(@RequestBody  UserLogin userLogin) {
        User user = userServiceImpl.findByidcard(userLogin, id);
        //不管有没有登录成功，均会返回一个对象，因此不能判断user是否为null
        String name = user.getName();

        if(name.equals("密码错误")||name.equals("验证码错误")||name.equals("用户名错误")){
            return new ResponseBean(403,name,null);
        }
        else{
            String token = jwtutil.creatToken(user);
            return new ResponseBean(200,"登录成功",token);
        }
    }



    @GetMapping("/verifycode")
    @ApiOperation("获取验证码")
    public void verifycode(HttpServletRequest request, HttpServletResponse response) {
        try {
            long l = System.currentTimeMillis();
            id= String.valueOf(l);
            String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            for(int i=0;i<6;i++){
                Random random = new Random();
                char c = randString.charAt(random.nextInt(randString.length()));
                id+=c;
            }
            verifyCode.getRandcode(request, response, id);//输出验证码图片方法
            System.out.println(id);
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
        } catch (Exception e) {
            log.info("获取图片失败");
            e.printStackTrace();
        }

    }

    @GetMapping("/userdata")
    @ApiOperation("获取用户信息")
    public ResponseBean userdata(HttpServletRequest request){
       String ipAddr = request.getHeader("originip");
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            Object user = claims.get("user");
            //将map中的类型转为User类
            User user1 = JSON.parseObject(JSON.toJSONString(user), User.class);

            if(user1.getPower()!=null)
                redisUtil.set(ipAddr+"admin",user1.getName()+","+user1.getIdcard()+","+user1.getDepartment()+','+user1.getPower(),60*60*10);

            else
                redisUtil.set(ipAddr+"user",user1.getName()+","+user1.getIdcard(),60*60*10);


            return new ResponseBean(200,"获取成功",user1);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseBean(403,"获取用户失败",null);
        }

    }

}
