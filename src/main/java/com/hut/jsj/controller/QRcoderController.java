package com.hut.jsj.controller;

import com.hut.jsj.service.impl.QRcodeServiceImpl;
import com.hut.jsj.utils.Iputil;
import com.hut.jsj.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/qrcode")
@Api(tags = "二维码控制器")
public class QRcoderController {
    @Autowired
    QRcodeServiceImpl qRcodeService;

    @GetMapping("/create")
    @ApiOperation("创建二维码")
    public ResponseBean create(HttpServletRequest request){
        String ipAddr = request.getHeader("originip");
        ipAddr+="user";
        String address = qRcodeService.create(ipAddr);
        if(address!=null)
            return new ResponseBean(200,"创建成功",address);
        else
            return new ResponseBean(403,"创建失败",address);
    }
}
