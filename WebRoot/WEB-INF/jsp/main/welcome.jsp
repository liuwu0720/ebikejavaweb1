<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css"
		href="<%=basePath%>static/css/iconfont.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>static/css/ebike.css">
</head>
<body>
	<div class="main_welcome">
		<div class="main_welcome_icon">
			<i class="iconfont">&#xe622;</i>
			<p>欢迎使用深圳市特殊行业电动自行车管理系统！</p>
		</div>
	</div>
</body>
</html>