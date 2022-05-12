package com.hut.jsj.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("教学情况")
public class WorkStatus {

    private Integer id;
    @ApiModelProperty("开始时间")
    private String starttime;
    @ApiModelProperty("结束时间")
    private String endtime;
    @ApiModelProperty("教学内容")
    private String content;
    @ApiModelProperty("课时")
    private String count;

    private String handlepeople;

    private String applytype;
    @ApiModelProperty("身份证")
    private String idcard;
    @ApiModelProperty("录入时间")
    private String intotime;
}
