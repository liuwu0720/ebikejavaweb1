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
 * DdcSjzd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DDC_SJZD")
public class DdcSjzd implements java.io.Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = -670824204661471129L;
	// Fields

	private Long id;
	private String dmz;
	private String dmms1;
	private String dmms2;
	private String dmlb;// 代码类别
	private String bz;
	private String cjr;
	private Date cjrq;
	private String cjbm;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;

	// Constructors

	/** default constructor */
	public DdcSjzd() {
	}

	/** full constructor */
	public DdcSjzd(String dmz, String dmms1, String dmms2, String dmlb,
			String bz, String cjr, Date cjrq, String cjbm, String synFlag,
			String tranFlag, Date tranDate) {
		this.dmz = dmz;
		this.dmms1 = dmms1;
		this.dmms2 = dmms2;
		this.dmlb = dmlb;
		this.bz = bz;
		this.cjr = cjr;
		this.cjrq = cjrq;
		this.cjbm = cjbm;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "DDC_SJZD", sequenceName = "SEQ_DDC_SJZD", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_SJZD")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DMZ", length = 20)
	public String getDmz() {
		return this.dmz;
	}

	public void setDmz(String dmz) {
		this.dmz = dmz;
	}

	@Column(name = "DMMS1", length = 200)
	public String getDmms1() {
		return this.dmms1;
	}

	public void setDmms1(String dmms1) {
		this.dmms1 = dmms1;
	}

	@Column(name = "DMMS2", length = 200)
	public String getDmms2() {
		return this.dmms2;
	}

	public void setDmms2(String dmms2) {
		this.dmms2 = dmms2;
	}

	@Column(name = "DMLB", length = 20)
	public String getDmlb() {
		return this.dmlb;
	}

	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}

	@Column(name = "BZ", length = 50)
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

	@Column(name = "CJRQ", length = 7)
	public Date getCjrq() {
		return this.cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	@Column(name = "CJBM", length = 20)
	public String getCjbm() {
		return this.cjbm;
	}

	public void setCjbm(String cjbm) {
		this.cjbm = cjbm;
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