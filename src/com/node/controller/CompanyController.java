/**
 * 文件名：CompanyController.java
 * 版本信息：Version 1.0
 * 日期：2016年3月2日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类描述：行业所属单位的相关控制器
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月2日 下午4:26:33
 */
@Controller("/controllerAction")
public class CompanyController {

	@RequestMapping("/getAllCompanys")
	public String getAllCompanys() {
		return "company/conpanyList";

	}
}
