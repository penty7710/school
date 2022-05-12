package com.hut.jsj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeInfo {
    @ApiModelProperty("职称编号")
    private String gradeno;
    @ApiModelProperty("职称名字")
    private String levelname;
    @ApiModelProperty("等级编号")
    private String levelno;
}
