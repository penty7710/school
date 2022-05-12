package com.hut.jsj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("管理岗和工勤岗审核的返回类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gltypes {
    private String name;

    private String idcard;

    private String department;

    @ApiModelProperty("现任职务")
    private String Zw;

    private String grjsname;

    private String worktime;

    private String applylevel;

}
