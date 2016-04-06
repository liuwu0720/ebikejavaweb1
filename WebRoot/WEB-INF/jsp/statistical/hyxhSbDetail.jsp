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

<title>区域车辆统计详情</title>

<%@include file="../common/common.jsp"%>
<style type="text/css">
#table  input{
	border: 0;
}
 #table2 input{
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
	var hyxhzh = '${hyxhzh}';
	$("#dg").datagrid({

		url : "<%=basePath%>approvalAction/queryRecordApprove?hyxhzh=" + hyxhzh,
		title :  "已申报列表",
		iconCls : 'icon-search',
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
			field : 'lsh',
			title : '流水号',
			align:'center',
			width : 120
		},{
			field : 'hyxhzhName',
			title : '行业协会',
			align:'center',
			width : 220,
			formatter:function(value,row,index){
				var query = "<a  href='javascript:void(0)'  onclick='queryHyxhDetail(\""+row.hyxhzh+"\")'>"+value+"</a>";
				return query;	
			}
		},{
			field : 'ssdwName',
			title : '单位名称',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var query = "<a  href='javascript:void(0)'  onclick='queryHyxhDwDetail(\""+row.ssdwId+"\")'>"+value+"</a>";
				return query;	
			}
		},{
			field : 'djh',
			title : '电机号',
			align:'center',
			width : 120
		},{
			field : 'jsrxm1',
			title : '驾驶人',
			align:'center',
			width : 120
		},{
			field : 'xsqyName',
			title : '行驶区域',
			align:'center',
			width : 120
		},{
			field : 'sqrq',
			title : '申请时间',
			align:'center',
			width : 120,
			formatter:function(value,index){
				var datevalue = new Date(value);
				return datevalue.toLocaleString();
			}
		},{
			field : 'slrq',
			title : '审批日期',
			align:'center',
			width : 120,
			formatter:function(value,index){
				var datevalue = new Date(value);
				return datevalue.toLocaleString();
			}
		},{
			field : 'slyj',
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
				var query = "<a  href='javascript:void(0)'  onclick='queryRow("+row.id+")'>查看</a>";
				return query;
			}
		}

		] ],

		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	
	$('#xsqy').combobox({    
	    url:'<%=basePath%>ebikeAction/getArea',    
	    valueField:'dmz',    
	    textField:'dmms1'   
	}); 

	$('#hyxsssdwmc').combobox({
	    		 	url:'<%=basePath%>industryAction/getDwmcByHyxh?hyxhzh='+hyxhzh,    
	    		    valueField:'id',    
	    		    textField:'dwmc'
	 })
	
});




//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		 lsh: $("#lsh").val(),
		 bjjg:$("#bjjg").combobox("getValue"),
		 dwmcId:$("#hyxsssdwmc").combobox("getValue")
	}); 
}



//查看
function queryRow(id){
	$.messager.progress({
		text:"正在处理，请稍候..."
	});
	window.location.href="<%=basePath%>approvalAction/queryRecordApprovalInfoById?id="+id+"&&type=1";
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
//查看单位详情
function queryHyxhDwDetail(obj){
	$.ajax({
		type: "GET",
   	    url: "<%=basePath%>industryAction/queryCompanyById",
   	   data:{
   		id:obj
	   }, 
	   dataType: "json",
	   success:function(data){
 			  if(data){
 				 $('#dgformDiv2').dialog('open').dialog('setTitle', '详情信息');
 				 $('#dgform2').form('load', data);
 				 $("#img2").attr("src",data.vcShowPath);
 			  }
 		  }
	})
}
</script>
</head>
<body class="easyui-layout">

	<div>
		<table id="dg" style="width:90%;">

			<div id="tb" style="padding: 5px; background: #E8F1FF;">
				<span>流水号</span>
				<input id="lsh" type="text" class="easyui-validatebox"  style="height: 32px;">  
				<span>公司名称：</span>
				<input id="hyxsssdwmc" style="height: 32px;">
				<span>审批状态：</span>
				<select  class="easyui-combobox" id=bjjg style="width:100px;height: 32px;">   
   				 	<option value="">审批中</option>   
  				    <option value="0">已同意</option>    
  					<option value="1">已拒绝</option> 
  					<option value="-1">所有</option> 
				</select>
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		</table>
	</div>
	
    <!-- 行业协会详情 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:650px;height:450px;padding:10px 20px 20px 20px;" closed="true">
		<form id="dgform" class="easyui-form">
			<table class="table" id="table">
				<tr>
					<th>协会名称</th>
					<td><input  name=hyxhmc type="text" class="easyui-validatebox" style="height: 32px;width:100%;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>协会类别</th>
					<td><input  name=hyxhlb type="text" class="easyui-validatebox" style="height: 32px;width:100%;" readonly="readonly"></td>
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
					<td><input  name="hyxhsjzpe" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
				<tr>
					
					<th>剩余配额</th>
					<td><input  name="lastpe" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
			</table>
				
		</form>
	</div>
	
	 <!-- 单位信息详情 -->
	<div id="dgformDiv2" class="easyui-dialog"
		style="width:650px;height:450px;padding:10px 20px 20px 20px;" closed="true">
		<form id="dgform2" class="easyui-form">
			<table class="table" id="table2">
				<tr>
					<th>单位名称</th>
					<td><input  name=dwmc type="text" class="easyui-validatebox" style="height: 32px;width:100%;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>所属协会</th>
					<td><input  name=hyxhzhName type="text" class="easyui-validatebox" style="height: 32px;width:100%;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>组织机构代码证号</th>
					<td><input  name=zzjgdmzh type="text" class="easyui-validatebox" style="height: 32px;width:100%;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>地址</th>
					<td><input  name="zsdz" type="text" class="easyui-validatebox" style="height: 32px;width:100%;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>联系人</th>
					<td><input  name="lxr" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
				<tr>
					
					<th>联系电话</th>
					<td><input  name="lxdh" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>配额</th>
					<td><input  name="dwpe" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
				<tr>
					
					<th>营业执照图片</th>
					<td><img id="img2"  class="easyui-validatebox" style="width:300px"   /><br/></td>
				</tr>
			</table>
				
		</form>
	</div>
</body>
</html>