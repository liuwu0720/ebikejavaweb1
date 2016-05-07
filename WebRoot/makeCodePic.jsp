<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page contentType="image/jpeg" %>
<jsp:useBean id="image" scope="page" class="com.kirisun.blackradio.util.VerificationCode" />
<%
String str=image.getCertPic(0,0,response.getOutputStream());
session.setAttribute("certCode", str); 

out.clear();
 out = pageContext.pushBody();
%>
