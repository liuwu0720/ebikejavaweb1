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
	var hyxhzh = '${hyxhzh}';
	$("#dg").datagrid({

		url : "<%=basePath%>statisticalAction/queryByBackFlow?hyxhzh=" + hyxhzh,
		title :  "退办查询",
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
			width : 120
		},{
			field : 'DJH',
			title : '电机号',
			align:'center',
			width : 120
		},{
			field : 'SLR',
			title : '受理人',
			align:'center',
			width : 120
		},{
			field : 'SLBM',
			title : '受理部门',
			align:'center',
			width : 120
		},{
			field : 'SLRQ',
			title : '受理日期',
			align:'center',
			width : 120
		},{
			field : 'XSQY',
			title : '所属区域',
			align:'center',
			width : 120
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var query = "<a    onclick='queryRow("+row.ID+")'>查看详情</a>";
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
	
});

//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		 lsh: $("#lsh").val(),
		 djh: $('#djh').val(),
		 dtstart:$('#dtstart').datebox('getValue'),// 获取日期输入框的值)
		 dtend:$('#dtend').datebox('getValue'),
		 xsqy:$("#xsqy").combobox("getValue")
	}); 
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
	<div id="tb" class="searchdiv">
				<span>流水号</span>
				<input id="lsh" type="text" class="easyui-validatebox"></input>
				<span>电机号</span> <input id="djh" class="easyui-validatebox" type="text" >
				<span>行驶区域</span> <input id="xsqy" class="easyui-validatebox" type="text" ><br>
				<span>受理时间</span>
				<input id="dtstart" type="text" class="easyui-datebox" style="height: 30px;"></input> 至：  
				<input id="dtend" type="text" class="easyui-datebox" style="height: 30px;"></input>		
				  
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		<table id="dg" style="width:70%;">
		</table>
	
	</div>
	
	
</body>
</html>