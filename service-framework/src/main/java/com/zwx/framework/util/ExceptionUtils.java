package com.zwx.framework.util;

/**
 * Title: System Infrastructure 
 * Description: This class is the exception utility class
 * 
 * @author weixin.zhu
 */
public class ExceptionUtils {
	/**
	 * convert a common error to the RuntimeException
	 * 
	 * @param e
	 *          a common error
	 * @return RuntimeException
	 */
	public static RuntimeException parse(Exception e) {
		RuntimeException re = null;
		if (e instanceof RuntimeException) {
			re = (RuntimeException) e;
		} else {
			re = new RuntimeException(e.getMessage(), e);
		}
		return re;
	}

}
