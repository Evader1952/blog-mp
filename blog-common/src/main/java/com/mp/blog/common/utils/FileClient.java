//package com.mp.blog.common.utils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author lvlu
// * @date 2019-07-06 15:34
// **/
//public class FileClient {
//
//    private static String host = "47.110.44.150";
////    private static String host = "47.110.36.7";
//
//    private static int port = 22;
//
//    private static String username = "sftp";
//
//    private static String password = "ZanClick@#2019";
////    private static String password = "1234Qwer=";
//
//    private static String aesKey;
//
//    private static String downLoadDir = "/upload/files/hb/";
//
//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//
//    /**
//     * 下载文件
//     *
//     * @param filePath 文件路径，没有用默认路径
//     * @param fileName 文件名称
//     * @return
//     */
//    private static byte[] downLoad(String filePath, String fileName, String timeStr) {
//        try {
//            String downLoadDirStr = getDownLoadDirStr(filePath, timeStr);
//            if (DataUtil.isEmpty(fileName)) {
//                return null;
//            }
//            SftpUtils sftpUtils = new SftpUtils(host, port, username, password);
//            //连接sftp服务器
//            sftpUtils.connect();
//            //下载文件
//            InputStream inputStream = sftpUtils.downloadFile(downLoadDirStr, fileName);
//            byte[] bytes = null;
//            if (!DataUtil.isEmpty(inputStream)) {
//                bytes = toByteArray(inputStream);
//            }
//            //关闭连接
//            sftpUtils.disconnect();
//            //返回本地文件
//            return bytes;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 批量下载文件
//     *
//     * @param filePath 文件路径，没有用默认路径
//     * @param fileName 文件名称
//     * @return
//     */
//    private static List<byte[]> downloadFiles(String filePath, List<String> fileName, String timeStr) {
//        try {
//            String downLoadDirStr = getDownLoadDirStr(filePath, timeStr);
//            if (DataUtil.isEmpty(fileName)) {
//                return null;
//            }
//            SftpUtils sftpUtils = new SftpUtils(host, port, username, password);
//            //连接sftp服务器
//            sftpUtils.connect();
//            //下载文件
//            List<InputStream> inputStreams = sftpUtils.downloadFiles(downLoadDirStr, fileName);
//            List<byte[]> byteList = new ArrayList<>();
//            if (!DataUtil.isEmpty(inputStreams)) {
//                for (InputStream inputStream : inputStreams) {
//                    byteList.add(toByteArray(inputStream));
//                }
//            }
//            //关闭连接
//            sftpUtils.disconnect();
//            //返回本地文件
//            return byteList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 下载文件
//     *
//     * @param filePath 文件路径，没有用默认路径
//     * @param fileName 文件名称
//     * @return
//     */
//    private static List<byte[]> downloadFiles(String filePath, List<String> fileName) {
//        try {
//            String downLoadDirStr = getDownLoadDirStr(filePath, null);
//            if (DataUtil.isEmpty(fileName)) {
//                return null;
//            }
//            SftpUtils sftpUtils = new SftpUtils(host, port, username, password);
//            //连接sftp服务器
//            sftpUtils.connect();
//            //下载文件
//            List<InputStream> inputStreams = sftpUtils.downloadFiles(downLoadDirStr, fileName);
//            List<byte[]> byteList = new ArrayList<>();
//            if (!DataUtil.isEmpty(inputStreams)) {
//                for (InputStream inputStream : inputStreams) {
//                    byteList.add(toByteArray(inputStream));
//                }
//            }
//            //关闭连接
//            sftpUtils.disconnect();
//            //返回本地文件
//            return byteList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static String getDownLoadDirStr(String filePath, String timeStr) {
//        String downLoadDirStr = null;
//        if (!DataUtil.isEmpty(filePath)) {
//            downLoadDirStr = filePath;
//        } else {
//            if (DataUtil.isEmpty(timeStr)) {
//                downLoadDirStr = downLoadDir + simpleDateFormat.format(new Date()) + "/";
//            } else {
//                downLoadDirStr = downLoadDir + timeStr + "/";
//            }
//        }
//        return downLoadDirStr;
//    }
//
//    /**
//     * 下载商户图片文件
//     *
//     * @param filePath 文件路径（sftp路径，包含文件名称）
//     * @return
//     */
//    public static byte[] downLoadImageFile(String filePath) {
//        try {
//            String remoteDirName = filePath.substring(0, filePath.lastIndexOf("/") + 1);
//            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
//            return downLoad(remoteDirName, fileName, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 下载文件
//     * @param fileName  文件名称
//     * @param timeStr   日期
//     * @return
//     */
//    public static List<String> downLoadFileDecrypt(String fileName, String timeStr) {
//        try {
//            return FileUtils.readerFile(downLoad(null, fileName, timeStr));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 下载文件
//     * @param fileName  文件名称
//     * @param timeStr   日期
//     * @return
//     */
//    public static byte[] downLoadFile(String fileName, String timeStr) {
//        try {
//            return downLoad(null, fileName, timeStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 批量下载文件
//     * @param fileNameList  文件名集合
//     * @param timeStr       时间
//     * @return
//     */
//    public static List<byte[]> downloadFiles(List<String> fileNameList, String timeStr) {
//        try {
//            return downloadFiles(null, fileNameList, timeStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 批量下载文件
//     * @param fileNameList  文件名集合
//     * @param timeStr       时间
//     * @return
//     */
//    public static List<String> downloadFilesDecrypt(List<String> fileNameList, String timeStr) {
//        try {
//            return FileUtils.readerFileByteList(downloadFiles(null, fileNameList, timeStr));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static byte[] toByteArray(InputStream input) {
//        try {
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024*4];
//            int n = 0;
//            while (-1 != (n = input.read(buffer))) {
//                output.write(buffer, 0, n);
//            }
//            return output.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//}
