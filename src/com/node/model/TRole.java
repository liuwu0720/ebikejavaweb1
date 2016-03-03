/**
 * 文件名：TRole.java
 * 版本信息：Version 1.0
 * 日期：2016年3月3日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月3日 上午8:50:47
 */
@Entity
@Table(name = "T_ROLE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TRole implements Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = -8165644843257311009L;

	private Integer id;
	private String vcRoleName;// 角色名称（中文名称）
	private Integer nEnable;// 角色状态(0为有效，1为无效)
	private String vcDesc;// 角色描述
	private String vcRole;// 角色英文名称（必填写，且以ROLE_开头）
	private Integer iArchieveType;// 所属档案类型ID（1行业协会 2市交警支队）
	private Integer nLeader;// 是否领导(0:为领导;1:为员工)
	private Integer nIndex;// 领导等级标识（数字越大等级越高，不是领导该字段为空）

	@Id
	@SequenceGenerator(name = "T_ROLEID", sequenceName = "S_T_ROLE", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "T_ROLEID")
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

	@Column(name = "VC_ROLE_NAME", length = 20)
	public String getVcRoleName() {
		return vcRoleName;
	}

	/**
	 * @param vcRoleName
	 *            : set the property vcRoleName.
	 */
	public void setVcRoleName(String vcRoleName) {
		this.vcRoleName = vcRoleName;
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

	@Column(name = "VC_DESC", length = 50)
	public String getVcDesc() {
		return vcDesc;
	}

	/**
	 * @param vcDesc
	 *            : set the property vcDesc.
	 */
	public void setVcDesc(String vcDesc) {
		this.vcDesc = vcDesc;
	}

	@Column(name = "VC_ROLE", length = 50)
	public String getVcRole() {
		return vcRole;
	}

	/**
	 * @param vcRole
	 *            : set the property vcRole.
	 */
	public void setVcRole(String vcRole) {
		this.vcRole = vcRole;
	}

	@Column(name = "I_ARCHIVE_TYPE", precision = 22, scale = 0)
	public Integer getiArchieveType() {
		return iArchieveType;
	}

	/**
	 * @param iArchieveType
	 *            : set the property iArchieveType.
	 */
	public void setiArchieveType(Integer iArchieveType) {
		this.iArchieveType = iArchieveType;
	}

	@Column(name = "N_LEADER", precision = 22, scale = 0)
	public Integer getnLeader() {
		return nLeader;
	}

	/**
	 * @param nLeader
	 *            : set the property nLeader.
	 */
	public void setnLeader(Integer nLeader) {
		this.nLeader = nLeader;
	}

	@Column(name = "N_INDEX", precision = 22, scale = 0)
	public Integer getnIndex() {
		return nIndex;
	}

	/**
	 * @param nIndex
	 *            : set the property nIndex.
	 */
	public void setnIndex(Integer nIndex) {
		this.nIndex = nIndex;
	}

}
