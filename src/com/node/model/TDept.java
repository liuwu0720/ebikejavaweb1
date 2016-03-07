/**
 * 文件名：TDept.java
 * 版本信息：Version 1.0
 * 日期：2016年3月7日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.ArrayList;
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
 * 类描述：部门表
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月7日 下午3:54:29
 */
@Entity
@Table(name = "T_DEPT")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TDept implements java.io.Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 8106544361322460139L;
	private Integer id;
	private String vcDept;
	private Integer iPid;
	private String vcPdept;
	private Date dtAdd;
	private String vcAdd;
	private Integer nEnable;
	private List<TDept> subDepts = new ArrayList<TDept>();

	@SequenceGenerator(name = "T_DEPTID", sequenceName = "S_T_DEPT", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "T_DEPTID")
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

	@Column(name = "I_PID", precision = 22, scale = 0)
	public Integer getiPid() {
		return iPid;
	}

	/**
	 * @param iPid
	 *            : set the property iPid.
	 */
	public void setiPid(Integer iPid) {
		this.iPid = iPid;
	}

	@Column(name = "VC_PDEPT", length = 100)
	public String getVcPdept() {
		return vcPdept;
	}

	/**
	 * @param vcPdept
	 *            : set the property vcPdept.
	 */
	public void setVcPdept(String vcPdept) {
		this.vcPdept = vcPdept;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ADD", length = 7)
	public Date getDtAdd() {
		return dtAdd;
	}

	/**
	 * @param dtAdd
	 *            : set the property dtAdd.
	 */
	public void setDtAdd(Date dtAdd) {
		this.dtAdd = dtAdd;
	}

	@Column(name = "VC_ADD", length = 100)
	public String getVcAdd() {
		return vcAdd;
	}

	/**
	 * @param vcAdd
	 *            : set the property vcAdd.
	 */
	public void setVcAdd(String vcAdd) {
		this.vcAdd = vcAdd;
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

	@Transient
	public List<TDept> getSubDepts() {
		return subDepts;
	}

	/**
	 * @param subDepts
	 *            : set the property subDepts.
	 */
	public void setSubDepts(List<TDept> subDepts) {
		this.subDepts = subDepts;
	}

}
