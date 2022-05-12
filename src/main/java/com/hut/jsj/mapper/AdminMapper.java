package com.hut.jsj.mapper;

import com.hut.jsj.dto.Save1Data;
import com.hut.jsj.dto.Save2Data;
import com.hut.jsj.pojo.UserData;
import com.hut.jsj.vo.Applytypes;
import com.hut.jsj.vo.Gltypes;
import com.hut.jsj.vo.UserFiling;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminMapper {

    //查找用户
    public UserData findByusername(String username);

    //增加用户
    public int addAdmin(String idcard);

    //清零用户表的密码
    public int ucleanpwd(String teacherId);


    //查看填写情况
    public List<UserFiling> findfiling(String department);


    public List<Applytypes> audit1(String department);

    public List<Gltypes> audit2(String department);


    public int save1(Save1Data save1Data);

    public int save2(Save2Data save2Data);
}
