<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>top</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/frame.css">
  </head>
  <body>
	<div id="header_img_1">
		<div id="header_img_2"></div>
		<div id="header_img_3">
			<ul>
				<li><a href="user_logout.action" target="_top"><img src="<%=basePath%>static/images/frame_10.gif"></a></li>
				<li><a href="<%=basePath%>static/system/user/user_update_pwd.jsp" target="mainFrame"><img src="<%=basePath%>static/images/frame_09.gif"></a></li>
				<li><a href="user_getUserInfo.action" target="mainFrame"><img src="<%=basePath%>static/images/frame_08.gif"></a></li>
				<li><a href="<%=basePath%>static/system/frame.jsp" target="_top"><img src="<%=basePath%>static/images/frame_07.gif"></a></li>
			</ul>
		</div>
	</div>
	<div id="header_img_4">
		<div id="header_img_5"></div>
		<div id="header_menu">
			<div id="header_menu_logo"><img src="<%=basePath%>static/images/frame_18.gif" align="top"/></div>
			<span id="header_menu_text">管理菜单</span>
		</div>
		<div id="header_img_6"></div>
		<div id="header_text">当前登录用户：cs | 行业协会名称：测试</div>
		<div id="header_img_7"></div>
	</div>
  </body>
</html>
