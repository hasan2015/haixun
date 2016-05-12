package com.hx.xk.dto.base;

import java.sql.Timestamp;

import com.hx.xk.common.XkConstant;

/**
 * The entity base contains common fields.
 * 
 */
public class DtoBase implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7562420849930124552L;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;
	private Short active = XkConstant.V_TRUE;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Short getActive() {
		return active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

}
