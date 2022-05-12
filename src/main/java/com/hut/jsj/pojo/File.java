package com.hut.jsj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private Integer id;
    private String filename;
    private String addr;
    private String uploadtime;
    private String text;

    public File(String filename, String addr, String uploadtime,String text){
        this.filename = filename;
        this.addr = addr;
        this.uploadtime = uploadtime;
        this.text=text;
    }
}
