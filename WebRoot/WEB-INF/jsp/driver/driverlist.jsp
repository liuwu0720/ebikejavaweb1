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

<title>司机信息</title>

<%@include file="../common/common.jsp"%>


<script type="text/javascript">
$(document).ready(function(){
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);

	$("#dg").datagrid({

		url : "<%=basePath%>driverAction/queryAll" ,
		title :  "司机信息管理",
		iconCls : 'icon-danweixinxi',
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
			field : 'ID',
			title : '',
			align:'center',
			width : 120,
			hidden:true
		},{
			field : 'JSRXM',
			title : '驾驶人姓名',
			align:'center',
			width : 120
		},{
			field : 'XB',
			title : '性别',
			align:'center',
			width : 120,
			formatter:function(value,index){
				if(value == 0){
					return "男";
				}else if(value == 1){
					return "女";
				} 
				
			}   
		},{
			field : 'SFZHM',
			title : '身份证号',
			align:'center',
			width : 120
		},{
			field : 'LXDH',
			title : '联系电话',
			align:'center',
			width : 120
		},{
			field : 'USER_STATUS',
			title : '用户状态',
			align:'center',
			width : 120,
			formatter:function(value,index){
				if(value == 0){
					return "未认证";
				}else if(value == 1){
					return "已实名认证";
				}else{
					return "星级用户";
				}
			} 
		},{
			field : 'TRAN_DATE',
			title : '导入日期',
			align:'center',
			width : 120,
			formatter:function(value){
				var formateDate = new Date(value);
				return formateDate.toLocaleString();
			}
			
		},{
			field : 'XJ_RQ',
			title : '星级认证日期',
			align:'center',
			width : 120,
			formatter:function(value){
				var formateDate = new Date(value);
				return formateDate.toLocaleString();
			}
			
		},{
			field : 'XJ_MSG',
			title : '星级认证说明',
			align:'center',
			width : 120
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var query = "<a   onclick='queryRow("+row.ID+")'>查看</a>&nbsp;&nbsp;&nbsp;"
				var update = "<a   onclick='updateRow("+row.ID+")'>审核</a>&nbsp;&nbsp;&nbsp;"
				var del = "<a   onclick='deleteRow("+row.ID+")'>删除</a>&nbsp;&nbsp;&nbsp;"
				if(row.USER_STATUS>0){
					return query+update;
				}else{
					return query+update+del;
				}
				
			}
		}

		] ],
		onLoadSuccess:function(){  
            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        }
	});
	$('#hyxsssdwmc').combobox()
	
	$('#hyxhzh').combobox({    
	    url:'<%=basePath%>industryAction/getAllIndustry',    
	    valueField:'hyxhzh',    
	    textField:'hyxhmc',
	    onSelect:function(param){
	    	$('#hyxsssdwmc').combobox({
	    		 	url:'<%=basePath%>industryAction/getDwmcByHyxh?hyxhzh='+param.hyxhzh,    
	    		    valueField:'id',    
	    		    textField:'dwmc'
	    	})
		}
	});
});



//查询功能
function doSearch(){
	
	 $('#dg').datagrid('load',{
		 sfzhm: $('#sfzhm').val(),
		 jsrxm: $('#jsrxm').val(),
		 userStatus:$('#userStatus').combobox('getValue'),
		 hyxhzh:$("#hyxhzh").combobox("getValue"),
		 dwmcId:$("#hyxsssdwmc").combobox("getValue")
	}); 
}

//查看详情
function queryRow(id){
	$.messager.progress({
		text:"正在处理，请稍候..."
	});
	window.location.href="<%=basePath%>driverAction/queryInfoById?id="+id
}
//审核详情
function updateRow(id){
	$.messager.progress({
		text:"正在处理，请稍候..."
	});
	window.location.href="<%=basePath%>driverAction/updateInfoById?id="+id

}

function deleteRow(id){
	$.messager.confirm('警告', '该条记录将删除，请确认', function(r){
		if (r){
			$.post("<%=basePath%>driverAction/deleteRow", 
					{id:id},    
					   function (data, textStatus)
					   {     
							
						if (data.isSuccess) {
							$.messager.show({ // show error message
								title : '提示',
								msg : data.message
							});
							$("#dg").datagrid('reload');
						
						}else{
							alert(data.message);
							$("#dg").datagrid('reload');
						}
					   }
				  ,"json");
		}
	});
}


</script>
</head>
<body class="easyui-layout">

	<div>
		<div id="tb" class="searchdiv">
				<span>协会名称</span>
				<input id="hyxhzh" style="height: 32px;">  
				<span>公司名称：</span>
				<input id="hyxsssdwmc" style="height: 32px;"><br>
				<span>身份证号:</span> <input id="sfzhm" class="easyui-validatebox">
				<span>姓名:</span>
				<input id="jsrxm" class="easyui-validatebox">
				<span>状态</span>	
				<select class="easyui-combobox" style="width:100px;height:32px; " id="userStatus">
					<option value="0">未认证</option>
					<option value="1">实名认证</option>
					<option value="2">星级认证</option>
				</select>
				 <a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
				</div>	
			</div>
		<table id="dg" style="width:90%;">
		</table>
	</div>
	

</body>
</html>