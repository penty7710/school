package com.hut.jsj.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Department {
    @ApiModelProperty("单位名称")
    private String  department;
    @ApiModelProperty("单位id")
    private String  departid;
}
