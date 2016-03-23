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
 * 
 * 类描述：行业协会信息所属单位操作日志表
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月12日 下午5:42:10
 */
@Entity
@Table(name = "DDC_HYXH_SSDW_LOG", schema = "EBIKEOUT")
public class DdcHyxhSsdwLog implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = -3232524407242885291L;
	private Long id;
	private String hyxhzh;
	private String dwmc;
	private String zzjgdmzh;
	private String zsdz;
	private String lxr;
	private String lxdh;
	private String bz;
	private String sqr;
	private Date sqrq;
	private String zt;
	private String shr;
	private Date shrq;
	private String shbm;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	private String czr;
	private String czbm;
	private Date czrq;
	private String cznr;
	private Double dwpe;

	// Constructors

	/** default constructor */
	public DdcHyxhSsdwLog() {
	}

	/** minimal constructor */
	public DdcHyxhSsdwLog(String hyxhzh) {
		this.hyxhzh = hyxhzh;
	}

	/** full constructor */
	public DdcHyxhSsdwLog(String hyxhzh, String dwmc, String zzjgdmzh,
			String zsdz, String lxr, String lxdh, String bz, String sqr,
			Date sqrq, String zt, String shr, Date shrq, String shbm,
			String synFlag, String tranFlag, Date tranDate, String czr,
			String czbm, Date czrq, String cznr, Double dwpe) {
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
		this.czr = czr;
		this.czbm = czbm;
		this.czrq = czrq;
		this.cznr = cznr;
		this.dwpe = dwpe;
	}

	@Id
	@SequenceGenerator(name = "DDC_HYXH_SSDW_LOG", sequenceName = "SEQ_HYXH_SSDW_LOG_XH", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_HYXH_SSDW_LOG")
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

	@Column(name = "DWMC", length = 100)
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

	@Column(name = "CZR", length = 10)
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

	@Column(name = "DWPE", precision = 0)
	public Double getDwpe() {
		return this.dwpe;
	}

	public void setDwpe(Double dwpe) {
		this.dwpe = dwpe;
	}

}