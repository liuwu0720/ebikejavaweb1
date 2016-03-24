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

<title>电动车档案更正</title>

<%@include file="../common/common.jsp"%>


<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup ({
		   cache: false //缓存
		});
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	var flag = 'update';
	$("#dg").datagrid({

		url : "<%=basePath%>ebikeAction/queryAll?flag=" +flag ,
		title :  "电动车档案更正",
		iconCls : 'icon-search',
		striped : true,
		fitColumns:true,   //数据列太少 未自适应
		pagination : true,
		rownumbers : true,
		pageSize:size,
		singleSelect : true,//只选中单行
		height:h,
		loadMsg:'正在加载,请稍等...',
		columns : [ [{
			field : 'ID',
			title : 'ID',
			checkbox : true,
			align:'center',
			width : 120
		},{
			field : 'DWMC',
			title : '单位名称',
			align:'center',
			width : 120
		},{
			field : 'DABH',
			title : '档案编号',
			align:'center',
			width : 220
		},{
			field : 'CPHM',
			title : '车牌号',
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
			width : 120
		},{
			field : 'XSQY',
			title : '行驶区域',
			align:'center',
			width : 120
		},{
			field : 'GDYJ',
			title : '归档意见',
			align:'center',
			width : 120,
			formatter:function(value,index){
				if(value == 0){
					return '办结';
				}else{
					return '退办'
				}
			}
		},{
			field : 'ZT',
			title : '车辆状态',
			align:'center',
			width : 120,
			formatter:function(value,index){
				if(value == '注销'){
					
					return "<p style='color:red'>注销</p>";
				}else{
					return value;
				}
			}
		},{
			field : 'null',
			title:'操作',
			align:'center',
			width : 120,
			formatter:function(value,row,index){
				var update  = "<a  href='javascript:void(0)'  onclick='updateRow("+row.ID+")'>更正</a>";
				var query = "<a  href='javascript:void(0)'  onclick='queryRow("+row.ID+")'>查看</a>&nbsp;&nbsp;&nbsp;";
				return query+update;	
				
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
		dabh: $("#dabh").val(),
		djh: $('#djh').val(),
		cphm:$("#cphm").val(),
		jsrxm1:$("#jsrxm1").val(),
		sfzhm:$("#sfzhm").val(),
		xsqy: $("#xsqy").combobox("getValue")
	}); 
}


//查看
function queryRow(id){
	window.location.href="<%=basePath%>ebikeAction/queryInfoById?id="+id

}

//修改
function updateRow(id){
	$('#dgform').form('clear');

	$('#pe').hide();
	$('#dw').attr("readonly",true);
	$.ajax({
		type: "GET",
   	    url: "<%=basePath%>ebikeAction/queryDdcDaxxbById",
   	   data:{
		  id:id
	   }, 
	   dataType: "json",
	   success:function(data){
 			  if(data){
 				 $('#dgformDiv').dialog('open').dialog('setTitle', '详情信息');
 				 $('#dgform').form('load', data);
 				 if(data.vcShowEbikeImg == null){
 					 $("#img").attr("src","<%=basePath%>static/images/iconfont-wu.png");
 				 }else{
 					 $("#img").attr("src",data.vcShowEbikeImg);
 				 }
 				 if(data.vcShowUser1Img == null){
 					 $("#img1").attr("src","<%=basePath%>static/images/iconfont-wu.png");
 				 }else{
 					 $("#img1").attr("src",data.vcShowUser1Img);
 				 }
 				if(data.vcShowUser2Img == null){
					 $("#img2").attr("src","<%=basePath%>static/images/iconfont-wu.png");
				 }else{
					 $("#img2").attr("src",data.vcShowUser2Img);
				 }
 			
 				//车身颜色
 				$('#cysy').combobox({
 					 url:'<%=basePath%>ebikeAction/getAllColorsAjax',    
 					    valueField:'dmz',    
 					    textField:'dmms1',
 					    value:data.cysy  //默认选中的值       
 				});
 				//行驶区域
 				$('#xsqy2').combobox({
 					 url:'<%=basePath%>ebikeAction/getArea',    
 					    valueField:'dmz',    
 					    textField:'dmms1',
 					    value:data.xsqy   //默认选中的值       
 				})
 			  }
 		  }
	})
}


var AllowExt=".jpg|.jpeg|.gif|.bmp|.png|" //允许上传的文件类型 ŀ为无限制 每个扩展名后边要加一个"|" 小写字母表示
function CheckFileSize(obj){
	 if(obj.value != ""){
         //检测类型
         var val = obj.value;
         var FileExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
         if(AllowExt.indexOf(FileExt+"|") == -1){//判断文件类型是否允许上传
        	 $.messager.alert('警告','你上传的不是图片文件');    
         	return false;
         }
	 }     
}



//保存操作
function updateSaveData(){
	$.messager.progress({
		text:"正在处理，请稍候..."
	});
	$('#dgform').form('submit', {
				url : "<%=basePath%>ebikeAction/saveOrUpdate",
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

	<div>
		<table id="dg" style="width:90%;">

			<div id="tb" style="padding: 5px; background: #E8F1FF;">
				<span>行驶区域</span>
				<input id="xsqy" style="height: 32px;">  
				<span>档案编号：</span>
				<input id="dabh" type="text" class="easyui-validatebox" name="dabh" ></input>
				<span>电机号:</span> <input id="djh" name="djh"
					class="easyui-validatebox" type="text" > &nbsp;&nbsp;&nbsp;
				<span>姓名:</span> <input id="jsrxm1" name="jsrxm1"
					class="easyui-validatebox" type="text" > &nbsp;&nbsp;&nbsp;
				<span>身份证号:</span> <input id="sfzhm" name="sfzhm"
					class="easyui-validatebox" type="text" > &nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" plain="true" onclick="doSearch()"
					iconCls="icon-search">查询 </a>
			</div>
		</table>
	</div>
	
	<!-- 点新增，编辑时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:850px;height:550px;padding:10px 20px 20px 20px;"
		closed="true">
		<form id="dgform" class="easyui-form" enctype="multipart/form-data"
			method="post">
			<table class="table">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id"></input>
					</td>
				</tr>
				<tr>
					<td>车辆状态：</td>
					<td><input  name="ztName" style="height:30px;width: 200px;" readonly="readonly"></td>
					<td>车牌号码</td>
					<td><input  name="cphm" style="height:30px;width: 200px;"/><br /></td>
				</tr>
				<tr>
					<td>申报单位：</td>
					<td><input id="dw" name="zzjgdmzhName" style="height:30px;width: 200px;"><span id="pe" style="color: red;display: none"></span></td>
					<td>车身照片</td>
					<td><input  type="file" id="file_upload"
						name="file_upload" /><br /></td>
				</tr>
				<tr>
					<td>品牌型号</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:true" name="ppxh"
						style="height: 32px;"></input></td>
					<td>车身颜色</td>
					<td><input id="cysy" name="cysy" style="height:30px;"></td>
				</tr>
				<tr>
					<td>电机号：</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:true" name="djh" style="height: 32px"></input>
					</td>
					<td>脚踏装置:</td>
					<td><select id="jtzz" class="easyui-combobox" name="jtzz"
						style="height:32px;width: 50px;">
							<option value="0">有</option>
							<option value="1">无</option>
					</select></td>
				</tr>
				<tr>
					<td>驾驶人姓名1</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:true" name="jsrxm1" style="height: 32px"></td>

					<td>驾驶人姓名2</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:false" name="jsrxm2" style="height: 32px"></td>
				</tr>
				<tr>
					<td>驾驶人性别1</td>
					<td><select id="xb1" class="easyui-combobox" name="xb1" required="true"  
						style="height:32px;width: 100px;">
						    <option value="-1">--请选择--</option>
							<option value="0">男</option>
							<option value="1">女</option>
					</select></td>
					<td>驾驶人性别2</td>
					<td><select id="xb2" class="easyui-combobox" name="xb2" 
						style="height:32px;width: 100px;">
						    <option value="-1">--请选择--</option>
							<option value="0">男</option>
							<option value="1">女</option>
					</select></td>
				</tr>
				<tr>
					<td>身份证号码1</td>
					<td><input class="easyui-validatebox" type="text" id="sfzmhm1"
						data-options="required:true,validType:'idcard'" name="sfzmhm1" style="height: 32px">
					</td>
					<td>身份证号码2</td>
					<td><input class="easyui-validatebox" type="text"  validType="notequals['#sfzmhm1']" 
					  name="sfzmhm2" style="height: 32px">
					</td>
				</tr>
				<tr>
					<td>联系电话1</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:true,validType:'phoneNum'" name="lxdh1" style="height: 32px">
					</td>
					<td>联系电话2</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:false,validType:'phoneNum'" name="lxdh2" style="height: 32px">
					</td>
				</tr>
				<tr>
					<td>驾驶人照片1</td>
					<td><input  type="file" 
						name="file_upload1" onchange="CheckFileSize(this);" /><br /></td>
					<td>驾驶人照片2</td>
					<td><input  type="file" id="file_upload2"
						name="file_upload2" /><br /></td>
				</tr>
				<tr>
					<td>行驶区域</td>
					<td><input id="xsqy2" name="xsqy" style="height:30px;" required="true"  ></td>
					<td>备注</td>
					<td><textarea rows="5" cols="25" name="bz"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><div  class="imgdiv"><p>驾驶人1</p><img id="img1" /></div></td>
					<td colspan="2"><div  class="imgdiv"><p>驾驶人2</p><img id="img2"/></div></td>

				</tr>
				<tr>
					<td>车身照片</td>
					<td colspan="3"><div  class="imgdiv"><img id="img"  /></div></td>
				</tr>
			</table>
				<input  type="hidden" name="vcEbikeImg"	>
				<input  type="hidden" name="vcUser1Img">
				<input type="hidden" name="vcUser2Img">
				<input type="hidden" name="dabh" >
				<input  type="hidden" name="ywlx">
				<input  type="hidden" name="ywyy">
				
				
		</form>
		<div>
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