package com.hx.xk.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hx.xk.entity.base.BaseEntityXk;

/**
 * Ygrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ygrade")
public class Ygrade extends BaseEntityXk implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4559929703847563023L;
	private Integer gradeid;
	private Integer areaid;
	private Yspecialty yspecialty;
	private String code;//名称
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
	private String ability;//能力
	private String manner;//态度
	private String link;
	private String applystatus;
	private Set<Ymygrade> ymygrades = new HashSet<Ymygrade>(0);
	private Set<Yschedule> yschedules = new HashSet<Yschedule>(0);

	// Constructors

	/** default constructor */
	public Ygrade() {
	}

	/** full constructor */
	public Ygrade(Yspecialty yspecialty, String code, String area, String year,
			String term, String degree, String startdate, String enddate,
			Integer times, String type, String phone, String costdetail,
			Float cost, String description, String agelimit,
			String genderlimit, String ability, String manner,
			Set<Ymygrade> ymygrades, Set<Yschedule> yschedules) {
		this.yspecialty = yspecialty;
		this.code = code;
		this.area = area;
		this.year = year;
		this.term = term;
		this.degree = degree;
		this.startdate = startdate;
		this.enddate = enddate;
		this.times = times;
		this.type = type;
		this.phone = phone;
		this.costdetail = costdetail;
		this.cost = cost;
		this.description = description;
		this.agelimit = agelimit;
		this.genderlimit = genderlimit;
		this.ability = ability;
		this.manner = manner;
		this.ymygrades = ymygrades;
		this.yschedules = yschedules;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gradeid", unique = true, nullable = false)
	public Integer getGradeid() {
		return this.gradeid;
	}

	public void setGradeid(Integer gradeid) {
		this.gradeid = gradeid;
	}
	 
	/**
	 * @return the areaid
	 */
	@JoinColumn(name = "areaid")
	public Integer getAreaid() {
		return areaid;
	}

	/**
	 * @param areaid the areaid to set
	 */
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "specialtyid")
	public Yspecialty getYspecialty() {
		return this.yspecialty;
	}

	public void setYspecialty(Yspecialty yspecialty) {
		this.yspecialty = yspecialty;
	}

	@Column(name = "code", length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "area", length = 128)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "year", length = 32)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "term", length = 32)
	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Column(name = "degree", length = 32)
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "startdate", length = 32)
	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	@Column(name = "enddate", length = 32)
	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	@Column(name = "times")
	public Integer getTimes() {
		return this.times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	@Column(name = "type", length = 32)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "phone", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "costdetail", length = 128)
	public String getCostdetail() {
		return this.costdetail;
	}

	public void setCostdetail(String costdetail) {
		this.costdetail = costdetail;
	}

	@Column(name = "cost", precision = 12)
	public Float getCost() {
		return this.cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	@Column(name = "description", length = 2048)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "agelimit", length = 128)
	public String getAgelimit() {
		return this.agelimit;
	}

	public void setAgelimit(String agelimit) {
		this.agelimit = agelimit;
	}

	@Column(name = "genderlimit", length = 32)
	public String getGenderlimit() {
		return this.genderlimit;
	}

	public void setGenderlimit(String genderlimit) {
		this.genderlimit = genderlimit;
	}

	@Column(name = "ability", length = 128)
	public String getAbility() {
		return this.ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	@Column(name = "manner", length = 128)
	public String getManner() {
		return this.manner;
	}

	public void setManner(String manner) {
		this.manner = manner;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ygrade")
	public Set<Ymygrade> getYmygrades() {
		return this.ymygrades;
	}

	public void setYmygrades(Set<Ymygrade> ymygrades) {
		this.ymygrades = ymygrades;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ygrade")
	public Set<Yschedule> getYschedules() {
		return this.yschedules;
	}

	public void setYschedules(Set<Yschedule> yschedules) {
		this.yschedules = yschedules;
	}

	/**
	 * @return the link
	 */
	@Column(name = "link", length = 128)
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the applystatus
	 */
	@Column(name = "applystatus", length = 128)
	public String getApplystatus() {
		return applystatus;
	}

	/**
	 * @param applystatus the applystatus to set
	 */
	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}

}