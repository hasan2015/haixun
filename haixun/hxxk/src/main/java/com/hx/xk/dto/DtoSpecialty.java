/**
 * 
 */
package com.hx.xk.dto;

import com.hx.xk.dto.base.DtoBase;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:24:12
 * 
 */
public class DtoSpecialty extends DtoBase implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886458883664937593L;
	private Integer specialtyid;
	private String name;
	private Integer gradecount;

	/**
	 * @return the specialtyid
	 */
	public Integer getSpecialtyid() {
		return specialtyid;
	}

	/**
	 * @param specialtyid
	 *            the specialtyid to set
	 */
	public void setSpecialtyid(Integer specialtyid) {
		this.specialtyid = specialtyid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gradecount
	 */
	public Integer getGradecount() {
		return gradecount;
	}

	/**
	 * @param gradecount the gradecount to set
	 */
	public void setGradecount(Integer gradecount) {
		this.gradecount = gradecount;
	}

}
