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
	var dabh = '${dabh}';
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	var type = '${type}';
	$("#dg").datagrid({

		url : "<%=basePath%>statisticalAction/queryDabhFlowList?dabh=" +dabh,
		title :  "业务详情",
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
			field : 'HYXHMC',
			title : '行业协会名称',
			align:'center',
			width : 120
		},{
			field : 'DWMC',
			title : '单位名称',
			align:'center',
			width : 120
		},{
			field : 'SLRQ',
			title : '申请日期',
			align:'center',
			width : 120,
			formatter:function(value){
				var formatvalue = new Date(value);
				return formatvalue.toLocaleString();
			}
		},{
			field : 'SLYJ',
			title : '受理意见',
			align:'center',
			width : 120,
			formatter:function(value,row){
				if(value == 0){
					return "已同意";
				}else if(value == 1){
					return "已拒绝";
				}else{
					if(row.slIndex==1){
						return "等待民警审批";
					}else{
						return "等待领导审批";
					}
				}
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
		 dtstart:$('#dtstart').datebox('getValue'),// 获取日期输入框的值)
		 dtend:$('#dtend').datebox('getValue')
	}); 
}

function excelExport(){
	var titleArr = ["车牌号","电机号","档案编号","驾驶人1","行驶区域","所属协会","单位名称","申请日期"]; 
	var keysArr =["CPHM","DJH","DABH","JSRXM1","XSQYNAME","HYXHMC","DWMC","SLRQ"];
	var rows = $('#dg').datagrid('getData').rows;
	for(var i in rows) {
		if(rows[i]['SLYJ'] == 0){
			rows[i]['SLYJ'] = "办结";
		}else{
			rows[i]['SLYJ'] = "退办";
		}
		rows[i]['SLRQ'] = getLocalTime(rows[i]['SLRQ']);
	}
	var actionUrl = '<%=basePath%>ebikeAction/exportExcel';
	var fileName="业务详情";
	var content = JSON.stringify(rows);
	commonExcelExport(titleArr,keysArr,content,actionUrl,fileName);
	
	
}
</script>
</head>
<body class="easyui-layout">

	<div class="searchdiv">
		<div >
				<span>受理时间</span>
				<input id="dtstart" type="text" class="easyui-datebox" style="height: 30px;"></input>至  
				<input id="dtend" type="text" class="easyui-datebox" style="height: 30px;"></input>				
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		<table id="dg" style="width:90%;">
		</table>
	</div>
		
	
</body>
</html>