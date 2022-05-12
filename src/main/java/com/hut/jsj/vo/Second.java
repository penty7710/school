package com.hut.jsj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Second {
    private String applylevel;

    private String applydepartment;

    @ApiModelProperty("作品名称")
    private String name;

    private String gettime;

    private String rank;

    private String group;
}
