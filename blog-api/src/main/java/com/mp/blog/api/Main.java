package com.mp.blog.api;

import com.mp.blog.common.generator.CodeGenerator;

/**
 * @Author: mapei
 * @Date: 2020/7/9 11:37
 */
public class Main {
    public static void main(String[] args) {
        String basePack = Main.class.getPackage().getName();
        CodeGenerator codeGenerator = new CodeGenerator();
//        codeGenerator.generateMybatisXml(basePack, User.class);
//        codeGenerator.generateDao(basePack, User.class);
//        codeGenerator.generateService(basePack, User.class);
        codeGenerator.generateCreateSqlForPackage(basePack);
    }
}
