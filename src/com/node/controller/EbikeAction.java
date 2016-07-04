/**
 * 文件名：EbikeAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月24日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.node.model.DdcDaxxb;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.DdcSjzd;
import com.node.model.JtUser;
import com.node.model.PicPath;
import com.node.object.JtViewDept;
import com.node.service.IEbikeService;
import com.node.service.IInDustryService;
import com.node.service.IJtUserService;
import com.node.util.AjaxUtil;
import com.node.util.ExcelUtil;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述 电动车档案管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月24日 下午1:52:24
 */
@ApiIgnore
@Controller
@RequestMapping("/ebikeAction")
public class EbikeAction {

	@Autowired
	IEbikeService iEbikeService;

	@Autowired
	IInDustryService iInDustryService;

	@Autowired
	IJtUserService iJtUserService;

	/**
	 * 
	 * 方法描述：档案查询页面
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午1:54:16
	 */
	@RequestMapping("/getAll")
	public String getAll() {
		return "archives/ebikeQuery";
	}

	/**
	 * 
	 * 方法描述：档案更正
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午4:42:51
	 */
	@RequestMapping("/getAllupdate")
	public String getAllupdate() {
		return "archives/ebikeUpdate";
	}

	/**
	 * 
	 * 方法描述：档案补订
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午6:30:34
	 */
	@RequestMapping("/getAlladd")
	public String getAlladd() {
		return "archives/ebikeAdd";
	}

	/**
	 * 
	 * 方法描述：档案查询
	 * 
	 * @param request
	 * @param djh
	 * @param cphm
	 * @param jsrxm1
	 * @param dabh
	 * @param sfzhm1
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午2:00:31
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request, String djh,
			String zt, String dwmcId, String hyxhzh, String flag, String cphm,
			String jsrxm1, String dabh, String sfzhm1, String xsqy,
			HttpServletResponse response) {

		Page p = ServiceUtil.getcurrPage(request);

		String sql = "select A.ID,A.DABH,A.CPHM,A.DJH,A.JSRXM1,A.GDYJ,A.SFZMHM1,A.SLYJ,  A.hyxhzh,A.SSDWID, A.SLRQ,"
				+ " (select b.hyxhmc from ddc_hyxh_base b where b.hyxhzh=a.hyxhzh and rownum=1) as hyxhmc,"
				+ "(SELECT S.DWMC FROM DDC_HYXH_SSDW S WHERE S.ID=A.SSDWID and rownum=1 ) AS DWMC,"
				+ "(select d.DMMS1 from ddc_sjzd d where d.dmz=a.xsqy and d.dmlb='SSQY'  and rownum=1) as xsqy, "
				+ "(SELECT D.DMMS1 FROM DDC_SJZD D WHERE D.DMZ=A.ZT AND D.DMLB='CLZT'  and rownum=1)AS ZT from DDC_DAXXB A where 1=1 ";

		if (StringUtils.isNotBlank(flag)) {
			// 档案更正
			if (flag.equals("update")) {
				sql += " and a.zt!='E'";// E-注销
			}
		}
		// 状态
		if (StringUtils.isNotBlank(zt)) {
			sql += " and a.zt = '" + zt + "'";
		}

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
			sql += " and a.cphm like '%" + cphm + "%'";
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
	 * 方法描述：导出excel
	 * 
	 * @param titleArr
	 *            标头中文列名
	 * @param keysArr
	 *            列名的英文(对应表中的字段)
	 * @param content
	 *            需要导出的数据内容
	 * @version: 1.0
	 * @author: Daniel Zou
	 * @version: 2016年4月14日 下午2:00:31
	 */
	@RequestMapping("/exportExcel")
	public String exportExcel(String content, String[] titleArr,
			String[] keysArr, String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(content);
		// 创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = ExcelUtil.getWorkBook(titleArr, keysArr, jsonArray,
				fileName);
		response.setContentType("application/vnd.ms-excel;");
		fileName = fileName + ".xls";
		fileName = new String(fileName.getBytes(), "iso8859-1");
		response.setHeader("content-disposition", "attachment; filename="
				+ fileName); // 设定输出文件头
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		ServletOutputStream out = response.getOutputStream();
		byte[] b = new byte[1024];
		int length;
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		while ((length = is.read(b)) > 0) {
			out.write(b, 0, length);
		}
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 方法描述：
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: Daniel Zou
	 * @version: 2016年3月24日 下午4:54:58
	 */
	@RequestMapping("/queryDdcDaxxbById")
	@ResponseBody
	public DdcDaxxb queryDdcDaxxbById(String id, HttpServletRequest request,
			HttpServletResponse response) {
		long did = Long.parseLong(id);
		DdcDaxxb ddcDaxxb = iEbikeService.getById(did);
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
		if (StringUtils.isNotBlank(ddcDaxxb.getSsdwId())) {
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(Long
					.parseLong(ddcDaxxb.getSsdwId()));
			if (ddcHyxhSsdw != null) {
				ddcDaxxb.setSsdwName(ddcHyxhSsdw.getDwmc());
			} else {
				ddcDaxxb.setSsdwName(null);
			}
		}

		String showEbikeImg = parseUrl(ddcDaxxb.getVcEbikeImg());
		String showUser1Img = parseUrl(ddcDaxxb.getVcUser1Img());
		String showUser2Img = parseUrl(ddcDaxxb.getVcUser2Img());
		ddcDaxxb.setVcShowEbikeImg(showEbikeImg);
		ddcDaxxb.setVcShowUser1Img(showUser1Img);
		ddcDaxxb.setVcShowUser2Img(showUser2Img);
		return ddcDaxxb;
	}

