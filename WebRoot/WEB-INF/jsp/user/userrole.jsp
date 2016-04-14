<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色管理</title>
    <%@include file="../common/common.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	
	var randomNu = (new Date().getTime()) ^ Math.random();
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	$("#dg").datagrid({

		url : "<%=basePath%>userRoleAction/queryAll?time=" + randomNu,
		title :  "用户角色管理",
		iconCls : 'icon-edit',
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		pageSize:size,
		singleSelect : true,//只选中单行
		height:h,
		width:w,
		columns : [ [{
			field : 'id',
			title : 'ID',
			checkbox : true,
			align:'center',
			width : 120
		},{
			field : 'roleName',
			title : '角色名称',
			align:'center',
			width : 120
		},{
			field : 'roleType',
			title : '是否审批',
			align:'center',
			width : 120,
			formatter:function(value){
				if(value == 1){
					return "是"
				}else{
					return "<p style='color:red'>否</p>";
				}
			}
		},{
			field : 'roleDesc',
			title : '角色描述',
			align:'center',
			width : 120
		},{
			field : 'roleSort',
			title : '审批顺序',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				if(row.roleType == 1){
					return value;
				}
			}
		}, {
			field : 'opDate',
			title : '添加时间',
			align:'center',
			width : 120
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var del =  "<a  href='javascript:void(0)'  onclick='deleteRow("+row.id+")'>删除</a>&nbsp;&nbsp;&nbsp;";
				var auth = "<a  href='javascript:void(0)'  onclick='authRow("+row.id+")'>授权</a>&nbsp;&nbsp;&nbsp;";
				return del+auth;
			}
		}

		] ],
		toolbar : [ {
			id : 'btnadd1',
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
		}],
		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
});	

function addRowData(){
	$('#dgform').form('clear');
	 $('#dgformDiv').dialog('open').dialog('setTitle', '用户角色');
}
function updateRowData(){
	 var row = $('#dg').datagrid('getSelected');
	 if(row){
		 $('#dgformDiv').dialog('open').dialog('setTitle', '用户角色');
		 $('#dgform').form('clear');
		 $('#dgform').form('load',row);
	 }
	 
}

function deleteRow(id){
	$.messager.confirm('Confirm', '确认需要删除 吗?', function(r){
		if (r) {
			$.post('<%=basePath%>userRoleAction/deleteById', {
				id : id
			}, function(result) {
				if (result.isSuccess) {
					$.messager.show({ // show error message
						title : '提示',
						msg :"删除成功"
					});
					$("#dg").datagrid('reload'); // reload the user data
					
				} else {
					alert(data.message);
				}
			}, 'json');
	}});
}
function updateSaveData(){
	var flag = checkValue();
	if(flag){
	
	$.messager.progress({
		text:"正在处理，请稍候..."
	});
	$('#dgform').form('submit', {
		url : "<%=basePath%>userRoleAction/saveUpdate",
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
				$("#dg").datagrid('reload'); // reload the user data
				$('#dgformDiv').dialog('close');
			}else{
				alert(data.message);
			}
			$.messager.progress('close'); // 如果提交成功则隐藏进度条

		}

	});
	
	}
}

function checkValue(){
	var roleType = $("#roleType").combo("getValue");
	var roleSort = $("#roleSort").val();
	
	if(roleType == 1 && roleSort == 0){
		alert("请填写审批顺序");
		return false;
	}else{
		return true;
	}
}

function authRow(id){
	window.location.href="<%=basePath%>userRoleAction/authRow?id="+id
}

</script>
</head>
<body class="easyui-layout">
 <div>
	<table id="dg" style="width:90%;">
		
	</table>
</div>	


<!-- 点编辑时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:550px;height:450px;padding:10px 20px 20px 20px;"
		closed="true" buttons="#dlg-buttons1">
		<form id="dgform" class="easyui-form" method="post">
			<table class="table">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id" ></input>
					</td>
				</tr>
				<tr>
					<td>角色名称：</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:true" name="roleName" ></input></td>
				</tr>
				<tr>
					<td>是否审批：</td>
					<td>
						<select class="easyui-combobox" name="roleType" id="roleType"  
						style="height:32px;width: 50px;">
							<option value="1">是</option>
							<option value="0">否</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>审批顺序</td>
					<td><input class="easyui-numberspinner" id="roleSort" name="roleSort" data-options="increment:1,min:0"  style="width:120px;height:30px;"></input>
					</td>
				</tr>
				
				<tr>
					<td>功能描述</td>
					<td>
						<textarea rows="5" cols="30" name="roleDesc"></textarea>  
					</td>
				</tr>
			</table>
				<input type="hidden" name="nEnable">	
		</form>
		<div id="dlg-buttons1">
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


