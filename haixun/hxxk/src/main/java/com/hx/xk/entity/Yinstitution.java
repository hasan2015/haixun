package com.hx.xk.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hx.xk.entity.base.BaseEntityXk;

/**
 * yinstitution entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yinstitution")
public class Yinstitution extends BaseEntityXk implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6474779173680435900L;
	private Integer institutionid;
	private String name;
	private String homeurl;
	private Timestamp refreshdate;
	/**
	 * @return the institutionid
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "institutionid", unique = true, nullable = false)
	public Integer getInstitutionid() {
		return institutionid;
	}
	/**
	 * @param institutionid the institutionid to set
	 */
	public void setInstitutionid(Integer institutionid) {
		this.institutionid = institutionid;
	}
	/**
	 * @return the name
	 */
	@Column(name = "name", length = 128)
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the homeurl
	 */
	@Column(name = "homeurl", length = 128)
	public String getHomeurl() {
		return homeurl;
	}
	/**
	 * @param homeurl the homeurl to set
	 */
	public void setHomeurl(String homeurl) {
		this.homeurl = homeurl;
	}
	/**
	 * @return the refreshdate
	 */
	@Column(name = "refreshdate")
	public Timestamp getRefreshdate() {
		return refreshdate;
	}
	/**
	 * @param refreshdate the refreshdate to set
	 */
	public void setRefreshdate(Timestamp refreshdate) {
		this.refreshdate = refreshdate;
	}
	
}