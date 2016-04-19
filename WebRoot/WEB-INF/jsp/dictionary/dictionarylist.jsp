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

<title>字典管理</title>

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

		url : "<%=basePath%>dictionaryAction/queryAll?time=" + randomNu,
		title :  "字典管理",
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
			field : 'dmlb',
			title : '代码类别',
			align:'center',
			width : 120
		},{
			field : 'bz',
			title : '备注',
			align:'center',
			width : 220
		},{
			field : 'dmz',
			title : '代码值',
			align:'center',
			width : 120
		},{
			field : 'dmms1',
			title : '代码描述',
			align:'center',
			width : 120
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var del = "<a    onclick='deleteRow("+row.id+")'>删除</a>&nbsp;&nbsp;&nbsp;";
				return del;	
			}
		}

		] ],
		toolbar : [ {
			id : 'btn1',
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				addRowData();
			}
		}, {
			id : 'btn2',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				updateRow();
			}
		}],

		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	
	
});
//删除
function deleteRow(id){
	$.messager.confirm('警告', '确认删除这条记录码', function(r){
		if (r){
			$.post("<%=basePath%>dictionaryAction/del", 
					{ id:id},     
					   function (data, textStatus){     
							
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
					   }
				  ,"json");
		}
	});
	
}
function addRowData(){
	 $('#dgformDiv').dialog('open').dialog('setTitle', '编辑信息');
	 $('#dgform').form('clear');
	 $("#dmlb").combobox({
		 	valueField:'label',    
		    textField:'value',
		    data: [{
				label: 'CSYS',
				value: 'CSYS'
			},{
				label: 'TBYY',
				value: 'TBYY'
			},{
				label: 'BASQZL',
				value: 'BASQZL'
			},{
				label: 'BGSQZL',
				value: 'BGSQZL'
			},{
				label: 'ZXSQZL',
				value: 'ZXSQZL'
			}],
			onSelect:function(record){
				var bzs=new Array("车身颜色","退办原因","备案申请资料","变更申请资料","注销申请资料");
			 switch(record.value){
					
					case 'CSYS':$("#bz").val(bzs[0]);break;
					case 'TBYY':$("#bz").val(bzs[1]);break;
					case 'BASQZL':$("#bz").val(bzs[2]);break;
					case 'BGSQZL':$("#bz").val(bzs[3]);break;
					case 'ZXSQZL':$("#bz").val(bzs[4]);break;
					default:$("#bz").val("");
				}
			}
		})
}


//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		 bz: $("#bz1").val(),
		 dmms1: $('#dmms1').val()
	}); 
}


//修改
function updateRow(){
	 $('#dgform').form('clear');
	 var row = $('#dg').datagrid('getSelected');
	   if (row){
	    	 $('#dgformDiv').dialog('open').dialog('setTitle', '编辑信息');
	    	 $('#dgform').form('load', row);
	     }else{
	    	 $.messager.alert('提示','请选择你要修改的行');    
	     } 

}

//保存操作

function updateSaveData(){
	$.messager.progress();
	$('#dgform').form('submit', {
				url : "<%=basePath%>dictionaryAction/saveOrUpdate",
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



</script>
</head>
<body class="easyui-layout">

	<div class="searchdiv">
		<div >
				<span>备注：</span>
				<input id="bz1" type="text" class="easyui-validatebox"></input>
				<span>代码描述:</span> <input id="dmms1" name="dmms1"
					class="easyui-validatebox" type="text" > &nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		<table id="dg" style="width:90%;">
		</table>
	</div>
	
	
		<!-- 点查看时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:550px;height:450px;padding:10px 20px 20px 20px;"
		closed="true"  buttons="#dlg-buttons">
		<form id="dgform" class="easyui-form" enctype="multipart/form-data"
			method="post">
			<table id="table1" class="table table-condensed">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id"></input>
					</td>
				</tr>
				<tr>
					<th>代码类别:</th>
					<td><input id="dmlb" name="dmlb" style="height: 32px;" >  
					</td>
				<tr>
					<th>备注：</th>
					<td><input name="bz" id="bz"  type="text" class="easyui-validatebox" ></td>
				</tr>
				
				<tr>
					<th>代码值</th>
					<td><input id="ss" class="easyui-numberspinner" name="dmz"
						data-options="increment:1,required:true,validType:'number'"  min="0"
						style="width:120px;height:30px;"></input></td>
				</tr>
			
				<tr>
					<th>代码描述</th>
					<td>
						<textarea rows="5" cols="30" name="dmms1"></textarea>  
					</td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons">
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