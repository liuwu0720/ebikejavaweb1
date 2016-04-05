package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * DdcFlow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DDC_FLOW")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DdcFlow implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 1944942817973566027L;
	private Long id;
	private String lsh;
	private String ywlx;
	private String ywlxName;
	private String ywyy;
	private String ywyyName;
	private String hyxhzh;
	private String hyxhName;
	private String dabh;
	private String zzjgdmzh;
	private String zzjgdmzhName;
	private String cphm;
	private String ppxh;
	private String cysy;
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
	private String xsqy;
	private String xsqyName;
	private String bz;
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
	private String yclb;// 验车类别
	private String vcEbikeImg;// 车身图片
	private String vcUser1Img;// 驾驶人1图片
	private String vcUser2Img;// 驾驶人2图片
	private String vcShowEbikeImg;
	private String vcShowUser1Img;
	private String vcShowUser2Img;
	private List<String> slzlList;

	// Constructors

	/** default constructor */
	public DdcFlow() {
	}

	/** minimal constructor */
	public DdcFlow(String lsh) {
		this.lsh = lsh;
	}

	/** full constructor */
	public DdcFlow(String lsh, String ywlx, String ywyy, String hyxhzh,
			String dabh, String zzjgdmzh, String cphm, String ppxh,
			String cysy, String djh, String jtzz, String jsrxm1, String xb1,
			String sfzmhm1, String lxdh1, String jsrxm2, String xb2,
			String sfzmhm2, String lxdh2, String jsrxm3, String xb3,
			String sfzmhm3, String lxdh3, String xsqy, String bz, String slzl,
			String slyj, String slbz, String slr, Date slrq, String slbm,
			String gdyj, String tbyy, String gdbz, String gdr, Date gdrq,
			String gdbm, String synFlag, String tranFlag, Date tranDate,
			String yclb) {
		this.lsh = lsh;
		this.ywlx = ywlx;
		this.ywyy = ywyy;
		this.hyxhzh = hyxhzh;
		this.dabh = dabh;
		this.zzjgdmzh = zzjgdmzh;
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
		this.slzl = slzl;
		this.slyj = slyj;
		this.slbz = slbz;
		this.slr = slr;
		this.slrq = slrq;
		this.slbm = slbm;
		this.gdyj = gdyj;
		this.tbyy = tbyy;
		this.gdbz = gdbz;
		this.gdr = gdr;
		this.gdrq = gdrq;
		this.gdbm = gdbm;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.yclb = yclb;
	}

	// Property accessors
	@SequenceGenerator(name = "DDC_FLOW", sequenceName = "SEQ_DDL_FLOW", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_FLOW")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "LSH", nullable = false, length = 20)
	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
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

	@Column(name = "DABH", length = 20)
	public String getDabh() {
		return this.dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	@Column(name = "ZZJGDMZH", length = 200)
	public String getZzjgdmzh() {
		return this.zzjgdmzh;
	}

	public void setZzjgdmzh(String zzjgdmzh) {
		this.zzjgdmzh = zzjgdmzh;
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

	@Temporal(TemporalType.DATE)
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

	@Column(name = "TBYY", length = 100)
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

	@Temporal(TemporalType.DATE)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRAN_DATE", length = 7)
	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Column(name = "YCLB", length = 20)
	public String getYclb() {
		return this.yclb;
	}

	public void setYclb(String yclb) {
		this.yclb = yclb;
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
	public String getZzjgdmzhName() {
		return zzjgdmzhName;
	}

	/**
	 * @param zzjgdmzhName
	 *            : set the property zzjgdmzhName.
	 */
	public void setZzjgdmzhName(String zzjgdmzhName) {
		this.zzjgdmzhName = zzjgdmzhName;
	}

	@Transient
	public String getYwlxName() {
		return ywlxName;
	}

	/**
	 * @param ywlxName
	 *            : set the property ywlxName.
	 */
	public void setYwlxName(String ywlxName) {
		this.ywlxName = ywlxName;
	}

	@Transient
	public String getYwyyName() {
		return ywyyName;
	}

	/**
	 * @param ywyyName
	 *            : set the property ywyyName.
	 */
	public void setYwyyName(String ywyyName) {
		this.ywyyName = ywyyName;
	}

	@Transient
	public List<String> getSlzlList() {
		return slzlList;
	}

	/**
	 * @param slzlList
	 *            : set the property slzlList.
	 */
	public void setSlzlList(List<String> slzlList) {
		this.slzlList = slzlList;
	}

	@Transient
	public String getHyxhName() {
		return hyxhName;
	}

	/**
	 * @param hyxhName
	 *            : set the property hyxhName.
	 */
	public void setHyxhName(String hyxhName) {
		this.hyxhName = hyxhName;
	}

}