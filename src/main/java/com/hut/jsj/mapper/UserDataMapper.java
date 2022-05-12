package com.hut.jsj.mapper;

import com.hut.jsj.pojo.UserData;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataMapper {

    UserData findByidcard(String idcard);

    int deleteByPrimaryKey(String idcard);

    int insert(UserData record);

    int insertSelective(UserData record);

    UserData selectByPrimaryKey(String idcard);

    int updateByPrimaryKeySelective(UserData record);

    int updateByPrimaryKey(UserData record);

    int updatewrite(String idcard);
}