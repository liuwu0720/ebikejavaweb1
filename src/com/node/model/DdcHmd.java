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
 * DdcHmd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DDC_HMD")
public class DdcHmd implements java.io.Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 5176637778815769903L;
	// Fields

	private Long id;
	private String jsrxm;
	private String sfzhm;
	private String xb;
	private String lxdh;
	private String bz;
	private String cjr;
	private String cjbm;
	private Date cjrq;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;

	// Constructors

	/** default constructor */
	public DdcHmd() {
	}

	/** full constructor */
	public DdcHmd(String jsrxm, String sfzhm, String xb, String lxdh,
			String bz, String cjr, String cjbm, Date cjrq, String synFlag,
			String tranFlag, Date tranDate) {
		this.jsrxm = jsrxm;
		this.sfzhm = sfzhm;
		this.xb = xb;
		this.lxdh = lxdh;
		this.bz = bz;
		this.cjr = cjr;
		this.cjbm = cjbm;
		this.cjrq = cjrq;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
	}

	// Property accessors
	@SequenceGenerator(name = "DDC_HMD", sequenceName = "SEQ_DDC_HMD", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_HMD")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "JSRXM", length = 20)
	public String getJsrxm() {
		return this.jsrxm;
	}

	public void setJsrxm(String jsrxm) {
		this.jsrxm = jsrxm;
	}

	@Column(name = "SFZHM", length = 20)
	public String getSfzhm() {
		return this.sfzhm;
	}

	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}

	@Column(name = "XB", length = 10)
	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	@Column(name = "LXDH", length = 20)
	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	@Column(name = "BZ", length = 200)
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "CJR", length = 20)
	public String getCjr() {
		return this.cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	@Column(name = "CJBM", length = 20)
	public String getCjbm() {
		return this.cjbm;
	}

	public void setCjbm(String cjbm) {
		this.cjbm = cjbm;
	}

	@Column(name = "CJRQ", length = 7)
	public Date getCjrq() {
		return this.cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	@Column(name = "SYN_FLAG", length = 10)
	public String getSynFlag() {
		return this.synFlag;
	}

	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	@Column(name = "TRAN_FLAG", length = 10)
	public String getTranFlag() {
		return this.tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

	@Column(name = "TRAN_DATE", length = 7)
	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

}