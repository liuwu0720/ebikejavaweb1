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

<title>行业协会账户管理</title>

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

		url : "<%=basePath%>industryAction/queryAll?time=" + randomNu,
		title :  "行业协会账户管理",
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
			field : 'hyxhmc',
			title : '行业协会名称',
			align:'center',
			width : 120
		},{
			field : 'hyxhfzr',
			title : '行业协会负责人',
			align:'center',
			width : 220
		},{
			field : 'hyxhfzrdh',
			title : '负责人电话',
			align:'center',
			width : 120
		},{
			field : 'totalPe',
			title : '总配额',
			align:'center',
			width : 50
		},{
			field : 'hyxhsjzpe',
			title : '剩余配额',
			align:'center',
			width : 50
		},{
			field : 'hyxhlb',
			title : '车牌号码首字母',
			align:'center',
			width : 50
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
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var reset = "<a  href='javascript:void(0)'  onclick='resetRow("+row.id+")'>重置密码</a>&nbsp;&nbsp;&nbsp;";
				var del = "<a  href='javascript:void(0)'  onclick='deleteRow("+row.id+")'>删除</a>&nbsp;&nbsp;&nbsp;";
				return reset+del;	
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
//重置
function resetRow(id){
	$.messager.confirm('警告', '确认重置密码？', function(r){
		if (r){
			$.post("<%=basePath%>industryAction/reset", 
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



//删除
function deleteRow(id){
	$.messager.confirm('警告', '确认删除这条记录吗？', function(r){
		if (r){
			$.post("<%=basePath%>industryAction/del", 
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
	  $('#hyxhmc_tr,#hyxhlb_tr').show();
	  $("#hyxhsjzpe_tr").hide();
	 $("#hyxhlb").combobox({
		 	valueField:'label',    
		    textField:'value',
		    data: [{
				label: 'A',
				value: 'A'
			},{
				label: 'B',
				value: 'B'
			},{
				label: 'C',
				value: 'C'
			},{
				label: 'D',
				value: 'D'
			},{
				label: 'E',
				value: 'E'
			},{
				label: 'F',
				value: 'F'
			},{
				label: 'G',
				value: 'G'
			},{
				label: 'H',
				value: 'H'
			},{
				label: 'I',
				value: 'I'
			},{
				label: 'J',
				value: 'J'
			},{
				label: 'K',
				value: 'K'
			},{
				label: 'L',
				value: 'L'
			},{
				label: 'M',
				value: 'M'
			},{
				label: 'N',
				value: 'N'
			},{
				label: 'O',
				value: 'O'
			},{
				label: 'P',
				value: 'P'
			},{
				label: 'Q',
				value: 'Q'
			},{
				label: 'R',
				value: 'R'
			},{
				label: 'S',
				value: 'S'
			},{
				label: 'T',
				value: 'T'
			},{
				label: 'U',
				value: 'U'
			},{
				label: 'V',
				value: 'V'
			},{
				label: 'W',
				value: 'W'
			},{
				label: 'X',
				value: 'X'
			},{
				label: 'Y',
				value: 'Y'
			},{
				label: 'Z',
				value: 'Z'
			}]
		})
}


//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		 hyxhmc: $("#hyxhmc").val()
	}); 
}


//修改
function updateRow(){
	 $('#dgform').form('clear');
	 var row = $('#dg').datagrid('getSelected');
	   if (row){
		     $('#hyxhmc_tr,#hyxhlb_tr').hide();
		     $("#hyxhsjzpe_tr").show();
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
				url : "<%=basePath%>industryAction/saveOrUpdate",
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
		<div>
				<span>协会名称：</span>
				<input id="hyxhmc" type="text" class="easyui-validatebox"></input>
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		<table id="dg">
		</table>
	</div>
	
	
		<!-- 点查看时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:550px;height:450px;padding:10px 20px 20px 20px;"
		closed="true"  buttons="#dlg-buttons">
		<form id="dgform" class="easyui-form" enctype="multipart/form-data"
			method="post">
			<table id="table1" class="dialogtable borderinput">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id"></input>
					</td>
				</tr>
				<tr id="hyxhmc_tr">
					<th>协会名称:</th>
					<td><input  name="hyxhmc" class="easyui-validatebox" data-options="required:true" style="height: 32px;" >  
					</td>
				<tr>
					<th>协会账号：</th>
					<td><input name="hyxhzh" type="text" class="easyui-validatebox" data-options="required:true,validType:'english'"></td>
				</tr>
				
				<tr id="hyxhlb_tr">
					<th>车牌号码首字母</th>
					<td><input id="hyxhlb" name="hyxhlb" style="height: 32px;" ></td>
				</tr>
				<tr>
					<th>总配额</th>
					<td>
					<input   class="easyui-numberspinner" name="totalPe"
						data-options="increment:1,required:true,validType:'number'"  min="0"
						style="width:120px;height:30px;"></input>
					</td>
				</tr>
				<tr id="hyxhsjzpe_tr">
					<th>剩余配额</th>
					<td>
					<input   class="easyui-numberspinner" name="hyxhsjzpe"
						data-options="increment:1,validType:'number'"  min="0"
						style="width:120px;height:30px;"></input>
					</td>
				</tr>
				<tr>
					<th>负责人</th>
					<td>
						<input  name="hyxhfzr" type="text" class="easyui-validatebox" style="height: 32px;" > 
					</td>
				</tr>
				<tr>
					<th>负责人电话</th>
					<td>
						<input class="easyui-validatebox" type="text" 
						data-options="required:true,validType:'phoneNum'" name="hyxhfzrdh"
						style="height: 32px;"></input>
					</td>
				</tr>
				<tr>
					<th>协会地址</th>
					<td>
						<input  name="hyxhdz" type="text" class="easyui-validatebox" style="height: 32px;" > 
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td>
						<textarea rows="5" cols="30" name="bz"></textarea> 
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