	/**
	 * 
	 * 方法描述：查看详情页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月18日 上午9:53:42
	 */
	@RequestMapping("/queryInfoById")
	public String queryInfoById(String id, HttpServletRequest request) {
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
		if (StringUtils.isNotBlank(ddcDaxxb.getSsdwId())) {
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(Long
					.parseLong(ddcDaxxb.getSsdwId()));
			if (ddcHyxhSsdw != null) {
				ddcDaxxb.setSsdwName(ddcHyxhSsdw.getDwmc());
			} else {
				ddcDaxxb.setSsdwName(null);
			}
		}
		// 业务类型
		String ywlxName = iEbikeService.findByProPerties("YWLX",
				ddcDaxxb.getYwlx());
		ddcDaxxb.setYwlxName(ywlxName);

		DdcHyxhBase ddcHyxhBase = iInDustryService
				.getDdcHyxhBaseByCode(ddcDaxxb.getHyxhzh());// 行业协会账号
		ddcDaxxb.setHyxhzhName(ddcHyxhBase.getHyxhmc());
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
		String vcUser1CardImg1Show = parseUrl(ddcDaxxb.getVcUser1CardImg1());
		String vcUser1CardImg2Show = parseUrl(ddcDaxxb.getVcUser1CardImg2());
		String vcUser2CardImg1Show = parseUrl(ddcDaxxb.getVcUser2CardImg1());
		String vcUser2CardImg2Show = parseUrl(ddcDaxxb.getVcUser2CardImg2());
		String vcEbikeInvoiceImgShow = parseUrl(ddcDaxxb.getVcEbikeInvoiceImg());
		String vcUser1WorkImgShow = parseUrl(ddcDaxxb.getVcUser1WorkImg());
		String vcUser2WorkImgShow = parseUrl(ddcDaxxb.getVcUser2WorkImg());
		String vcQualifiedImgShow = parseUrl(ddcDaxxb.getVcQualifiedImg());
		String vcEbikeInsuranceImgShow = parseUrl(ddcDaxxb.getVcEbikeInsuranceImg());
		
		ddcDaxxb.setVcUser1WorkImgShow(vcUser1WorkImgShow);
		ddcDaxxb.setVcUser2WorkImgShow(vcUser2WorkImgShow);
		ddcDaxxb.setVcQualifiedImgShow(vcQualifiedImgShow);
		ddcDaxxb.setVcEbikeInsuranceImgShow(vcEbikeInsuranceImgShow);
		
		ddcDaxxb.setVcShowEbikeImg(showEbikeImg);
		ddcDaxxb.setVcShowUser1Img(showUser1Img);
		ddcDaxxb.setVcShowUser2Img(showUser2Img);
		ddcDaxxb.setVcUser1CardImg1Show(vcUser1CardImg1Show);
		ddcDaxxb.setVcUser1CardImg2Show(vcUser1CardImg2Show);
		ddcDaxxb.setVcUser2CardImg1Show(vcUser2CardImg1Show);
		ddcDaxxb.setVcUser2CardImg2Show(vcUser2CardImg2Show);
		ddcDaxxb.setVcEbikeInvoiceImgShow(vcEbikeInvoiceImgShow);
		request.setAttribute("ddcDaxxb", ddcDaxxb);
		request.setAttribute("slzls", slzls);
		return "archives/ebikedetail";
	}

