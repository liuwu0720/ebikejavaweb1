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

		url : "<%=basePath%>statisticalAction/queryByBusiness?time=" + randomNu,
		title :  "业务量统计",
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
			field : 'cname',
			title : '行驶区域',
			align:'center',
			width : 120
		},{
			field : 'ba',
			title : '备案量',
			align:'center',
			width : 220,
			formatter:function(value,row,index){
				if(row.ename !== 'total'){
					var detail = "<a  href='javascript:void(0)'  onclick='badetail(\""+row.ename+"\")'>"+row.ba+"</a>";
					return detail;
				}else{
					return value;
				}
				
			}
		},{
			field : 'bg',
			title : '变更量',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				if(row.ename !== 'total'){
					var detail = "<a  href='javascript:void(0)'  onclick='bgdetail(\""+row.ename+"\")'>"+row.bg+"</a>";
					return detail;
				}else{
					return value;
				}
				
			}
		},{
			field : 'zy',
			title : '转移量',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				if(row.ename !== 'total'){
					var detail = "<a  href='javascript:void(0)'  onclick='zydetail(\""+row.ename+"\")'>"+row.zy+"</a>";
					return detail;
				}else{
					return value;
				}
				
			}
		},{
			field : 'zx',
			title : '注销量',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				if(row.ename !== 'total'){
					var detail = "<a  href='javascript:void(0)'  onclick='zxdetail(\""+row.ename+"\")'>"+row.zx+"</a>";
					return detail;
				}else{
					return value;
				}
				
			}
		},{
			field : 'jy',
			title : '检验量',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				if(row.ename !== 'total'){
					var detail = "<a  href='javascript:void(0)'  onclick='jydetail(\""+row.ename+"\")'>"+row.jy+"</a>";
					return detail;
				}else{
					return value;
				}
				
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
	
	
});
function excelExport(){
	var titleArr = ["行驶区域","备案量","变更量","转移量","注销量","检验量"]; 
	var keysArr =["cname","ba","bg","zy","zx","jy"];
	var rows = $('#dg').datagrid('getData').rows;
	var actionUrl = '<%=basePath%>ebikeAction/exportExcel';
	var fileName="业务量统计";
	var content = JSON.stringify(rows);
	commonExcelExport(titleArr,keysArr,content,actionUrl,fileName);
	
	
}

function badetail(obj){
	var areacode = obj;
	window.location.href="<%=basePath%>statisticalAction/getBesinessDetail?areacode="+areacode+"&&type=A";
}
function bgdetail(obj){
	var areacode = obj;
	window.location.href="<%=basePath%>statisticalAction/getBgBesinessDetail?areacode="+areacode+"&&type=B";
}
function zydetail(obj){
	var areacode = obj;
	window.location.href="<%=basePath%>statisticalAction/getBgBesinessDetail?areacode="+areacode+"&&type=C";
}
function zxdetail(obj){
	var areacode = obj;
	window.location.href="<%=basePath%>statisticalAction/getBgBesinessDetail?areacode="+areacode+"&&type=D";
}
function jydetail(obj){
	var areacode = obj;
	window.location.href="<%=basePath%>statisticalAction/getBesinessDetail?areacode="+areacode+"&&type=E";
}	
</script>
</head>
<body class="easyui-layout">

	<div>
		<table id="dg" style="width:90%;">
		</table>
	</div>
		
	
</body>
</html>