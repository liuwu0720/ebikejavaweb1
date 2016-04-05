/**
 * 文件名：DustryAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月23日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

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

import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.JtUser;
import com.node.model.PicPath;
import com.node.service.IInDustryService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述：行业协会管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 下午5:03:00
 */
@Controller
@RequestMapping("/industryAction")
public class IndustryAction {
	@Autowired
	IInDustryService iInDustryService;

	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午5:04:21
	 */
	@RequestMapping("/getAll")
	public String getAll() {
		return "industry/industrys";
	}

	@RequestMapping("/getAllCompany")
	public String getAllCompany() {
		return "industry/companys";
	}

	/**
	 * 
	 * 方法描述：行业协会配额管理
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午1:47:44
	 */
	@RequestMapping("/getIndusryQuota")
	public String getIndusryQuota() {
		return "industry/industryQuota";
	}

	/**
	 * 
	 * 方法描述：单位配额管理
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午3:05:44
	 */
	@RequestMapping("/getCompanyQuota")
	public String getCompanyQuota() {
		return "industry/companyQuota";
	}

	/**
	 * 
	 * 方法描述：行业协会配额管理
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午2:01:38
	 */
	@RequestMapping("/queryIndusryQuota")
	@ResponseBody
	public Map<String, Object> queryIndusryQuota(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(DdcHyxhBase.class);
		hql.addOrderBy("id", "desc");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iInDustryService.queryByHql(hql);
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) resultMap
				.get("rows");

		for (int i = 0; i < mapList.size(); i++) {
			DdcHyxhBase ddcHyxhBase = (DdcHyxhBase) mapList.get(i);
			int lastpe = iInDustryService.getDdcHyxhBaseLastPe(ddcHyxhBase);// 已用配额
			ddcHyxhBase.setLastpe(lastpe);
		}
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：修改行业协会配额
	 * 
	 * @param hyxhsjzpe
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午2:56:41
	 */
	@RequestMapping("/saveOrUpdateQuota")
	public void saveOrUpdateQuota(String hyxhsjzpe, String id,
			HttpServletRequest request, HttpServletResponse response) {
		long dId = Long.parseLong(id);
		DdcHyxhBase ddcHyxhBase = iInDustryService.getDdcHyxhBase(dId);
		int pe = Integer.parseInt(hyxhsjzpe);
		ddcHyxhBase.setHyxhsjzpe(pe);
		try {
			iInDustryService.update(ddcHyxhBase);
			AjaxUtil.rendJson(response, true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "修改失败，系统错误");
		}
	}

	/**
	 * 
	 * 方法描述：修改行业协会所属单位的配额
	 * 
	 * @param dwpe
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午3:19:37
	 */
	@RequestMapping("/saveOrUpdateCompanyQuota")
	public void saveOrUpdateCompanyQuota(String dwpe, String id,
			HttpServletResponse response) {
		long dId = Long.parseLong(id);
		int ssdwDwpe = Integer.parseInt(dwpe);
		DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(dId);
		// 所属的行业协会
		DdcHyxhBase ddcHyxhBase = iInDustryService
				.getDdcHyxhBaseByCode(ddcHyxhSsdw.getHyxhzh());
		int minusNum = ssdwDwpe - ddcHyxhSsdw.getDwpe();

		if (ssdwDwpe == ddcHyxhSsdw.getDwpe()) {
			AjaxUtil.rendJson(response, true, "修改成功！");
			return;
		} else {
			if (ddcHyxhBase.getHyxhsjzpe() < minusNum) {
				AjaxUtil.rendJson(response, false, "配额不足，修改失败");
				return;
			} else {
				ddcHyxhSsdw.setDwpe(ssdwDwpe);
				iInDustryService.update(ddcHyxhSsdw);
				ddcHyxhBase.setHyxhsjzpe(ddcHyxhBase.getHyxhsjzpe() - minusNum);
				iInDustryService.update(ddcHyxhBase);
				AjaxUtil.rendJson(response, true, "修改成功！");
			}
		}
	}

