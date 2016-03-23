/**
 * 文件名：DeptAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月21日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.object.JtViewDept;
import com.node.service.ISystemService;

/**
 * 类描述：用户管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 下午4:35:49
 */
@Controller
@RequestMapping("/deptAction")
public class DeptAction {
	@Autowired
	ISystemService iSystemService;

	/**
	 * 
	 * 方法描述：页面跳转
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午5:10:47
	 */
	@RequestMapping("/getAll")
	public String getAll() {
		return "user/userlist";
	}

	/**
	 * 
	 * 方法描述：部门树形结构
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午4:37:51
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public Object getTree(HttpServletRequest request, String orgId,
			String orgName, String upOrg, HttpServletResponse response) {

		List<JtViewDept> jtViewDepts = iSystemService.getDepts(orgId, orgName,
				upOrg);
		Map<String, Object> mainMap = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (JtViewDept jp : jtViewDepts) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", jp.getOrgId());
			map.put("pid", jp.getUpOrg());
			map.put("name", jp.getOrgName());
			if (jp.getOrgId().equals("30015")) {
				map.put("open", "true");
			}
			mapList.add(map);
		}
		mainMap.put("map", mapList);
		JSONArray json = new JSONArray(mapList);

		/*
		 * List<JsonTreeData> jsonTreeDatas = new ArrayList<>(); for (JtViewDept
		 * jtViewDept : jtViewDepts) { JsonTreeData treeData = new
		 * JsonTreeData(); treeData.setId(jtViewDept.getOrgId());
		 * treeData.setPid(jtViewDept.getUpOrg());
		 * treeData.setText(jtViewDept.getOrgName());
		 * jsonTreeDatas.add(treeData); } List<JsonTreeData> newTreeDataList =
		 * TreeNodeUtil .getfatherNode(jsonTreeDatas);
		 */

		return json.toString();
	}
}
