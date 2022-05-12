package com.hut.jsj.controller;


import com.hut.jsj.pojo.XZlevel;
import com.hut.jsj.vo.*;
import com.hut.jsj.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/optioncode")
@Api(tags ="获取职位、条件等信息")
public class InfoController {

    @Autowired
    InfoService infoService;
    @ApiOperation("根据申请的职称获得对应可填写的任选条件的代码")
    @GetMapping("/findcode")
    public ResponseBean findOption(@RequestParam("levelname") String levelname){

        List<Option> options = infoService.findBycode(levelname);
        if(options!=null)
            return new ResponseBean(200,"查询成功",options);
        else
            return new ResponseBean(403,"查询失败",null);
    }

    @GetMapping("/levelname")
    @ApiOperation("根据现有职称获取可以申请的职称")
    public ResponseBean getlevelnames (@RequestParam("levelno") String levelno ){
        List<GradeInfo> gradeInfos = infoService.getlevelnames(levelno);
        if(gradeInfos == null)
            return new ResponseBean(403,"查询失败",null);
        else
            return new ResponseBean(200,"查询成功",gradeInfos);
    }

    @GetMapping("/bxtj")
    @ApiOperation("根据申请的职称获得可以填写的必选条件")
    public ResponseBean findbxtj(@RequestParam("levelname") String levelname){
        List<Bxtj> bxtjs = infoService.findbxtj(levelname);
        if(bxtjs==null)
            //这里是业务需求，本来不应该这么返回
            return new ResponseBean(200,"查询成功",null);
        else
            return new ResponseBean(200,"查询成功",bxtjs);
    }

    @GetMapping("/xzlevel")
    @ApiOperation("获取管理岗位的职称类型")
    public ResponseBean findxzlevel(@RequestParam("xzlevel") String levelNo){
        List<XZlevel> xZlevels = infoService.findxzlevel(levelNo);
        if(xZlevels.size()==0)
            return new ResponseBean(403,"无数据",null);
        else
            return new ResponseBean(200,"查询成功",xZlevels);
    }

    @GetMapping("grjslevel")
    @ApiOperation("获取工勤岗位的职称类型")
    public ResponseBean findgrlevel(@RequestParam("grjslevel")String grjslevel){
        List<XZlevel> xZlevels = infoService.findgrlevel(grjslevel);
        if(xZlevels.size()==0)
            return new ResponseBean(403,"无数据",null);
        else
            return new ResponseBean(200,"查询成功",xZlevels);
    }

    @PostMapping("/code")
    @ApiOperation(("根据条件代码返回条件的描述信息"))
    public ResponseBean describe(@RequestBody  List<String> optioncode){
        List<OptionCode> optionCodes = infoService.getDescribe(optioncode);
        if(optionCodes==null)
            return new ResponseBean(403,"查询失败",null);
        else
            return new ResponseBean(200,"查询成功",optionCodes);

    }

    @GetMapping("/fteacher")
    @ApiOperation("获取非高校教师的职称类型")
    public ResponseBean fteacher(@RequestParam("zyjslevel2") String zyjslevel2){
        List<GradeInfo> gradeInfos = infoService.findfteacher(zyjslevel2);
        if(gradeInfos==null)
            return new ResponseBean(403,"无数据",null);
        else
            return new ResponseBean(200,"查询成功",gradeInfos);
    }

    @GetMapping("/rxtjts")
    @ApiModelProperty("获取任选代码提示")
    public ResponseBean rxtjts(@RequestParam("gwname") String gwname){
        List<Rxtj> rxtj = infoService.getRxtj(gwname);
        if(rxtj==null)
            return new ResponseBean(403,"查询失败",null);
        else
            return new ResponseBean(200,"查询成功",rxtj);
    }

    @GetMapping("/first")
    @ApiModelProperty("获取已填写的工作情况")
    public ResponseBean first(@RequestParam("idcard")String idcard){
        List<Workstatu> workstatu = infoService.getWorkstatu(idcard);
        if(workstatu != null){
            return new ResponseBean(200,"查询成功",workstatu);
        }else{
            return new ResponseBean(403,"查询失败",null);
        }
    }

    @GetMapping("/second")
    @ApiModelProperty("获取已填写的必选条件")
    public ResponseBean second(@RequestParam("idcard")String idcard){
        Second bxtj = infoService.getBxtj(idcard);
        if(bxtj !=null){
            return new ResponseBean(200,"查询成功",bxtj);
        }else{
            return new ResponseBean(403,"查询失败",null);
        }
    }

    @GetMapping("third")
    @ApiModelProperty("获取已填写的任选代码")
    public ResponseBean third(@RequestParam("idcard")String idcard){
        List<String> code =  infoService.getCode(idcard);
        if(code != null){
            return new ResponseBean(200,"查询成功",code);
        }else{
            return new ResponseBean(403,"查询失败",null);
        }
    }

    @GetMapping("fourth")
    @ApiModelProperty("获取已填写的任选条件")
    public ResponseBean fourth(@RequestParam("idcard")String idcard){
        List<Second> rxtj = infoService.findRxtj(idcard);
        if(rxtj != null) {
            return new ResponseBean(200,"查询成功",rxtj);
        } else {
            return new ResponseBean(403,"查询失败",null);
        }
    }

}
