/**
 * 文件名：MainAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月2日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.node.model.JtMenu;
import com.node.model.JtUser;
import com.node.service.IJtUserService;

/**
 * 类描述：主页的页面加载
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月2日 上午8:34:59
 */
@Controller
@RequestMapping("/mainAction")
public class MainAction {

	@Autowired
	IJtUserService iJtUserService;

	/**
	 * 
	 * 方法描述：展示主页的显示隐藏按钮
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 上午9:22:36
	 */
	@RequestMapping("/getControl")
	public String getControl() {
		return "main/control";
	}

	/**
	 * 
	 * 方法描述：展示主页的页头
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 上午9:24:07
	 */
	@RequestMapping("/getHeader")
	public String getHeader() {
		return "main/header";
	}

	/**
	 * 
	 * 方法描述：主页的欢迎页面
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 上午9:36:01
	 */
	@RequestMapping("/getWelcome")
	public String getWelcomePage() {
		return "main/welcome";
	}

	/**
	 * 
	 * 方法描述：加载左侧菜单
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 上午10:33:56
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getSidebar")
	public String getSidebar(HttpServletRequest request) {
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		String userRole = jtUser.getUserRole();
		String userRolePri = jtUser.getUserPri();
		List<JtMenu> jtMenus = iJtUserService.getByRole(userRole);
		List<JtMenu> jtMenus2 = new ArrayList<>();
		if (StringUtils.isNotBlank(userRolePri)) {
			String[] menuArray = userRolePri.split(",");
			for (String menuStr : menuArray) {
				JtMenu jtMenu = iJtUserService.getByMenuId(Integer
						.parseInt(menuStr));
				jtMenus2.add(jtMenu);
			}
		}
		// 转入set去重s
		Set<JtMenu> jtMenuSet = new HashSet<JtMenu>();
		jtMenuSet.addAll(jtMenus);
		jtMenuSet.addAll(jtMenus2);

		List<JtMenu> allJtMenus = new ArrayList<>();
		allJtMenus.addAll(jtMenuSet);
		// 排序处理
		Collections.sort(allJtMenus);
		List<JtMenu> nodeJtMenus = new ArrayList<JtMenu>();
		for (JtMenu jtMenu : allJtMenus) {
			if (jtMenu.getiParent().equals(0)) {
				nodeJtMenus.add(jtMenu);
			}
		}

		for (JtMenu nodeJtMenu : nodeJtMenus) {
			for (JtMenu subJtMenu : allJtMenus) {
				if (subJtMenu.getiParent().equals(nodeJtMenu.getId())) {
					nodeJtMenu.getSubJtMenus().add(subJtMenu);
				}
			}
		}
		request.setAttribute("nodeJtMenus", nodeJtMenus);
		System.out.println("jtMenuSet = " + jtMenuSet);
		return "main/getSlidebar";
	}
}
