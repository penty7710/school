package com.hut.jsj.service.impl;

import com.hut.jsj.service.QRcodeService;
import com.hut.jsj.utils.QRCodeUtil;
import com.hut.jsj.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QRcodeServiceImpl implements QRcodeService {

    @Autowired
    QRCodeUtil qrCodeUtil;

    @Autowired
    RedisUtil redisUtil;


    @Override
    public String create(String ipAddr) {
        String address;
        try {
            String information = (String) redisUtil.get(ipAddr);
            address = qrCodeUtil.createQrcode(information);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  address;
    }
}
