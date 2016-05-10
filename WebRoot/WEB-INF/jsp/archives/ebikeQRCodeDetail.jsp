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

<script type="text/javascript" src="<%=basePath%>static/js/qrcode.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	/*生成二维码*/
	var qrcode = new QRCode(document.getElementById("qrcode"), {
        width : 150,//设置宽高
        height : 150
    });
    qrcode.makeCode(${ddcDaxxb.dabh});
    
    if('${ddcDaxxb.jsrxm2}'){
	    var qrcode2 = new QRCode(document.getElementById("qrcode2"), {
	        width : 150,//设置宽高
	        height : 150
	    });
	    
	    qrcode2.makeCode(${ddcDaxxb.dabh});
    }
    
  
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
    	<!--startprint-->
    	
  <div id="ebikeQRCode">		
  <div class="content"  >
	  <div class="contentLeft" style="">
		    <div  class="photo">
		    <img id="img1_1"  src="<%=basePath%>static/images/iconfont-wu.png"/>
		    </div>
		    <div  class="qrcode" >
		      <div id="qrcode">
		      </div>
		      <span class="warm">二维码仅供公安扫描有效</span>
		    </div>
	   </div>
	   <div class="contentRight">
		    <div style="display:inline-block;width:330px;height:400px;">
		   <ul>
		        <li><label>人员姓名&nbsp;:&nbsp;</label> ${ddcDaxxb.jsrxm1 }</li>
		        <li><label>联系电话&nbsp;:&nbsp;</label>${ddcDaxxb.lxdh2 }</li>
		        <li><label>性别&nbsp;:&nbsp;</label>	<c:if test="${ddcDaxxb.xb1 == 0 }">男</c:if>
					<c:if test="${ddcDaxxb.xb1 == 1 }">女</c:if></li>
		    	<li><label>申报单位&nbsp;:&nbsp;</label> ${ddcDaxxb.sswdName }</li>
		    	<li><label>品牌型号&nbsp;:&nbsp;</label>${ddcDaxxb.ppxh }</li>
		    	<li><label>电机号&nbsp;:&nbsp;</label>${ddcDaxxb.djh }</li>
		    	<li><label>车身颜色&nbsp;:&nbsp;</label> ${ddcDaxxb.cysyName }</li>
		    	<li><label>行驶区域&nbsp;:&nbsp;</label> ${ddcDaxxb.xsqyName }</li>
		    	<li><label>车辆状态&nbsp;:&nbsp;</label>${ddcDaxxb.ztName }</li>
		    	<li><label>业务类型&nbsp;:&nbsp;</label>${ddcDaxxb.ywlxName }</li>   
		    	<li><label>档案编号&nbsp;:&nbsp;</label>${ddcDaxxb.dabh}</li>
		    	<li><label>受理人&nbsp;:&nbsp;</label>${ddcDaxxb.slrName }</li>
		    </ul>	 
		     </div>
	   </div>   
   </div>
   
 
   	<c:if test="${null != ddcDaxxb.jsrxm2}">
   	<div class="content">
	<div class="contentLeft">
	<div  class="photo">
	<img id="img1_1"  src="<%=basePath%>static/images/iconfont-wu.png"/>
	</div>
	<div  class="qrcode">
	<div id="qrcode2">
	</div>
	<span class="warm" style="text-align:center">二维码仅供公安扫描有效</span>
	</div>
	</div>
	<div class="contentRight">
	<div style="display:inline-block;width:330px;">
	<ul>
	<li><label>人员姓名&nbsp;:&nbsp;</label> ${ddcDaxxb.jsrxm2 }</li>
	<li><label>联系电话&nbsp;:&nbsp;</label>${ddcDaxxb.lxdh2 }</li>
	 <li><label>性别&nbsp;:&nbsp;</label>	<c:if test="${ddcDaxxb.xb2 == 0 }">男</c:if>
					<c:if test="${ddcDaxxb.xb2 == 1 }">女</c:if></li>
	<li><label>申报单位&nbsp;:&nbsp;</label> ${ddcDaxxb.ssdwName }</li>
	<li><label>品牌型号&nbsp;:&nbsp;</label>${ddcDaxxb.ppxh }</li>
	<li><label>电机号&nbsp;:&nbsp;</label>${ddcDaxxb.djh }</li>
	<li><label>车身颜色&nbsp;:&nbsp;</label> ${ddcDaxxb.cysyName }</li>
	<li><label>行驶区域&nbsp;:&nbsp;</label> ${ddcDaxxb.xsqyName }</li>
	<li><label>车辆状态&nbsp;:&nbsp;</label>${ddcDaxxb.ztName }</li>
	<li><label>业务类型&nbsp;:&nbsp;</label>${ddcDaxxb.ywlxName }</li>
	<li><label>档案编号&nbsp;:&nbsp;</label>${ddcDaxxb.dabh}</li>
	<li><label>受理人&nbsp;:&nbsp;</label>${ddcDaxxb.slrName }</li>
	</ul>
	</div>
	</div>
	</div>
	</c:if>
	<!--endprint-->	
</div>  
	   <div class="btndiv_print">
				<button type="button" onclick="exportPage()" class="btn_primary">打印</button>
				<button type="button" class="btn_default" onclick="history.back()">返回</button>
	   </div>
  </body>
</html>
