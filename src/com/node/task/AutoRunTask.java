/**
  * 文件名：AutoRunTask.java
  * 版本信息：Version 1.0
  * 日期：2016年6月13日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.node.service.ITaskService;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年6月13日 下午8:50:07 
 */
@Component("scheduledTaskManager")
public class AutoRunTask {
	private static final Logger logger = Logger.getLogger("内外网数据同步");
	@Autowired
	ITaskService iTaskService;
	/**
	 * 
	  * 方法描述： 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月13日 下午8:53:35
	 */
	@Scheduled(cron = "0 50 16 * * *?")
	public void autoTask(){
		String sql="update  DDC_DRIVER t set t.user_status=2  where t.xj_flag is not null";
		iTaskService.updateBySql(sql);
		iTaskService.updateDdcDriverImg();
	}
	
	@Scheduled(cron = "0 52 15 * * *?")
	public void updateDriverState(){
		String sql="update  DDC_DRIVER t set t.user_status=2  where t.xj_flag is not null";
		iTaskService.updateBySql(sql);
		iTaskService.updateDdcDriverImg();
		String sql2=" update DDC_DRIVER t set t.syn_flag='ADD' where t.xj_rq>(select sysdate - interval '4' day from dual ) and t.syn_flag is not null ";
		iTaskService.updateBySql(sql2);
	}
}
