package com.mp.blog.common.utils;

import com.alipay.api.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流操作类
 *
 * @author duchong
 * @date 2019-6-22 14:41:42
 */
public class IoUtils {
    private static Logger log = LoggerFactory.getLogger(IoUtils.class);

    /**
     * 获取图片内容
     *
     * @param path
     * @return
     */
    public static FileItem getFileItem(String path) {
        byte[] bytes = null;
        String filename = getFileName(path);
        if (isLocalPath(path)){
            bytes = fileToByte(path);
        }else {
            bytes = getFileStream(path);
        }
        return new FileItem(filename,bytes);
    }

    /**
     * 获取图片内容
     * @param imageStream   图片文件流
     * @param fileName      图片名称
     * @return
     */
    public static FileItem getFileItemByInputStream(InputStream imageStream, String fileName) {
        if (DataUtil.isEmpty(imageStream)) {
            return null;
        }
        try {
            byte[] bytes = readInputStream(imageStream);
            return new FileItem(fileName, bytes);
        } catch (Exception e) {
            log.error("获取图片内容错误:{}", e);
            return null;
        }
    }

    /**
     * 得到文件流
     *
     * @param url
     * @return
     */
    public static byte[] getFileStream(String url) {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            byte[] btImg = readInputStream(inStream);
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到文件流
     *
     * @param path
     * @return
     */
    public static String getFileName(String path) {
        if (path == null) {
            return null;
        }
        String fileName = null;
        if (isLocalPath(path)) {
            fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
        } else {
            fileName = new File(path).getName();
        }
        if (fileName.indexOf(".") == -1) {
            fileName += ".jpg";
        }
        return fileName;
    }


    /**
     * 是否为本地路径
     *
     * @param path
     * @return
     */
    public static Boolean isLocalPath(String path) {
        Boolean isLocalPath = false;
        if (path.indexOf("http://") == -1 && path.indexOf("https://") == -1) {
            isLocalPath = true;
        }
        return isLocalPath;
    }


    /**
     * 从输入流中读取数据
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }


    /**
     * 叫读取到的问价你转换成byte[]
     *
     * @param filePath
     * @return
     */
    public static byte[] fileToByte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 叫读取到的问价你转换成byte[]
     *
     * @param filePath
     * @return
     */
    public static void byteToFile(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    /**
     * 读取一个文本 一行一行读取
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readLineTxt(String path) throws IOException {
        List<String> list = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line.lastIndexOf("---") < 0) {
                list.add(line);
            }
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }


    /**
     * 读取一个文本 一行一行读取
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<Map<String,String>> getList(String path) throws IOException {
        List<String> list = new ArrayList<String>();
        List<Map<String,String>> tradeList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line.lastIndexOf("---") < 0) {
                list.add(line);
            }
        }
        br.close();
        isr.close();
        fis.close();
        return tradeList;
    }
}
