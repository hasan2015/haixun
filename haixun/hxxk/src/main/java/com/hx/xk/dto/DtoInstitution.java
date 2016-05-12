/**
 * 
 */
package com.hx.xk.dto;

import java.sql.Timestamp;

import com.hx.xk.dto.base.DtoBase;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:24:12
 * 
 */
public class DtoInstitution extends DtoBase implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8496331643287050432L;
	private Integer institutionid;
	private String name;
	private String homeurl;
	private Timestamp refreshdate;
	/**
	 * @return the institutionid
	 */
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
