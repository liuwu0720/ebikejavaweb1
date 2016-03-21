package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * JtUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JT_USER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class JtUser implements java.io.Serializable {

	// Fields

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 941151215631239955L;
	private Integer id;
	private String userCode;
	private String userPassword;
	private String userName;
	private String userOrg;
	private String userState;
	private String userRole;
	private String opIp;
	private String opDate;
	private String opRemark;
	private String userPri;

	// Constructors

	/** default constructor */
	public JtUser() {
	}

	/** minimal constructor */
	public JtUser(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public JtUser(Integer id, String userCode, String userPassword,
			String userName, String userOrg, String userState, String userRole,
			String opIp, String opDate, String opRemark, String userPri) {
		this.id = id;
		this.userCode = userCode;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userOrg = userOrg;
		this.userState = userState;
		this.userRole = userRole;
		this.opIp = opIp;
		this.opDate = opDate;
		this.opRemark = opRemark;
		this.userPri = userPri;
	}

	@SequenceGenerator(name = "JT_USER", sequenceName = "SEQ_JT_USER", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "JT_USER")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "USER_CODE", length = 20)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "USER_PASSWORD", length = 20)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "USER_NAME", length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_ORG", length = 150)
	public String getUserOrg() {
		return this.userOrg;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}

	@Column(name = "USER_STATE", length = 2)
	public String getUserState() {
		return this.userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	@Column(name = "USER_ROLE", length = 100)
	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
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

	@Column(name = "USER_PRI", length = 300)
	public String getUserPri() {
		return this.userPri;
	}

	public void setUserPri(String userPri) {
		this.userPri = userPri;
	}

}