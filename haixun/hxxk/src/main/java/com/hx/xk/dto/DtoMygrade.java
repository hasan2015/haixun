/**
 * 
 */
package com.hx.xk.dto;

import com.hx.xk.dto.base.DtoBase;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:24:12
 * 
 */
public class DtoMygrade extends DtoBase implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886458883664937593L;
	private Integer mygradeid;
	private DtoUser yuser;
	private DtoGrade ygrade;
	private String reservedcode;
	private String applystatus;
	private String overtime;
	//
	private String linkHref;
	private String linkText;

	/**
	 * @return the mygradeid
	 */
	public Integer getMygradeid() {
		return mygradeid;
	}

	/**
	 * @param mygradeid
	 *            the mygradeid to set
	 */
	public void setMygradeid(Integer mygradeid) {
		this.mygradeid = mygradeid;
	}

	/**
	 * @return the yuser
	 */
	public DtoUser getYuser() {
		return yuser;
	}

	/**
	 * @param yuser
	 *            the yuser to set
	 */
	public void setYuser(DtoUser yuser) {
		this.yuser = yuser;
	}

	/**
	 * @return the ygrade
	 */
	public DtoGrade getYgrade() {
		return ygrade;
	}

	/**
	 * @param ygrade
	 *            the ygrade to set
	 */
	public void setYgrade(DtoGrade ygrade) {
		this.ygrade = ygrade;
	}

	/**
	 * @return the reservedcode
	 */
	public String getReservedcode() {
		return reservedcode;
	}

	/**
	 * @param reservedcode
	 *            the reservedcode to set
	 */
	public void setReservedcode(String reservedcode) {
		this.reservedcode = reservedcode;
	}

	/**
	 * @return the applystatus
	 */
	public String getApplystatus() {
		return applystatus;
	}

	/**
	 * @param applystatus
	 *            the applystatus to set
	 */
	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}

	/**
	 * @return the overtime
	 */
	public String getOvertime() {
		return overtime;
	}

	/**
	 * @param overtime
	 *            the overtime to set
	 */
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	/**
	 * @return the linkHref
	 */
	public String getLinkHref() {
		return linkHref;
	}

	/**
	 * @param linkHref
	 *            the linkHref to set
	 */
	public void setLinkHref(String linkHref) {
		this.linkHref = linkHref;
	}

	/**
	 * @return the linkText
	 */
	public String getLinkText() {
		return linkText;
	}

	/**
	 * @param linkText
	 *            the linkText to set
	 */
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

}
