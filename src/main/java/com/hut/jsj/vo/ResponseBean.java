package com.hut.jsj.vo;

import lombok.Data;

@Data
//返回状态信息的实体类
public class ResponseBean {
    //状态码
    private int code;
    //提示信息
    private String msg;
    //数据
    private Object data;

    public ResponseBean() {
    }

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
