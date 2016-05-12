package com.hx.xk.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

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
 * Ymyclock entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ymyclock")
public class Ymyclock extends BaseEntityXk implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1019190611143955201L;
	private Integer myclockid;
	private Ymygrade ymygrade; 
	private String name;
	private Timestamp starttime;
	private Timestamp endtime;
	private Integer intervalm;
	private Integer duration;

 

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "myclockid", unique = true, nullable = false)
	public Integer getMyclockid() {
		return this.myclockid;
	}

	public void setMyclockid(Integer myclockid) {
		this.myclockid = myclockid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mygradeid")
	public Ymygrade getYmygrade() {
		return this.ymygrade;
	}

	public void setYmygrade(Ymygrade ymygrade) {
		this.ymygrade = ymygrade;
	}
 

	@Column(name = "name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the starttime
	 */
	@Column(name = "starttime")
	public Timestamp getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	@Column(name = "endtime")
	public Timestamp getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the intervalm
	 */
	@Column(name = "intervalm")
	public Integer getIntervalm() {
		return intervalm;
	}

	/**
	 * @param intervalm the intervalm to set
	 */
	public void setIntervalm(Integer intervalm) {
		this.intervalm = intervalm;
	}

	/**
	 * @return the duration
	 */
	@Column(name = "duration")
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	

}