/**
 * 文件名：UserRoleAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月22日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.JtMenu;
import com.node.model.JtRole;
import com.node.model.JtRoleMenu;
import com.node.service.IJtUserService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;

/**
 * 类描述：角色管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月22日 下午7:01:18
 */
@Controller
@RequestMapping("/userRoleAction")
public class UserRoleAction {
	@Autowired
	IJtUserService iJtUserService;

	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午7:02:33
	 */
	@RequestMapping("/getAll")
	public String getAll() {
		return "user/userrole";
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午7:37:36
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(JtRole.class);
		hql.addOrderBy("id");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iJtUserService.getAllRolesByHql(hql);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：删除
	 * 
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午8:20:48
	 */
	@RequestMapping("/deleteById")
	public void deleteById(String id, HttpServletRequest request,
			HttpServletResponse response) {
		int delId = Integer.parseInt(id);
		try {
			iJtUserService.deleteRoleById(delId);
			AjaxUtil.rendJson(response, true, "删除成功!");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "删除成功!");
		}
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param jtRole
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午8:39:32
	 */
	@RequestMapping("/saveUpdate")
	public void saveUpdate(JtRole jtRole, HttpServletResponse response) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		jtRole.setOpDate(format.format(nowDate));
		jtRole.setRoleState("1");
		try {
			if (jtRole.getId() == null) {
				iJtUserService.saveJtRole(jtRole);
			} else {
				iJtUserService.updateJtUserRole(jtRole);
			}
			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "操作失败！系统错误");
		}
	}

	/**
	 * 
	 * 方法描述：授权界面
	 * 
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午9:38:19
	 */
	@RequestMapping("/authRow")
	public String authRow(HttpServletRequest request, String id,
			HttpServletResponse response) {
		int roleId = Integer.parseInt(id);
		List<JtMenu> jtMenus = iJtUserService.getAllMenus();
		List<JtMenu> jtRoleMenus = iJtUserService.getAllMenusByRole(roleId);// 当前角色所拥有的资源
		JtRole jtRole = iJtUserService.getJtRoleById(roleId);
		request.setAttribute("jtRole", jtRole);
		request.setAttribute("jtMenus", jtMenus);
		String jtMenusString = "";
		for (JtMenu jtMenu : jtRoleMenus) {
			jtMenusString += jtMenu.getId() + ",";
		}
		System.out.println("jtMenusString= " + jtMenusString);
		request.setAttribute("jtMenusString", jtMenusString);
		return "user/userroleAuth";
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param userId
	 * @param response
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午10:26:35
	 */
	@RequestMapping("/saveUpdateUserAuth")
	public void saveUpdateUserAuth(HttpServletRequest request, String roleId,
			HttpServletResponse response) {
		String[] priArray = request.getParameterValues("priBox");
		String subPriString = "";
		String priString = "";
		if (priArray != null && priArray.length > 0) {
			for (String pri : priArray) {
				String[] priStrings = request.getParameterValues(pri);
				if (priStrings != null && priStrings.length > 0) {
					for (String str : priStrings) {
						subPriString += str + ",";
					}
				}

				priString += pri + ",";
			}

		}
		String menuString = priString + subPriString;
		System.out.println("priString + subPriString = " + priString
				+ subPriString);
		try {
			if (StringUtils.isNotBlank(roleId)) {
				int roId = Integer.parseInt(roleId);
				iJtUserService.deleteMenusByRoleId(roId);
			}
			String[] menuArray = menuString.split(",");
			if (StringUtils.isNotBlank(menuString)) {
				for (String menuId : menuArray) {
					JtRoleMenu jtRoleMenu = new JtRoleMenu();
					if (StringUtils.isNotBlank(roleId)) {
						jtRoleMenu.setRoleid(Integer.parseInt(roleId));
					} else {
						jtRoleMenu.setRoleid(0);
					}
					jtRoleMenu.setMenuid(Integer.parseInt(menuId));
					iJtUserService.saveJtRoleMenu(jtRoleMenu);
				}
			}

			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "系统错误");
		}

	}
}
