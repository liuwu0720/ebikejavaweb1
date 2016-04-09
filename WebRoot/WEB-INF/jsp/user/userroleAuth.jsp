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

<title>用户授权界面</title>

<%@include file="../common/common.jsp"%>

<style type="text/css">
#main2 {
	border-collapse: collapse;
}

#main2 tr {
	height: 30px;
	background-color: #EEF2FB;
	line-height: 30px;
}

#main2 th {
	text-align: right;
	font-weight: normal;
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
$(document).ready(function(){
	var jtMenusString ='${jtMenusString}';
	var jtMenusArray = jtMenusString.split(',');
	for(var i in jtMenusArray){
		if(jtMenusArray[i]!=""){
			var roleId = jtMenusArray[i];
			document.getElementById(roleId).checked=true;
		}
	}
	
});

function parentClick(par){
	
	var list = document.getElementsByName(par.value);
	if(par.checked){
   		for(var m=0;m<list.length;m++){
			list[m].checked=true;
		}
	}else{
		for(var m=0;m<list.length;m++){
			list[m].checked=false;
		}
	}
}

function middleClick(obj){
	
	document.getElementById(obj.name).checked=true;
	
	if(obj.checked){
		obj.checked = true;
	}else{
		obj.checked = false;
	}
}

function save(){
	
		$.messager.progress({
			text:"正在处理，请稍候..."
		});
		$('#dgform').form('submit', {
			url : "<%=basePath%>userRoleAction/saveUpdateUserAuth",
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
					history.go(-1);
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
	<div class="maindiv">
		<form  id="dgform" class="easyui-form" >
		<table id="main2" class="table">
			<tr>
				<th>角色名称</th>
				<td>${jtRole.roleName }<input type="hidden" name="roleId" value="${jtRole.id }"></td>
			</tr>
			<tr>
				<th>选择权限</th>
				<td>
					<table id="menu">
						<c:forEach items="${jtMenus }" var="pri">
							<c:if test="${pri.iParent == 0 }">
								<tr>
									<td>
										<p style="font-weight: bold;">
											<input  id="${pri.id }" type="checkbox" value="${pri.id }"   name="priBox" onclick="parentClick(this);">${pri.vcMenu }</p>
								<tr>
									<c:forEach items="${jtMenus }" var="sonpri">
										<c:if test="${pri.id == sonpri.iParent }">
											<td><input type="checkbox" value="${sonpri.id }" name="${pri.id }" id="${sonpri.id }"  onclick="middleClick(this);">${sonpri.vcMenu }&nbsp;&nbsp;&nbsp;
											</td>
										</c:if>
									</c:forEach>
								</tr>
								</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
		<div class="btndiv">
		<button type="button" onclick="save()" class="btn">保存</button>
		<button type="button" class="btn" onclick="history.back()">返回</button>
		</div>
		</form>
	</div>
</body>
</html>
