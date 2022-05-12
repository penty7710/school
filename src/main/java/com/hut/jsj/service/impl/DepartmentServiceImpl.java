package com.hut.jsj.service.impl;

import com.hut.jsj.mapper.DepartmentMapper;
import com.hut.jsj.service.DepartmetnService;
import com.hut.jsj.utils.RedisUtil;
import com.hut.jsj.vo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmetnService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    @Override
    public List<Department> findTeacher(String department) {
        List<Department> list = departmentMapper.findTeacher();
        Department findspecial = departmentMapper.findspecial(department);
        if(findspecial!=null)
            list.add(findspecial);
        return list;
    }
}
