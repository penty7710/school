package com.hut.jsj.mapper;

import com.hut.jsj.pojo.XZlevel;
import com.hut.jsj.vo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoMapper {

    //根据code获取条件类型
    public List<Option> findBycode(String levelname);

    //根据当前的职称获取可以填写的职称类型
    public List<GradeInfo> getlevelnames(String levelno, char start);

    //根据当前职称获取可填写的必选条件
    public List<Bxtj> findbxtj(int codeno,int start);

    public List<XZlevel> findxzlevel(String levelNo);

    public List<XZlevel> findgrlevel(String grlevel);

    public List<OptionCode> getDescribe(List<String> code);

    public List<GradeInfo> findfteacher(String zyjslevel2);

    public List<Rxtj> getRxtj();

    //根据岗位名称获取对应代码
    public int findCodeNo(String levelname);

    public List<Workstatu> getWorkstatu(String idcard);

    public Second getBxtj(String idcard);

    public List<String> getCode(String idcard);

    public List<Second> findRxtj(String idcard);
}
