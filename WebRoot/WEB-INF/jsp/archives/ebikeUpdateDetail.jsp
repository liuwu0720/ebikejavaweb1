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
    <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<%@include file="../common/common.jsp"%>

	<script type="text/javascript">
	
 	$(document).ready(function(){
 		  
			$('#cysy').combobox({
				 url:'<%=basePath%>ebikeAction/getAllColorsAjax',    
				    valueField:'dmz',    
				    textField:'dmms1',
				    value:'${ddcDaxxb.cysy}'   
			});
			$('#xb1').combobox({
				value:'${ddcDaxxb.xb1 }'
			});
			$('#xb2').combobox({
				value:'${ddcDaxxb.xb2 }'
			});
			
			
			$('#xsqy').combobox({
				 url:'<%=basePath%>ebikeAction/getArea',    
				    valueField:'dmz',    
				    textField:'dmms1',
				    value:'${ddcDaxxb.xsqy }'
			})
	}) 
	
	

function updateSaveData(){
	
	$('#dgform').form('submit', {
				url : "<%=basePath%>ebikeAction/saveOrUpdate",
				onSubmit : function() {
					
					var isValid = $("#dgform").form('enableValidation').form('validate');

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
						history.go(-1);
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
  <div  class="maindiv">
 
    	<h2>更改车辆备案信息</h2>
    	<form  id="dgform"  class="easyui-form" enctype="multipart/form-data"
			method="post" >
    	<table id="main" class="table table-condensed"  border="1" cellpadding="0" cellspacing="0" width="98%">
    			<tr>
					<th>品牌型号</th>
					<td><input class="easyui-validatebox" type="text" value="${ddcDaxxb.ppxh }"
						data-options="required:true" name="ppxh"></input></td>
					<th>电机号：</th>
					<td><input  class="easyui-validatebox" type="text" value="${ddcDaxxb.djh }"
						 name="djh"></input>
					</td>	
					<th>车身颜色</th>
					<td><input id="cysy" style="height: 32px;" name="cysy">	</td>
					<th>脚踏装置:</th>
					<td>
						<select id="jtzz" class="easyui-combobox" name="jtzz"   value="${ddcDaxxb.jtzz }"
							style="height:32px;width: 50px;">
							<option value="0">有</option>
							<option value="1">无</option>
						</select>
					</td>
				</tr>
    		<tr>
    			<th>档案编号</th>
    			<td><input class="easyui-validatebox" type="text" value="${ddcDaxxb.dabh }"
						data-options="required:true" name="dabh"></td>
    			
    			<th>所属单位</th>
    			<td>${ddcDaxxb.ssdwName }</td>
				<th>车牌号码</th>
					<td>
					<input class="easyui-validatebox" type="text" value="${ddcDaxxb.cphm }"
						data-options="required:true" name="cphm"></input>
				</td>
				<th>行驶区域</th>
    			<td><input id="xsqy" name="xsqy" style="height:30px;"  ></td>
			</tr>
			<tr>
    			<th>驾驶人姓名1</th>
    			<td>
    			<input class="easyui-validatebox" type="text" value="${ddcDaxxb.jsrxm1 }"
						data-options="required:true" name="jsrxm1"></input>	
    			</td>
    			<th>驾驶人性别1</th>
    			<td>
					<select id="xb1" class="easyui-combobox" name="xb1"   value="${ddcDaxxb.xb1 }"
							style="height:32px;width: 80px;">
							<option value="-1">请选择</option>
							<option value="0">男</option>
							<option value="1">女</option>
					</select>
				</td>
    			<th>身份证号码1</th>
    			<td>
					<input class="easyui-validatebox" type="text" value="${ddcDaxxb.sfzmhm1 }"
						data-options="required:true,validType:'idcard'" name="sfzmhm1"></input>	
				</td>
				<th>联系电话1</th>
				<td><input class="easyui-validatebox" type="text" value="${ddcDaxxb.lxdh1 }"
						data-options="required:true,validType:'phoneNum'" name="lxdh1" style="height: 32px">
				</td>
			</tr>
				<tr>
    			<th>驾驶人姓名2</th>
    			<td>
    			<input class="easyui-validatebox" type="text" value="${ddcDaxxb.jsrxm2 }" name="jsrxm2"></input>	
    			</td>
    			<th>驾驶人性别2</th>
    			<td>
					<select  id="xb2" class="easyui-combobox" name="xb2"   value="${ddcDaxxb.xb2 }"
							style="height:32px;width: 80px;">
							<option value="-1">请选择</option>
							<option value="0">男</option>
							<option value="1">女</option>
					</select>
				</td>
    			<th>身份证号码2</th>
    			<td>
					<input class="easyui-validatebox" type="text" value="${ddcDaxxb.sfzmhm2 }"
						data-options="validType:'idcard'" name="sfzmhm2"></input>	
				</td>
				<th>联系电话2</th>
				<td><input class="easyui-validatebox" type="text" value="${ddcDaxxb.lxdh2 }"
						data-options="validType:'phoneNum'" name="lxdh2" style="height: 32px">
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
    	<input type="hidden" value="${ddcDaxxb.id }" name="id">
    </form>	

			<div class="btndiv">
			<input type="button" class="btn" onclick="updateSaveData();" value="保存">
			<input type="button" class="btn" onclick="history.back();" value="返回">
			</div>
		
  </div>  
    
  </body>
</html>
