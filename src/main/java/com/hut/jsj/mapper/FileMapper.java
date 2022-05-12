package com.hut.jsj.mapper;

import com.hut.jsj.pojo.File;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper {
    public int insert (List<File> fileList);

    public List<File> findAll();

    public int delFile(String filename);
}
