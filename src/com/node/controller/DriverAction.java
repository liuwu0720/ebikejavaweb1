/**
  * 文件名：DriverAction.java
  * 版本信息：Version 1.0
  * 日期：2016年6月21日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.DdcDriver;
import com.node.model.PicPath;
import com.node.service.IDriverService;
import com.node.service.IInDustryService;
import com.node.util.AjaxUtil;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年6月21日 下午6:13:48 
 */
@Controller
@RequestMapping("/driverAction")
public class DriverAction {
	
	@Autowired
	IDriverService iDriverService;
	
	@Autowired
	IInDustryService iInDustryService;
	
	@RequestMapping("/getAll")
	public String getAll(){
		return "driver/driverlist";
	}
	
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request,HttpServletResponse response,String sfzhm, String jsrxm,Integer userStatus){
		Page p = ServiceUtil.getcurrPage(request);

		String sql="select id, jsrxm, xb, lxdh, syn_flag, tran_date, user_code, user_password, sfzhm, "
				+ "vcuser_img, vc_userworkimg, user_status, illeage_times, user_note, ssdwid, hyxhzh, "
				+ "vc_user_cardimg1, vc_user_cardimg2, xj_flag, xj_msg, xj_rq from ddc_driver where 1=1";
		
		if (StringUtils.isNotBlank(sfzhm)) {
			sql += " and sfzhm='"+sfzhm+"'";
		}
		if (StringUtils.isNotBlank(jsrxm)) {
			sql += " and jsrxm='"+jsrxm+"'";
		}
		if (userStatus != null) {
			sql += " and user_status="+userStatus+"";
		}else {
			sql += " and user_status = 0";
		}
		sql += " order by id desc";
		Page page = ServiceUtil.getcurrPage(request);
		Map<String, Object> resultMap = iDriverService.queryBySpringsql(sql,page);
		return resultMap;
	}
	/**
	 * 
	  * 方法描述：查看详情
	  * @param request
	  * @param id
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月21日 下午7:20:00
	 */
	@RequestMapping("/queryInfoById")
	public String queryInfoById(HttpServletRequest request, String id) {
		long driverId = Long.parseLong(id);
		DdcDriver ddcDriver = iDriverService.getDriverById(driverId);
		String hyxhName = iDriverService.getHyxhNameByHyxhzh(ddcDriver
				.getHyxhzh());
		String ssdwName = iDriverService.getDwmcById(ddcDriver.getSsdwId());
		request.setAttribute("hyxhName", hyxhName);
		request.setAttribute("ssdwName", ssdwName);
		ddcDriver.setVcShowUserImg(parseUrl(ddcDriver.getVcUserImg()));
		ddcDriver
				.setVcUserCardImg1Show(parseUrl(ddcDriver.getVcUserCardImg1()));
		ddcDriver
				.setVcUserCardImg2Show(parseUrl(ddcDriver.getVcUserCardImg2()));
		ddcDriver.setVcUserWorkImgShow(parseUrl(ddcDriver.getVcUserWorkImg()));
		request.setAttribute("ddcDriver", ddcDriver);
		return "driver/driverinfo";
	}
	
	/**
	 * 
	 * 方法描述：修改详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年6月16日 下午7:32:06
	 */
	@RequestMapping("/updateInfoById")
	public String updateInfoById(HttpServletRequest request, String id) {
		long driverId = Long.parseLong(id);
		DdcDriver ddcDriver = iDriverService.getDriverById(driverId);
		String hyxhName = iDriverService.getHyxhNameByHyxhzh(ddcDriver
				.getHyxhzh());
		String ssdwName = iDriverService.getDwmcById(ddcDriver.getSsdwId());
		request.setAttribute("hyxhName", hyxhName);
		request.setAttribute("ssdwName", ssdwName);
		ddcDriver.setVcShowUserImg(parseUrl(ddcDriver.getVcUserImg()));
		ddcDriver
				.setVcUserCardImg1Show(parseUrl(ddcDriver.getVcUserCardImg1()));
		ddcDriver
				.setVcUserCardImg2Show(parseUrl(ddcDriver.getVcUserCardImg2()));
		ddcDriver.setVcUserWorkImgShow(parseUrl(ddcDriver.getVcUserWorkImg()));
		request.setAttribute("ddcDriver", ddcDriver);
		return "driver/driverupdate";
	}
	
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
	  * @param request
	  * @param state
	  * @param response
	  * @param id 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月21日 下午7:50:59
	 */
	@RequestMapping("/sureApproveRecord")
	public void sureApproveRecord(HttpServletRequest request,String state,HttpServletResponse response,String id, String note) {
		//拒绝
		long driverId=Long.parseLong(id);
		DdcDriver ddcDriver = iDriverService.getDriverById(driverId);
		if(state.equals("1")){
			ddcDriver.setUserNote(note);
		}else if (state.equals("0")) {
			ddcDriver.setUserStatus(1);
		}
		ddcDriver.setSynFlag(SystemConstants.SYSFLAG_UPDATE);
		try {
			iDriverService.updateDdcDriver(ddcDriver);
			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "系统错误，请重试");
		}
	}
}
