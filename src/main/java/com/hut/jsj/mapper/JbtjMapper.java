package com.hut.jsj.mapper;


import com.hut.jsj.pojo.Jbtj;
import org.springframework.stereotype.Repository;

@Repository
public interface JbtjMapper {

    public Jbtj findByType(String type);
}
