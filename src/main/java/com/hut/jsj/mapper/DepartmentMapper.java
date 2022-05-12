package com.hut.jsj.mapper;

import com.hut.jsj.vo.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    public List<Department> findAll();

    public List<Department> findTeacher();

    public Department findspecial(String department);
}
