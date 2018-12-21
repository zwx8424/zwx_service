package com.zwx.framework.api;

public class SimpleTransObject implements TransObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8111460016383794855L;

	public SimpleTransObject clone() {
		try {
			SimpleTransObject cloneObj = (SimpleTransObject) super.clone();
			return cloneObj;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("CloneNotSupportedException", e);
		}
	}
}
