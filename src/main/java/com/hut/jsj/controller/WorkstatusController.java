package com.hut.jsj.controller;


import com.hut.jsj.pojo.WorkStatus;
import com.hut.jsj.service.impl.WorkStatusServiceImpl;
import com.hut.jsj.vo.ResponseBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "教学情况管理")
@RequestMapping("/workstatus")
public class WorkstatusController {
    @Autowired
    WorkStatusServiceImpl workStatusService;

    @PostMapping("/insert")
    public ResponseBean insertStatus(@RequestBody List<WorkStatus> workStatuses){
        int t = workStatusService.insert(workStatuses);
        if(t==0)
            return new ResponseBean(403,"添加失败",null);
        else
            return new ResponseBean(200,"添加成功",null);
    }

}
