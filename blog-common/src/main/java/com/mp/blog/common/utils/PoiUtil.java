package com.mp.blog.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * excel导入导出工具
 *
 * @author duchong
 * @date 2019-11-13 13:57:26
 */
@Component
public class PoiUtil {




    private static String SERVER_PATH;

    @Value("${serverPath}")
    public void setAccessKey(String serverPath) {
        PoiUtil.SERVER_PATH = serverPath;
    }

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(PoiUtil.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    private static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";



    public static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在
        if (null == file) {
            logger.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static HSSFWorkbook getWorkBook(MultipartFile file) {
        HSSFWorkbook workbook = null;
        try {
            checkFile(file);
            workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }


    /**
     * 批量导出
     *
     * @param request
     * @param response
     * @param headers
     * @param keys
     * @param lists
     */
    public static void batchExport(String[] headers, String[] keys, List<JSONObject> lists, HttpServletRequest request, HttpServletResponse response) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Sheet1");
        sheet.setDefaultColumnWidth(18);
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            XSSFCell headerCell = row.createCell(i);
            headerCell.setCellStyle(style);
            headerCell.setCellValue(headers[i]);
        }
        int rowIndex = 0;
        XSSFCell cell = null;
        for (JSONObject obj : lists) {
            XSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            rowIndex++;
            row = sheet.createRow(rowIndex);

            for (int i = 0; i < keys.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(obj.getString(keys[i]));
            }
        }
        String filename = "导出信息" + DateUtil.formatDate(new Date(), PATTERN_YYYYMMDDHHMMSS) + ".xlsx";
        String filepath = request.getRealPath("/") + filename;
        FileOutputStream out = new FileOutputStream(filepath);
        wb.write(out);
        out.close();
        downloadExcel(filepath, response);
    }

    /**
     * 下载
     *
     * @param response
     * @param filepath
     */
    public static void downloadExcel(String filepath, HttpServletResponse response)
            throws IOException {
        File file = new File(filepath);
        String fileName = file.getName();
        response.setContentType("application/x-xls;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        response.setCharacterEncoding("utf-8");
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] b = new byte[fis.available()];
        fis.read(b);
        OutputStream out = response.getOutputStream();
        out.write(b);
        out.flush();
        fis.close();
        if (file.exists()) {
            file.delete();
        }
        out.close();
    }

    /**
     * 批量导出
     *
     * @param headers
     * @param keys
     * @param lists
     */
    public static void batchExport(String[] headers, String[] keys, List<JSONObject> lists, String filename) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Sheet1");
        sheet.setDefaultColumnWidth(18);
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            XSSFCell headerCell = row.createCell(i);
            headerCell.setCellStyle(style);
            headerCell.setCellValue(headers[i]);
        }
        int rowIndex = 0;
        XSSFCell cell = null;
        for (JSONObject obj : lists) {
            XSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            rowIndex++;
            row = sheet.createRow(rowIndex);

            for (int i = 0; i < keys.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(obj.getString(keys[i]));
            }
        }

        FileOutputStream out = new FileOutputStream(filename);
        wb.write(out);
        out.close();
    }

    /**
     * 批量导出
     *
     * @param headers
     * @param keys
     * @param lists
     */
    public static void sxssBatchExport(String[] headers, String[] keys, List<JSONObject> lists, String filename) throws Exception {
        SXSSFWorkbook wb = new SXSSFWorkbook(8000);
        int size = lists.size();
        if (size > 5000) {
            wb.setCompressTempFiles(true);
        }
        Sheet sh = wb.createSheet();
        sh.setDefaultColumnWidth(18);
        Row row = sh.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = row.createCell(i);
            headerCell.setCellValue(headers[i]);
        }
        int rowIndex = 0;
        Cell cell = null;
        for (JSONObject obj : lists) {
            rowIndex++;
            row = sh.createRow(rowIndex);
            for (int i = 0; i < keys.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(obj.getString(keys[i]));
            }
        }
        FileOutputStream out = new FileOutputStream(filename);
        wb.write(out);
        out.close();
        wb.dispose();
    }






    public static void export(String[] headers, String[] keys,List lists, String name) throws Exception {
        String filename = SERVER_PATH + name + ".xlsx";
        String zipName = SERVER_PATH + name + ".zip";
        SXSSFWorkbook wb = new SXSSFWorkbook (1000);
        int total = lists.size();
        //每个sheet最大数据量
        int maxData = 5000;
        //多少个sheet
        int avg = total / maxData;
        for (int i = 0; i < avg+1; i++) {
            SXSSFSheet sheet = (SXSSFSheet) wb.createSheet("Sheet" + (i+1));
            Row row = sheet.createRow(0);
            for (int j = 0; j < headers.length; j++) {
                Cell headerCell = row.createCell(j);
                headerCell.setCellValue(headers[j]);
            }
            int num = i * maxData;
            int index = 0;
            int rowIndex = 0;
            Cell cell = null;
            for (int m = num; m < lists.size(); m++) {
                if (index == maxData) {
                    break;
                }
                rowIndex++;
                row = sheet.createRow(rowIndex);
                for (int l = 0; l < keys.length; l++) {
                    cell = row.createCell(l);
                    Object o1 = lists.get(m);
                    Object value = getValue(o1, keys[l]);
                    if (value!=null) {
                        cell.setCellValue(String.valueOf(value));
                    }
                }
                index++;
            }
            System.out.println(i);
        }
        FileOutputStream out = new FileOutputStream(filename);
        wb.write(out);
        out.close();
        wb.dispose();
        //压缩文件
        if (total>5000){
            File zipFile = new File(zipName);
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            recursionZip(zipOut, new File(filename));
            zipOut.close();
        }
    }

    private static void recursionZip(ZipOutputStream zipOut, File file) throws Exception {
        if (file.isDirectory()) {
//            File[] files = file.listFiles();
//            if (files.length > 0) {
//                for (File fileSec : files) {
//                    recursionZip(zipOut, fileSec, baseDir + file.getName() + "/");
//                }
//            } else {
//                zipOut.putNextEntry(new ZipEntry(baseDir + file.getName() + "/"));
//            }
        } else {
            byte[] buf = new byte[1024];
            InputStream input = new FileInputStream(file);
            zipOut.putNextEntry(new ZipEntry(file.getName()));
            int len;
            while ((len = input.read(buf)) != -1) {
                zipOut.write(buf, 0, len);
            }
            input.close();
        }
    }
    public static Object getValue(Object model, String key) throws Exception{
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName().equals(key)){
                return field.get(model);
            }
        }
        return null;
    }
}
