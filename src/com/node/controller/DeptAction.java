/**
 * 文件名：DeptAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月6日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.TDept;
import com.node.service.IDeptService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.JsonTreeData;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;
import com.node.util.TreeNodeUtil;

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

	@Autowired
	IDeptService iDeptService;

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

	/**
	 * 
	 * 方法描述：easyui:分页查询
	 * 
	 * @param request
	 * @param vcDept
	 * @param iPid
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月7日 下午4:31:23
	 */
	@RequestMapping("/queryAllDepts")
	@ResponseBody
	public Map<String, Object> queryAllDepts(HttpServletRequest request,
			String vcDept, String vcPid) {

		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(TDept.class);
		if (StringUtils.isNotBlank(vcDept)) {
			hql.addLike("vcDept", vcDept);
		}
		if (StringUtils.isNotBlank(vcPid)) {
			hql.addEqual("iPid", Integer.parseInt(vcPid));
		} else {
			hql.addEqual("iPid", 0);
		}
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iDeptService.queryAllDepts(hql);

		return resultMap;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param tDept
	 * @param vcPid
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月7日 下午4:34:27
	 */
	@RequestMapping("/saveOrUpdate")
	public void saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, TDept tDept, String vcPid) {
		int pid = 0;
		if (StringUtils.isNotBlank(vcPid)) {
			pid = Integer.parseInt(vcPid);
		}
		try {
			tDept.setDtAdd(new Date());
			tDept.setiPid(pid);
			tDept.setnEnable(SystemConstants.ENABLE);
			String vcPdept = iDeptService.getVcDeptNameById(pid);
			tDept.setVcPdept(vcPdept);
			iDeptService.saveOrUpdate(tDept);
			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "系统错误");
		}
	}

	/**
	 * 
	 * 方法描述：获取树形菜单
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月7日 下午5:03:18
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public Object getTree() {
		String jsonVcr = "";
		List<TDept> tDepts = iDeptService.findAll();
		JSONArray jsarr = new JSONArray();

		List<JSONObject> nodeJsonObjects = new ArrayList<>();
		/*
		 * for (TDept tDept : tDepts) {
		 * 
		 * if (tDept.getiPid().equals(SystemConstants.ROOT_PARENTID)) {
		 * 
		 * JSONObject obj = new JSONObject(); obj.element("id", tDept.getId());
		 * obj.element("text", tDept.getVcDept()); obj.element("children", new
		 * ArrayList<JSONObject>()); nodeJsonObjects.add(obj); } } for
		 * (JSONObject object : nodeJsonObjects) { for (TDept suDept : tDepts) {
		 * if (object.get("id").equals(suDept.getiPid())) { JSONObject subObj =
		 * new JSONObject(); subObj.element("id", suDept.getId());
		 * subObj.element("text", suDept.getVcDept());
		 * object.getJSONArray("children").add(subObj); } } jsarr.add(object); }
		 */
		List<JsonTreeData> treeDataList = new ArrayList<JsonTreeData>();
		for (TDept tDept : tDepts) {
			JsonTreeData treeData = new JsonTreeData();
			treeData.setId(tDept.getId() + "");
			treeData.setPid(tDept.getiPid() + "");
			treeData.setText(tDept.getVcDept());
			treeDataList.add(treeData);
		}
		List<JsonTreeData> newTreeDataList = TreeNodeUtil
				.getfatherNode(treeDataList);
		System.out.println(newTreeDataList);
		return newTreeDataList;

	}
}
