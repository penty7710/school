package com.hut.jsj.service.impl;

import com.hut.jsj.dto.Save1Data;
import com.hut.jsj.dto.Save2Data;
import com.hut.jsj.dto.UserLogin;
import com.hut.jsj.mapper.AdminMapper;
import com.hut.jsj.pojo.UserData;
import com.hut.jsj.service.AdminService;
import com.hut.jsj.utils.RedisUtil;
import com.hut.jsj.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User findByusername(UserLogin userLogin, String ipAddr) {
        //拿到验证码
        String verifycode = (String) redisUtil.get(ipAddr);
        UserData user = adminMapper.findByusername(userLogin.getIdcard());
        /*if(!adminLogin.getYzm().equalsIgnoreCase(verifycode)){
            admin.setUsername("验证码错误");
            return admin;
        }*/

        if(user==null){
            return new User("用户名错误");
        }

        String password = userLogin.getPwd();
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
    public int addAdmin(String idcard) {
        try {
            return adminMapper.addAdmin(idcard);
        } catch (Exception e) {
            return -1;
        }

    }

    @Override
    public int cleanpwd(String teacherId) {
       return adminMapper.ucleanpwd(teacherId);
    }

    @Override
    public Filing view(String department) {
        List<UserFiling> list = new ArrayList<>();
        list = adminMapper.findfiling(department);
        int count=0;
        int total=0;
        for (UserFiling userFiling : list) {
            if(userFiling.getWrite().equals("1")){
                count++;
                userFiling.setWrite("完成申报");
            }else
                userFiling.setWrite("未完成申报");
            total++;
        }
        return new Filing(count,total,list);
    }


    @Override
    public List<Applytypes> audit1(String department) {
        return  adminMapper.audit1(department);
    }

    @Override
    public List<Gltypes> audit2(String department) {
        List<Gltypes> gltypes = adminMapper.audit2(department);
        return  gltypes;
    }

    @Override
    @Transactional
    public int save1(List<Save1Data> save1Data) {
        int n=0;
        for (Save1Data save1Datum : save1Data) {
            int i = adminMapper.save1(save1Datum);
            n+=i;
        }
        if(n<save1Data.size())
            return 0;
        else
            return 1;
    }

    @Override
    public int save2(List<Save2Data> save2Data) {
        int n=0;
        for (Save2Data save2Datum : save2Data) {
            int i = adminMapper.save2(save2Datum);
            n+=i;
        }
        if(n<save2Data.size())
            return 0;
        else
            return 1;
    }
}
