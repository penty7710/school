package com.hut.jsj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("必选条件")
public class Bxtj {
    @ApiModelProperty("岗位名称")
    private String gwname;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("表头")
    private String tablehead;
    @ApiModelProperty("需要填写的数量")
    private int count;
}
