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

<title>协会配额审批</title>

<%@include file="../common/common.jsp"%>
<style type="text/css">
#table input{
	border: 0;
}
</style>

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

		url : "<%=basePath%>approvalAction/queryQuotaApprove?time=" + randomNu,
		title :  "协会配额审批",
		iconCls : 'icon-search',
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		pageSize:size,
		width:w,
		singleSelect : true,//只选中单行
		height:h,
		loadMsg:'正在加载,请稍等...',
		columns : [ [{
			field : 'lsh',
			title : '流水号',
			align:'center',
			width : 120
		},{
			field : 'hyxhsqpe',
			title : '行业协会申请配额',
			align:'center',
			width : 220
		},{
			field : 'hyxhmc',
			title : '行业协会名称',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var query = "<a    onclick='queryHyxhDetail(\""+row.hyxhzh+"\")'>"+value+"</a>";
				return query;	
			}
		},{
			field : 'sqrq',
			title : '申请日期',
			align:'center',
			width : 120
		},{
			field : 'bjjg',
			title : '审批状态',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				if(value == 0){
					return "已同意";
				}else if(value == 1){
					return "<p style='color:red'>已拒绝</p>";
				}else{
					return "审批中";
				}
			}
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var query = "<a    onclick='queryRow("+row.id+")'>查看</a>";
				var approve = "<a    onclick='approveRow("+row.id+")'>审批</a>";
				if(row.currentApprove){
					return approve;	
				}else{
					return query;
				}
				
			}
		}

		] ],

		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	
	
});


//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		 lsh: $("#lsh").val(),
		 hyxhmc: $('#hyxhmc').val(),
		 bjjg:$("#bjjg").combobox("getValue")
	}); 
}

//查看行业协会详情
function queryHyxhDetail(obj){

	$.ajax({
		type: "GET",
   	    url: "<%=basePath%>industryAction/queryHxyHyxhBaseByCode",
   	   data:{
   		code:obj
	   }, 
	   dataType: "json",
	   success:function(data){
 			  if(data){
 				 $('#dgformDiv').dialog('open').dialog('setTitle', '详情信息');
 				 $('#dgform').form('load', data);
 			  }
 		  }
	})
	
}


//查看
function queryRow(id){
	window.location.href="<%=basePath%>approvalAction/queryApprovalInfoById?id="+id+"&&type=1"
}
//审批
function approveRow(id){
	window.location.href="<%=basePath%>approvalAction/queryApprovalInfoById?id="+id+"&&type=2"
}


</script>
</head>
<body class="easyui-layout">


			<div class="searchdiv">
				<span>流水号</span>
				<input id="lsh" type="text" class="easyui-validatebox"  style="height: 32px;">  
				<span>协会名称：</span>
				<input id="hyxhmc" type="text" class="easyui-validatebox" name="dabh" ></input>
				<span>审批状态：</span>
				<select  class="easyui-combobox" id=bjjg style="width:100px;height: 32px;">   
   				 	<option value="">审批中</option>   
  				   <option value="0">已同意</option>    
  					<option value="1">已拒绝</option> 
				</select>
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			
		<table id="dg" style="width:90%;">
		</table>
</div>
	
    <!-- 点编辑时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:500px;height:400px;padding:10px 20px 20px 20px;"
		closed="true" buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form">
			<table class="dialogtable">
				<tr>
					<th>协会名称</th>
					<td><input  name=hyxhmc type="text" class="easyui-validatebox" style="height: 32px;width:100%;" readonly="readonly"></td>
					
				</tr>
				<tr>
					<th>协会地址</th>
					<td><input  name="hyxhdz" type="text" class="easyui-validatebox" style="height: 32px;width:100%;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>协会负责人</th>
					<td><input  name="hyxhfzr" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
				<tr>
					
					<th>联系电话</th>
					<td><input  name="hyxhfzrdh" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>总配额</th>
					<td><input  name="totalPe" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
				<tr>
					
					<th>剩余配额</th>
					<td><input  name="hyxhsjzpe" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
			</table>
				
		</form>
		
	</div>
</body>
</html>