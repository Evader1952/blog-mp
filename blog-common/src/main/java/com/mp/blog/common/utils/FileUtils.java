package com.mp.blog.common.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lvlu
 * @date 2019-07-03 18:14
 **/
public class FileUtils {

    //生产环境秘钥
    private static final String aesKey = "dEsBgPZW2r3ZZ85u";
    //测试环境秘钥
    private static final String aesKeyDev = "1234567890123456";

    public static String decryptStr(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Hex.decodeHex(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String encryptStr(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            //AES加密
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(sSrc.getBytes("utf-8"));
            char[] encrypt = Hex.encodeHex(original);
            String originalString = new String(encrypt);
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密文件内容
     * @param file
     * @return
     */
    public static List<String> readerFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str = null;
            List<String> strings = new ArrayList<>();
            while((str = reader.readLine()) != null){
                if (!"@END@".equals(str)) {
                    strings.add(decryptStr(str.replace("\n", ""), aesKey));
                }
            }
            return strings;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密文件内容
     * @param bytes
     * @return
     */
    public static List<String> readerFile(byte[] bytes) {
        try {
            if (!DataUtil.isEmpty(bytes)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                String str = null;
                List<String> strings = new ArrayList<>();
                while((str = reader.readLine()) != null){
                        strings.add(decryptStr(str.replace("\n", ""), aesKey));
                }
                return strings;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密多个文件内容
     * @param byteList
     * @return
     */
    public static List<String> readerFileByteList(List<byte[]> byteList) {
        try {
            if (!DataUtil.isEmpty(byteList)) {
                List<String> strings = new ArrayList<>();
                for (byte[] bytes : byteList) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                    String str = null;
                    while((str = reader.readLine()) != null){
                        strings.add(decryptStr(str.replace("\n", ""), aesKey));
                    }
                }
                return strings;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> readerFile(List<String> list) {
        try {
            List<String> strings = new ArrayList<>();
            for (String s : list) {
                strings.add(decryptStr(s.replace("\n", ""), aesKey));
            }
            return strings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
