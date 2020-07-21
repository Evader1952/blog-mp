package com.mp.blog.shopping;

import com.mp.blog.common.generator.CodeGenerator;
import com.mp.blog.shopping.entity.RedPacket;
import com.mp.blog.shopping.entity.Trade;

/**
 * @Author: mapei
 * @Date: 2020/7/9 11:37
 */
public class Main {
    public static void main(String[] args) {
        String basePack = Main.class.getPackage().getName();
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.generateMybatisXml(basePack, RedPacket.class);
        codeGenerator.generateDao(basePack,RedPacket .class);
        codeGenerator.generateService(basePack, RedPacket.class);
        codeGenerator.generateCreateSqlForPackage(basePack);
    }
}
