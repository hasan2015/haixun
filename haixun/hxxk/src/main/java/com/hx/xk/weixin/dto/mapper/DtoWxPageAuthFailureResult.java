/**
 * 
 */
package com.hx.xk.weixin.dto.mapper;

/**
 * 
 * @author Hasan
 * @Date 2015-8-20 上午9:41:13
 * 
 */
public class DtoWxPageAuthFailureResult implements java.io.Serializable {
	 
	private int errcode;
	private String errmsg;
	/**
	 * @return the errcode
	 */
	public int getErrcode() {
		return errcode;
	}
	/**
	 * @param errcode the errcode to set
	 */
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	/**
	 * @return the errmsg
	 */
	public String getErrmsg() {
		return errmsg;
	}
	/**
	 * @param errmsg the errmsg to set
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
 

}
