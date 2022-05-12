package com.hut.jsj.service;

import com.hut.jsj.pojo.XZlevel;
import com.hut.jsj.vo.*;

import java.util.List;

public interface InfoService {

    public List<Option> findBycode(String levelname);

    public List<GradeInfo> getlevelnames(String levelno);

    public List<Bxtj> findbxtj(String levelname);

    public List<XZlevel> findxzlevel(String levelNo);

    public List<XZlevel> findgrlevel(String grlevel);

    public List<OptionCode> getDescribe(List<String> code);

    public List<GradeInfo> findfteacher(String zyjslevel2);


    public List<Rxtj> getRxtj(String gwname);

    public List<Workstatu> getWorkstatu(String idcard);

    public Second getBxtj(String idcard);

    public List<String> getCode(String idcard);

    public List<Second> findRxtj(String idcard);

}
