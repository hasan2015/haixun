package com.hx.xk.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Dresult entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xresult")
public class Xresult extends BaseEntity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3581780393620675025L;
	private Long resultid;
	private String code;
	private String result;

	// Constructors

	/** default constructor */
	public Xresult() {
	}

	/** minimal constructor */
	public Xresult(Long resultid) {
		this.resultid = resultid;
	}

	/** full constructor */
	public Xresult(Long resultid, String code, String result) {
		this.resultid = resultid;
		this.code = code;
		this.result = result;
	}

	// Property accessors
	@Id
	// @TableGenerator(name = "S_DRESULT_ID_GENERATOR", pkColumnValue =
	// "S_DRESULT", table = "entity_id_table", pkColumnName = "id_name_col",
	// valueColumnName = "value_col", allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// , generator = "S_DRESULT_ID_GENERATOR")
	@Column(name = "resultid", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getResultid() {
		return resultid;
	}

	public void setResultid(Long resultid) {
		this.resultid = resultid;
	}

	@Column(name = "code", length = 32)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "result", length = 2048)
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}