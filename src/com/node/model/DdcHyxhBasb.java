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
 * 类描述：行业协会配额申报
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月19日 上午10:01:13
 */
@Entity
@Table(name = "DDC_HYXH_BASB", uniqueConstraints = @UniqueConstraint(columnNames = "LSH"))
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DdcHyxhBasb implements java.io.Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 7057451283368786958L;
	// Fields

	private Long id;
	private String lsh;
	private String hyxhzh;
	private String hyxhmc;
	private Integer hyxhsqpe;// 行业协会申请配额
	private String bz;
	private String sqr;
	private Date sqrq;
	private Integer hyxhbcsjpe;// 行业协会本次实际批准配额
	private String bjjg;// 办结结果 0同意 1不同意 为空表示在审批中
	private String bjbz;// 办结备注
	private String bzjr;// 办结人
	private String bzjrName;
	private String bjbm;// 办结部门
	private String bjbmName;
	private Date bjrq;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	private Integer slIndex;// 受理审批顺序
	private boolean isCurrentApprove = false;// 是否是当前用户审批

	// Constructors

	/** default constructor */
	public DdcHyxhBasb() {
	}

	/** minimal constructor */
	public DdcHyxhBasb(String lsh) {
		this.lsh = lsh;
	}

	@SequenceGenerator(name = "DDC_HYXH_BASB", sequenceName = "seq_hyxh_basb_xh", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_HYXH_BASB")
	@Column(name = "ID", unique = true, nullable = false, precision = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "LSH", unique = true, nullable = false, length = 20)
	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	@Column(name = "HYXHZH", length = 20)
	public String getHyxhzh() {
		return this.hyxhzh;
	}

	public void setHyxhzh(String hyxhzh) {
		this.hyxhzh = hyxhzh;
	}

	@Column(name = "HYXHSQPE", precision = 0)
	public Integer getHyxhsqpe() {
		return this.hyxhsqpe;
	}

	public void setHyxhsqpe(Integer hyxhsqpe) {
		this.hyxhsqpe = hyxhsqpe;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "SQRQ", length = 7)
	public Date getSqrq() {
		return this.sqrq;
	}

	public void setSqrq(Date sqrq) {
		this.sqrq = sqrq;
	}

	@Column(name = "HYXHBCSJPE", precision = 0)
	public Integer getHyxhbcsjpe() {
		return this.hyxhbcsjpe;
	}

	public void setHyxhbcsjpe(Integer hyxhbcsjpe) {
		this.hyxhbcsjpe = hyxhbcsjpe;
	}

	@Column(name = "BJJG", length = 20)
	public String getBjjg() {
		return this.bjjg;
	}

	public void setBjjg(String bjjg) {
		this.bjjg = bjjg;
	}

	@Column(name = "BJBZ", length = 100)
	public String getBjbz() {
		return this.bjbz;
	}

	public void setBjbz(String bjbz) {
		this.bjbz = bjbz;
	}

	@Column(name = "BZJR", length = 10)
	public String getBzjr() {
		return this.bzjr;
	}

	public void setBzjr(String bzjr) {
		this.bzjr = bzjr;
	}

	@Column(name = "BJBM", length = 50)
	public String getBjbm() {
		return this.bjbm;
	}

	public void setBjbm(String bjbm) {
		this.bjbm = bjbm;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BJRQ", length = 7)
	public Date getBjrq() {
		return this.bjrq;
	}

	public void setBjrq(Date bjrq) {
		this.bjrq = bjrq;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "TRAN_DATE", length = 7)
	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Column(name = "SL_INDEX", precision = 0)
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

	@Column(name = "HYXHMC", length = 50)
	public String getHyxhmc() {
		return hyxhmc;
	}

	/**
	 * @param hyxhmc
	 *            : set the property hyxhmc.
	 */
	public void setHyxhmc(String hyxhmc) {
		this.hyxhmc = hyxhmc;
	}

	@Transient
	public String getBzjrName() {
		return bzjrName;
	}

	/**
	 * @param bzjrName
	 *            : set the property bzjrName.
	 */
	public void setBzjrName(String bzjrName) {
		this.bzjrName = bzjrName;
	}

	@Transient
	public String getBjbmName() {
		return bjbmName;
	}

	/**
	 * @param bjbmName
	 *            : set the property bjbmName.
	 */
	public void setBjbmName(String bjbmName) {
		this.bjbmName = bjbmName;
	}

	@Transient
	public boolean isCurrentApprove() {
		return isCurrentApprove;
	}

	/**
	 * @param isCurrentApprove
	 *            : set the property isCurrentApprove.
	 */
	public void setCurrentApprove(boolean isCurrentApprove) {
		this.isCurrentApprove = isCurrentApprove;
	}

}