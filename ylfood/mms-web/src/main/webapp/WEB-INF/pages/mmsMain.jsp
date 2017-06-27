<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<% String path = application.getInitParameter("static-file"); %>
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>MMS-产品设计模块</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon" href="<%=path%>/img/pms/PMS_logo.ico" type="image/x-icon" />
<!-- 全局样式 -->
<link rel="stylesheet"
	href="<%=path%>/css/pms/pms.css">
<link href="<%=path%>/css/css.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/menu.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/head.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="mmsMainApp" ng-controller="mmsMainCtrl">
<input type="text" id="server" value="<%=path%>" style="display: none;"/>

	<jsp:include page="./common/head.jsp"></jsp:include>
		<div class="row">
		<jsp:include page="./common/menu.jsp"></jsp:include>
		<div class="sp-menu-right">

		</div>
	</div>
	
	<script type="text/javascript" src="<%=path%>/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/lib/angular-1.5.9.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/index.js"></script>
</body>
</html>