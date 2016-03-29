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
			flowStatis.setCphm(objMap.get("CPHM").toString());
			flowStatis.setDjh(objMap.get("DJH").toString());
			flowStatis.setSlr(objMap.get("SLR") == null ? null : objMap.get(
					"SLR").toString());
			flowStatis.setSlbm(objMap.get("SLBM") == null ? null : objMap.get(
					"SLBM").toString());
			flowStatis.setSlrq(objMap.get("SLRQ").toString());
			String sql2 = "select jb from OA_DEPT_VIEW where org_id='"
					+ flowStatis.getSlbm() + "'";
			String jb = iJtUserDao.getDateBySQL(sql2).toString();
			// 根据级别查询出中队名称、大队名称
			if (jb.equals("2")) {
				flowStatis.setZdmc("无");// 中队名称

				String sql3 = "select org_name from OA_DEPT_VIEW where org_id='"
						+ flowStatis.getSlbm() + "'";
				String ddmc = iJtUserDao.getDateBySQL(sql3).toString();// 大队名称
				flowStatis.setDdmc(ddmc);

			} else if (jb.equals("3")) {
				String sql4 = "select org_name from OA_DEPT_VIEW where org_id='"
						+ flowStatis.getSlbm() + "'";
				String zdmc = iJtUserDao.getDateBySQL(sql4).toString();
				flowStatis.setZdmc(zdmc);
				String sql5 = "select org_name from OA_DEPT_VIEW where org_id='"
						+ getDept(flowStatis.getSlbm()) + "'";
				String ddmc = iJtUserDao.getDateBySQL(sql5).toString();
				flowStatis.setDdmc(ddmc);

			} else if (jb.equals("4")) {
				String sql6 = "select org_name from OA_DEPT_VIEW where org_id=(select up_org from xdda_dept_view where org_id='"
						+ flowStatis.getSlbm() + "')";
				String zdmc = iJtUserDao.getDateBySQL(sql6).toString();
				flowStatis.setZdmc(zdmc);
				String sql7 = "select org_name from OA_DEPT_VIEW where org_id='"
						+ getDept(flowStatis.getSlbm()) + "'";
				String ddmc = iJtUserDao.getDateBySQL(sql7).toString();
				flowStatis.setDdmc(ddmc);
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
			sb.append("(select count(*) ba from ddc_daxxb d where d.slbm in ");
			sb.append("(select org_id from OA_DEPT_VIEW start with org_id='"
					+ map.get("dcode")
					+ "' connect by prior org_id=up_org)) a,");
			sb.append("(select count(*) tb from ddc_flow f where f.slyj='1' and f.slbm in ");
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
