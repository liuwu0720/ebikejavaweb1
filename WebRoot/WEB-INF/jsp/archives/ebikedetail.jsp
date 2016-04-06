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
<script type="text/javascript">

$(document).ready(function(){
	if('${ddcHyxhSsdwclsb.vcShowEbikeImg}'==''){
		 $("#img_0").attr("src","<%=basePath%>static/images/iconfont-wu.png");
	}else{
		$("#img_0").attr("src",'${ddcDaxxb.vcShowEbikeImg}');
	}
	if('${ddcHyxhSsdwclsb.vcShowUser2Img}'==''){
		 $("#img2_2").attr("src","<%=basePath%>static/images/iconfont-wu.png");
	}else{
		$("#img2_2").attr("src",'${ddcDaxxb.vcShowUser2Img}');
	}
	if('${ddcHyxhSsdwclsb.vcShowUser1Img}'==''){
		 $("#img1_1").attr("src","<%=basePath%>static/images/iconfont-wu.png");
	}else{
		$("#img1_1").attr("src",'${ddcDaxxb.vcShowUser1Img}');
	} 
})

function exportPage() {
	$("#table1").css('width', '650px');
	var bdhtml=window.document.body.innerHTML;
	var startStr="<!--startprint-->";//设置打印开始区域 
	var endStr="<!--endprint-->";//设置打印结束区域 
	var printHtml=bdhtml.substring(bdhtml.indexOf(startStr)+startStr.length,bdhtml.indexOf(endStr));//从标记里获取需要打印的页面 
	window.document.body.innerHTML=printHtml;//需要打印的页面 
	window.print(); 
	window.document.body.innerHTML=bdhtml;//还原界面 
}
</script>

  </head>
  
  <body>
    <div  class="maindiv">
    <!--startprint-->
    	<table id="table1" class="table table-condensed"  border="1" cellpadding="0" cellspacing="0" width="98%">
				<tr>
					<th>申报单位</th>
					<td>${ddcDaxxb.zzjgdmzhName }</td>					
					<th>档案编号：</th>
					<td>${ddcDaxxb.dabh }</td>
					
				</tr>
				<tr>
					<th>业务类型</th>
					<td>${ddcDaxxb.ywlxName }</td>					
					<th>车辆状态</th>
					<td>${ddcDaxxb.ztName }</td>
				</tr>
				<tr>
					<th>品牌型号</th>
					<td>${ddcDaxxb.ppxh }</td>
					<th>车身颜色</th>
					<td>${ddcDaxxb.cysyName }</td>
					
				</tr>
				<tr>
					<th>电机号</th>
					<td>${ddcDaxxb.djh }</td>	
					<th>脚踏装置:</th>
					<c:if test="${ddcDaxxb.jtzz == 0 }">
					<td>有</td>
					</c:if>
					<c:if test="${ddcDaxxb.jtzz == 1 }">
					<td>无</td>
					</c:if>
				</tr>
				<tr>
					<th>驾驶人姓名1</th>
					<td>${ddcDaxxb.jsrxm1 }</td>	
					
					<th>驾驶人性别1</th>
					<c:if test="${ddcDaxxb.xb1 == 0 }">
					<td>男</td>
					</c:if>
					<c:if test="${ddcDaxxb.xb1 == 1 }">
					<td>女</td>
					</c:if>
					
				</tr>
				<tr>
					<th>身份证号码1</th>
					<td>${ddcDaxxb.sfzmhm1 }</td>
					<th>联系电话1</th>
					<td>${ddcDaxxb.lxdh1 }</td>
				</tr>
				<tr>
					<th>驾驶人姓名2</th>
					<td>${ddcDaxxb.jsrxm2 }</td>
					<th>驾驶人性别2</th>
					<td>
					<c:if test="${ddcDaxxb.xb2 == 0 }">男</c:if>
					<c:if test="${ddcDaxxb.xb2 == 1 }">女</c:if>
					</td>
					
				</tr>
				<tr>
				<th>身份证号码2</th>
					<td>${ddcDaxxb.sfzmhm2 }</td>
					<th>联系电话2</th>
					<td>${ddcDaxxb.lxdh2 }</td>
				</tr>
				<tr>
					<th>行驶区域</th>
					<td>${ddcDaxxb.xsqyName }</td>
					<th>申报备注</th>
					<td>${ddcDaxxb.bz }</td>
				</tr>
				<tr>
					<th>审检日期</th>
					<td><fmt:formatDate value="${ddcDaxxb.syrq }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
					<th>受理日期</th>
					<td><fmt:formatDate value="${ddcDaxxb.slrq }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
				
				</tr>
				<tr>
					<th>受理人</th>
					<td>${ddcDaxxb.slrName }</td>
					<th>受理部门</th>
					<td>${ddcDaxxb.slbmName }</td>
					
				</tr>
				<tr>
					<th>受理意见</th>
					<c:if test="${ddcDaxxb.slyj == 0 }">
					<td>同意</td>
					</c:if>
					<c:if test="${ddcDaxxb.slyj == 1 }">
					<td>不同意</td>
					</c:if>	
					<th>受理备注</th>
					<td>${ddcDaxxb.slbz }</td>
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
					
				</tr>
				<tr>
					<td colspan="4">
					<div  class="imgdiv">
					<p>车身照片</p>
					<img id="img_0"  src="<%=basePath%>static/images/iconfont-wu.png"/>
					</div><br /></td>
				</tr>
			</table>
		<!--endprint-->		
			<div class="btndiv">
		<button type="button" onclick="exportPage()" class="btn">打印</button>
		<button type="button" class="btn" onclick="history.back()">返回</button>
		</div>
    </div>
  </body>
</html>
