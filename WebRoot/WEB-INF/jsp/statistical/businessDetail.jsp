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

<title>业务量统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<%@include file="../common/common.jsp"%>


<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup ({
		   cache: false //缓存
		});
	var area = '${area}';
	var type = '${type}';
	var typeName = "";
	if(type == 'A'){
		typeName="备案";
	}
	if(type == 'B'){
		typeName="变更";
	}
	if(type == 'C'){
		typeName="转移";
	}
	if(type == 'D'){
		typeName="注销";
	}
	if(type == 'E'){
		typeName="检验";
	}
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	
	$("#dg").datagrid({

		url : "<%=basePath%>statisticalAction/queryByBusinessDetail?area=" +area+"&type="+type,
		title :  typeName+"业务详情",
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
			field : 'id',
			title : 'id',
			checkbox : true,
			align:'center',
			width : 120
		},{
			field : 'ddmc',
			title : '大队名称',
			align:'center',
			width : 120
		},{
			field : 'zdmc',
			title : '中队名称',
			align:'center',
			width : 220
		},{
			field : 'slr',
			title : '民警',
			align:'center',
			width : 120
		},{
			field : 'dabh',
			title : '档案编号',
			align:'center',
			width : 120
		},{
			field : 'cphm',
			title : '车牌号码',
			align:'center',
			width : 120
		},{
			field : 'djh',
			title : '电机号',
			align:'center',
			width : 120
		},{
			field : 'slrq',
			title : '受理日期',
			align:'center',
			width : 120
		},{
			field : 'null',
			title : '操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var detail = "<a  href='javascript:void(0)'  onclick='getDetail("+row.id+")'>详情信息</a>&nbsp;&nbsp;&nbsp;";
				return detail;	
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
function getDetail(id){
	window.location.href="<%=basePath%>statisticalAction/getFlowDetailById?id="+id;
}

//查询功能
function doSearch(){
	 $('#dg').datagrid('load',{
		dabh: $("#dabh").val(),
		djh: $('#djh').val(),
		cphm:$("#cphm").val(),
		 dwmcId:$("#hyxsssdwmc").combobox("getValue"),
		 xsqy:$("#xsqy").combobox("getValue"),
		 hyxhzh:$("#hyxhzh").combobox("getValue"),
	}); 
}
</script>
</head>
<body class="easyui-layout">

	<div>
		<table id="dg" style="width:90%;">

			<div id="tb" style="padding-top: 5px; background: #E8F1FF;width: 100%;">
				<span>协会名称：</span>
				<input id="hyxhzh" style="height: 32px;">  
				<span>公司名称：</span>
				<input id="hyxsssdwmc" style="height: 32px;">
				<span>行驶区域：</span>
				<input id="xsqy" style="height: 32px;width: 80px;">
				<span>档案编号：</span>
				<input id="dabh" type="text" class="easyui-validatebox" name="dabh" ></input>
				<span>电机号:</span> <input id="djh" name="djh"
					class="easyui-validatebox" type="text" > &nbsp;&nbsp;&nbsp;
				
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		</table>
	</div>
		
	
		<!-- 点查看时弹出的表单 -->
	<div id="dgformDiv2" class="easyui-dialog"
		style="width:850px;height:550px;padding:10px 20px 20px 20px;"
		closed="true" >
		<form id="dgform2" class="easyui-form" enctype="multipart/form-data"
			method="post">
			<table id="table1" class="table table-condensed">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id"></input>
					</td>
				</tr>
				<tr>
					<th>申报单位：</th>
					<td><input  name="zzjgdmzhName" class="easyui-validatebox" type="text" style="height:30px;width: 200px;" readonly="readonly"></td>					
					<th>业务流水号：</th>
					<td><input name="lsh"  type="text" class="easyui-validatebox" readonly="readonly"></td>
				</tr>
				<tr>
					<th>业务类型：</th>
					<td><input  name="ywlxName" class="easyui-validatebox" type="text" readonly="readonly"></td>					
					<th>业务原因：</th>
					<td><input name="ywyyName"  type="text" class="easyui-validatebox" readonly="readonly"></td>
				</tr>
				
				<tr>
					<th>品牌型号</th>
					<td><input class="easyui-validatebox" type="text" readonly="readonly"
						data-options="required:true" name="ppxh"
						style="height: 32px;"></input></td>
					<th>车身颜色</th>
					<td><input  name="cysyName" type="text" style="height:30px;" readonly="readonly"></td>
				</tr>
				<tr>
					<th>电机号：</th>
					<td><input class="easyui-validatebox" type="text"
						 name="djh" style="height: 32px" readonly="readonly"></input>
					</td>
					<th>脚踏装置:</th>
					<td><select class="easyui-combobox" name="jtzz"  readonly="readonly"
						style="height:32px;width: 50px;">
							<option value="0">有</option>
							<option value="1">无</option>
					</select></td>
				</tr>
				<tr>
					<th>驾驶人姓名1</th>
					<td><input class="easyui-validatebox" type="text"  readonly="readonly"
					       name="jsrxm1" style="height: 32px"></td>

					<th>驾驶人姓名2</th>
					<td><input class="easyui-validatebox" type="text"  readonly="readonly"
						 name="jsrxm2" style="height: 32px"></td>
				</tr>
				<tr>
					<th>驾驶人性别1</th>
					<td><select  id="xb1" class="easyui-combobox" name="xb1"   readonly="readonly"
						style="height:32px;width: 100px;">
							<option value="-1">请选择</option>
							<option value="0">男</option>
							<option value="1">女</option>
					</select></td>

					<th>驾驶人性别2</th>
					<td><select id="xb2" class="easyui-combobox" name="xb2"    readonly="readonly"
						style="height:32px;width: 100px;">
							<option value="-1">请选择</option>
							<option value="0">男</option>
							<option value="1">女</option>
					</select></td>
				</tr>
				<tr>
					<th>身份证号码1</th>
					<td><input class="easyui-validatebox" type="text" id="sfzmhm1"  readonly="readonly"
						 name="sfzmhm1" style="height: 32px">
					</td>
					<th>身份证号码2</th>
					<td><input class="easyui-validatebox" type="text"  readonly="readonly" 
					  name="sfzmhm2" style="height: 32px">
					</td>
				</tr>
				<tr>
					<th>联系电话1</th>
					<td><input class="easyui-validatebox" type="text"  readonly="readonly"
						 name="lxdh1" style="height: 32px">
					</td>
					<th>联系电话2</th>
					<td><input class="easyui-validatebox" type="text"  readonly="readonly"
						 name="lxdh2" style="height: 32px">
					</td>
				</tr>
				<tr>
					<th>行驶区域</th>
					<td><input id="xsqy" name="xsqyName"  style="height:30px;"  readonly="readonly" ></td>
					<th>备注</th>
					<td><textarea rows="5" cols="25" name="bz"></textarea></td>
				</tr>
				<tr>
					<th>受理意见</th>
					<td><input id="slyj" name="slyj"  style="height:30px;"   readonly="readonly" ></td>
					<th>受理日期</th>
					<td><input id="xsqy" name="slrq"  style="height:30px;"   readonly="readonly" ></td>
				</tr>
				<tr>
					<th>受理资料</th>
					<td colspan="3"><input id="slzlList" name="slzlList"  style="height:30px;width:100%"   readonly="readonly" ></td>
				</tr>
				<tr>
					<th>归档意见</th>
					<td><input id="slyj" name="gdyj"  style="height:30px;"   readonly="readonly" ></td>
					<th>归档日期</th>
					<td><input id="xsqy" name="gdrq"  style="height:30px;"   readonly="readonly" ></td>
				</tr>
					<tr>
					<td colspan="2">
					<div  class="imgdiv">
					<p>驾驶人1</p><img id="img1_1"  src="<%=basePath%>static/images/iconfont-wu.png"/></div>
					</td>
					<td colspan="2">
					<div  class="imgdiv">
					<p>驾驶人2</p><img id="img2_2" src="<%=basePath%>static/images/iconfont-wu.png" /></div></td>
				</tr>
				<tr>
					<th>车身照片</th>
					<td colspan="3"><div  class="imgdiv">
					<img id="img_0"  src="<%=basePath%>static/images/iconfont-wu.png" /></div></td>
				</tr>
			</table>
		</form>
	
	</div>
</body>
</html>