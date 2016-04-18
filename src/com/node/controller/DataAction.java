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
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			HttpServletResponse response, RedirectAttributes attributes)
			throws IOException {
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
}
