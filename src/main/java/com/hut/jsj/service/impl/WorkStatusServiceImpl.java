package com.hut.jsj.service.impl;

import com.hut.jsj.mapper.WorkstatusMapper;
import com.hut.jsj.pojo.WorkStatus;
import com.hut.jsj.service.WorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WorkStatusServiceImpl implements WorkStatusService {

    @Autowired
    WorkstatusMapper workstatusMapper;

    @Override
    public int insert(List<WorkStatus> workStatus) {
        String intotime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String idcard = null;
        for (WorkStatus status : workStatus) {
            status.setIntotime(intotime);
            idcard= status.getIdcard();
        }
        delete(idcard);
        int t = workstatusMapper.insert(workStatus);
        int size = workStatus.size();
        if(size==t)
            return 1;
        else return 0;
    }

    @Override
    public int delete(String idcard) {
       return  workstatusMapper.delete(idcard);
    }
}
