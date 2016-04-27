package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * DdcDriver entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DDC_DRIVER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DdcDriver implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 7678082016832049336L;
	private Long id;
	private String dabh;
	private Long daid;
	private String jsrxm;
	private String xb;
	private String lxdh;
	private String synFlag;
	private Date tranDate;
	private String userCode;
	private String userPassword;
	private String sfzhm;
	private String vcUserImg;// 驾驶人1图片
	private String vcShowUserImg;

	// Constructors

	/** default constructor */
	public DdcDriver() {
	}

	// Property accessors
	@SequenceGenerator(name = "DDC_DRIVER", sequenceName = "SEQ_DDC_DRIVER", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_DRIVER")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DABH", length = 50)
	public String getDabh() {
		return this.dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	@Column(name = "DAID", precision = 0)
	public Long getDaid() {
		return this.daid;
	}

	public void setDaid(Long daid) {
		this.daid = daid;
	}

	@Column(name = "JSRXM", length = 50)
	public String getJsrxm() {
		return this.jsrxm;
	}

	public void setJsrxm(String jsrxm) {
		this.jsrxm = jsrxm;
	}

	@Column(name = "XB", length = 50)
	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	@Column(name = "LXDH", length = 50)
	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	@Column(name = "SYN_FLAG", length = 50)
	public String getSynFlag() {
		return this.synFlag;
	}

	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	@Column(name = "TRAN_DATE", length = 7)
	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Column(name = "USER_CODE", length = 50)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "USER_PASSWORD", length = 50)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "SFZHM", length = 50)
	public String getSfzhm() {
		return sfzhm;
	}

	/**
	 * @param sfzhm
	 *            : set the property sfzhm.
	 */
	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}

	@Column(name = "VCUSER_IMG", length = 100)
	public String getVcUserImg() {
		return vcUserImg;
	}

	/**
	 * @param vcUserImg
	 *            : set the property vcUserImg.
	 */
	public void setVcUserImg(String vcUserImg) {
		this.vcUserImg = vcUserImg;
	}

	@Transient
	public String getVcShowUserImg() {
		return vcShowUserImg;
	}

	/**
	 * @param vcShowUserImg
	 *            : set the property vcShowUserImg.
	 */
	public void setVcShowUserImg(String vcShowUserImg) {
		this.vcShowUserImg = vcShowUserImg;
	}

}