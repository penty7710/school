package com.hut.jsj.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("申请的类别")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionCode {
    private String codeNo;
    private String describe;
    private String typename;
    private int count;
    private String tablehead;
}
