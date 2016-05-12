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
 * Ymygrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ymygrade")
public class Ymygrade extends BaseEntityXk implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1019190611143955201L;
	private Integer mygradeid;
	private Yuser yuser;
	private Ygrade ygrade;
	private String reservedcode;
	private String applystatus;
	private String overtime;

	// Constructors

	/** default constructor */
	public Ymygrade() {
	}

	/** full constructor */
	public Ymygrade(Yuser yuser, Ygrade ygrade, String reservedcode,
			String applystatus, String overtime) {
		this.yuser = yuser;
		this.ygrade = ygrade;
		this.reservedcode = reservedcode;
		this.applystatus = applystatus;
		this.overtime = overtime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mygradeid", unique = true, nullable = false)
	public Integer getMygradeid() {
		return this.mygradeid;
	}

	public void setMygradeid(Integer mygradeid) {
		this.mygradeid = mygradeid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	public Yuser getYuser() {
		return this.yuser;
	}

	public void setYuser(Yuser yuser) {
		this.yuser = yuser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gradeid")
	public Ygrade getYgrade() {
		return this.ygrade;
	}

	public void setYgrade(Ygrade ygrade) {
		this.ygrade = ygrade;
	}

	@Column(name = "reservedcode", length = 32)
	public String getReservedcode() {
		return this.reservedcode;
	}

	public void setReservedcode(String reservedcode) {
		this.reservedcode = reservedcode;
	}

	@Column(name = "applystatus", length = 32)
	public String getApplystatus() {
		return this.applystatus;
	}

	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}

	@Column(name = "overtime", length = 32)
	public String getOvertime() {
		return this.overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

}