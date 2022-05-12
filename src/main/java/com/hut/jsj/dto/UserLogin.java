package com.hut.jsj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @ApiModelProperty("身份证或教师id")
    private String idcard;
    @ApiModelProperty("密码")
    private String pwd;
    @ApiModelProperty("验证码")
    private String yzm;
}
