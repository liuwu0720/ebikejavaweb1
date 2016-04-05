/**
 * 文件名：UserAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月1日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.node.model.JtUser;
import com.node.service.IJtUserService;
import com.node.util.AjaxUtil;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述：用户首页登录、用户增删改查的一些操作
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月1日 上午12:09:31
 */
@Controller
@RequestMapping("/userAction")
public class UserAction {

	@Autowired
	IJtUserService iJtUserService;

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
		/*
		 * String code = (String) request.getSession().getAttribute("certCode");
		 * 
		 * if (StringUtils.isEmpty(code) || StringUtils.isEmpty(ccode)) {
		 * AjaxUtil.rendJson(response, false, "验证码获取失败，请刷新页面重试"); return; }
		 * 
		 * 
		 * if (!code.equalsIgnoreCase(ccode)) { AjaxUtil.rendJson(response,
		 * false, "验证码不正确"); return; }
		 */
		JtUser jtUser = iJtUserService.findByAccount(cuser);

		if (jtUser == null || !jtUser.getUserPassword().equals(cpassword)) {

			AjaxUtil.rendJson(response, false, "用户名或密码错误！");
			return;
		} else {
			request.getSession().setAttribute(SystemConstants.SESSION_USER,
					jtUser);
			AjaxUtil.rendJson(response, true, "验证通过");
		}

	}

	/**
	 * 
	 * 方法描述：登录到首页
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 上午9:00:35
	 */
	@RequestMapping("/loginToMain")
	public String loginToMain() {
		return "main";

	}

	/**
	 * 
	 * 方法描述：跳转到用户管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午4:32:18
	 */
	@RequestMapping("/getAll")
	public String getAllUsers(HttpServletRequest request,
			HttpServletResponse response) {
		return "user/userlist";

	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午5:12:59
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryAllUsers")
	@ResponseBody
	public Map<String, Object> queryAllUsers(HttpServletRequest request,
			String userCode, String userName, String deptId) {
		Page p = ServiceUtil.getcurrPage(request);
		StringBuffer sql = new StringBuffer(
				"select t.id, t.USER_CODE, t.USER_NAME, t.ORG_ID,d.org_name, t.FLAG, j.id as jid,j.user_role,j.OP_DATE,j.USER_STATE  "
						+ " from oa_user_view t  left join jt_user j   on t.USER_CODE = j.user_code LEFT JOIN "
						+ " OA_DEPT_VIEW D ON t.ORG_ID=d.ORG_ID where t.USER_CODE is not null");

		if (StringUtils.isNotBlank(deptId)) {
			sql.append(" and t.ORG_ID = " + Integer.parseInt(deptId));
		}
		if (StringUtils.isNotBlank(userCode)) {
			sql.append(" and t.USER_CODE = '" + userCode + "'");
		}
		if (StringUtils.isNotBlank(userName)) {
			sql.append(" and t.USER_NAME like '%" + userName + "%'");
		}
		sql.append("  order by t.id");
		Map<String, Object> resultMap = iJtUserService.getBySpringSql(
				sql.toString(), p);
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultMap
				.get("rows");
		Map<String, Object> newMap = new HashMap<String, Object>();
		List<JtUser> jtUsers = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			JtUser jtUser = new JtUser();
			jtUser.setId(map.get("JID") == null ? 0 : Integer.parseInt(map.get(
					"JID").toString()));
			jtUser.setUserCode(map.get("USER_CODE").toString());
			jtUser.setUserName(map.get("USER_NAME") == null ? null : map.get(
					"USER_NAME").toString());
			jtUser.setUserOrg(map.get("ORG_ID").toString());
			jtUser.setUserOrgName(map.get("ORG_NAME") == null ? null : map.get(
					"ORG_NAME").toString());
			jtUser.setUserRole(map.get("USER_ROLE") == null ? null : map.get(
					"USER_ROLE").toString());
			jtUser.setOpDate(map.get("OP_DATE") == null ? null : map.get(
					"OP_DATE").toString());
			jtUser.setUserRoleName(getRoleName(jtUser.getUserRole()));
			jtUsers.add(jtUser);
		}
		newMap.put("total", resultMap.get("total").toString());
		newMap.put("rows", jtUsers);

		return newMap;
	}

	/**
	 * 
	 * 方法描述：重置用户密码
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午1:52:08
	 */
	@RequestMapping("/resetJtuser")
	public void resetJtuser(HttpServletRequest request,
			HttpServletResponse response, String id) {
		int jtuserId = Integer.parseInt(id);
		JtUser jtUser = iJtUserService.getJtUserById(jtuserId);
		jtUser.setUserPassword(jtUser.getUserCode());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		jtUser.setOpDate(format.format(nowDate));
		try {
			iJtUserService.updateJtUser(jtUser);
			AjaxUtil.rendJson(response, true, "成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "系统错误，操作失败");
		}
	}

	/**
	 * 
	 * 方法描述：将用户转入系统
	 * 
	 * @param request
	 * @param response
	 * @param userCode
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午2:10:53
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/addIntoUser")
	public void addIntoUser(HttpServletRequest request,
			HttpServletResponse response, String userCode) {
		String sql = "select * from oa_user_view where USER_CODE = '"
				+ userCode + "'";
		Map<String, Object> resultMap = iJtUserService
				.getBySpringSql(sql, null);
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultMap
				.get("rows");

		Map<String, Object> oaUserMap = list.get(0);
		JtUser jtUser = new JtUser();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		jtUser.setOpDate(format.format(nowDate));
		jtUser.setUserCode(userCode);
		jtUser.setUserPassword(userCode);
		jtUser.setUserName(oaUserMap.get("USER_NAME") == null ? null
				: oaUserMap.get("USER_NAME").toString());
		jtUser.setUserOrg(oaUserMap.get("ORG_ID") == null ? null : oaUserMap
				.get("ORG_ID").toString());
		try {
			iJtUserService.save(jtUser);
			AjaxUtil.rendJson(response, true, "成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "系统错误，操作失败");
		}
	}

	/**
	 * 
	 * 方法描述：授权界面
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午2:58:11
	 */
	@RequestMapping("/authRow")
	public String authRow(HttpServletRequest request,
			HttpServletResponse response, String id) {
		JtUser jtUser = iJtUserService.getJtUserById(Integer.parseInt(id));

		List<JtRole> jtRoles = iJtUserService.getAllRoles();
		List<JtMenu> jtMenus = iJtUserService.getAllMenus();
		request.setAttribute("jtUser", jtUser);
		request.setAttribute("jtRoles", jtRoles);
		request.setAttribute("jtMenus", jtMenus);
		return "user/userAuth";
	}

	/**
	 * 
	 * 方法描述：保存用户权限
	 * 
	 * @param request
	 * @param response
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午5:00:26
	 */
	@RequestMapping("/saveUpdateUserAuth")
	public void saveUpdateUserAuth(HttpServletRequest request, String userId,
			HttpServletResponse response) {
		String[] roleArray = request.getParameterValues("roleId");
		String[] priArray = request.getParameterValues("priBox");
		String roleString = "";
		String priString = "";
		if (roleArray != null && roleArray.length > 0) {
			for (String role : roleArray) {
				roleString += role + ",";
			}
		}

		String subPriString = "";
		if (priArray != null && priArray.length > 0) {
			for (String pri : priArray) {
				String[] priStrings = request.getParameterValues(pri);
				if (priStrings != null && priStrings.length > 0) {
					for (String str : priStrings) {
						subPriString += str + ",";
					}
				}

				System.out.println("priStrings = " + priStrings);
				priString += pri + ",";

			}

		}
		int uId = Integer.parseInt(userId);
		JtUser jtUser = iJtUserService.getJtUserById(uId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		jtUser.setOpDate(format.format(nowDate));
		jtUser.setUserRole(roleString);
		jtUser.setUserPri(priString + subPriString);
		try {
			iJtUserService.updateJtUser(jtUser);
			AjaxUtil.rendJson(response, true, "成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "系统错误！操作失败");
		}
	}

	/**
	 * 
	 * 方法描述：删除用户
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午1:41:28
	 */
	@RequestMapping("/delJtuser")
	public void delJtuser(HttpServletRequest request,
			HttpServletResponse response, String id) {
		int jtuserId = Integer.parseInt(id);
		try {
			iJtUserService.deleteJtUserById(jtuserId);
			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "系统错误");
		}
	}

	/**
	 * 方法描述：获取用户角色
	 * 
	 * @param userRole
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 上午10:39:30
	 */
	private String getRoleName(String userRole) {
		String roleName = iJtUserService.getRoleNameByRoleCode(userRole);
		return roleName;
	}

	/**
	 * 
	 * 方法描述：退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月31日 上午11:29:26
	 */
	@RequestMapping("/loginout")
	public String loginout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute(SystemConstants.SESSION_USER);
		PrintWriter out = response.getWriter();
		out.println("<script>window.parent.location.replace('"
				+ request.getContextPath() + "/index.jsp')</script>");
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 
	 * 方法描述：修改密码界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月31日 上午11:33:43
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(HttpServletRequest request,
			HttpServletResponse response) {
		JtUser jtUser = (JtUser) request.getSession().getAttribute(
				SystemConstants.SESSION_USER);
		String roleName = iJtUserService.getRoleNameByRoleCode(jtUser
				.getUserRole());
		String deptName = iJtUserService.getDeptNameByUser(jtUser.getUserOrg());
		jtUser.setUserRoleName(roleName);
		jtUser.setUserOrgName(deptName);
		request.setAttribute("jtUser", jtUser);
		return "user/modifyUser";
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月31日 下午12:13:09
	 * @throws IOException
	 */
	@RequestMapping("/saveModifyUser")
	public void saveModifyUser(HttpServletRequest request, String id,
			String userPassword, HttpServletResponse response)
			throws IOException {
		try {
			JtUser jtUser = iJtUserService.getJtUserById(Integer.parseInt(id));
			jtUser.setUserPassword(userPassword);
			iJtUserService.updateJtUser(jtUser);
			AjaxUtil.rendJson(response, true, "成功");

		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "失败！");
		}
	}
}
