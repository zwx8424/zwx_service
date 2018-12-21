package com.zwx.framework.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Title: System Infrastructure 
 * Description: This class is the utility class
 * 
 * @author weixin.zhu
 */
public class Tools {

	/**
	 * 取得错误堆栈字符串
	 * 
	 * @param ex
	 * @return
	 */
	public static String toPrintStackTraceStr(Throwable ex) {
		// StringWriter将包含堆栈信息
		StringWriter stringWriter = new StringWriter();
		// 必须将StringWriter封装成PrintWriter对象，
		// 以满足printStackTrace的要求
		PrintWriter printWriter = new PrintWriter(stringWriter);
		// 获取堆栈信息
		ex.printStackTrace(printWriter);
		// 转换成String，并返回该String
		StringBuffer error = stringWriter.getBuffer();
		return error.toString();
	}

	/**
	 * 获取配置参数
	 * 
	 * @param key
	 *          配置参数名称
	 * @return 对应的值
	 */
	public static String getClassPath() {
		return Tools.class.getClassLoader().getResource("").getPath();
	}

	@SuppressWarnings("rawtypes")
	public static String getSystemProperties() {
		StringBuffer strb = new StringBuffer();
		strb.append("=========================print system properties start!\n");
		Properties pros = System.getProperties();
		Enumeration enu = pros.keys();
		String key = null;
		String value = null;
		while (enu.hasMoreElements()) {
			key = (String) enu.nextElement();
			value = (String) pros.get(key);
			if ("line.separator".equals(key)) {
				String lineSeparatorStr = "";
				for (int i = 0; i < value.length(); i++) {
					char temp = value.charAt(i);
					switch (temp) {
					case 10:
						lineSeparatorStr += "\\n";
						break;
					case 13:
						lineSeparatorStr += "\\r";
						break;
					}
				}
				strb.append(key + "=" + lineSeparatorStr+"\n");
			} else {
				strb.append(key + "=" + value+"\n");
			}
		}
		strb.append("=========================print system properties end!");
		return strb.toString();
	}

	public static String formatDateToStr(Date date,String dateFormat) {
		if(StringUtils.isNullOrEmpty(dateFormat)){
			dateFormat = "yyyy-MM-dd";
		}
		return new SimpleDateFormat(dateFormat).format(date);
	}

}
