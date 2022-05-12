package com.hut.jsj.controller;


import com.hut.jsj.dto.CheckData;
import com.hut.jsj.pojo.ApplyData;
import com.hut.jsj.service.impl.ApplyDataServiceImpl;
import com.hut.jsj.utils.Iputil;
import com.hut.jsj.utils.RedisUtil;
import com.hut.jsj.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@Api(tags = "申请材料管理器")
@RequestMapping("/applydata")
public class ApplyDataController {

    @Autowired
    ApplyDataServiceImpl applyDataService;

    @Autowired
    RedisUtil redisUtil;
/*
    @PostMapping("insert")
    @ApiOperation("填写申请材料")
    public ResponseBean insert(@RequestBody ApplyData applyDatas) throws ParseException {
        int t = applyDataService.insert(applyDatas);
        if(t==0){
            return new ResponseBean(400,"添加错误",null);
        }else{
            return new ResponseBean(200,"添加成功",null);
        }
    }*/


    @PostMapping("check")
    @ApiOperation("审核申请材料")
    public ResponseBean check (@RequestBody CheckData checkData){
        int t;
        try {
            t = applyDataService.update(checkData.getCheckstatus(), checkData.getCheckpeople(),checkData.getName());
        } catch (ParseException e) {
            return new ResponseBean(403,"时间转换错误",null);
        }
        if(t==0)
            return new ResponseBean(403,"审核失败",null);
        else
            return new ResponseBean(200,"审核成功",null);
    }

}
