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

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.node.model.TResource;
import com.node.model.TRole;
import com.node.service.IUserService;

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
	private IUserService iUserService;

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
	@RequestMapping("/getSidebar")
	public String getSidebar(HttpServletRequest request) {
		// TUser user = (TUser) request.getSession().getAttribute("user");
		// System.out.println(user.getId());
		// 一个用户可能有多个角色，所以对应资源进行去重处理
		List<TRole> rolelist = iUserService.getAllRoleByUserId(1);
		List<TResource> resolist = new ArrayList<TResource>();
		for (TRole role : rolelist) {
			List<TResource> resources = iUserService.getByRoleid(role.getId());
			if (CollectionUtils.isNotEmpty(resources))
				resolist.addAll(resources);
		}
		// 转换为 Set 去重
		Set<TResource> set = new HashSet<TResource>();
		set.addAll(resolist);

		// 再加入List
		List<TResource> reslist = new ArrayList<TResource>();
		reslist.addAll(set);

		List<TResource> nodeResources = null;
		if (CollectionUtils.isNotEmpty(reslist)) {
			for (TResource tResource : reslist) {
				if (tResource.getiParent() == 0) {
					nodeResources = new ArrayList<TResource>();
					nodeResources.add(tResource);
					System.out.println(nodeResources);
				}
			}
			Collections.sort(nodeResources);// 排序
			for (TResource pResource : nodeResources) {
				for (TResource subResource : reslist) {

					if (subResource.getiParent().equals(pResource.getId())) {
						pResource.getSubTresources().add(subResource);
					}
				}
			}

			request.setAttribute("nodeResources", nodeResources);

		} else {
			request.setAttribute("message", "您还没授权，请联系管理员");
		}

		return "main/getSlidebar";
	}
}
