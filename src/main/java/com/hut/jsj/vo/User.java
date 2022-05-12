package com.hut.jsj.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel("用户简单实体类")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("身份证")
    private String idcard;
    @ApiModelProperty("学院")
    private String department;
    @ApiModelProperty("学院代码")
    private Integer departmentno;
    @ApiModelProperty("民族")
    private String mz;
    @ApiModelProperty("工作时间")
    private String worktime;
    @ApiModelProperty("职务")
    private String ZW;
    @ApiModelProperty("行政")
    private String xzlevel;
    @ApiModelProperty("职称1")
    private String ZYJSname1;
    @ApiModelProperty("职称1代码")
    private Integer ZYJSlevel1;
    @ApiModelProperty("职称1获得时间")
    private String pzdate1;
    @ApiModelProperty("教师编号？")
    private String tzigeid;
    @ApiModelProperty("职称2")
    private String ZYJSname2;
    @ApiModelProperty("职称2代码")
    private Integer ZYJSlevel2;
    @ApiModelProperty("职称2获得时间")
    private String pzdate2;
    @ApiModelProperty("工人职称")
    private String grjsname;
    @ApiModelProperty("工人代码")
    private Integer grjslevel;
    @ApiModelProperty("用户权限")
    private Integer power;
    @ApiModelProperty("岗位控制")
    private int gwkz [];

    public User(String name){
        this.name =name;
    }
}
