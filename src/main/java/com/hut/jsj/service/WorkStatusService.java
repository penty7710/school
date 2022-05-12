package com.hut.jsj.service;

import com.hut.jsj.pojo.WorkStatus;

import java.util.List;

public interface WorkStatusService  {
    public  int insert (List<WorkStatus> workStatus);


    public int delete(String idcard);
}
