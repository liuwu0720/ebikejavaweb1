/**
 * 文件名：DataAction.java
 * 版本信息：Version 1.0
 * 日期：2016年4月14日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.node.model.FileRecord;
import com.node.model.PicPath;
import com.node.service.IDataService;
import com.node.service.IEbikeService;
import com.node.service.IInDustryService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述：数据导出
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年4月14日 下午4:29:50
 */
@ApiIgnore
@Controller
@RequestMapping("/dataAction")
public class DataAction {

	@Autowired
	IEbikeService iEbikeService;

	@Autowired
	IDataService iDataService;

	@Autowired
	IInDustryService iInDustryService;

	@RequestMapping("/getAll")
	public String export() {
		return "system/exportData";
	}

	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(FileRecord.class);
		hql.addOrderBy("id", "desc");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iEbikeService.queryFileRecordByHql(hql);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param attributes
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月17日 下午7:07:00
	 * @throws IOException
	 */
	@RequestMapping("/importExcel")
	public void importExcel(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求...
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				String message = "success";
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file.isEmpty()) {
					AjaxUtil.rendJson(response, false, "上传的文件为空");
					return;
				}
				if (file.getOriginalFilename().endsWith("_N.xls")) {
					AjaxUtil.rendJson(response, false, "你导入的是内网的文件！");
					return;
				}
				message = iDataService.updateReadExcel(file.getInputStream());
				if (message.equalsIgnoreCase("success")) {
					try {
						PicPath picPath = iInDustryService
								.getImgPathById(SystemConstants.PIC_EXCEL);
						String getImagePath = picPath.getVcAddpath();// 保存路径
						String fileName = file.getOriginalFilename();
						File image2 = new File(getImagePath, fileName);
						file.transferTo(image2);
						FileRecord fileRecord = new FileRecord();
						fileRecord.setDateTime(new Date());
						fileRecord.setFileName(fileName);
						fileRecord.setFilePath(picPath.getVcParsePath() + "/"
								+ fileName);
						fileRecord.setFlag(0);// 0-导入 1-导出
						iDataService.saveFileRecord(fileRecord);
						AjaxUtil.rendJson(response, true, "成功");
					} catch (Exception e) {
						e.printStackTrace();
						AjaxUtil.rendJson(response, false, "失败!系统错误");
					}
				} else {
					AjaxUtil.rendJson(response, false, "操作失败，原因:" + message);
				}

			}
		}
	}

	/**
	 * 
	 * 方法描述：excel导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月21日 下午1:50:00
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel")
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response) throws IOException, WriteException {
		try {
			PicPath imgPath = iInDustryService
					.getImgPathById(SystemConstants.PIC_IMG);
			String path = imgPath.getVcAddpath();
			String fileName = createFileName() + ".xls";
			String filePath = path + "/" + fileName;// 写入
			String outPath = imgPath.getVcParsePath() + fileName;// 解析
			File pathFile = new File(filePath);
			WritableWorkbook wwb = null;
			pathFile.createNewFile();
			wwb = Workbook.createWorkbook(pathFile);
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 15,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			// WritableFont wfc1 = new WritableFont(WritableFont.ARIAL, 10,
			// WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
			// Colour.RED);
			WritableFont wfc2 = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcfFC = new WritableCellFormat(wfc);
			// wcfFC.setBackground(Colour.DARK_YELLOW); 背景
			// WritableCellFormat wcfFC1 = new WritableCellFormat(wfc1);
			WritableCellFormat wcfFC2 = new WritableCellFormat(wfc2);
			// wcfFC.setWrap(true);//自动换行
			wcfFC.setAlignment(Alignment.CENTRE);// 把水平对齐方式指定为居中
			wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
			/**
			 * 档案信息表，内网新增、或者修改
			 */
			WritableSheet ws = wwb.createSheet("DAXXB", 0);
			iDataService.createDaxxbExcel(wcfFC, wcfFC2, ws);
			/**
			 * 审批信息表 内网只新增
			 */
			WritableSheet ws2 = wwb.createSheet("ddc_approve_user", 1);
			iDataService.createApproveUsers(wcfFC, wcfFC2, ws2);
			/**
			 * 流水表DDCFLOW 内网只新增
			 */
			WritableSheet ws3 = wwb.createSheet("ddcflow", 2);
			iDataService.createDdcflows(wcfFC, wcfFC2, ws3);
			/**
			 * 配额申报 ddc_hyxh_basb 内网只修改
			 */
			WritableSheet ws4 = wwb.createSheet("ddc_hyxh_basb", 3);
			iDataService.createDdcHyxhBasb(wcfFC, wcfFC2, ws4);
			/**
			 * 行业协会 ddc_hyxh_base: 内网新增或修改
			 */
			WritableSheet ws5 = wwb.createSheet("ddc_hyxh_base", 4);
			iDataService.createDdcHyxhBase(wcfFC, wcfFC2, ws5);
			/**
			 * ddc_hyxh_ssdw: 内网只修改
			 */
			WritableSheet ws6 = wwb.createSheet("ddc_hyxh_ssdw", 5);
			iDataService.createDdcHyxhSsdw(wcfFC, wcfFC2, ws6);
			/**
			 * ddc_hyxh_ssdwclsb:内网只修改
			 */
			WritableSheet ws7 = wwb.createSheet("ddc_hyxh_ssdwclsb", 6);
			iDataService.createDdcHyxhSsdwClSb(wcfFC, wcfFC2, ws7);
			/**
			 * ddc_driver 司机，内网修改
			 */
			WritableSheet ws8 = wwb.createSheet("ddc_driver", 7);
			iDataService.createDdcDriver(wcfFC, wcfFC2, ws8);
			/**
			 * DDC_HMD 黑名单 内网新增或修改
			 */
			/*WritableSheet ws9 = wwb.createSheet("DDC_HMD ", 8);
			iDataService.createDdcHmd(wcfFC, wcfFC2, ws9);*/

			FileRecord fileRecord = new FileRecord();
			fileRecord.setFilePath(outPath);
			fileRecord.setFileName(fileName);
			fileRecord.setFlag(1);// 0-导入 1-导出
			fileRecord.setDateTime(new Date());
			iDataService.saveFileRecord(fileRecord);
			wwb.write();
			wwb.close();
			response.getWriter().print(outPath);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("2");
		}
		return null;
	}

	private String createFileName() {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());

		return formatDate + "_N";
	}
}
