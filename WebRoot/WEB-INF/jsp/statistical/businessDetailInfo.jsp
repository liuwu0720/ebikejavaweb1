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
	var flag =true;
	if(flag){
		$.messager.progress({
			text:"正在处理，请稍候..."
		});
	$('#dgform').form('submit', {
				url : "<%=basePath%>approvalAction/sureApproveCancel",
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
						window.location.href="<%=basePath%>approvalAction/cancelApproval"
					}else{
						alert(data.message);
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			});
	}
}

function updateSaveData2(){
	var flag =true;
	if(flag){
		$.messager.progress({
			text:"正在处理，请稍候..."
		});
	$('#dgform2').form('submit', {
				url : "<%=basePath%>approvalAction/sureApproveCancel",
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
						window.location.href="<%=basePath%>approvalAction/cancelApproval"
					}else{
						alert(data.message);
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			});
	}

	
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
	<c:if test="${ddcApproveUsers!=null }">
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
		<!--endprint-->		
			<div class="btndiv">			
			<button type="button" onclick="exportPage()" class="btn">打印</button>
			<button type="button" class="btn" onclick="history.back()">返回</button>
			<c:if test="${type==1 }">
			<button type="button" onclick="sureState(0)" class="btn">同意</button>
			<button type="button" onclick="sureState(1)" class="btn">拒绝</button>
			</c:if>
		</div>
    </div>		
        <!-- 点退办时弹出的表单 -->
	<div id="dgformDiv" class="easyui-dialog"
		style="width:550px;padding:10px 20px 20px 20px;"
		closed="true" buttons="#dlg-buttons2">
		<form id="dgform" class="easyui-form" method="post">
			<div class="tbdiv">
			<input type="hidden" name="id" value="${ddcFlow.id }">
			<input type="hidden" name="state">
				<ul>
				
	   				<li><p>备注:</p></li>
	   				<li>
	   				<textarea rows="10" cols="65" name="note"></textarea>
	   				</li>
	   			</ul>	
	   			
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
			<input type="hidden" name="id" value="${ddcFlow.id }">
			<input type="hidden" name="state">
				<ul>
					
	   				</li>
	   				<li><p>备注:</p></li>
	   				<li>
	   				<textarea rows="10" cols="65" name="note"></textarea>
	   				</li>
	   			</ul>	
	   			
	   			
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
