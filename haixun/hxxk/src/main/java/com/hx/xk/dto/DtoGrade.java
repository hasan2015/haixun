/**
 * 
 */
package com.hx.xk.dto;

import java.util.HashSet;
import java.util.Set;

import com.hx.xk.dto.base.DtoBase;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:24:12
 * 
 */
public class DtoGrade extends DtoBase implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886458883664937593L;
	private Integer gradeid;
	private Integer areaid;
	private DtoSpecialty yspecialty;
	private String code;
	private String area;
	private String year;
	private String term;
	private String degree;
	private String startdate;
	private String enddate;
	private Integer times;
	private String type;
	private String phone;
	private String costdetail;
	private Float cost;
	private String description;
	private String agelimit;
	private String genderlimit;
	private String ability;
	private String manner;
	private String link;
	private String applystatus;
	//
	private String specialtyName;

	//
	private Set<DtoSchedule> yschedules = new HashSet<DtoSchedule>(0);

	//
	private String linkHref;
	private String linkText; 

	/**
	 * @return the gradeid
	 */
	public Integer getGradeid() {
		return gradeid;
	}

	/**
	 * @param gradeid
	 *            the gradeid to set
	 */
	public void setGradeid(Integer gradeid) {
		this.gradeid = gradeid;
	}

	/**
	 * @return the areaid
	 */
	public Integer getAreaid() {
		return areaid;
	}

	/**
	 * @param areaid
	 *            the areaid to set
	 */
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	/**
	 * @return the yspecialty
	 */
	public DtoSpecialty getYspecialty() {
		return yspecialty;
	}

	/**
	 * @param yspecialty
	 *            the yspecialty to set
	 */
	public void setYspecialty(DtoSpecialty yspecialty) {
		this.yspecialty = yspecialty;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
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
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term
	 *            the term to set
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
	 * @param degree
	 *            the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the startdate
	 */
	public String getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate
	 *            the startdate to set
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the enddate
	 */
	public String getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate
	 *            the enddate to set
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	/**
	 * @return the times
	 */
	public Integer getTimes() {
		return times;
	}

	/**
	 * @param times
	 *            the times to set
	 */
	public void setTimes(Integer times) {
		this.times = times;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the costdetail
	 */
	public String getCostdetail() {
		return costdetail;
	}

	/**
	 * @param costdetail
	 *            the costdetail to set
	 */
	public void setCostdetail(String costdetail) {
		this.costdetail = costdetail;
	}

	/**
	 * @return the cost
	 */
	public Float getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(Float cost) {
		this.cost = cost;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
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
	 * @param agelimit
	 *            the agelimit to set
	 */
	public void setAgelimit(String agelimit) {
		this.agelimit = agelimit;
	}

	/**
	 * @return the genderlimit
	 */
	public String getGenderlimit() {
		return genderlimit;
	}

	/**
	 * @param genderlimit
	 *            the graderlimit to set
	 */
	public void setGenderlimit(String genderlimit) {
		this.genderlimit = genderlimit;
	}

	/**
	 * @return the ability
	 */
	public String getAbility() {
		return ability;
	}

	/**
	 * @param ability
	 *            the ability to set
	 */
	public void setAbility(String ability) {
		this.ability = ability;
	}

	/**
	 * @return the manner
	 */
	public String getManner() {
		return manner;
	}

	/**
	 * @param manner
	 *            the manner to set
	 */
	public void setManner(String manner) {
		this.manner = manner;
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
	 * @return the yschedules
	 */
	public Set<DtoSchedule> getYschedules() {
		return yschedules;
	}

	/**
	 * @param yschedules
	 *            the yschedules to set
	 */
	public void setYschedules(Set<DtoSchedule> yschedules) {
		this.yschedules = yschedules;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
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
