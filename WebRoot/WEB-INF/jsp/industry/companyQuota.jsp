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

<title>行业协会所属单位配额管理</title>

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

		url : "<%=basePath%>industryAction/queryAllCompany?time=" + randomNu,
		title :  "行业协会所属单位配额管理",
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		pageSize:size,
		singleSelect : true,//只选中单行
		height:h,
		loadMsg:'正在加载,请稍等...',
		columns : [ [{
			field : 'id',
			title : 'id',
			checkbox : true,
			align:'center',
			width : 120
		},{
			field : 'dwmc',
			title : '单位名称',
			align:'center',
			width : 220
		},{
			field : 'lxr',
			title : '联系人',
			align:'center',
			width : 120
		},{
			field : 'lxdh',
			title : '联系人电话',
			align:'center',
			width : 120
		},{
			field : 'sqr',
			title : '申请人',
			align:'center',
			width : 120
		},{
			field : 'sqrq',
			title : '申请日期',
			align:'center',
			width : 120,
			formatter:function(value,index){
				var unixTimestamp = new Date(value);   
				return unixTimestamp.toLocaleString();
			}   
		},{
			field : 'hyxhzh',
			title : '所属协会',
			align:'center',
			width : 120
		},{
			field : 'zt',
			title : '状态',
			align:'center',
			width : 120,
			formatter:function(value,index){
				if(value == 0){
					return "<p style='color:red'>禁用</p>"
				}else{
					return "启用";
				}
			}
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var reset = "<a  href='javascript:void(0)'  onclick='upateRowQty("+row.id+")'>修改配额</a>&nbsp;&nbsp;&nbsp;";
				return reset;	
			}
		}

		] ],
		
		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	
	$('#hyxhzh').combobox({    
	    url:'<%=basePath%>industryAction/getAllIndustry',    
	    valueField:'hyxhzh',    
	    textField:'hyxhmc'   
	});  
});

//修改配额
function upateRowQty(id){
	$.ajax({
		url:"<%=basePath%>industryAction/queryCompanyById",
		data:{
			id:id
		},
		 dataType: "json",
		success:function(data){
			 if(data){
				 $('#dgformDiv').dialog('open').dialog('setTitle', '详情信息');
	 				$('#dgform').form('clear');
	 				 $('#dgform').form('load', data);
			 }
		}
	})
}

//保存操作

function updateSaveData(){
	$.messager.progress();
	$('#dgform').form('submit', {
				url : "<%=basePath%>industryAction/saveOrUpdateCompanyQuota",
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



//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		 hyxhzh: $("#hyxhzh").combobox("getValue"),
		 dwmc:$("#dwmc").val()
	}); 
}
</script>
</head>
<body class="easyui-layout">

	<div>
		<table id="dg" style="width:90%;">

			<div id="tb" style="padding: 5px; background: #E8F1FF;">
				<span>协会名称名称：</span>
				<input id="hyxhzh" style="height: 32px;">  
				
				<span>单位名称：</span>
				<input id="dwmc" type="text" class="easyui-validatebox"></input>
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		</table>
	</div>
	
	<!-- 点新增，编辑时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog" closed="true" style="width: 300px;"
		buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form" method="post"  >
			<table class="table">
				<tr>
					<td>申请配额:</td>
					<td><input id="hyxhsjzpe" class="easyui-numberspinner" name="dwpe"
						data-options="increment:1,required:true,validType:'number'"  min="0"
						style="width:120px;height:30px;"></input></td>
				</tr>
				
			</table>
			<input type="hidden" name="id" />
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