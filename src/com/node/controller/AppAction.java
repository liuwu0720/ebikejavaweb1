/**
 * 文件名：AppAction.java
 * 版本信息：Version 1.0
 * 日期：2016年4月22日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.node.model.DdcApproveUser;
import com.node.model.DdcDaxxb;
import com.node.model.DdcDriver;
import com.node.model.DdcFlow;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.DdcHyxhSsdwclsb;
import com.node.model.DdcSjzd;
import com.node.model.JtRole;
import com.node.model.JtUser;
import com.node.model.PicPath;
import com.node.service.IEbikeService;
import com.node.service.IInDustryService;
import com.node.service.IJtUserService;
import com.node.util.AjaxUtil;
import com.node.util.SystemConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 类描述：霏霏地
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年4月22日 下午3:33:24
 */
@Controller
@RequestMapping("/appAction")
@Api(value = "appAction-api", description = "APP微信端相关接口", position = 5)
public class AppAction {

	@Autowired
	IEbikeService iEbikeService;

	@Autowired
	IInDustryService iInDustryService;

	@Autowired
	IJtUserService iJtUserService;

	@ApiIgnore
	@RequestMapping("/interface")
	public String getinterface() {
		return "system/apiIndex";
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param dabh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月22日 下午7:17:17
	 */
	@ApiOperation(value = "根据档案编号查询出车辆信息", notes = "二维码扫描档案编号返回的数据<br/>"
			+ "	private String dabh;// 档案编号4403 00000001<br/>"
			+ " private String cysyName;//车身颜色<br/>	"
			+ "private String djh;// 电机号<br/>	private String jtzz;// 脚踏装置（有、无）<br/>"
			+ "private String jsrxm1;// 驾驶人姓名1<br/>	 private String xb1;// 性别1<br/>"
			+ "private String sfzmhm1;// 身份证号码1<br/>private String lxdh1;// 联系电话1<br/>	"
			+ "private String jsrxm2;// 驾驶人姓名2	<br/>private String xb2;// 性别2<br/>"
			+ "	private String sfzmhm2;// 身份证号码2<br/>	private String lxdh2;// 联系电话2<br/>"
			+ "private String xsqyName;//行驶区域<br/>private String ztName;//车辆状态<br/>"
			+ "private String hyxhzhName;//协会名称<br/>private String ssdwName;//单位名称<br/>	"
			+ "private String vcShowEbikeImg;//车辆图片地址<br/>private String vcShowUser1Img;//驾驶人1图片地址<br/>"
			+ "private String vcShowUser2Img;//驾驶人2图片地址《br/>" + "", position = 5)
	@RequestMapping(value = "/getEbikeInfoByDabh", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getEbikeInfoByDabh(
			@ApiParam(value = "档案编号", required = true) @RequestParam("dabh") String dabh) {
		DdcDaxxb ddcDaxxb = iEbikeService.getDdcDaxxbByDabh(dabh);
		if (ddcDaxxb == null) {
			return AjaxUtil.getMapByNotException(false, null);
		} else {
			String cysyName = iEbikeService.findByProPerties("CSYS",
					ddcDaxxb.getCysy());

			ddcDaxxb.setCysyName(cysyName);// 车身颜色
			String xsqyName = iEbikeService.findByProPerties("SSQY",
					ddcDaxxb.getXsqy());
			ddcDaxxb.setXsqyName(xsqyName);// 所属区域

			String ztName = iEbikeService.findByProPerties("CLZT",
					ddcDaxxb.getZt());
			ddcDaxxb.setZtName(ztName);
			// 申报单位
			if (StringUtils.isNotBlank(ddcDaxxb.getSsdwId())) {
				DdcHyxhSsdw ddcHyxhSsdw = iInDustryService
						.getDdcHyxhSsdwById(Long.parseLong(ddcDaxxb.getSsdwId()));
				if (ddcHyxhSsdw != null) {
					ddcDaxxb.setSsdwName(ddcHyxhSsdw.getDwmc());
				} else {
					ddcDaxxb.setSsdwName(null);
				}
			}
			DdcHyxhBase ddcHyxhBase = iInDustryService
					.getDdcHyxhBaseByCode(ddcDaxxb.getHyxhzh());
			ddcDaxxb.setHyxhzhName(ddcHyxhBase.getHyxhmc());
			String showUser1Img = parseUrl(ddcDaxxb.getVcUser1Img());
			String showUser2Img = parseUrl(ddcDaxxb.getVcUser2Img());
			String showEbikeImg = parseUrl(ddcDaxxb.getVcEbikeImg());
			ddcDaxxb.setVcShowEbikeImg(showEbikeImg);
			ddcDaxxb.setVcShowUser1Img(showUser1Img);
			ddcDaxxb.setVcShowUser2Img(showUser2Img);
			return AjaxUtil.getMapByNotException(true, ddcDaxxb);
		}

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
	 * 
	 * @param lsh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年5月10日 下午3:23:13
	 */
	@ApiOperation(value = "根据流水号查询出车辆申报信息", notes = "车辆申报时系统会自动一个流水号，验证民警根据该流水号查出该申报车辆的详情信息)<br/>"
			+ "lsh:流水号；hyxhzhName：行业协会名称；ssdwName：单位名称<br/>"
			+ "ppxh:品牌型号；cysyName：车身颜色；jsrxm1：驾驶人1姓名<br/>"
			+ "xb1:性别1：0男 1女;sfzmhm1:身份证号码1<br/>"
			+ "lxdh1:联系电话1；jsrxm2：驾驶人2姓名；xb2：性别2：0男 1女<br/>"
			+ "sfzmhm2:身份证号码2；lxdh2：联系电话2；xsqyName：行驶区域<br/>"
			+ "vcUser1CardImg1Show;// 驾驶人1身份证照片正面<br/>"
			+ "vcUser1CardImg2Show;// 驾驶人1身份证照片反面<br/>"
			+ " vcUser2CardImg1Show;// 驾驶人2身份证照片正面<br/>"
			+ "vcUser2CardImg2Show;// 驾驶人2身份证照片反面<br/>"
			+ "vcEbikeInvoiceImgShow//飚车发票照片<br/>"
			+ "vcShowEbikeImg:车辆照片<br/>"
			+ "vcShowUser1Img:驾驶人1头像<br/>"
			+ "vcShowUser2Img:驾驶人2头像<br/>"
			+ "slIndex;// 受理审批顺序 0-等待行业协会审批 1-民警审批 2-支队领导审批" + "", position = 5)
	@RequestMapping(value = "/getEbikeInfoByLsh", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getEbikeInfoByLsh(
			@ApiParam(value = "流水号", required = true) @RequestParam("lsh") String lsh) {
		DdcHyxhSsdwclsb ddcHyxhSsdwclsb = iEbikeService
				.getDdcHyxhSsdwclsbByLsh(lsh);
		if (ddcHyxhSsdwclsb == null) {
			return AjaxUtil.getMapByNotException(false, null);
		} else {
			String cysyName = iEbikeService.findByProPerties("CSYS",
					ddcHyxhSsdwclsb.getCysy());

			ddcHyxhSsdwclsb.setCysyName(cysyName);// 车身颜色
			String xsqyName = iEbikeService.findByProPerties("SSQY",
					ddcHyxhSsdwclsb.getXsqy());
			ddcHyxhSsdwclsb.setXsqyName(xsqyName);// 行驶区域
			DdcHyxhBase ddcHyxhBase = iEbikeService
					.getHyxhByCode(ddcHyxhSsdwclsb.getHyxhzh());
			if (ddcHyxhBase != null) {
				ddcHyxhSsdwclsb.setHyxhzhName(ddcHyxhBase.getHyxhmc());
			}

			// 申报单位
			if (StringUtils.isNotBlank(ddcHyxhSsdwclsb.getSsdwId())) {
				DdcHyxhSsdw ddcHyxhSsdw = iInDustryService
						.getDdcHyxhSsdwById(Long.parseLong(ddcHyxhSsdwclsb
								.getSsdwId()));
				if (ddcHyxhSsdw != null) {
					ddcHyxhSsdwclsb.setSsdwName(ddcHyxhSsdw.getDwmc());
				} else {
					ddcHyxhSsdwclsb.setSsdwName(null);
				}
			}
			String showEbikeImg = parseUrl(ddcHyxhSsdwclsb.getVcEbikeImg());
			String showUser1Img = parseUrl(ddcHyxhSsdwclsb.getVcUser1Img());
			String showUser2Img = parseUrl(ddcHyxhSsdwclsb.getVcUser2Img());
			String vcUser1CardImg1Show = parseUrl(ddcHyxhSsdwclsb
					.getVcUser1CardImg1());
			String vcUser1CardImg2Show = parseUrl(ddcHyxhSsdwclsb
					.getVcUser1CardImg2());
			String vcUser2CardImg1Show = parseUrl(ddcHyxhSsdwclsb
					.getVcUser2CardImg1());
			String vcUser2CardImg2Show = parseUrl(ddcHyxhSsdwclsb
					.getVcUser1CardImg2());
			String vcEbikeInvoiceImgShow = parseUrl(ddcHyxhSsdwclsb
					.getVcEbikeInvoiceImg());
			ddcHyxhSsdwclsb.setVcShowEbikeImg(showEbikeImg);
			ddcHyxhSsdwclsb.setVcShowUser1Img(showUser1Img);
			ddcHyxhSsdwclsb.setVcShowUser2Img(showUser2Img);
			ddcHyxhSsdwclsb.setVcUser1CardImg1Show(vcUser1CardImg1Show);
			ddcHyxhSsdwclsb.setVcUser1CardImg2Show(vcUser1CardImg2Show);
			ddcHyxhSsdwclsb.setVcUser2CardImg1Show(vcUser2CardImg1Show);
			ddcHyxhSsdwclsb.setVcUser2CardImg2Show(vcUser2CardImg2Show);
			ddcHyxhSsdwclsb.setVcEbikeInvoiceImgShow(vcEbikeInvoiceImgShow);
			List<DdcSjzd> slzList = iEbikeService.getSjzdByDmlb("BASQZL");// 数据字典中所有的受理资料
			List<DdcSjzd> dbyyDdcSjzds = iEbikeService.getSjzdByDmlb("TBYY");// 数据字典中所有的退办原因
			Map<String, Object> map = new HashMap<>();
			map.put("slzList", slzList);
			map.put("dbyyDdcSjzds", dbyyDdcSjzds);
			map.put("ddcHyxhSsdwclsb", ddcHyxhSsdwclsb);
			return AjaxUtil.getMapByNotException(true, map);
		}
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param response
	 * @param userCode
	 * @param userPassword
	 * @param ccode
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年5月10日 下午10:06:31
	 */
	@ApiOperation(value = "用户登录接口", notes = "app用户登录接口 ", position = 5)
	@RequestMapping(value = "/loginByApp", method = RequestMethod.POST)
	public void loginByApp(
			HttpServletRequest request,
			HttpServletResponse response,
			@ApiParam(value = "用户名", required = true) @RequestParam("userCode") String userCode,
			@ApiParam(value = "用户密码", required = true) @RequestParam("userPassword") String userPassword) {

		JtUser jtUser = iJtUserService.findByAccount(userCode);

		if (jtUser == null || !jtUser.getUserPassword().equals(userPassword)) {

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
	 * 方法描述：审批
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param state
	 * @param note
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年5月10日 下午4:04:17
	 */
	@ApiOperation(value = "备案审批通过或拒绝", notes = "验车民警的手持终端通过查询流水号后用手持终端现场进行审批", position = 5)
	@RequestMapping(value = "/sureApproveRecord", method = RequestMethod.POST)
	public void sureApproveRecord(
			HttpServletRequest request,
			@ApiParam(value = "流水ID", required = true) @RequestParam("id") String id,
			@ApiParam(value = "slzl：如果备案通过审批的资料,多项选择，取ID值然后以‘,’号相连<br/>", required = false) @RequestParam(value = "slzl", required = false) String slzl,
			@ApiParam(value = "tbyy：如果备案拒绝，勾选的退办原因,多项选择，取ID值然后以‘,’号相连<br/>", required = false) @RequestParam(value = "tbyy", required = false) String tbyy,
			@ApiParam(value = "state:1是拒绝  0是同意") @RequestParam(value = "state", required = false) String state,
			@ApiParam(value = "note:审批备注信息") @RequestParam(value = "note", required = false) String note,
			HttpServletResponse response) {
		long dId = Long.parseLong(id);
		DdcHyxhSsdwclsb ddcHyxhSsdwclsb = iEbikeService
				.getDdcHyxhSsdwclsbById(dId);
		if (ddcHyxhSsdwclsb.getSlIndex() > 1) {
			AjaxUtil.rendJson(response, false, "该车辆已经由验车民警审批过了");
			return;
		}

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
			ddcHyxhSsdwclsb.setSlrq(new Date());// 办结日期
			ddcHyxhSsdwclsb.setSlbm(deptName);
			ddcHyxhSsdwclsb.setSynFlag(SystemConstants.SYSFLAG_UPDATE);
			ddcHyxhSsdwclsb.setTranDate(new Date());
			if (StringUtils.isNotBlank(tbyy)) {
				ddcHyxhSsdwclsb.setTbyy(tbyy);
			}
			// 业务流水
			DdcFlow ddcFlow = new DdcFlow();
			ddcFlow.setLsh(ddcHyxhSsdwclsb.getLsh());
			ddcFlow.setYwlx("A");
			ddcFlow.setYwyy("A");
			ddcFlow.setHyxhzh(ddcHyxhBase.getHyxhzh());
			ddcFlow.setSsdwId(ddcHyxhSsdwclsb.getSsdwId());
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
			ddcFlow.setVcEbikeImg(ddcHyxhSsdwclsb.getVcEbikeImg());
			ddcFlow.setVcEbikeInvoiceImg(ddcHyxhSsdwclsb.getVcEbikeInvoiceImg());
			ddcFlow.setVcUser1Img(ddcHyxhSsdwclsb.getVcUser1Img());
			ddcFlow.setVcUser2Img(ddcHyxhSsdwclsb.getVcUser2Img());
			ddcFlow.setVcUser1CardImg1(ddcHyxhSsdwclsb.getVcUser1CardImg1());
			ddcFlow.setVcUser1CardImg2(ddcHyxhSsdwclsb.getVcUser1CardImg2());
			ddcFlow.setVcUser2CardImg1(ddcHyxhSsdwclsb.getVcUser2CardImg1());
			ddcFlow.setVcUser2CardImg2(ddcHyxhSsdwclsb.getVcUser2CardImg2());

			ddcFlow.setSlbz(note);
			ddcFlow.setSlr(jtUser.getUserCode());
			ddcFlow.setSlrq(ddcHyxhSsdwclsb.getSqrq());// 申请时间
			ddcFlow.setSlbm(jtUser.getUserOrg());
			ddcFlow.setTbyy(tbyy);
			ddcFlow.setGdbz(note);
			ddcFlow.setGdr(jtUser.getUserCode());
			ddcFlow.setGdrq(new Date());
			ddcFlow.setGdyj(SystemConstants.NOTAGREE);
			ddcFlow.setYclb("0");
			ddcFlow.setSlIndex(ddcHyxhSsdwclsb.getSlIndex());
			ddcFlow.setSynFlag(SystemConstants.SYSFLAG_ADD);
			ddcFlow.setTranDate(new Date());
			ddcFlow.setVcTableName(SystemConstants.RECORDSBTABLE);
			ddcFlow.setiTableId(ddcHyxhSsdwclsb.getId());

			// 审批 人
			DdcApproveUser approveUser = new DdcApproveUser();
			String sql = "select SEQ_DDC_APPROVE_USER.nextval from dual";
			Object object = iEbikeService.getDateBySQL(sql);
			String seq = object.toString();
			String md = new SimpleDateFormat("yyMMdd").format(new Date());
			String approveNo = "N" + md + seq;// 生成审批编号
			approveUser.setApproveNo(approveNo);
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
			approveUser.setSysFlag(SystemConstants.SYSFLAG_ADD);
			approveUser.setTranDate(new Date());
			// 单位回收配额
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(Long
					.parseLong(ddcHyxhSsdwclsb.getSsdwId()));
			ddcHyxhSsdw.setDwpe(ddcHyxhSsdw.getDwpe() + 1);
			ddcHyxhSsdw.setSynFlag(SystemConstants.SYSFLAG_UPDATE);
			ddcHyxhSsdw.setTranDate(new Date());
			try {
				iEbikeService.updateDdcHyxhSsdwclsb(ddcHyxhSsdwclsb);
				iEbikeService.saveDdcApproveUser(approveUser);
				iEbikeService.saveDdcFlow(ddcFlow);
				iInDustryService.update(ddcHyxhSsdw);
				AjaxUtil.rendJson(response, true, "审批成功!");
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "审批失败!系统错误");
			}
		} else if (state.equals("0")) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 2);
			Date syrq = calendar.getTime();// 审验日期，当前时间+2年

			// 同意:如果当前审批人是最后审批人，则整个流程结束
			List<JtRole> jtRoles = iJtUserService.getAllApproveRoles();
			if (ddcHyxhSsdwclsb.getSlIndex() == jtRoles.size()) {
				ddcHyxhSsdwclsb.setSlr(jtUser.getUserName());
				ddcHyxhSsdwclsb.setSlyj(SystemConstants.AGREE);
				ddcHyxhSsdwclsb.setSlbz(note);
				ddcHyxhSsdwclsb.setSlzl(slzl);
				ddcHyxhSsdwclsb.setSlrq(new Date());
				ddcHyxhSsdwclsb.setSlbm(deptName);
				ddcHyxhSsdwclsb.setCphm(createCphm(ddcHyxhBase));
				ddcHyxhSsdwclsb.setSynFlag(SystemConstants.SYSFLAG_UPDATE);
				ddcHyxhSsdwclsb.setTranDate(new Date());

				// 审批人及审批状态
				DdcApproveUser approveUser = new DdcApproveUser();
				String sql = "select SEQ_DDC_APPROVE_USER.nextval from dual";
				Object object = iEbikeService.getDateBySQL(sql);
				String seq = object.toString();
				String md = new SimpleDateFormat("yyMMdd").format(new Date());
				String approveNo = "N" + md + seq;// 生成审批编号
				approveUser.setApproveNo(approveNo);
				approveUser.setUserName(jtUser.getUserName());// 姓名
				approveUser.setUserOrgname(jtUser.getUserOrgName());// 部门
				approveUser.setUserRoleName(jtUser.getUserRoleName());// 角色
				approveUser.setApproveIndex(ddcHyxhSsdwclsb.getSlIndex());
				approveUser.setApproveNote(note);
				approveUser.setApproveState(Integer.parseInt(state));
				approveUser.setApproveTable(SystemConstants.RECORDSBTABLE);
				approveUser.setApproveTableid(ddcHyxhSsdwclsb.getId());
				approveUser.setApproveTime(new Date());
				approveUser.setLsh(ddcHyxhSsdwclsb.getLsh());
				approveUser.setSysFlag(SystemConstants.SYSFLAG_ADD);
				approveUser.setTranDate(new Date());

				// 档案信息表
				DdcDaxxb daxxb = new DdcDaxxb();
				daxxb.setDabh(createDabh());
				daxxb.setYwlx("A");
				daxxb.setYwyy("A");
				daxxb.setHyxhzh(ddcHyxhSsdwclsb.getHyxhzh());
				daxxb.setSsdwId(ddcHyxhSsdwclsb.getSsdwId());
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
				daxxb.setSlzl(slzl);
				daxxb.setSlyj(SystemConstants.AGREE);
				daxxb.setSlbz(note);
				daxxb.setSlr(jtUser.getUserCode());
				daxxb.setSlrq(new Date());
				daxxb.setSlbm(jtUser.getUserOrg());
				daxxb.setGdyj(SystemConstants.AGREE);
				daxxb.setGdr(jtUser.getUserCode());
				daxxb.setGdrq(new Date());
				daxxb.setGdbm(jtUser.getUserOrg());
				daxxb.setVcEbikeImg(ddcHyxhSsdwclsb.getVcEbikeImg());
				daxxb.setVcEbikeInvoiceImg(ddcHyxhSsdwclsb
						.getVcEbikeInvoiceImg());
				daxxb.setVcUser1Img(ddcHyxhSsdwclsb.getVcUser1Img());
				daxxb.setVcUser2Img(ddcHyxhSsdwclsb.getVcUser2Img());
				daxxb.setVcUser1CardImg1(ddcHyxhSsdwclsb.getVcUser1CardImg1());
				daxxb.setVcUser1CardImg2(ddcHyxhSsdwclsb.getVcUser1CardImg2());
				daxxb.setVcUser2CardImg1(ddcHyxhSsdwclsb.getVcUser2CardImg1());
				daxxb.setVcUser2CardImg2(ddcHyxhSsdwclsb.getVcUser2CardImg2());

				daxxb.setSynFlag(SystemConstants.SYSFLAG_ADD);
				daxxb.setTranDate(new Date());

				// 业务流水
				DdcFlow ddcFlow = new DdcFlow();
				ddcFlow.setLsh(ddcHyxhSsdwclsb.getLsh());
				ddcFlow.setYwlx("A");
				ddcFlow.setYwyy("A");
				ddcFlow.setDabh(daxxb.getDabh());
				ddcFlow.setCphm(ddcHyxhSsdwclsb.getCphm());
				ddcFlow.setHyxhzh(ddcHyxhBase.getHyxhzh());
				ddcFlow.setSsdwId(ddcHyxhSsdwclsb.getSsdwId());
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
				ddcFlow.setSlrq(ddcHyxhSsdwclsb.getSqrq());
				ddcFlow.setSlIndex(ddcHyxhSsdwclsb.getSlIndex());
				ddcFlow.setSlbm(jtUser.getUserOrg());
				ddcFlow.setTbyy(tbyy);
				ddcFlow.setGdbz(note);
				ddcFlow.setGdr(jtUser.getUserCode());
				ddcFlow.setGdyj(SystemConstants.AGREE);
				ddcFlow.setGdrq(new Date());
				ddcFlow.setYclb("0");
				ddcFlow.setVcEbikeImg(ddcHyxhSsdwclsb.getVcEbikeImg());
				ddcFlow.setVcEbikeInvoiceImg(ddcHyxhSsdwclsb
						.getVcEbikeInvoiceImg());
				ddcFlow.setVcUser1Img(ddcHyxhSsdwclsb.getVcUser1Img());
				ddcFlow.setVcUser2Img(ddcHyxhSsdwclsb.getVcUser2Img());
				ddcFlow.setVcUser1CardImg1(ddcHyxhSsdwclsb.getVcUser1CardImg1());
				ddcFlow.setVcUser1CardImg2(ddcHyxhSsdwclsb.getVcUser1CardImg2());
				ddcFlow.setVcUser2CardImg1(ddcHyxhSsdwclsb.getVcUser2CardImg1());
				ddcFlow.setVcUser2CardImg2(ddcHyxhSsdwclsb.getVcUser2CardImg2());
				ddcFlow.setSynFlag(SystemConstants.SYSFLAG_ADD);
				ddcFlow.setTranDate(new Date());
				ddcFlow.setVcTableName(SystemConstants.RECORDSBTABLE);
				ddcFlow.setiTableId(ddcHyxhSsdwclsb.getId());
				try {
					iEbikeService.updateDdcHyxhSsdwclsb(ddcHyxhSsdwclsb);
					iEbikeService.saveDdcApproveUser(approveUser);
					iEbikeService.saveDdcFlow(ddcFlow);
					iEbikeService.saveDaxxb(daxxb);
					DdcDriver ddcDriver = new DdcDriver();
					ddcDriver.setDabh(daxxb.getDabh());
					ddcDriver.setDaid(daxxb.getId());
					ddcDriver.setXb(daxxb.getXb1());
					ddcDriver.setJsrxm(daxxb.getJsrxm1());
					ddcDriver.setLxdh(daxxb.getLxdh1());
					ddcDriver.setUserCode(daxxb.getLxdh1());
					ddcDriver.setUserPassword("123456");
					ddcDriver.setSfzhm(daxxb.getSfzmhm1());
					ddcDriver.setSynFlag(SystemConstants.SYSFLAG_ADD);
					ddcDriver.setTranDate(new Date());
					ddcDriver.setVcUserImg(daxxb.getVcUser1Img());
					iEbikeService.saveDdcDriver(ddcDriver);
					if (StringUtils.isNotBlank(daxxb.getJsrxm2())) {
						DdcDriver ddcDriver2 = new DdcDriver();
						ddcDriver2.setDabh(daxxb.getDabh());
						ddcDriver2.setDaid(daxxb.getId());
						ddcDriver2.setXb(daxxb.getXb2());
						ddcDriver2.setJsrxm(daxxb.getJsrxm2());
						ddcDriver2.setLxdh(daxxb.getLxdh2());
						ddcDriver2.setUserCode(daxxb.getLxdh2());
						ddcDriver2.setUserPassword("123456");
						ddcDriver2.setSfzhm(daxxb.getSfzmhm2());
						ddcDriver2.setSynFlag(SystemConstants.SYSFLAG_ADD);
						ddcDriver2.setTranDate(new Date());
						ddcDriver2.setVcUserImg(daxxb.getVcUser2Img());
						iEbikeService.saveDdcDriver(ddcDriver2);
					}
					AjaxUtil.rendJson(response, true, "保存成功!");
				} catch (Exception e) {
					e.printStackTrace();
					AjaxUtil.rendJson(response, false, "保存失败!系统错误");
				}
			} else {
				// 审批人指向下一个角色
				int nextRoleIndex = ddcHyxhSsdwclsb.getSlIndex() + 1;
				ddcHyxhSsdwclsb.setSlIndex(nextRoleIndex);
				ddcHyxhSsdwclsb.setSynFlag(SystemConstants.SYSFLAG_UPDATE);
				ddcHyxhSsdwclsb.setTranDate(new Date());
				DdcApproveUser approveUser = new DdcApproveUser();
				String sql = "select SEQ_DDC_APPROVE_USER.nextval from dual";
				Object object = iEbikeService.getDateBySQL(sql);
				String seq = object.toString();
				String md = new SimpleDateFormat("yyMMdd").format(new Date());
				String approveNo = "N" + md + seq;// 生成审批编号
				approveUser.setApproveNo(approveNo);
				approveUser.setUserName(jtUser.getUserName());// 姓名
				approveUser.setUserOrgname(jtUser.getUserOrgName());// 部门
				approveUser.setUserRoleName(jtUser.getUserRoleName());// 角色
				approveUser.setApproveIndex(ddcHyxhSsdwclsb.getSlIndex());
				approveUser.setApproveNote(note);
				approveUser.setApproveState(Integer.parseInt(state));
				approveUser.setApproveTable(SystemConstants.RECORDSBTABLE);
				approveUser.setApproveTableid(ddcHyxhSsdwclsb.getId());
				approveUser.setApproveTime(new Date());
				approveUser.setSysFlag(SystemConstants.SYSFLAG_ADD);
				approveUser.setTranDate(new Date());
				approveUser.setLsh(ddcHyxhSsdwclsb.getLsh());
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
}
