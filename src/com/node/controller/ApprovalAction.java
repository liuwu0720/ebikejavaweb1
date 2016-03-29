/**
 * 文件名：ApprovalAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月28日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.text.DateFormat;
import java.text.ParseException;
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

import com.node.model.DdcApproveUser;
import com.node.model.DdcHyxhBasb;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.DdcHyxhSsdwclsb;
import com.node.model.JtRole;
import com.node.model.JtUser;
import com.node.model.PicPath;
import com.node.service.IEbikeService;
import com.node.service.IInDustryService;
import com.node.service.IJtUserService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述：审批管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月28日 下午3:35:43
 */
@Controller
@RequestMapping("/approvalAction")
public class ApprovalAction {
	@Autowired
	IEbikeService iEbikeService;

	@Autowired
	IJtUserService iJtUserService;

	@Autowired
	IInDustryService iInDustryService;

	/**
	 * 
	 * 方法描述：配额审批
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午3:56:35
	 */
	@RequestMapping("/quotaApprove")
	public String quotaApprove() {
		return "approve/quotaApprove";
	}

	/**
	 * 
	 * 方法描述：备案审批
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 下午2:10:23
	 */
	@RequestMapping("/recordApprove")
	public String recordApprove() {
		return "approve/recordApprove";
	}

	/**
	 * 
	 * 方法描述：备案审批
	 * 
	 * @param request
	 * @param lsh
	 * @param hyxhmc
	 * @param dwmc
	 * @param bjjg
	 * @param xsqy
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 下午3:54:10
	 */
	@RequestMapping("/queryRecordApprove")
	@ResponseBody
	public Map<String, Object> queryRecordApprove(HttpServletRequest request,
			String lsh, String hyxhmc, String dwmcId, String bjjg, String xsqy) {
		Page p = ServiceUtil.getcurrPage(request);
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		// 获取该用户的审批角色，可能为多重审批角色
		List<JtRole> approveJtRoles = iJtUserService
				.getApproveRolesByUser(jtUser);
		String sql = "select sb.id, sb.lsh,(select distinct b.HYXHMC from ddc_hyxh_base b where b.hyxhzh = sb.hyxhzh) as hyxhmc,"
				+ "(select distinct d.DWMC from ddc_hyxh_ssdw d where d.id = sb.ssdw_id) as dwmc ,sb.djh,sb.jsrxm1,sb.sqrq,"
				+ "(select zd.dmms1 from ddc_sjzd zd where zd.dmz=sb.xsqy and zd.dmlb='SSQY') as xsqy,sb.SLYJ ,sb.SL_INDEX from ddc_hyxh_ssdwclsb sb where 1=1";
		if (StringUtils.isNotBlank(lsh)) {
			sql += " and sb.lsh like '%" + lsh + "%'";
		}
		if (StringUtils.isNotBlank(hyxhmc)) {
			sql += " and sb.hyxhmc like '%" + hyxhmc + "%'";
		}
		if (StringUtils.isNotBlank(dwmcId)) {
			sql += " and sb.SSDW_ID like '%" + dwmcId + "%'";
		}
		if (StringUtils.isNotBlank(xsqy)) {
			sql += " and sb.xsqy ='" + xsqy + "'";
		}
		if (StringUtils.isBlank(bjjg)) {
			sql += " and sb.SLYJ is null ";
		} else if (StringUtils.isNotBlank(bjjg)) {
			sql += " and sb.SLYJ = " + bjjg;
		}
		sql += " order by sb.id desc ";

		Map<String, Object> resultMap = iEbikeService.queryBySpringSql(sql, p);

		List<Map<String, Object>> listMaps = (List<Map<String, Object>>) resultMap
				.get("rows");
		List<DdcHyxhSsdwclsb> ddcHyxhSsdwclsbs = new ArrayList<>();
		for (int i = 0; i < listMaps.size(); i++) {
			Map<String, Object> objMap = listMaps.get(i);
			DdcHyxhSsdwclsb ddcHyxhSsdwclsb = new DdcHyxhSsdwclsb();
			ddcHyxhSsdwclsb.setId(Long.parseLong(objMap.get("ID").toString()));
			ddcHyxhSsdwclsb.setLsh(objMap.get("LSH") == null ? null : objMap
					.get("LSH").toString());
			ddcHyxhSsdwclsb.setHyxhzh(objMap.get("HYXHMC") == null ? null
					: objMap.get("HYXHMC").toString());
			ddcHyxhSsdwclsb.setJsrxm1(objMap.get("JSRXM1") == null ? null
					: objMap.get("JSRXM1").toString());
			ddcHyxhSsdwclsb.setXsqyName(objMap.get("XSQY") == null ? null
					: objMap.get("XSQY").toString());
			ddcHyxhSsdwclsb.setSsdwName(objMap.get("DWMC") == null ? null
					: objMap.get("DWMC").toString());
			ddcHyxhSsdwclsb.setDjh(objMap.get("DJH") == null ? null : objMap
					.get("DJH").toString());
			ddcHyxhSsdwclsb.setSlyj(objMap.get("SLYJ") == null ? null : objMap
					.get("SLYJ").toString());
			ddcHyxhSsdwclsb
					.setSqrq(stringToDate(objMap.get("SQRQ").toString()));
			ddcHyxhSsdwclsb.setSlIndex(objMap.get("SL_INDEX") == null ? null
					: Integer.parseInt(objMap.get("SL_INDEX").toString()));
			ddcHyxhSsdwclsb.setApprove(isApprove(approveJtRoles,
					ddcHyxhSsdwclsb));
			ddcHyxhSsdwclsbs.add(ddcHyxhSsdwclsb);
		}
		Map<String, Object> newMap = new HashMap<>();
		newMap.put("total", resultMap.get("total"));
		newMap.put("rows", ddcHyxhSsdwclsbs);
		return newMap;
	}

