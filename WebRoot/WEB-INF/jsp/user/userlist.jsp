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
	
	var randomNu = (new Date().getTime()) ^ Math.random();
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	$("#dg").datagrid({

		url : "<%=basePath%>userAction/queryAllUsers?time=" + randomNu,
		title :  "用户管理",
		iconCls : 'icon-edit',
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		pageSize:20,
		singleSelect : true,//只选中单行
		height:h,
		width:w*0.85,
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
			field : 'userOrgName',
			title : '用户所属部门',
			align:'center',
			width : 120
		},{
			field : 'userRoleName',
			title : '用户角色',
			align:'center',
			width : 120
		},{
			field : 'null',
			title : '备注说明',
			align:'center',
			width : 180,
			formatter:function(value,row,index){
				if(row.id > 0){
					return "已转入本系统";
				}else{
					return "<p style='color:red'>OA系统用户, 未转入本系统</p>";
				}
			}
		}, {
			field : 'opDate',
			title : '添加时间',
			align:'center',
			width : 120
		},{
			field : 'id',
			title:'操作',
			align:'center',
			width : 220,
			formatter:function(value,row,index){
				var del =  "<a  href='javascript:void(0)'  onclick='deleteRow("+row.id+")'>删除</a>&nbsp;&nbsp;&nbsp;";
				var auth = "<a  href='javascript:void(0)'  onclick='authRow("+row.id+")'>授权</a>&nbsp;&nbsp;&nbsp;";
				var reset = "<a  href='javascript:void(0)'  onclick='resetRow("+row.id+")'>重置密码</a>&nbsp;&nbsp;&nbsp;";
				var adduser = "<a  href='javascript:void(0)'  onclick='addIntoUser(\""+row.userCode+"\")'>转入本系统</a>&nbsp;&nbsp;&nbsp;";
				if(row.id > 0){
					return del + auth + reset;
				}else{
					return adduser;
				}
				
			}
		}

		] ],

		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	$('#userRole').combobox({    
	    url:'<%=basePath%>userAction/getRoles',    
	    valueField:'id',    
	    textField:'roleName'   
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
		},
		callback: {
			onClick: onClick
		}
	};	
	function onClick(e, treeId, treeNode){
		var deptId = treeNode.id;
		$('#dg').datagrid('load',{
			deptId: deptId
		});
	}
});
//转入本系统
function addIntoUser(userCode){
	$.messager.confirm('警告', '确认转入本系统吗?', function(r){
		if(r){
			$.post("<%=basePath%>userAction/addIntoUser",{
				userCode:userCode
			},function(data,textStatus){
				if (data.isSuccess) {
					$.messager.show({ // show error message
						title : '提示',
						msg : data.message
					});
					$("#dg").datagrid('reload');
				}else{
					alert(data.message);
				}
			},"json");
		}
	});
}


//删除
function deleteRow(id){
	$.messager.confirm('警告', '确认从本系统中删除这条记录吗?', function(r){
		if (r){
			$.post("<%=basePath%>userAction/delJtuser", 
					{ id:id},     
					   function (data, textStatus)
					   {     
							
						if (data.isSuccess) {
							$.messager.show({ // show error message
								title : '提示',
								msg : data.message
							});
							
							$("#dg").datagrid('reload');
						}else{
							alert(data.message);
						}
					   }
				  ,"json");
		}
	});
	
}  

//密码重置
function resetRow(id){
	$.messager.confirm('警告', '重置后密码为该用户的账号，请确认?', function(r){
		if (r){
			$.post("<%=basePath%>userAction/resetJtuser", 
					{ id:id},     
					   function (data, textStatus)
					   {     
							
						if (data.isSuccess) {
							$.messager.show({ // show error message
								title : '提示',
								msg : data.message
							});
							
							$("#dg").datagrid('reload');
						}else{
							alert(data.message);
						}
					   }
				  ,"json");
		}
	});
	
}
//添加
function addRowData(){
	$("#addSpan").show();
	$("#editSpan").hide();
	$('#dgform').form('clear');
	$('#dgformDiv').dialog('open').dialog('setTitle', '新增用户');
	
}
//授权
function authRow(id){
	window.location.href="<%=basePath%>userAction/authRow?id="+id
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
		userName: $('#userName').val(),
		userCode: $('#usercode').val(),
		deptName:$('#deptName').val(),
		userRole:$("#userRole").combobox("getValue")
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
	  <div id="tb" class="searchdiv">
				<span>用户姓名:</span> <input id="userName" class="easyui-validatebox" type="text" > 
				<span>账号/警号:</span> <input id="usercode" class="easyui-validatebox" type="text" ><br>
				<span>部门名称:</span> <input id="deptName" class="easyui-validatebox" type="text" >
				<span>用户角色:</span> <input id="userRole" style="height: 32px;">
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()" iconCls="icon-search">查询</a>
			</div>
		<table id="dg" style="width:100%;">
		</table>
	</div>

</body>
</html>
