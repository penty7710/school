package com.hut.jsj.service;

import com.hut.jsj.pojo.ApplyData;

import java.text.ParseException;
import java.util.List;

public interface ApplyDataService {

    public int insert (List<ApplyData> applyDatas) throws ParseException;


    public int update(String checkstatus, String checkpeople,String name) throws ParseException;

    public int delete (String idcard);
}
