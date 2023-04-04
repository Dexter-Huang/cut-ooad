package com.exchange.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;


public class CodeGen {
    public static void main(String[] args) {
        // 请记得修改输出路径
        String url="jdbc:mysql://43.139.185.152:3306/cs304?serverTimezone=GMT%2b8";
        String username="root";
        String password="123456";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("hml") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() //禁止打开输出目录
                            .outputDir("D:\\IDEA_project\\team-project-211\\resourceexchange\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.exchange") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\IDEA_project\\team-project-211\\resourceexchange\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok(); //启动lombok
                    builder.mapperBuilder().enableMapperAnnotation().build(); //启用@mapper注释
                    builder.controllerBuilder().enableHyphenStyle().enableRestStyle(); //启用驼峰转连字符样式
                    builder.addInclude("user"); // 设置需要生成的表名
                })
                .execute();
    }
}
