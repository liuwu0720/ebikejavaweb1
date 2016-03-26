/**
 * 文件名：FlowStatis.java
 * 版本信息：Version 1.0
 * 日期：2016年3月25日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.object;

import java.io.Serializable;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月25日 下午1:09:40
 */
public class FlowStatis implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String lsh;
	private String dabh;
	private String cphm;
	private String djh;
	private String slr;
	private String slbm;
	private String slrq;
	private String ddmc;// 大队名称
	private String zdmc;// 中队名称

	public FlowStatis() {
		super();
	}

	public FlowStatis(Long id, String lsh, String dabh, String cphm,
			String djh, String slr, String slbm, String slrq, String ddmc,
			String zdmc) {
		super();
		this.id = id;
		this.lsh = lsh;
		this.dabh = dabh;
		this.cphm = cphm;
		this.djh = djh;
		this.slr = slr;
		this.slbm = slbm;
		this.slrq = slrq;
		this.ddmc = ddmc;
		this.zdmc = zdmc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDabh() {
		return dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public String getCphm() {
		return cphm;
	}

	public void setCphm(String cphm) {
		this.cphm = cphm;
	}

	public String getDjh() {
		return djh;
	}

	public void setDjh(String djh) {
		this.djh = djh;
	}

	public String getSlr() {
		return slr;
	}

	public void setSlr(String slr) {
		this.slr = slr;
	}

	public String getSlbm() {
		return slbm;
	}

	public void setSlbm(String slbm) {
		this.slbm = slbm;
	}

	public String getSlrq() {
		return slrq;
	}

	public void setSlrq(String slrq) {
		this.slrq = slrq;
	}

	public String getDdmc() {
		return ddmc;
	}

	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}

	public String getZdmc() {
		return zdmc;
	}

	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getLsh() {
		return lsh;
	}

}