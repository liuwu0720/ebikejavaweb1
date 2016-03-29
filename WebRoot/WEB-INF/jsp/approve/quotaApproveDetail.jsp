<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
<title>档案详情</title>

<%@include file="../common/common.jsp"%>
<style type="text/css">
#main {
	border-collapse: collapse;
}

#main tr {
	height: 30px;
	background-color: #EEF2FB;
	line-height: 30px;
}

#table1 th {
	text-align: left;
	font-weight: bold;
	width: 10%;
}

.maindiv {
	background-color: #E4E4FB;
	vertical-align: middle;
}

.maindiv input {
	vertical-align: middle;
}
.btn{
	width: 100px;
	height: 32px;
	background-color: #A9A9F7;
	text-align: center;
}
.btndiv{
text-align: center;
}
</style>
<script type="text/javascript">
function sureState(state){
	//$('#dgform').form('clear');
	 $('#dgformDiv').dialog('open').dialog('setTitle', '填写审批意见(可以为空)');
	 $('#dgform').form('load', {
		 state:state
	 });
}
//保存操作

function updateSaveData(){
	$.messager.progress();
	$('#dgform').form('submit', {
				url : "<%=basePath%>approvalAction/sureApprove",
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
						window.location.href="<%=basePath%>approvalAction/quotaApprove"
					}else{
						alert(data.message);
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			});
}
</script>

  </head>
  
  <body>
    <div  class="maindiv">
    
    	<table id="table1" class="table table-condensed">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id"></input>
					</td>
				</tr>
				<tr>
					<th>流水号：</th>
					<td>${ddcHyxhBasb.lsh }</td>					
					
				</tr>
				<tr>
					<th>协会名称：</th>
					<td>${ddcHyxhBasb.hyxhmc }</td>
				</tr>
				<tr>
					<th>申请配额：</th>
					<td>${ddcHyxhBasb.hyxhsqpe }</td>					
					
				</tr>
				<tr>
					<th>申请备注</th>
					<td colspan="3">
					${ddcHyxhBasb.bz }
					</td>	
					
				</tr>
				
				<tr>
					<th>审批状态</th>
					<td>
					<c:if test="${ddcHyxhBasb.bjjg == 0 }">
					<span>已同意</span>
					</c:if>
					<c:if test="${ddcHyxhBasb.bjjg == 1 }">
					<span>已拒绝</span>
					</c:if>
					<c:if test="${ddcHyxhBasb.bjjg == null }">
					<span>审批中</span>
					</c:if>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>办结日期</th>
					<td><fmt:formatDate value="${ddcHyxhBasb.bjrq }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					
				</tr>
				<tr>
					<th>办结人</th>
					<td>${ddcHyxhBasb.bzjr }  &nbsp; ${ddcHyxhBasb.bjbmName }</td>
				</tr>
				<tr>
					<th>办结意见</th>
					<td colspan="3">
					${ddcHyxhBasb.bjbz }
					
					</td>	
					
				</tr>
				
			</table>
			<table class="table table-condensed">
				<caption style="text-align: center">审批人及审批意见</caption>
				<tr>
					<td>审批人</td>
					<td>审批人角色</td>
					<td>审批部门</td>
					<td>审批日期</td>
					<td>审批意见</td>
					<td>审批备注</td>
				</tr>
				<c:forEach items="${approveUsers }" var="approve">
				<tr>
					<td>${approve.userName }</td>
					<td>${approve.userRoleName }</td>
					<td>${approve.userOrgname }</td>
					<td><fmt:formatDate value="${approve.approveTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<c:if test="${approve.approveState==0 }">
					<td>同意</td>
					</c:if>
					<c:if test="${approve.approveState==1 }">
					<td>拒绝</td>
					</c:if>
					<td>${approve.approveNote }</td>
				</tr>		
				</c:forEach>
			</table>
		<c:if test="${type == 2 }">
			<div class="btndiv">
			<button type="button" onclick="sureState(0)" class="btn">同意</button>
			<button type="button" onclick="sureState(1)" class="btn">拒绝</button>
			<button type="button" class="btn" onclick="history.back()">返回</button>
			</div>
		</c:if>	
		<c:if test="${type == 1 }">
			<div class="btndiv">
			<button type="button" class="btn" onclick="history.back()">返回</button>
			</div>
		</c:if>	
    </div>
    
    <!-- 点编辑时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:350px;height:250px;padding:10px 20px 20px 20px;"
		closed="true" buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form" method="post">
			<input type="hidden" name="id" value="${ddcHyxhBasb.id }">
			<input type="hidden" name="state">
			<textarea rows="10" cols="45" name="note"></textarea>
				
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
