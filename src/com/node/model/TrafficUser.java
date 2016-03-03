/**
 * 文件名：TrafficUser.java
 * 版本信息：Version 1.0
 * 日期：2016年3月3日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
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
 * 类描述：交警支队民警信息表
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月3日 下午3:40:13
 */
@Entity
@Table(name = "T_TRAFFIC_USER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TrafficUser implements java.io.Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = -2269382979351399461L;

	private Integer id;
	private String vcNameString;
	private String vcAccount;
	private String vcDept;
	private Integer iDept;
	private Integer nEnable;
	private String vcRoleList;
	private Date dtAddDate;

	@SequenceGenerator(name = "T_TRAFFIC_USERID", sequenceName = "S_T_TRAFFIC_USER", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "T_TRAFFIC_USERID")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            : set the property id.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "VC_NAME", length = 100)
	public String getVcNameString() {
		return vcNameString;
	}

	/**
	 * @param vcNameString
	 *            : set the property vcNameString.
	 */
	public void setVcNameString(String vcNameString) {
		this.vcNameString = vcNameString;
	}

	@Column(name = "VC_ACCOUNT", length = 100)
	public String getVcAccount() {
		return vcAccount;
	}

	/**
	 * @param vcAccount
	 *            : set the property vcAccount.
	 */
	public void setVcAccount(String vcAccount) {
		this.vcAccount = vcAccount;
	}

	@Column(name = "VC_DEPT", length = 100)
	public String getVcDept() {
		return vcDept;
	}

	/**
	 * @param vcDept
	 *            : set the property vcDept.
	 */
	public void setVcDept(String vcDept) {
		this.vcDept = vcDept;
	}

	@Column(name = "I_DEPT", precision = 22, scale = 0)
	public Integer getiDept() {
		return iDept;
	}

	/**
	 * @param iDept
	 *            : set the property iDept.
	 */
	public void setiDept(Integer iDept) {
		this.iDept = iDept;
	}

	@Column(name = "N_ENABLE", precision = 22, scale = 0)
	public Integer getnEnable() {
		return nEnable;
	}

	/**
	 * @param nEnable
	 *            : set the property nEnable.
	 */
	public void setnEnable(Integer nEnable) {
		this.nEnable = nEnable;
	}

	@Column(name = "VC_ROLES", length = 500)
	public String getVcRoleList() {
		return vcRoleList;
	}

	/**
	 * @param vcRoleList
	 *            : set the property vcRoleList.
	 */
	public void setVcRoleList(String vcRoleList) {
		this.vcRoleList = vcRoleList;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ADDTIME", length = 7)
	public Date getDtAddDate() {
		return dtAddDate;
	}

	/**
	 * @param dtAddDate
	 *            : set the property dtAddDate.
	 */
	public void setDtAddDate(Date dtAddDate) {
		this.dtAddDate = dtAddDate;
	}

}
