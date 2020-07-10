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
        codeGenerator.generateMybatisXml(basePack, UserRole.class);
        codeGenerator.generateDao(basePack, UserRole.class);
        codeGenerator.generateService(basePack, UserRole.class);
        codeGenerator.generateCreateSqlForPackage(basePack);
    }
}
