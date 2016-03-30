package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * DdcHyxhSsdwclsb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DDC_HYXH_SSDWCLSB", uniqueConstraints = @UniqueConstraint(columnNames = "LSH"))
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DdcHyxhSsdwclsb implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 204700208717659645L;
	private Long id;
	private String lsh;
	private String hyxhzh;
	private String hyxhzhName;
	private String ssdwId;// 所属单位ID,对应 DDC_HYXH_SSDW的记录ID
	private String ssdwName;// 申报单位名称
	private String cphm;
	private String ppxh;
	private String cysy;// 车身颜色code
	private String cysyName;// 车身颜色
	private String djh;
	private String jtzz;
	private String jsrxm1;
	private String xb1;
	private String sfzmhm1;
	private String lxdh1;
	private String jsrxm2;
	private String xb2;
	private String sfzmhm2;
	private String lxdh2;
	private String jsrxm3;
	private String xb3;
	private String sfzmhm3;
	private String lxdh3;
	private String xsqy;// 行驶区域
	private String xsqyName;
	private String bz;
	private String sqr;
	private Date sqrq;
	private String slzl;// 受理资料，分割 字典表
	private String slyj;// 受理意见 0同意 1不同意
	private String slbz;
	private String slr;
	private Date slrq;
	private String slbm;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	private String sqip;
	private String vcEbikeImg;// 车身图片
	private String vcUser1Img;// 驾驶人1图片
	private String vcUser2Img;// 驾驶人2图片
	private String vcShowEbikeImg;
	private String vcShowUser1Img;
	private String vcShowUser2Img;
	private Integer slIndex;
	private String tbyy;// 退办原因
	private boolean isApprove = false;

	// Constructors

	/** default constructor */
	public DdcHyxhSsdwclsb() {
	}

	/** minimal constructor */
	public DdcHyxhSsdwclsb(String lsh) {
		this.lsh = lsh;
	}

	/** full constructor */
	public DdcHyxhSsdwclsb(String lsh, String hyxhzh, String ssdwId,
			String cphm, String ppxh, String cysy, String djh, String jtzz,
			String jsrxm1, String xb1, String sfzmhm1, String lxdh1,
			String jsrxm2, String xb2, String sfzmhm2, String lxdh2,
			String jsrxm3, String xb3, String sfzmhm3, String lxdh3,
			String xsqy, String bz, String sqr, Date sqrq, String slzl,
			String slyj, String slbz, String slr, Date slrq, String slbm,
			String synFlag, String tranFlag, Date tranDate, String sqip) {
		this.lsh = lsh;
		this.hyxhzh = hyxhzh;
		this.ssdwId = ssdwId;
		this.cphm = cphm;
		this.ppxh = ppxh;
		this.cysy = cysy;
		this.djh = djh;
		this.jtzz = jtzz;
		this.jsrxm1 = jsrxm1;
		this.xb1 = xb1;
		this.sfzmhm1 = sfzmhm1;
		this.lxdh1 = lxdh1;
		this.jsrxm2 = jsrxm2;
		this.xb2 = xb2;
		this.sfzmhm2 = sfzmhm2;
		this.lxdh2 = lxdh2;
		this.jsrxm3 = jsrxm3;
		this.xb3 = xb3;
		this.sfzmhm3 = sfzmhm3;
		this.lxdh3 = lxdh3;
		this.xsqy = xsqy;
		this.bz = bz;
		this.sqr = sqr;
		this.sqrq = sqrq;
		this.slzl = slzl;
		this.slyj = slyj;
		this.slbz = slbz;
		this.slr = slr;
		this.slrq = slrq;
		this.slbm = slbm;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.sqip = sqip;
	}

	@Id
	@SequenceGenerator(name = "DDC_HYXH_SSDWCLSB", sequenceName = "SEQ_HYXH_SSDWCLSB_XH", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_HYXH_SSDWCLSB")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "LSH", unique = true, nullable = false, length = 50)
	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	@Column(name = "HYXHZH", length = 50)
	public String getHyxhzh() {
		return this.hyxhzh;
	}

	public void setHyxhzh(String hyxhzh) {
		this.hyxhzh = hyxhzh;
	}

	@Column(name = "SSDW_ID", length = 200)
	public String getSsdwId() {
		return this.ssdwId;
	}

	public void setSsdwId(String ssdwId) {
		this.ssdwId = ssdwId;
	}

	@Column(name = "CPHM", length = 50)
	public String getCphm() {
		return this.cphm;
	}

	public void setCphm(String cphm) {
		this.cphm = cphm;
	}

	@Column(name = "PPXH", length = 50)
	public String getPpxh() {
		return this.ppxh;
	}

	public void setPpxh(String ppxh) {
		this.ppxh = ppxh;
	}

	@Column(name = "CYSY", length = 50)
	public String getCysy() {
		return this.cysy;
	}

	public void setCysy(String cysy) {
		this.cysy = cysy;
	}

	@Column(name = "DJH", length = 50)
	public String getDjh() {
		return this.djh;
	}

	public void setDjh(String djh) {
		this.djh = djh;
	}

	@Column(name = "JTZZ", length = 50)
	public String getJtzz() {
		return this.jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	@Column(name = "JSRXM1", length = 50)
	public String getJsrxm1() {
		return this.jsrxm1;
	}

	public void setJsrxm1(String jsrxm1) {
		this.jsrxm1 = jsrxm1;
	}

	@Column(name = "XB1", length = 50)
	public String getXb1() {
		return this.xb1;
	}

	public void setXb1(String xb1) {
		this.xb1 = xb1;
	}

	@Column(name = "SFZMHM1", length = 50)
	public String getSfzmhm1() {
		return this.sfzmhm1;
	}

	public void setSfzmhm1(String sfzmhm1) {
		this.sfzmhm1 = sfzmhm1;
	}

	@Column(name = "LXDH1", length = 50)
	public String getLxdh1() {
		return this.lxdh1;
	}

	public void setLxdh1(String lxdh1) {
		this.lxdh1 = lxdh1;
	}

	@Column(name = "JSRXM2", length = 50)
	public String getJsrxm2() {
		return this.jsrxm2;
	}

	public void setJsrxm2(String jsrxm2) {
		this.jsrxm2 = jsrxm2;
	}

	@Column(name = "XB2", length = 50)
	public String getXb2() {
		return this.xb2;
	}

	public void setXb2(String xb2) {
		this.xb2 = xb2;
	}

	@Column(name = "SFZMHM2", length = 50)
	public String getSfzmhm2() {
		return this.sfzmhm2;
	}

	public void setSfzmhm2(String sfzmhm2) {
		this.sfzmhm2 = sfzmhm2;
	}

	@Column(name = "LXDH2", length = 50)
	public String getLxdh2() {
		return this.lxdh2;
	}

	public void setLxdh2(String lxdh2) {
		this.lxdh2 = lxdh2;
	}

	@Column(name = "JSRXM3", length = 50)
	public String getJsrxm3() {
		return this.jsrxm3;
	}

	public void setJsrxm3(String jsrxm3) {
		this.jsrxm3 = jsrxm3;
	}

	@Column(name = "XB3", length = 50)
	public String getXb3() {
		return this.xb3;
	}

	public void setXb3(String xb3) {
		this.xb3 = xb3;
	}

	@Column(name = "SFZMHM3", length = 50)
	public String getSfzmhm3() {
		return this.sfzmhm3;
	}

	public void setSfzmhm3(String sfzmhm3) {
		this.sfzmhm3 = sfzmhm3;
	}

	@Column(name = "LXDH3", length = 50)
	public String getLxdh3() {
		return this.lxdh3;
	}

	public void setLxdh3(String lxdh3) {
		this.lxdh3 = lxdh3;
	}

	@Column(name = "XSQY", length = 50)
	public String getXsqy() {
		return this.xsqy;
	}

	public void setXsqy(String xsqy) {
		this.xsqy = xsqy;
	}

	@Column(name = "BZ", length = 500)
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "SQR", length = 50)
	public String getSqr() {
		return this.sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	@Column(name = "SQRQ", length = 7)
	public Date getSqrq() {
		return this.sqrq;
	}

	public void setSqrq(Date sqrq) {
		this.sqrq = sqrq;
	}

	@Column(name = "SLZL", length = 100)
	public String getSlzl() {
		return this.slzl;
	}

	public void setSlzl(String slzl) {
		this.slzl = slzl;
	}

	@Column(name = "SLYJ", length = 10)
	public String getSlyj() {
		return this.slyj;
	}

	public void setSlyj(String slyj) {
		this.slyj = slyj;
	}

	@Column(name = "SLBZ", length = 100)
	public String getSlbz() {
		return this.slbz;
	}

	public void setSlbz(String slbz) {
		this.slbz = slbz;
	}

	@Column(name = "SLR", length = 20)
	public String getSlr() {
		return this.slr;
	}

	public void setSlr(String slr) {
		this.slr = slr;
	}

	@Column(name = "SLRQ", length = 7)
	public Date getSlrq() {
		return this.slrq;
	}

	public void setSlrq(Date slrq) {
		this.slrq = slrq;
	}

	@Column(name = "SLBM", length = 20)
	public String getSlbm() {
		return this.slbm;
	}

	public void setSlbm(String slbm) {
		this.slbm = slbm;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRAN_DATE", length = 7)
	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Column(name = "SQIP", length = 50)
	public String getSqip() {
		return this.sqip;
	}

	public void setSqip(String sqip) {
		this.sqip = sqip;
	}

	@Transient
	public String getSsdwName() {
		return ssdwName;
	}

	/**
	 * @param ssdwName
	 *            : set the property ssdwName.
	 */
	public void setSsdwName(String ssdwName) {
		this.ssdwName = ssdwName;
	}

	@Transient
	public String getCysyName() {
		return cysyName;
	}

	/**
	 * @param cysyName
	 *            : set the property cysyName.
	 */
	public void setCysyName(String cysyName) {
		this.cysyName = cysyName;
	}

	@Transient
	public String getXsqyName() {
		return xsqyName;
	}

	/**
	 * @param xsqyName
	 *            : set the property xsqyName.
	 */
	public void setXsqyName(String xsqyName) {
		this.xsqyName = xsqyName;
	}

	@Column(name = "VC_EBIKE_IMG", length = 100)
	public String getVcEbikeImg() {
		return vcEbikeImg;
	}

	/**
	 * @param vcEbikeImg
	 *            : set the property vcEbikeImg.
	 */
	public void setVcEbikeImg(String vcEbikeImg) {
		this.vcEbikeImg = vcEbikeImg;
	}

	@Column(name = "VC_USER1IMG", length = 100)
	public String getVcUser1Img() {
		return vcUser1Img;
	}

	/**
	 * @param vcUser1Img
	 *            : set the property vcUser1Img.
	 */
	public void setVcUser1Img(String vcUser1Img) {
		this.vcUser1Img = vcUser1Img;
	}

	@Column(name = "VC_USER2IMG", length = 100)
	public String getVcUser2Img() {
		return vcUser2Img;
	}

	/**
	 * @param vcUser2Img
	 *            : set the property vcUser2Img.
	 */
	public void setVcUser2Img(String vcUser2Img) {
		this.vcUser2Img = vcUser2Img;
	}

	@Transient
	public String getVcShowEbikeImg() {
		return vcShowEbikeImg;
	}

	/**
	 * @param vcShowEbikeImg
	 *            : set the property vcShowEbikeImg.
	 */
	public void setVcShowEbikeImg(String vcShowEbikeImg) {
		this.vcShowEbikeImg = vcShowEbikeImg;
	}

	@Transient
	public String getVcShowUser1Img() {
		return vcShowUser1Img;
	}

	/**
	 * @param vcShowUser1Img
	 *            : set the property vcShowUser1Img.
	 */
	public void setVcShowUser1Img(String vcShowUser1Img) {
		this.vcShowUser1Img = vcShowUser1Img;
	}

	@Transient
	public String getVcShowUser2Img() {
		return vcShowUser2Img;
	}

	/**
	 * @param vcShowUser2Img
	 *            : set the property vcShowUser2Img.
	 */
	public void setVcShowUser2Img(String vcShowUser2Img) {
		this.vcShowUser2Img = vcShowUser2Img;
	}

	@Transient
	public boolean isApprove() {
		return isApprove;
	}

	/**
	 * @param isApprove
	 *            : set the property isApprove.
	 */
	public void setApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}

	@Column(name = "SL_INDEX")
	public Integer getSlIndex() {
		return slIndex;
	}

	/**
	 * @param slIndex
	 *            : set the property slIndex.
	 */
	public void setSlIndex(Integer slIndex) {
		this.slIndex = slIndex;
	}

	@Transient
	public String getHyxhzhName() {
		return hyxhzhName;
	}

	/**
	 * @param hyxhzhName
	 *            : set the property hyxhzhName.
	 */
	public void setHyxhzhName(String hyxhzhName) {
		this.hyxhzhName = hyxhzhName;
	}

	@Column(name = "TBYY", length = 100)
	public String getTbyy() {
		return tbyy;
	}

	/**
	 * @param tbyy
	 *            : set the property tbyy.
	 */
	public void setTbyy(String tbyy) {
		this.tbyy = tbyy;
	}

}