/**
 * 文件名：StatisticalAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月25日
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.node.model.DdcApproveUser;
import com.node.model.DdcFlow;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.DdcHyxhSsdwclsb;
import com.node.model.DdcSjzd;
import com.node.model.JtRole;
import com.node.model.JtUser;
import com.node.model.PicPath;
import com.node.object.JtViewDept;
import com.node.object.Statics;
import com.node.service.IEbikeService;
import com.node.service.IInDustryService;
import com.node.service.IJtUserService;
import com.node.service.IStatisticalService;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述：统计查询
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月25日 上午9:42:26
 */
@ApiIgnore
@Controller
@RequestMapping("/statisticalAction")
public class StatisticalAction {

	@Autowired
	IStatisticalService iStatisticalService;

	@Autowired
	IEbikeService iEbikeService;

	@Autowired
	IInDustryService iInDustryService;

	@Autowired
	IJtUserService iJtUserService;

	/**
	 * 
	 * 方法描述：业务量统计
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月25日 上午9:43:58
	 */
	@RequestMapping("/getByBusiness")
	public String getByBusiness() {
		return "statistical/business";
	}

	/**
	 * 
	 * 方法描述：区域统计
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 上午9:08:57
	 */
	@RequestMapping("/getByArea")
	public String getByArea() {
		return "statistical/area";
	}

	/**
	 * 
	 * 方法描述：协会车辆统计
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 上午9:50:04
	 */
	@RequestMapping("/getByHyxh")
	public String getByHyxh() {
		return "statistical/hyxh";
	}

	/**
	 * 
	 * 方法描述：行业协会已申报的列表
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午12:43:02
	 */
	@RequestMapping("/getHyxhSbDetail")
	public String getHyxhSbDetail(HttpServletRequest request, String hyxhzh) {
		request.setAttribute("hyxhzh", hyxhzh);
		DdcHyxhBase hyxhBase = iInDustryService.getDdcHyxhBaseByCode(hyxhzh);
		request.setAttribute("hyxhBase", hyxhBase);
		return "statistical/hyxhSbDetail";
	}

	/**
	 * 
	 * 方法描述：行业协会备案的列表
	 * 
	 * @param request
	 * @param hyxhzh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午2:02:50
	 */
	@RequestMapping("/getHyxhBaDetail")
	public String getHyxhBaDetail(HttpServletRequest request, String hyxhzh) {
		request.setAttribute("hyxhzh", hyxhzh);
		DdcHyxhBase hyxhBase = iInDustryService.getDdcHyxhBaseByCode(hyxhzh);
		request.setAttribute("hyxhBase", hyxhBase);
		return "statistical/hyxhBaDetail";
	}

	/**
	 * 
	 * 方法描述： 行业协会退办列表
	 * 
	 * @param request
	 * @param hyxhzh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午3:05:10
	 */
	@RequestMapping("/getHyxhTbDetail")
	public String getHyxhTbDetail(HttpServletRequest request, String hyxhzh) {
		request.setAttribute("hyxhzh", hyxhzh);
		DdcHyxhBase hyxhBase = iInDustryService.getDdcHyxhBaseByCode(hyxhzh);
		request.setAttribute("hyxhBase", hyxhBase);
		return "statistical/hyxhTbDetail";
	}

	/**
	 * 
	 * 方法描述：大队统计
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午3:27:59
	 */
	@RequestMapping("/getByTeam")
	public String getByTeam() {
		return "statistical/teamlist";
	}

	/**
	 * 
	 * 方法描述：页面跳转:所有的退办查询
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午5:48:30
	 */
	@RequestMapping("/getByBack")
	public String getByBack() {
		return "statistical/backFlow";
	}

	/**
	 * 
	 * 方法描述：流水查询
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 上午10:20:36
	 */
	@RequestMapping("/getByWater")
	public String getByWater() {
		return "statistical/waterFlow";
	}

