package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 角色资源关联表 TRoleResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ROLE_RESOURCE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TRoleResource implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -874575552097375985L;
	// Fields

	private Integer id;
	private Integer iRoleId;
	private Integer iResourceId;

	// Constructors

	/** default constructor */
	public TRoleResource() {
	}

	// Property accessors
	@SequenceGenerator(name = "RRID", sequenceName = "s_t_role_resource", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "RRID")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "I_ROLE_ID", precision = 22, scale = 0)
	public Integer getiRoleId() {
		return iRoleId;
	}

	/**
	 * @param iRoleId
	 *            : set the property iRoleId.
	 */
	public void setiRoleId(Integer iRoleId) {
		this.iRoleId = iRoleId;
	}

	@Column(name = "I_RESOURCE_ID", precision = 22, scale = 0)
	public Integer getiResourceId() {
		return iResourceId;
	}

	/**
	 * @param iResourceId
	 *            : set the property iResourceId.
	 */
	public void setiResourceId(Integer iResourceId) {
		this.iResourceId = iResourceId;
	}

}