package com.hut.jsj.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration  //标注这是一个配置类
@EnableSwagger2  //开启Swagger2
public class swaggerConfig {
    @Bean
    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //enable 是否启动Swagger,false不启动
                .enable(true)
                //设置组的名称
                .groupName("hut")
                .select()
                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackage,指定要扫描的包
                //any，扫描全部
                //none，不扫描
                .apis(RequestHandlerSelectors.basePackage("com.hut.jsj.controller"))
                //paths,过滤什么样路径，过滤的接口swaggerui上面就看不到了
                .paths(PathSelectors.any())
                //build一定要写
                .build();
    }

    //配置swagger的基础信息---描述
    private ApiInfo apiInfo(){
        Contact contact = new Contact("pty","#","1219002685@qq.com");
        return new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos", contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }

}
