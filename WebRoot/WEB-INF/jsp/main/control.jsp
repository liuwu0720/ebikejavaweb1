<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=basePath %>static/js/jquery.min.js" language="javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
//iframe on-off
	var iframe_h  = $(document).height();	
	var $iframe_obj = $("#iframe_btn");
	$iframe_obj.height(iframe_h);
	$iframe_obj.click(function(){                   
		var yy = $(window.parent.document).find("#frameset").attr("cols");           
		if(yy== "0,10,*"){
			$(this).removeClass("iframe_on").addClass("iframe_off");
			$(window.parent.document).find("#frameset").attr("cols","200,10,*");
			$(window.parent.document).find("#sidebar").attr("scrolling","auto");
			
		}else{
			$(this).removeClass("iframe_off").addClass("iframe_on");
			$(window.parent.document).find("#frameset").attr("cols","0,10,*");
			$(window.parent.document).find("#sidebar").attr("scrolling","no");
		}
	});
});
</script>
</head>

<body class="control">
<center>
  <table height="100%" cellspacing="0" cellpadding="0" border="0">
    <tbody>
        <tr>
          <td valign="middle"><a href="javascript:void();" id="iframe_btn" class="iframe_off" title="隐藏 | 显示"></a></td>
        </tr>
 	 </tbody>
  </table>
</center>
</body>
</html>