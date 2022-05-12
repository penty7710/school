package com.hut.jsj.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("填写情况的用户数据")
public class UserFiling {
    private String name;
    private String sex;
    private String idcard;
    private String brith;
    private String write;
}
