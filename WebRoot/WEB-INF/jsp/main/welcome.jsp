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
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/frame.css">
</head>
<body>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <body>
	<table width="100%" height="80%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">
				<div id="main_welcome"></div>
				<div id="main_name">欢迎使用深圳市特殊行业电动自行车管理系统！</div>
			</td>
		</tr>
	</table>
  </body>
</html>

</body>
</html>