package com.node.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	private String vcUserWorkImg;// 居住证或在职证明
	private Integer userStatus;
	private Integer illeagalTimes;
	private Integer ssdwId;
	private String hyxhzh;
	private String userNote;
	// Constructors

	private String vcUserCardImg1;// 身份证正面
	private String vcUserCardImg2;

	private byte[] blobUserCardImg1;
	private byte[] blobUserCardImg2;
	private byte[] blobUserImg;
	private String xjFlag;
	private String xjMsg;
	private Date xjRq;
	
	
	private String vcShowUserImgShow;
	private String vcUserWorkImgShow;
	private String vcUserCardImg1Show;// 身份证正面
	private String vcUserCardImg2Show;
	
	private String hyxhmc;
	private String ssdwmc;
	
	
	
	

	@Transient
	public String getHyxhmc() {
		return hyxhmc;
	}

	/**
	 * @param hyxhmc : set the property hyxhmc.
	 */
	public void setHyxhmc(String hyxhmc) {
		this.hyxhmc = hyxhmc;
	}

	@Transient
	public String getSsdwmc() {
		return ssdwmc;
	}

	/**
	 * @param ssdwmc : set the property ssdwmc.
	 */
	public void setSsdwmc(String ssdwmc) {
		this.ssdwmc = ssdwmc;
	}
	
	
	

	@Transient
	public String getVcUserCardImg1Show() {
		return vcUserCardImg1Show;
	}

	/**
	 * @param vcUserCardImg1Show : set the property vcUserCardImg1Show.
	 */
	public void setVcUserCardImg1Show(String vcUserCardImg1Show) {
		this.vcUserCardImg1Show = vcUserCardImg1Show;
	}

	@Transient
	public String getVcUserCardImg2Show() {
		return vcUserCardImg2Show;
	}

	/**
	 * @param vcUserCardImg2Show : set the property vcUserCardImg2Show.
	 */
	public void setVcUserCardImg2Show(String vcUserCardImg2Show) {
		this.vcUserCardImg2Show = vcUserCardImg2Show;
	}

	@Transient
	public String getVcShowUserImgShow() {
		return vcShowUserImgShow;
	}

	/**
	 * @param vcShowUserImgShow : set the property vcShowUserImgShow.
	 */
	public void setVcShowUserImgShow(String vcShowUserImgShow) {
		this.vcShowUserImgShow = vcShowUserImgShow;
	}

	@Transient
	public String getVcUserWorkImgShow() {
		return vcUserWorkImgShow;
	}

	/**
	 * @param vcUserWorkImgShow : set the property vcUserWorkImgShow.
	 */
	public void setVcUserWorkImgShow(String vcUserWorkImgShow) {
		this.vcUserWorkImgShow = vcUserWorkImgShow;
	}

	@Column(name = "XJ_FLAG", length = 10)
	public String getXjFlag() {
		return xjFlag;
	}

	/**
	 * @param xjFlag : set the property xjFlag.
	 */
	public void setXjFlag(String xjFlag) {
		this.xjFlag = xjFlag;
	}

	@Column(name = "XJ_MSG", length = 200)
	public String getXjMsg() {
		return xjMsg;
	}

	/**
	 * @param xjMsg : set the property xjMsg.
	 */
	public void setXjMsg(String xjMsg) {
		this.xjMsg = xjMsg;
	}

	@Column(name = "XJ_RQ", length = 7)
	public Date getXjRq() {
		return xjRq;
	}

	/**
	 * @param xjRq : set the property xjRq.
	 */
	public void setXjRq(Date xjRq) {
		this.xjRq = xjRq;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "BLOB_USER_CARDIMG1", columnDefinition = "BLOB", nullable = true)
	public byte[] getBlobUserCardImg1() {
		return blobUserCardImg1;
	}

	/**
	 * @param blobUserCardImg1
	 *            : set the property blobUserCardImg1.
	 */
	public void setBlobUserCardImg1(byte[] blobUserCardImg1) {
		this.blobUserCardImg1 = blobUserCardImg1;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "BLOB_USER_CARDIMG2", columnDefinition = "BLOB", nullable = true)
	public byte[] getBlobUserCardImg2() {
		return blobUserCardImg2;
	}

	/**
	 * @param blobUserCardImg2
	 *            : set the property blobUserCardImg2.
	 */
	public void setBlobUserCardImg2(byte[] blobUserCardImg2) {
		this.blobUserCardImg2 = blobUserCardImg2;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "BLOB_USER_IMG", columnDefinition = "BLOB", nullable = true)
	public byte[] getBlobUserImg() {
		return blobUserImg;
	}

	/**
	 * @param blobUserImg
	 *            : set the property blobUserImg.
	 */
	public void setBlobUserImg(byte[] blobUserImg) {
		this.blobUserImg = blobUserImg;
	}

	@Column(name = "VC_USER_CARDIMG1", length = 100)
	public String getVcUserCardImg1() {
		return vcUserCardImg1;
	}

	/**
	 * @param vcUserCardImg1
	 *            : set the property vcUserCardImg1.
	 */
	public void setVcUserCardImg1(String vcUserCardImg1) {
		this.vcUserCardImg1 = vcUserCardImg1;
	}

	@Column(name = "VC_USER_CARDIMG2", length = 100)
	public String getVcUserCardImg2() {
		return vcUserCardImg2;
	}

	/**
	 * @param vcUserCarImg2
	 *            : set the property vcUserCarImg2.
	 */
	public void setVcUserCardImg2(String vcUserCardImg2) {
		this.vcUserCardImg2 = vcUserCardImg2;
	}

	/** default constructor */
	public DdcDriver() {
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "VC_USERWORKIMG")
	public String getVcUserWorkImg() {
		return vcUserWorkImg;
	}

	/**
	 * @param vcUserWorkImg
	 *            : set the property vcUserWorkImg.
	 */
	public void setVcUserWorkImg(String vcUserWorkImg) {
		this.vcUserWorkImg = vcUserWorkImg;
	}

	@Column(name = "USER_STATUS")
	public Integer getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus
	 *            : set the property userStatus.
	 */
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name = "ILLEAGE_TIMES", length = 100)
	public Integer getIlleagalTimes() {
		return illeagalTimes;
	}

	/**
	 * @param illeagalTimes
	 *            : set the property illeagalTimes.
	 */
	public void setIlleagalTimes(Integer illeagalTimes) {
		this.illeagalTimes = illeagalTimes;
	}

	@Column(name = "SSDWID")
	public Integer getSsdwId() {
		return ssdwId;
	}

	/**
	 * @param ssdwId
	 *            : set the property ssdwId.
	 */
	public void setSsdwId(Integer ssdwId) {
		this.ssdwId = ssdwId;
	}

	@Column(name = "HYXHZH", length = 20)
	public String getHyxhzh() {
		return hyxhzh;
	}

	/**
	 * @param hyxhzh
	 *            : set the property hyxhzh.
	 */
	public void setHyxhzh(String hyxhzh) {
		this.hyxhzh = hyxhzh;
	}

	@Column(name = "USER_NOTE", length = 100)
	public String getUserNote() {
		return userNote;
	}

	/**
	 * @param userNote : set the property userNote.
	 */
	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}
	
	
}