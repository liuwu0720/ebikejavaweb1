
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ page
	language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>树形菜单</title>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/ebike.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/iconfont.css">
<script type="text/javascript"
	src="<%=basePath%>static/js/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//页面中的DOM已经装载完成时，执行的代码
		$(".menu-li > a").click(function() {
			//找到主菜单项对应的子菜单项
			
			$(this).parent().addClass("open");
			$(this).parent().siblings().removeClass("open");

		});

	});
</script>
<style>
</style>
</head>
<body>
	<div class="menu">
		<div class="menu-header">
			<div class="menu-icon">菜单管理</div>
			<ul class="menu-ul">
				<c:forEach items="${nodeJtMenus }" var="node">
					<li class="menu-li">
					<a href="javascript:void(0)">
					<i class="iconfont">${node.vcIcon }</i>${node.vcMenu } </a>
						<ul>
						<c:forEach items="${node.subJtMenus }" var="sub">
							<li><a href="${sub.vcUrl }" target="main"><i class="iconfont">${sub.vcIcon }</i>${sub.vcMenu }</a></li>
						</c:forEach>	
						</ul>
					</li>
				</c:forEach>
			</ul>

		</div>

	</div>


</body>
</html>
