package com.hut.jsj.service.impl;

import com.hut.jsj.pojo.Jbtj;
import com.hut.jsj.mapper.JbtjMapper;
import com.hut.jsj.service.JbtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JbtjServiceImpl implements JbtjService {

    @Autowired
    JbtjMapper jbtjMapper;

    @Override
    public Jbtj findByType(String type) {
        return  jbtjMapper.findByType(type);
    }
}
