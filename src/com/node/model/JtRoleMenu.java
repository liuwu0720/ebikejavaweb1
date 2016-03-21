package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * JtRoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JT_ROLE_MENU")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class JtRoleMenu implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 5953190496040678815L;
	private Integer id;
	private Integer roleid;
	private Integer menuid;

	// Constructors

	/** default constructor */
	public JtRoleMenu() {
	}

	/** minimal constructor */
	public JtRoleMenu(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public JtRoleMenu(Integer id, Integer roleid, Integer menuid) {
		this.id = id;
		this.roleid = roleid;
		this.menuid = menuid;
	}

	@SequenceGenerator(name = "JT_ROLE_MENU", sequenceName = "SEQ_JT_ROLE_MENU", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "JT_ROLE_MENU")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ROLEID", precision = 0)
	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Column(name = "MENUID", precision = 0)
	public Integer getMenuid() {
		return this.menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

}