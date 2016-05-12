package com.hx.xk.dto.base;

import com.hx.xk.common.XkConstant;

/**
 * @author Hasan
 * 
 */

public class DtoResult extends DtoBase implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -336750085969783700L;
	private String code = XkConstant.RESULT_CODE_SUCCESS;
	private Object result;

	public DtoResult() {

	}

	public DtoResult(String re) {
		result = re;
	}

	public DtoResult(String code, String re) {
		this.code = code;
		this.result = re;
	}

	public String getCode() {
		return code;
	}

	public Object getResult() {
		return result;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
