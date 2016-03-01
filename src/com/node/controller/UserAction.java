/**
 * 文件名：UserAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月1日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.node.model.TUser;
import com.node.service.IUserService;
import com.node.util.AjaxUtil;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月1日 上午12:09:31
 */
@Controller
@RequestMapping("/userAction")
public class UserAction {
	@Autowired
	IUserService iUserService;

	/**
	 * 
	 * 方法描述：登录首页
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月1日 下午4:49:30
	 */
	@RequestMapping("/index")
	public String index() {

		return "index";
	}

	@RequestMapping("/checkUser")
	public void checkUser(HttpServletRequest request,
			HttpServletResponse response, String cuser, String cpassword,
			String ccode) {
		String code = (String) request.getSession().getAttribute("certCode");
		System.out.println("code1 = " + code);
		System.out.println("code2 = " + ccode);
		if (!code.equalsIgnoreCase(ccode)) {
			AjaxUtil.rendJson(response, false, "111111111");

		}

		TUser tUser = iUserService.findById(44);
		System.out.println(tUser);
		org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
		String pw = t.encodePassword(tUser.getVcPassword(),
				tUser.getVcAccount());

	}
}
