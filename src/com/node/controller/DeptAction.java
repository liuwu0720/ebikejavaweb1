/**
 * 文件名：DeptAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月6日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类描述：部门管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月6日 下午7:55:30
 */
@Controller
@RequestMapping("/deptAction")
public class DeptAction {

	/**
	 * 
	 * 方法描述：页面跳转
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月6日 下午8:06:44
	 */
	@RequestMapping("/getDepts")
	public String getResource() {
		return "user/deptList";

	}

	@RequestMapping("/getDeptsLists")
	public String getDeptsList() {
		return "user/userlist";
	}
}
