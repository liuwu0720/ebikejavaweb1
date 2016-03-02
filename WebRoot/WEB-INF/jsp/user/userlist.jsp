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
		columns : [ [ {
			field : 'vcAccount',
			title : '用户账号',
			align:'center',
			width : 120
		}, {
			field : 'dtAddtime',
			title : '添加时间',
			width : 120,
			formatter:function(value,index){
				var unixTimestamp = new Date(value);   
                return unixTimestamp.toLocaleString();
			}
		},{
			field : 'id',
			title:'操作',
			width : 120,
			formatter:function(value,index){
				var update = '<a href="javascript:void(0)" onclick="updateRow('+value+')">修改</a>&nbsp;&nbsp;  ';
				var dele = '<a href="#" onclick="deleteRow(this)">删除</a>';
				return update+dele;
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
		} ]
	});
});

//修改
function updateRow(obj){
	var id  = obj;
	$.ajax({
		 type: "GET",
		  url: "<%=basePath%>userAction/queryUserById",
		  data:{
			  id:id
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
//添加
function addRowData(){
	$('#dgformDiv').dialog('open').dialog('setTitle', '编辑相关信息');
	 $('#dgform').form('load', null);
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
</script>
</head>
<body class="easyui-layout">
	<table id="dg" style="width:100%;">
	</table>
	
	<!-- 点编辑时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:500px;height:420px;padding:10px 60px 20px 60px;"
		closed="true" buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table class="table table-bordered table-condensed">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id"></input>
					</td>
				</tr>
				<tr>
					<td>用户姓名：</td>
					<td><input class="easyui-validatebox" type="text" data-options="required:true"
						
						name="vcUsername"></input></td>
				</tr>
				<tr>
					<td>用户账号：</td>
					<td><input class="easyui-validatebox" type="text" data-options="required:true"
						name="vcAccount"></input></td>
				</tr>
				

			</table>
		</form>
	</div>
	<div id="dlg-buttons2">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBtn"
			iconCls="icon-ok" onclick="updateSaveData()" style="width:90px">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dgformDiv').dialog('close')"
			style="width:90px">Cancel</a>
	</div>
</body>
</html>
