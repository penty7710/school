package com.hut.jsj.utils;


import com.hut.jsj.dto.Applydata;
import com.hut.jsj.dto.PdfCode;
import com.hut.jsj.dto.PdfData;
import com.hut.jsj.pojo.WorkStatus;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 生成pdf
 */

@Component
public class PdfUtil {


    private static Font titlefont;
    private static Font headfont;
    private static Font keyfont;
    private static Font textfont;
    private static Font textsfont;

    // 最大宽度
    private static int maxWidth = 520;
    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            titlefont = new Font(bfChinese, 20, Font.NORMAL);
            headfont = new Font(bfChinese, 14, Font.BOLD);
            keyfont = new Font(bfChinese, 10, Font.BOLD);
            textfont = new Font(bfChinese, 10, Font.NORMAL);
            textsfont = new Font(bfChinese, 15, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成高校教师类的pdf文档
    public  String createPdf(PdfData pdfData,String addres) throws IOException, DocumentException   {
        //创建document对象
        Document document = new Document();
        String address = "D:/project/"+"/pdf/"+pdfData.getName()+pdfData.getIdcard()+".pdf";
        //获取PdfWriter实例
        PdfWriter.getInstance(document,new FileOutputStream(address));
        //打开文档
        document.open();

        //解决中文不显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChina18 = new Font(bfChinese, 18);
        Font fontChina12 = new Font(bfChinese, 12);

        //二维码
        Image image = Image.getInstance(addres);
        image.setAlignment(Image.RIGHT);
        document.add(image);

        //标题
        Paragraph paragraph = new Paragraph("湖南工业大学2021-2023年岗位定级申请表",titlefont);
        paragraph.setAlignment(1);//居中
        document.add(paragraph);

        Paragraph blank = new Paragraph(" ");
        document.add(blank);


        PdfUtil PdfUtil = new PdfUtil();

        //表格
        PdfPTable table = PdfUtil.creteTable(new float[] {30,120,100,120,100,120,120});

        // 个人信息
        table.addCell(PdfUtil.createCell(4,"个人信息"));
        table.addCell(PdfUtil.createCell("姓名"));
        table.addCell(PdfUtil.createCell(pdfData.getName()));
        table.addCell(PdfUtil.createCell("性别"));
        table.addCell(PdfUtil.createCell(pdfData.getSex()));
        table.addCell(PdfUtil.createCell("所属单位"));
        table.addCell(PdfUtil.createCell(pdfData.getXy()));

        table.addCell(PdfUtil.createCell("现任专业技术职务"));

        if(pdfData.getApplygw().equals("高校教师系列"))
            table.addCell(PdfUtil.createCell(pdfData.getZYJSname1()));
        else
            table.addCell(PdfUtil.createCell(pdfData.getZYJSname2()));
        table.addCell(PdfUtil.createCell("取得时间"));

        if(pdfData.getApplygw().equals("高校教师系列"))
            table.addCell(PdfUtil.createCell(pdfData.getPzdate1()));
        else
            table.addCell(PdfUtil.createCell(pdfData.getPzdate2()));

        table.addCell(PdfUtil.createCell("教师资格证书编号"));
        table.addCell(PdfUtil.createCell(pdfData.getTzigeid()));

        table.addCell(PdfUtil.createCell("申请岗位类别"));
        table.addCell(PdfUtil.createCell(pdfData.getApplygw()));
        table.addCell(PdfUtil.createCell("申请岗位等级"));
        table.addCell(PdfUtil.createCell(pdfData.getApplylevel()));
        table.addCell(PdfUtil.createCell("考核单位"));
        table.addCell(PdfUtil.createCell(pdfData.getDepartment()));


        table.addCell(PdfUtil.createCell("工作业绩取得的年度",2));

        String year = pdfData.getYears().toString();
        int length = year.length();
        year = year.substring(1, length - 1).replace(",","\t\t");
        table.addCell(PdfUtil.createCell(year,4));

        document.add(table);
        document.add(blank);

        //拿到所有的代码集合
        List<PdfCode> pdfCodes = pdfData.getPdfCodes();
        int size = pdfCodes.size();

        if(pdfData.getBxtj()==true){
            //必选条件
            PdfPTable table1 = PdfUtil.creteTable(new float[] {30,40,70,190,70,190,120});
            PdfCode pdfCode = pdfCodes.get(0);
            Applydata[] applydatas = pdfCode.getApplydatas();
            table1.addCell(PdfUtil.createCell(5,"必选条件"));
            table1.addCell(PdfUtil.createCell(pdfCode.getDescribe(),6,0));
            table1.addCell(PdfUtil.createCell("序号"));
            table1.addCell(PdfUtil.createCell("发表时间"));
            table1.addCell(PdfUtil.createCell(applydatas[0].getTablehead()));
            table1.addCell(PdfUtil.createCell("排名"));
            table1.addCell(PdfUtil.createCell("刊物名称"));
            table1.addCell(PdfUtil.createCell("审核人签字盖章"));
            int i=1;
            for (Applydata applydata : applydatas) {
                if(applydata.getName()==null||applydata.getName().equals(""))
                    continue;
                String s = Integer.toString(i);
                String publishtime = applydata.getPublishtime().substring(0, 4);
                table1.addCell(PdfUtil.createCell(s));
                table1.addCell(PdfUtil.createCell(publishtime));
                table1.addCell(PdfUtil.createCell(applydata.getName()));
                table1.addCell(PdfUtil.createCell(applydata.getRank()));
                table1.addCell(PdfUtil.createCell(applydata.getGroup()));
                table1.addCell(PdfUtil.createCell(" "));
                i++;
            }
            pdfCodes.remove(0);
            document.add(table1);
            document.add(blank);
        }

        size = pdfCodes.size();
        //任选条件
        PdfPTable table2 = PdfUtil.creteTable(new float[] {30,40,70,190,70,190,120});


        //根据前端传过来的数据动态生成条数，先获取一共有多少个不同的代码，再获取任选代码的条数。
        int sum=0;
        for(int t=0; t<size; t++){
            Applydata[] applydatas1 = pdfCodes.get(t).getApplydatas();
            sum+=applydatas1.length;
        }
        table2.addCell(PdfUtil.createCell(2*size+sum,"任选条件"));


        for (int t=0;t<size;t++) {
            int n =1;
            PdfCode pdfCode1 = pdfCodes.get(t);
            Applydata[] applydata = pdfCode1.getApplydatas();
            table2.addCell(createCell(pdfCode1.getCode()+pdfCode1.getDescribe(),6,0));
            table2.addCell(createCell("序号"));
            table2.addCell(createCell("发表时间"));
            table2.addCell(createCell(applydata[0].getTablehead()));
            table2.addCell(createCell("排名"));
            table2.addCell(createCell("授予组织"));
            table2.addCell(createCell("审核人签字盖章"));
            for (Applydata applydatum : applydata) {
                if(applydatum.getName()==null||applydatum.getName().equals(""))
                    continue;
                applydatum.setPublishtime(applydatum.getPublishtime().substring(0,4));
                PdfUtil.createTable(table2,applydatum,n++);
            }
        }
        document.add(table2);


        document.add(blank);
        //教学情况
        PdfPTable table3 = PdfUtil.creteTable(new float[] {30,40,70,260,190,120});
        table3.addCell(PdfUtil.createCell(4,"教学情况"));
        table3.addCell(PdfUtil.createCell("序号"));
        table3.addCell(PdfUtil.createCell("时间"));
        table3.addCell(PdfUtil.createCell("课程名称"));
        table3.addCell(PdfUtil.createCell("工作量"));
        table3.addCell(PdfUtil.createCell("审核人签字盖章"));


        List<WorkStatus> workStatusList = pdfData.getWorkStatusList();
        int t = workStatusList.size();
        int id =1;
        for (WorkStatus workStatus : workStatusList) {
            PdfUtil.createTable(table3,id++,workStatus);
        }
        document.add(table3);
        document.add(blank);
        document.add(blank);
        document.add(blank);
        document.add(blank);

       String url="D:/project/img";

        //个人承诺
        PdfPTable table4 = PdfUtil.creteTable(new float[]{30, 680});
        table4.addCell(PdfUtil.createCell("个人承诺"));
        Image image1 = Image.getInstance(url+"/本人承诺.png");
        PdfPCell cell = PdfUtil.createCell("",true);
        cell.setImage(image1);

        table4.addCell(cell);
        document.add(table4);
        document.add(blank);
        document.add(blank);

        //所在单位推荐意见
        PdfPTable table5 = PdfUtil.creteTable(new float[]{30, 680});
        table5.addCell(PdfUtil.createCell("所在单位推荐意见"));
        Image image2 = Image.getInstance(url + "/所在单位意见.png");
        PdfPCell cell1 = PdfUtil.createCell("",true);
        cell1.setImage(image2);
        table5.addCell(cell1);
        document.add(table5);
        document.add(blank);
        document.add(blank);


        PdfPTable table6 = PdfUtil.creteTable(new float[]{30, 800});
        table6.addCell(PdfUtil.createCell("学校岗位定级工作领导小组意见"));
        Image image3 = Image.getInstance(url + "/学校意见.png");
        PdfPCell cell2 = PdfUtil.createCell("",true);
        cell2.setImage(image3);
        //关闭
        table6.addCell(cell2);
        document.add(table6);
        document.close();
        return address;
    }


    //生成管理岗位的pdf文档
    public String createPdfOfgg(PdfData pdfData,String addres) throws IOException, DocumentException {
        //创建document对象
        Document document = new Document();
        String address ="D:/project"+"/pdf/"+pdfData.getName()+pdfData.getIdcard()+".pdf";
        //获取PdfWriter实例
        PdfWriter.getInstance(document,new FileOutputStream(address));
        //打开文档
        document.open();

        //解决中文不显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChina18 = new Font(bfChinese, 18);
        Font fontChina12 = new Font(bfChinese, 12);

        //二维码
        Image image = Image.getInstance(addres);
        image.setAlignment(Image.RIGHT);

        document.add(image);

        //标题
        Paragraph paragraph = new Paragraph("湖南工业大学2021-2023年岗位定级申请表",titlefont);
        paragraph.setAlignment(1);//居中
        document.add(paragraph);

        Paragraph blank = new Paragraph(" ");
        document.add(blank);


        PdfUtil PdfUtil = new PdfUtil();

        //表格
        PdfPTable table = PdfUtil.creteTable(new float[] {30,120,100,120,100,120,120});

        // 个人信息
        table.addCell(PdfUtil.createCell(4,"个人信息"));
        table.addCell(PdfUtil.createCell("姓名"));
        table.addCell(PdfUtil.createCell(pdfData.getName()));
        table.addCell(PdfUtil.createCell("性别"));
        table.addCell(PdfUtil.createCell(pdfData.getSex()));
        table.addCell(PdfUtil.createCell("所属单位"));
        table.addCell(PdfUtil.createCell(pdfData.getDepartment()));


        table.addCell(PdfUtil.createCell("参加工作时间"));
        table.addCell(PdfUtil.createCell(pdfData.getWorktime()));

        table.addCell(PdfUtil.createCell("现任行政职务"));
        table.addCell(PdfUtil.createCell(pdfData.getZw()));

        table.addCell(PdfUtil.createCell("身体状况"));
        table.addCell(PdfUtil.createCell("健康"));



        table.addCell(PdfUtil.createCell("申请岗位类别"));
        table.addCell(PdfUtil.createCell(pdfData.getApplygw()));

        table.addCell(PdfUtil.createCell("申请岗位类型"));
        table.addCell(PdfUtil.createCell(pdfData.getApplylevel()));

        table.addCell(PdfUtil.createCell("申请岗位等级"));
        table.addCell(PdfUtil.createCell(pdfData.getApplydj()));


        document.add(table);
        document.add(blank);

        PdfPTable table1 = PdfUtil.creteTable(new float[]{30,40,70,260,190,120});
        table1.addCell(PdfUtil.createCell(4,"聘任情况"));
        table1.addCell(PdfUtil.createCell("序号"));
        table1.addCell(PdfUtil.createCell("起止时间"));
        table1.addCell(PdfUtil.createCell("工作部门及岗位"));
        table1.addCell(PdfUtil.createCell("行政职务"));
        table1.addCell(PdfUtil.createCell("证明人签字"));

        List<WorkStatus> workStatusList = pdfData.getWorkStatusList();
        int t = workStatusList.size();
        int id =1;
        for (WorkStatus workStatus : workStatusList) {
            PdfUtil.createTable(table1,id++,workStatus);
        }
        document.add(table1);
        document.add(blank);

        String url="D:/project/img";

        //个人承诺
        PdfPTable table4 = PdfUtil.creteTable(new float[]{30, 680});
        table4.addCell(PdfUtil.createCell("个人承诺"));
        Image image1 = Image.getInstance(url+"/本人承诺.png");
        image1.scaleAbsoluteHeight(400);
        PdfPCell cell = PdfUtil.createCell("",true);
        cell.setImage(image1);
        table4.addCell(cell);
        document.add(table4);
        document.add(blank);

        //所在单位推荐意见
        PdfPTable table5 = PdfUtil.creteTable(new float[]{30, 680});
        table5.addCell(PdfUtil.createCell("所在单位推荐意见"));
        Image image2 = Image.getInstance(url + "/所在单位意见.png");
        PdfPCell cell1 = PdfUtil.createCell("",true);
        cell1.setImage(image2);
        table5.addCell(cell1);
        document.add(table5);
        document.add(blank);

        PdfPTable table6 = PdfUtil.creteTable(new float[]{30, 680});
        table6.addCell(PdfUtil.createCell("学校岗位定级工作领导小组意见"));
        Image image3 = Image.getInstance(url + "/学校意见.png");
        PdfPCell cell2 = PdfUtil.createCell("",true);
        cell2.setImage(image3);
        table6.addCell(cell2);
        document.add(table6);
        //关闭
        document.close();
        return address;
    }


    public String createPdfOfgq(PdfData pdfData,String addres) throws IOException, DocumentException {
        //创建document对象
        Document document = new Document();
        String address ="D:/project"+"/pdf/"+pdfData.getName()+pdfData.getIdcard()+".pdf";
        //获取PdfWriter实例
        PdfWriter.getInstance(document,new FileOutputStream(address));
        //打开文档
        document.open();

        //解决中文不显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChina18 = new Font(bfChinese, 18);
        Font fontChina12 = new Font(bfChinese, 12);

        //二维码
        Image image = Image.getInstance(addres);
        image.setAlignment(Image.RIGHT);

        document.add(image);

        //标题
        Paragraph paragraph = new Paragraph("湖南工业大学2021-2023年岗位定级申请表",titlefont);
        paragraph.setAlignment(1);//居中
        document.add(paragraph);

        Paragraph blank = new Paragraph(" ");
        document.add(blank);


        PdfUtil PdfUtil = new PdfUtil();

        //表格
        PdfPTable table = PdfUtil.creteTable(new float[] {30,120,100,120,100,120,120});

        // 个人信息
        table.addCell(PdfUtil.createCell(4,"个人信息"));
        table.addCell(PdfUtil.createCell("姓名"));
        table.addCell(PdfUtil.createCell(pdfData.getName()));
        table.addCell(PdfUtil.createCell("性别"));
        table.addCell(PdfUtil.createCell(pdfData.getSex()));
        table.addCell(PdfUtil.createCell("所属单位"));
        table.addCell(PdfUtil.createCell(pdfData.getDepartment()));


        table.addCell(PdfUtil.createCell("参加工作时间"));
        table.addCell(PdfUtil.createCell(pdfData.getWorktime()));

        table.addCell(PdfUtil.createCell("现有工勤岗位"));
        table.addCell(PdfUtil.createCell(pdfData.getGrjsname()));

        table.addCell(PdfUtil.createCell("身体状况"));
        table.addCell(PdfUtil.createCell("健康"));

        table.addCell(PdfUtil.createCell("申请岗位类别"));
        table.addCell(PdfUtil.createCell(pdfData.getApplygw()));

        table.addCell(PdfUtil.createCell("申请岗位类型"));
        table.addCell(PdfUtil.createCell(pdfData.getApplylevel()));

        table.addCell(PdfUtil.createCell("申请岗位等级"));
        table.addCell(PdfUtil.createCell(pdfData.getApplydj()));


        document.add(table);
        document.add(blank);

        PdfPTable table1 = PdfUtil.creteTable(new float[]{30,40,70,260,190,120});
        table1.addCell(PdfUtil.createCell(4,"聘任情况"));
        table1.addCell(PdfUtil.createCell("序号"));
        table1.addCell(PdfUtil.createCell("起止时间"));
        table1.addCell(PdfUtil.createCell("工作部门及岗位"));
        table1.addCell(PdfUtil.createCell("工勤技术等级"));
        table1.addCell(PdfUtil.createCell("证明人签字"));

        List<WorkStatus> workStatusList = pdfData.getWorkStatusList();
        int t = workStatusList.size();
        int id =1;
        for (WorkStatus workStatus : workStatusList) {
            PdfUtil.createTable(table1,id++,workStatus);
        }
        document.add(table1);
        document.add(blank);

        String url="D:/project/img";

        //个人承诺
        PdfPTable table4 = PdfUtil.creteTable(new float[]{30, 680});
        table4.addCell(PdfUtil.createCell("个人承诺"));
        Image image1 = Image.getInstance(url+"/本人承诺.png");
        image1.scaleAbsoluteHeight(400);
        PdfPCell cell = PdfUtil.createCell("",true);
        cell.setImage(image1);
        table4.addCell(cell);
        document.add(table4);
        document.add(blank);

        //所在单位推荐意见
        PdfPTable table5 = PdfUtil.creteTable(new float[]{30, 680});
        table5.addCell(PdfUtil.createCell("所在单位推荐意见"));
        Image image2 = Image.getInstance(url + "/所在单位意见.png");
        PdfPCell cell1 = PdfUtil.createCell("",true);
        cell1.setImage(image2);
        table5.addCell(cell1);
        document.add(table5);
        document.add(blank);

        PdfPTable table6 = PdfUtil.creteTable(new float[]{30, 680});
        table6.addCell(PdfUtil.createCell("学校岗位定级工作领导小组意见"));
        Image image3 = Image.getInstance(url + "/学校意见.png");
        PdfPCell cell2 = PdfUtil.createCell("",true);
        cell2.setImage(image3);
        table6.addCell(cell2);
        document.add(table6);
        //关闭
        document.close();
        return address;

    }

    //简单的单元格  单元格大小固定？写的时候没写注释，给忘记了
    public PdfPCell createCell(String value){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderWidth(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Paragraph(value,textfont));
        cell.setFixedHeight(32);
        return  cell;
    }



    //简单的单元格
    public PdfPCell createCell(String value,boolean a){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderWidth(1);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPhrase(new Paragraph(value,textsfont));
        return  cell;
    }

    //跨列的单元格
    public PdfPCell createCell(String value,int col){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(col);
        cell.setBorderWidth(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Paragraph(value,textfont));
        cell.setFixedHeight(32);
        return  cell;
    }

    //跨行的单元格
    public PdfPCell createCell(int row,String value){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(row);
        cell.setBorderWidth(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Paragraph(value,textfont));
        cell.setFixedHeight(32);
        return  cell;
    }

    //跨列的 自定义对齐方式单元格
    public PdfPCell createCell(String value,int col,int alin){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(col);
        cell.setBorderWidth(1);
        cell.setHorizontalAlignment(alin);
        cell.setPhrase(new Paragraph(value,textfont));
        cell.setFixedHeight(32);
        return  cell;
    }

    //创建表格
    public PdfPTable creteTable(float[] cols){
        PdfPTable table = new PdfPTable(cols);
        table.setTotalWidth(maxWidth);
        table.setLockedWidth(true);
        table.getDefaultCell().setBorder(1);
        table.setWidthPercentage(100);
        return table;
    }


    //创建任选条件时的表格
    public void createTable(PdfPTable table,Applydata applydata,int i){
        table.addCell(createCell(Integer.toString(i)));
        table.addCell(createCell(applydata.getPublishtime()));
        table.addCell(createCell(applydata.getName()));
        table.addCell(createCell(applydata.getRank()));
        table.addCell(createCell(applydata.getGroup()));
        table.addCell(createCell(" "));
    }

    //教学情况
    public void createTable(PdfPTable table,int t,WorkStatus workStatus){
        table.addCell(createCell(Integer.toString(t)));
        if(workStatus.getEndtime()!=null)
            table.addCell(createCell(workStatus.getStarttime()+"---"+workStatus.getEndtime()));
        else{
            String starttime = workStatus.getStarttime();
            starttime = starttime.substring(0, 4);
            table.addCell(createCell(starttime));
        }
        table.addCell(createCell(workStatus.getContent()));
        table.addCell(createCell(workStatus.getCount()));
        table.addCell(createCell(" "));
    }


}
