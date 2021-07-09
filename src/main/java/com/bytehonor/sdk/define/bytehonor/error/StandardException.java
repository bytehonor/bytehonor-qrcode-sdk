package com.bytehonor.sdk.define.bytehonor.error;

/**
 * top exception
 * 
 * @author lijianqiang
 *
 */
public class StandardException extends RuntimeException {

	private static final long serialVersionUID = -183184291876365329L;

	/**
	 * error code
	 */
	private int code;

	public StandardException() {
		super();
	}

	public StandardException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
