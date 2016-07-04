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

	<script type="text/javascript">
	
<%-- 	$(document).ready(function(){
		if('${ddcDaxxb.vcShowEbikeImg}'==''){
			 $("#img_0").attr("src","<%=basePath%>static/images/iconfont-wu.png");
		}else{
			$("#img_0").attr("src",'${ddcDaxxb.vcShowEbikeImg}');
		}
		if('${ddcDaxxb.vcShowUser2Img}'==''){
			 $("#img2_2").attr("src","<%=basePath%>static/images/iconfont-wu.png");
		}else{
			$("#img2_2").attr("src",'${ddcDaxxb.vcShowUser2Img}');
		}
		if('${ddcDaxxb.vcShowUser1Img}'==''){
			 $("#img1_1").attr("src","<%=basePath%>static/images/iconfont-wu.png");
		}else{
			$("#img1_1").attr("src",'${ddcDaxxb.vcShowUser1Img}');
		} 
	}) --%>
	
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
    	<h2>车辆备案详情</h2>
    	<form action="" id="dgform">
    	<table id="main" class="table table-condensed"  border="1" cellpadding="0" cellspacing="0" width="98%">
    		<tr>
    			<th>档案编号</th>
    			<td>${ddcDaxxb.dabh }</td>
    			<th>车牌号码</th>
    			<td>${ddcDaxxb.cphm }</td>
    			<th>单位名称</th>
    			<td>${ddcDaxxb.ssdwName }(所属协会:${ddcDaxxb.hyxhzhName })</td>
    			<th>品牌型号</th>
    			<td>${ddcDaxxb.ppxh }</td>
    		</tr>
    		<tr>
    			<th>电机号</th>
    			<td>${ddcDaxxb.djh }</td>
    			<th>脚踏装置</th>
    			<c:if test="${ddcDaxxb.jtzz==0 }">
    			 <td>有</td>
    			</c:if>
    			<c:if test="${ddcDaxxb.jtzz==1 }">
    			 <td>无</td>
    			</c:if>
    			<th>行驶区域</th>
    			<td>全市范围(${ddcDaxxb.xsqyName }交警大队备案 )</td>
    			<th>车身颜色</th>
    			<td>${ddcDaxxb.cysyName }</td>
    		</tr>
    		<tr>
    			<th>驾驶人1姓名</th>
    			<td>${ddcDaxxb.jsrxm1 }</td>
    			<th>身份证号码1</th>
    			<td>${ddcDaxxb.sfzmhm1 }</td>
    			<th>驾驶人1性别</th>
    			<c:if test="${ddcDaxxb.xb1==0 }">
    			 <td>男</td>
    			</c:if>
    			<c:if test="${ddcDaxxb.xb1==1 }">
    			 <td>女</td>
    			</c:if>
    			<th>联系电话1</th>
    			<td>${ddcDaxxb.lxdh1 }</td>
    			
    		</tr>
    		<tr>
    			<th>驾驶人姓名2</th>
    			<td>${ddcDaxxb.jsrxm2 }</td>
    			<th>身份证号码2</th>
    			<td>${ddcDaxxb.sfzmhm2 }</td>
    			<th>驾驶人2性别</th>
    			 <td>
    			 <c:if test="${ddcDaxxb.xb2==0 }">
    			 	男
    			 </c:if>
    			 <c:if test="${ddcDaxxb.xb2==1 }">
    			 	女
    			 </c:if>
    			 </td>
    			
    			<th>联系电话2</th>
    			<td>${ddcDaxxb.lxdh2 }</td>
    		</tr>
    		<tr>
    			<th>办理人</th>
    			<td>${ddcDaxxb.slrName }</td>
    			<th>办理部门</th>
    			<td>${ddcDaxxb.slbmName }</td>
    			<th>办理日期</th>
    			<td><fmt:formatDate value="${ddcDaxxb.slrq }" pattern="yyyy-MM-dd"/></td>
    			<th>审验日期</th>
    			<td><fmt:formatDate value="${ddcDaxxb.syrq }" pattern="yyyy-MM-dd"/></td>
    		</tr>
    		
    		<tr>
    			<th>受理资料</th>
    			<td colspan="7">
    				<c:forEach items="${slzls }" var="tb">
    					<p>${tb.dmms1 }</p>
    				</c:forEach>
    			</td>
    		</tr>
    		
				<tr>
    			<td colspan="2">
					<div  class="imgdiv">
					<p>车身照片</p>
					<img   src="${ddcDaxxb.vcShowEbikeImg }"/>
					</div></td>
					<td colspan="2">
					<div  class="imgdiv">
					<p>购车发票</p>
					<img  src="${ddcDaxxb.vcEbikeInvoiceImgShow }"/>
					</div></td>
    		   <td colspan="2">
					<div  class="imgdiv">
					<p>车辆合格证照片</p>
					<img   src="${ddcDaxxb.vcQualifiedImgShow }"/>
					</div></td>
					<td colspan="2">
					<div  class="imgdiv">
					<p>投保凭证</p>
					<img  src="${ddcDaxxb.vcEbikeInsuranceImgShow }"/>
					</div></td>
    		</tr>
			
				<tr>
					<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人1照片</p>
					<img  src="${ddcDaxxb.vcShowUser1Img }"/></div>
					</td>
					<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人1身份证正面</p>
					<a href="${ddcDaxxb.vcUser1CardImg1Show }" target="_blank">
					<img   src="${ddcDaxxb.vcUser1CardImg1Show }"/>
					</a></div>
				</td>
				<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人1身份证反面</p>
					<a href="${ddcDaxxb.vcUser1CardImg2Show }" target="_blank">
					<img  src="${ddcDaxxb.vcUser1CardImg2Show }"/>
					</a></div>
				</td>
				<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人1在职证明或居住证</p>
					<a href="${ddcDaxxb.vcUser1WorkImgShow }" target="_blank">
					<img  src="${ddcDaxxb.vcUser1WorkImgShow }"/>
					</a></div>
				</td>	
					
				</tr>
				
				<tr>
						<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人2照片</p>
					<img  src="${ddcDaxxb.vcShowUser2Img }"/></div>
					</td>
					<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人2身份证正面</p>
					<a href="${ddcDaxxb.vcUser2CardImg1Show }" target="_blank">
					<img   src="${ddcDaxxb.vcUser2CardImg1Show }"/>
					</a></div>
				</td>
				<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人2身份证反面</p>
					<a href="${ddcDaxxb.vcUser2CardImg2Show }" target="_blank">
					<img  src="${ddcDaxxb.vcUser2CardImg2Show }"/>
					</a></div>
				</td>
				<td colspan="2">
					<div class="imgdiv"> 
					<p>驾驶人2在职证明或居住证</p>
					<a href="${ddcDaxxb.vcUser2WorkImgShow }" target="_blank">
					<img  src="${ddcDaxxb.vcUser2WorkImgShow }"/>
					</a></div>
				</td>
			</tr>	
    	</table>
    	<input type="hidden" name="id" value="${ddcDaxxb.id }">
    </form>	
	<!--endprint-->	
			<div class="btndiv">
			<button type="button" class="btn" onclick="exportPage()">打印</button>	
			<button type="button" class="btn" onclick="history.back()">返回</button>
			</div>
		
  </div>  
    
  </body>
</html>
