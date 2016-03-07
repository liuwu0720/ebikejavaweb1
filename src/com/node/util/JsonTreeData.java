/**
 * 文件名：JsonTreeData.java
 * 版本信息：Version 1.0
 * 日期：2016年3月7日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.util;

import java.util.List;

/**
 * 类描述：树 json model 数据
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月7日 下午7:23:09
 */
public class JsonTreeData {
	public String id; // json id
	public String pid; //
	public String text; // json 显示文本
	public String state; // json 'open','closed'
	public List<JsonTreeData> children; //

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<JsonTreeData> getChildren() {
		return children;
	}

	public void setChildren(List<JsonTreeData> children) {
		this.children = children;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}
