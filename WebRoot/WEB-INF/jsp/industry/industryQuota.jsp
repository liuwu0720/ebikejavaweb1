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

<title>行业协会配额管理</title>

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

		url : "<%=basePath%>industryAction/queryIndusryQuota?time=" + randomNu,
		title :  "行业协会配额管理",
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
			field : 'hyxhmc',
			title : '行业协会名称',
			align:'center',
			width : 220
		},{
			field : 'hyxhfzr',
			title : '行业协会负责人',
			align:'center',
			width : 120
		},{
			field : 'hyxhfzrdh',
			title : '负责人电话',
			align:'center',
			width : 120
		},{
			field : 'totalPe',
			title : '总配额',
			align:'center',
			width : 120
		},{
			field : 'hyxhsjzpe',
			title : '剩余配额',
			align:'center',
			width : 120
		},{
			field : 'hyxhlb',
			title : '车牌号码首字母',
			align:'center',
			width : 120
		},{
			field : 'cjr',
			title : '创建人',
			align:'center',
			width : 120
		},{
			field : 'cjrq',
			title : '创建日期',
			align:'center',
			width : 120,
			formatter:function(value,index){
				var unixTimestamp = new Date(value);   
				return unixTimestamp.toLocaleString();
			}   
		}
		/* ,{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var reset = "<a    onclick='upateRowQty("+row.id+")'>修改配额</a>&nbsp;&nbsp;&nbsp;";
				
				return reset;	
			}
		} */

		] ],
		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            $("a").removeAttr("href");
        }
	});
});


function excelExport (){

	var titleArr = ["行业协会名称","行业协会负责人","负责人电话","总配额","已有配额","车牌号码首字母","创建人","创建日期"]; 
	var keysArr =["hyxhmc","hyxhfzr","hyxhfzrdh","hyxhsjzpe","lastpe","hyxhlb","cjr","cjrq"];
	var rows = $('#dg').datagrid('getData').rows;
	for(var i in rows) {
		rows[i]['cjrq'] = getLocalTime(rows[i]['cjrq']);
	}
	var actionUrl = '<%=basePath%>ebikeAction/exportExcel';
	var fileName="行业协会信息";
	var content = JSON.stringify(rows);
	commonExcelExport(titleArr,keysArr,content,actionUrl,fileName);
}

//修改配额
function upateRowQty(id){
	$.ajax({
		url:"<%=basePath%>industryAction/queryIndustryById",
		data:{
			id:id
		},
		 dataType: "json",
		success:function(data){
			 if(data){
				 $('#dgformDiv').dialog('open').dialog('setTitle', '详情信息');
	 				$('#dgform').form('clear');
	 				 $('#dgform').form('load', data);
			 }
		}
	})
}


//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		 hyxhmc: $("#hyxhmc").val()
	}); 
}
//保存
function updateSaveData(){
	var flag = checkvalues();
	if(flag){
		$.messager.progress({
			text:"正在处理，请稍候..."
		});
		$('#dgform').form('submit', {
					url : "<%=basePath%>industryAction/saveOrUpdateQuota",
					onSubmit : function() {
						
						var isValid = $("#dgform").form('enableValidation').form(
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
							$('#dgformDiv').dialog('close');
							
							$("#dg").datagrid('reload');
						}else{
							alert(data.message);
						}
						$.messager.progress('close'); // 如果提交成功则隐藏进度条

					}

				});
	}
	
}
function checkvalues(){
	var totalPe = parseInt($("#totalPe").val());
	var hyxhsjzpe= parseInt($("#hyxhsjzpe").val());
	if(hyxhsjzpe > totalPe){
		$.messager.alert('警告',"剩余配额为"+lastpe+"不能小于这个数");    
		return false;
	}else{
		return true;
	}
}

</script>
</head>
<body class="easyui-layout">

	<div  class="searchdiv">
		
			<div >
				<span>协会名称：</span>
				<input id="hyxhmc" type="text" class="easyui-validatebox"></input>
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		<table id="dg" style="width:90%;">

		</table>
	</div>
	
	
	<!-- 点新增，编辑时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog" closed="true" style="width: 300px;"
		buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form" method="post"  >
			<table class="table">
				<tr>
					<td>总配额:</td>
					<td><input id="totalPe" class="easyui-numberspinner" name="totalPe"
						data-options="increment:1,required:true,validType:'number'"  min="0"  max="1000000"
						style="width:120px;height:30px;"></input></td>
				</tr>
				<tr>
					<td>剩余配额</td>
					<td><input type="text" id="hyxhsjzpe" name="hyxhsjzpe" readonly="readonly" style="border: 0px;color: red"> </td>
				</tr>
			</table>
			<input type="hidden" name="id" />
		</form>
		<div id="dlg-buttons2">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBtn"
				iconCls="icon-ok" onclick="updateSaveData()" style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#dgformDiv').dialog('close')"
				style="width:90px">取消</a>
		</div>
	</div>
	
	
</body>
</html>
