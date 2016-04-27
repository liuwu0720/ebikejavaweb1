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

<title>数据记录</title>

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
	grid = $("#dg").datagrid({

		url : "<%=basePath%>dataAction/queryAll?time=" + randomNu,
		title :  "文件记录",
		iconCls : 'icon-search',
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		pageSize:20,
		singleSelect : true,//只选中单行
		 autoRowHeight:true,
		 width:w,
		 height:h, 
		loadMsg:'正在加载,请稍等...',
		columns : [ [{
			field : 'fileName',
			title : '文件名称',
			align:'center',
			width : 220,
			formatter:function(value,row){
				return "<a href='"+row.filePath+"'>"+value+"</a>"
			}
		},{
			field : 'dateTime',
			title : '操作时间',
			align:'center',
			width : 120,
			formatter:function(value){
				var timevalue = new Date(value);
				return timevalue.toLocaleString();
			}
		},{
			field : 'flag',
			title : '操作类型',
			align:'center',
			width : 120,
			formatter:function(value){
				if(value == 0){
					return "导入";
				}else{
					return "导出";
				}
					
			}
		}
		] ],
		toolbar : [ {
			id : 'btn1',
			text : '导出内网数据',
			iconCls : 'icon-redo',
			handler : function() {
				exportRowData();
			}
		}],
		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	

});

function exportRowData(){
	$.ajax({
		type:'post',							
		url: "<%=basePath%>dataAction/exportExcel",
		dataType: 'html',
		success:function(data){		
			
			if(data=='2'){
				alert('下载失败!');
			}else{
				//var path = ""+data;
				//var path = data;
				//alert(data);
				$("#dg").datagrid("reload");
				window.location = data;
			}
		}						
	});
}
function uploadFile(){
	$('#fileform').form('submit', {
		url : "<%=basePath%>dataAction/importExcel",
		onSubmit : function() {
			var isValid = $("#fileform").form('enableValidation').form(
					'validate');

			if (!isValid) {
				$.messager.progress('close'); // 如果表单是无效的则隐藏进度条
			}
			return isValid; // 返回false终止表单提交
		},
		success : function(data) {
			var data = eval('(' + data + ')'); // change the JSON
			if (data.isSuccess) {
				$.messager.show({ // show error message
					title : '提示',
					msg : data.message
				});
				$("#dg").datagrid("reload");
			}else{
				alert(data.message);
			}
			$.messager.progress('close'); // 如果提交成功则隐藏进度条

		}

	});
}


</script>
</head>
<body  class="easyui-layout">

	<div class="searchdiv">
		<div >
			
			<form id="fileform" class="easyui-form" action="<%=basePath%>dataAction/importExcel" method="post"  enctype="multipart/form-data">
				<span>导入外网文件</span><input id="card2img_jsr2" type="file" data-options="required:true"
						name="fileinput"  />
				<button type="button" onclick="uploadFile()" class="btn">上传</button>			
			</form>		
		</div>
		<table id="dg" style="width:90%;">
		</table>
	</div>
	
	
	
</body>
</html>