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
	
	
	function sureState(state){
		//拒绝
		if(state == 1){
		 $('#dgformDiv').dialog('open').dialog('setTitle', '填写审批意见');	
		 $('#dgform').form('load', {
			 state:state
			 });
		}
		//同意
		if(state == 0){
			$('#dgformDiv2').dialog('open').dialog('setTitle', '填写审批意见');	
			 $('#dgform2').form('load', {
				 state:state
			});
		}
	
	}
	//保存操作

	function updateSaveData(){
		var flag = checkVarible();
		if(flag){
			$.messager.progress({
				text:"正在处理，请稍候..."
			});
		$('#dgform').form('submit', {
					url : "<%=basePath%>approvalAction/sureApproveRecord",
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
							$('#dgformDiv').dialog('close');
							window.location.href="<%=basePath%>approvalAction/recordApprove"
						}else{
							alert(data.message);
						}
						$.messager.progress('close'); // 如果提交成功则隐藏进度条

					}

				});
		}
	}
	
	function checkVarible(){
		if(!$("input[name='sltbyyzls']:checked").val()){
			alert("请勾选退办原因!")
			return false;
		}else{
			var vs="";
			$('[id=sltbyyzls]:checked').each(function(){
				vs += $(this).val()+',';
			});
			vs=vs.substr(0,vs.lastIndexOf(','));
			$("#tbyy").val(vs);
			return true;
		}
	}
	
	
	function updateSaveData2(){
		var flag = checkVarible2();
		if(flag){
			$.messager.progress({
				text:"正在处理，请稍候..."
			});
		$('#dgform2').form('submit', {
					url : "<%=basePath%>approvalAction/sureApproveRecord",
					onSubmit : function() {
						var isValid = $("#dgform2").form('enableValidation').form(
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
							$('#dgformDiv2').dialog('close');
							window.location.href="<%=basePath%>approvalAction/recordApprove"
						}else{
							alert(data.message);
						}
						$.messager.progress('close'); // 如果提交成功则隐藏进度条

					}

				});
		}
	
		
	}
	
	
	function checkVarible2(){
		if(!$("input[name='slzList']:checked").val()){
			alert("请勾选受理资料!")
			return false;
		}else{
			var vs="";
			$('[id=slzList]:checked').each(function(){
				vs += $(this).val()+',';
			});
			vs=vs.substr(0,vs.lastIndexOf(','));
			$("#slzl").val(vs);
			return true;
		}
	}
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
    		<c:if test="${ddcHyxhSsdwclsb.slyj==1 }">
    		<tr>
    			<th>退办原因</th>
    			<td colspan="7">
    				<c:forEach items="${selectlTbyy }" var="tb">
    					<p>${tb.dmms1 }</p>
    				</c:forEach>
    			</td>
    		</tr>
    		<tr>
    			<th>办结意见</th>
    			<td colspan="7">
    				${ddcHyxhSsdwclsb.slbz }
    			</td>
    		</tr>
			</c:if>
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
				<c:forEach items="${approveUsers }" var="approve">
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
		<c:if test="${type == 2 }">
			<div class="btndiv">
			<button type="button" onclick="sureState(0)" class="btn">同意</button>
			<button type="button" onclick="sureState(1)" class="btn">拒绝</button>
			<button type="button" class="btn" onclick="history.back()">返回</button>
			</div>
		</c:if>	
		<c:if test="${type == 1 }">
			<div class="btndiv">
			<button type="button" class="btn" onclick="history.back()">返回</button>
			</div>
		</c:if>	
    </form>
    
      <!-- 点退办时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:550px;padding:10px 20px 20px 20px;"
		closed="true" buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form" method="post">
			<div class="tbdiv">
			<input type="hidden" name="id" value="${ddcHyxhSsdwclsb.id }">
			<input type="hidden" name="state">
				<ul>
					<li><p>退办原因:</p><li>
					<li>
	   				<c:forEach items="${dbyyDdcSjzds }" var="bg">
	   				<input type="checkbox" id="sltbyyzls"  name="sltbyyzls" value="${bg.dmz}" />${bg.dmms1 }<br/>
	   				</c:forEach>
	   				</li>
	   				<li><p>备注:</p></li>
	   				<li>
	   				<textarea rows="10" cols="65" name="note"></textarea>
	   				</li>
	   			</ul>	
	   			<input type="hidden"  name="tbyy" id="tbyy">
			</div>
		</form>
		<div id="dlg-buttons2" style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBtn"
			iconCls="icon-ok" onclick="updateSaveData()" style="width:90px">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dgformDiv').dialog('close')"
			style="width:90px">取消</a>
		</div>
	</div>
	
	  <!-- 点同意时弹出的表单 -->
	<div id="dgformDiv2" class="easyui-dialog"
		style="width:550px;padding:10px 20px 20px 20px;"
		closed="true" buttons="#dlg-buttons">
		<form id="dgform2" class="easyui-form" method="post">
			<div class="tbdiv">
			<input type="hidden" name="id" value="${ddcHyxhSsdwclsb.id }">
			<input type="hidden" name="state">
				<ul>
					<li><p>受理资料:</p><li>
					<li>
	   				<c:forEach items="${slzList }" var="bg">
	   				<input type="checkbox" id="slzList"  name="slzList" checked="checked" value="${bg.dmz}" />${bg.dmms1 }<br/>
	   				</c:forEach>
	   				</li>
	   				<li><p>备注:</p></li>
	   				<li>
	   				<textarea rows="10" cols="65" name="note"></textarea>
	   				</li>
	   			</ul>	
	   			
	   			<input type="hidden"  name="slzl" id="slzl">
			</div>
		</form>
		<div id="dlg-buttons" style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBtn"
			iconCls="icon-ok" onclick="updateSaveData2()" style="width:90px">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dgformDiv2').dialog('close')"
			style="width:90px">取消</a>
		</div>
	</div>
  </body>
</html>