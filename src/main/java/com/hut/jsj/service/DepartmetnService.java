package com.hut.jsj.service;

import com.hut.jsj.vo.Department;

import java.util.List;

public interface DepartmetnService {
    public List<Department> findAll();

    public List<Department> findTeacher(String department);
}
