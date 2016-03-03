package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 用户角色关联表 IUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_USER_ROLE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TUserRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3109148825966970473L;
	private Integer id;
	private Integer iUser;
	private Integer iRole;
	private Integer nEnable;

	// Constructors

	/** default constructor */
	public TUserRole() {
	}

	/** full constructor */
	public TUserRole(Integer iUser, Integer iRole, Integer nEnable) {
		this.iUser = iUser;
		this.iRole = iRole;
		this.nEnable = nEnable;
	}

	// Property accessors
	@SequenceGenerator(name = "USER_ROLE", sequenceName = "S_USER_ROLE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "USER_ROLE")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "I_USER", precision = 22, scale = 0)
	public Integer getiUser() {
		return iUser;
	}

	/**
	 * @param iUser
	 *            : set the property iUser.
	 */
	public void setiUser(Integer iUser) {
		this.iUser = iUser;
	}

	@Column(name = "I_ROLE", precision = 22, scale = 0)
	public Integer getiRole() {
		return iRole;
	}

	/**
	 * @param iRole
	 *            : set the property iRole.
	 */
	public void setiRole(Integer iRole) {
		this.iRole = iRole;
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

}