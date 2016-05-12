package com.hx.xk.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hx.xk.entity.base.BaseEntityXk;

/**
 * Yschedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yschedule")
public class Yschedule extends BaseEntityXk implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8196290145850809038L;
	private Integer scheduleid;
	private Ygrade ygrade;
	private String week;
	private String starttime;
	private String endtime;
	private String address;

	// Constructors

	/** default constructor */
	public Yschedule() {
	}

	/** full constructor */
	public Yschedule(Ygrade ygrade, String week, String starttime,
			String endtime, String address) {
		this.ygrade = ygrade;
		this.week = week;
		this.starttime = starttime;
		this.endtime = endtime;
		this.address = address;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "scheduleid", unique = true, nullable = false)
	public Integer getScheduleid() {
		return this.scheduleid;
	}

	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gradeid")
	public Ygrade getYgrade() {
		return this.ygrade;
	}

	public void setYgrade(Ygrade ygrade) {
		this.ygrade = ygrade;
	}

	@Column(name = "week", length = 32)
	public String getWeek() {
		return this.week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	@Column(name = "starttime", length = 32)
	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	@Column(name = "endtime", length = 32)
	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Column(name = "address", length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}