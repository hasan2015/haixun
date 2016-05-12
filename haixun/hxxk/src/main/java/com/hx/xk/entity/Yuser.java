package com.hx.xk.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hx.xk.entity.base.BaseEntityXk;

/**
 * Yuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yuser")
public class Yuser extends BaseEntityXk implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5845265870689110054L;
	private Integer userid;
	private String name;
	private String id;
	private String mobile;
	private String school;
	private String grade;
	private String gender;
	private String patriarch;//家长姓名
	private String homephone;
	private String birthday;
	private String description;
	private String password;
	private Integer status;
	private Timestamp latestdate;

	private Set<Ymygrade> ymygrades = new HashSet<Ymygrade>(0);
	private Set<Ywxaccount> ywxaccounts = new HashSet<Ywxaccount>(0);

	// Constructors

	/** default constructor */
	public Yuser() {
	}

	/** full constructor */
	public Yuser(String name, String id, String mobile, String school,
			String grade, String gender, String patriarch, String homephone,
			String birthday, String description, String password,
			Set<Ymygrade> ymygrades) {
		this.name = name;
		this.id = id;
		this.mobile = mobile;
		this.school = school;
		this.grade = grade;
		this.gender = gender;
		this.patriarch = patriarch;
		this.homephone = homephone;
		this.birthday = birthday;
		this.description = description;
		this.password = password;
		this.ymygrades = ymygrades;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userid", unique = true, nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "id", length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "mobile", length = 32)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "school", length = 128)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "grade", length = 32)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "gender", length = 32)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "patriarch", length = 32)
	public String getPatriarch() {
		return this.patriarch;
	}

	public void setPatriarch(String patriarch) {
		this.patriarch = patriarch;
	}

	@Column(name = "homephone", length = 32)
	public String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	@Column(name = "birthday", length = 32)
	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "description", length = 2048)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "yuser")
	public Set<Ymygrade> getYmygrades() {
		return this.ymygrades;
	}

	public void setYmygrades(Set<Ymygrade> ymygrades) {
		this.ymygrades = ymygrades;
	}

	/**
	 * @return the ywxaccounts
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "yuser")
	public Set<Ywxaccount> getYwxaccounts() {
		return ywxaccounts;
	}

	/**
	 * @param ywxaccounts the ywxaccounts to set
	 */
	public void setYwxaccounts(Set<Ywxaccount> ywxaccounts) {
		this.ywxaccounts = ywxaccounts;
	}

	/**
	 * @return the password
	 */
	@Column(name = "password", length = 32)
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the latestdate
	 */
	@Column(name = "latestdate")
	public Timestamp getLatestdate() {
		return latestdate;
	}

	/**
	 * @param latestdate the latestdate to set
	 */
	public void setLatestdate(Timestamp latestdate) {
		this.latestdate = latestdate;
	}

}