package com.hut.jsj.pojo;

import com.hut.jsj.dto.ApplyCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyData {
    private Integer id;
    private Integer datatype;
    @ApiModelProperty("身份证")
    private String idcard;
    @ApiModelProperty("审核状态")
    private String checkstatus;
    @ApiModelProperty("审核时间")
    private Date checktime;
    @ApiModelProperty("审核人")
    private String checkpeople;
    @ApiModelProperty("填写时间")
    private Date intotime;
    @ApiModelProperty("发表时间")
    private String publishtime;
    @ApiModelProperty("作品名称")
    private String name;
    @ApiModelProperty("排名")
    private String rank;
    @ApiModelProperty("授予组织")
    private String group;
    @ApiModelProperty("表头")
    private String tablehead;
    @ApiModelProperty("代码")
    private String code;
}