package com.hx.xk.entity;

import static javax.persistence.GenerationType.IDENTITY;

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
 * Yspecialty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yspecialty")
public class Yspecialty extends BaseEntityXk implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2434075769917233519L;
	private Integer specialtyid;
	private String name;
	private Set<Ygrade> ygrades = new HashSet<Ygrade>(0);

	// Constructors

	/** default constructor */
	public Yspecialty() {
	}

	/** full constructor */
	public Yspecialty(String name, Set<Ygrade> ygrades) {
		this.name = name;
		this.ygrades = ygrades;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "specialtyid", unique = true, nullable = false)
	public Integer getSpecialtyid() {
		return this.specialtyid;
	}

	public void setSpecialtyid(Integer specialtyid) {
		this.specialtyid = specialtyid;
	}

	@Column(name = "name", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "yspecialty")
	public Set<Ygrade> getYgrades() {
		return this.ygrades;
	}

	public void setYgrades(Set<Ygrade> ygrades) {
		this.ygrades = ygrades;
	}

}