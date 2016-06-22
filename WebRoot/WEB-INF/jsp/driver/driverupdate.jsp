<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'driverupdate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="../common/common.jsp"%>
	<script type="text/javascript">
	
	$(document).ready(function(){
		$('#xb').combobox({
			value:'${ddcDriver.xb }'
		});
	});
	
	
	function sureState(state){
		//拒绝
		if(state == 1){
		 $('#dgformDiv').dialog('open').dialog('setTitle', '填写审批意见');	
		 $('#dgform').form('load', {
			 state:state
			 });
		}
		//同意
		if(state == 0){
			 $('#dgform').form('load', {
				 state:state
			});
			 updateSaveData();
		}
	
	}
	//保存操作

	function updateSaveData(){
		var flag = true;
		if(flag){
			$.messager.progress({
				text:"正在处理，请稍候..."
			});
		$('#dgform').form('submit', {
					url : "<%=basePath%>driverAction/sureApproveRecord",
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
							window.history.back();
						}else{
							alert(data.message);
						}
						$.messager.progress('close'); // 如果提交成功则隐藏进度条

					}

				});
		}
	}
	
	</script>
  </head>
  
  <body>
   <div class="maindiv">
   		<form  enctype="multipart/form-data"
			method="post">
			<h2>司机档案详情</h2>
			<table id="main" class="table table-condensed"  border="1" cellpadding="0" cellspacing="0" width="98%">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id" value="${ddcDriver.id }"></input>
					</td>
				</tr>
				<tr>
				<th>驾驶人姓名</th>
					<td><input class="easyui-validatebox" type="text"
						value="${ddcDriver.jsrxm }" data-options="required:true"
						name="jsrxm"></input></td>
				<th>驾驶人性别</th>
					<td><select id="xb" class="easyui-combobox" name="xb"
						value="${ddcDriver.xb }" style="height:32px;width: 80px;">
							<option value="-1">请选择</option>
							<option value="0">男</option>
							<option value="1">女</option>
					</select></td>
				</tr>
				 
				 <tr>
				 	<th>身份证号码</th>
				 	<td><input class="easyui-validatebox" type="text"
						value="${ddcDriver.sfzhm }"
						data-options="required:true,validType:'idcard'" name="sfzhm"></input>
					</td>
					<th>联系电话</th>
					<td><input class="easyui-validatebox" type="text"
						value="${ddcDriver.lxdh }"
						data-options="required:true,validType:'phoneNum'" name="lxdh"
						style="height: 32px"></td>
			 </tr>
			<tr>
				<td colspan="2">
					<div class="imgdiv">
						<p>驾驶人头像照片</p>
						<a href="${ddcDriver.vcShowUserImg }" target="_blank">
							<img src="${ddcDriver.vcShowUserImg }" />
						</a>
					</div>
				</td>
				<td colspan="2">
					<div class="imgdiv">
						<p>驾驶人居住证或在职证明</p>
						<a href="${ddcDriver.vcUserWorkImgShow }" target="_blank">
							<img src="${ddcDriver.vcUserWorkImgShow }" />
						</a>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<div class="imgdiv">
						<p>驾驶人身份证正面</p>
						<a href="${ddcDriver.vcUserCardImg1Show }" target="_blank">
							<img src="${ddcDriver.vcUserCardImg1Show }" />
						</a>
					</div>
				</td>
				<td colspan="2">
					<div class="imgdiv">
						<p>驾驶人身份证反面</p>
						<a href="${ddcDriver.vcUserCardImg2Show }" target="_blank">
							<img src="${ddcDriver.vcUserCardImg2Show }" />
						</a>
					</div>
				</td>
			</tr>
				 <input  type="hidden" name="xjFlag" value="${ddcDriver.xjFlag }">
				  <input  type="hidden" name="xjMsg" value="${ddcDriver.xjMsg }">
				  <input  type="hidden" name="xjRq" value="${ddcDriver.xjRq }">
				  <input  type="hidden" name="userCode" value="${ddcDriver.userCode }">
				   <input  type="hidden" name="userPassword" value="${ddcDriver.userPassword }">
				   <input  type="hidden" name="illeagalTimes" value="${ddcDriver.illeagalTimes }">
				    <input  type="hidden" name="vcUserImg" value="${ddcDriver.vcUserImg }">
				     <input  type="hidden" name="vcUserWorkImg" value="${ddcDriver.vcUserWorkImg }">
				      <input  type="hidden" name="vcUserCardImg1" value="${ddcDriver.vcUserCardImg1 }">
				       <input  type="hidden" name="vcUserCardImg2" value="${ddcDriver.vcUserCardImg2 }">
				       <input  type="hidden" name="userNote" value="${ddcDriver.userNote }">
				        
				   
			</table>
				<div class="btndiv">			
			<button type="button" onclick="sureState(0)" class="btn">同意</button>
			<button type="button" onclick="sureState(1)" class="btn">拒绝</button>
		</div>
		</form>
		</div>
		
		
		      <!-- 点退办时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:550px;padding:10px 20px 20px 20px;"
		closed="true" buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form" method="post">
			<div class="tbdiv">
			<input type="hidden" name="id" value="${ddcDriver.id }">
			<input type="hidden" name="state">
				<ul>
				
	   				<li><p>备注:</p></li>
	   				<li>
	   				<textarea rows="10" cols="65" name="note"></textarea>
	   				</li>
	   			</ul>	
	   			
			</div>
		</form>
		<div id="dlg-buttons2" style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBtn"
			iconCls="icon-ok" onclick="updateSaveData()" style="width:90px">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dgformDiv').dialog('close')"
			style="width:90px">取消</a>
		</div>
	</div>
	
	  <!-- 点同意时弹出的表单 -->
	<div id="dgformDiv2" class="easyui-dialog"
		style="width:550px;padding:10px 20px 20px 20px;"
		closed="true" buttons="#dlg-buttons">
		<form id="dgform2" class="easyui-form" method="post">
			<div class="tbdiv">
			<input type="hidden" name="id" value="${ddcDriver.id }">
			<input type="hidden" name="state">
				<ul>
	   				</li>
	   				<li><p>备注:</p></li>
	   				<li>
	   				<textarea rows="10" cols="65" name="note"></textarea>
	   				</li>
	   			</ul>	
			</div>
		</form>
		<div id="dlg-buttons" style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBtn"
			iconCls="icon-ok" onclick="updateSaveData2()" style="width:90px">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dgformDiv2').dialog('close')"
			style="width:90px">取消</a>
		</div>
	</div>
  </body>
</html>
