package com.hut.jsj.service.impl;

import com.hut.jsj.dto.Applydata;
import com.hut.jsj.dto.PdfCode;
import com.hut.jsj.dto.PdfData;
import com.hut.jsj.mapper.PdfMapper;
import com.hut.jsj.pojo.ApplyData;
import com.hut.jsj.pojo.ApplyType;
import com.hut.jsj.pojo.GlType;
import com.hut.jsj.service.PdfService;
import com.hut.jsj.utils.PdfUtil;
import com.hut.jsj.utils.QRCodeUtil;
import com.hut.jsj.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    PdfUtil pdfUtil;

    @Autowired
    QRCodeUtil qrCodeUtil;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PdfMapper pdfMapper;

    @Autowired
    ApplyDataServiceImpl applyDataService;

    private static String address;

    //生成pdf
    @Override
    public String createPdf(PdfData pdfData) throws ParseException {
        //二维码的地址
        String addres;
        try {
            String information = pdfData.getName()+","+pdfData.getIdcard();
            addres = qrCodeUtil.createQrcode(information);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if(pdfData.getGxjs()==true){
            String idcard = pdfData.getIdcard();
            String applylevel = pdfData.getApplylevel();
            String applydepartment = pdfData.getDepartment();
            String applycode ="";
            int t = pdfData.getPdfCodes().size();
            int n=0;
            for (PdfCode pdfCode : pdfData.getPdfCodes()) {
                n++;
                if(pdfCode.getCode().equals(""))
                    continue;
                applycode+=pdfCode.getCode();
                if(n<t)
                    applycode+=",";
            }
             ApplyType applyType = new ApplyType(idcard, applydepartment, applylevel, applycode);
             pdfMapper.deleteapplytype(idcard);
             int i = pdfMapper.insertapplytype(applyType);
            if(i==0)
                return null;

            //先删除之前数据库中的申请数据
            applyDataService.delete(idcard);

            //增加申请数据到数据库
            List<ApplyData> list = new ArrayList<>();
            for (PdfCode pdfCode : pdfData.getPdfCodes()) {
                ApplyData applyData = new ApplyData();
                applyData.setIdcard(idcard);
                for (Applydata applydata : pdfCode.getApplydatas()) {
                    applyData.setName(applydata.getName());
                    applyData.setGroup(applydata.getGroup());
                    applyData.setPublishtime(applydata.getPublishtime());
                    applyData.setRank(applydata.getRank());
                    applyData.setTablehead(applydata.getTablehead());
                }
                if(pdfCode.getCode().equals(""))
                    applyData.setDatatype(1);
                else
                    applyData.setDatatype(2);
                applyData.setCode(pdfCode.getCode());
                list.add(applyData);
            }

            int m = applyDataService.insert(list);
            if(m==0)
                return null;
        }else{
            String idcard = pdfData.getIdcard();
            String applylevel = pdfData.getApplydj();
            String applydepartment = pdfData.getDepartment();
            GlType glType = new GlType(idcard, applylevel, applydepartment);
            pdfMapper.deletegltype(idcard);
            int i = pdfMapper.insertgltype(glType);
            if(i==0)
                return null;
        }

        try {
            if(pdfData.getGxjs()==true)
                address = pdfUtil.createPdf(pdfData,addres);
            else if(pdfData.getApplygw().equals("管理岗位"))
                address = pdfUtil.createPdfOfgg(pdfData,addres);
            else
                address = pdfUtil.createPdfOfgq(pdfData,addres);
        } catch (Exception e) {
            e.printStackTrace();
           return null;
        }
        int i = address.lastIndexOf("/");
        String substring = address.substring(i + 1);
        address = "/pdf/"+substring;
        return address;
    }

    //将pdf的地址，名字保存到数据库
    @Override
    public int insert() {
        String name = address.substring(5);
        String addres = "D:/project"+address;
        int t;
        String pdf = pdfMapper.findPdf(name);
        if(pdf!=null)
            pdfMapper.delete(name);
        try {
            t = pdfMapper.insert(name, addres);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return t;
    }

    //查看该用户的pdf
    @Override
    public String findPdf(String information) {
        //本来只想要姓名，但是后面想到可能存在重名的情况，因此还是需要姓名和身份证统一定位。
        String[] split = information.split(",");
        String addr = pdfMapper.findPdf(split[0]+split[1]+".pdf");

        if(addr == null)
            return addr;
        int t =addr.indexOf("/pdf");
        addr = addr.substring(t);
        return addr;
    }
}
