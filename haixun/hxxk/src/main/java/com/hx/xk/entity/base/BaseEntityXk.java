package com.hx.xk.entity.base;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.hx.xk.common.XkConstant;

@MappedSuperclass
public class BaseEntityXk extends BaseEntity {
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;//=new Timestamp(System.currentTimeMillis());
	private Short active = XkConstant.V_TRUE;

	/**
	 * @return the createdBy
	 */
	@Column(name = "createdby")
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	@Column(name = "createddate")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	@Column(name = "updatedby")
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	@Column(name = "updateddate")
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the active
	 */
	@Column(name = "active")
	public Short getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Short active) {
		this.active = active;
	}

	 
}