	/**
	 * 
	 * 方法描述：档案信息更改
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月15日 下午8:04:38
	 */
	@RequestMapping("/updateDaxxb")
	public String updateDaxxb(String id, HttpServletRequest request) {
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
		if (StringUtils.isNotBlank(ddcDaxxb.getSsdwId())) {
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(Long
					.parseLong(ddcDaxxb.getSsdwId()));
			if (ddcHyxhSsdw != null) {
				ddcDaxxb.setSsdwName(ddcHyxhSsdw.getDwmc());
			} else {
				ddcDaxxb.setSsdwName(null);
			}
		}
		// 业务类型
		String ywlxName = iEbikeService.findByProPerties("YWLX",
				ddcDaxxb.getYwlx());
		ddcDaxxb.setYwlxName(ywlxName);

		DdcHyxhBase ddcHyxhBase = iInDustryService
				.getDdcHyxhBaseByCode(ddcDaxxb.getHyxhzh());// 行业协会账号
		ddcDaxxb.setHyxhzhName(ddcHyxhBase.getHyxhmc());
		// 用户
		// JtUser jtUser =
		// iJtUserService.getJtUserByUserCode(ddcDaxxb.getSlr());
		// ddcDaxxb.setSlrName(jtUser.getUserName());
		// 部门
		// JtViewDept jtViewDept = iJtUserService.getJtDeptByOrg(ddcDaxxb
		// .getSlbm());
		// ddcDaxxb.setSlbmName(jtViewDept.getOrgName());

		// List<DdcSjzd> slzls =
		// iEbikeService.getSelectSlzl(ddcDaxxb.getSlzl());// 选中的退办原因
		String showEbikeImg = parseUrl(ddcDaxxb.getVcEbikeImg());
		String showUser1Img = parseUrl(ddcDaxxb.getVcUser1Img());
		String showUser2Img = parseUrl(ddcDaxxb.getVcUser2Img());
		String vcUser1CardImg1Show = parseUrl(ddcDaxxb.getVcUser1CardImg1());
		String vcUser1CardImg2Show = parseUrl(ddcDaxxb.getVcUser1CardImg2());
		String vcUser2CardImg1Show = parseUrl(ddcDaxxb.getVcUser2CardImg1());
		String vcUser2CardImg2Show = parseUrl(ddcDaxxb.getVcUser2CardImg2());
		String vcEbikeInvoiceImgShow = parseUrl(ddcDaxxb.getVcEbikeInvoiceImg());
		
		String vcUser1WorkImgShow = parseUrl(ddcDaxxb.getVcUser1WorkImg());
		String vcUser2WorkImgShow = parseUrl(ddcDaxxb.getVcUser2WorkImg());
		String vcQualifiedImgShow = parseUrl(ddcDaxxb.getVcQualifiedImg());
		String vcEbikeInsuranceImgShow = parseUrl(ddcDaxxb.getVcEbikeInsuranceImg());
		
		ddcDaxxb.setVcUser1WorkImgShow(vcUser1WorkImgShow);
		ddcDaxxb.setVcUser2WorkImgShow(vcUser2WorkImgShow);
		ddcDaxxb.setVcQualifiedImgShow(vcQualifiedImgShow);
		ddcDaxxb.setVcEbikeInsuranceImgShow(vcEbikeInsuranceImgShow);
		ddcDaxxb.setVcShowEbikeImg(showEbikeImg);
		ddcDaxxb.setVcShowUser1Img(showUser1Img);
		ddcDaxxb.setVcShowUser2Img(showUser2Img);
		ddcDaxxb.setVcUser1CardImg1Show(vcUser1CardImg1Show);
		ddcDaxxb.setVcUser1CardImg2Show(vcUser1CardImg2Show);
		ddcDaxxb.setVcUser2CardImg1Show(vcUser2CardImg1Show);
		ddcDaxxb.setVcUser2CardImg2Show(vcUser2CardImg2Show);
		ddcDaxxb.setVcEbikeInvoiceImgShow(vcEbikeInvoiceImgShow);
		request.setAttribute("ddcDaxxb", ddcDaxxb);
		// request.setAttribute("slzls", slzls);
		return "archives/ebikeUpdateDetail";
	}

