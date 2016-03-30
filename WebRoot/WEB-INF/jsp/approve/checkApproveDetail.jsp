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
		font-weight:bold;
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
	.btn{
	width: 100px;
	height: 32px;
	background-color: #A9A9F7;
	text-align: center;
	}
	.btndiv{
		text-align: center;
	}
	.tbdiv{
		margin: 0 40px;
	}
	.tbdiv p{
		font-weight: bolder;
	}
	</style>
	<script type="text/javascript">
	
	$(document).ready(function(){
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
	})
	
	
	
	//保存操作

	function checkSure(obj){
		
		$.messager.progress({
				text:"正在处理，请稍候..."
			});
		$('#dgform').form('submit', {
					url : "<%=basePath%>approvalAction/sureCheckApprove?id="+obj,
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
						
							window.location.href="<%=basePath%>approvalAction/checkApprove"
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
    <form id="dgform" class="easyui-form" method="post">
    	<table id="main" class="table table-condensed"  border="1" cellpadding="0" cellspacing="0" width="98%">
    		<tr>
    			<th>档案编号</th>
    			<td>${ddcDaxxb.dabh }</td>
    			<th>协会名称</th>
    			<td>${ddcDaxxb.hyxhzhmc }</td>
    			<th>单位名称</th>
    			<td>${ddcDaxxb.zzjgdmzhName }</td>
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
    			<td>${ddcDaxxb.xsqyName }</td>
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
		
			<div class="btndiv">
				
			<button type="button" class="btn" onclick="checkSure(${ddcDaxxb.id})">检验合格</button>
			<button type="button" class="btn" onclick="history.back()">返回</button>
			</div>
		
    </form>
    
    
  </body>
</html>
