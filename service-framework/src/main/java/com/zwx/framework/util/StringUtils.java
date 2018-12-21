package com.zwx.framework.util;

/**
 * Title: System Infrastructure 
 * Description: This class is the string utility class
 * 
 * @author weixin.zhu
 */
public class StringUtils {
	/**
	 * <p>Checks whether the <code>String</code> is empty or null</p>
	 * @param str String
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String str) {
		return org.apache.commons.lang3.StringUtils.isBlank(str);
	}

	public static String lpad(String str, int size, String padStr) {
		return org.apache.commons.lang3.StringUtils.leftPad(str, size, padStr);
	}
}
