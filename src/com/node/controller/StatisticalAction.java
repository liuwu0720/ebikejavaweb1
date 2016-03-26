/**
 * 文件名：StatisticalAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月25日
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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.DdcFlow;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.PicPath;
import com.node.object.FlowStatis;
import com.node.object.Statics;
import com.node.service.IEbikeService;
import com.node.service.IInDustryService;
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
@Controller
@RequestMapping("/statisticalAction")
public class StatisticalAction {

	@Autowired
	IStatisticalService iStatisticalService;

	@Autowired
	IEbikeService iEbikeService;

	@Autowired
	IInDustryService iInDustryService;

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
			String xsqy, String lsh, String djh) {
		Page page = ServiceUtil.getcurrPage(request);
		StringBuffer sb = new StringBuffer();
		sb.append("select a.id,a.lsh,a.djh,(select user_name from xdda_user_oa where user_code=a.slr) as slr,");
		sb.append("to_char(a.slrq,'yyyy-mm-dd hh24:mi:ss') as slrq from ddc_flow a where a.slyj='1'");
		if (StringUtils.isNotBlank(xsqy)) {
			sb.append(" and a.xsqy='" + xsqy + "'");
		}
		if (StringUtils.isNotBlank(lsh)) {
			sb.append(" and a.lsh='" + lsh + "'");
		}
		if (StringUtils.isNotBlank(djh)) {
			sb.append(" and a.djh='" + djh + "'");
		}

		sb.append(" order by a.id desc");
		Map<String, Object> resultMap = iStatisticalService
				.findBySpringSqlPage(sb.toString(), page);
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultMap
				.get("rows");
		List<FlowStatis> flowStatis = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> objMap = list.get(i);
			FlowStatis fs = new FlowStatis();
			fs.setId(Long.valueOf(objMap.get("ID").toString()));
			fs.setLsh(objMap.get("LSH").toString());
			fs.setDjh(objMap.get("DJH").toString());
			fs.setSlr(objMap.get("SLR").toString());
			fs.setSlrq(objMap.get("SLRQ").toString());
			flowStatis.add(fs);
		}
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("total", flowStatis.size());
		newMap.put("rows", flowStatis);
		return newMap;
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
	 * 方法描述 大队退办列表
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
			String team) {
		Page page = ServiceUtil.getcurrPage(request);
		String sql = " select f.* from ddc_flow f where f.slyj='1' and f.slbm in (select org_id  from OA_DEPT_VIEW  "
				+ " start with org_id in  (select t.org_id from OA_DEPT_VIEW t where t.up_org="
				+ team + ")   connect by prior org_id = up_org)";
		sql += "  order by f.id desc";
		Map<String, Object> resultMap = iInDustryService.getBySpringSql(sql,
				page);
		return resultMap;

	}

	/**
	 * 
	 * 方法描述：查询大队列表详情
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
			String team) {
		Page page = ServiceUtil.getcurrPage(request);
		String sql = "select d.* from ddc_daxxb d where d.slbm in (select org_id  from OA_DEPT_VIEW  start with org_id  = '"
				+ team + "'  connect by prior org_id = up_org)";

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
		String sql = "select f.lsh,(select d.DWMC from  DDC_HYXH_SSDW d where d.id= f.zzjgdmzh)as dwmc,"
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
		String sql = " select da.dabh,(select d.DWMC from  DDC_HYXH_SSDW d where d.id= da.zzjgdmzh )as dwmc, "
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
		String sql = "select sb.id,sb.lsh,(select d.DWMC from  DDC_HYXH_SSDW d where d.id=sb.ssdw_id) as dwmc,"
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
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct a.hyxhzh,a.hyxhmc, decode(a.total,'','0',a.total) total,"
				+ " decode(a.sb, '', '0', a.sb) sb, decode(b.total, '', '0', b.total) ba,a.tb");
		sb.append(" from (select n.hyxhzh, n.hyxhmc,  decode((m.total), '', '0', (m.total)) total,decode((m.total - n.total), '', '0', (m.total - n.total)) sb,n.total tb");
		sb.append(" from (select s.hyxhzh, count(*) total from ddc_hyxh_ssdwclsb s group by s.hyxhzh) m");
		sb.append(" right join (select h.hyxhzh, h.hyxhmc, decode(c.total, '', '0', c.total) total from ddc_hyxh_base h");
		sb.append(" left join (select f.hyxhzh, count(*) total from ddc_flow f where f.slyj = '1' group by f.hyxhzh) c");
		sb.append(" on h.hyxhzh = c.hyxhzh where h.hyxhzh!='cs') n on m.hyxhzh = n.hyxhzh) a");
		sb.append(" full join (select d.hyxhzh, d.hyxhmc, x.total from ddc_hyxh_base d");
		sb.append(" left join (select t.hyxhzh, count(*) total from ddc_daxxb t group by t.hyxhzh) x");
		sb.append(" on d.hyxhzh = x.hyxhzh where d.hyxhzh!='cs') b on a.hyxhzh = b.hyxhzh order by a.hyxhmc desc");
		Map<String, Object> sqlMap = iStatisticalService.findBySpringSqlPage(
				sb.toString(), p);
		List<Map<String, Object>> sqlList = (List<Map<String, Object>>) sqlMap
				.get("rows");
		List<Statics> slist = new ArrayList<>();
		Long sbs = 0l;// 总申报量
		Long bas = 0l;// 总备案量
		Long tbs = 0l;// 总退办量
		Long totalSbs = 0l;

		for (int i = 0; i < sqlList.size(); i++) {
			Map<String, Object> objMap = sqlList.get(i);
			Statics stat = new Statics();
			stat.setTotal(objMap.get("TOTAL").toString());
			stat.setEname(objMap.get("HYXHZH").toString());
			stat.setCname(objMap.get("HYXHMC").toString());
			stat.setSb(objMap.get("SB").toString());
			stat.setBa(objMap.get("BA").toString());
			stat.setTb(objMap.get("TB").toString());

			totalSbs += Long.valueOf(objMap.get("TOTAL").toString());
			sbs += Long.valueOf(objMap.get("SB").toString());
			bas += Long.valueOf(objMap.get("BA").toString());
			tbs += Long.valueOf(objMap.get("TB").toString());

			slist.add(stat);
		}

		Statics stats = new Statics();
		stats.setEname("total");
		stats.setCname("总计");
		stats.setTotal(String.valueOf(totalSbs));
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
	@RequestMapping("/queryByArea")
	@ResponseBody
	public Map<String, Object> queryByArea(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		StringBuffer sb = new StringBuffer();
		sb.append("select a.dmms1, decode(a.sb, '', '0', a.sb) sb, decode(b.total, '', '0', b.total) ba, a.tb");
		sb.append(" from (select n.dmz, n.dmms1, decode((m.total - n.total), '', '0', (m.total - n.total)) sb,n.total tb");
		sb.append(" from (select t.xsqy, count(*) total from ddc_hyxh_ssdwclsb t where t.hyxhzh!='cs' group by t.xsqy) m");
		sb.append(" right join (select h.dmz, h.dmms1, decode(g.total, '', '0', g.total) total from ddc_sjzd h");
		sb.append(" left join (select f.xsqy, count(*) total from ddc_flow f where f.slyj = '1' group by f.xsqy) g");
		sb.append(" on h.dmz = g.xsqy where h.dmlb = 'SSQY') n on m.xsqy = n.dmz) a");
		sb.append(" full join (select s.dmz, s.dmms1, x.total from ddc_sjzd s");
		sb.append(" left join (select d.xsqy, count(*) total from ddc_daxxb d group by d.xsqy) x");
		sb.append(" on s.dmz = x.xsqy where s.dmlb = 'SSQY') b on a.dmz = b.dmz order by a.dmms1 asc");
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
		sb.append("sum(case when s.ywlx='A' then s.total else 0 end) as ba,");
		sb.append("sum(case when s.ywlx='B' then s.total else 0 end) as bg,");
		sb.append("sum(case when s.ywlx='C' then s.total else 0 end) as zy,");
		sb.append("sum(case when s.ywlx='D' then s.total else 0 end) as zx,");
		sb.append("sum(case when s.ywlx='E' then s.total else 0 end) as jy");
		sb.append(" from (select t.xsqy,t.ywlx,count(*) total from ddc_flow t where t.cphm is not null");
		sb.append(" group by t.xsqy,t.ywlx) s group by s.xsqy");
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
	 * 方法描述：页面跳转
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
	 * 方法描述：业务量详情
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
			HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		String sql1 = "select t.id,";
		sql1 += "t.cphm,t.djh,(select user_name from jt_user where user_code=t.slr) as slr,t.dabh,";
		sql1 += "t.slbm,to_char(t.slrq,'yyyy-mm-dd hh24:mi:ss') as slrq from ddc_flow t where t.cphm is not null";
		sql1 += " and t.ywlx='" + type + "' and t.xsqy='" + area + "'";
		sql1 += " order by t.slrq desc";
		Map<String, Object> detailMap = iStatisticalService.findBySpringSql(
				sql1, p);

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
	@ResponseBody
	public DdcFlow getFlowDetailById(String id) {
		long flowId = Long.parseLong(id);
		DdcFlow ddcFlow = iEbikeService.getFlowById(flowId);
		String cysyName = iEbikeService.findByProPerties("CSYS",
				ddcFlow.getCysy());
		ddcFlow.setCysyName(cysyName);// 车身颜色
		String xsqyName = iEbikeService.findByProPerties("SSQY",
				ddcFlow.getXsqy());
		ddcFlow.setXsqyName(xsqyName);// 所属区域

		// 申报单位
		if (StringUtils.isNotBlank(ddcFlow.getZzjgdmzh())) {
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(Long
					.parseLong(ddcFlow.getZzjgdmzh()));
			if (ddcHyxhSsdw != null) {
				ddcFlow.setZzjgdmzhName(ddcHyxhSsdw.getDwmc());
			} else {
				ddcFlow.setZzjgdmzhName(null);
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
		// 受理资料
		String[] slzls = ddcFlow.getSlzl().split(",");
		List<String> slzllist = new ArrayList<>();
		for (String s : slzls) {
			String dmz = s;
			String dmlb = "BASQZL";
			String ss = iEbikeService.findByProPerties(dmlb, dmz);
			slzllist.add(ss);
		}
		ddcFlow.setSlzlList(slzllist);
		String showEbikeImg = parseUrl(ddcFlow.getVcEbikeImg());
		String showUser1Img = parseUrl(ddcFlow.getVcUser1Img());
		String showUser2Img = parseUrl(ddcFlow.getVcUser2Img());
		ddcFlow.setVcShowEbikeImg(showEbikeImg);
		ddcFlow.setVcShowUser1Img(showUser1Img);
		ddcFlow.setVcShowUser2Img(showUser2Img);
		return ddcFlow;
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
}
