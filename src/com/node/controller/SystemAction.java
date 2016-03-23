/**
 * 文件名：SystemActionf.java
 * 版本信息：Version 1.0
 * 日期：2016年3月21日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.JtMenu;
import com.node.service.ISystemService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述：菜单管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 下午2:32:39
 */
@Controller
@RequestMapping("/systemAction")
public class SystemAction {

	@Autowired
	ISystemService iSystemService;

	/**
	 * 
	 * 方法描述：页面跳转
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午2:33:53
	 */
	@RequestMapping("/getAll")
	public String getAll() {
		return "system/resourcetree";
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午2:37:48
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(JtMenu.class);
		hql.addOrderBy("iParent");
		hql.addOrderBy("nSort");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iSystemService.queryByHql(hql);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午2:58:18
	 */
	@RequestMapping("/getParentMenu")
	@ResponseBody
	public List<JtMenu> getParentMenu() {
		List<JtMenu> jtMenus = iSystemService.getParentMenu();
		return jtMenus;
	}

	/**
	 * 
	 * 方法描述：保存操作
	 * 
	 * @param request
	 * @param response
	 * @param jtMenu
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午3:03:35
	 */
	@RequestMapping("/saveOrUpdate")
	public void saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, JtMenu jtMenu) {
		if (jtMenu.getiParent() == null) {
			jtMenu.setiParent(SystemConstants.ROOT_PARENTID);
		} else {
			JtMenu parentMenu = iSystemService.getMenuById(jtMenu.getiParent());
			jtMenu.setiParent(parentMenu.getId());
			jtMenu.setVcParent(parentMenu.getVcMenu());
		}

		try {
			if (jtMenu.getId() == null) {
				iSystemService.save(jtMenu);
			} else {
				iSystemService.update(jtMenu);
			}
			AjaxUtil.rendJson(response, true, "操作成功！");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "系统错误!");
		}
	}

	/**
	 * 
	 * 方法描述：禁用
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午4:04:47
	 */
	@RequestMapping("/del")
	public void del(HttpServletRequest request, HttpServletResponse response,
			String id) {
		int menuId = Integer.parseInt(id);
		JtMenu jtMenu = iSystemService.getMenuById(menuId);
		jtMenu.setnEnable(SystemConstants.DISABLE);
		try {
			iSystemService.update(jtMenu);
			AjaxUtil.rendJson(response, true, "成功！");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "失败！系统错误");
		}
	}
}
