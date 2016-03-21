/**
 * 文件名：JtViewDept.java
 * 版本信息：Version 1.0
 * 日期：2016年3月21日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.object;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 下午4:44:15
 */
public class JtViewDept {
	private Integer id;
	private String orgId;
	private String upOrg;
	private String orgName;
	private String jb;
	private String flag;

	/**
	 * @return id : return the property id.
	 */
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

	/**
	 * @return orgId : return the property orgId.
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            : set the property orgId.
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return upOrg : return the property upOrg.
	 */
	public String getUpOrg() {
		return upOrg;
	}

	/**
	 * @param upOrg
	 *            : set the property upOrg.
	 */
	public void setUpOrg(String upOrg) {
		this.upOrg = upOrg;
	}

	/**
	 * @return orgName : return the property orgName.
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName
	 *            : set the property orgName.
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return jb : return the property jb.
	 */
	public String getJb() {
		return jb;
	}

	/**
	 * @param jb
	 *            : set the property jb.
	 */
	public void setJb(String jb) {
		this.jb = jb;
	}

	/**
	 * @return flag : return the property flag.
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            : set the property flag.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
