/**
  * 文件名：UserAction.java
  * 版本信息：Version 1.0
  * 日期：2016年3月1日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.node.model.TUser;
import com.node.service.IUserService;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月1日 上午12:09:31 
 */
@Controller
@RequestMapping("/userAction")
public class UserAction {
	@Autowired
	IUserService iUserService;
	
	
	@RequestMapping("/login.do")
	public String login(){
		System.out.println("***********************");
		TUser tUser = iUserService.findById(44);
		System.out.println(tUser);
		org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
		String pw = t.encodePassword( tUser.getVcPassword() , tUser.getVcAccount() );
		System.out.println("pw = "+pw);
		return "main";
	}
}
