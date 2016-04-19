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

<title>流水查询</title>

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

		url : "<%=basePath%>statisticalAction/queryByWater?time=" + randomNu,
		title :  "流水查询",
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
			title : '流水号',
			align:'center',
			width : 220
		},{
			field : 'CPHM',
			title : '车牌号码',
			align:'center',
			width : 220
		},{
			field : 'DJH',
			title : '电机号',
			align:'center',
			width : 220
		},{
			field : 'SLRQ',
			title : '受理日期',
			align:'center',
			width : 220,
			formatter:function(value,index){
				var unixTimestamp = new Date(value);   
				return unixTimestamp.toLocaleString();
			}
			
			
		},{
			field : 'YWLX',
			title : '业务类型',
			align:'center',
			width : 220
		},{
			field : 'null',
			title : '详细信息',
			align:'center',
			width : 220,
			formatter:function(value,row,index){
				var detail = "<a    onclick='flowDetail("+row.ID+")'>详细信息</a>";
				return detail;
			}
		}
		] ],
		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	
	$("#ywlx").combobox({
		 url:"<%=basePath%>ebikeAction/getDdcSjzds?dmlb=YWLX ",    
		 valueField:'dmz',    
		 textField:'dmms1'
	})
});

//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		 ywlx:$("#ywlx").combobox("getValue"),
		 cphm: $("#cphm").val(),
		djh: $('#djh').val(),
		lsh:$("#lsh").val()
	}); 
}
//查看业务流水 详情
function flowDetail(id){
	$.messager.progress({
		text:"正在处理，请稍候..."
	});
	window.location.href="<%=basePath%>statisticalAction/getFlowDetailById?id="+id ;
	
}
</script>
</head>
<body class="easyui-layout">

	<div class="searchdiv">
	  	<div id="tb" >
				<span>业务类型：</span>
				<input id="ywlx"  style="height: 32px;">
				<span>车牌号：</span>
				<input id="cphm" type="text" class="easyui-validatebox"  ></input>
				<span>电机号:</span> <input id="djh" 
					class="easyui-validatebox" type="text" >
				<span>流水号:</span> <input id="lsh" 
					class="easyui-validatebox" type="text" >	
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		<table id="dg" style="width:70%;">
		</table>
	
	</div>
	
	
</body>
</html>