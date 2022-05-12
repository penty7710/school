package com.hut.jsj.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("管理员")
public class Admin {

    @ApiModelProperty("编号")
    private int id;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("真实姓名")
    private String truename;

    @ApiModelProperty("类型")
    private int type;

    @ApiModelProperty("学院")
    private String department;


    public Admin(String username,String password,String truename,int type,String department){
        this.username=username;
        this.password=password;
        this.truename=truename;
        this.type=type;
        this.department=department;
    }
}
