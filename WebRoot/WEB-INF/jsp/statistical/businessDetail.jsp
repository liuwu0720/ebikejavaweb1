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

<title>业务量统计</title>
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
	var type = '${type}';
	var typeName = "";
	if(type == 'A'){
		typeName="备案";
	}
	if(type == 'B'){
		typeName="变更";
	}
	if(type == 'C'){
		typeName="转移";
	}
	if(type == 'D'){
		typeName="注销";
	}
	if(type == 'E'){
		typeName="检验";
	}
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	
	$("#dg").datagrid({

		url : "<%=basePath%>statisticalAction/queryByBusinessDetail?area=" +area+"&type="+type,
		title :  typeName+"业务详情",
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
			field : 'ddmc',
			title : '大队名称',
			align:'center',
			width : 120
		},{
			field : 'zdmc',
			title : '中队名称',
			align:'center',
			width : 220
		},{
			field : 'slr',
			title : '民警',
			align:'center',
			width : 120
		},{
			field : 'dabh',
			title : '档案编号',
			align:'center',
			width : 120
		},{
			field : 'cphm',
			title : '车牌号码',
			align:'center',
			width : 120
		},{
			field : 'djh',
			title : '电机号',
			align:'center',
			width : 120
		},{
			field : 'slrq',
			title : '受理日期',
			align:'center',
			width : 120
		},{
			field : 'null',
			title : '操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var detail = "<a  href='javascript:void(0)'  onclick='getDetail("+row.id+")'>详情信息</a>&nbsp;&nbsp;&nbsp;";
				return detail;	
			}
		}
		] ],
		toolbar : [ {
			id : 'btn1',
			text : '导出',
			iconCls : 'icon-print',
			handler : function() {
				excelExport();
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
function getDetail(id){
	window.location.href="<%=basePath%>statisticalAction/getFlowDetailById?id="+id;
}
//导出excel
function excelExport(){
	var titleArr = ["大队名称","中队名称","民警","档案编号","车牌号码","电机号","受理日期"]; 
	var keysArr =["ddmc","zdmc","slr","dabh","cphm","djh","slrq"];
	var rows = $('#dg').datagrid('getData').rows;
	var actionUrl = '<%=basePath%>ebikeAction/exportExcel';
	var fileName="业务量统计";
	var content = JSON.stringify(rows);
	commonExcelExport(titleArr,keysArr,content,actionUrl,fileName);
	
	
}

//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		djh: $('#djh').val(),
		cphm:$("#cphm").val(),
		 dwmcId:$("#hyxsssdwmc").combobox("getValue"),
		 xsqy:$("#xsqy").combobox("getValue"),
		 hyxhzh:$("#hyxhzh").combobox("getValue")
	}); 
}
</script>
</head>
<body class="easyui-layout">

	<div class="searchdiv">
		<div>
				<span>协会名称</span>
				<input id="hyxhzh" style="height: 32px;">  
				<span>公司名称</span>
				<input id="hyxsssdwmc" style="height: 32px;">
				<span>行驶区域</span>
				<input id="xsqy" style="height: 32px;width: 80px;"><br>
				<span>车牌号</span>
				<input id="cphm" type="text" class="easyui-validatebox"></input>
				<span>电机号</span> <input id="djh" name="djh"
					class="easyui-validatebox" type="text" >
				
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		<table id="dg" style="width:90%;">
		</table>
	</div>
	
	</div>
</body>
</html>