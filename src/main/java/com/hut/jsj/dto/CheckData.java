package com.hut.jsj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckData {
    @ApiModelProperty("作品名称")
    private String name;
    @ApiModelProperty("审核人")
    private String checkpeople;
    @ApiModelProperty("审核状态")
    private String checkstatus;
}
