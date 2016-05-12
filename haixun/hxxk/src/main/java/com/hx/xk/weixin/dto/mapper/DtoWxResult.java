/**
 * 
 */
package com.hx.xk.weixin.dto.mapper;

/**
 * @author Hasan
 * @Date 2015-8-16 下午8:37:54
 * 
 */
public class DtoWxResult  implements java.io.Serializable {
	// {"errcode":0,"errmsg":"ok","msgid":213974289}
	private int errcode;
	private String errmsg;
	private int msgid;

	/**
	 * @return the errcode
	 */
	public int getErrcode() {
		return errcode;
	}

	/**
	 * @param errcode
	 *            the errcode to set
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
	 * @param errmsg
	 *            the errmsg to set
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	/**
	 * @return the msgid
	 */
	public int getMsgid() {
		return msgid;
	}

	/**
	 * @param msgid
	 *            the msgid to set
	 */
	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}

}
