package com.hut.jsj.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("管理岗位职称")
public class XZlevel {
    @ApiModelProperty("编号")
    private Integer levelno;
    @ApiModelProperty("职位：厅局级正职等")
    private String xzlevel;
    @ApiModelProperty("职位分级：正校级1档等")
    private List<String> gradeInfos;
}
