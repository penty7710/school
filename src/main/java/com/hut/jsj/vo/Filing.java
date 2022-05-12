package com.hut.jsj.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@ApiModel("填写情况,count是完成人数，total是总人数")
public class Filing {
    private  int count ;
    private  int total;
    private List<UserFiling> list;
}
