package com.hut.jsj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applytypes {

    @ApiModelProperty("姓名")
    private String name;
    private String idcard;
    @ApiModelProperty("工作部门")
    private String department;
    @ApiModelProperty("现任职务")
    private String zyjsname1;
    @ApiModelProperty("取得日期")
    private String pzdate1;
    @ApiModelProperty("教师资格证号")
    private String tzigeid;
    @ApiModelProperty("申报等级")
    private String applylevel;
    @ApiModelProperty("申报代码")
    private String applycode;

}
