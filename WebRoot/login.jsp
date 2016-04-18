<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/login.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/iconfont.css">
  </head>
  <script type="text/javascript" src="<%=basePath %>static/js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript">
  function reloadImage(url) {
		var img = document.getElementById("codeImage");
		img.src = url + "?Code=" + Math.random();
	}
	document.onkeydown = function(e) {
		if (!e)
			e = window.event;
		if ((e.keyCode || e.which) == 13) {
			checklogin(); //转到验证的函数
		}
	}
  
  function _validateForm() {
	    var username = $.trim($("#username").val());
	    var password = $.trim($("#password").val());
	    var code = $.trim($("#code").val());
	    if (!username) {
	        $(".form-error").css("visibility", "visible").find("span").html("填写用户名");
	        return false;
	    }
	    if (!password) {
	        $(".form-error").css("visibility", "visible").find("span").html("填写密码");
	        return false;
	    }
	    if (!code) {
	        $(".form-error").css("visibility", "visible").find("span").html("填写验证码");
	        return false;
	    }
	    
	    return {username: username, password: password,code:code};
	}

  
  function checklogin() {
	  var data = _validateForm();
      if (!data) return;
      $.post("<%=basePath %>userAction/checkUser",{
			cuser : data.username,
			cpassword : data.password,
			ccode : data.code
		},function(data, textStatus){
			 if(!data.isSuccess){
				 return $(".form-error").css("visibility", "visible").find("span").html(data.message);
			 }else{
				 document.getElementById("login_form").submit();

			 }
		},"json")
  }
		
		
		
  </script>
  <body>
   <div class="login-panel">
    <div class="login-title">
        深圳特殊行业电动自行车管理系统
    </div>
    <div class="login-box">
        <div class="login-form">
            <form action="<%=basePath %>userAction/loginToMain" method="post"
								id="login_form">
                <div class="form-error">
                    <i class="iconfont">&#xe60d;</i><span>用户名或密码错误</span>
                </div>
                <div class="form-item">
                    <label>用户名</label>
                    <div class="form-input">
                        <i class="iconfont">&#xe602;</i>
                        <input type="text" id="username" placeholder="填写用户名" maxlength="20">
                    </div>
                </div>
                <div class="form-item">
                    <label>密　码</label>
                    <div class="form-input">
                        <i class="iconfont">&#xe603;</i>
                        <input type="password" id="password" placeholder="填写用户密码" maxlength="20">
                    </div>
                </div>
                <div class="form-item">
                    <label>验证码</label>
                    <div class="form-inline">
                        <input type="text" id="code" maxlength="4" placeholder="">
                        <img  onclick="javascript:reloadImage('makeCodePic.jsp');" src="makeCodePic.jsp" id="codeImage">
                    </div>
                </div>
                <div class="form-item">
                    <a class="form-btn btn-login" onclick="checklogin();">登&nbsp;录&nbsp;</a>
                </div>
            </form>
        </div>
    </div>
</div>
  </body>
</html>
