package com.hut.jsj.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyType {
    private String idcard;

    private String applydepartment;

    private String applylevel;

    private String applycode;
}