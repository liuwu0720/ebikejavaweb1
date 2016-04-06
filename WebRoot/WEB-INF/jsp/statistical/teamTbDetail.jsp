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

<title>大队退办列表详情</title>

<%@include file="../common/common.jsp"%>


<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup ({
		   cache: false //缓存
		});
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	var team = '${team}';
	$("#dg").datagrid({

		url : "<%=basePath%>statisticalAction/queryTeamTbDetail?team=" + team,
		title :  "大队退办列表详情",
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
			field : 'LSH',
			title : '业务流水号',
			align:'center',
			width : 120
		},{
			field : 'PPXH',
			title : '品牌型号',
			align:'center',
			width : 80
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
			field : 'XSQY',
			title : '行驶区域',
			align:'center',
			width : 80
		},{
			field : 'SFZMHM1',
			title : '身份证号码',
			align:'center',
			width : 120
		},{
			field : 'HYXHMC',
			title : '协会名称',
			align:'center',
			width : 180,
			formatter:function(value,row,index){
				var query = "<a  href='javascript:void(0)'  onclick='queryHyxhDetail(\""+row.HYXHZH+"\")'>"+value+"</a>";
				return query
			}		
		},{
			field : 'DWMC',
			title : '单位名称',
			align:'center',
			width : 180,
			formatter:function(value,row,index){
				var query = "<a  href='javascript:void(0)'  onclick='queryHyxhDwDetail(\""+row.ZZJGDMZH+"\")'>"+value+"</a>";
				return query;	
			}
		},{
			field : 'SLRQ',
			title : '受理日期',
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
			width : 100,
			formatter:function(value,row,index){
				var query = "<a  href='javascript:void(0)'  onclick='queryRow("+row.ID+")'>查看详情</a>";
				return query;
			}
		}
		] ],
		toolbar : [ {
			id : 'btn1',
			text : '返回',
			iconCls : 'icon-back',
			handler : function() {
				history.go(-1);
			}
		}],
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
		 lsh: $("#lsh").val(),
		 djh: $('#djh').val(),
		 dtstart:$('#dtstart').datebox('getValue'),// 获取日期输入框的值)
		 dtend:$('#dtend').datebox('getValue'),
		 xsqy:$("#xsqy").combobox("getValue"),
		 dwmcId:$("#hyxsssdwmc").combobox("getValue"),
		 hyxhzh:$("#hyxhzh").combobox("getValue")
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
//查看详情
function queryRow(id){
	$.messager.progress({
		text:"正在处理，请稍候..."
	});
	window.location.href="<%=basePath%>statisticalAction/getFlowDetailById?id="+id ;
}
</script>
</head>
<body class="easyui-layout">

	<div>
		
	 
		<table id="dg" style="width:90%;">
			<div id="tb" class="searchdiv">
				<span>流水号</span>
				<input id="lsh" type="text" class="easyui-validatebox"></input>
				<span>电机号</span> <input id="djh" class="easyui-validatebox" type="text" >
				<span>行驶区域</span> <input id="xsqy" style="height: 32px;"  type="text" ><br>
				<span>受理时间</span>
				<input id="dtstart" type="text" class="easyui-datebox" style="height: 30px;"></input> 至：  
				<input id="dtend" type="text" class="easyui-datebox" style="height: 30px;"></input>		
				<span>协会名称</span>
				<input id="hyxhzh" style="height: 32px;">  
				<span>公司名称</span>
				<input id="hyxsssdwmc" style="height: 32px;">  
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		</table>
		
	</div>
		
	   <!-- 行业协会详情 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:650px;height:450px;padding:10px 20px 20px 20px;" closed="true">
		<form id="dgform" class="easyui-form">
			<table class="table input_border0" id="table">
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
			<table class="table input_border0" id="table2">
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