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
import javax.persistence.UniqueConstraint;

/**
 * 类描述：行业协会所属单位信息，由外网新增后同步至内网
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月11日 下午7:11:14
 */
@Entity
@Table(name = "DDC_HYXH_SSDW", uniqueConstraints = @UniqueConstraint(columnNames = "DWMC"))
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DdcHyxhSsdw implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 1024295025412125137L;
	private Long id;
	private String hyxhzh;
	private String dwmc;// 单位名称
	private String zzjgdmzh;// 组织机构代码证号
	private String zsdz;// 住所地址（深圳市福田区）
	private String lxr;// 联系人
	private String lxdh;// 联系电话
	private String bz;// 备注信息
	private String sqr;
	private Date sqrq;// 申请时间
	private String zt;// 状态 0 禁用 1 启用
	private String shr;// 审核人
	private Date shrq;
	private String shbm;// 审核部门
	private String synFlag;// 同步标志 刚开始为空 Y1-外网同步，不可修改等待晚上同步到内网 ;Y2-晚上已同步至内网
	private String tranFlag;
	private Date tranDate;
	private Integer dwpe;// 单位配额
	private String vcPicPath;// 图片名称
	private String vcShowPath;// 图片显示路径 不与数据库关联

	// Constructors

	/** default constructor */
	public DdcHyxhSsdw() {
	}

	/** minimal constructor */
	public DdcHyxhSsdw(String hyxhzh) {
		this.hyxhzh = hyxhzh;
	}

	/** full constructor */
	public DdcHyxhSsdw(String hyxhzh, String dwmc, String zzjgdmzh,
			String zsdz, String lxr, String lxdh, String bz, String sqr,
			Date sqrq, String zt, String shr, Date shrq, String shbm,
			String synFlag, String tranFlag, Date tranDate, Integer dwpe) {
		this.hyxhzh = hyxhzh;
		this.dwmc = dwmc;
		this.zzjgdmzh = zzjgdmzh;
		this.zsdz = zsdz;
		this.lxr = lxr;
		this.lxdh = lxdh;
		this.bz = bz;
		this.sqr = sqr;
		this.sqrq = sqrq;
		this.zt = zt;
		this.shr = shr;
		this.shrq = shrq;
		this.shbm = shbm;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.dwpe = dwpe;
	}

	@Id
	@SequenceGenerator(name = "DDC_HYXH_SSDW", sequenceName = "SEQ_HYXH_SSDW_XH", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_HYXH_SSDW")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "HYXHZH", nullable = false, length = 20)
	public String getHyxhzh() {
		return this.hyxhzh;
	}

	public void setHyxhzh(String hyxhzh) {
		this.hyxhzh = hyxhzh;
	}

	@Column(name = "DWMC", unique = true, length = 100)
	public String getDwmc() {
		return this.dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	@Column(name = "ZZJGDMZH", length = 200)
	public String getZzjgdmzh() {
		return this.zzjgdmzh;
	}

	public void setZzjgdmzh(String zzjgdmzh) {
		this.zzjgdmzh = zzjgdmzh;
	}

	@Column(name = "ZSDZ", length = 100)
	public String getZsdz() {
		return this.zsdz;
	}

	public void setZsdz(String zsdz) {
		this.zsdz = zsdz;
	}

	@Column(name = "LXR", length = 50)
	public String getLxr() {
		return this.lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	@Column(name = "LXDH", length = 50)
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

	@Column(name = "ZT", length = 1)
	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	@Column(name = "SHR", length = 20)
	public String getShr() {
		return this.shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	@Column(name = "SHRQ", length = 7)
	public Date getShrq() {
		return this.shrq;
	}

	public void setShrq(Date shrq) {
		this.shrq = shrq;
	}

	@Column(name = "SHBM", length = 20)
	public String getShbm() {
		return this.shbm;
	}

	public void setShbm(String shbm) {
		this.shbm = shbm;
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

	@Column(name = "DWPE", precision = 0)
	public Integer getDwpe() {
		return this.dwpe;
	}

	public void setDwpe(Integer dwpe) {
		this.dwpe = dwpe;
	}

	@Column(name = "VC_PICPATH", length = 500)
	public String getVcPicPath() {
		return vcPicPath;
	}

	/**
	 * @param vcPicPath
	 *            : set the property vcPicPath.
	 */
	public void setVcPicPath(String vcPicPath) {
		this.vcPicPath = vcPicPath;
	}

	@Transient
	public String getVcShowPath() {
		return vcShowPath;
	}

	/**
	 * @param vcShowPath
	 *            : set the property vcShowPath.
	 */
	public void setVcShowPath(String vcShowPath) {
		this.vcShowPath = vcShowPath;
	}

}