	/**
	 * 
	 * 方法描述：查看二维码详情页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月18日 上午9:53:42
	 */
	@RequestMapping("/queryQRCodeById")
	public String queryQRCodeById(String id, HttpServletRequest request) {
		long sbId = Long.parseLong(id);
		DdcDaxxb ddcDaxxb = iEbikeService.getById(sbId);
		String cysyName = iEbikeService.findByProPerties("CSYS",
				ddcDaxxb.getCysy());

		ddcDaxxb.setCysyName(cysyName);// 车身颜色
		String xsqyName = iEbikeService.findByProPerties("SSQY",
				ddcDaxxb.getXsqy());
		ddcDaxxb.setXsqyName(xsqyName);// 所属区域
		// 状态
		String ztName = iEbikeService
				.findByProPerties("CLZT", ddcDaxxb.getZt());
		ddcDaxxb.setZtName(ztName);
		// 申报单位
		if (StringUtils.isNotBlank(ddcDaxxb.getSsdwId())) {
			DdcHyxhSsdw ddcHyxhSsdw = iInDustryService.getDdcHyxhSsdwById(Long
					.parseLong(ddcDaxxb.getSsdwId()));
			if (ddcHyxhSsdw != null) {
				ddcDaxxb.setSsdwName(ddcHyxhSsdw.getDwmc());
			} else {
				ddcDaxxb.setSsdwName(null);
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

		// 受理人
		JtUser jtUser = iJtUserService.getJtUserByUserCode(ddcDaxxb.getSlr());
		ddcDaxxb.setSlrName(jtUser.getUserName());
		// 受理部门
		JtViewDept jtViewDept = iJtUserService.getJtDeptByOrg(jtUser
				.getUserOrg());
		ddcDaxxb.setSlbm(jtViewDept.getOrgName());
		String showEbikeImg = parseUrl(ddcDaxxb.getVcEbikeImg());
		String showUser1Img = parseUrl(ddcDaxxb.getVcUser1Img());
		String showUser2Img = parseUrl(ddcDaxxb.getVcUser2Img());
		ddcDaxxb.setVcShowEbikeImg(showEbikeImg);
		ddcDaxxb.setVcShowUser1Img(showUser1Img);
		ddcDaxxb.setVcShowUser2Img(showUser2Img);
		request.setAttribute("ddcDaxxb", ddcDaxxb);
		return "archives/ebikeQRCodeDetail";
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
	 * 方法描述：查询出所有行驶区域
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午4:19:06
	 */
	@RequestMapping("/getArea")
	@ResponseBody
	public List<DdcSjzd> getArea() {
		String dmlb = "SSQY";
		List<DdcSjzd> ddcSjzds = iEbikeService.getAllSjzdByDmlb(dmlb);
		return ddcSjzds;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 上午10:58:32
	 */
	@RequestMapping("/getDdcSjzds")
	@ResponseBody
	public List<DdcSjzd> getDdcSjzds(HttpServletRequest request) {
		String dmlb = request.getParameter("dmlb");
		List<DdcSjzd> ddcSjzds = iEbikeService.getAllSjzdByDmlb(dmlb);
		return ddcSjzds;
	}

	/**
	 * 
	 * 方法描述：ajax查询所有车身颜色
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月15日 下午2:21:13
	 */
	@RequestMapping("/getAllColorsAjax")
	@ResponseBody
	public List<DdcSjzd> getAllColorsAjax(HttpServletRequest request,
			HttpServletResponse response) {
		String dmlb = "CSYS";
		List<DdcSjzd> ddcSjzds = iEbikeService.getAllSjzdByDmlb(dmlb);
		return ddcSjzds;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param response
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午5:43:28
	 */
	@RequestMapping("/saveOrUpdate")
	public void saveOrUpdate(
			HttpServletRequest request,
			DdcDaxxb ddcDaxxb1,
			@RequestParam(value = "file_upload", required = false) MultipartFile fileupload,
			@RequestParam(value = "file_upload1", required = false) MultipartFile file_upload1,
			@RequestParam(value = "file_upload2", required = false) MultipartFile file_upload2,
			HttpServletResponse response) {

		try {
			DdcDaxxb ddcDaxxb = iEbikeService
					.getDdcDaxxbById(ddcDaxxb1.getId());
			ddcDaxxb.setCphm(ddcDaxxb1.getCphm());
			ddcDaxxb.setCysy(ddcDaxxb1.getCysy());
			ddcDaxxb.setDjh(ddcDaxxb1.getDjh());
			ddcDaxxb.setJtzz(ddcDaxxb1.getJtzz());
			ddcDaxxb.setJsrxm1(ddcDaxxb1.getJsrxm1());
			ddcDaxxb.setJsrxm2(ddcDaxxb1.getJsrxm2());
			ddcDaxxb.setXb1(ddcDaxxb1.getXb1());
			ddcDaxxb.setXb2(ddcDaxxb1.getXb2());
			ddcDaxxb.setSfzmhm1(ddcDaxxb1.getSfzmhm1());
			ddcDaxxb.setSfzmhm2(ddcDaxxb1.getSfzmhm2());
			ddcDaxxb.setLxdh1(ddcDaxxb1.getLxdh1());
			ddcDaxxb.setLxdh2(ddcDaxxb1.getLxdh2());
			ddcDaxxb.setXsqy(ddcDaxxb1.getXsqy());
			ddcDaxxb.setBz(ddcDaxxb1.getBz());
			ddcDaxxb.setSynFlag(SystemConstants.SYSFLAG_UPDATE);
			ddcDaxxb.setTranDate(new Date());
			/*
			 * String ebike_jpgPath = uploadImg(request, fileupload);// 上传车身照片
			 * if (StringUtils.isNotBlank(ebike_jpgPath)) {
			 * ddcDaxxb.setVcEbikeImg(ebike_jpgPath); } else {
			 * ddcDaxxb.setVcEbikeImg(ddcDaxxb.getVcEbikeImg()); }
			 * 
			 * String vcUser1_img = uploadImg(request, file_upload1);// 上传驾驶人1照片
			 * if (StringUtils.isNotBlank(vcUser1_img)) {
			 * ddcDaxxb.setVcUser1Img(vcUser1_img); } else {
			 * ddcDaxxb.setVcUser1Img(ddcDaxxb.getVcUser1Img()); }
			 * 
			 * String vcUser2_img = uploadImg(request, file_upload2);// 上传驾驶人2照片
			 * if (StringUtils.isNotBlank(vcUser2_img)) {
			 * ddcDaxxb.setVcUser2Img(vcUser2_img); } else {
			 * ddcDaxxb.setVcUser2Img(ddcDaxxb.getVcUser2Img()); }
			 */
			iEbikeService.updateDdcDaxxb(ddcDaxxb);

			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "系统错误，操作失败");
		}
	}

	/**
	 * 方法描述：
	 * 
	 * @param request
	 * @param fileupload
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月15日 下午3:15:24
	 */
	private String uploadImg(HttpServletRequest request, MultipartFile file)
			throws FileNotFoundException, IOException {
		if (!file.isEmpty()) {
			PicPath imgPath = iInDustryService
					.getImgPathById(SystemConstants.PIC_IMG);
			String source = imgPath.getVcAddpath();// 图片保存路径
			SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
			source = source + "/" + format.format(new Date());
			if (!source.endsWith("/")) {
				source += "/";
			}
			if (StringUtils.isBlank(source)) {
				System.out.println("source路径查不到！");
				return null;
			}
			String path = source;
			String jpgPath = UUID.randomUUID() + file.getOriginalFilename();

			File pathFile = new File(path, jpgPath);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}

			path += jpgPath;

			file.transferTo(pathFile);

			return format.format(new Date()) + "/" + jpgPath;
		} else {
			return null;
		}
	}
}
