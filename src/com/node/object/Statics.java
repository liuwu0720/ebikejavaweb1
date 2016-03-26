/**
 * 文件名：Statics.java
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
 * @version: 2016年3月25日 上午10:15:50
 */
public class Statics implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String ename;// 行驶区域代码值
	private String cname;// 行驶区域中文名
	private String ba;// 备案量
	private String bg;// 变更
	private String zy;// 转移
	private String zx;// 注销
	private String jy;// 检验
	private String sb;// 退办
	private String tb;// 退办
	private String total;// 总数
	private String sy;// 已使用

	public Statics() {
		super();
	}

	public Statics(String id, String ename, String cname, String ba, String bg,
			String zy, String zx, String jy, String sb, String tb,
			String total, String sy) {
		super();
		this.id = id;
		this.ename = ename;
		this.cname = cname;
		this.ba = ba;
		this.bg = bg;
		this.zy = zy;
		this.zx = zx;
		this.jy = jy;
		this.sb = sb;
		this.tb = tb;
		this.total = total;
		this.sy = sy;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getBa() {
		return ba;
	}

	public void setBa(String ba) {
		this.ba = ba;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getZx() {
		return zx;
	}

	public void setZx(String zx) {
		this.zx = zx;
	}

	public String getJy() {
		return jy;
	}

	public void setJy(String jy) {
		this.jy = jy;
	}

	public void setTb(String tb) {
		this.tb = tb;
	}

	public String getTb() {
		return tb;
	}

	public void setSb(String sb) {
		this.sb = sb;
	}

	public String getSb() {
		return sb;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSy() {
		return sy;
	}

	public void setSy(String sy) {
		this.sy = sy;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}