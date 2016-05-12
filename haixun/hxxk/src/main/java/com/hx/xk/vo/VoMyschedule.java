/**
 * 
 */
package com.hx.xk.vo;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:24:12
 * 
 */
public class VoMyschedule implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886458883664937593L;
	private Integer mygradeid;
	private Integer userid;
	private Integer gradeid;
	//
	private Integer scheduleid;
	// private Ygrade ygrade;
	private String week;
	private String starttime;
	private String endtime;
	private String address;

	// private String reservedcode;// 预约号
	// private String gradecode;// 班级代码
	// private String gradeApplystatus;// 报名状态
	// private String overtime;// 过期时间

	// private Integer gradeid;
	// private String code;
	// private String area;
	// private String year;
	// private String term;
	// private String degree;
	// private String week;
	// private String[] weeks;
	// private String starttime;
	// private String endtime;
	// private String description;
	// private String agelimit;
	// private String applystatus;

	private Integer specialtyid;
	private String specialtyName;

	public Integer getMygradeid() {
		return mygradeid;
	}

	public void setMygradeid(Integer mygradeid) {
		this.mygradeid = mygradeid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getGradeid() {
		return gradeid;
	}

	public void setGradeid(Integer gradeid) {
		this.gradeid = gradeid;
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
	 * @return the specialtyid
	 */
	public Integer getSpecialtyid() {
		return specialtyid;
	}

	/**
	 * @param specialtyid
	 *            the specialtyid to set
	 */
	public void setSpecialtyid(Integer specialtyid) {
		this.specialtyid = specialtyid;
	}

	/**
	 * @return the specialtyName
	 */
	public String getSpecialtyName() {
		return specialtyName;
	}

	/**
	 * @param specialtyName
	 *            the specialtyName to set
	 */
	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

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
