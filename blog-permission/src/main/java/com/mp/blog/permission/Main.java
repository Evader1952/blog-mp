package com.mp.blog.permission;

import com.mp.blog.common.generator.CodeGenerator;
import com.mp.blog.permission.entity.*;

/**
 * @Author: mapei
 * @Date: 2020/7/9 11:37
 */
public class Main {
    public static void main(String[] args) {
        String basePack = Main.class.getPackage().getName();
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.generateMybatisXml(basePack, Role.class);
        codeGenerator.generateDao(basePack, Role.class);
        codeGenerator.generateService(basePack, Role.class);
        codeGenerator.generateCreateSqlForPackage(basePack);
    }
}
