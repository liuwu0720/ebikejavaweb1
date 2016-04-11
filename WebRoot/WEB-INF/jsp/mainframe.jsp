<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>LayOut</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/demo.css">
<script type="text/javascript"	src="<%=basePath%>static/js/jquery.min.js"></script>
<script type="text/javascript"	src="<%=basePath%>static/js/jquery.easyui.min.js"></script>
</head>
<script language="JavaScript">

$(document).ready(function () {
    $('.easyui-accordion li a').click(function () {
        var tabTitle = $(this).text();
        var url = $(this).attr("href");
        addTab(tabTitle, url);
        $('.easyui-accordion li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });

    function addTab(subtitle, url) {
        if (!$('#tabs').tabs('exists', subtitle)) {
            $('#tabs').tabs('add', {
                title: subtitle,
                content: createFrame(url),
                closable: true,
                width: $('#mainPanle').width() - 10,
                height: $('#mainPanle').height() - 26
            });
        } else {
            $('#tabs').tabs('select', subtitle);
       }
        tabClose();
    }


    function createFrame(url) {
        var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
        return s;
    }


    function tabClose() {
        /*双击关闭TAB选项卡*/
        $(".tabs-inner").dblclick(function () {
            var subtitle = $(this).children("span").text();
            $('#tabs').tabs('close', subtitle);
        })

        $(".tabs-inner").bind('contextmenu', function (e) {
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY,
            });
            var subtitle = $(this).children("span").text();
            $('#mm').data("currtab", subtitle);
            return false;
        });
    }

    //绑定右键菜单事件
function tabCloseEven() {
        //关闭当前
 $('#mm-tabclose').click(function () {
            var currtab_title = $('#mm').data("currtab");
            $('#tabs').tabs('close', currtab_title);
        })
        //全部关闭
 $('#mm-tabcloseall').click(function () {
            $('.tabs-inner span').each(function (i, n) {
                var t = $(n).text();
               $('#tabs').tabs('close', t);
            });
        });

        //关闭除当前之外的TAB
        $('#mm-tabcloseother').click(function () {
            var currtab_title = $('#mm').data("currtab");
            $('.tabs-inner span').each(function (i, n) {
                var t = $(n).text();
                if (t != currtab_title)
                    $('#tabs').tabs('close', t);
            });
        });
        //关闭当前右侧的TAB
        $('#mm-tabcloseright').click(function () {
            var nextall = $('.tabs-selected').nextAll();
            if (nextall.length == 0) {
               //msgShow('系统提示','后边没有啦~~','error');
                alert('后边没有啦~~');
                return false;
            }
            nextall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                $('#tabs').tabs('close', t);
            });
            return false;
       });
        //关闭当前左侧的TAB
        $('#mm-tabcloseleft').click(function () {
            var prevall = $('.tabs-selected').prevAll();
            if (prevall.length == 0) {
                alert('到头了，前边没有啦~~');
                return false;
            }
            prevall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                $('#tabs').tabs('close', t);
            });
            return false;
        });

        //退出
        $("#mm-exit").click(function () {
            $('#mm').menu('hide');

        })
    }
});
</script>
<style>
.footer {
	width: 100%;
	text-align: center;
	line-height: 35px;
}

.top-bg {
	background-color: #698cba;
	height: 80px;
}
</style>
</head>

<body class="easyui-layout">
	<div region="north" border="true" split="true"
		style="overflow: hidden; height: 80px;">
		<div class="top-bg"></div>
	</div>
	<div region="south" border="true" split="true"
		style="overflow: hidden; height: 40px;">
		
	</div>
    <div region="west" class="west" title="menu">
		<ul id="tree"></ul>
	</div>
	<div id="mainPanle" region="center" style="overflow: hidden;"></div>
</body>
</html>
