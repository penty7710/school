package com.hut.jsj.controller;

import com.hut.jsj.pojo.File;
import com.hut.jsj.service.impl.FileServiceImpl;
import com.hut.jsj.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "文件")
@RequestMapping("/file")
@RestController
public class FileController {
    @Autowired
    FileServiceImpl uploadService;

    @ApiOperation("文件上传并保存地址到数据库,如果是用户提交申请文件，传一个false")
    @PostMapping("/upload")
    //根据gswj判断是否上传的是公示文件
    public ResponseBean uploadfile( @RequestParam("file") MultipartFile[] files, @RequestParam("gswj") Boolean gswj,@RequestParam("text") String text){
        List<File> gsFiles = uploadService.uploadfile(files,gswj,text);
        if(gsFiles==null)
            return new ResponseBean(403,"上传失败",null);
        else{
            int t = uploadService.insert(gsFiles);
            if(t==0)
                return new ResponseBean(403,"保存数据库失败",null);
            else
                return new ResponseBean(200,"上传成功",null);
        }
    }


    @GetMapping("findAll")
    @ApiOperation("将上传的文件进行回显")
    public ResponseBean findAll(){
        List<File> files = uploadService.findAll();
        if(files ==null)
            return new ResponseBean(403,"查询失败",null);
        else
            return new ResponseBean(200,"查询成功", files);
    }

    @GetMapping("delete")
    @ApiOperation("删除填报页展示出来的文件,需要传文件名字")
    public ResponseBean delFile(String filename){
        int t = uploadService.delFile(filename);
        if(t==0)
            return new ResponseBean(403,"删除失败",null);
        else
            return new ResponseBean(200,"删除成功",null);
    }
}
