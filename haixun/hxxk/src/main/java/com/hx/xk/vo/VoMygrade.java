/**
 * 
 */
package com.hx.xk.vo;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:24:12
 * 
 */
public class VoMygrade implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886458883664937593L;
	private Integer mygradeid;
	private Integer userid;
	private Integer gradeid;

	private String reservedcode;// 预约号
//	private String gradecode;// 班级代码
	private String gradeApplystatus;// 报名状态
	private String overtime;// 过期时间

//	private Integer gradeid;
	private String code;
	private String area;
	private String year;
	private String term;
	private String degree;
	private String week;
	private String[] weeks;
	private String starttime;
	private String endtime;
	private String description;
	private String agelimit;
	private String applystatus;

	private Integer specialtyid;
	private String specialtyName;
	
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

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

	public String getReservedcode() {
		return reservedcode;
	}

	public void setReservedcode(String reservedcode) {
		this.reservedcode = reservedcode;
	}

	public String getApplystatus() {
		return applystatus;
	}

	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the week
	 */
	public String getWeek() {
		return week;
	}

	/**
	 * @param week the week to set
	 */
	public void setWeek(String week) {
		this.week = week;
	}

	/**
	 * @return the weeks
	 */
	public String[] getWeeks() {
		return weeks;
	}

	/**
	 * @param weeks the weeks to set
	 */
	public void setWeeks(String[] weeks) {
		this.weeks = weeks;
	}

	/**
	 * @return the starttime
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime the starttime to set
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
	 * @param endtime the endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the agelimit
	 */
	public String getAgelimit() {
		return agelimit;
	}

	/**
	 * @param agelimit the agelimit to set
	 */
	public void setAgelimit(String agelimit) {
		this.agelimit = agelimit;
	}

	/**
	 * @return the specialtyid
	 */
	public Integer getSpecialtyid() {
		return specialtyid;
	}

	/**
	 * @param specialtyid the specialtyid to set
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
	 * @param specialtyName the specialtyName to set
	 */
	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	/**
	 * @return the gradeApplystatus
	 */
	public String getGradeApplystatus() {
		return gradeApplystatus;
	}

	/**
	 * @param gradeApplystatus the gradeApplystatus to set
	 */
	public void setGradeApplystatus(String gradeApplystatus) {
		this.gradeApplystatus = gradeApplystatus;
	}

}
