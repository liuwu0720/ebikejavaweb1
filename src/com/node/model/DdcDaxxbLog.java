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
 * DdcDaxxbLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DDC_DAXXB_LOG")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DdcDaxxbLog implements java.io.Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 5117158049244294570L;
	// Fields

	private Long id;
	private String dabh;
	private String ywlx;
	private String ywyy;
	private String hyxhzh;
	private String ssdwId;// 单位ID
	private String ssdwName;
	private String cphm;
	private String ppxh;
	private String cysy;
	private String cysyName;
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
	private String xsqy;
	private String xsqyName;
	private String bz;
	private String zt;
	private Date syrq;
	private String slzl;
	private String slyj;
	private String slbz;
	private String slr;
	private Date slrq;
	private String slbm;
	private String gdyj;
	private String tbyy;
	private String gdbz;
	private String gdr;
	private Date gdrq;
	private String gdbm;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	private String czr;
	private String czbm;
	private Date czrq;
	private String cznr;
	private String vcEbikeImg;// 车身图片
	private String vcUser1Img;// 驾驶人1图片
	private String vcUser2Img;// 驾驶人2图片
	private String vcShowEbikeImg;
	private String vcShowUser1Img;
	private String vcShowUser2Img;
	private String vcUser1CardImg1;// 驾驶人1身份证照片正面
	private String vcUser1CardImg2;// 驾驶人1身份证照片反面
	private String vcUser2CardImg1;// 驾驶人2身份证照片正面
	private String vcUser2CardImg2;// 驾驶人2身份证照片反面
	private String vcEbikeInvoiceImg;// 购车发票
	private String vcUser1CardImg1Show;
	private String vcUser1CardImg2Show;// 驾驶人1身份证照片反面
	private String vcUser2CardImg1Show;// 驾驶人2身份证照片正面
	private String vcUser2CardImg2Show;// 驾驶人2身份证照片反面
	private String vcEbikeInvoiceImgShow;

	// Constructors

	/** default constructor */
	public DdcDaxxbLog() {
	}

	/** minimal constructor */
	public DdcDaxxbLog(String dabh) {
		this.dabh = dabh;
	}

	// Property accessors
	@SequenceGenerator(name = "DDC_DAXXBLOG", sequenceName = "SEQ_DDC_DAXXB_LOG", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_DAXXBLOG")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DABH", nullable = false, length = 20)
	public String getDabh() {
		return this.dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	@Column(name = "YWLX", length = 20)
	public String getYwlx() {
		return this.ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	@Column(name = "YWYY", length = 20)
	public String getYwyy() {
		return this.ywyy;
	}

	public void setYwyy(String ywyy) {
		this.ywyy = ywyy;
	}

	@Column(name = "HYXHZH", length = 20)
	public String getHyxhzh() {
		return this.hyxhzh;
	}

	public void setHyxhzh(String hyxhzh) {
		this.hyxhzh = hyxhzh;
	}

	@Column(name = "SSDWID", length = 500)
	public String getSsdwId() {
		return ssdwId;
	}

	public void setSsdwId(String ssdwId) {
		this.ssdwId = ssdwId;
	}

	@Column(name = "CPHM", length = 20)
	public String getCphm() {
		return this.cphm;
	}

	public void setCphm(String cphm) {
		this.cphm = cphm;
	}

	@Column(name = "PPXH", length = 20)
	public String getPpxh() {
		return this.ppxh;
	}

	public void setPpxh(String ppxh) {
		this.ppxh = ppxh;
	}

	@Column(name = "CYSY", length = 20)
	public String getCysy() {
		return this.cysy;
	}

	public void setCysy(String cysy) {
		this.cysy = cysy;
	}

	@Column(name = "DJH", length = 30)
	public String getDjh() {
		return this.djh;
	}

	public void setDjh(String djh) {
		this.djh = djh;
	}

	@Column(name = "JTZZ", length = 20)
	public String getJtzz() {
		return this.jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	@Column(name = "JSRXM1", length = 20)
	public String getJsrxm1() {
		return this.jsrxm1;
	}

	public void setJsrxm1(String jsrxm1) {
		this.jsrxm1 = jsrxm1;
	}

	@Column(name = "XB1", length = 20)
	public String getXb1() {
		return this.xb1;
	}

	public void setXb1(String xb1) {
		this.xb1 = xb1;
	}

	@Column(name = "SFZMHM1", length = 20)
	public String getSfzmhm1() {
		return this.sfzmhm1;
	}

	public void setSfzmhm1(String sfzmhm1) {
		this.sfzmhm1 = sfzmhm1;
	}

	@Column(name = "LXDH1", length = 20)
	public String getLxdh1() {
		return this.lxdh1;
	}

	public void setLxdh1(String lxdh1) {
		this.lxdh1 = lxdh1;
	}

	@Column(name = "JSRXM2", length = 20)
	public String getJsrxm2() {
		return this.jsrxm2;
	}

	public void setJsrxm2(String jsrxm2) {
		this.jsrxm2 = jsrxm2;
	}

	@Column(name = "XB2", length = 20)
	public String getXb2() {
		return this.xb2;
	}

	public void setXb2(String xb2) {
		this.xb2 = xb2;
	}

	@Column(name = "SFZMHM2", length = 20)
	public String getSfzmhm2() {
		return this.sfzmhm2;
	}

	public void setSfzmhm2(String sfzmhm2) {
		this.sfzmhm2 = sfzmhm2;
	}

	@Column(name = "LXDH2", length = 20)
	public String getLxdh2() {
		return this.lxdh2;
	}

	public void setLxdh2(String lxdh2) {
		this.lxdh2 = lxdh2;
	}

	@Column(name = "JSRXM3", length = 20)
	public String getJsrxm3() {
		return this.jsrxm3;
	}

	public void setJsrxm3(String jsrxm3) {
		this.jsrxm3 = jsrxm3;
	}

	@Column(name = "XB3", length = 20)
	public String getXb3() {
		return this.xb3;
	}

	public void setXb3(String xb3) {
		this.xb3 = xb3;
	}

	@Column(name = "SFZMHM3", length = 20)
	public String getSfzmhm3() {
		return this.sfzmhm3;
	}

	public void setSfzmhm3(String sfzmhm3) {
		this.sfzmhm3 = sfzmhm3;
	}

	@Column(name = "LXDH3", length = 20)
	public String getLxdh3() {
		return this.lxdh3;
	}

	public void setLxdh3(String lxdh3) {
		this.lxdh3 = lxdh3;
	}

	@Column(name = "XSQY", length = 20)
	public String getXsqy() {
		return this.xsqy;
	}

	public void setXsqy(String xsqy) {
		this.xsqy = xsqy;
	}

	@Column(name = "BZ", length = 200)
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "ZT", length = 10)
	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	@Column(name = "SYRQ", length = 7)
	public Date getSyrq() {
		return this.syrq;
	}

	public void setSyrq(Date syrq) {
		this.syrq = syrq;
	}

	@Column(name = "SLZL", length = 100)
	public String getSlzl() {
		return this.slzl;
	}

	public void setSlzl(String slzl) {
		this.slzl = slzl;
	}

	@Column(name = "SLYJ", length = 20)
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

	@Column(name = "GDYJ", length = 20)
	public String getGdyj() {
		return this.gdyj;
	}

	public void setGdyj(String gdyj) {
		this.gdyj = gdyj;
	}

	@Column(name = "TBYY", length = 10)
	public String getTbyy() {
		return this.tbyy;
	}

	public void setTbyy(String tbyy) {
		this.tbyy = tbyy;
	}

	@Column(name = "GDBZ", length = 100)
	public String getGdbz() {
		return this.gdbz;
	}

	public void setGdbz(String gdbz) {
		this.gdbz = gdbz;
	}

	@Column(name = "GDR", length = 20)
	public String getGdr() {
		return this.gdr;
	}

	public void setGdr(String gdr) {
		this.gdr = gdr;
	}

	@Column(name = "GDRQ", length = 7)
	public Date getGdrq() {
		return this.gdrq;
	}

	public void setGdrq(Date gdrq) {
		this.gdrq = gdrq;
	}

	@Column(name = "GDBM", length = 20)
	public String getGdbm() {
		return this.gdbm;
	}

	public void setGdbm(String gdbm) {
		this.gdbm = gdbm;
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

	@Column(name = "CZR", length = 20)
	public String getCzr() {
		return this.czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	@Column(name = "CZBM", length = 20)
	public String getCzbm() {
		return this.czbm;
	}

	public void setCzbm(String czbm) {
		this.czbm = czbm;
	}

	@Column(name = "CZRQ", length = 7)
	public Date getCzrq() {
		return this.czrq;
	}

	public void setCzrq(Date czrq) {
		this.czrq = czrq;
	}

	@Column(name = "CZNR", length = 20)
	public String getCznr() {
		return this.cznr;
	}

	public void setCznr(String cznr) {
		this.cznr = cznr;
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

	@Column(name = "VC_EBIKE_INVOICE", length = 100)
	public String getVcEbikeInvoiceImg() {
		return vcEbikeInvoiceImg;
	}

	/**
	 * @param vcEbikeInvoiceImg
	 *            : set the property vcEbikeInvoiceImg.
	 */
	public void setVcEbikeInvoiceImg(String vcEbikeInvoiceImg) {
		this.vcEbikeInvoiceImg = vcEbikeInvoiceImg;
	}

	@Transient
	public String getVcEbikeInvoiceImgShow() {
		return vcEbikeInvoiceImgShow;
	}

	/**
	 * @param vcEbikeInvoiceImgShow
	 *            : set the property vcEbikeInvoiceImgShow.
	 */
	public void setVcEbikeInvoiceImgShow(String vcEbikeInvoiceImgShow) {
		this.vcEbikeInvoiceImgShow = vcEbikeInvoiceImgShow;
	}

	@Column(name = "VC_USER1_CARDIMG1", length = 100)
	public String getVcUser1CardImg1() {
		return vcUser1CardImg1;
	}

	/**
	 * @param vcUser1CardImg1
	 *            : set the property vcUser1CardImg1.
	 */
	public void setVcUser1CardImg1(String vcUser1CardImg1) {
		this.vcUser1CardImg1 = vcUser1CardImg1;
	}

	@Column(name = "VC_USER1_CARDIMG2", length = 100)
	public String getVcUser1CardImg2() {
		return vcUser1CardImg2;
	}

	/**
	 * @param vcUser1CardImg2
	 *            : set the property vcUser1CardImg2.
	 */
	public void setVcUser1CardImg2(String vcUser1CardImg2) {
		this.vcUser1CardImg2 = vcUser1CardImg2;
	}

	@Column(name = "VC_USER2_CARDIMG1", length = 100)
	public String getVcUser2CardImg1() {
		return vcUser2CardImg1;
	}

	/**
	 * @param vcUser2CardImg1
	 *            : set the property vcUser2CardImg1.
	 */
	public void setVcUser2CardImg1(String vcUser2CardImg1) {
		this.vcUser2CardImg1 = vcUser2CardImg1;
	}

	@Column(name = "VC_USER2_CARDIMG2", length = 100)
	public String getVcUser2CardImg2() {
		return vcUser2CardImg2;
	}

	/**
	 * @param vcUser2CardImg2
	 *            : set the property vcUser2CardImg2.
	 */
	public void setVcUser2CardImg2(String vcUser2CardImg2) {
		this.vcUser2CardImg2 = vcUser2CardImg2;
	}

	@Transient
	public String getVcUser1CardImg1Show() {
		return vcUser1CardImg1Show;
	}

	/**
	 * @param vcUser1CardImg1Show
	 *            : set the property vcUser1CardImg1Show.
	 */
	public void setVcUser1CardImg1Show(String vcUser1CardImg1Show) {
		this.vcUser1CardImg1Show = vcUser1CardImg1Show;
	}

	@Transient
	public String getVcUser1CardImg2Show() {
		return vcUser1CardImg2Show;
	}

	/**
	 * @param vcUser1CardImg2Show
	 *            : set the property vcUser1CardImg2Show.
	 */
	public void setVcUser1CardImg2Show(String vcUser1CardImg2Show) {
		this.vcUser1CardImg2Show = vcUser1CardImg2Show;
	}

	@Transient
	public String getVcUser2CardImg1Show() {
		return vcUser2CardImg1Show;
	}

	/**
	 * @param vcUser2CardImg1Show
	 *            : set the property vcUser2CardImg1Show.
	 */
	public void setVcUser2CardImg1Show(String vcUser2CardImg1Show) {
		this.vcUser2CardImg1Show = vcUser2CardImg1Show;
	}

	@Transient
	public String getVcUser2CardImg2Show() {
		return vcUser2CardImg2Show;
	}

	/**
	 * @param vcUser2CardImg2Show
	 *            : set the property vcUser2CardImg2Show.
	 */
	public void setVcUser2CardImg2Show(String vcUser2CardImg2Show) {
		this.vcUser2CardImg2Show = vcUser2CardImg2Show;
	}
}