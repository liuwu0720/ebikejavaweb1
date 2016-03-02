<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<base href="<%=basePath%>">
<%@include file="../common/common.jsp"%>

</head>

<body>
	<div style="width:200px;height:auto;background:#7190E0;" >
		<div class="easyui-panel" title="信息管理列表" collapsible="true">
		<div class="menudiv">
			<ul>
				<li><a href="<%=basePath%>userAction/getAllUsers" target="main">单位信息管理</a></li>
				<li><a href="#" target="main">电动车申报管理</a></li>
				<li><a href="#" target="main">单位配额管理</a></li>
			</ul>
		</div>
			
		</div>

	</div>
</body>
</html>
