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

<title>电动车档案查询</title>
<style type="text/css">
#table  input{
	border: 0;
}
 #table2 input{
	border: 0;
}
</style>
<%@include file="../common/common.jsp"%>


<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup ({
		   cache: false //缓存
		});
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	var hyxhzh= '${hyxhBase.hyxhzh}';
	$("#dg").datagrid({

		url : "<%=basePath%>statisticalAction/queryBaList?hyxhzh="+ hyxhzh,
		title :  "电动车档案查询管理",
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
			field : 'HYXHMC',
			title : '行业协会名称',
			align:'center',
			width : 220,
			formatter:function(value,row,index){
				var query = "<a    onclick='queryHyxhDetail(\""+row.HYXHZH+"\")'>"+value+"</a>";
				return query;	
			}
		},{
			field : 'DWMC',
			title : '单位名称',
			align:'center',
			width : 220,
			formatter:function(value,row,index){
				var query = "<a    onclick='queryHyxhDwDetail(\""+row.SSDWID+"\")'>"+value+"</a>";
				return query;	
			}
		},{
			field : 'DABH',
			title : '档案编号',
			align:'center',
			width : 120
		},{
			field : 'CPHM',
			title : '车牌号',
			align:'center',
			width : 120
		},{
			field : 'DJH',
			title : '电机号',
			align:'center',
			width : 120
		},{
			field : 'JSRXM1',
			title : '驾驶人',
			align:'center',
			width : 120
		},{
			field : 'SFZMHM1',
			title : '身份证号码',
			align:'center',
			width : 120
		},{
			field : 'XSQY',
			title : '行驶区域',
			align:'center',
			width : 120
		},{
			field : 'SLYJ',
			title : '受理意见',
			align:'center',
			width : 120,
			formatter:function(value,index){
				if(value == 0){
					return '办结';
				}else{
					return '退办'
				}
			}
		},{
			field : 'ZT',
			title : '车辆状态',
			align:'center',
			width : 120,
			formatter:function(value,index){
				if(value == '注销'){
					
					return "<p style='color:red'>注销</p>";
				}else{
					return value;
				}
			}
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var query = "<a    onclick='queryRow("+row.ID+")'>查看</a>" 
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
	$('#hyxsssdwmc').combobox()
	
	$('#hyxhzh').combobox({    
	    url:'<%=basePath%>industryAction/getAllIndustry',    
	    valueField:'hyxhzh',    
	    textField:'hyxhmc',
	    onSelect:function(param){
	    	$('#hyxsssdwmc').combobox({
	    		 	url:'<%=basePath%>industryAction/getDwmcByHyxh?hyxhzh='+param.hyxhzh,    
	    		    valueField:'id',    
	    		    textField:'dwmc'
	    	})
		}
	});
});


//查询功能
function doSearch(){
	
	 $('#dg').datagrid('load',{
		dabh: $("#dabh").val(),
		djh: $('#djh').val(),
		cphm:$("#cphm").val(),
		jsrxm1:$("#jsrxm1").val(),
		sfzhm:$("#sfzhm").val(),
		 hyxhzh:$("#hyxhzh").combobox("getValue"),
		 dwmcId:$("#hyxsssdwmc").combobox("getValue")
	}); 
}


//查看
function queryRow(id){
	window.location.href="<%=basePath%>ebikeAction/queryInfoById?id="+id

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

	<div  class="searchdiv">
		<div id="tb" >

				<span>档案编号</span>
				<input id="dabh" type="text" class="easyui-validatebox" name="dabh" ></input>
				<span>电机号&nbsp;&nbsp;</span> <input id="djh" name="djh"
					class="easyui-validatebox" type="text" ><br/>

				<span>姓名</span> <input id="jsrxm1" name="jsrxm1"
					class="easyui-validatebox" type="text" >
				<span>协会名称</span>
				<input id="hyxhzh" style="height: 32px;">  
				<span>公司名称</span>
				<input id="hyxsssdwmc" style="height: 32px;">	
				
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		<table id="dg" style="width:90%;">

		</table>
	</div>
	
 <!-- 行业协会详情 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:500px;height:400px;padding:10px 20px 20px 20px;" closed="true">
		<form id="dgform" class="easyui-form">
			<table class="dialogtable">
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
					<td><input  name="totalPe" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
				<tr>
					
					<th>剩余配额</th>
					<td><input  name="hyxhsjzpe" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
			</table>
				
		</form>
	</div>
	
	 <!-- 单位信息详情 -->
	<div id="dgformDiv2" class="easyui-dialog"
		style="width:500px;height:400px;padding:10px 20px 20px 20px;" closed="true">
		<form id="dgform2" class="easyui-form">
			<table class="dialogtable">
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
					<th>总配额</th>
					<td><input  name="totalPe" type="text" class="easyui-validatebox" style="height: 32px;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>剩余配额</th>
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