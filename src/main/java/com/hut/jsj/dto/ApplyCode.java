package com.hut.jsj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApplyCode {
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

    private int datatype;
}
