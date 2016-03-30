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

<title>备案列表详情</title>

<%@include file="../common/common.jsp"%>


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

		url : "<%=basePath%>statisticalAction/queryHyxhBaDetail?hyxhzh=" + hyxhzh,
		title :  "备案列表详情",
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
			field : 'DABH',
			title : '档案编号',
			align:'center',
			width : 120
		},{
			field : 'DWMC',
			title : '单位名称',
			align:'center',
			width : 220
		},{
			field : 'PPXH',
			title : '品牌型号',
			align:'center',
			width : 80
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
			field : 'JSRXM1',
			title : '驾驶人',
			align:'center',
			width : 120
		},{
			field : 'SFZMHM1',
			title : '身份证号码',
			align:'center',
			width : 220
		},{
			field : 'SYRQ',
			title : '受理日期',
			align:'center',
			width : 120,
			formatter:function(value,index){
				var unixTimestamp = new Date(value);   
				return unixTimestamp.toLocaleString();
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
	
	
});
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
		<table id="dg" style="width:90%;">
			<div id="tb" style="padding: 5px; background: #E8F1FF;">
				<span>协会名称名称：${hyxhBase.hyxhmc }</span>
			</div>
		</table>
		<!--endprint-->		
		
	</div>
	
	
</body>
</html>