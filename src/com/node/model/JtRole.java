package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * JtRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JT_ROLE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class JtRole implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = -5161729489887579767L;
	private Integer id;
	private String roleName;
	private String roleType;
	private String roleState;
	private String opIp;
	private String opDate;
	private String opRemark;

	// Constructors

	/** default constructor */
	public JtRole() {
	}

	/** minimal constructor */
	public JtRole(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public JtRole(Integer id, String roleName, String roleType,
			String roleState, String opIp, String opDate, String opRemark) {
		this.id = id;
		this.roleName = roleName;
		this.roleType = roleType;
		this.roleState = roleState;
		this.opIp = opIp;
		this.opDate = opDate;
		this.opRemark = opRemark;
	}

	@SequenceGenerator(name = "JT_ROLE", sequenceName = "SEQ_JT_ROLE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "JT_ROLE")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ROLE_NAME", length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_TYPE", length = 10)
	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Column(name = "ROLE_STATE", length = 2)
	public String getRoleState() {
		return this.roleState;
	}

	public void setRoleState(String roleState) {
		this.roleState = roleState;
	}

	@Column(name = "OP_IP", length = 20)
	public String getOpIp() {
		return this.opIp;
	}

	public void setOpIp(String opIp) {
		this.opIp = opIp;
	}

	@Column(name = "OP_DATE", length = 50)
	public String getOpDate() {
		return this.opDate;
	}

	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}

	@Column(name = "OP_REMARK", length = 200)
	public String getOpRemark() {
		return this.opRemark;
	}

	public void setOpRemark(String opRemark) {
		this.opRemark = opRemark;
	}

}