package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PicPath entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PIC_PATH")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PicPath implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 5985290281724432361L;
	private Integer id;
	private String vcAddpath;
	private Date dtAdd;
	private Integer iType;
	private String vcType;
	private String vcParsePath;

	// Constructors

	/** default constructor */
	public PicPath() {
	}

	/** full constructor */
	public PicPath(String vcAddpath, Date dtAdd, Integer iType, String vcType,
			String vcParsePath) {
		this.vcAddpath = vcAddpath;
		this.dtAdd = dtAdd;
		this.iType = iType;
		this.vcType = vcType;
		this.vcParsePath = vcParsePath;
	}

	// Property accessors
	// Property accessors
	@SequenceGenerator(name = "PIC_PATH", sequenceName = "SEQ_PIC_PATH", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "PIC_PATH")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "VC_ADDPATH", length = 100)
	public String getVcAddpath() {
		return this.vcAddpath;
	}

	public void setVcAddpath(String vcAddpath) {
		this.vcAddpath = vcAddpath;
	}

	@Column(name = "DT_ADD", length = 7)
	public Date getDtAdd() {
		return this.dtAdd;
	}

	public void setDtAdd(Date dtAdd) {
		this.dtAdd = dtAdd;
	}

	@Column(name = "I_TYPE", precision = 0)
	public Integer getiType() {
		return this.iType;
	}

	public void setiType(Integer iType) {
		this.iType = iType;
	}

	@Column(name = "VC_TYPE", length = 100)
	public String getVcType() {
		return this.vcType;
	}

	public void setVcType(String vcType) {
		this.vcType = vcType;
	}

	@Column(name = "VC_PARSE_PATH", length = 100)
	public String getVcParsePath() {
		return this.vcParsePath;
	}

	public void setVcParsePath(String vcParsePath) {
		this.vcParsePath = vcParsePath;
	}

}