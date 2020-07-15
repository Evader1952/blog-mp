package com.mp.blog.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 公共方法.
 * @author xiaowen
 *
 */
public class Commons {

	/**
	 * Spring3文件上传.
	 * @param file 文件file对象
	 * @param
	 * @return
	 * @throws IOException
	 */
	public static String fileUpload(MultipartFile file, String fileurl) throws IOException {
		String fileurl_callback = "";
		if (!file.isEmpty()) {
			fileurl_callback = fileurl+"/"+file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			FileOutputStream fos = new FileOutputStream(fileurl_callback);
			fos.write(bytes);  //写入文件
		}
		return fileurl_callback;
	}

	/**
	 * 将一个整形�?变成字符�?并且根据位数将左边补0
	 * @param nativeValue 原�?
	 * @param nativeLength 原长�?
	 * @return
	 * @throws Exception
	 */
	public static String radixComplementZero(int nativeValue, int nativeLength) throws Exception {
		if (nativeLength <1)
			throw new Exception("nativeLength too small");
		if (nativeValue<1)
			throw new Exception("nativeValue too small");
		if (String.valueOf(nativeValue).length()>nativeLength)
			throw new Exception("nativeValue too big");
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<nativeLength;i++){
			sb.append("0");
		}
		DecimalFormat df = new DecimalFormat(sb.toString());
		return df.format(nativeValue);
	}
	/**
	 * 生成随即密码
	 *
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */

	public static String getNextDay(String nowdate, String delay) {
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		}catch(Exception e){
			return "";
		}
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	/**


	 public static void formatMerchandiseByCattyShow2(Interrelated vo){
	 if (vo!=null && vo.getMerchandise_pricefactor()!=null && vo.getMerchandise_pricefactor().intValue()>1){
	 //份价  *500 /因子 = 斤价
	 vo.setMerchandise_netprice_(new BigDecimal(Double.valueOf(IConstants.df.format(vo.getMerchandise_netprice_().doubleValue() * IConstants.CATTY / vo.getMerchandise_pricefactor().doubleValue()))));
	 //单位写死变斤
	 vo.setMerchandise_unit_(IConstants.CATTY_TEXT);
	 } else {
	 vo.setMerchandise_unit_(vo.getMerchandise_unit_());
	 }
	 }


	 /**
	 * 得到星期几
	 *
	 * @param djrq
	 * @return
	 */
	public static String getweekofday(String djrq) {
		final String dayNames[] = { "7", "1", "2", "3", "4", "5", "6" };
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();

		try {
			date = sdfInput.parse(djrq);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		String result = dayNames[dayOfWeek - 1];

		return result;
	}

	/**
	 * 判断变量是否为空
	 *
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s);
	}
	/**
	 * 使用率计算
	 *
	 * @return
	 */
	public static String fromUsage(long free, long total) {
		Double d = new BigDecimal(free * 100 / total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(d);
	}


	/**
	 * 传入原图名称，，获得一个以时间格式的新名称
	 *
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 取得html网页内容 UTF8编码
	 *
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlUTF8(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "UTF-8");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * 取得html网页内容 GBK编码
	 *
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlGBK(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "GBK");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * @param inputStream
	 * @param uncode
	 *            编码 GBK 或 UTF-8
	 * @return
	 * @throws Exception
	 */
	public static String readHtml(InputStream inputStream, String uncode) throws Exception {
		InputStreamReader input = new InputStreamReader(inputStream, uncode);
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		return contentBuf.toString();
	}

	/**
	 *
	 * @return 返回资源的二进制数据 @
	 */
	public static byte[] readInputStream(InputStream inputStream) {

		// 定义一个输出流向内存输出数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 定义一个缓冲区
		byte[] buffer = new byte[1024];
		// 读取数据长度
		int len = 0;
		// 当取得完数据后会返回一个-1
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				// 把缓冲区的数据 写到输出流里面
				byteArrayOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		// 得到数据后返回
		return byteArrayOutputStream.toByteArray();

	}

	/**
	 * 创建指定数量的随机字符串
	 * @param numberFlag 是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length){
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do{
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if(('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		}while (bDone);
		return retStr;
	}
}
