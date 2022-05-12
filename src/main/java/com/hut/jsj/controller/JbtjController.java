package com.hut.jsj.controller;


import com.hut.jsj.pojo.Jbtj;
import com.hut.jsj.service.impl.JbtjServiceImpl;
import com.hut.jsj.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "基本条件管理")
@RestController
@RequestMapping("/jbtj")
public class JbtjController {

    @Autowired
    JbtjServiceImpl jbtjService;

    @GetMapping("/find")
    @ApiOperation("传入填报的四个类别，目前只写了高校教师的")
    public ResponseBean findByType(@RequestParam("type") String type){
        Jbtj jbtj = jbtjService.findByType(type);
        if(jbtj==null)
            return new ResponseBean(403,"查询失败",null);
        else
            return new ResponseBean(200,"查询成功",jbtj);
    }
}
