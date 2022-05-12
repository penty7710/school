package com.hut.jsj.service.impl;

import com.hut.jsj.mapper.InfoMapper;
import com.hut.jsj.pojo.XZlevel;
import com.hut.jsj.vo.*;
import com.hut.jsj.service.InfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    InfoMapper infoMapper;

    @Override
    public List<Option> findBycode(String levelname) {

        List<Option> options = infoMapper.findBycode(levelname);
        if(options.size()==0)
            return null;
        else return options;
    }

    @Override
    public List<GradeInfo> getlevelnames(String levelno) {
        char start = levelno.charAt(0);
        List<GradeInfo> gradeInfos = infoMapper.getlevelnames(levelno, start);
        if(gradeInfos.size()==0)
            return null;
        else
            return gradeInfos;
    }

    @Override
    public List<Bxtj> findbxtj(String levelname) {
        int codeNo = infoMapper.findCodeNo(levelname);
        int start = codeNo/1000;
        if(start==1&&codeNo>=1031)
            return null;
        if(start==2&&codeNo>=2031)
            return null;
        List<Bxtj> bxtjs = infoMapper.findbxtj(codeNo,start);
        return bxtjs;
    }

    @Override
    public List<XZlevel> findxzlevel(String levelNo) {
        if(levelNo==null||levelNo.equals("")||levelNo.equals("0"))
            levelNo="9999";
        List<XZlevel> xZlevels = infoMapper.findxzlevel(levelNo);
        return xZlevels;
    }

    @Override
    public List<XZlevel> findgrlevel(String grlevel) {
        if(grlevel==null||grlevel.equals("0")||grlevel.equals(""))
            grlevel="9999";
        List<XZlevel> xZlevels = infoMapper.findgrlevel(grlevel);

        return xZlevels;
    }

    @Override
    public List<OptionCode> getDescribe(List<String> code) {
        List<OptionCode> optionCodes = infoMapper.getDescribe(code);
        return optionCodes;
    }

    @Override
    public List<GradeInfo> findfteacher(String zyjslevel2) {
        if(zyjslevel2==null||zyjslevel2.equals("")||zyjslevel2.equals(""))
            zyjslevel2="9999";
        List<GradeInfo> gradeInfos = infoMapper.findfteacher(zyjslevel2);
        return gradeInfos;
    }

    @Override
    public List<Rxtj> getRxtj(String gwname) {
        List<Rxtj> rxtj = infoMapper.getRxtj();
        return rxtj;
    }

    @Override
    public List<Workstatu> getWorkstatu(String idcard) {
        List<Workstatu> workstatus = infoMapper.getWorkstatu(idcard);
        return workstatus;
    }

    @Override
    public Second getBxtj(String idcard) {
        Second bxtj = infoMapper.getBxtj(idcard);
        return bxtj;
    }

    @Override
    public List<String> getCode(String idcard) {
        List<String> code = infoMapper.getCode(idcard);
        return code;
    }

    @Override
    public List<Second> findRxtj(String idcard) {
        List<Second> rxtj = infoMapper.findRxtj(idcard);
        return  rxtj;
    }
}