	/**
	 * 方法描述：
	 * 
	 * @param approveJtRoles
	 * @param ddcHyxhSsdwclsb
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 下午5:27:36
	 */
	private boolean isApprove(List<JtRole> approveJtRoles,
			DdcHyxhSsdwclsb ddcHyxhSsdwclsb) {
		if (approveJtRoles == null || approveJtRoles.size() == 0) {
			return false;
		} else if (StringUtils.isNotBlank(ddcHyxhSsdwclsb.getSlyj())) {
			return false;// 已经审批完成的
		} else {
			for (JtRole jtRole : approveJtRoles) {
				if (jtRole.getRoleSort().equals(
						ddcHyxhSsdwclsb.getSlIndex() + "")) {

					return true;

				}
			}
		}
		return false;
	}

	public static Date stringToDate(String str) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			// Fri Feb 24 00:00:00 CST 2012
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 2012-02-24

		return date;
	}

	/**
	 * 
	 * 方法描述：配额申请
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午3:39:28
	 */
	@RequestMapping("/queryQuotaApprove")
	@ResponseBody
	public Map<String, Object> queryQuotaApprove(HttpServletRequest request,
			String lsh, String hyxhmc, String bjjg) {
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		// 获取该用户的审批角色，可能为多重审批角色
		List<JtRole> approveJtRoles = iJtUserService
				.getApproveRolesByUser(jtUser);
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(DdcHyxhBasb.class);
		if (StringUtils.isNotBlank(lsh)) {
			hql.addEqual("lsh", lsh);
		}
		if (StringUtils.isNotBlank(hyxhmc)) {
			hql.addLike("hyxhmc", hyxhmc);
		}
		if (StringUtils.isBlank(bjjg)) {
			hql.addIsNull("bjjg");
		} else {
			hql.addEqual("bjjg", bjjg);
		}

		hql.addOrderBy("id", "desc");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iEbikeService
				.queryDdcHyxhBasbByHql(hql);
		List<Map<String, Object>> resultList = (List<Map<String, Object>>) resultMap
				.get("rows");
		for (int i = 0; i < resultList.size(); i++) {
			DdcHyxhBasb ddcHyxhBasb = (DdcHyxhBasb) resultList.get(i);
			ddcHyxhBasb.setCurrentApprove(isCurrentApprove(approveJtRoles,
					ddcHyxhBasb));
		}

		return resultMap;
	}

	/**
	 * 方法描述：配额申请：判断是否是当前用户来审批
	 * 
	 * @param approveJtRoles
	 *            当前用户的角色列表
	 * @param ddcHyxhBasb
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 上午9:40:52
	 */
	private boolean isCurrentApprove(List<JtRole> approveJtRoles,
			DdcHyxhBasb ddcHyxhBasb) {
		if (approveJtRoles == null || approveJtRoles.size() == 0) {
			return false;
		} else if (StringUtils.isNotBlank(ddcHyxhBasb.getBjjg())) {
			return false;// 已经审批完成的
		} else {
			for (JtRole jtRole : approveJtRoles) {
				if (jtRole.getRoleSort().equals(ddcHyxhBasb.getSlIndex() + "")) {

					return true;

				}
			}
		}
		return false;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午5:08:34
	 */
	@RequestMapping("/queryApprovalInfoById")
	public String queryApprovalInfoById(HttpServletRequest request, String id,
			String type) {
		long dId = Long.parseLong(id);
		DdcHyxhBasb ddcHyxhBasb = iEbikeService.getDdcHyxhBasbById(dId);
		String approveTableName = SystemConstants.PESBTABLE;
		List<DdcApproveUser> approveUsers = iEbikeService
				.findApproveUsersByProperties(approveTableName,
						ddcHyxhBasb.getId());
		request.setAttribute("approveUsers", approveUsers);
		request.setAttribute("ddcHyxhBasb", ddcHyxhBasb);
		request.setAttribute("type", type);// 1查看 2审批
		return "approve/quotaApproveDetail";
	}

	/**
	 * 
	 * 方法描述：查看车辆备案申报详情
	 * 
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 下午6:26:29
	 */
	@RequestMapping("/queryRecordApprovalInfoById")
	public String queryRecordApprovalInfoById(HttpServletRequest request,
			String id, String type) {
		long dId = Long.parseLong(id);
		DdcHyxhSsdwclsb ddcHyxhSsdwclsb = iEbikeService
				.getDdcHyxhSsdwclsbById(dId);
		String cysyName = iEbikeService.findByProPerties("CSYS",
				ddcHyxhSsdwclsb.getCysy());

		ddcHyxhSsdwclsb.setCysyName(cysyName);// 车身颜色
		String xsqyName = iEbikeService.findByProPerties("SSQY",
				ddcHyxhSsdwclsb.getXsqy());
		ddcHyxhSsdwclsb.setXsqyName(xsqyName);// 行驶区域
		DdcHyxhBase ddcHyxhBase = iEbikeService.getHyxhByCode(ddcHyxhSsdwclsb
				.getHyxhzh());
		if (ddcHyxhBase != null) {
			ddcHyxhSsdwclsb.setHyxhzhName(ddcHyxhBase.getHyxhmc());
		}

		// 申报单位
		if (StringUtils.isNotBlank(ddcHyxhSsdwclsb.getSsdwId())) {
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(Long
					.parseLong(ddcHyxhSsdwclsb.getSsdwId()));
			if (ddcHyxhSsdw != null) {
				ddcHyxhSsdwclsb.setSsdwName(ddcHyxhSsdw.getDwmc());
			} else {
				ddcHyxhSsdwclsb.setSsdwName(null);
			}
		}
		String showEbikeImg = parseUrl(ddcHyxhSsdwclsb.getVcEbikeImg());
		String showUser1Img = parseUrl(ddcHyxhSsdwclsb.getVcUser1Img());
		String showUser2Img = parseUrl(ddcHyxhSsdwclsb.getVcUser2Img());
		ddcHyxhSsdwclsb.setVcShowEbikeImg(showEbikeImg);
		ddcHyxhSsdwclsb.setVcShowUser1Img(showUser1Img);
		ddcHyxhSsdwclsb.setVcShowUser2Img(showUser2Img);
		request.setAttribute("ddcHyxhSsdwclsb", ddcHyxhSsdwclsb);
		return "approve/recordApproveDetail";
	}

	/**
	 * 方法描述：图片显示路径进行解析
	 * 
	 * @param vcPicPath
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月12日 下午3:23:40
	 */
	private String parseUrl(String vcPicPath) {
		if (StringUtils.isNotBlank(vcPicPath)) {
			PicPath picPath = iInDustryService
					.getImgPathById(SystemConstants.PIC_IMG);
			String subPath = picPath.getVcParsePath();
			if (!subPath.endsWith("/")) {
				subPath += "/";
			}
			return subPath + vcPicPath;
		} else {
			return null;
		}

	}

	/**
	 * 
	 * 方法描述：配额申报审批
	 * 
	 * @param request
	 * @param response
	 * @param id
	 *            申报表ID
	 * @param state
	 *            办结结果 0同意 1不同意 为空表示在审批中
	 * @param note
	 *            用户审批备注
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午7:01:08
	 */
	@RequestMapping("/sureApprove")
	public void sureApprove(HttpServletRequest request,
			HttpServletResponse response, String id, String state, String note) {
		long dId = Long.parseLong(id);
		DdcHyxhBasb ddcHyxhBasb = iEbikeService.getDdcHyxhBasbById(dId);
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		String roleName = iJtUserService.getRoleNameByRoleCode(jtUser
				.getUserRole());
		String deptName = iJtUserService.getDeptNameByUser(jtUser.getUserOrg());
		jtUser.setUserRoleName(roleName);
		jtUser.setUserOrgName(deptName);
		if (state.equals("1")) {
			// 如果拒绝，则审批流程结束
			ddcHyxhBasb.setBjbm(jtUser.getUserOrgName());
			ddcHyxhBasb.setBjrq(new Date());
			ddcHyxhBasb.setBjbz(note);
			ddcHyxhBasb.setBjjg(state);
			ddcHyxhBasb.setBzjr(jtUser.getUserName());// 办结人
			DdcApproveUser approveUser = new DdcApproveUser();
			approveUser.setUserName(jtUser.getUserName());// 姓名
			approveUser.setUserOrgname(jtUser.getUserOrgName());// 部门
			approveUser.setUserRoleName(jtUser.getUserRoleName());// 角色
			approveUser.setApproveIndex(ddcHyxhBasb.getSlIndex());
			approveUser.setApproveNote(note);
			approveUser.setApproveState(Integer.parseInt(state));
			approveUser.setApproveTable(SystemConstants.PESBTABLE);
			approveUser.setApproveTableid(ddcHyxhBasb.getId());
			approveUser.setApproveTime(new Date());
			try {
				iEbikeService.updateDdcHyxhBasb(ddcHyxhBasb);
				iEbikeService.saveDdcApproveUser(approveUser);
				AjaxUtil.rendJson(response, true, "保存成功!");
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "保存失败!系统错误");
			}
		} else if (state.equals("0")) {
			// 同意:如果当前审批人是最后审批人，则整个流程结束,DdcHyxhBase实际配额数量改变
			List<JtRole> jtRoles = iJtUserService.getAllApproveRoles();
			if (ddcHyxhBasb.getSlIndex() == jtRoles.size()) {
				ddcHyxhBasb.setBjbm(jtUser.getUserOrgName());
				ddcHyxhBasb.setBjrq(new Date());
				ddcHyxhBasb.setBjbz(note);
				ddcHyxhBasb.setBjjg(state);
				ddcHyxhBasb.setBzjr(jtUser.getUserName());// 办结人
				DdcApproveUser approveUser = new DdcApproveUser();
				approveUser.setUserName(jtUser.getUserName());// 姓名
				approveUser.setUserOrgname(jtUser.getUserOrgName());// 部门
				approveUser.setUserRoleName(jtUser.getUserRoleName());// 角色
				approveUser.setApproveIndex(ddcHyxhBasb.getSlIndex());
				approveUser.setApproveNote(note);
				approveUser.setApproveState(Integer.parseInt(state));
				approveUser.setApproveTable(SystemConstants.PESBTABLE);
				approveUser.setApproveTableid(ddcHyxhBasb.getId());
				approveUser.setApproveTime(new Date());

				DdcHyxhBase ddcHyxhBase = iInDustryService
						.getDdcHyxhBaseByCode(ddcHyxhBasb.getHyxhzh());
				ddcHyxhBase.setHyxhsjzpe(ddcHyxhBase.getHyxhsjzpe()
						+ ddcHyxhBasb.getHyxhsqpe());
				try {
					iEbikeService.updateDdcHyxhBasb(ddcHyxhBasb);
					iEbikeService.saveDdcApproveUser(approveUser);
					iInDustryService.update(ddcHyxhBase);
					AjaxUtil.rendJson(response, true, "保存成功!");
				} catch (Exception e) {
					e.printStackTrace();
					AjaxUtil.rendJson(response, false, "保存失败!系统错误");
				}
			} else {
				// 审批人指向下一个角色
				int nextRoleIndex = ddcHyxhBasb.getSlIndex() + 1;
				ddcHyxhBasb.setSlIndex(nextRoleIndex);
				DdcApproveUser approveUser = new DdcApproveUser();
				approveUser.setUserName(jtUser.getUserName());// 姓名
				approveUser.setUserOrgname(jtUser.getUserOrgName());// 部门
				approveUser.setUserRoleName(jtUser.getUserRoleName());// 角色
				approveUser.setApproveIndex(ddcHyxhBasb.getSlIndex());
				approveUser.setApproveNote(note);
				approveUser.setApproveState(Integer.parseInt(state));
				approveUser.setApproveTable(SystemConstants.PESBTABLE);
				approveUser.setApproveTableid(ddcHyxhBasb.getId());
				approveUser.setApproveTime(new Date());
				try {
					iEbikeService.updateDdcHyxhBasb(ddcHyxhBasb);
					iEbikeService.saveDdcApproveUser(approveUser);
					AjaxUtil.rendJson(response, true, "审批成功!");
				} catch (Exception e) {
					e.printStackTrace();
					AjaxUtil.rendJson(response, false, "审批失败!系统错误");
				}
			}

		}

	}
}
