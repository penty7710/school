package com.hut.jsj.service.impl;

import com.hut.jsj.dto.UserLogin;
import com.hut.jsj.mapper.UserDataMapper;
import com.hut.jsj.vo.User;
import com.hut.jsj.pojo.UserData;
import com.hut.jsj.service.UserService;
import com.hut.jsj.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class  UserServiceImpl implements UserService {

    @Autowired
    UserDataMapper userDataMapper;

    @Autowired
    RedisUtil redisUtil;


    public User findByidcard( UserLogin userLogin, String ipAddr){
        //从redis获取验证码
        String verifycode = (String) redisUtil.get(ipAddr);
    /*    System.out.println(ipAddr);
        System.out.println(verifycode);
       if(!userLogin.getYzm().equalsIgnoreCase(verifycode)){
            //因为我这里需要返回一个User对象，所以我这里也把错误信息给封装成一个对象，注意在controller的时候的判断
            return new User("验证码错误");
        }*/
        UserData user = userDataMapper.findByidcard(userLogin.getIdcard());
        if(user==null)
            return new User("用户名错误");
        if(userLogin.getPwd().equals(user.getPwd())){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String worktime = null,pzdate1 = null,pzdate2 = null;
            if(user.getWorktime()!=null)
            worktime = simpleDateFormat.format(user.getWorktime());
            if(user.getPzdate1()!=null)
             pzdate1 = simpleDateFormat.format(user.getPzdate1());
            if(user.getPzdate2()!=null)
             pzdate2 = simpleDateFormat.format(user.getPzdate2());
            int gwkz [] = new int[4];
            if(user.getZyjsflag1()!=0)
                gwkz[0]=1;
            if(user.getZyjsflag2()!=0)
                gwkz[1]=1;
            if(user.getXzflag()!=0)
                gwkz[2]=1;
            if(user.getGrflag()!=0)
                gwkz[3]=1;
            return new User(
                    user.getName(),
                    user.getSex(),
                    user.getIdcard(),
                    user.getDepartment(),
                    user.getDepartmentno(),
                    user.getMz(),
                    worktime,
                    user.getZw(),
                    user.getXzlevel(),
                    user.getZyjsname1(),
                    user.getZyjslevel1(),
                    pzdate1,
                    user.getTzigeid(),
                    user.getZyjsname2(),
                    user.getZyjslevel2(),
                    pzdate2,
                    user.getGrjsname(),
                    user.getGrjslevel(),
                    user.getPower(),
                    gwkz
            );
        }
        else return new User("密码错误");
    }

    @Override
    public int updatewrite(String idcard) {
       return  userDataMapper.updatewrite(idcard);
    }
}
