<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'businessDetailInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="../common/common.jsp"%>
<script type="text/javascript">
	
	$(document).ready(function(){
		if('${ddcFlow.vcShowEbikeImg}'==''){
			 $("#img_0").attr("src","<%=basePath%>static/images/iconfont-wu.png");
		}else{
			$("#img_0").attr("src",'${ddcFlow.vcShowEbikeImg}');
		}
		if('${ddcFlow.vcShowUser2Img}'==''){
			 $("#img2_2").attr("src","<%=basePath%>static/images/iconfont-wu.png");
		}else{
			$("#img2_2").attr("src",'${ddcFlow.vcShowUser2Img}');
		}
		if('${ddcFlow.vcShowUser1Img}'==''){
			 $("#img1_1").attr("src","<%=basePath%>static/images/iconfont-wu.png");
		}else{
			$("#img1_1").attr("src",'${ddcFlow.vcShowUser1Img}');
		} 
	})
	
	</script>
  </head>
  
  <body>
     <form action="">
    	<table id="main" class="table table-condensed"  border="1" cellpadding="0" cellspacing="0" width="98%">
    		<tr>
    			<th>流水号</th>
    			<td>${ddcFlow.lsh }</td>
    			<th>协会名称</th>
    			<td>${ddcFlow.hyxhName }</td>
    			<th>单位名称</th>
    			<td>${ddcFlow.zzjgdmzhName }</td>
    			<th>品牌型号</th>
    			<td>${ddcFlow.ppxh }</td>
    		</tr>
    		<tr>
    			<th>电机号</th>
    			<td>${ddcFlow.djh }</td>
    			<th>脚踏装置</th>
    			<c:if test="${ddcFlow.jtzz==0 }">
    			 <td>有</td>
    			</c:if>
    			<c:if test="${ddcFlow.jtzz==1 }">
    			 <td>无</td>
    			</c:if>
    			<th>行驶区域</th>
    			<td>${ddcFlow.xsqyName }</td>
    			<th>车身颜色</th>
    			<td>${ddcFlow.cysyName }</td>
    		</tr>
    		<tr>
    			<th>驾驶人1姓名</th>
    			<td>${ddcFlow.jsrxm1 }</td>
    			<th>身份证号码1</th>
    			<td>${ddcFlow.sfzmhm1 }</td>
    			<th>驾驶人1性别</th>
    			<c:if test="${ddcFlow.xb1==0 }">
    			 <td>男</td>
    			</c:if>
    			<c:if test="${ddcFlow.xb1==1 }">
    			 <td>女</td>
    			</c:if>
    			<th>联系电话1</th>
    			<td>${ddcFlow.lxdh1 }</td>
    			
    		</tr>
    		<tr>
    			<th>驾驶人姓名2</th>
    			<td>${ddcFlow.jsrxm2 }</td>
    			<th>身份证号码2</th>
    			<td>${ddcFlow.sfzmhm2 }</td>
    			<th>驾驶人2性别</th>
    			 <td>
    			 <c:if test="${ddcFlow.xb2==0 }">
    			 	男
    			 </c:if>
    			 <c:if test="${ddcFlow.xb2==1 }">
    			 	女
    			 </c:if>
    			 </td>
    			
    			<th>联系电话2</th>
    			<td>${ddcFlow.lxdh2 }</td>
    		</tr>
    		
    	
    		<tr>
    			<th>办理人</th>
    			<td>${ddcFlow.slr }</td>
    			<th>办理部门</th>
    			<td>${ddcFlow.slbm }</td>
    			<th>办理日期</th>
    			<td><fmt:formatDate value="${ddcFlow.slrq }" pattern="yyyy-MM-dd"/></td>
    			<th>办理结果</th>
    			<c:if test="${ddcFlow.slyj==0 }">
    			<td>已同意</td>
    			</c:if>
    			<c:if test="${ddcFlow.slyj==1 }">
    			<td>已拒绝</td>
    			</c:if>
    			<c:if test="${ddcFlow.slyj==null }">
    			<td>审批中</td>
    			</c:if>
    		</tr>
    		<c:if test="${ddcFlow.slyj==1 }">
    		<tr>
    			<th>退办原因</th>
    			<td colspan="7">
    				<c:forEach items="${selectTbyySjzds }" var="tb">
    					<p>${tb.dmms1 }</p>
    				</c:forEach>
    			</td>
    		</tr>
    		</c:if>
    	
    		<tr>
    			<th>${ddcFlow.ywlxName }受理资料</th>
    			<td colspan="7">
    				<c:forEach items="${selectSlzls }" var="tb">
    					<p>${tb.dmms1 }</p>
    				</c:forEach>
    			</td>
    		</tr>
    		
    		<tr>
    			<th>办结意见</th>
    			<td colspan="7">
    				${ddcFlow.slbz }
    			</td>
    		</tr>
			
				<tr>
					<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人1照片</p>
					<img id="img1_1"  src="<%=basePath%>static/images/iconfont-wu.png"/></div>
					</td>
					<td colspan="2">
					<div  class="imgdiv">
					<p>驾驶人2照片</p>
					<img id="img2_2"  src="<%=basePath%>static/images/iconfont-wu.png"/>
					</div><br /></td>
					<td colspan="4">
					<div  class="imgdiv">
					<p>车身照片</p>
					<img id="img_0"  src="<%=basePath%>static/images/iconfont-wu.png"/>
					</div><br /></td>
				</tr>
    	</table>
    	<c:if test="${ddcApproveUsers !=null }">
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
				<c:forEach items="${ddcApproveUsers }" var="approve">
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
		</c:if>
		<div class="btndiv">
			<button type="button" class="btn" onclick="history.back()">返回</button>
		</div>
    </form>
  </body>
</html>
