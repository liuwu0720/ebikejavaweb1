/**
  * 文件名：DdcDriverDaxx.java
  * 版本信息：Version 1.0
  * 日期：2016年6月7日
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
import javax.persistence.UniqueConstraint;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年6月7日 下午9:51:53 
 */
@Entity
@Table(name = "ddc_driver_daxx", uniqueConstraints = @UniqueConstraint(columnNames = "ID"))
public class DdcDriverDaxx implements Serializable{

	
	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 245110489522435189L;

	private Long id;
	private Long driverId;
	private Long daId;
	private String sysFlag;
	
	
	@Column(name = "SYN_FLAG", length = 20)
	public String getSysFlag() {
		return sysFlag;
	}
	/**
	 * @param sysFlag : set the property sysFlag.
	 */
	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}
	@SequenceGenerator(name = "SEQ_DDC_DRIVER_DAXXID", sequenceName = "SEQ_DDC_DRIVER_DAXX", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_DDC_DRIVER_DAXXID")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "DRIVERID")
	public Long getDriverId() {
		return driverId;
	}
	/**
	 * @param driverId : set the property driverId.
	 */
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	
	@Column(name = "DAID")
	public Long getDaId() {
		return daId;
	}
	/**
	 * @param daId : set the property daId.
	 */
	public void setDaId(Long daId) {
		this.daId = daId;
	}
	
}
