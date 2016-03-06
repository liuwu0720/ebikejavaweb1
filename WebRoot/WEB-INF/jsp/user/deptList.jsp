<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deptList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->
<%@include file="../common/common.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
	
		$("#dg").datagrid({

		url : "<%=basePath%>userAction/queryAllUsers",
		title :  "用户管理",
		iconCls : 'icon-edit',
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		height:700,
		columns : [ [{
			field : 'vcAccount',
			title : '用户账号',
			align:'center',
			width : 120
		},{
			field : 'vcNameString',
			title : '用户姓名',
			align:'center',
			width : 120
		},{
			field : 'vcDept',
			title : '用户所属部门',
			align:'center',
			width : 120
		},{
			field : 'vcRoleList',
			title : '用户角色',
			align:'center',
			width : 120
		},{
			field : 'nEnable',
			title : '是否有效',
			align:'center',
			width : 120,
			formatter:function(value,index){
				if(value == 0){
					return "有效";
				}else{
					return "无效";
				}
			}
		}, {
			field : 'dtAddDate',
			title : '添加时间',
			align:'center',
			width : 120,
			formatter:function(value,index){
				var unixTimestamp = new Date(value);   
                return unixTimestamp.toLocaleString();
			}
		},{
			field : 'id',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				
				return "<a  href='javascript:void(0)'  onclick='deleteRow("+row.id+")'>删除</a>";
			}
		}

		] ],

		toolbar : [ {
			id : 'btnadd2',
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				addRowData();
			}
		},{
			id : 'btnadd2',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				
				updateRowData();
			}
		} ],
		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	});
	</script>

  </head>
   <body class="easyui-layout">   
    <div data-options="region:'west',title:'部门总览',split:true" style="width:180px" >wwww</div>   
    <div   data-options="region:'center',title:'部门详情'" class="center">
    	<table id="dg">
		<div id="tb" style="padding: 5px; background: #E8F1FF;">
			<span>用户姓名:</span> <input id="itemid"
				style="line-height:26px;border:1px solid #ccc"> <span>
				所属部门:</span> <input id="productid"
				style="line-height:26px;border:1px solid #ccc"> 
				<a 	class="easyui-linkbutton" plain="true" onclick="doSearch()" iconCls="icon-search" >查询</a>
		</div>
	</table>
    </div>   
</body> 
</html>
