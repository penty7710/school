package com.hut.jsj.service;

import com.hut.jsj.dto.Save1Data;
import com.hut.jsj.dto.Save2Data;
import com.hut.jsj.dto.UserLogin;
import com.hut.jsj.vo.Applytypes;
import com.hut.jsj.vo.Filing;
import com.hut.jsj.vo.Gltypes;
import com.hut.jsj.vo.User;

import java.util.List;

public interface AdminService {

    //查找用户
    public User findByusername(UserLogin adminLogin, String ipAddr);

    //增加xx为学院管理人
    public int addAdmin(String username);

    public int cleanpwd(String idcard);

    public Filing  view(String department);

    public List<Applytypes> audit1(String department);

    public List<Gltypes> audit2(String department);

    public int  save1(List<Save1Data> save1Data);

    public int save2(List<Save2Data>  save2Data);
}
