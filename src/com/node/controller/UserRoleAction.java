/**
 * 文件名：UserRoleAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月7日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月7日 下午7:47:00
 */
@Controller
@RequestMapping("/roleAction")
public class UserRoleAction {
	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月7日 下午7:48:46
	 */
	@RequestMapping("/getAllRoles")
	public String getAllRoles() {
		return "user/userRoleList";

	}
}
