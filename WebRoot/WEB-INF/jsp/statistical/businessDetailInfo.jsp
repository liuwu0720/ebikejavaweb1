<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>流水详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="../common/common.jsp"%>
<script type="text/javascript">



function exportPage() {
	$("#main").css('width', '650px');
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
    	<h2>业务流水详情</h2>
    	<table id="main" class="table table-condensed"  border="1" cellpadding="0" cellspacing="0" width="98%">
    		
				<tr>
					<th>申报单位</th>
					<td>${ddcFlow.ssdwName }</td>
					<th>所属协会</th>
					<td>${ddcFlow.hyxhzhName }</td>					
					<th>档案编号：</th>
					<td>${ddcFlow.dabh }</td>
					<th>流水号</th>
					<td>${ddcFlow.lsh }</td>
				</tr>
				<tr>
					<th>业务类型</th>
					<td>${ddcFlow.ywlxName }</td>					
					<th>车牌号码</th>
					<td>${ddcFlow.cphm }</td>
					<th>品牌型号</th>
					<td>${ddcFlow.ppxh }</td>
					<th>所属单位</th>
					<td>${ddcFlow.ssdwName }</td>
				</tr>
				<tr>
					<th>电机号</th>
					<td>${ddcFlow.djh }</td>	
					<th>脚踏装置:</th>
					<c:if test="${ddcFlow.jtzz == 0 }">
					<td>有</td>
					</c:if>
					<c:if test="${ddcFlow.jtzz == 1 }">
					<td>无</td>
					</c:if>
					<th>行驶区域</th>
					<td>${ddcFlow.xsqyName }</td>
					<th>车身颜色</th>
    				<td>${ddcFlow.cysyName }</td>
				</tr>
				<tr>
					<th>驾驶人姓名1</th>
					<td>${ddcFlow.jsrxm1 }</td>	
					<th>驾驶人性别1</th>
					<c:if test="${ddcFlow.xb1 == 0 }">
					<td>男</td>
					</c:if>
					<c:if test="${ddcFlow.xb1 == 1 }">
					<td>女</td>
					</c:if>
					<th>身份证号码1</th>
					<td>${ddcFlow.sfzmhm1 }</td>
					<th>联系电话1</th>
					<td>${ddcFlow.lxdh1 }</td>
				</tr>
				<tr>
					<th>驾驶人姓名2</th>
					<td>${ddcFlow.jsrxm2 }</td>
					<th>驾驶人性别2</th>
					<td>
					<c:if test="${ddcFlow.xb2 == 0 }">男</c:if>
					<c:if test="${ddcFlow.xb2 == 1 }">女</c:if>
					</td>
					<th>身份证号码2</th>
					<td>${ddcFlow.sfzmhm2 }</td>
					<th>联系电话2</th>
					<td>${ddcFlow.lxdh2 }</td>
				</tr>
				<tr>
					
					<th>申请日期</th>
					<td><fmt:formatDate value="${ddcFlow.slrq }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
    				<th>办结日期</th>
					<td><fmt:formatDate value="${ddcFlow.gdrq }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
					<th>办结意见</th>
					<td>
						<c:if test="${ddcFlow.gdyj==null }">
							审批中
						</c:if>
						<c:if test="${ddcFlow.gdyj==0 }">
							办结
						</c:if>
						<c:if test="${ddcFlow.gdyj==1 }">
							退办
						</c:if>
					</td>
				</tr>
				<tr>
    				<th>受理资料</th>
    				<td colspan="7">
    				<c:forEach items="${slzlDdcSjzds }" var="sl">
    					<p>${sl.dmms1 }</p>
    				</c:forEach>
    				</td>
    			</tr>
    			<tr>
    				<th>申请备注</th>
					<td colspan="7">${ddcFlow.bz }</td>
					
    			</tr>
    			<tr>
    				<th>归档备注</th>
					<td  colspan="7">${ddcFlow.gdbz }</td
    			</tr>
    			<c:if test="${tbyyDdcSjzds!=null }">
    			<tr>
    				<th>退办资料</th>
    				<td colspan="7">
    				<c:forEach items="${tbyyDdcSjzds }" var="tb">
    					<p>${tb.dmms1 }</p>
    				</c:forEach>
    				</td>
    			</tr>
    			</c:if>
				<tr>
					<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人1照片</p>
					<a href="${ddcFlow.vcShowUser1Img }" target="_blank">
					<img src="${ddcFlow.vcShowUser1Img }"/>
					</a></div>
					</td>
					<td colspan="2">
					<div  class="imgdiv">
					<p>驾驶人2照片</p>
					<a href="${ddcFlow.vcShowUser2Img }" target="_blank">
					<img src="${ddcFlow.vcShowUser2Img }"/>
					</a>
					</div</td>
					<td colspan="2">
					<a href="${ddcFlow.vcShowEbikeImg }" target="_blank">
					<div  class="imgdiv">
					<p>车身照片</p>
					<img   src="${ddcFlow.vcShowEbikeImg }"/>
					</div></a></td>
					<td colspan="2">
					<a href="${ddcFlow.vcEbikeInvoiceImgShow }" target="_blank">
					<div  class="imgdiv">
					<p>购车发票</p>
					<img   src="${ddcFlow.vcEbikeInvoiceImgShow }"/>
					</div></a></td>
				</tr>
			<tr>
				<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人1身份证正面</p>
					<a href="${ddcFlow.vcUser1CardImg1Show }" target="_blank">
					<img   src="${ddcFlow.vcUser1CardImg1Show }"/>
					</a></div>
				</td>
				<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人1身份证反面</p>
					<a href="${ddcFlow.vcUser1CardImg2Show }" target="_blank">
					<img  src="${ddcFlow.vcUser1CardImg2Show }"/>
					</a></div>
				</td>
				<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人2身份证正面</p>
					<a href="${ddcFlow.vcUser2CardImg1Show }" target="_blank">
					<img   src="${ddcFlow.vcUser2CardImg1Show }"/>
					</a></div>
				</td>
				<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人2身份证反面</p>
					<a href="${ddcFlow.vcUser2CardImg2Show }" target="_blank">
					<img  src="${ddcFlow.vcUser2CardImg2Show }"/>
					</a></div>
				</td>
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
