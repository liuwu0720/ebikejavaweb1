/**
 * 文件名：UserAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月1日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.TUser;
import com.node.model.TrafficUser;
import com.node.service.ITrafficService;
import com.node.service.IUserService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
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
	IUserService iUserService;

	@Autowired
	ITrafficService iTrafficService;

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

		TUser tUser = iUserService.findByAccount(cuser);

		org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
		String pw = t.encodePassword(cuser, cpassword);
		if (tUser == null || !tUser.getVcPassword().equals(pw)) {

			AjaxUtil.rendJson(response, false, "用户名或密码错误！");
			return;
		} else {
			request.getSession().setAttribute("user", tUser);
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
	@RequestMapping("/getAllUsers")
	public String getAllUsers(HttpServletRequest request,
			HttpServletResponse response) {
		return "user/userlist";

	}

	/**
	 * 
	 * 方法描述：系统管理：用户管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午6:13:13
	 */
	@RequestMapping("/queryAllUsers")
	@ResponseBody
	public Map<String, Object> queryAllUsers(HttpServletRequest request,
			String vcNameString, String vcDept, HttpServletResponse response) {
		HttpSession session = request.getSession();
		TUser user = (TUser) session.getAttribute("user");
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(TrafficUser.class);
		if (StringUtils.isNotBlank(vcNameString)) {
			hql.addLike("vcNameString", vcNameString);
		}
		if (StringUtils.isNotBlank(vcDept)) {
			hql.addLike("vcDept", vcDept);
		}
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iTrafficService.queryTrafficeUsers(hql);

		/*
		 * HqlHelper hql = new HqlHelper(TUser.class); hql.setQueryPage(p);
		 * Map<String, Object> resultMap = iUserService.queryAllUsers(hql);
		 */

		return resultMap;

	}

	/**
	 * 
	 * 方法描述：页面ajax请求，根据ID查询出该用户
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午6:15:03
	 */
	@RequestMapping("/queryUserById")
	@ResponseBody
	public TUser queryUserById(String id) {
		int userId = Integer.parseInt(id);
		System.out.println("id ----------" + userId);
		TUser tUser = iUserService.getById(userId);
		return tUser;

	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 下午8:11:49
	 */
	@RequestMapping("/queryTrafficById")
	@ResponseBody
	public TrafficUser queryTrafficById(String id) {
		int trafficId = Integer.parseInt(id);
		TrafficUser trafficUser = iTrafficService.getById(trafficId);
		return trafficUser;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月4日 下午3:58:17
	 */
	@RequestMapping("/delTrafficUser")
	public void delTrafficUser(String id, HttpServletResponse response) {
		int trafficId = Integer.parseInt(id);
		try {
			iTrafficService.deleteById(trafficId);
			AjaxUtil.rendJson(response, true, "操作成功！");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "操作失败");
		}
	}

	/**
	 * 
	 * 方法描述：交警支队内部管理：新增或修改用户
	 * 
	 * @param tUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午6:39:28
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public void saveOrUpdate(TrafficUser trafficUser, String vcPw,
			HttpServletResponse response) {

		if (trafficUser.getId() == null) {
			/**
			 * 查找该账号是否被占用
			 */
			List<TrafficUser> trafficUsers = iTrafficService
					.findByVcAccount(trafficUser.getVcAccount());

			if (CollectionUtils.isEmpty(trafficUsers)) {
				String tt = null;
				if (StringUtils.isBlank(vcPw)) {
					org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
					tt = t.encodePassword("123456", trafficUser.getVcAccount());
				} else {
					org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
					tt = t.encodePassword(vcPw, trafficUser.getVcAccount());
				}

				try {
					iTrafficService.saveTrafficUser(trafficUser);
					TUser tUser = new TUser();
					tUser.setVcAccount(trafficUser.getVcAccount());
					tUser.setVcPassword(tt);
					tUser.setVcUsername(trafficUser.getVcNameString());
					tUser.setiArchiveType(SystemConstants.IARCHIVETYPE_TRAFFICE);
					tUser.setiArchive(trafficUser.getId());
					iUserService.saveUser(tUser);
					AjaxUtil.rendJson(response, true, "操作成功");
				} catch (Exception e) {
					e.printStackTrace();
					AjaxUtil.rendJson(response, false, "系统故障，操作失败！");
				}
			} else {

				AjaxUtil.rendJson(response, false,
						"账号【" + trafficUser.getVcAccount() + "】已被注册");
			}

		} else {
			TrafficUser trafficUserBefore = iTrafficService.getById(trafficUser
					.getId());
			// 编辑保存时如果用户没有修改账号名则不进行重复验证
			if (!trafficUserBefore.getVcAccount().equals(
					trafficUser.getVcAccount())) {
				List<TrafficUser> trafficUsers = iTrafficService
						.findByVcAccount(trafficUser.getVcAccount());
				if (trafficUsers.size() > 0) {
					AjaxUtil.rendJson(response, false,
							"账号【" + trafficUser.getVcAccount() + "】已被注册");
				}
			}

			try {
				String tt = null;
				// 如果输入字段表明修改密码
				TUser tUser = iUserService.getByIAchiveId(trafficUserBefore
						.getId());
				if (StringUtils.isNotBlank(vcPw)) {
					org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
					tt = t.encodePassword(vcPw, trafficUser.getVcAccount());
					tUser.setVcPassword(tt);
				}

				tUser.setVcAccount(trafficUser.getVcAccount());
				tUser.setDtAddtime(new Date());
				tUser.setiArchive(trafficUser.getId());
				tUser.setiArchiveType(SystemConstants.IARCHIVETYPE_TRAFFICE);
				iUserService.updateUser(tUser);
				trafficUser.setDtAddDate(new Date());
				trafficUser.setnEnable(SystemConstants.ENABLE);
				iTrafficService.update(trafficUser);
				AjaxUtil.rendJson(response, true, "操作成功！");

			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "系统错误！操作失败！");
			}
		}

	}
}
