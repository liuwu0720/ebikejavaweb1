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
import java.util.Calendar;
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
import com.node.model.DdcDaxxb;
import com.node.model.DdcFlow;
import com.node.model.DdcHyxhBasb;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.DdcHyxhSsdwclsb;
import com.node.model.DdcSjzd;
import com.node.model.JtRole;
import com.node.model.JtUser;
import com.node.model.PicPath;
import com.node.object.JtViewDept;
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
	 * 方法描述：检验审批
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午5:27:13
	 */
	@RequestMapping("/checkApprove")
	public String checkApprove() {
		return "approve/checkApprove";
	}

	/**
	 * 
	 * 方法描述：查询电动车检验列表
	 * 
	 * @param djh
	 * @param dabh
	 * @param cphm
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午5:34:36
	 */
	@RequestMapping("/queryCheckApprove")
	@ResponseBody
	public Map<String, Object> queryCheckApprove(String djh, String dabh,
			String hyxhzh, String xsqy, String dwmcId,
			HttpServletRequest request, String cphm) {
		String sql = "select A.ID,A.DABH,A.CPHM,A.DJH,A.JSRXM1,A.GDYJ,A.SFZMHM1,A.SYRQ, "
				+ "(select distinct b.HYXHMC from DDC_HYXH_BASE b where b.HYXHZH = A.HYXHZH  and rownum=1) as HYXHMC,a.HYXHZH,"
				+ " (SELECT distinct S.DWMC FROM DDC_HYXH_SSDW S WHERE S.ID=A.ZZJGDMZH  and rownum=1) AS DWMC,a.ZZJGDMZH,"
				+ "(select distinct d.DMMS1 from ddc_sjzd d where d.dmz=a.xsqy and d.dmlb='SSQY' and rownum=1) as xsqy, "
				+ "(SELECT distinct D.DMMS1 FROM DDC_SJZD D WHERE D.DMZ=A.ZT AND D.DMLB='CLZT'  and rownum=1)AS ZT from DDC_DAXXB A WHERE 1=1 ";
		// 电机号
		if (StringUtils.isNotBlank(djh)) {
			sql += " and a.djh like '%" + djh + "%'";
		}
		// 档案编号
		if (StringUtils.isNotBlank(dabh)) {
			sql += " and a.dabh like '%" + dabh + "%'";
		}
		// 车牌号
		if (StringUtils.isNotBlank(cphm)) {
			sql += " and a.sfzhm1 like '%" + cphm + "%'";
		}
		// 单位
		if (StringUtils.isNotBlank(dwmcId)) {
			sql += " and a.ZZJGDMZH = " + dwmcId;
		}
		// 协会
		if (StringUtils.isNotBlank(hyxhzh)) {
			sql += " and a.HYXHZH = '" + hyxhzh + "'";
		}
		// 行驶区域
		if (StringUtils.isNotBlank(xsqy)) {
			sql += " and a.XSQY = '" + xsqy + "'";
		}

		sql += " and a.ZT !='E'  order by A.ID DESC";
		Page p = ServiceUtil.getcurrPage(request);
		Map<String, Object> resultMap = iEbikeService.queryBySpringSql(sql, p);

		return resultMap;
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
			String lsh, String hyxhzh, String dwmcId, String bjjg, String xsqy) {
		Page p = ServiceUtil.getcurrPage(request);
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		// 获取该用户的审批角色，可能为多重审批角色
		List<JtRole> approveJtRoles = iJtUserService
				.getApproveRolesByUser(jtUser);
		String sql = "select sb.id, sb.lsh,(select distinct b.HYXHMC from ddc_hyxh_base b where b.hyxhzh = sb.hyxhzh) as hyxhmc,sb.hyxhzh,"
				+ "(select distinct d.DWMC from ddc_hyxh_ssdw d where d.id = sb.ssdw_id) as dwmc ,sb.ssdw_id,sb.djh,sb.jsrxm1,sb.sqrq,sb.SLRQ,"
				+ "(select distinct zd.dmms1 from ddc_sjzd zd where zd.dmz=sb.xsqy and zd.dmlb='SSQY') as xsqy,sb.SLYJ ,sb.SL_INDEX from ddc_hyxh_ssdwclsb sb where 1=1";
		if (StringUtils.isNotBlank(lsh)) {
			sql += " and sb.lsh like '%" + lsh + "%'";
		}
		if (StringUtils.isNotBlank(hyxhzh)) {
			sql += " and sb.hyxhzh ='" + hyxhzh + "'";
		}
		if (StringUtils.isNotBlank(dwmcId)) {
			sql += " and sb.SSDW_ID =" + dwmcId + "";
		}
		if (StringUtils.isNotBlank(xsqy)) {
			sql += " and sb.xsqy ='" + xsqy + "'";
		}
		if (StringUtils.isBlank(bjjg)) {
			sql += " and sb.SLYJ is null ";
		} else if (StringUtils.isNotBlank(bjjg)) {
			if (!bjjg.equals("-1")) {
				sql += " and sb.SLYJ = " + bjjg;
			}

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
			ddcHyxhSsdwclsb.setHyxhzh(objMap.get("HYXHZH") == null ? null
					: objMap.get("HYXHZH").toString());
			ddcHyxhSsdwclsb.setHyxhzhName(objMap.get("HYXHMC") == null ? null
					: objMap.get("HYXHMC").toString());
			ddcHyxhSsdwclsb.setJsrxm1(objMap.get("JSRXM1") == null ? null
					: objMap.get("JSRXM1").toString());
			ddcHyxhSsdwclsb.setXsqyName(objMap.get("XSQY") == null ? null
					: objMap.get("XSQY").toString());
			ddcHyxhSsdwclsb.setSsdwId(objMap.get("SSDW_ID") == null ? null
					: objMap.get("SSDW_ID").toString());
			ddcHyxhSsdwclsb.setSsdwName(objMap.get("DWMC") == null ? null
					: objMap.get("DWMC").toString());
			ddcHyxhSsdwclsb.setDjh(objMap.get("DJH") == null ? null : objMap
					.get("DJH").toString());
			ddcHyxhSsdwclsb.setSlyj(objMap.get("SLYJ") == null ? null : objMap
					.get("SLYJ").toString());
			ddcHyxhSsdwclsb
					.setSqrq(stringToDate(objMap.get("SQRQ").toString()));
			if (objMap.get("SLRQ") != null) {
				ddcHyxhSsdwclsb.setSlrq(stringToDate(objMap.get("SLRQ")
						.toString()));
			}

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
	 * 方法描述：查看配额申报详情
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
		String approveTableName = SystemConstants.RECORDSBTABLE;
		List<DdcApproveUser> approveUsers = iEbikeService
				.findApproveUsersByProperties(approveTableName,
						ddcHyxhSsdwclsb.getId());
		List<DdcSjzd> selectlTbyy = iEbikeService.getDbyyList(
				ddcHyxhSsdwclsb.getTbyy(), "TBYY");// 选中的退办原因
		List<DdcSjzd> dbyyDdcSjzds = iEbikeService.getSjzdByDmlb("TBYY");// 数据字典中所有的退办原因
		List<DdcSjzd> slzList = iEbikeService.getSjzdByDmlb("BASQZL");// 数据字典中所有的受理资料
		request.setAttribute("selectlTbyy", selectlTbyy);
		request.setAttribute("approveUsers", approveUsers);
		request.setAttribute("ddcHyxhSsdwclsb", ddcHyxhSsdwclsb);
		request.setAttribute("type", type);// 1查看 2审批
		request.setAttribute("dbyyDdcSjzds", dbyyDdcSjzds);
		request.setAttribute("slzList", slzList);
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
			approveUser.setLsh(ddcHyxhBasb.getLsh());
			// 保存流水

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

	/**
	 * 
	 * 方法描述：备案审批
	 * 
	 * @param request
	 * @param id
	 * @param state
	 * @param note
	 * @param response
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 上午9:39:51
	 */
	@RequestMapping("/sureApproveRecord")
	public void sureApproveRecord(HttpServletRequest request, String id,
			String slzl, String tbyy, String state, String note,
			HttpServletResponse response) {
		long dId = Long.parseLong(id);
		DdcHyxhSsdwclsb ddcHyxhSsdwclsb = iEbikeService
				.getDdcHyxhSsdwclsbById(dId);
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		String roleName = iJtUserService.getRoleNameByRoleCode(jtUser
				.getUserRole());
		String deptName = iJtUserService.getDeptNameByUser(jtUser.getUserOrg());
		jtUser.setUserRoleName(roleName);
		jtUser.setUserOrgName(deptName);
		DdcHyxhBase ddcHyxhBase = iInDustryService
				.getDdcHyxhBaseByCode(ddcHyxhSsdwclsb.getHyxhzh());// 行业协会账号
		if (state.equals("1")) {
			// 如果拒绝，则审批流程结束 DdcHyxhSsdwclsb DdcFlow
			ddcHyxhSsdwclsb.setSlr(jtUser.getUserName());
			ddcHyxhSsdwclsb.setSlyj(SystemConstants.NOTAGREE);
			ddcHyxhSsdwclsb.setSlbz(note);
			ddcHyxhSsdwclsb.setSlrq(new Date());
			ddcHyxhSsdwclsb.setSlbm(deptName);
			if (StringUtils.isNotBlank(tbyy)) {
				ddcHyxhSsdwclsb.setTbyy(tbyy);
			}
			// 业务流水
			DdcFlow ddcFlow = new DdcFlow();
			ddcFlow.setLsh(ddcHyxhSsdwclsb.getLsh());
			ddcFlow.setYwlx("A");
			ddcFlow.setYwyy("A");
			ddcFlow.setHyxhzh(ddcHyxhBase.getHyxhzh());
			ddcFlow.setZzjgdmzh(ddcHyxhSsdwclsb.getSsdwId());
			ddcFlow.setPpxh(ddcHyxhSsdwclsb.getPpxh());
			ddcFlow.setCysy(ddcHyxhSsdwclsb.getCysy());
			ddcFlow.setDjh(ddcHyxhSsdwclsb.getDjh());
			ddcFlow.setJtzz(ddcHyxhSsdwclsb.getJtzz());
			ddcFlow.setJsrxm1(ddcHyxhSsdwclsb.getJsrxm1());
			ddcFlow.setXb1(ddcHyxhSsdwclsb.getXb1());
			ddcFlow.setSfzmhm1(ddcHyxhSsdwclsb.getSfzmhm1());
			ddcFlow.setLxdh1(ddcHyxhSsdwclsb.getLxdh1());
			ddcFlow.setJsrxm2(ddcHyxhSsdwclsb.getJsrxm2());
			ddcFlow.setXb2(ddcHyxhSsdwclsb.getXb2());
			ddcFlow.setSfzmhm2(ddcHyxhSsdwclsb.getSfzmhm2());
			ddcFlow.setLxdh2(ddcHyxhSsdwclsb.getLxdh2());
			ddcFlow.setXsqy(ddcHyxhSsdwclsb.getXsqy());
			ddcFlow.setBz(ddcHyxhSsdwclsb.getBz());
			ddcFlow.setSlyj(SystemConstants.NOTAGREE);
			ddcFlow.setSlbz(note);
			ddcFlow.setSlr(jtUser.getUserCode());
			ddcFlow.setSlrq(new Date());
			ddcFlow.setSlbm(jtUser.getUserOrg());
			ddcFlow.setTbyy(tbyy);
			ddcFlow.setGdbz(note);
			ddcFlow.setGdr(jtUser.getUserCode());
			ddcFlow.setGdrq(new Date());
			ddcFlow.setYclb("0");
			ddcFlow.setSynFlag("UW");
			ddcFlow.setTranFlag(null);

			// 审批 人
			DdcApproveUser approveUser = new DdcApproveUser();
			approveUser.setUserName(jtUser.getUserName());// 姓名
			approveUser.setUserOrgname(jtUser.getUserOrgName());// 部门
			approveUser.setUserRoleName(jtUser.getUserRoleName());// 角色
			approveUser.setApproveIndex(ddcHyxhSsdwclsb.getSlIndex());
			approveUser.setApproveNote(note);
			approveUser.setApproveState(Integer.parseInt(state));
			approveUser.setApproveTable(SystemConstants.RECORDSBTABLE);
			approveUser.setApproveTableid(ddcHyxhSsdwclsb.getId());
			approveUser.setApproveTime(new Date());
			approveUser.setLsh(ddcFlow.getLsh());
			try {
				iEbikeService.updateDdcHyxhSsdwclsb(ddcHyxhSsdwclsb);
				iEbikeService.saveDdcApproveUser(approveUser);
				iEbikeService.saveDdcFlow(ddcFlow);
				AjaxUtil.rendJson(response, true, "审批成功!");
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "审批失败!系统错误");
			}
		} else if (state.equals("0")) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 2);
			Date syrq = calendar.getTime();// 审验日期，当前时间+2年

			// 同意:如果当前审批人是最后审批人，则整个流程结束,DdcHyxhBase实际配额数量改变
			List<JtRole> jtRoles = iJtUserService.getAllApproveRoles();
			if (ddcHyxhSsdwclsb.getSlIndex() == jtRoles.size()) {
				ddcHyxhSsdwclsb.setSlr(jtUser.getUserName());
				ddcHyxhSsdwclsb.setSlyj(SystemConstants.AGREE);
				ddcHyxhSsdwclsb.setSlbz(note);
				ddcHyxhSsdwclsb.setSlrq(new Date());
				ddcHyxhSsdwclsb.setSlbm(deptName);
				ddcHyxhSsdwclsb.setCphm(createCphm(ddcHyxhBase));

				// 业务流水
				DdcFlow ddcFlow = new DdcFlow();
				ddcFlow.setLsh(ddcHyxhSsdwclsb.getLsh());
				ddcFlow.setYwlx("A");
				ddcFlow.setYwyy("A");
				ddcFlow.setHyxhzh(ddcHyxhBase.getHyxhzh());
				ddcFlow.setZzjgdmzh(ddcHyxhSsdwclsb.getSsdwId());
				ddcFlow.setPpxh(ddcHyxhSsdwclsb.getPpxh());
				ddcFlow.setCysy(ddcHyxhSsdwclsb.getCysy());
				ddcFlow.setDjh(ddcHyxhSsdwclsb.getDjh());
				ddcFlow.setJtzz(ddcHyxhSsdwclsb.getJtzz());
				ddcFlow.setJsrxm1(ddcHyxhSsdwclsb.getJsrxm1());
				ddcFlow.setXb1(ddcHyxhSsdwclsb.getXb1());
				ddcFlow.setSfzmhm1(ddcHyxhSsdwclsb.getSfzmhm1());
				ddcFlow.setLxdh1(ddcHyxhSsdwclsb.getLxdh1());
				ddcFlow.setJsrxm2(ddcHyxhSsdwclsb.getJsrxm2());
				ddcFlow.setXb2(ddcHyxhSsdwclsb.getXb2());
				ddcFlow.setSfzmhm2(ddcHyxhSsdwclsb.getSfzmhm2());
				ddcFlow.setLxdh2(ddcHyxhSsdwclsb.getLxdh2());
				ddcFlow.setXsqy(ddcHyxhSsdwclsb.getXsqy());
				ddcFlow.setBz(ddcHyxhSsdwclsb.getBz());
				ddcFlow.setSlyj(SystemConstants.AGREE);
				ddcFlow.setSlbz(note);
				ddcFlow.setSlr(jtUser.getUserCode());
				ddcFlow.setSlzl(slzl);
				ddcFlow.setSlrq(new Date());
				ddcFlow.setSlbm(jtUser.getUserOrg());
				ddcFlow.setTbyy(tbyy);
				ddcFlow.setGdbz(note);
				ddcFlow.setGdr(jtUser.getUserCode());
				ddcFlow.setGdrq(new Date());
				ddcFlow.setYclb("0");
				ddcFlow.setSynFlag("UW");
				ddcFlow.setTranFlag(null);
				// 审批人及审批状态
				DdcApproveUser approveUser = new DdcApproveUser();
				approveUser.setUserName(jtUser.getUserName());// 姓名
				approveUser.setUserOrgname(jtUser.getUserOrgName());// 部门
				approveUser.setUserRoleName(jtUser.getUserRoleName());// 角色
				approveUser.setApproveIndex(ddcHyxhSsdwclsb.getSlIndex());
				approveUser.setApproveNote(note);
				approveUser.setApproveState(Integer.parseInt(state));
				approveUser.setApproveTable(SystemConstants.RECORDSBTABLE);
				approveUser.setApproveTableid(ddcHyxhSsdwclsb.getId());
				approveUser.setApproveTime(new Date());

				// 档案信息表
				DdcDaxxb daxxb = new DdcDaxxb();
				daxxb.setDabh(createDabh());
				daxxb.setYwlx(ddcFlow.getYwlx());
				daxxb.setYwyy(ddcFlow.getYwyy());
				daxxb.setHyxhzh(ddcHyxhSsdwclsb.getHyxhzh());
				daxxb.setZzjgdmzh(ddcHyxhSsdwclsb.getSsdwId());
				daxxb.setCphm(ddcHyxhSsdwclsb.getCphm());
				daxxb.setPpxh(ddcHyxhSsdwclsb.getPpxh());
				daxxb.setCysy(ddcHyxhSsdwclsb.getCysy());
				daxxb.setDjh(ddcHyxhSsdwclsb.getDjh());
				daxxb.setJtzz(ddcHyxhSsdwclsb.getJtzz());
				daxxb.setJsrxm1(ddcHyxhSsdwclsb.getJsrxm1());
				daxxb.setXb1(ddcHyxhSsdwclsb.getXb1());
				daxxb.setSfzmhm1(ddcHyxhSsdwclsb.getSfzmhm1());
				daxxb.setLxdh1(ddcHyxhSsdwclsb.getLxdh1());
				daxxb.setJsrxm2(ddcHyxhSsdwclsb.getJsrxm2());
				daxxb.setXb2(ddcHyxhSsdwclsb.getXb2());
				daxxb.setSfzmhm2(ddcHyxhSsdwclsb.getSfzmhm2());
				daxxb.setLxdh2(ddcHyxhSsdwclsb.getLxdh2());
				daxxb.setXsqy(ddcHyxhSsdwclsb.getXsqy());
				daxxb.setBz(ddcHyxhSsdwclsb.getBz());
				daxxb.setZt("A");
				daxxb.setSyrq(syrq);
				daxxb.setSlzl(ddcFlow.getSlzl());
				daxxb.setSlyj(ddcFlow.getSlyj());
				daxxb.setSlbz(ddcFlow.getSlbz().trim());
				daxxb.setSlr(jtUser.getUserCode());
				daxxb.setSlrq(new Date());
				daxxb.setSlbm(jtUser.getUserOrg());
				daxxb.setGdyj(ddcFlow.getGdyj());
				daxxb.setGdr(jtUser.getUserCode());
				daxxb.setGdrq(new Date());
				daxxb.setGdbm(jtUser.getUserOrg());
				daxxb.setSynFlag("UW");
				daxxb.setTranFlag(null);

				try {
					iEbikeService.updateDdcHyxhSsdwclsb(ddcHyxhSsdwclsb);
					iEbikeService.saveDdcApproveUser(approveUser);
					iEbikeService.saveDdcFlow(ddcFlow);
					iEbikeService.saveDaxxb(daxxb);
					AjaxUtil.rendJson(response, true, "保存成功!");
				} catch (Exception e) {
					e.printStackTrace();
					AjaxUtil.rendJson(response, false, "保存失败!系统错误");
				}
			} else {
				// 审批人指向下一个角色
				int nextRoleIndex = ddcHyxhSsdwclsb.getSlIndex() + 1;
				ddcHyxhSsdwclsb.setSlIndex(nextRoleIndex);
				DdcApproveUser approveUser = new DdcApproveUser();
				approveUser.setUserName(jtUser.getUserName());// 姓名
				approveUser.setUserOrgname(jtUser.getUserOrgName());// 部门
				approveUser.setUserRoleName(jtUser.getUserRoleName());// 角色
				approveUser.setApproveIndex(ddcHyxhSsdwclsb.getSlIndex());
				approveUser.setApproveNote(note);
				approveUser.setApproveState(Integer.parseInt(state));
				approveUser.setApproveTable(SystemConstants.RECORDSBTABLE);
				approveUser.setApproveTableid(ddcHyxhSsdwclsb.getId());
				approveUser.setApproveTime(new Date());
				try {
					iEbikeService.updateDdcHyxhSsdwclsb(ddcHyxhSsdwclsb);
					iEbikeService.saveDdcApproveUser(approveUser);
					AjaxUtil.rendJson(response, true, "审批成功!");
				} catch (Exception e) {
					e.printStackTrace();
					AjaxUtil.rendJson(response, false, "审批失败!系统错误");
				}
			}
		}

	}

	/**
	 * 方法描述：调用存储过程生成档案编号
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午2:14:54
	 */
	private String createDabh() {
		String dabhString = iEbikeService.getDabhByProcess();
		return dabhString;
	}

	/**
	 * 方法描述：调用存储过程生成车牌号码
	 * 
	 * @param ddcHyxhBase
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午2:10:43
	 */
	private String createCphm(DdcHyxhBase ddcHyxhBase) {
		String cphm = iEbikeService.getCphmByProcess(ddcHyxhBase);
		return cphm;
	}

	/**
	 * 
	 * 方法描述：查询电动车档案详情 ddc_daxxb
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午6:21:32
	 */
	@RequestMapping("/queryDaxxbDetail")
	public String queryDaxxbDetail(String id, HttpServletRequest request) {

		long longId = Long.parseLong(id);
		DdcDaxxb ddcDaxxb = iEbikeService.getDdcDaxxbById(longId);
		String cysyName = iEbikeService.findByProPerties("CSYS",
				ddcDaxxb.getCysy());

		ddcDaxxb.setCysyName(cysyName);// 车身颜色
		String xsqyName = iEbikeService.findByProPerties("SSQY",
				ddcDaxxb.getXsqy());
		ddcDaxxb.setXsqyName(xsqyName);// 所属区域

		String ztName = iEbikeService
				.findByProPerties("CLZT", ddcDaxxb.getZt());
		ddcDaxxb.setZtName(ztName);
		// 申报单位
		if (StringUtils.isNotBlank(ddcDaxxb.getZzjgdmzh())) {
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(Long
					.parseLong(ddcDaxxb.getZzjgdmzh()));
			if (ddcHyxhSsdw != null) {
				ddcDaxxb.setZzjgdmzhName(ddcHyxhSsdw.getDwmc());
			} else {
				ddcDaxxb.setZzjgdmzhName(null);
			}
		}
		// 业务类型
		String ywlxName = iEbikeService.findByProPerties("YWLX",
				ddcDaxxb.getYwlx());
		ddcDaxxb.setYwlxName(ywlxName);
		// 业务原因
		String ywyyName = iEbikeService.findByProPerties("YWYY_A",
				ddcDaxxb.getYwyy());
		ddcDaxxb.setYwyyName(ywyyName);

		DdcHyxhBase ddcHyxhBase = iInDustryService
				.getDdcHyxhBaseByCode(ddcDaxxb.getHyxhzh());// 行业协会账号
		ddcDaxxb.setHyxhzhmc(ddcHyxhBase.getHyxhmc());
		// 用户
		JtUser jtUser = iJtUserService.getJtUserByUserCode(ddcDaxxb.getSlr());
		ddcDaxxb.setSlrName(jtUser.getUserName());
		// 部门
		JtViewDept jtViewDept = iJtUserService.getJtDeptByOrg(ddcDaxxb
				.getSlbm());
		ddcDaxxb.setSlbmName(jtViewDept.getOrgName());

		List<DdcSjzd> slzls = iEbikeService.getSelectSlzl(ddcDaxxb.getSlzl());// 选中的退办原因
		String showEbikeImg = parseUrl(ddcDaxxb.getVcEbikeImg());
		String showUser1Img = parseUrl(ddcDaxxb.getVcUser1Img());
		String showUser2Img = parseUrl(ddcDaxxb.getVcUser2Img());
		ddcDaxxb.setVcShowEbikeImg(showEbikeImg);
		ddcDaxxb.setVcShowUser1Img(showUser1Img);
		ddcDaxxb.setVcShowUser2Img(showUser2Img);
		request.setAttribute("ddcDaxxb", ddcDaxxb);
		request.setAttribute("slzls", slzls);
		return "approve/checkApproveDetail";
	}

	/**
	 * 
	 * 方法描述：审验合格
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午7:25:47
	 */
	@RequestMapping("/sureCheckApprove")
	public void sureCheckApprove(HttpServletRequest request,
			HttpServletResponse response, String id) {
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		long dId = Long.parseLong(id);
		DdcDaxxb daxxb = iEbikeService.getDdcDaxxbById(dId);
		Date oldSyrq = daxxb.getSyrq();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldSyrq);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 2);
		Date newSyrq = calendar.getTime();// 生成新审验日期
		daxxb.setSyrq(newSyrq);

		// 业务流水
		DdcFlow flow = new DdcFlow();
		// 生成流水号
		String sql = "select seq_ddl_flow.nextval from dual";
		Object object = iEbikeService.getDateBySQL(sql);
		String seq = object.toString();
		String md = new SimpleDateFormat("yyMMdd").format(new Date());
		String lsh = "A" + md + seq;// 生成流水表流水号
		flow.setLsh(lsh);
		flow.setYwlx("E");
		flow.setYwyy("A");
		flow.setHyxhzh(daxxb.getHyxhzh());
		flow.setZzjgdmzh(daxxb.getZzjgdmzh());
		flow.setDabh(daxxb.getDabh());
		flow.setCphm(daxxb.getCphm());
		flow.setPpxh(daxxb.getPpxh());
		flow.setCysy(daxxb.getCysy());
		flow.setDjh(daxxb.getDjh());
		flow.setJtzz(daxxb.getJtzz());
		flow.setSlyj(daxxb.getSlyj());
		flow.setSlzl(daxxb.getSlzl());
		flow.setSlbz(daxxb.getSlbz());
		flow.setSlr(jtUser.getUserCode());
		flow.setSlrq(new Date());
		flow.setSlbm(jtUser.getUserOrg());
		flow.setJsrxm1(daxxb.getJsrxm1());
		flow.setXb1(daxxb.getXb1());
		flow.setSfzmhm1(daxxb.getSfzmhm1());
		flow.setLxdh1(daxxb.getLxdh1());
		flow.setJsrxm2(daxxb.getJsrxm2());
		flow.setXb2(daxxb.getXb2());
		flow.setSfzmhm2(daxxb.getSfzmhm2());
		flow.setLxdh2(daxxb.getLxdh2());
		flow.setXsqy(daxxb.getXsqy());
		flow.setBz(daxxb.getBz());
		flow.setGdyj(daxxb.getGdyj());
		flow.setGdr(jtUser.getUserCode());
		flow.setGdbm(jtUser.getUserOrg());
		flow.setGdrq(new Date());
		flow.setSynFlag("UW");
		flow.setTranFlag(null);

		try {
			iEbikeService.updateDdcDaxxb(daxxb);
			iEbikeService.saveDdcFlow(flow);
			AjaxUtil.rendJson(response, true, "成功!");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "操作失败！");
		}

	}
}
