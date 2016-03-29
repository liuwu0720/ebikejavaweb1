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

<title>退办查询</title>

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

		url : "<%=basePath%>statisticalAction/queryByBackFlow?time=" + randomNu,
		title :  "退办查询",
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		pageSize:size,
		singleSelect : true,//只选中单行
		height:h,
		loadMsg:'正在加载,请稍等...',
		columns : [ [{
			field : 'lsh',
			title : '流水号',
			align:'center',
			width : 220
		},{
			field : 'djh',
			title : '电机号',
			align:'center',
			width : 220
		},{
			field : 'slr',
			title : '受理人',
			align:'center',
			width : 220
		},{
			field : 'slbm',
			title : '受理部门',
			align:'center',
			width : 220
		},{
			field : 'slrq',
			title : '受理日期',
			align:'center',
			width : 220
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
		djh: $('#djh').val()
	}); 
}
</script>
</head>
<body class="easyui-layout">

	<div>
	  <!--startprint-->
		<table id="dg" style="width:70%;">
			<div id="tb" style="padding: 5px; background: #E8F1FF;">
				<span>流水号：</span>
				<input id="lsh" type="text" class="easyui-validatebox" name="lsh" ></input>
				<span>电机号:</span> <input id="djh" name="djh"
					class="easyui-validatebox" type="text" > &nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		</table>
	<!--endprint-->			
	</div>
	
	
</body>
</html>