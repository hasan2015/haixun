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
 * Ywxaccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywxaccount")
public class Ywxaccount extends BaseEntityXk implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5845265870689110054L;
	private Integer wxaccountid;
	private Yuser yuser;
	private String openid;
	private String nickname;
	private String sex;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private String privilege;
	private String unionid;
	private Integer status;

	/**
	 * @return the wxaccountid
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "wxaccountid", unique = true, nullable = false)
	public Integer getWxaccountid() {
		return wxaccountid;
	}

	/**
	 * @param wxaccountid
	 *            the wxaccountid to set
	 */
	public void setWxaccountid(Integer wxaccountid) {
		this.wxaccountid = wxaccountid;
	}

	/**
	 * @return the yuser
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	public Yuser getYuser() {
		return yuser;
	}

	/**
	 * @param yuser
	 *            the yuser to set
	 */
	public void setYuser(Yuser yuser) {
		this.yuser = yuser;
	}

	/**
	 * @return the openid
	 */
	@Column(name = "openid", length = 128)
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid
	 *            the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * @return the nickname
	 */
	@Column(name = "nickname", length = 128)
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the sex
	 */
	@Column(name = "sex", length = 128)
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the province
	 */
	@Column(name = "province", length = 128)
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	@Column(name = "city", length = 128)
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	@Column(name = "country", length = 128)
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the headimgurl
	 */
	@Column(name = "headimgurl", length = 128)
	public String getHeadimgurl() {
		return headimgurl;
	}

	/**
	 * @param headimgurl
	 *            the headimgurl to set
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	/**
	 * @return the privilege
	 */
	@Column(name = "privilege", length = 128)
	public String getPrivilege() {
		return privilege;
	}

	/**
	 * @param privilege
	 *            the privilege to set
	 */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	/**
	 * @return the unionid
	 */
	@Column(name = "unionid", length = 128)
	public String getUnionid() {
		return unionid;
	}

	/**
	 * @param unionid
	 *            the unionid to set
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	/**
	 * @return the status
	 */
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}