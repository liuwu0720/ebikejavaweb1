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

<title>变更业务量统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<%@include file="../common/common.jsp"%>


<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup ({
		   cache: false //缓存
		});
	var area = '${area}';
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	var type = '${type}';
	$("#dg").datagrid({

		url : "<%=basePath%>statisticalAction/queryByBgBusinessDetail?area=" +area+"&&type="+type,
		title :  "变更业务详情",
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		pageSize:size,
		singleSelect : true,//只选中单行
		height:h,
		width:w,
		loadMsg:'正在加载,请稍等...',
		toolbar : [ {
			id : 'btn1',
			text : '导出',
			iconCls : 'icon-print',
			handler : function() {
				excelExport();
			}
		}],
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
			field : 'CPHM',
			title : '车牌号码',
			align:'center',
			width : 120
		},{
			field : 'DJH',
			title : '电机号',
			align:'center',
			width : 120
		},{
			field : 'DABH',
			title : '档案编号',
			align:'center',
			width : 120
		},{
			field : 'JSRXM1',
			title : '驾驶人1',
			align:'center',
			width : 120
		},{
			field : 'XSQYNAME',
			title : '所属区域',
			align:'center',
			width : 120
		},{
			field : 'SLRQ',
			title : '受理日期',
			align:'center',
			width : 120,
			formatter:function(value){
				var formatvalue = new Date(value);
				return formatvalue.toLocaleString();
			}
		},{
			field : 'null',
			title : '操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var detail = "<a    onclick='getDetail("+row.ID+")'>详情信息</a>&nbsp;&nbsp;&nbsp;";
				return detail;	
			}
		}
		] ],
		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            $("a").removeAttr("href");
        }
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
function getDetail(id){
	window.location.href="<%=basePath%>statisticalAction/getFlowDetailById?id="+id;
}

//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		dabh: $("#dabh").val(),
		djh: $('#djh').val(),
		cphm:$("#cphm").val(),
		dwId:$("#hyxsssdwmc").combobox("getValue"),
		 hyxhzh:$("#hyxhzh").combobox("getValue"),
		 dtstart:$('#dtstart').datebox('getValue'),// 获取日期输入框的值)
		 dtend:$('#dtend').datebox('getValue')
	}); 
}

function excelExport(){
	var titleArr = ["车牌号","电机号","档案编号","驾驶人1","行驶区域","受理日期"]; 
	var keysArr =["CPHM","DJH","DABH","JSRXM1","XSQYNAME","SLRQ"];
	var rows = $('#dg').datagrid('getData').rows;
	
	var actionUrl = '<%=basePath%>ebikeAction/exportExcel';
	var fileName="业务详情";
	var content = JSON.stringify(rows);
	commonExcelExport(titleArr,keysArr,content,actionUrl,fileName);
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

	<div class="searchdiv">
		<div >
				<span>协会名称</span>
				<input id="hyxhzh" style="height: 32px;">  
				<span>公司名称</span>
				<input id="hyxsssdwmc" style="height: 32px;">
				<span>档案编号</span>
				<input id="dabh" type="text" style="width: 80px;"></input>
				<span>电机号</span> <input id="djh"  type="text" ><br>
				<span>车牌号</span> <input id="cphm"type="text" >
				<span>受理时间</span>
				<input id="dtstart" type="text" class="easyui-datebox" style="height: 30px;"></input>至  
				<input id="dtend" type="text" class="easyui-datebox" style="height: 30px;"></input>				
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