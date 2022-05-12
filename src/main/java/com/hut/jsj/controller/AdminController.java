package com.hut.jsj.controller;

import com.hut.jsj.dto.Save1Data;
import com.hut.jsj.dto.Save2Data;
import com.hut.jsj.dto.UserLogin;
import com.hut.jsj.service.AdminService;
import com.hut.jsj.utils.Iputil;
import com.hut.jsj.utils.Jwtutil;
import com.hut.jsj.utils.RedisUtil;
import com.hut.jsj.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/admin")
@RestController
@Api(tags="管理员")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private  Jwtutil jwtutil;

    @Autowired
    private RedisUtil redisUtil;


    @PostMapping("/login")
    public ResponseBean login(@RequestBody UserLogin userLogin, HttpServletRequest request){
        String  ipAddr = request.getHeader("originip");
        /*String  ipAddr = Iputil.getIpAddr(request);*/
        User user = adminService.findByusername(userLogin, ipAddr);
        String name = user.getName();
        //找出用户名中是否包含错误字段
        if(name.equals("密码错误")||name.equals("验证码错误")||name.equals("用户名错误")){
            return new ResponseBean(403,name,null);
        }else{
            String token = jwtutil.creatToken(user);
            return new ResponseBean(200,"登录成功",token);
        }

    }

    @GetMapping("/add")
    @ApiOperation("指定xxx为学院管理员,传teacherId")
    public ResponseBean addAdmin(@RequestParam("username") String idcard,HttpServletRequest request){
        String  ipAddr = request.getHeader("originip");
        /*String ipAddr = Iputil.getIpAddr(request);*/
        String type = yzadmin(ipAddr);
        if(type.contains("该用户"))
            return new ResponseBean(403,type,null);
        //如果type不是等于2，说明不是学校管理员
        if(!type.equals("2"))
            return new ResponseBean(403,"权限不够",null);

        int t = adminService.addAdmin(idcard);
        if(t==0)
            return new ResponseBean(403,"该用户已经是管理员，请勿重复添加",null);
        else if(t==-1)
            return new ResponseBean(403,"添加失败",null);
        else
            return new ResponseBean(200,"添加成功",null);
    }

    @GetMapping("/clean")
    @ApiOperation("密码清零")
    public ResponseBean cleanpwd(@RequestParam("teacherId") String teacherId,HttpServletRequest request){
        String  ipAddr = request.getHeader("originip");
       /* String ipAddr = Iputil.getIpAddr(request);*/
        String type = yzadmin(ipAddr);
        if(type.contains("该用户"))
            return new ResponseBean(403,type,null);
        //如果type不是等于1，说明不是学校管理员
        if(!type.equals("2"))
            return new ResponseBean(403,"权限不够",null);

        int t = adminService.cleanpwd(teacherId);
        if(t==0)
            return new ResponseBean(403,"密码清零失败",null);
        else
            return new ResponseBean(200,"密码清零成功",null);
    }

    @GetMapping("view")
    @ApiOperation("查看查看情况")
    public ResponseBean view(HttpServletRequest request){
        String  ipAddr = request.getHeader("originip");
        String type = yzadmin(ipAddr);
        if(type.contains("该用户"))
            return new ResponseBean(403,type,null);
        String department = type.split(",")[2];
        String power = type.split(",")[3];
        if(power.equals("2"))
            department=null;
        Filing view = adminService.view(department);
        if(view==null)
            return new ResponseBean(403,"查询失败",null);
        else
            return new ResponseBean(200,"查询成功",view);
    }

    @GetMapping("/audit1")
    @ApiOperation("审核专业技术岗位")
    public ResponseBean audit1(HttpServletRequest request){
        /*String ipAddr = Iputil.getIpAddr(request);*/
        String  ipAddr = request.getHeader("originip");
        String type = yzadmin(ipAddr);
        if(type.contains("该用户"))
            return new ResponseBean(403,type,null);
        String[] split = type.split(",");
        String department = split[2];
        String power = split[3];
        if(power.equals("2"))
            department=null;
        List<Applytypes> applytypes = adminService.audit1(department);
        if(applytypes.size()==0)
            return new ResponseBean(403,"查询失败",null);
        else
            return new ResponseBean(200,"查询成功",applytypes);

    }

    @GetMapping("/audit2")
    @ApiOperation("审核工勤和管理岗")
    public  ResponseBean audit2(HttpServletRequest request){
        /*String ipAddr = Iputil.getIpAddr(request);*/
        String  ipAddr = request.getHeader("originip");
        String type = yzadmin(ipAddr);
        if(type.contains("该用户"))
            return new ResponseBean(403,type,null);
        String[] split = type.split(",");
        String department = split[2];
        String power = split[3];
        if(power.equals("2"))
            department=null;
        List<Gltypes> gltypes = adminService.audit2(department);
        if(gltypes.size()==0)
            return new ResponseBean(403,"查询失败",null);
        else
            return new ResponseBean(200,"查询成功",gltypes);
    }


    @PostMapping("/sava1")
    @ApiOperation("保存专技审核的数据")
    public ResponseBean save1 (@RequestBody List<Save1Data> save1Data){
        int t = adminService.save1(save1Data);
        if(t==0)
            return new ResponseBean(403,"保存失败",null);
        else
            return new ResponseBean(200,"保存成功",null);

    }


   @PostMapping("/save2")
   public ResponseBean save2 (@RequestBody List<Save2Data> save2Data){
       int t = adminService.save2(save2Data);
       if(t==0)
           return new ResponseBean(403,"保存失败",null);
       else
           return new ResponseBean(200,"保存成功",null);

   }


    public String yzadmin(String ipAddr){
        ipAddr += "admin";
        String admin  = (String) redisUtil.get(ipAddr);
        System.out.println(admin);
        if(admin==null)
            return "该用户不是管理员";
        return admin;
    }





}
