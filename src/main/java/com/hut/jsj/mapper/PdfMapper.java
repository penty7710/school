package com.hut.jsj.mapper;

import com.hut.jsj.pojo.ApplyType;
import com.hut.jsj.pojo.GlType;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfMapper {

    public int insert(String name,String addres);

    public String findPdf(String name);

    public int delete(String name);

    public int insertapplytype(ApplyType applyType);

    public int insertgltype(GlType glType);

    public int deleteapplytype(String incard);

    public int deletegltype(String incard);
}
