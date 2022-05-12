package com.hut.jsj.mapper;

import com.hut.jsj.pojo.WorkStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkstatusMapper {
    public int insert (List<WorkStatus> workStatus);


    public int delete(String idcard);
}
