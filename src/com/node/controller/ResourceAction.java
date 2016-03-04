/**
 * 文件名：ResourceAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月4日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.TResource;
import com.node.service.IResourceService;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月4日 下午4:47:16
 */
@Controller
@RequestMapping("/resourceAction")
public class ResourceAction {
	@Autowired
	IResourceService iResourceService;

	@RequestMapping("/getResource")
	public String getAllResource(HttpServletRequest request) {
		List<TResource> list = iResourceService.loadAll();
		// 获得所有资源
		String jsonStr = iResourceService.getJsonTree(list);
		request.setAttribute("jsonStr", jsonStr);
		System.out.println(jsonStr);
		/*
		 * // 获得所有档案类型 List<TArchiveType> atypes =
		 * atypeService.loadAllByEnable(); String types =
		 * atypeService.getJsonTree(atypes); request.setAttribute("types",
		 * types);
		 */
		return "system/resourcetree";// 跳转到资源页面
	}

	/**
	 * 
	 * 方法描述：查出资源详情信息
	 * 
	 * @param request
	 * @param resourceId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月4日 下午7:59:53
	 */
	@RequestMapping("/getResourceByid")
	@ResponseBody
	public TResource getResourceByid(HttpServletRequest request,
			@RequestParam("resourceId") String resourceId) {
		TResource resource = iResourceService.get(Integer.parseInt(resourceId));

		return resource;

	}
}
