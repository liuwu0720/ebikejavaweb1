<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />

<title>特殊行业电动自行车管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=basePath %>static/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
			function reloadImage(url) {
				var img = document.getElementById("img");
				img.src = url + "?Code=" + Math.random();
			}
			document.onkeydown = function(e) {
				if (!e)
					e = window.event;
				if ((e.keyCode || e.which) == 13) {
					checklogin(); //转到验证的函数
				}
			}
			function resetlogin(){
				document.getElementById("login_form").reset();
			}
			function checklogin() {
				var cuser = $("#user").val();
				var cpassword = $("#password").val();
				var ccode = $("#code").val();
				var nullReg = /[^\f\n\r\t\v]/;
			
				if (!nullReg.test(cuser)) {
					alert("请输入单位帐号!");
					$("#user").focus();
					return false;
				} else if (!nullReg.test(cpassword)) {
					alert("请输入密码!");
					$("#password").focus();
					return false;
				} else if (!nullReg.test(ccode)) {
					alert("请输入验证码!");
					$("#code").focus();
					return false;
				}
				
				$.post("<%=basePath %>userAction/checkUser",{
					cuser : cuser,
					cpassword : cpassword,
					ccode : ccode
				},function(data, textStatus){
					 if(!data.isSuccess){
						 alert(data.message);
					 }else{
						 document.getElementById("login_form").submit();

					 }
				},"json")
		
	}
</script>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
}

html,body {
	overflow: hidden;
}
</style>
</head>
<body>

	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td bgcolor="#1075b1">&nbsp;</td>
		</tr>
		<tr>
			<td height="608" background="<%=basePath %>static/images/login_03.gif">
				<table width="847" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="318" valign="bottom"
							background="<%=basePath %>static/images/login_01.gif">&nbsp;</td>
					</tr>
					<tr>
						<td height="84">
							<form action="/user_login.action" method="post"
								id="login_form" style="margin:0;padding:0;">
								<table width="100%" height="84" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="381" background="<%=basePath %>static/images/login_04.gif">&nbsp;</td>
										<td width="162" background="<%=basePath %>static/images/login_05.gif">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0" style="font-size: 12px; color: #adc9d9;">
												<tr valign="bottom">
													<td width="50" height="24" align="right">用户名：</td>
													<td><input name="hyxhzh" type="text" id="user"
														style="width: 100px; height: 17px; border: solid 1px #153966; font-size: 12px; color: #283439; background-color: #87adbf;">
													</td>
												</tr>
												<tr valign="bottom">
													<td height="24" align="right">密&nbsp;码：</td>
													<td><input name="hyxhmm" type="password" id="password"
														style="width: 100px; height: 17px; border: solid 1px #153966; font-size: 12px; color: #283439; background-color: #87adbf;">
													</td>
												</tr>
												<tr valign="bottom">
													<td height="24" align="right">验证码：</td>
													<td><input name="code" type="text" id="code"
														style="display:block;float:left;width: 36px; height: 18px; border: solid 1px #153966; font-size: 12px; color: #283439; background-color: #87adbf;">
														<img id="img" onclick="javascript:reloadImage('makeCodePic.jsp');"
														title="点击刷新"
														style="cursor: pointer;display:block;float:left;width:60px;height:20px;margin:1px 2px auto 3px;"
														border="0" src="makeCodePic.jsp" /></td>
												</tr>
											</table>
										</td>
										<td width="26" background="<%=basePath %>static/images/login_06.gif">&nbsp;</td>
										<td width="67" background="<%=basePath %>static/images/login_07.gif">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td height="30" align="center"><img
														onclick="checklogin();" style="cursor: pointer;"
														src="<%=basePath %>static/images/submit.gif" width="57"
														height="20"></td>
												</tr>
												<tr>
													<td height="30" align="center"><img
														src="<%=basePath %>static/images/reset.gif"
														style="cursor: pointer;" onclick="resetlogin();"
														width="57" height="20"></td>
												</tr>
											</table>
										</td>
										<td width="211" background="<%=basePath %>static/images/login_08.gif">&nbsp;</td>
									</tr>
								</table>
							</form>
						</td>
					</tr>
					<tr>
						<td height="206" background="<%=basePath %>static/images/login_02.gif">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td bgcolor="#152753">&nbsp;</td>
		</tr>
	</table>
</body>
</html>
