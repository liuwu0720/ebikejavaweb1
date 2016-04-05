<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>top</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/iconfont.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/ebike.css">
<script type="text/javascript">
function sessionOut(){
	window.location.href="<%=basePath%>userAction/loginout"
}

</script>	
	
</head>
<body>
	<!--  <i class="iconfont header-icon">&#xe600;</i> -->
	<div class="headerdiv">
		<div class="header">
			<i class="iconfont header-icon">&#xe600;</i>
			
		</div>
		<div class="header-tool">
			<ul>
				<li><a href="<%=basePath%>userAction/modifyPassword" title="设置" target="main"> <i
						class="iconfont header-icon-setting">&#xe601;</i><br> <span>设置</span></li>
				<li></a> <a href="javascript:void(0)" target="_top" title="退出" onclick="sessionOut()"> <i
						class="iconfont header-icon-setting">&#xe604;</i><br> <span>退出
					</span>
				</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
