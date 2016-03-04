<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>树形菜单</title>
<base href="<%=basePath%>"/>
	<link rel="stylesheet" href="<%=basePath%>static/css/backhome.css" type="text/css"/>
	<link rel="stylesheet" href="<%=basePath%>static/css/zTreeStyle.css" type="text/css"/>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/ztree/js/jquery.ztree.core-3.5.js"></script>
	
	<script type="text/javascript" >
	
	function setFontCss(treeId, treeNode) {
		return treeNode.level == 0 ? {color:"red",fontsize:"20px"} : {};
	};
	
		var setting = {
				view:{
					showIcon: true,
					fontCss : setFontCss
				},
				key:{
					name:"name",
					url:"url"
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pId",
						rootPId: 0
					}
				},
				callback: {
					beforeClick: function(treeId, treeNode) {
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						if (treeNode.isParent) {
							zTree.expandNode(treeNode);
							return false;
						}
					}
				}
		};

		var zNodes =${meaustr};

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</script>

</head>
<body class="sidebar">
	<div class="tree">
		<ul id="treeDemo" style="margin-left: 10px;" class="ztree"></ul>
	</div>
	
	
</body>
</html>
