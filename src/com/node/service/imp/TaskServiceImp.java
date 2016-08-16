/**
 * 文件名：TaskServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年6月13日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.node.dao.IDdcDriverDao;
import com.node.model.DdcDriver;
import com.node.service.ITaskService;
import com.node.util.SystemConstants;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年6月13日 下午9:05:02
 */
@Service
public class TaskServiceImp implements ITaskService {
	private static final Logger logger = Logger.getLogger("内外网数据同步service");
	@Autowired
	IDdcDriverDao iDdcDriverDao;
	InputStream in = null;

	Configuration cfg = null;
	SessionFactory sessions = null;
	Session session = null;
	Transaction tx = null;

	@Override
	public void updateDdcDriverImg() {
		// 得到session
		/*
		 * cfg = new Configuration().configure(); sessions =
		 * cfg.buildSessionFactory(); session = sessions.openSession();
		 */

		List<DdcDriver> ddcDrivers = iDdcDriverDao.findByProperty("userStatus",
				0);
		
		if (CollectionUtils.isNotEmpty(ddcDrivers)) {
			for (DdcDriver ddcDriver : ddcDrivers) {
				ddcDriver.setSynFlag(SystemConstants.SYSFLAG_ADD);
				if (StringUtils.isNotBlank(ddcDriver.getVcUserImg())) {
					ddcDriver.setSynFlag(SystemConstants.SYSFLAG_ADD);
					String imgPath = SystemConstants.FILE_READPATH
							+ ddcDriver.getVcUserImg();
					System.out.println(imgPath);
					try {
						in = new FileInputStream(imgPath);
						byte[] b = new byte[in.available()];
						in.read(b);
						in.close();
						ddcDriver.setBlobUserImg(b);
						ddcDriver.setUserNote(null);
						ddcDriver.setUserStatus(1);
						iDdcDriverDao.update(ddcDriver);
					} catch (FileNotFoundException e) {
						logger.warn("找不到头像文件:" + imgPath + "司机信息:"+ddcDriver.getJsrxm()+",sfz="+ddcDriver.getSfzhm());
						ddcDriver.setUserNote("找不到头像文件");
						ddcDriver.setSynFlag(SystemConstants.SYSFLAG_ADD);
						ddcDriver.setUserStatus(0);
						iDdcDriverDao.update(ddcDriver);
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (StringUtils.isNotBlank(ddcDriver.getVcUserCardImg1())) {
					String imgPath = SystemConstants.FILE_READPATH
							+ ddcDriver.getVcUserCardImg1();
					System.out.println(imgPath);
					try {
						in = new FileInputStream(imgPath);
						byte[] b = new byte[in.available()];
						in.read(b);
						in.close();
						ddcDriver.setBlobUserCardImg1(b);
						ddcDriver.setUserNote(null);
						ddcDriver.setUserStatus(1);
						iDdcDriverDao.update(ddcDriver);
					} catch (FileNotFoundException e) {
						logger.warn("找不到身份证正面:" + imgPath + "司机信息:"+ddcDriver.getJsrxm()+",sfz="+ddcDriver.getSfzhm());
						System.out.println("找不到文件：" + imgPath);
						ddcDriver.setUserNote("找不到身份证正面");
						ddcDriver.setUserStatus(0);
						ddcDriver.setSynFlag(SystemConstants.SYSFLAG_ADD);
						iDdcDriverDao.update(ddcDriver);
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (StringUtils.isNotBlank(ddcDriver.getVcUserCardImg2())) {
					String imgPath = SystemConstants.FILE_READPATH
							+ ddcDriver.getVcUserCardImg2();
					System.out.println(imgPath);
					try {
						in = new FileInputStream(imgPath);
						byte[] b = new byte[in.available()];
						in.read(b);
						in.close();
						ddcDriver.setBlobUserCardImg2(b);
						ddcDriver.setUserNote(null);
						ddcDriver.setUserStatus(1);
						iDdcDriverDao.update(ddcDriver);
					} catch (FileNotFoundException e) {
						logger.warn("找不到身份证反面:" + imgPath  + "司机信息:"+ddcDriver.getJsrxm()+",sfz="+ddcDriver.getSfzhm());
						System.out.println("找不到文件：" + imgPath);
						ddcDriver.setUserNote("找不到身份证反面");
						ddcDriver.setUserStatus(0);
						ddcDriver.setSynFlag(SystemConstants.SYSFLAG_ADD);
						iDdcDriverDao.update(ddcDriver);
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ITaskService#updateBySql(java.lang.String)
	 */
	@Override
	public void updateBySql(String sql) {
		// TODO Auto-generated method stub
		iDdcDriverDao.updateBySql(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.ITaskService#updateDdcDriverImgByDriver(com.node.model
	 * .DdcDriver)
	 */
	@Override
	public void updateDdcDriverImgByDriver(DdcDriver ddcDriver) {
		if (StringUtils.isNotBlank(ddcDriver.getVcUserImg())) {
			String imgPath = SystemConstants.FILE_READPATH
					+ ddcDriver.getVcUserImg();
			System.out.println(imgPath);
			try {
				in = new FileInputStream(imgPath);
				byte[] b = new byte[in.available()];
				in.read(b);
				in.close();
				ddcDriver.setBlobUserImg(b);
				iDdcDriverDao.update(ddcDriver);
			} catch (FileNotFoundException e) {
				logger.warn("找不到头像文件:" + imgPath + "司机信息:"
						+ JSON.toJSONString(ddcDriver));
				ddcDriver.setUserStatus(0);
				iDdcDriverDao.update(ddcDriver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}

		}
		if (StringUtils.isNotBlank(ddcDriver.getVcUserCardImg1())) {
			String imgPath = SystemConstants.FILE_READPATH
					+ ddcDriver.getVcUserCardImg1();
			System.out.println(imgPath);
			try {
				in = new FileInputStream(imgPath);
				byte[] b = new byte[in.available()];
				in.read(b);
				in.close();
				ddcDriver.setBlobUserCardImg1(b);
				iDdcDriverDao.update(ddcDriver);
			} catch (FileNotFoundException e) {
				logger.warn("找不到身份证正面:" + imgPath+  "司机信息:"+ddcDriver.getJsrxm()+",sfz="+ddcDriver.getSfzhm());
				System.out.println("找不到文件：" + imgPath);
				ddcDriver.setUserStatus(0);
				iDdcDriverDao.update(ddcDriver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}

		}
		if (StringUtils.isNotBlank(ddcDriver.getVcUserCardImg2())) {
			String imgPath = SystemConstants.FILE_READPATH
					+ ddcDriver.getVcUserCardImg2();
			System.out.println(imgPath);
			try {
				in = new FileInputStream(imgPath);
				byte[] b = new byte[in.available()];
				in.read(b);
				in.close();
				ddcDriver.setBlobUserCardImg2(b);
				iDdcDriverDao.update(ddcDriver);
			} catch (FileNotFoundException e) {
				logger.warn("找不到身份证反面:" + imgPath + "司机信息:"+ddcDriver.getJsrxm()+",sfz="+ddcDriver.getSfzhm());
				System.out.println("找不到文件：" + imgPath);
				ddcDriver.setUserStatus(0);
				iDdcDriverDao.update(ddcDriver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}

		}

	}

	
		/* (non-Javadoc)
		 * @see com.node.service.ITaskService#updateDriverImgBlob()
		 */
	@Override
	public void updateDriverImgBlob() {
		List<DdcDriver> ddcDrivers = iDdcDriverDao.findByProperty("synFlag",
				SystemConstants.SYSFLAG_AG);
		if (CollectionUtils.isNotEmpty(ddcDrivers)) {
			for (DdcDriver ddcDriver : ddcDrivers) {
				if (StringUtils.isNotBlank(ddcDriver.getVcUserImg())) {
					String imgPath = SystemConstants.FILE_READPATH
							+ ddcDriver.getVcUserImg();
					System.out.println(imgPath);
					try {
						in = new FileInputStream(imgPath);
						byte[] b = new byte[in.available()];
						in.read(b);
						in.close();
						ddcDriver.setUserStatus(1);
						ddcDriver.setBlobUserImg(b);
						iDdcDriverDao.update(ddcDriver);
					} catch (FileNotFoundException e) {
						logger.warn("找不到头像文件:" + imgPath +  "司机信息:"+ddcDriver.getJsrxm()+",sfz="+ddcDriver.getSfzhm());
						ddcDriver.setUserNote("找不到头像文件");
						ddcDriver.setUserStatus(0);
						iDdcDriverDao.update(ddcDriver);
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}

				}
				if (StringUtils.isNotBlank(ddcDriver.getVcUserCardImg1())) {
					String imgPath = SystemConstants.FILE_READPATH
							+ ddcDriver.getVcUserCardImg1();
					System.out.println(imgPath);
					try {
						in = new FileInputStream(imgPath);
						byte[] b = new byte[in.available()];
						in.read(b);
						in.close();
						ddcDriver.setUserStatus(1);
						ddcDriver.setBlobUserCardImg1(b);
						iDdcDriverDao.update(ddcDriver);
					} catch (FileNotFoundException e) {
						logger.warn("找不到身份证正面:" + imgPath  +"司机信息:"+ddcDriver.getJsrxm()+",sfz="+ddcDriver.getSfzhm());
						System.out.println("找不到文件：" + imgPath);
						ddcDriver.setUserStatus(0);
						iDdcDriverDao.update(ddcDriver);
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (StringUtils.isNotBlank(ddcDriver.getVcUserCardImg2())) {
					String imgPath = SystemConstants.FILE_READPATH
							+ ddcDriver.getVcUserCardImg2();
					System.out.println(imgPath);
					try {
						in = new FileInputStream(imgPath);
						byte[] b = new byte[in.available()];
						in.read(b);
						in.close();
						ddcDriver.setUserStatus(1);
						ddcDriver.setBlobUserCardImg2(b);
						iDdcDriverDao.update(ddcDriver);
					} catch (FileNotFoundException e) {
						logger.warn("找不到身份证反面:" + imgPath + "司机信息:"+ddcDriver.getJsrxm()+",sfz="+ddcDriver.getSfzhm());
						System.out.println("找不到文件：" + imgPath);
						ddcDriver.setUserStatus(0);
						iDdcDriverDao.update(ddcDriver);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}
		
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.ITaskService#updateBySql2(java.lang.String)
		 */
	@Override
	public int updateBySql2(String sql) {
		 int row = iDdcDriverDao.updateBySql2(sql);
		return row;
	}

}