	/**
	 * 
	 * 方法描述：档案编号的详细流水
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月22日 下午2:20:25
	 */
	@RequestMapping("/getFlowListByDabh")
	public String getFlowListByDabh(String dabh, HttpServletRequest request) {
		request.setAttribute("dabh", dabh);
		return "statistical/dabhFlows";
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 上午10:24:19
	 */
	@RequestMapping("/queryByWater")
	@ResponseBody
	public Map<String, Object> queryByWater(HttpServletRequest request,
			String hyxhzh, String dwmcId, String dtstart, String dtend,
			String lsh, String ywlx, String djh, String cphm) {
		Page page = ServiceUtil.getcurrPage(request);
		String sql = "select f.id,f.cphm,f.djh,f.slrq ,f.LSH,f.slyj,f.sl_index,"
				+ "(select t.DWMC from DDC_HYXH_SSDW t where t.id = f.ssdwid) as dwmc,"
				+ "(select d.dmms1 from ddc_sjzd d where d.dmz=f.ywlx and d.dmlb='YWLX') as ywlx "
				+ " from ddc_flow f where 1=1 ";
		if (StringUtils.isNotBlank(ywlx)) {
			sql += " and f.ywlx = '" + ywlx + "'";
		}
		if (StringUtils.isNotBlank(djh)) {
			sql += " and f.djh = '" + djh + "'";
		}
		if (StringUtils.isNotBlank(cphm)) {
			sql += " and f.cphm like '%" + cphm + "%'";
		}
		if (StringUtils.isNotBlank(lsh)) {
			sql += " and f.lsh like '" + lsh + "'";
		}
		if (StringUtils.isNotBlank(dtstart)) {
			sql += " and f.slrq >=to_date('" + dtstart + "','yyyy-MM-dd')";
		}
		if (StringUtils.isNotBlank(dtend)) {
			sql += " and f.slrq <=to_date('" + dtend + "','yyyy-MM-dd')";
		}
		if (StringUtils.isNotBlank(hyxhzh)) {
			sql += " and f.HYXHZH = '" + hyxhzh + "'";
		}
		if (StringUtils.isNotBlank(dwmcId)) {
			sql += " and f.SSDWID = " + dwmcId;
		}

		sql += " order by f.slrq desc";
		Map<String, Object> resultMap = iStatisticalService
				.findBySpringSqlPage(sql, page);
		return resultMap;

	}

	/**
	 * 
	 * 方法描述：所有的退办查询列表
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午5:51:46
	 */
	@RequestMapping("/queryByBackFlow")
	@ResponseBody
	public Map<String, Object> queryByBackFlow(HttpServletRequest request,
			String hyxhzh, String dtstart, String dtend, String xsqy,
			String lsh, String djh) {
		Page page = ServiceUtil.getcurrPage(request);
		StringBuffer sb = new StringBuffer();
		sb.append("select a.id,a.lsh,a.djh,(select user_name from oa_user_view where user_code=a.slr and rownum=1 ) as slr,");
		sb.append("(select distinct d.DMMS1 from ddc_sjzd d where d.dmz=a.xsqy and d.dmlb='SSQY' and rownum=1) as xsqy,");
		sb.append("  (select t.org_name from oa_dept_view t where t.org_id=a.slbm and rownum=1 ) as slbm,");
		sb.append("to_char(a.slrq,'yyyy-mm-dd hh24:mi:ss') as slrq from ddc_flow a where a.slyj='1' and a.YWLX='A' and a.hyxhzh != 'cs'");
		if (StringUtils.isNotBlank(xsqy)) {
			sb.append(" and a.xsqy='" + xsqy + "'");
		}
		if (StringUtils.isNotBlank(lsh)) {
			sb.append(" and a.lsh='" + lsh + "'");
		}
		if (StringUtils.isNotBlank(djh)) {
			sb.append(" and a.djh='" + djh + "'");
		}
		if (StringUtils.isNotBlank(dtstart)) {
			sb.append(" and a.slrq >=to_date('" + dtstart + "','yyyy-MM-dd')");
		}
		if (StringUtils.isNotBlank(dtend)) {
			sb.append(" and a.slrq <=to_date('" + dtend + "','yyyy-MM-dd')");
		}
		if (StringUtils.isNotBlank(hyxhzh)) {
			sb.append(" and a.HYXHZH = '" + hyxhzh + "'");
		}

		sb.append(" order by a.id desc");
		Map<String, Object> resultMap = iStatisticalService
				.findBySpringSqlPage(sb.toString(), page);

		return resultMap;
	}

	/**
	 * 
	 * 方法描述：大队统计查询
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午3:30:37
	 */
	@RequestMapping("/queryByTeam")
	@ResponseBody
	public Map<String, Object> queryByTeam(HttpServletRequest request) {

		List<?> dlist = getDeptList();
		Map<String, Object> teamMap = iStatisticalService.findTeam(dlist);

		return teamMap;
	}

	/**
	 * 
	 * 方法描述：页面跳转：大队备案列表
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午4:55:49
	 */
	@RequestMapping("/getTeamBadetail")
	public String getTeamBadetail(HttpServletRequest request, String team) {
		request.setAttribute("team", team);
		return "statistical/teamBaDetail";
	}

	/**
	 * 
	 * 方法描述：页面跳转:大队退办列表
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午5:04:50
	 */
	@RequestMapping("/getTeamTbDetail")
	public String getTeamTbDetail(HttpServletRequest request, String team) {
		request.setAttribute("team", team);
		return "statistical/teamTbDetail";
	}

	/**
	 * 
	 * 方法描述：区域申报列表
	 * 
	 * @param request
	 * @param areacode
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月5日 下午4:17:39
	 */
	@RequestMapping("/getAreaSbList")
	public String getAreaSbList(HttpServletRequest request, String areacode) {
		request.setAttribute("areacode", areacode);
		return "statistical/areaSbList";
	}

	/**
	 * 
	 * 方法描述：区域已备案列表
	 * 
	 * @param request
	 * @param areacode
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月5日 下午4:30:00
	 */
	@RequestMapping("/getAreaBaList")
	public String getAreaBaList(HttpServletRequest request, String areacode) {
		request.setAttribute("areacode", areacode);
		return "statistical/areaBaList";
	}

	/**
	 * 
	 * 方法描述：区域退办查询
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月5日 下午9:57:23
	 */
	@RequestMapping("/getAreaTbList")
	public String getAreaTbList(HttpServletRequest request, String areacode) {
		request.setAttribute("areacode", areacode);
		return "statistical/areaTbList";
	}

	/**
	 * 
	 * 方法描述 大队退办列表----退办
	 * 
	 * @param request
	 * @param team
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午5:10:47
	 */
	@RequestMapping("/queryTeamTbDetail")
	@ResponseBody
	public Map<String, Object> queryTeamTbDetail(HttpServletRequest request,
			String xsqy, String lsh, String djh, String dtend, String dtstart,
			String dwmcId, String hyxhzh, String team) {
		Page page = ServiceUtil.getcurrPage(request);
		String sql = " select f.id,(select distinct d.DMMS1 from ddc_sjzd d where d.dmz=f.xsqy and d.dmlb='SSQY' and rownum=1) as xsqy,"
				+ "f.ppxh,f.JSRXM1,f.SFZMHM1 , "
				+ "f.lsh,f.djh,f.slrq,f.hyxhzh,(select d.HYXHMC from ddc_hyxh_base d where d.HYXHZH = f.HYXHZH and rownum = 1) as hyxhmc,"
				+ "f.SSDWID, (select s.DWMC from  DDC_HYXH_SSDW s where s.ID = f.SSDWID and rownum = 1) as dwmc  "
				+ " from ddc_flow f where f.slyj='1' and f.ywlx='A' and f.slbm in (select org_id  from OA_DEPT_VIEW  "
				+ " start with org_id ="
				+ team
				+ " connect by prior org_id = up_org)";
		if (StringUtils.isNotBlank(xsqy)) {
			sql += " and f.xsqy='" + xsqy + "'";
		}
		if (StringUtils.isNotBlank(lsh)) {
			sql += " and f.lsh like'%" + lsh + "%'";
		}
		if (StringUtils.isNotBlank(djh)) {
			sql += " and f.djh like '%" + djh + "%'";
		}
		if (StringUtils.isNotBlank(dtstart)) {
			sql += " and f.slrq >=to_date('" + dtstart + "','yyyy-MM-dd')";
		}
		if (StringUtils.isNotBlank(dtend)) {
			sql += " and f.slrq <=to_date('" + dtend + "','yyyy-MM-dd')";
		}
		if (StringUtils.isNotBlank(hyxhzh)) {
			sql += " and f.HYXHZH = '" + hyxhzh + "'";
		}
		if (StringUtils.isNotBlank(dwmcId)) {
			sql += " and f.SSDWID = " + dwmcId;
		}

		sql += "  order by f.id desc";
		Map<String, Object> resultMap = iInDustryService.getBySpringSql(sql,
				page);
		return resultMap;

	}

	/**
	 * 
	 * 方法描述：查询大队列表---备案
	 * 
	 * @param request
	 * @param team
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午4:54:14
	 */
	@RequestMapping("/queryTeamBaDetail")
	@ResponseBody
	public Map<String, Object> queryTeamBaDetail(HttpServletRequest request,
			String xsqy, String dabh, String djh, String cphm, String dwmcId,
			String hyxhzh, String team) {
		Page page = ServiceUtil.getcurrPage(request);
		String sql = "select d.id,d.dabh,d.cphm,d.hyxhzh,(select b.HYXHMC from ddc_hyxh_base b where b.hyxhzh=d.hyxhzh and rownum=1 ) as hyxhzhName,d.ppxh, d.djh,"
				+ "  d.SSDWID,(select s.DWMC from ddc_hyxh_ssdw s where s.id = d.SSDWID and rownum = 1) as dwmc,"
				+ "d.jsrxm1,d.sfzmhm1,d.SLRQ  from ddc_daxxb d where d.zt != 'E' and d.slbm in (select org_id  from OA_DEPT_VIEW  start with org_id  = '"
				+ team + "'  connect by prior org_id = up_org) ";
		if (StringUtils.isNotBlank(dabh)) {
			sql += " and d.dabh like '%" + dabh + "%'";
		}
		if (StringUtils.isNotBlank(djh)) {
			sql += " and  d.djh like '%" + djh + "%'";
		}
		if (StringUtils.isNotBlank(cphm)) {
			sql += " and d.cphm like '%" + cphm + "%'";
		}
		if (StringUtils.isNotBlank(dwmcId)) {
			sql += " and  d.SSDWID like '%" + dwmcId + "%'";
		}
		if (StringUtils.isNotBlank(hyxhzh)) {
			sql += " and d.HYXHZH like '%" + hyxhzh + "%'";
		}
		if (StringUtils.isNotBlank(xsqy)) {
			sql += " and d.XSQY = '" + xsqy + "' ";
		}

		sql += "  order by d.id desc";
		Map<String, Object> resultMap = iInDustryService.getBySpringSql(sql,
				page);
		return resultMap;

	}

	/**
	 * 
	 * 方法描述：退办列表
	 * 
	 * @param request
	 * @param hyxhzh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午3:09:05
	 */
	@RequestMapping("/queryHyxhTbDetail")
	@ResponseBody
	public Map<String, Object> queryHyxhTbDetail(HttpServletRequest request,
			String hyxhzh) {
		Page page = ServiceUtil.getcurrPage(request);
		String sql = "select f.lsh,(select d.DWMC from  DDC_HYXH_SSDW d where d.id= f.SSDWID)as dwmc,"
				+ " f.ppxh,f.djh,f.jsrxm1,f.sfzmhm1,f.slrq from ddc_flow f where f.slyj='1' and f.hyxhzh='"
				+ hyxhzh + "'";
		sql += "  order by f.id desc";
		Map<String, Object> resultMap = iInDustryService.getBySpringSql(sql,
				page);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：：行业协会备案的列表
	 * 
	 * @param request
	 * @param hyxhzh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午2:11:07
	 */
	@RequestMapping("/queryHyxhBaDetail")
	@ResponseBody
	public Map<String, Object> queryHyxhBaDetail(HttpServletRequest request,
			String hyxhzh) {
		Page page = ServiceUtil.getcurrPage(request);
		String sql = " select da.dabh,(select d.DWMC from  DDC_HYXH_SSDW d where d.id= da.SSDWID )as dwmc, "
				+ "da.djh,  da.cphm,da.ppxh,da.jsrxm1,da.sfzmhm1,da.syrq"
				+ " from ddc_daxxb da where da.hyxhzh='"
				+ hyxhzh
				+ "' and da.gdyj='0'";
		sql += "  order by da.id desc";

		Map<String, Object> resultMap = iInDustryService.getBySpringSql(sql,
				page);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：行业协会已申报的列表
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午12:43:02
	 */
	@RequestMapping("/queryHyxhSbDetail")
	@ResponseBody
	public Map<String, Object> queryHyxhSbDetail(HttpServletRequest request,
			String hyxhzh) {
		Page page = ServiceUtil.getcurrPage(request);
		String sql = "select sb.id,sb.lsh,(select d.DWMC from  DDC_HYXH_SSDW d where d.id=sb.SSDWID) as dwmc,"
				+ "(select h.HYXHMC from DDC_HYXH_BASE h where h.hyxhzh=sb.hyxhzh) as hyxhmc,"
				+ "sb.ppxh,sb.djh,sb.jsrxm1,sb.sfzmhm1,(select jt.USER_NAME from jt_user jt where jt.user_code=sb.slr) as slrmc,"
				+ "(select dept.org_name from oa_dept_view dept where dept.org_id = sb.slbm) as slbmmc,"
				+ "sb.slrq from ddc_hyxh_ssdwclsb sb where sb.hyxhzh='"
				+ hyxhzh + "' and sb.slyj='0'";
		sql += "  order by sb.id desc";
		Map<String, Object> resultMap = iInDustryService.getBySpringSql(sql,
				page);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：行业协会
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 上午10:13:47
	 */
	@RequestMapping("/queryByHyxh")
	@ResponseBody
	public Map<String, Object> queryByHyxh(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		String sql = "select distinct a.hyxhzh,  a.hyxhmc,decode(a.sb, '', '0', a.sb) sb,decode(b.total, '', '0', b.total) ba,"
				+ "a.tb,a.totalpe from (select n.hyxhzh,  n.hyxhmc, decode(m.total, '', '0', m.total) sb,  n.total tb,n.totalpe "
				+ " from (select s.hyxhzh, count(*) total  from ddc_hyxh_ssdwclsb s where s.hyxhzh != 'cs'   group by s.hyxhzh) m"
				+ "  right join (select h.hyxhzh,  h.hyxhmc,h.totalpe , decode(c.total, '', '0', c.total) total from ddc_hyxh_base h"
				+ " left join (select f.hyxhzh, count(*) total  from ddc_flow f  where f.slyj = '1' and f.ywlx='A'  group by f.hyxhzh) c"
				+ "  on h.hyxhzh = c.hyxhzh    where h.hyxhzh != 'cs') n   on m.hyxhzh = n.hyxhzh) a"
				+ " full join (select d.hyxhzh, d.hyxhmc, x.total from ddc_hyxh_base d left join (select t.hyxhzh, count(*) total"
				+ " from ddc_daxxb t where t.zt!='E' group by t.hyxhzh) x on d.hyxhzh = x.hyxhzh where d.hyxhzh != 'cs') b  on a.hyxhzh = b.hyxhzh"
				+ " order by a.hyxhmc desc";
		Map<String, Object> sqlMap = iStatisticalService.findBySpringSqlPage(
				sql, p);
		List<Map<String, Object>> sqlList = (List<Map<String, Object>>) sqlMap
				.get("rows");
		List<Statics> slist = new ArrayList<>();
		Long sbs = 0l;// 总申报量
		Long bas = 0l;// 总备案量
		Long tbs = 0l;// 总退办量
		Long totals = 0l;

		for (int i = 0; i < sqlList.size(); i++) {
			Map<String, Object> objMap = sqlList.get(i);
			Statics stat = new Statics();
			stat.setEname(objMap.get("HYXHZH").toString());
			stat.setCname(objMap.get("HYXHMC").toString());
			stat.setTotal(objMap.get("TOTALPE") == null ? null : objMap.get(
					"TOTALPE").toString());
			stat.setSb(objMap.get("SB").toString());
			stat.setBa(objMap.get("BA").toString());
			stat.setTb(objMap.get("TB").toString());

			sbs += Long.valueOf(objMap.get("SB").toString());
			bas += Long.valueOf(objMap.get("BA").toString());
			tbs += Long.valueOf(objMap.get("TB").toString());
			totals += Long.valueOf(objMap.get("TOTALPE").toString());
			slist.add(stat);
		}

		Statics stats = new Statics();
		stats.setEname("total");
		stats.setCname("总计");
		stats.setTotal(String.valueOf(totals));
		stats.setSb(String.valueOf(sbs));
		stats.setBa(String.valueOf(bas));
		stats.setTb(String.valueOf(tbs));
		slist.add(stats);
		Map<String, Object> newMap = new HashMap<>();
		newMap.put("total", sqlMap.get("total"));
		newMap.put("rows", slist);
		return newMap;
	}

	/**
	 * 
	 * 方法描述：区域统计
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 上午9:12:47
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryByArea")
	@ResponseBody
	public Map<String, Object> queryByArea(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		StringBuffer sb = new StringBuffer();
		sb.append("select a.dmz,a.dmms1, decode(a.sb, '', '0', a.sb) sb, decode(b.total, '', '0', b.total) ba, a.tb");
		sb.append(" from (select n.dmz, n.dmms1, decode((m.total), '', '0', (m.total)) sb,n.total tb");
		sb.append(" from (select t.xsqy, count(*) total from ddc_hyxh_ssdwclsb t where t.hyxhzh!='cs' group by t.xsqy) m");
		sb.append(" right join (select h.dmz, h.dmms1, decode(g.total, '', '0', g.total) total from ddc_sjzd h");
		sb.append(" left join (select f.xsqy, count(*) total from ddc_flow f where f.slyj = '1'  and f.ywlx = 'A'  and f.hyxhzh!='cs' group by f.xsqy) g");
		sb.append(" on h.dmz = g.xsqy where h.dmlb = 'SSQY') n on m.xsqy = n.dmz) a");
		sb.append(" full join (select s.dmz, s.dmms1, x.total from ddc_sjzd s");
		sb.append(" left join (select d.xsqy, count(*) total from ddc_daxxb d where d.hyxhzh != 'cs' and d.ZT != 'E' group by d.xsqy) x");
		sb.append(" on s.dmz = x.xsqy where s.dmlb = 'SSQY' ) b on a.dmz = b.dmz order by a.dmms1 asc");
		Map<String, Object> sqlMap = iStatisticalService.findBySpringSqlPage(
				sb.toString(), p);
		List<Map<String, Object>> sqlList = (List<Map<String, Object>>) sqlMap
				.get("rows");
		List<Statics> sList = new ArrayList<>();

		Long sbs = 0l;// 总申报量
		Long bas = 0l;// 总备案量
		Long tbs = 0l;// 总退办量
		for (int i = 0; i < sqlList.size(); i++) {
			Map<String, Object> objMap = sqlList.get(i);
			Statics statics = new Statics();
			statics.setCname(objMap.get("DMMS1") == null ? null : objMap.get(
					"DMMS1").toString());
			statics.setBa(objMap.get("BA") == null ? null : objMap.get("BA")
					.toString());
			statics.setSb(objMap.get("SB") == null ? null : objMap.get("SB")
					.toString());
			statics.setTb(objMap.get("TB") == null ? null : objMap.get("TB")
					.toString());
			statics.setEname(objMap.get("DMZ") == null ? null : objMap.get(
					"DMZ").toString());
			sList.add(statics);
			if (objMap.get("SB") != null) {
				sbs += Long.valueOf(objMap.get("SB").toString());
			}
			if (objMap.get("BA") != null) {
				bas += Long.valueOf(objMap.get("BA").toString());
			}
			if (objMap.get("TB") != null) {
				tbs += Long.valueOf(objMap.get("TB").toString());
			}

		}

		Statics totalStatics = new Statics();
		totalStatics.setCname("统计");
		totalStatics.setSb(String.valueOf(sbs));
		totalStatics.setBa(String.valueOf(bas));
		totalStatics.setTb(String.valueOf(tbs));
		sList.add(totalStatics);
		Map<String, Object> newMap = new HashMap<>();
		newMap.put("total", sqlMap.get("total"));
		newMap.put("rows", sList);
		return newMap;
	}

	/**
	 * 
	 * 方法描述：业务量统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月25日 上午10:10:37
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryByBusiness")
	@ResponseBody
	public Map<String, Object> queryByBusiness(HttpServletRequest request,
			HttpServletResponse response) {
		// JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		StringBuffer sb = new StringBuffer();
		sb.append("select s.xsqy,(select d.dmms1 from ddc_sjzd d where d.dmz=s.xsqy and d.dmlb='SSQY') zhcn,");
		sb.append("sum(case when s.ywlx='A' and s.slyj=0  then s.total else 0 end) as ba,");
		sb.append("sum(case when s.ywlx='B' then s.total else 0 end) as bg,");
		sb.append("sum(case when s.ywlx='C' then s.total else 0 end) as zy,");
		sb.append("sum(case when s.ywlx='D' then s.total else 0 end) as zx,");
		sb.append("sum(case when s.ywlx='E' then s.total else 0 end) as jy");
		sb.append(" from (select t.xsqy,t.ywlx,count(*) total,t.slyj  from ddc_flow t where 1=1  and t.hyxhzh != 'cs'");
		sb.append(" group by t.xsqy,t.ywlx,t.slyj) s group by s.xsqy");
		Page p = ServiceUtil.getcurrPage(request);
		Map<String, Object> reMap = iStatisticalService.findBySpringSqlPage(
				sb.toString(), p);
		List<Map<String, Object>> staticsList = (List<Map<String, Object>>) reMap
				.get("rows");
		List<Statics> staticsTotal = new ArrayList<>();
		int bal = 0; // 总备案量
		int bgl = 0; // 总变更量
		int zyl = 0; // 总转移量
		int zxl = 0; // 总注销量
		int jyl = 0; // 总校验量
		for (int i = 0; i < staticsList.size(); i++) {
			Map<String, Object> staticMap = staticsList.get(i);
			Statics statics = new Statics();
			statics.setEname(staticMap.get("XSQY").toString());
			statics.setCname(staticMap.get("ZHCN").toString());
			statics.setBa(staticMap.get("BA").toString());
			statics.setBg(staticMap.get("BG").toString());
			statics.setZy(staticMap.get("ZY").toString());
			statics.setZx(staticMap.get("ZX").toString());
			statics.setJy(staticMap.get("JY").toString());
			bal += Long.valueOf(staticMap.get("BA").toString());
			bgl += Long.valueOf(staticMap.get("BG").toString());
			zyl += Long.valueOf(staticMap.get("ZY").toString());
			zxl += Long.valueOf(staticMap.get("ZX").toString());
			jyl += Long.valueOf(staticMap.get("JY").toString());
			staticsTotal.add(statics);
		}
		Statics st = new Statics();
		st.setEname("total");
		st.setCname("总计");
		st.setBa(String.valueOf(bal));
		st.setBg(String.valueOf(bgl));
		st.setZy(String.valueOf(zyl));
		st.setZx(String.valueOf(zxl));
		st.setJy(String.valueOf(jyl));
		staticsTotal.add(st);
		Map<String, Object> totalMap = new HashMap<String, Object>();
		totalMap.put("total", reMap.get("total"));
		totalMap.put("rows", staticsTotal);
		return totalMap;
	}

	/**
	 * 
	 * 方法描述：业务量统计---备案 检验
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月25日 上午11:50:17
	 */
	@RequestMapping(value = "/getBesinessDetail", method = RequestMethod.GET)
	public String getBesinessDetail(HttpServletRequest request,
			String areacode, String type) {
		request.setAttribute("area", areacode);
		request.setAttribute("type", type);
		return "statistical/businessDetail";
	}

	/**
	 * 
	 * 方法描述：业务量统计--变更
	 * 
	 * @param request
	 * @param areacode
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月6日 上午10:28:43
	 */
	@RequestMapping("/getBgBesinessDetail")
	public String getBgBesinessDetail(HttpServletRequest request, String type,
			String areacode) {
		request.setAttribute("area", areacode);
		request.setAttribute("type", type);
		return "statistical/businessBgDetail";
	}

	/**
	 * 
	 * 方法描述：查询行业协会统计详情
	 * 
	 * @param request
	 * @param hyxhzh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 上午10:42:45
	 */
	@RequestMapping("/getHyxhStatisDetail")
	public String getHyxhStatisDetail(HttpServletRequest request, String hyxhzh) {
		return "statistical/hyxhStatisDetail";
	}

	/**
	 * 
	 * 方法描述：业务量详情---备案 检验
	 * 
	 * @param area
	 * @param type
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月25日 下午1:04:29
	 */
	@RequestMapping("/queryByBusinessDetail")
	@ResponseBody
	public Map<String, Object> queryByBusinessDetail(String area, String type,
			String dabh, String djh, String cphm, String dwmcId, String xsqy,
			String hyxhzh, HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		String sql1 = "select t.id,(select b.hyxhmc from ddc_hyxh_base b where b.hyxhzh=t.hyxhzh and rownum=1) as hyxhmc,"
				+ "(SELECT S.DWMC FROM DDC_HYXH_SSDW S WHERE S.ID=t.SSDWID and rownum=1 ) AS DWMC,";
		sql1 += "t.cphm,t.djh,(select user_name from jt_user where user_code=t.slr and rownum = 1) as slr,t.dabh,";
		sql1 += "t.slbm,to_char(t.slrq,'yyyy-mm-dd hh24:mi:ss') as slrq from ddc_flow t where 1=1 and t.slyj=0 and t.hyxhzh != 'cs'";
		sql1 += " and t.ywlx='" + type + "' and t.xsqy='" + area + "'";
		if (StringUtils.isNotBlank(dabh)) {
			sql1 += " and t.DABH like '%" + dabh + "%'";
		}
		if (StringUtils.isNotBlank(djh)) {
			sql1 += " and  t.djh = '%" + djh + "%'";
		}
		if (StringUtils.isNotBlank(cphm)) {
			sql1 += " and  t.CPHM like '%" + cphm + "%'";
		}
		if (StringUtils.isNotBlank(hyxhzh)) {
			sql1 += " and t.HYXHZH = '" + hyxhzh + "'";
		}
		if (StringUtils.isNotBlank(dwmcId)) {
			sql1 += " and  t.SSDWID = " + dwmcId;
		}
		if (StringUtils.isNotBlank(xsqy)) {
			sql1 += " and t.xsqy = " + xsqy;
		}

		sql1 += " order by t.slrq desc";
		Map<String, Object> detailMap = iStatisticalService.findBySpringSql(
				sql1, p);

		return detailMap;
	}

	/**
	 * 
	 * 方法描述：业务量详情 ---- 变更
	 * 
	 * @param area
	 * @param djh
	 * @param hyxhzh
	 * @param dwId
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月6日 上午10:51:21
	 */
	@RequestMapping("/queryByBgBusinessDetail")
	@ResponseBody
	public Map<String, Object> queryByBgBusinessDetail(String area, String djh,
			String dabh, String dtstart, String dtend, String cphm,
			String type, String hyxhzh, String dwId, HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		String sql1 = "select t.id,t.cphm,t.djh,t.dabh,t.JSRXM1,t.SFZMHM1,t.hyxhzh,t.SSDWID,(select s.dmms1 from ddc_sjzd s where s.dmz = t.XSQY "
				+ "and s.dmlb = 'SSQY' and rownum = 1) as xsqyName, "
				+ "(select b.hyxhmc from ddc_hyxh_base b where b.hyxhzh=t.hyxhzh and rownum=1) as hyxhmc,";
		sql1 += "t.slrq,(select d.DWMC from DDC_HYXH_SSDW d where d.id=t.SSDWID) as dwmc from ddc_flow t where t.cphm is not null and t.hyxhzh != 'cs'";
		sql1 += " and t.ywlx='" + type + "' and t.xsqy='" + area + "'";
		if (StringUtils.isNotBlank(djh)) {
			sql1 += " and t.djh like '%" + djh + "%'";
		}
		if (StringUtils.isNotBlank(hyxhzh)) {
			sql1 += " and t.HYXHZH = '" + hyxhzh + "'";
		}
		if (StringUtils.isNotBlank(dwId)) {
			sql1 += " and t.SSDWID = '" + dwId + "'";
		}
		if (StringUtils.isNotBlank(dabh)) {
			sql1 += " and t.dabh like '" + dabh + "'";
		}

		if (StringUtils.isNotBlank(cphm)) {
			sql1 += " and t.cphm like '" + cphm + "'";
		}
		if (StringUtils.isNotBlank(dtstart)) {
			sql1 += " and t.slrq >=to_date('" + dtstart + "','yyyy-MM-dd')";
		}
		if (StringUtils.isNotBlank(dtend)) {
			sql1 += " and t.slrq <=to_date('" + dtend + "','yyyy-MM-dd')";
		}

		sql1 += " order by t.slrq desc";
		Map<String, Object> detailMap = iStatisticalService
				.findBySpringSqlPage(sql1, p);
		return detailMap;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月25日 下午6:42:59
	 */
	@RequestMapping("/getFlowDetailById")
	public String getFlowDetailById(String id, HttpServletRequest request,
			String type) {
		long flowId = Long.parseLong(id);
		DdcFlow ddcFlow = iEbikeService.getFlowById(flowId);
		String cysyName = iEbikeService.findByProPerties("CSYS",
				ddcFlow.getCysy());
		ddcFlow.setCysyName(cysyName);// 车身颜色
		String xsqyName = iEbikeService.findByProPerties("SSQY",
				ddcFlow.getXsqy());
		ddcFlow.setXsqyName(xsqyName);// 所属区域

		// 申报单位
		if (StringUtils.isNotBlank(ddcFlow.getSsdwId())) {
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(Long
					.parseLong(ddcFlow.getSsdwId()));
			if (ddcHyxhSsdw != null) {
				ddcFlow.setSsdwName(ddcHyxhSsdw.getDwmc());
			} else {
				ddcFlow.setSsdwName(null);
			}
		}
		// 协会名称
		if (StringUtils.isNotBlank(ddcFlow.getHyxhzh())) {
			DdcHyxhBase ddcHyxhBase = iInDustryService
					.getDdcHyxhBaseByCode(ddcFlow.getHyxhzh());
			if (ddcHyxhBase != null) {
				ddcFlow.setHyxhzhName(ddcHyxhBase.getHyxhmc());
			}
		}

		// 业务类型
		String ywlxName = iEbikeService.findByProPerties("YWLX",
				ddcFlow.getYwlx());
		ddcFlow.setYwlxName(ywlxName);
		// 业务原因
		String ywyyName = iEbikeService.findByProPerties("YWYY_A",
				ddcFlow.getYwyy());
		ddcFlow.setYwyyName(ywyyName);
		List<DdcApproveUser> ddcApproveUsers = new ArrayList<>();
		// 注销的业务原因
		if (ddcFlow.getYwlx().equalsIgnoreCase("D")) {
			String[] ywyyStrings = ddcFlow.getYwyy().split(",");
			String ywyyNameList = "";
			for (String ywyy : ywyyStrings) {
				String zxywyyName = iEbikeService.findByProPerties("YWYY_D",
						ywyy);
				ywyyNameList += zxywyyName + ",";
			}
			ddcFlow.setYwyyName(ywyyNameList);
			// String approveTableName = SystemConstants.DDCFLOWTABLE;
			ddcApproveUsers = iEbikeService.findApproveUsersByLsh(ddcFlow
					.getLsh());
		}
		ddcApproveUsers = iEbikeService.findApproveUsersByLsh(ddcFlow.getLsh());

		// 受理 人
		if (StringUtils.isNotBlank(ddcFlow.getSlr())) {
			JtUser jtUser = iJtUserService
					.getJtUserByUserCode(ddcFlow.getSlr());
			if (jtUser != null) {
				ddcFlow.setSlr(jtUser.getUserName());
			}

		}

		// 受理部门
		if (StringUtils.isNotBlank(ddcFlow.getSlbm())) {
			JtViewDept jtViewDept = iJtUserService.getJtDeptByOrg(ddcFlow
					.getSlbm());
			if (jtViewDept != null) {
				ddcFlow.setSlbm(jtViewDept.getOrgName());
			}

		}

		// 受理资料
		List<DdcSjzd> selectSlzls = new ArrayList<>();// 选中的受理资料
		if (ddcFlow.getYwlx().equals("A")) {
			selectSlzls = iEbikeService
					.getDbyyList(ddcFlow.getSlzl(), "BASQZL");// 备案
		}
		if (ddcFlow.getYwlx().equals("B")) {
			selectSlzls = iEbikeService
					.getDbyyList(ddcFlow.getSlzl(), "BGSQZL");// 变更
		}
		if (ddcFlow.getYwlx().equals("C")) {
			selectSlzls = iEbikeService
					.getDbyyList(ddcFlow.getSlzl(), "ZYSQZL");// 转移
		}
		if (ddcFlow.getYwlx().equals("D")) {
			selectSlzls = iEbikeService
					.getDbyyList(ddcFlow.getSlzl(), "ZXSQZL");// 注销
		}

		List<DdcSjzd> selectTbyySjzds = iEbikeService.getDbyyList(
				ddcFlow.getTbyy(), "TBYY");
		String showEbikeImg = parseUrl(ddcFlow.getVcEbikeImg());
		String showUser1Img = parseUrl(ddcFlow.getVcUser1Img());
		String showUser2Img = parseUrl(ddcFlow.getVcUser2Img());
		String vcUser1CardImg1Show = parseUrl(ddcFlow.getVcUser1CardImg1());
		String vcUser1CardImg2Show = parseUrl(ddcFlow.getVcUser1CardImg2());
		String vcUser2CardImg1Show = parseUrl(ddcFlow.getVcUser2CardImg1());
		String vcUser2CardImg2Show = parseUrl(ddcFlow.getVcUser2CardImg2());
		String vcEbikeInvoiceImgShow = parseUrl(ddcFlow.getVcEbikeInvoiceImg());
		ddcFlow.setVcShowEbikeImg(showEbikeImg);
		ddcFlow.setVcShowUser1Img(showUser1Img);
		ddcFlow.setVcShowUser2Img(showUser2Img);
		ddcFlow.setVcUser1CardImg1Show(vcUser1CardImg1Show);
		ddcFlow.setVcUser1CardImg2Show(vcUser1CardImg2Show);
		ddcFlow.setVcUser2CardImg1Show(vcUser2CardImg1Show);
		ddcFlow.setVcUser2CardImg2Show(vcUser2CardImg2Show);
		ddcFlow.setVcEbikeInvoiceImgShow(vcEbikeInvoiceImgShow);

		ddcFlow.setVcUser1WorkImgShow(parseUrl(ddcFlow.getVcUser1WorkImg()));
		ddcFlow.setVcUser2WorkImgShow(parseUrl(ddcFlow.getVcUser2WorkImg()));
		ddcFlow.setVcEbikeInsuranceImgShow(parseUrl(ddcFlow
				.getVcEbikeInsuranceImg()));
		ddcFlow.setVcQualifiedImgShow(parseUrl(ddcFlow.getVcQualifiedImg()));
		
		ddcFlow.setVcReportImgShow(parseUrl(ddcFlow.getVcReportImg()));
		ddcFlow.setVcScrapImgShow(parseUrl(ddcFlow.getVcScrapImg()));
		ddcFlow.setVcOtherImgShow(parseUrl(ddcFlow.getVcOtherImg()));
		ddcFlow.setVcDjImgShow(parseUrl(ddcFlow.getVcDjImg()));
		request.setAttribute("ddcFlow", ddcFlow);
		request.setAttribute("selectSlzls", selectSlzls);
		request.setAttribute("ddcApproveUsers", ddcApproveUsers);
		request.setAttribute("selectTbyySjzds", selectTbyySjzds);
		request.setAttribute("type", type);
		return "statistical/businessDetailInfo";
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

	public List<?> getDeptList() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
		Map<String, String> map4 = new HashMap<String, String>();
		Map<String, String> map5 = new HashMap<String, String>();
		Map<String, String> map6 = new HashMap<String, String>();
		Map<String, String> map7 = new HashMap<String, String>();
		Map<String, String> map8 = new HashMap<String, String>();
		Map<String, String> map9 = new HashMap<String, String>();
		Map<String, String> map10 = new HashMap<String, String>();
		Map<String, String> map11 = new HashMap<String, String>();
		Map<String, String> map12 = new HashMap<String, String>();
		Map<String, String> map13 = new HashMap<String, String>();

		map1.put("dcode", "30058");
		map1.put("dname", "罗湖大队");
		map2.put("dcode", "30068");
		map2.put("dname", "福田大队");
		map3.put("dcode", "30081");
		map3.put("dname", "口岸大队");
		map4.put("dcode", "30091");
		map4.put("dname", "南山大队");
		map5.put("dcode", "30097");
		map5.put("dname", "盐田大队");
		map6.put("dcode", "30102");
		map6.put("dname", "龙岗大队");
		map7.put("dcode", "30111");
		map7.put("dname", "宝安大队");
		map8.put("dcode", "30144");
		map8.put("dname", "机场大队");
		map9.put("dcode", "30146");
		map9.put("dname", "蛇口港大队");
		map10.put("dcode", "30469");
		map10.put("dname", "龙华大队");
		map11.put("dcode", "30470");
		map11.put("dname", "大鹏大队");
		map12.put("dcode", "30393");
		map12.put("dname", "光明大队");
		map13.put("dcode", "30402");
		map13.put("dname", "坪山大队");

		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
		list.add(map8);
		list.add(map9);
		list.add(map10);
		list.add(map11);
		list.add(map12);
		list.add(map13);
		return list;
	}

	/**
	 * 
	 * 方法描述：查询区域统计------备案详情列表
	 * 
	 * @param request
	 * @param djh
	 * @param zt
	 * @param dwmcId
	 * @param hyxhzh
	 * @param flag
	 * @param cphm
	 * @param jsrxm1
	 * @param dabh
	 * @param sfzhm1
	 * @param xsqy
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月16日 上午11:05:32
	 */
	@RequestMapping("/queryBaList")
	@ResponseBody
	public Map<String, Object> queryBaList(HttpServletRequest request,
			String djh, String zt, String dwmcId, String hyxhzh, String flag,
			String cphm, String jsrxm1, String dabh, String sfzhm1,
			String xsqy, HttpServletResponse response) {

		Page p = ServiceUtil.getcurrPage(request);

		String sql = "select A.ID,A.DABH,A.CPHM,A.DJH,A.JSRXM1,A.GDYJ,A.SFZMHM1,A.SLYJ,  A.hyxhzh,A.SSDWID, "
				+ " (select b.hyxhmc from ddc_hyxh_base b where b.hyxhzh=a.hyxhzh and rownum=1) as hyxhmc,"
				+ "(SELECT S.DWMC FROM DDC_HYXH_SSDW S WHERE S.ID=A.SSDWID and rownum=1 ) AS DWMC,"
				+ "(select d.DMMS1 from ddc_sjzd d where d.dmz=a.xsqy and d.dmlb='SSQY'  and rownum=1) as xsqy, "
				+ "(SELECT D.DMMS1 FROM DDC_SJZD D WHERE D.DMZ=A.ZT AND D.DMLB='CLZT'  and rownum=1)AS ZT from DDC_DAXXB A where 1=1"
				+ " and a.HYXHZH != 'cs' and a.zt != 'E'";

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
		// 驾驶人
		if (StringUtils.isNotBlank(jsrxm1)) {
			sql += " and a.JSRXM1 like '%" + jsrxm1 + "%'";
		}
		// 身份证
		if (StringUtils.isNotBlank(sfzhm1)) {
			sql += " and a.SFZMHM1 like '%" + sfzhm1 + "%'";
		}
		// 行驶区域
		if (StringUtils.isNotBlank(xsqy)) {
			sql += " and a.XSQY = '" + xsqy + "'";
		}
		// 协会名称
		if (StringUtils.isNotBlank(hyxhzh)) {
			sql += " and a.HYXHZH = '" + hyxhzh + "'";
		}
		// 单位名称
		if (StringUtils.isNotBlank(dwmcId)) {
			sql += " and a.SSDWID = " + dwmcId;
		}
		sql += "  order by A.ID DESC";
		Map<String, Object> resultMap = iEbikeService.queryBySpringSql(sql, p);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：已申报列表
	 * 
	 * @param request
	 * @param lsh
	 * @param hyxhzh
	 * @param dwmcId
	 * @param bjjg
	 * @param xsqy
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月25日 下午2:18:35
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
				+ "(select distinct d.DWMC from ddc_hyxh_ssdw d where d.id = sb.SSDWID) as dwmc ,sb.SSDWID,sb.djh,sb.jsrxm1,sb.sqrq,sb.SLRQ,"
				+ "(select distinct zd.dmms1 from ddc_sjzd zd where zd.dmz=sb.xsqy and zd.dmlb='SSQY') as xsqy,sb.SLYJ ,sb.SL_INDEX from ddc_hyxh_ssdwclsb sb "
				+ "where 1=1 and sb.HYXHZH!='cs'";
		if (StringUtils.isNotBlank(lsh)) {
			sql += " and sb.lsh like '%" + lsh + "%'";
		}
		if (StringUtils.isNotBlank(hyxhzh)) {
			sql += " and sb.hyxhzh ='" + hyxhzh + "'";
		}
		if (StringUtils.isNotBlank(dwmcId)) {
			sql += " and sb.SSDWID =" + dwmcId + "";
		}
		if (StringUtils.isNotBlank(xsqy)) {
			sql += " and sb.xsqy ='" + xsqy + "'";
		}
		if (StringUtils.isNotBlank(bjjg)) {
			if (bjjg.equals("0") || bjjg.equals("1")) {
				sql += " and sb.slyj =" + Integer.parseInt(bjjg);
			} else {
				sql += " and sb.SLYJ is null ";
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
			ddcHyxhSsdwclsb.setSsdwId(objMap.get("SSDWID") == null ? null
					: objMap.get("SSDWID").toString());
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
	 * 方法描述：判断车辆申报是否为当前用户审批
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

	/**
	 * 
	 * 方法描述：根据档案编号查询所有流水详情列表
	 * 
	 * @param request
	 * @param dabh
	 * @param dwId
	 * @param hyxhzh
	 * @param dtstart
	 * @param dtend
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月22日 下午2:51:33
	 */
	@RequestMapping("/queryDabhFlowList")
	@ResponseBody
	public Map<String, Object> queryDabhFlowList(HttpServletRequest request,
			String dabh, String dwId, String hyxhzh, String dtstart,
			String dtend) {
		Page page = ServiceUtil.getcurrPage(request);
		String sql = " select f.id,(select distinct d.DMMS1 from ddc_sjzd d where d.dmz=f.xsqy and d.dmlb='SSQY' and rownum=1) as XSQYNAME,f.slyj,f.ywlx,"
				+ "(select distinct d.DMMS1 from ddc_sjzd d where d.dmz=f.ywlx and d.dmlb='YWLX' and rownum=1) as ywlxname,"
				+ "f.ppxh,f.JSRXM1,f.SFZMHM1 ,f.cphm,f.dabh, "
				+ "f.lsh,f.djh,f.slrq,f.hyxhzh,(select d.HYXHMC from ddc_hyxh_base d where d.HYXHZH = f.HYXHZH and rownum = 1) as hyxhmc,"
				+ "f.SSDWID, (select s.DWMC from  DDC_HYXH_SSDW s where s.ID = f.SSDWID and rownum = 1) as dwmc,f.SL_INDEX  "
				+ " from ddc_flow f where 1=1 ";

		if (StringUtils.isNotBlank(dtstart)) {
			sql += " and f.slrq >=to_date('" + dtstart + "','yyyy-MM-dd')";
		}
		if (StringUtils.isNotBlank(dtend)) {
			sql += " and f.slrq <=to_date('" + dtend + "','yyyy-MM-dd')";
		}
		if (StringUtils.isNotBlank(dabh)) {
			sql += " and f.dabh  like '%" + dabh + "%'";
		}

		sql += "  order by f.id desc";
		Map<String, Object> resultMap = iInDustryService.getBySpringSql(sql,
				page);
		return resultMap;
	}
}
