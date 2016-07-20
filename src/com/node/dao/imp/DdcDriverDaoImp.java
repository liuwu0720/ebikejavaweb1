/**
 * 文件名：DdcDriverDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年4月16日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.node.dao.IDdcDriverDao;
import com.node.model.DdcDriver;
import com.node.util.DateStrUtil;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年4月16日 下午7:15:51
 */
@Repository
public class DdcDriverDaoImp extends GenericHibernateDao<DdcDriver, Long>
		implements IDdcDriverDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.dao.IDdcDriverDao#findXjDriver()
	 */
	@Override
	public List<DdcDriver> findXjDriver() {
		String sql = "select id, jsrxm, xb, lxdh, syn_flag, tran_date, user_code, user_password, sfzhm, vcuser_img, vc_userworkimg, user_status, "
				+ "illeage_times, user_note, ssdwid, hyxhzh, vc_user_cardimg1, vc_user_cardimg2, xj_flag, xj_msg, xj_rq from ddc_driver where SYN_FLAG='ADD' ";
		Map<String, Object> maps = getSpringSQL(sql, null);
		List<Map<String, Object>> list = (List<Map<String, Object>>) maps
				.get("rows");
		List<DdcDriver> ddcDrivers = new ArrayList<DdcDriver>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			DdcDriver ddcDriver = new DdcDriver();
			ddcDriver.setId(Long.parseLong(map.get("ID").toString()));
			ddcDriver.setUserStatus(map.get("USER_STATUS") == null ? null
					: Integer.parseInt(map.get("USER_STATUS").toString()));
			ddcDriver.setXjFlag(map.get("XJ_FLAG") == null ? null : map.get(
					"XJ_FLAG").toString());
			ddcDriver.setXjMsg(map.get("XJ_MSG") == null ? null : map.get(
					"XJ_MSG").toString());
			ddcDriver.setXjRq(map.get("XJ_RQ") == null ? null : DateStrUtil
					.toDate(map.get("XJ_RQ").toString()));
			ddcDriver.setUserNote(map.get("USER_NOTE") == null ? null : map
					.get("USER_NOTE").toString());
			ddcDrivers.add(ddcDriver);
			/*
			 * ddcDriver.setHyxhzh(map.get("ID").toString());
			 * ddcDriver.setIlleagalTimes
			 * (map.get("ILLEAGE_TIMES")==null?null:Integer
			 * .parseInt(map.get("ILLEAGE_TIMES").toString()));
			 * ddcDriver.setJsrxm(map.get("JSRXM").toString());
			 * ddcDriver.setLxdh(map.get("LXDH").toString());
			 * ddcDriver.setSfzhm(map.get("SFZHM").toString());
			 * ddcDriver.setSsdwId
			 * (Integer.parseInt(map.get("SSDWID").toString()));
			 * ddcDriver.setSynFlag
			 * (map.get("SYN_FLAG")==null?null:map.get("SYN_FLAG").toString());
			 * ddcDriver.setUserCode(map.get("USER_CODE").toString());
			 * ddcDriver.setUserPassword(map.get("USER_PASSWORD").toString());
			 * ddcDriver
			 * .setUserStatus(map.get("USER_STATUS")==null?null:Integer.
			 * parseInt(map.get("USER_STATUS").toString()));
			 */

		}
		return ddcDrivers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.dao.IDdcDriverDao#findAllBySfzhm(java.lang.String)
	 */
	@Override
	public List<DdcDriver> findAllBySfzhm(String sfzmhm1) {
		String sql = "select id, jsrxm, xb, lxdh, syn_flag, tran_date, user_code, user_password, sfzhm, vcuser_img, vc_userworkimg, user_status, "
				+ "illeage_times, user_note, ssdwid, hyxhzh, vc_user_cardimg1, vc_user_cardimg2, xj_flag, xj_msg, xj_rq from ddc_driver where  sfzhm='"
				+ sfzmhm1 + "' ";
		Map<String, Object> maps = getSpringSQL(sql, null);
		List<Map<String, Object>> list = (List<Map<String, Object>>) maps
				.get("rows");
		List<DdcDriver> ddcDrivers = new ArrayList<DdcDriver>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			DdcDriver ddcDriver = new DdcDriver();
			ddcDriver.setId(Long.parseLong(map.get("ID").toString()));
			ddcDriver.setUserStatus(map.get("USER_STATUS") == null ? null
					: Integer.parseInt(map.get("USER_STATUS").toString()));
			ddcDriver.setXjFlag(map.get("XJ_FLAG") == null ? null : map.get(
					"XJ_FLAG").toString());
			ddcDriver.setXjMsg(map.get("XJ_MSG") == null ? null : map.get(
					"XJ_MSG").toString());
			ddcDriver.setXjRq(map.get("XJ_RQ") == null ? null : DateStrUtil
					.toDate(map.get("XJ_RQ").toString()));
			ddcDriver.setUserNote(map.get("USER_NOTE") == null ? null : map
					.get("USER_NOTE").toString());

			ddcDriver.setIlleagalTimes(map.get("ILLEAGE_TIMES") == null ? null
					: Integer.parseInt(map.get("ILLEAGE_TIMES").toString()));
			ddcDriver.setJsrxm(map.get("JSRXM").toString());
			ddcDriver.setLxdh(map.get("LXDH").toString());
			ddcDriver.setSfzhm(map.get("SFZHM").toString());
			ddcDriver.setSsdwId(Integer.parseInt(map.get("SSDWID").toString()));
			ddcDriver.setSynFlag(map.get("SYN_FLAG") == null ? null : map.get(
					"SYN_FLAG").toString());
			ddcDriver.setUserCode(map.get("USER_CODE").toString());
			ddcDriver.setUserPassword(map.get("USER_PASSWORD").toString());
			ddcDriver.setUserStatus(map.get("USER_STATUS") == null ? null
					: Integer.parseInt(map.get("USER_STATUS").toString()));
			ddcDriver.setHyxhzh(map.get("HYXHZH").toString());
			ddcDrivers.add(ddcDriver);

		}
		return ddcDrivers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.dao.IDdcDriverDao#updateBySql(java.lang.String)
	 */
	@Override
	public void updateBySql(String sql) {
		// TODO Auto-generated method stub
		int row = jdbcTemplate.update(sql);
		System.out.println("row=" + row);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.dao.IDdcDriverDao#updateBySql2(java.lang.String)
	 */
	@Override
	public int updateBySql2(String sql) {
		int row = jdbcTemplate.update(sql);
		return row;
	}

}