	/**
	 * 
	 * 方法描述：根据ID查询行业协会详情
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午2:10:44
	 */
	@RequestMapping("/queryIndustryById")
	@ResponseBody
	public DdcHyxhBase queryIndustryById(String id) {
		long dId = Long.parseLong(id);
		DdcHyxhBase ddcHyxhBase = iInDustryService.getDdcHyxhBase(dId);
		int lastpe = iInDustryService.getDdcHyxhBaseLastPe(ddcHyxhBase);
		ddcHyxhBase.setLastpe(lastpe);
		return ddcHyxhBase;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param code
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 上午11:54:05
	 */
	@RequestMapping("/queryHxyHyxhBaseByCode")
	@ResponseBody
	public DdcHyxhBase queryHxyHyxhBaseByCode(String code) {
		DdcHyxhBase ddcHyxhBase = iInDustryService.getDdcHyxhBaseByCode(code);
		int lastpe = iInDustryService.getDdcHyxhBaseLastPe(ddcHyxhBase);
		ddcHyxhBase.setLastpe(lastpe);
		return ddcHyxhBase;
	}

	/**
	 * 
	 * 方法描述：查询所有行业协会
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:18:34
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(DdcHyxhBase.class);
		hql.addOrderBy("id", "desc");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iInDustryService.queryByHql(hql);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：查询所有行业协会所属单位
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 上午10:39:22
	 */
	@RequestMapping("/queryAllCompany")
	@ResponseBody
	public Map<String, Object> queryAllCompany(HttpServletRequest request,
			String hyxhzh, String dwmc) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(DdcHyxhSsdw.class);
		if (StringUtils.isNotBlank(hyxhzh)) {
			hql.addEqual("hyxhzh", hyxhzh);
		}
		if (StringUtils.isNotBlank(dwmc)) {
			hql.addLike("dwmc", dwmc);
		}

		hql.addOrderBy("id", "desc");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iInDustryService.queryByHql(hql);

		List<Map<String, Object>> mapList = (List<Map<String, Object>>) resultMap
				.get("rows");
		for (int i = 0; i < mapList.size(); i++) {
			DdcHyxhSsdw ddcHyxhSsdw = (DdcHyxhSsdw) mapList.get(i);
			DdcHyxhBase ddcHyxhBase = iInDustryService
					.getDdcHyxhBaseByCode(ddcHyxhSsdw.getHyxhzh());
			ddcHyxhSsdw.setHyxhzhName(ddcHyxhBase.getHyxhmc());
		}
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：删除行业协会
	 * 
	 * @param request
	 * @param response
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:32:29
	 */
	@RequestMapping("/del")
	public void del(HttpServletRequest request, HttpServletResponse response,
			String id) {
		try {
			long dId = Long.parseLong(id);
			iInDustryService.deleteById(dId);
			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
		}
	}

	/**
	 * 
	 * 方法描述：删除所属单位
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 上午11:05:25
	 */
	@RequestMapping("/delCompany")
	public void delCompany(HttpServletRequest request,
			HttpServletResponse response, String id) {
		try {
			long dId = Long.parseLong(id);
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(dId);
			ddcHyxhSsdw.setZt("0");
			iInDustryService.update(ddcHyxhSsdw);
			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
		}
	}

	/**
	 * 
	 * 方法描述：根据ID查询出单位详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午12:11:26
	 */
	@RequestMapping("/queryCompanyById")
	@ResponseBody
	public DdcHyxhSsdw queryCompanyById(HttpServletRequest request, String id) {
		long dId = Long.parseLong(id);
		DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(dId);
		DdcHyxhBase ddcHyxhBase = iInDustryService
				.getDdcHyxhBaseByCode(ddcHyxhSsdw.getHyxhzh());
		ddcHyxhSsdw.setHyxhzhName(ddcHyxhBase.getHyxhmc());
		String showImg = parseUrl(ddcHyxhSsdw.getVcPicPath());
		ddcHyxhSsdw.setVcShowPath(showImg);
		return ddcHyxhSsdw;
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
	 * 方法描述：
	 * 
	 * @param request
	 * @param response
	 * @param ddcHyxhBase
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:43:44
	 */
	@RequestMapping("/saveOrUpdate")
	public void saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, DdcHyxhBase ddcHyxhBase) {
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		ddcHyxhBase.setCjr(jtUser.getUserName());
		ddcHyxhBase.setCjbm(jtUser.getUserOrg());
		ddcHyxhBase.setCjrq(new Date());
		if (ddcHyxhBase.getId() == null) {
			try {
				ddcHyxhBase.setHyxhmm("123456");
				iInDustryService.save(ddcHyxhBase);
				AjaxUtil.rendJson(response, true, "新增成功，默认密码为123456");
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
			}
		} else {
			try {
				iInDustryService.update(ddcHyxhBase);
				AjaxUtil.rendJson(response, true, "操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
			}
		}
	}

	/**
	 * 
	 * 方法描述：行业协会所属单位
	 * 
	 * @param request
	 * @param response
	 * @param ddcHyxhSsdw
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 上午11:20:16
	 */
	@RequestMapping("/saveOrUpdateCompany")
	public void saveOrUpdateCompany(HttpServletRequest request,
			HttpServletResponse response, DdcHyxhSsdw ddcHyxhSsdw) {

		try {
			if (ddcHyxhSsdw.getId() == null) {
				iInDustryService.save(ddcHyxhSsdw);
			} else {
				iInDustryService.update(ddcHyxhSsdw);
			}
			AjaxUtil.rendJson(response, true, "操作成功！");

		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
		}
	}

	/**
	 * 
	 * 方法描述：重置
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午7:08:00
	 */
	@RequestMapping("/reset")
	public void reset(HttpServletRequest request, HttpServletResponse response,
			String id) {
		long dId = Long.parseLong(id);
		try {
			DdcHyxhBase ddcHyxhBase = iInDustryService.getDdcHyxhBase(dId);
			ddcHyxhBase.setHyxhmm("123456");
			iInDustryService.update(ddcHyxhBase);
			AjaxUtil.rendJson(response, true, "新增成功，密码为123456");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
		}
	}

	/**
	 * 
	 * 方法描述：ajax获取所有行业协会
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 上午11:10:52
	 */
	@RequestMapping("/getAllIndustry")
	@ResponseBody
	public List<DdcHyxhBase> getAllIndustry() {
		List<DdcHyxhBase> ddcHyxhBases = iInDustryService.getAllDDcHyxhBase();
		return ddcHyxhBases;
	}

	/**
	 * 
	 * 方法描述：根据行业协会账号查出所有公司
	 * 
	 * @param hyxhzh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 下午4:37:05
	 */
	@RequestMapping("/getDwmcByHyxh")
	@ResponseBody
	public List<DdcHyxhSsdw> getDwmcByHyxh(String hyxhzh) {
		List<DdcHyxhSsdw> ddcHyxhSsdws = iInDustryService
				.getAllDdcHyxhSsdwByHyxh(hyxhzh);
		return ddcHyxhSsdws;
	}
}
