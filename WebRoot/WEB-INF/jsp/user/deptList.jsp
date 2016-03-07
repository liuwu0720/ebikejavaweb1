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
 var nodePid = 0;
	$(document).ready(function(){
		$('#tt').tree({    
		    url:'<%=basePath%>deptAction/getTree' ,
		    onClick: function(node){
		    	//$("#pid").attr("value",node.id);
		    	nodePid = node.id;
		    	$("#dg").datagrid("reload",{
		    		"vcPid":node.id
		    	});
			}
		});  
		$("#dg").datagrid({

		url : "<%=basePath%>deptAction/queryAllDepts" ,
		title :  "部门管理",
		iconCls : 'icon-edit',
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		height:700,
		columns : [ [{
			field : 'vcDept',
			title : '部门名称',
			align:'center',
			width : 120
		},{
			field : 'vcPdept',
			title : '上级部门',
			align:'center',
			width : 120
		},{
			field : 'vcAdd',
			title : '添加人',
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
			field : 'dtAdd',
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
	
	//添加
	function addRowData(){
		$('#dgform').form('clear');
		$('#dgform').form('load',{
			"vcPid":nodePid
		});


		$('#dgformDiv').dialog('open').dialog('setTitle', '新增用户');
		
	}
	
	//保存操作
	function updateSaveData(){
		$.messager.progress();
		$('#dgform').form(
				'submit',
				{

					url : "<%=basePath%>deptAction/saveOrUpdate",
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
							$('#tt').tree('reload');
						}else{
							alert(data.message);
						}
						$.messager.progress('close'); // 如果提交成功则隐藏进度条

					}

				}

		);
	}
	
	$('#tt').tree({
		onClick: function(node){
			console.log(node);  // 在用户点击的时候提示
		}
	});


	</script>

  </head>
   <body class="easyui-layout">   
    <div data-options="region:'west',title:'部门总览',split:true" style="width:15%;" >
		<ul class="easyui-tree" id="tt"></ul>  
		
	</div>   
    <div   data-options="region:'center',title:'部门详情'" class="center">
    	<table id="dg">
		<div id="tb" style="padding: 5px; background: #E8F1FF;">
			<span>
				部门名称:</span> <input id="productid"
				style="line-height:26px;border:1px solid #ccc"> 
				<a 	class="easyui-linkbutton" plain="true" onclick="doSearch()" iconCls="icon-search" >查询</a>
		</div>
	</table>
    </div>   
    
    	<!-- 点新增或编辑时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:350px;height:220px;padding:10px 20px 20px 20px;"
		closed="true" buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form" method="post"
			>
			<table class="table">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id" style="height: 32px"></input>
					</td>
				</tr>
				<tr>
					<td>部门名称：</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:true" name="vcDept" style="height: 32px"></input></td>
				</tr>
				<tr><input type="hidden" id="pid" name="vcPid">
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
</body> 
</html>
