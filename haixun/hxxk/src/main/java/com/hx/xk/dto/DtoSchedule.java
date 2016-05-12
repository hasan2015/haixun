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
public class DtoSchedule extends DtoBase implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886458883664937593L;
	private Integer scheduleid;
	private DtoGrade ygrade;
	private String week;
	private String starttime;
	private String endtime;
	private String address;

	/**
	 * @return the scheduleid
	 */
	public Integer getScheduleid() {
		return scheduleid;
	}

	/**
	 * @param scheduleid
	 *            the scheduleid to set
	 */
	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
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
	 * @return the week
	 */
	public String getWeek() {
		return week;
	}

	/**
	 * @param week
	 *            the week to set
	 */
	public void setWeek(String week) {
		this.week = week;
	}

	/**
	 * @return the starttime
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime
	 *            the starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime
	 *            the endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
