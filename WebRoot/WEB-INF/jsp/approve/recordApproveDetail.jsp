<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>备案详情</title>
    
	<%@include file="../common/common.jsp"%>
		<style type="text/css">
	*{
		margin:0;
		padding:0;
		font-size:12px;
	}
	#main{
		border-collapse:collapse;
	}
	#main,#main tr,#main th,#main td{
		border:1px solid #C4E1FF;
	}
	#main tr{
		height:30px;
		background-color:#EEF2FB;
		line-height:30px;
	}
	#main th{
		text-align:right;
		font-weight:normal;
		width:10%;
	}
	#main td{
		width:15%;
		text-align:left;
		padding-left:5px;
	}
	.maindiv{
		background-color: #E4E4FB;
		vertical-align:middle;
	}
	.maindiv input{
		vertical-align:middle;
	}
	
	</style>
	<script type="text/javascript">
	
	$(document).ready(function(){
		if('${ddcHyxhSsdwclsb.vcShowEbikeImg}'==''){
			 $("#img_0").attr("src","<%=basePath%>static/images/iconfont-wu.png");
		}else{
			$("#img_0").attr("src",'${ddcHyxhSsdwclsb.vcShowEbikeImg}');
		}
		if('${ddcHyxhSsdwclsb.vcShowUser2Img}'==''){
			 $("#img2_2").attr("src","<%=basePath%>static/images/iconfont-wu.png");
		}else{
			$("#img2_2").attr("src",'${ddcHyxhSsdwclsb.vcShowUser2Img}');
		}
		if('${ddcHyxhSsdwclsb.vcShowUser1Img}'==''){
			 $("#img1_1").attr("src","<%=basePath%>static/images/iconfont-wu.png");
		}else{
			$("#img1_1").attr("src",'${ddcHyxhSsdwclsb.vcShowUser1Img}');
		} 
	})
	</script>
	
  </head>
  
  <body>
    <form action="">
    	<table id="main" class="table table-condensed"  border="1" cellpadding="0" cellspacing="0" width="98%">
    		<tr>
    			<th>流水号</th>
    			<td>${ddcHyxhSsdwclsb.lsh }</td>
    			<th>协会名称</th>
    			<td>${ddcHyxhSsdwclsb.hyxhzhName }</td>
    			<th>单位名称</th>
    			<td>${ddcHyxhSsdwclsb.ssdwName }</td>
    			<th>品牌型号</th>
    			<td>${ddcHyxhSsdwclsb.ppxh }</td>
    		</tr>
    		<tr>
    			<th>电机号</th>
    			<td>${ddcHyxhSsdwclsb.djh }</td>
    			<th>脚踏装置</th>
    			<c:if test="${ddcHyxhSsdwclsb.jtzz==0 }">
    			 <td>有</td>
    			</c:if>
    			<c:if test="${ddcHyxhSsdwclsb.jtzz==1 }">
    			 <td>无</td>
    			</c:if>
    			<th>行驶区域</th>
    			<td>${ddcHyxhSsdwclsb.xsqyName }</td>
    			<th>车身颜色</th>
    			<td>${ddcHyxhSsdwclsb.cysyName }</td>
    		</tr>
    		<tr>
    			<th>驾驶人1姓名</th>
    			<td>${ddcHyxhSsdwclsb.jsrxm1 }</td>
    			<th>身份证号码1</th>
    			<td>${ddcHyxhSsdwclsb.sfzmhm1 }</td>
    			<th>驾驶人1性别</th>
    			<c:if test="${ddcHyxhSsdwclsb.xb1==0 }">
    			 <td>男</td>
    			</c:if>
    			<c:if test="${ddcHyxhSsdwclsb.xb1==1 }">
    			 <td>女</td>
    			</c:if>
    			<th>联系电话1</th>
    			<td>${ddcHyxhSsdwclsb.lxdh1 }</td>
    			
    		</tr>
    		<tr>
    			<th>驾驶人姓名2</th>
    			<td>${ddcHyxhSsdwclsb.jsrxm2 }</td>
    			<th>身份证号码2</th>
    			<td>${ddcHyxhSsdwclsb.sfzmhm2 }</td>
    			<th>驾驶人2性别</th>
    			 <td>
    			 <c:if test="${ddcHyxhSsdwclsb.xb2==0 }">
    			 	男
    			 </c:if>
    			 <c:if test="${ddcHyxhSsdwclsb.xb2==1 }">
    			 	女
    			 </c:if>
    			 </td>
    			
    			<th>联系电话2</th>
    			<td>${ddcHyxhSsdwclsb.lxdh2 }</td>
    		</tr>
    		
    		<tr>
    			
    			<th>申请备注</th>
    			<td>${ddcHyxhSsdwclsb.bz }</td>
    			<th>申请人</th>
    			<td>${ddcHyxhSsdwclsb.sqr }</td>
    			<th>申请日期</th>
    			<td><fmt:formatDate value="${ddcHyxhSsdwclsb.sqrq }" pattern="yyyy-MM-dd"/></td>
    			<th></th>
    			<td></td>
    		</tr>
    		<tr>
    			<th>办理人</th>
    			<td>${ddcHyxhSsdwclsb.slr }</td>
    			<th>办理部门</th>
    			<td>${ddcHyxhSsdwclsb.slbm }</td>
    			<th>办理日期</th>
    			<td><fmt:formatDate value="${ddcHyxhSsdwclsb.slrq }" pattern="yyyy-MM-dd"/></td>
    			<th>办理结果</th>
    			<c:if test="${ddcHyxhSsdwclsb.slyj==0 }">
    			<td>已同意</td>
    			</c:if>
    			<c:if test="${ddcHyxhSsdwclsb.slyj==1 }">
    			<td>已拒绝</td>
    			</c:if>
    			<c:if test="${ddcHyxhSsdwclsb.slyj==null }">
    			<td>审批中</td>
    			</c:if>
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
    </form>
  </body>
</html>
