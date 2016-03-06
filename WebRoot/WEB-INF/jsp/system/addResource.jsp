<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'addResource.jsp' starting page</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript"
	src="<%=basePath%>static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/js/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/demo.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/color.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/css/ebike.css">
	
	<script type="text/javascript">
    $(function () {
        $('#dlg').dialog({
            title: '对话框',
            iconCls:"icon-edit",
            collapsible: true,
            minimizable: true,
            maximizable: true,
            resizable: true,
            width: 300,
            height: 200,
            modal: true,
            href: "Content.aspx",
            onClose: function () {
                alert("Close");
            },
            toolbar: [{
                text: 'Add',
                iconCls: 'icon-add',
                handler: function () {
                    alert('add');
                }
            }, '-', {
                text: 'Save',
                iconCls: 'icon-save',
                handler: function () {
                    alert('save');
                }
            }],
            buttons: [{
                text: 'Ok',
                iconCls: 'icon-ok',
                handler: function () {
                    alert('ok');
                }
            }, {
                text: 'Cancel',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#dlg').dialog('close');
                }
            }]
        });
    });
</script>
</head>

<body>
	<div id="dlg" class="easyui-dialog" style="width:300px; height:200px;" title="对话框" iconCls="icon-edit">
        Content
    </div>
</body>
</html>
