<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'userlist.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<%@include file="../common/common.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>static/js/ztree/js/jquery.ztree.core-3.5.min.js"></script>	
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/js/ztree/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript">
var deptId = 0;
$(document).ready(function(){
	$.ajaxSetup ({
		   cache: false //å³é­AJAXç¸åºçç¼å­
		});
	
	$.ajax({
			url:'deptAction/getTree',
			type:'post',
			dataType:'json',
			success:function(msg){
			zNodes = msg;
			$.fn.zTree.init($("#deptTree"), setting, zNodes);
			}
		})
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pid"
			}
		}
	};	
	
	var randomNu = (new Date().getTime()) ^ Math.random();
	$("#dg").datagrid({

		url : "<%=basePath%>userAction/queryAllUsers?time=" + randomNu,
		title :  "用户管理",
		iconCls : 'icon-edit',
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		height:700,
		columns : [ [{
			field : 'userCode',
			title : '用户账号',
			align:'center',
			width : 120
		},{
			field : 'userName',
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

function deleteRow(id){
	$.messager.confirm('警告', '确认删除这条记录码', function(r){
		if (r){
			$.post("<%=basePath%>userAction/delTrafficUser", 
					{ id:id},     
					   function (data, textStatus)
					   {     
							
						if (data.isSuccess) {
							$.messager.show({ // show error message
								title : '提示',
								msg : data.message
							});
							$('#dgformDiv').dialog('close');
							$("#dg").datagrid('reload');
						}else{
							alert(data.message);
						}
					   }
				  ,"json");
		}
	});
	
}  

//修改
function updateRowData(){
	$("#editSpan").show();
	$("#addSpan").hide();
	$('#dgform').form('clear');
	 var row = $('#dg').datagrid('getSelected');
   if (row){
    	 $('#dgformDiv').dialog('open').dialog('setTitle', '编辑用户');
    	 $('#dgform').form('load', row);
     }else{
    	 $.messager.alert('提示','请选择你要修改的行');    
     } 
  
	
}
//添加
function addRowData(){
	$("#addSpan").show();
	$("#editSpan").hide();
	$('#dgform').form('clear');
	$('#dgformDiv').dialog('open').dialog('setTitle', '新增用户');
	
}


//保存操作

function updateSaveData(){
	$.messager.progress();
	$('#dgform').form(
			'submit',
			{

				url : "<%=basePath%>userAction/saveOrUpdate",
				onSubmit : function() {
					var isValid = $("#dgform").form('enableValidation').form(
							'validate');

					if (!isValid) {
						$.messager.progress('close'); // 如果表单是无效的则隐藏进度条
					}
					return isValid; // 返回false终止表单提交
				},
				success : function(data) {
					var data = eval('(' + data + ')'); // change the JSON
					
					if (data.isSuccess) {
						$.messager.show({ // show error message
							title : '提示',
							msg : data.message
						});
						$('#dgformDiv').dialog('close');
						$("#dg").datagrid('reload');
					}else{
						alert(data.message);
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			}

	);
}


//查询功能
function doSearch(){
	$('#dg').datagrid('load',{
		vcNameString: $('#itemid').val(),
		vcDept: $('#productid').val()
	});
}
</script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',title:'部门总览',split:true" style="width:25%;" >
		<div class="zTreeDemoBackground left">
				
				<ul id="deptTree" class="ztree"></ul>
			</div>
		
	</div>   

	  <div   data-options="region:'center',title:'用户管理'" class="center">
		<table id="dg" style="width:90%;">
			<div id="tb" style="padding: 5px; background: #E8F1FF;">
				<span>用户姓名:</span> <input id="userName"
					style="line-height:26px;border:1px solid #ccc"> <span>
					账号/警号:</span> <input id="usercode"
					style="line-height:26px;border:1px solid #ccc"> <a
					class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询</a>
			</div>
		</table>
	</div>

</body>
</html>
