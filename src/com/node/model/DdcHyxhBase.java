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

/**
 * 
 * 类描述：电动车行业协会基本信息表：由内网同步过来
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月12日 上午10:25:34
 */
@Entity
@Table(name = "DDC_HYXH_BASE")
public class DdcHyxhBase implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = -7332171538374420398L;
	private Long id;
	private String hyxhzh;
	private String hyxhmm;
	private String hyxhmc;//
	private String hyxhdz;//
	private String hyxhfzr;
	private String hyxhfzrdh;
	private Integer hyxhsjzpe;// 行业协会实际剩余配额
	private String bz;
	private String cjr;
	private Date cjrq;
	private String cjbm;
	private String hyxhlb;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	private Integer totalPe;// 总配额（在DDC_HYXH_BASB 支队领导批完并办结去加这个数

	// Constructors

	/** default constructor */
	public DdcHyxhBase() {
	}

	/** minimal constructor */
	public DdcHyxhBase(String hyxhzh) {
		this.hyxhzh = hyxhzh;
	}

	/** full constructor */
	public DdcHyxhBase(String hyxhzh, String hyxhmm, String hyxhmc,
			String hyxhdz, String hyxhfzr, String hyxhfzrdh, Integer hyxhsjzpe,
			String bz, String cjr, Date cjrq, String cjbm, String hyxhlb,
			String synFlag, String tranFlag, Date tranDate) {
		this.hyxhzh = hyxhzh;
		this.hyxhmm = hyxhmm;
		this.hyxhmc = hyxhmc;
		this.hyxhdz = hyxhdz;
		this.hyxhfzr = hyxhfzr;
		this.hyxhfzrdh = hyxhfzrdh;
		this.hyxhsjzpe = hyxhsjzpe;
		this.bz = bz;
		this.cjr = cjr;
		this.cjrq = cjrq;
		this.cjbm = cjbm;
		this.hyxhlb = hyxhlb;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
	}

	@SequenceGenerator(name = "DDC_HYXH_BASE", sequenceName = "SEQ_DDC_HYXH_BASE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_HYXH_BASE")
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

	@Column(name = "HYXHMM", length = 20)
	public String getHyxhmm() {
		return this.hyxhmm;
	}

	public void setHyxhmm(String hyxhmm) {
		this.hyxhmm = hyxhmm;
	}

	@Column(name = "HYXHMC", length = 100)
	public String getHyxhmc() {
		return this.hyxhmc;
	}

	public void setHyxhmc(String hyxhmc) {
		this.hyxhmc = hyxhmc;
	}

	@Column(name = "HYXHDZ", length = 200)
	public String getHyxhdz() {
		return this.hyxhdz;
	}

	public void setHyxhdz(String hyxhdz) {
		this.hyxhdz = hyxhdz;
	}

	@Column(name = "HYXHFZR", length = 50)
	public String getHyxhfzr() {
		return this.hyxhfzr;
	}

	public void setHyxhfzr(String hyxhfzr) {
		this.hyxhfzr = hyxhfzr;
	}

	@Column(name = "HYXHFZRDH", length = 50)
	public String getHyxhfzrdh() {
		return this.hyxhfzrdh;
	}

	public void setHyxhfzrdh(String hyxhfzrdh) {
		this.hyxhfzrdh = hyxhfzrdh;
	}

	@Column(name = "HYXHSJZPE", precision = 0)
	public Integer getHyxhsjzpe() {
		return this.hyxhsjzpe;
	}

	public void setHyxhsjzpe(Integer hyxhsjzpe) {
		this.hyxhsjzpe = hyxhsjzpe;
	}

	@Column(name = "BZ", length = 200)
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

	@Column(name = "HYXHLB", length = 10)
	public String getHyxhlb() {
		return this.hyxhlb;
	}

	public void setHyxhlb(String hyxhlb) {
		this.hyxhlb = hyxhlb;
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

	@Column(name = "TOTALPE", precision = 0)
	public Integer getTotalPe() {
		return totalPe;
	}

	/**
	 * @param totalPe
	 *            : set the property totalPe.
	 */
	public void setTotalPe(Integer totalPe) {
		this.totalPe = totalPe;
	}

}