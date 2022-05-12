package com.hut.jsj.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

@Component
public class QRCodeUtil {
    // 二维码尺寸
    private static final int QRCODE_SIZE = 500;
    // LOGO宽度
    private static final int WIDTH = 50;
    // LOGO高度
    private static final int HEIGHT = 50;

    public  String  createQrcode(String infomation) throws WriterException, IOException {
        String contents = infomation.split(",")[1];
        HashMap<EncodeHintType, Object> map = new HashMap<>();
        //设置字符集
        map.put(EncodeHintType.CHARACTER_SET,"utf-8");
        //设置纠错等级L/M/Q/H,等级越高越不容易识别，等级最高为H
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置边框
        map.put(EncodeHintType.MARGIN,2);
        String format = "png";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, map);

        Path file=new File("D:/project"+"/QRCode/"+infomation.split(",")[0]+infomation.split(",")[1]+".png").toPath();
        //Path file=new File("D:/"+infomation.split(",")[0]+".png").toPath();
        MatrixToImageWriter.writeToPath(bitMatrix,format,file);
        return file.toString();
    }
}