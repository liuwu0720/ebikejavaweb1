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
 * 
 * 类描述：电动车档案信息表
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月11日 下午5:47:46
 */
@Entity
@Table(name = "DDC_DAXXB", uniqueConstraints = @UniqueConstraint(columnNames = "DABH"))
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DdcDaxxb implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 243629761815051390L;
	private Long id;
	private String dabh;// 新进入的数据，自动生成档案编号4403 00000001；老数据去更新
	private String ywlx;// 业务类型
	private String ywlxName;
	private String ywyy;// 业务原因
	private String ywyyName;
	private String hyxhzh;//
	private String hyxhzhName;
	private String ssdwId;// 单位ID
	private String cphm;
	private String ppxh;
	private String ssdwName;
	private String cysy;// 车身验色
	private String cysyName;
	private String djh;// 电机号
	private String jtzz;// 脚踏装置（有、无）
	private String jsrxm1;// 驾驶人姓名1
	private String xb1;// 性别1
	private String sfzmhm1;// 身份证号码1
	private String lxdh1;// 联系电话1
	private String jsrxm2;// 驾驶人姓名2
	private String xb2;// 性别2
	private String sfzmhm2;// 身份证号码2
	private String lxdh2;// 联系电话2
	private String xsqy;// 行驶区域(福田区、罗湖区.....)
	private String xsqyName;
	private String bz;// 备注
	private String zt;// 车辆状态 填字典
	private String ztName;
	private Date syrq;// 审验日期：新车归档日期+2年或年审验车合格后的归档日期+2年
	private String slzl;// 受理资料，分割 字典表
	private String slyj;// 受理意见 0 同意 1 不同意 特别注意 档案更正是要填写本表记录
	private String slbz;// 受理备注
	private String slr;// 受理人
	private String slrName;
	private Date slrq;// 受理日期
	private String slbm;// 受理部门
	private String slbmName;
	private String gdyj;// 归档意见 0 办结 1 退办 成功办结的信息才会进入本表
	private String tbyy;// 退办原因
	private String gdbz;// 归档备注
	private String gdr;// 归档人
	private Date gdrq;// 归档日期
	private String gdbm;// 归档部门
	private String synFlag;// 同步标志 ADD新增 UP更改
	private String tranFlag;// 传送标志(该字段必须有索引)
	private Date tranDate;// 传送时间
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

	// 201606增加
	private String vcUser1WorkImg;// 驾驶人1 在职证明或居住证
	private String vcUser2WorkImg;// 驾驶人2 在职证明或居住证
	private String vcQualifiedImg;// 车辆合格证
	private String vcEbikeInsuranceImg;// 投保凭证
	private String vcUser1WorkImgShow;// 驾驶人1 在职证明或居住证
	private String vcUser2WorkImgShow;// 驾驶人2 在职证明或居住证
	private String vcQualifiedImgShow;// 车辆合格证
	private String vcEbikeInsuranceImgShow;// 投保凭证

	@Transient
	public String getVcUser1WorkImgShow() {
		return vcUser1WorkImgShow;
	}

	/**
	 * @param vcUser1WorkImgShow
	 *            : set the property vcUser1WorkImgShow.
	 */
	public void setVcUser1WorkImgShow(String vcUser1WorkImgShow) {
		this.vcUser1WorkImgShow = vcUser1WorkImgShow;
	}

	@Transient
	public String getVcUser2WorkImgShow() {
		return vcUser2WorkImgShow;
	}

	/**
	 * @param vcUser2WorkImgShow
	 *            : set the property vcUser2WorkImgShow.
	 */
	public void setVcUser2WorkImgShow(String vcUser2WorkImgShow) {
		this.vcUser2WorkImgShow = vcUser2WorkImgShow;
	}

	@Transient
	public String getVcQualifiedImgShow() {
		return vcQualifiedImgShow;
	}

	/**
	 * @param vcQualifiedImgShow
	 *            : set the property vcQualifiedImgShow.
	 */
	public void setVcQualifiedImgShow(String vcQualifiedImgShow) {
		this.vcQualifiedImgShow = vcQualifiedImgShow;
	}

	@Transient
	public String getVcEbikeInsuranceImgShow() {
		return vcEbikeInsuranceImgShow;
	}

	/**
	 * @param vcEbikeInsuranceImgShow
	 *            : set the property vcEbikeInsuranceImgShow.
	 */
	public void setVcEbikeInsuranceImgShow(String vcEbikeInsuranceImgShow) {
		this.vcEbikeInsuranceImgShow = vcEbikeInsuranceImgShow;
	}

	@Column(name = "VC_USER1WORKIMG", length = 100)
	public String getVcUser1WorkImg() {
		return vcUser1WorkImg;
	}

	/**
	 * @param vcUser1WorkImg
	 *            : set the property vcUser1WorkImg.
	 */
	public void setVcUser1WorkImg(String vcUser1WorkImg) {
		this.vcUser1WorkImg = vcUser1WorkImg;
	}

	@Column(name = "VC_USER2WORKIMG", length = 100)
	public String getVcUser2WorkImg() {
		return vcUser2WorkImg;
	}

	/**
	 * @param vcUser2WorkImg
	 *            : set the property vcUser2WorkImg.
	 */
	public void setVcUser2WorkImg(String vcUser2WorkImg) {
		this.vcUser2WorkImg = vcUser2WorkImg;
	}

	@Column(name = "VC_EBIKE_QUALIFIEDIMG", length = 100)
	public String getVcQualifiedImg() {
		return vcQualifiedImg;
	}

	/**
	 * @param vcQualifiedImg
	 *            : set the property vcQualifiedImg.
	 */
	public void setVcQualifiedImg(String vcQualifiedImg) {
		this.vcQualifiedImg = vcQualifiedImg;
	}

	@Column(name = "VC_EBIKE_INSURANCEIMG", length = 100)
	public String getVcEbikeInsuranceImg() {
		return vcEbikeInsuranceImg;
	}

	/**
	 * @param vcEbikeInsuranceImg
	 *            : set the property vcEbikeInsuranceImg.
	 */
	public void setVcEbikeInsuranceImg(String vcEbikeInsuranceImg) {
		this.vcEbikeInsuranceImg = vcEbikeInsuranceImg;
	}

	/** default constructor */
	public DdcDaxxb() {
	}

	/** minimal constructor */
	public DdcDaxxb(String dabh) {
		this.dabh = dabh;
	}

	// Property accessors
	@SequenceGenerator(name = "DDC_DAXXBID", sequenceName = "SEQ_DDC_DAXXB", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_DAXXBID")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DABH", unique = true, nullable = false, length = 20)
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

	/**
	 * @param ssdwId
	 *            : set the property ssdwId.
	 */
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

	@Temporal(TemporalType.TIMESTAMP)
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
	public String getZtName() {
		return ztName;
	}

	/**
	 * @param ztName
	 *            : set the property ztName.
	 */
	public void setZtName(String ztName) {
		this.ztName = ztName;
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

	@Transient
	public String getSlrName() {
		return slrName;
	}

	/**
	 * @param slrName
	 *            : set the property slrName.
	 */
	public void setSlrName(String slrName) {
		this.slrName = slrName;
	}

	@Transient
	public String getSlbmName() {
		return slbmName;
	}

	/**
	 * @param slbmName
	 *            : set the property slbmName.
	 */
	public void setSlbmName(String slbmName) {
		this.slbmName = slbmName;
	}

}