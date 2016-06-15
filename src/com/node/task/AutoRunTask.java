/**
  * 文件名：AutoRunTask.java
  * 版本信息：Version 1.0
  * 日期：2016年6月13日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.task;

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
	
	@Autowired
	ITaskService iTaskService;
	/**
	 * 
	  * 方法描述： 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月13日 下午8:53:35
	 */
	@Scheduled(cron = "0 35 22 * * *?")
	public void autoTask(){
		iTaskService.updateDdcDriverImg();
	}
}
