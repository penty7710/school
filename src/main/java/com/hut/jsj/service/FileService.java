package com.hut.jsj.service;

import com.hut.jsj.pojo.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    public List<File> uploadfile(MultipartFile[] files, Boolean gswj,String text);

    public List<File> findAll();

    public int delFile(String filename);
}
