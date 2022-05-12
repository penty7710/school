package com.hut.jsj.controller;

import com.hut.jsj.dto.PdfData;
import com.hut.jsj.service.impl.PdfServiceImpl;
import com.hut.jsj.service.impl.UserServiceImpl;
import com.hut.jsj.utils.RedisUtil;
import com.hut.jsj.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("pdf")
@Api(tags = "pdf控制器")
public class PdfController {
    @Autowired
    PdfServiceImpl pdfService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserServiceImpl userService;

    static String ipAddr;

    @PostMapping("/create")
    @ApiOperation("创建pdf")
    public ResponseBean createPdf(@RequestBody PdfData pdfData, HttpServletRequest request) throws ParseException {
        String address = pdfService.createPdf(pdfData);
        if(address!=null){
            int t = pdfService.insert();
            if(t!=0){
                userService.updatewrite(pdfData.getIdcard());
                return new ResponseBean(200,"保存成功",address);
            }
            else
                return new ResponseBean(403,"保存失败",null);
        }
        else
            userService.updatewrite(pdfData.getIdcard());
            return new ResponseBean(403,"创建失败",null);
    }

    @GetMapping("/view")
    @ApiOperation("查看用户生成的pdf")
    public ResponseBean viewPdf(HttpServletRequest request){
        ipAddr=request.getHeader("originip");
        String information = (String) redisUtil.get(ipAddr+"user");
        if(information==null)
        {
            information = (String) redisUtil.get(ipAddr+"admin");
            String[] split = information.split(",");
            information = split[0]+","+split[1];
        }
        String addr = pdfService.findPdf(information);
        if(addr == null)
            return new ResponseBean(403,"未查询到数据",null);
        else
            return new ResponseBean(200,"查找成功",addr);
    }

}
