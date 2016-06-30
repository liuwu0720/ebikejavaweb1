<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>司机档案</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="../common/common.jsp"%>
  </head>
  
  <body>
    <div class="maindiv">
		<h2>司机档案详情</h2>
		<!--startprint-->
		<table id="main" class="table table-condensed" border="1"
			cellpadding="0" cellspacing="0" width="98%">

			<tr>
				<th>所属协会</th>
				<td>${hyxhName }</td>
				<th>单位名称</th>
				<td>${ssdwName }</td>
				
			</tr>
			<tr>
				<th>姓名</th>
				<td>${ddcDriver.jsrxm }</td>
				<th>性别</th>
				<c:if test="${ddcDriver.xb==0 }">
					<td>男</td>
				</c:if>
				<c:if test="${ddcDriver.xb==1 }">
					<td>女</td>
				</c:if>
				<c:if test="${ddcDriver.xb==null }">
					<td>未选择</td>
				</c:if>
			</tr>
			<tr>
				<th>身份证号码</th>
				<td>${ddcDriver.sfzhm }</td>
				<th>联系电话</th>
				<td>${ddcDriver.lxdh }</td>
			</tr>
			<tr>
				<th>认证状态</th>
				<td>
				<c:if test="${ddcDriver.userStatus==0 }">
					未认证
				  </c:if>
				<c:if test="${ddcDriver.userStatus==1 }">
					已实名认证
				</c:if>	
				<c:if test="${ddcDriver.userStatus==2 }">
					已星级认证
				</c:if>	
				</td>
				<th>星级认证日期</th>
				<td><fmt:formatDate value="${ddcDriver.xjRq }"
						pattern="yyyy-MM-dd" /></td>
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
		</table>
		<div class="btndiv">
		<button type="button" class="btn" onclick="history.back()">返回</button>
		</div>
	</div>
</body>
</html>
