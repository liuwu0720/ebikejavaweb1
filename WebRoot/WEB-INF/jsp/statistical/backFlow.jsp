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

<title>区域统计</title>

<%@include file="../common/common.jsp"%>


<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup ({
		   cache: false //缓存
		});
	var h = getHeight('dg');
	
	var w = getWidth(400);
	var randomNu = (new Date().getTime()) ^ Math.random();
	$("#dg").datagrid({

		url : "<%=basePath%>statisticalAction/queryByBackFlow?time=" + randomNu,
		title :  "区域统计",
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		pageSize:50,
		singleSelect : true,//只选中单行
		height:h,
		loadMsg:'正在加载,请稍等...',
		columns : [ [{
			field : 'cname',
			title : '协会名称',
			align:'center',
			width : 220
		},{
			field : 'total',
			title : '总申报',
			align:'center',
			width : 220,
			formatter:function(value,row,index){
				if(row.ename !== 'total'){
					var detail = "<a  href='javascript:void(0)'  onclick='hySbdetail(\""+row.ename+"\")'>"+row.total+"</a>";
					return detail;
				}else{
					return value;
				}
			}
		},{
			field : 'sb',
			title : '已申报(总申报-已退办)',
			align:'center',
			width : 220
		},{
			field : 'ba',
			title : '备案',
			align:'center',
			width : 220,
			formatter:function(value,row,index){
				if(row.ename !== 'total'){
					var detail = "<a  href='javascript:void(0)'  onclick='hyBadetail(\""+row.ename+"\")'>"+value+"</a>";
					return detail;
				}else{
					return value;
				}
			}
		},{
			field : 'tb',
			title : '已退办',
			align:'center',
			width : 220,
			formatter:function(value,row,index){
				if(row.ename !== 'total'){
					var detail = "<a  href='javascript:void(0)'  onclick='tbBadetail(\""+row.ename+"\")'>"+value+"</a>";
					return detail;
				}else{
					return value;
				}
			}
		}
		] ],
		toolbar : [ {
			id : 'btn1',
			text : '打印',
			iconCls : 'icon-save',
			handler : function() {
				exportPage();
			}
		}],
		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	
	
});
//查询行业协会的已申报详情
function hySbdetail(obj){
	var hyxhzh = obj;
	window.location.href="<%=basePath%>statisticalAction/getHyxhSbDetail?hyxhzh="+hyxhzh;
}

//行业协会的备案列表详情
function hyBadetail(obj){
	var hyxhzh = obj;
	window.location.href="<%=basePath%>statisticalAction/getHyxhBaDetail?hyxhzh="+hyxhzh;
}
//退办
function tbBadetail(obj){
	var hyxhzh = obj;
	window.location.href="<%=basePath%>statisticalAction/getHyxhTbDetail?hyxhzh="+hyxhzh;
}


function exportPage() {
	$("#dg").css('width', '650px');
	var bdhtml=window.document.body.innerHTML;
	var startStr="<!--startprint-->";//设置打印开始区域 
	var endStr="<!--endprint-->";//设置打印结束区域 
	var printHtml=bdhtml.substring(bdhtml.indexOf(startStr)+startStr.length,bdhtml.indexOf(endStr));//从标记里获取需要打印的页面 
	window.document.body.innerHTML=printHtml;//需要打印的页面 
	window.print(); 
	window.document.body.innerHTML=bdhtml;//还原界面 
}
</script>
</head>
<body class="easyui-layout">

	<div>
	  <!--startprint-->
		<table id="dg" style="width:70%;">

		</table>
	<!--endprint-->			
	</div>
	
	
</body>
</html>