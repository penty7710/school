package com.hut.jsj.mapper;

import com.hut.jsj.dto.Applydata;
import com.hut.jsj.pojo.ApplyData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ApplyDataMapper {
    //增加
    int inserts(ApplyData applyDatas);

    int insertSelective(ApplyData record);

    //传入不同类型的参数，使用注解，在xml文件中直接使用#{xx}就行
    int update(@Param("checkstatus")String checkstatus, @Param("checktime")Date checkTime,
               @Param("checkpeopFle")String checkpeople,@Param("name")String name);

    int insert(ApplyData applyData);

    int deleteapplydata(String idcard);
}