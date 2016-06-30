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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		/*cfg = new Configuration().configure();
		sessions = cfg.buildSessionFactory();
		session = sessions.openSession();*/
		try {
			List<DdcDriver> ddcDrivers = iDdcDriverDao.findByProperty("userStatus", 1);
			if(CollectionUtils.isNotEmpty(ddcDrivers)){
				for(DdcDriver ddcDriver:ddcDrivers){
					if(StringUtils.isNotBlank(ddcDriver.getVcUserImg())){
						String imgPath = SystemConstants.FILE_READPATH+ddcDriver.getVcUserImg();
						System.out.println(imgPath);
						in = new FileInputStream(imgPath);
						byte[] b = new byte[in.available()];
						in.read(b);
						in.close();
						ddcDriver.setBlobUserImg(b);
						iDdcDriverDao.update(ddcDriver);
					}
					if (StringUtils.isNotBlank(ddcDriver.getVcUserCardImg1())) {
						String imgPath = SystemConstants.FILE_READPATH+ddcDriver.getVcUserCardImg1();
						System.out.println(imgPath);
						in = new FileInputStream(imgPath);
						byte[] b = new byte[in.available()];
						in.read(b);
						in.close();
						ddcDriver.setBlobUserCardImg1(b);
						iDdcDriverDao.update(ddcDriver);
					}
					if (StringUtils.isNotBlank(ddcDriver.getVcUserCardImg2())) {
						String imgPath = SystemConstants.FILE_READPATH+ddcDriver.getVcUserCardImg2();
						System.out.println(imgPath);
						in = new FileInputStream(imgPath);
						byte[] b = new byte[in.available()];
						in.read(b);
						in.close();
						ddcDriver.setBlobUserCardImg2(b);
						iDdcDriverDao.update(ddcDriver);
					}
					
				}
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("没找到文件");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
		/* (non-Javadoc)
		 * @see com.node.service.ITaskService#updateBySql(java.lang.String)
		 */
	@Override
	public void updateBySql(String sql) {
		// TODO Auto-generated method stub
		iDdcDriverDao.updateBySql(sql);
	}

}
