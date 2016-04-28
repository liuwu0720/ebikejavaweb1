/**
 * 文件名：StatisticalServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月25日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IJtUserDao;
import com.node.object.FlowStatis;
import com.node.object.Statics;
import com.node.service.IStatisticalService;
import com.node.util.Page;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月25日 上午10:20:06
 */
@Service
public class StatisticalServiceImp implements IStatisticalService {
	@Autowired
	IJtUserDao iJtUserDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IStatisticalService#findBySpringSql(java.lang.String,
	 * com.node.util.Page)
	 */
	@Override
	public Map<String, Object> findBySpringSql(String string, Page p) {
		Map<String, Object> resultMap = iJtUserDao.getSpringSQL(string, p);
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) resultMap
				.get("rows");
		List<FlowStatis> flowStatisList = new ArrayList<>();
		for (int i = 0; i < mapList.size(); i++) {
			Map<String, Object> objMap = mapList.get(i);
			FlowStatis flowStatis = new FlowStatis();
			flowStatis.setId(Long.parseLong(objMap.get("ID").toString()));
			flowStatis.setDabh(objMap.get("DABH") == null ? null : objMap.get(
					"DABH").toString());
			flowStatis.setCphm(objMap.get("CPHM") == null ? null : objMap.get(
					"CPHM").toString());
			flowStatis.setDjh(objMap.get("DJH").toString());
			flowStatis.setSlr(objMap.get("SLR") == null ? null : objMap.get(
					"SLR").toString());
			flowStatis.setSlbm(objMap.get("SLBM") == null ? null : objMap.get(
					"SLBM").toString());
			flowStatis.setSlrq(objMap.get("SLRQ").toString());
			flowStatis.setHyxhmc(objMap.get("HYXHMC").toString());
			flowStatis.setDwmc(objMap.get("DWMC").toString());
			String sql2 = "";
			if (StringUtils.isNotBlank(flowStatis.getSlbm())) {
				sql2 = "select org_name from OA_DEPT_VIEW  start with org_id = '"
						+ flowStatis.getSlbm()
						+ "'  connect by prior  up_org=org_id ";
				List<?> depts = (List<?>) iJtUserDao.getDateBySQL(sql2, null);
				if (CollectionUtils.isNotEmpty(depts)) {
					// 4级
					if (depts.size() == 4) {
						flowStatis.setZdmc(depts.get(1).toString());
						flowStatis.setDdmc(depts.get(2).toString());
					}
					// 3
					if (depts.size() == 3) {
						flowStatis.setZdmc(depts.get(0).toString());
						flowStatis.setDdmc(depts.get(1).toString());
					}
					if (depts.size() == 2) {
						flowStatis.setZdmc("无");
						flowStatis.setDdmc(depts.get(0).toString());
					}
				}
			}
			flowStatisList.add(flowStatis);
		}

		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("total", resultMap.get("total"));
		newMap.put("rows", flowStatisList);
		return newMap;
	}

	/**
	 * 方法描述：根据登陆民警所属部门获取所属大队部门编号
	 * 
	 * @param slbm
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月25日 下午2:50:49
	 */
	private String getDept(String slbm) {
		String dept = null;
		String sql = "select * from OA_DEPT_VIEW where org_id='" + slbm
				+ "' and up_org='30015'";
		int count = iJtUserDao.getCountSQL(sql);
		if (count == 1) {
			dept = slbm;
			return dept;
		} else {
			String sql2 = "select up_org from OA_DEPT_VIEW where org_id='"
					+ slbm + "'";
			String ddbm = iJtUserDao.getDateBySQL(sql2).toString();
			return ddbm;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IStatisticalService#findBySpringSqlPage(java.lang.String
	 * , com.node.util.Page)
	 */
	@Override
	public Map<String, Object> findBySpringSqlPage(String string, Page p) {
		// TODO Auto-generated method stub
		return iJtUserDao.getSpringSQL(string, p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IStatisticalService#findTeam(java.util.List)
	 */
	@Override
	public Map<String, Object> findTeam(List<?> dlist) {
		Long bas = 0l;// 总备案量
		Long tbs = 0l;// 总退办量
		List<Statics> slist = new ArrayList<Statics>();
		for (int i = 0; i < dlist.size(); i++) {
			Map<?, ?> map = (Map<?, ?>) dlist.get(i);
			StringBuffer sb = new StringBuffer();
			sb.append("select a.ba,b.tb from ");
			sb.append("(select count(*) ba from ddc_daxxb d where d.zt !='E' and d.slbm in ");
			sb.append("(select org_id from OA_DEPT_VIEW start with org_id='"
					+ map.get("dcode")
					+ "' connect by prior org_id=up_org)) a,");
			sb.append("(select count(*) tb from ddc_flow f where f.slyj='1' and f.ywlx = 'A' and f.slbm in ");
			sb.append("(select org_id from OA_DEPT_VIEW start with org_id='"
					+ map.get("dcode") + "' connect by prior org_id=up_org)) b");
			Object[] obj = (Object[]) iJtUserDao.getDateBySQL(sb.toString());
			Statics stat = new Statics();
			stat.setEname(map.get("dcode").toString());
			stat.setCname(map.get("dname").toString());
			stat.setBa(obj[0].toString());
			stat.setTb(obj[1].toString());

			bas += Long.valueOf(obj[0].toString());
			tbs += Long.valueOf(obj[1].toString());

			slist.add(stat);
		}

		Statics stats = new Statics();
		stats.setEname("total");
		stats.setCname("总计");
		stats.setBa(String.valueOf(bas));
		stats.setTb(String.valueOf(tbs));
		slist.add(stats);

		Map<String, Object> newMap = new HashMap<>();
		newMap.put("rows", slist);
		newMap.put("total", slist.size());

		return newMap;
	}
}
