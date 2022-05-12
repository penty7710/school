package com.hut.jsj.service.impl;

import com.hut.jsj.mapper.FileMapper;
import com.hut.jsj.pojo.File;
import com.hut.jsj.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    //上传文件
    @Override
    public List<File> uploadfile( MultipartFile[] files,  Boolean gswj, String text) {
        LinkedList<File> gsfilelist = new LinkedList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        if(files==null || files.length==0)
            return null;
        for (MultipartFile file : files) {
            //获取到文件的名字
            String filename = file.getOriginalFilename();
            String property = "D:/project";
            String path;
            //判断上传的是否是公示文件，将公式文件和用户上传文件保存到不同地方
            if(gswj==true)
                 path = property+"/gsfile";
            else
                path= property+"/userfile";
            java.io.File uploadfile = new java.io.File(path,filename);
            if(!uploadfile.exists())
                uploadfile.mkdirs();
            try {
                file.transferTo(uploadfile);
                gsfilelist.add(new File(filename,path,format,text));

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return gsfilelist;
    }


    //将文件数据保存到数据库
    public int  insert(List<File> files){
        int t = fileMapper.insert(files);
        if(t== files.size())
            return 1;
        else
            return 0;
    }


    //从数据库查询信息
    @Override
    public List<File> findAll(){
        List<File> gsfiles = fileMapper.findAll();
        if(gsfiles==null)
            return null;
        for (File file : gsfiles) {
            String addr = file.getAddr();
            //数据库文件地址格式为/usr/local/java/gsfile，定位到文件所在的文件夹
            int t = addr.lastIndexOf("/");
            String substring = addr.substring(t + 1);
            //拼接字符串  /file/gsfile/xxxx
            file.setAddr("/file/"+substring+"/"+file.getFilename());
        }
        return gsfiles;
    }

    @Override
    public int delFile(String filename) {
        int t = fileMapper.delFile(filename);
        if(t==0)
            return t;
        java.io.File file = new java.io.File("D:/project"+"/gsfile/" + filename);
        if(file.exists()){
            file.delete();
            return t;
        }
        return 0;
    }
}


