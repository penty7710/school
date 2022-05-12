package com.hut.jsj.controller;


import com.hut.jsj.service.impl.DepartmentServiceImpl;
import com.hut.jsj.vo.Department;
import com.hut.jsj.vo.ResponseBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/department"})
@Api(tags={"返回归口单位信息"})
public class DepartmentController {
    @Autowired
    DepartmentServiceImpl departmentService;

    @GetMapping("/all")
    public ResponseBean findAll(){
        List<Department> departments = departmentService.findAll();
        if(departments==null)
            return new ResponseBean(403, "查询错误", null);
        else
            return new ResponseBean(200,"查询成功",departments);
    }

    @GetMapping("/teacher")
    public ResponseBean findTeacher(@RequestParam("department") String department){
        List<Department> list = departmentService.findTeacher(department);
        if(list.size()==0)
            return new ResponseBean(403,"查询失败",null);
        else
            return new ResponseBean(200,"查询成功",list);
    }
}
