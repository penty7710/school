package com.hut.jsj.dto;

import com.hut.jsj.pojo.WorkStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdfData {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("身份证")
    private String idcard;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("所属单位")
    private String xy;
    @ApiModelProperty("考核单位")
    private String department;
    @ApiModelProperty("职务1")
    private String ZYJSname1;
    @ApiModelProperty("职务2")
    private String ZYJSname2;
    @ApiModelProperty("职务1取得时间")
    private String pzdate1;
    @ApiModelProperty("职务2取得时间")
    private String pzdate2;
    @ApiModelProperty("工作时间")
    private String worktime;
    @ApiModelProperty("专业技术证书编号")
    private String zyzsid;
    @ApiModelProperty("申请岗位类别")
    private String applygw;
    @ApiModelProperty("申请岗位类型")
    private String applylevel;
    @ApiModelProperty("申请岗位等级")
    private String applydj;
    @ApiModelProperty("教师资格证书编号")
    private String tzigeid;
    @ApiModelProperty("年份")
    private List<String> years;
    @ApiModelProperty("工作情况集合")
    private List<WorkStatus> workStatusList;
    @ApiModelProperty("申请材料集合")
    private List<PdfCode> pdfCodes;
    @ApiModelProperty("是否填写必选条件")
    private Boolean bxtj;
    @ApiModelProperty("是否是高校教师")
    private Boolean gxjs;
    @ApiModelProperty("行政职务")
    private String zw;
    @ApiModelProperty ("工勤等级")
    private String grjsname;
}
