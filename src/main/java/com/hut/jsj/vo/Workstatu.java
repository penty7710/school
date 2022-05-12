package com.hut.jsj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workstatu {

    @ApiModelProperty("开始时间")
    private String starttime;
    @ApiModelProperty("教学内容")
    private String content;
    @ApiModelProperty("课时")
    private String count;

}
