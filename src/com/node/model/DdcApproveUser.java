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
 * 类描述：审批人及意见表
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月28日 下午4:34:08
 */
@Entity
@Table(name = "DDC_APPROVE_USER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DdcApproveUser implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = -878478886411664702L;
	private Long id;
	private String userName;
	private String userOrgname;
	private String userRoleName;
	private Integer approveIndex;
	private String approveNote;
	private Integer approveState;// 审批意见 0-同意 1-拒绝
	private String approveTable;
	private Long approveTableid;
	private Date approveTime;
	private String lsh;
	private String sysFlag;
	private Date tranDate;
	private String approveNo;// 审批编号

	// Constructors

	/** default constructor */
	public DdcApproveUser() {
	}

	/** full constructor */
	public DdcApproveUser(String userName, String userOrgname,
			String userRoleName, Integer approveIndex, String approveNote,
			String approveTable, Long approveTableid) {
		this.userName = userName;
		this.userOrgname = userOrgname;
		this.userRoleName = userRoleName;
		this.approveIndex = approveIndex;
		this.approveNote = approveNote;
		this.approveTable = approveTable;
		this.approveTableid = approveTableid;
	}

	@SequenceGenerator(name = "DDC_APPROVE_USER", sequenceName = "SEQ_DDC_APPROVE_USER", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "DDC_APPROVE_USER")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_NAME", length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_ORGNAME", length = 50)
	public String getUserOrgname() {
		return this.userOrgname;
	}

	public void setUserOrgname(String userOrgname) {
		this.userOrgname = userOrgname;
	}

	@Column(name = "USER_ROLE_NAME", length = 200)
	public String getUserRoleName() {
		return this.userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	@Column(name = "APPROVE_INDEX", precision = 0)
	public Integer getApproveIndex() {
		return this.approveIndex;
	}

	public void setApproveIndex(Integer approveIndex) {
		this.approveIndex = approveIndex;
	}

	@Column(name = "APPROVE_NOTE", length = 500)
	public String getApproveNote() {
		return this.approveNote;
	}

	public void setApproveNote(String approveNote) {
		this.approveNote = approveNote;
	}

	@Column(name = "APPROVE_TABLE", length = 100)
	public String getApproveTable() {
		return this.approveTable;
	}

	public void setApproveTable(String approveTable) {
		this.approveTable = approveTable;
	}

	@Column(name = "APPROVE_TABLEID", precision = 0)
	public Long getApproveTableid() {
		return this.approveTableid;
	}

	public void setApproveTableid(Long approveTableid) {
		this.approveTableid = approveTableid;
	}

	@Column(name = "APPROVE_TIME")
	public Date getApproveTime() {
		return approveTime;
	}

	/**
	 * @param approveTime
	 *            : set the property approveTime.
	 */
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@Column(name = "APPROVE_STATE", precision = 0)
	public Integer getApproveState() {
		return approveState;
	}

	/**
	 * @param approveState
	 *            : set the property approveState.
	 */
	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
	}

	@Column(name = "LSH", length = 100)
	public String getLsh() {
		return lsh;
	}

	/**
	 * @param lsh
	 *            : set the property lsh.
	 */
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	@Column(name = "SYN_FLAG", length = 10)
	public String getSysFlag() {
		return sysFlag;
	}

	/**
	 * @param sysFlag
	 *            : set the property sysFlag.
	 */
	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRAN_DATE")
	public Date getTranDate() {
		return tranDate;
	}

	/**
	 * @param tranDate
	 *            : set the property tranDate.
	 */
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Column(name = "APPROVE_NO", unique = true, nullable = false, length = 100)
	public String getApproveNo() {
		return approveNo;
	}

	/**
	 * @param approveNo
	 *            : set the property approveNo.
	 */
	public void setApproveNo(String approveNo) {
		this.approveNo = approveNo;
	}

}