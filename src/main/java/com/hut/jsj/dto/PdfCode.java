package com.hut.jsj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("pdf必选条件和任选条件的集合")
public class PdfCode {
    @ApiModelProperty("材料代码")
    private String code;
    @ApiModelProperty("材料代码的具体含义，就是那一串文字")
    private String describe;
    @ApiModelProperty("applydate")
    private Applydata[] applydatas;
}
