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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%-- <%@include file="../common/common.jsp"%> --%>

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/demo.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/color.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/ebike.css">	
	<link rel="stylesheet" href="<%=basePath%>static/css/backhome.css"
	type="text/css" />
<link rel="stylesheet" href="<%=basePath%>static/css/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>static/js/jquery-1.7.2.min.js"></script>
	
<script type="text/javascript"
	src="<%=basePath%>static/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/js/ztree/js/jquery.ztree.core-3.5.js"></script>

<script type="text/javascript">

	var setting = {			
			data: {
				simpleData: {
					enable: true,					
				}
			},
			callback: {
				onDblClick: onClick,
				onRightClick: OnRightClick,
				beforeClick: function(treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					if (treeNode.isParent) {
						zTree.expandNode(treeNode);
						return false;
					}
				}
			}
	};
	
	//显示右键菜单
	function showRMenu(type, x, y,treeNode) {
		
		if (type !="root") {
			$("#rMenu ul").show();
			var type = treeNode.ntype;
		
			if(type==0){
				$("#m_add").show();
				$("#m_del").show();
				$("#g_add").show();
				
				
				$("#g_del").hide();					
				$("#c_add").hide();
				$("#c_del").hide();
			}else if(type==1){
				$("#c_add").show();
				$("#g_del").show();
				
				$("#m_add").hide();
				$("#g_add").hide();
				$("#m_del").hide();
				$("#c_del").hide();
			}else if(type==2){
				$("#c_del").show();
				
				$("#m_add").hide();
				$("#g_add").hide();
				$("#c_add").hide();
				$("#m_del").hide();
				$("#g_del").hide();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		
	}
	
	//隐藏右键菜单
	function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	//鼠标右键点击事件
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}
	
	var addCount = 1;
	function addTreeNode(resType) {
		hideRMenu();
		// 获得父id 
		var pid = node.id;
		var leef = 1;//是否叶子节点，默认不是
		if(resType==2)leef=0;//当 添加的是操作，则是叶子节点
		
		
		
	}

	
	//删除
	function removeTreeNode(resType) {
		hideRMenu();
		if(node.leaf>0){
			var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
			if (confirm(msg)==true){
				//删除并刷新页面
				del();
			}
		}else{
			//删除并刷新页面
			del();
		}
		
	}
	//删除并刷新页面
	function del(){
		var url = "<%=basePath%>resource/delResource?resourceId="+node.id;
		$.post(url,function(data){
			showResponse(data);
		})
	}



	//弹出页面
	function onClick(event, treeId, treeNode, clickFlag) {
		var id = treeNode.id;
		var url = '<%=basePath%>resourceAction/getResourceByid';
		$.ajax({
			url:url,
			data:{
				resourceId:id,
				resType:treeNode.ntype
			},
			dataType: "json",
			success:function(data){
				
   			  if(data){
   				 $('#dgformDiv').dialog('open').dialog('setTitle', '编辑相关信息');
   				 $('#dgform').form('load', data);
   				
   			  }
   		  }
		})

	}
	
	function OnRightClick(event, treeId, treeNode) {
		if (treeNode ) {
			//有选择节点的
			node=treeNode;
			zTree.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY,treeNode);
		}
	}

	var zTree, rMenu;
	$(document).ready(function() {
		var zNodes = ${jsonStr};
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var idvalue = getQueryString("idvalue");
		if(null != idvalue){
			var zjson = zTree.getNodeByParam("id",idvalue);
			if(null !=zjson){
				zTree.expandNode(zjson,true,false,true);
			}
		}
		rMenu = $("#rMenu");

	});
	
	
	/**
	 * 获得url上的参数
	 * create by hjx
	 */
	function getQueryString(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    var r = window.location.search.substr(1).match(reg);
	    if (r != null) return unescape(r[2]); return null;
	    }
</script>


<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	top: 0;
	background-color: #555;
	text-align: left;
	padding: 2px;
}

div#rMenu ul li {
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
</style>
<body>
	<div class="easyui-layout" style="width:90%;height:550px;">
	
		<div region="west" split="true" title="资源列表" style="width:250px;">
			<div class="tree">
				<ul id="treeDemo" style="margin-left: 10px;" class="ztree"></ul>
			</div>
			<div id="rMenu">
				<ul>
					<li id="m_add" onclick="addTreeNode(0);">增加菜单</li>
					<li id="m_del" onclick="removeTreeNode(0);">删除菜单</li>
					
				</ul>
			</div>
		</div>
		<div id="content" region="center" title="操作说明" style="padding:5px;">
			<p>1、双击菜单，强弹出该菜单的详情信息</p>
			<p>2、单击右键，进行增加或删除菜单操作</p>
		</div>
		
	</div>
	
	<!-- 点编辑时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:500px;height:420px;"
		closed="true" buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form" method="post">
			<table class="table">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id" style="height: 32px"></input>
					</td>
				</tr>
				<tr>
					<td>资源名称：</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:true" name="vcResourceName" style="height: 32px"></input></td>
				</tr>
				<tr>
					<td>链接地址：</td>
					<td><input class="easyui-validatebox" type="text"	name="vcUrl" style="height: 32px"></input></td>
				</tr>
				<tr>
					<td>排序：</td>
					<td><input class="easyui-validatebox" type="text" name="nSort" style="height: 32px"></input>
					</td>
				</tr>
				<tr>
					<td>资源描述：</td>
					<td>
					<textarea rows="" cols="" name="vcDesc"></textarea>
					</td>
				</tr>
				
			</table>
		</form>
		<div id="dlg-buttons2">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBtn"
			iconCls="icon-ok" onclick="updateSaveData()" style="width:90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dgformDiv').dialog('close')"
			style="width:90px">取消</a>
	</div>
	</div>
</body>
</html>