package com.hut.jsj.service.impl;

import com.hut.jsj.dto.ApplyCode;
import com.hut.jsj.dto.Applydata;
import com.hut.jsj.mapper.ApplyDataMapper;
import com.hut.jsj.pojo.ApplyData;
import com.hut.jsj.service.ApplyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ApplyDataServiceImpl implements ApplyDataService {
    @Autowired
    ApplyDataMapper applyDataMapper;

    @Override
    @Transactional
    public int insert(List<ApplyData> applyDatas) throws ParseException {
        int n = 0;

        for (ApplyData applyData : applyDatas) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //将当前时间转换为指定格式的字符串
            String intotime = format.format(new Date());
            //将字符串转换为Date类型的  其实这里应该一早就把时间设置为string
            Date parse = format.parse(intotime);
            applyData.setIntotime(parse);

            int t = applyDataMapper.insert(applyData);
            n+=t;

        }
        if(n<applyDatas.size())
            return 0;
        else return 1;
    }


    @Override
    public int update(String checkstatus,String checkpeople,String name) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        String format = simpleDateFormat.format(new Date());
        Date parse = simpleDateFormat.parse(format);
        return applyDataMapper.update(checkstatus, parse, checkpeople,name);
    }

    @Override
    public int delete(String idcard) {
       return  applyDataMapper.deleteapplydata(idcard);
    }
}
