package com.hut.jsj.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    @ApiModelProperty("身份证号")
    private String idcard;
    @ApiModelProperty("教师id")
    private String teacherid;
    @ApiModelProperty("密码")
    private String pwd;

    private String name;

    private String sex;

    private Date brith;
    @ApiModelProperty("民族")
    private String mz;
    @ApiModelProperty("学院")
    private String department;
    @ApiModelProperty("学院编号")
    private Integer departmentno;

    private Integer template;

    private Integer flag;
    @ApiModelProperty("教师编号")
    private String tzigeid;

    private String health;

    private String skilljob;

    private Date getdate;

    private String skillnum;

    private String teachernum;

    private Integer vip;

    private String joblevel;

    private String local;

    private Integer applytype;

    private String workyear;
    @ApiModelProperty("职务")
    private String zw;

    private String xzlevel;

    private Integer xzflag;

    private Date lxtime;

    private Date worktime;

    private String grjsname;

    private Integer grjslevel;

    private Integer grflag;

    private String zyjsname1;

    private Integer zyjslevel1;

    private Integer zyjsflag1;

    private Date pzdate1;

    private String zclevel;

    private String zyjsname2;

    private Integer zyjslevel2;

    private Integer zyjsflag2;

    private Date pzdate2;

    private String ndkhstatus;
    @ApiModelProperty("用户权限，0普通用户，1学院管理员 2 超级管理员")
    private Integer power;

}

