/**
 * 文件名：ResourceAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月4日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

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

import com.node.model.TResource;
import com.node.service.IResourceService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月4日 下午4:47:16
 */
@Controller
@RequestMapping("/resourceAction")
public class ResourceAction {
	@Autowired
	IResourceService iResourceService;

	/**
	 * 
	 * 方法描述：页面跳转
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午1:46:15
	 */
	@RequestMapping("/getResource")
	public String getResource() {
		return "system/resourcetree";

	}

	/**
	 * 
	 * 方法描述：easyui界面：获取所有菜单
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午1:30:12
	 */
	@RequestMapping("/queryAllResource")
	@ResponseBody
	public Map<String, Object> queryAllResource(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(TResource.class);
		hql.addOrderBy("iParent", "asc");
		hql.addOrderBy("nSort", "asc");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iResourceService.queryAllResource(hql);

		return resultMap;
	}

	/**
	 * 
	 * 方法描述：查出资源详情信息
	 * 
	 * @param request
	 * @param resourceId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月4日 下午7:59:53
	 */
	@RequestMapping("/getResourceByid")
	@ResponseBody
	public TResource getResourceByid(HttpServletRequest request,
			@RequestParam("resourceId") String resourceId) {
		TResource resource = iResourceService.get(Integer.parseInt(resourceId));

		return resource;

	}

	/**
	 * 
	 * 方法描述：ajax:获取所有父节点名称
	 * 
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午2:31:27
	 */
	@RequestMapping("/getParentResource")
	@ResponseBody
	public List<TResource> getParentResource() {
		List<TResource> pResources = iResourceService.getAllParentResource();
		return pResources;
	}

	/**
	 * 
	 * 方法描述：新增或编辑保存
	 * 
	 * @param tResource
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午2:51:06
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public void saveOrUpdate(TResource tResource, HttpServletResponse response) {
		try {

			if (tResource.getId() == null) {
				// 检查是否存在相同的菜单名称
				String messageString = iResourceService
						.findIsExisteName(tResource.getVcResourceName());
				if (messageString.equalsIgnoreCase("success")) {
					// 父节点为空则表根菜单
					if (StringUtils.isBlank(tResource.getVcParent())) {
						tResource.setiParent(SystemConstants.ROOT_PARENTID);
					} else {
						// 根据父节点的名称查出对应的ID
						int iParentId = iResourceService
								.getPidByResourceName(tResource.getVcParent());
						tResource.setiParent(iParentId);
					}
					try {
						iResourceService.save(tResource);
						AjaxUtil.rendJson(response, true, "操作成功");
					} catch (Exception e) {
						e.printStackTrace();

					}
				} else {
					AjaxUtil.rendJson(response, false, messageString);
				}

			} else {
				TResource beforeObj = iResourceService.getById(tResource
						.getId());
				String messageString = "success";
				// 编辑时如果和以前的名字一样，则不作验证
				if (!beforeObj.getVcResourceName().equals(
						tResource.getVcResourceName())) {
					messageString = iResourceService.findIsExisteName(tResource
							.getVcResourceName());

				}
				if (messageString.equalsIgnoreCase("success")) {
					// 父节点为空则表根菜单
					if (StringUtils.isBlank(tResource.getVcParent())) {
						tResource.setiParent(SystemConstants.ROOT_PARENTID);
					} else {
						// 根据父节点的名称查出对应的ID
						int iParentId = iResourceService
								.getPidByResourceName(tResource.getVcParent());
						tResource.setiParent(iParentId);
					}
					try {
						iResourceService.update(tResource);
						AjaxUtil.rendJson(response, true, "操作成功");
					} catch (Exception e) {
						e.printStackTrace();
						AjaxUtil.rendJson(response, false, "系统错误");
					}
				} else {
					AjaxUtil.rendJson(response, false, messageString);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "系统错误！");
		}
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午4:56:17
	 */
	@RequestMapping("/del")
	public void addRow(HttpServletRequest request, String id,
			HttpServletResponse response) {
		int tResourceId = Integer.parseInt(id);
		TResource tResource = iResourceService.get(tResourceId);
		tResource.setnEnable(SystemConstants.DISABLE);
		try {
			iResourceService.update(tResource);
			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "系统错误");
		}

	}
}
