/**
 * 文件名：TreeNodeUtil.java
 * 版本信息：Version 1.0
 * 日期：2016年3月7日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.util;

import java.util.ArrayList;
import java.util.List;

import com.node.object.JsonTreeData;

/**
 * 类描述：描述: 获取树节点集合
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月7日 下午7:23:56
 */
public class TreeNodeUtil {
	/**
	 * @Title: getfatherNode
	 * @Description 方法描述: 父节点
	 * @param 设定文件
	 *            ： @param treeDataList
	 * @param 设定文件
	 *            ： @return
	 * @return 返回类型：List<JsonTreeData>
	 * @throws
	 * @date 最后修改时间：2015年6月9日 下午6:39:26
	 */
	public final static List<JsonTreeData> getfatherNode(
			List<JsonTreeData> treeDataList) {
		List<JsonTreeData> newTreeDataList = new ArrayList<JsonTreeData>();
		for (JsonTreeData jsonTreeData : treeDataList) {
			if (jsonTreeData.getPid().equals("0")) {
				// 获取父节点下的子节点
				jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(),
						treeDataList));
				jsonTreeData.setState("open");
				newTreeDataList.add(jsonTreeData);
			}
		}
		return newTreeDataList;
	}

	/**
	 * @Title: getChildrenNode
	 * @Description 方法描述: 子节点
	 * @param 设定文件
	 *            ： @param pid
	 * @param 设定文件
	 *            ： @param treeDataList
	 * @param 设定文件
	 *            ： @return
	 * @return 返回类型：List<JsonTreeData>
	 * @throws
	 * @date 最后修改时间：2015年6月9日 下午6:39:50
	 */
	private final static List<JsonTreeData> getChildrenNode(String pid,
			List<JsonTreeData> treeDataList) {
		List<JsonTreeData> newTreeDataList = new ArrayList<JsonTreeData>();
		for (JsonTreeData jsonTreeData : treeDataList) {
			if (jsonTreeData.getPid() == null)
				continue;
			// 这是一个子节点
			if (jsonTreeData.getPid().equals(pid)) {
				// 递归获取子节点下的子节点
				jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(),
						treeDataList));
				jsonTreeData.setState("closed");
				newTreeDataList.add(jsonTreeData);
			} else {
				jsonTreeData.setState("open");
			}
		}
		return newTreeDataList;
	}
}
