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

<title>黑名单管理</title>

<%@include file="../common/common.jsp"%>


<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup ({
		   cache: false //缓存
		});
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	var randomNu = (new Date().getTime()) ^ Math.random();
	$("#dg").datagrid({

		url : "<%=basePath%>blackListAction/queryAll?time=" + randomNu,
		title :  "黑名单管理",
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		pageSize:size,
		singleSelect : true,//只选中单行
		height:h,
		width:w,
		loadMsg:'正在加载,请稍等...',
		columns : [ [{
			field : 'id',
			title : 'id',
			checkbox : true,
			align:'center',
			width : 120
		},{
			field : 'jsrxm',
			title : '驾驶人姓名',
			align:'center',
			width : 120
		},{
			field : 'sfzhm',
			title : '身份证号码',
			align:'center',
			width : 220
		},{
			field : 'xb',
			title : '性别',
			align:'center',
			width : 120,
			formatter:function(value){
				if(value == 0){
					return "男";
				}else{
					return "女";
				}
			}
			
		},{
			field : 'lxdh',
			title : '联系电话',
			align:'center',
			width : 120
		},{
			field : 'bz',
			title : '备注',
			align:'center',
			width : 120
		},{
			field : 'cjr',
			title : '录入 人',
			align:'center',
			width : 120
		},{
			field : 'cjrq',
			title : '录入日期',
			align:'center',
			width : 120,
			formatter:function(value,index){
				var unixTimestamp = new Date(value);   
				return unixTimestamp.toLocaleString();
			}   
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var del = "<a  href='javascript:void(0)'  onclick='deleteRow("+row.id+")'>删除</a>";
				
				return del;	
			}
		}

		] ],
		toolbar : [ {
			id : 'btn1',
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				addRowData();
			}
		}, {
			id : 'btn2',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				updateRow();
			}
		}],

		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	
	
});
//删除
function deleteRow(id){
	$.messager.confirm('警告', '确认删除这条记录码', function(r){
		if (r){
			$.post("<%=basePath%>blackListAction/del", 
					{ id:id},     
					   function (data, textStatus){     
							
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
function addRowData(){
	 $('#dgformDiv').dialog('open').dialog('setTitle', '编辑信息');
	 $('#dgform').form('clear');
}


//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		 jsrxm: $("#jsrxm").val(),
		 sfzhm: $('#sfzhm').val()
	}); 
}


//修改
function updateRow(){
	 $('#dgform').form('clear');
	 var row = $('#dg').datagrid('getSelected');
	   if (row){
	    	 $('#dgformDiv').dialog('open').dialog('setTitle', '编辑信息');
	    	 $('#dgform').form('load', row);
	     }else{
	    	 $.messager.alert('提示','请选择你要修改的行');    
	     } 

}

//保存操作

function updateSaveData(){
	$.messager.progress();
	$('#dgform').form('submit', {
				url : "<%=basePath%>blackListAction/saveOrUpdate",
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

			});
}


</script>
</head>
<body class="easyui-layout">

	<div>
		<table id="dg" style="width:90%;">

			<div id="tb" style="padding: 5px; background: #E8F1FF;">
				<span>姓名：</span>
				<input id="jsrxm" type="text" class="easyui-validatebox" name="jsrxm" ></input>
				<span>身份证号码:</span> <input id="sfzhm" name="sfzhm"
					class="easyui-validatebox" type="text" > &nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		</table>
	</div>
	
	
		<!-- 点查看时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:550px;height:450px;padding:10px 20px 20px 20px;"
		closed="true"  buttons="#dlg-buttons">
		<form id="dgform" class="easyui-form" enctype="multipart/form-data"
			method="post">
			<table id="table1" class="table table-condensed">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id"></input>
					</td>
				</tr>
				<tr>
					<th>驾驶人姓名：</th>
					<td><input  name="jsrxm" class="easyui-validatebox" data-options="required:true" type="text" style="height:30px;width: 200px;" ></td>					
					</tr>
				<tr>
					<th>身份证号码：</th>
					<td><input name="sfzhm"  type="text" class="easyui-validatebox" data-options="required:true,validType:'idcard'"></td>
				</tr>
				
				<tr>
					<th>联系电话</th>
					<td><input class="easyui-validatebox" type="text" 
						data-options="required:true,validType:'phoneNum'" name="lxdh"
						style="height: 32px;"></input></td>
				</tr>
				<tr>
					<th>性别:</th>
					<td><select class="easyui-combobox" name="xb"  data-options="required:true" id="cc"
						style="height:32px;width: 50px;">
							<option value="-1">请选择</option>
							<option value="0">男</option>
							<option value="1">女</option>
					</select></td>
				</tr>
				<tr>
					<th>备注</th>
					<td>
						<textarea rows="5" cols="30" name="bz"></textarea>  
					</td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons">
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