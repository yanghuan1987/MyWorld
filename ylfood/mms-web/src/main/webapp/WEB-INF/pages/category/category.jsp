<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>MMS-品类设计模块</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon"
	href="<%=path%>/img/pms/PMS_logo.ico"
	type="image/x-icon" />
<link rel="stylesheet" href="<%=path%>/css/pms/pms.css">
<link href="<%=path%>/css/css.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/popup.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/menu.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/head.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/pagination.css" rel="stylesheet" ype="text/css">
</head>
<body ng-app="categoryApp" ng-controller="categoryCtrl" ng-cloak style="overflow: hidden;" class="ng-cloak">
<input type="text" id="server" value="<%=path%>" style="display: none;"/>
	<jsp:include page="../common/categoryHead.jsp"></jsp:include>

	<!-- main container -->
	<div class="sp-div-none container-fluid">
		<div class="row">
		<jsp:include page="/WEB-INF/pages/common/menu.jsp"></jsp:include>
			<!-- sidebar -->
			<jsp:include page="../common/categoryMenu.jsp"></jsp:include>
			
			<div class="sp-menu-right" style="width: 80%;">
				<!-- 一、二级品类页面 -->
				<jsp:include page="categoryInfoOneTwoLevel.jsp"></jsp:include>
				<!-- 三级品类编辑 页面-->
				<jsp:include page="categoryInfoThreeLevel.jsp"></jsp:include>
			</div>
		</div>
	</div>
	</div>
	<!-- end main container -->
	<!-- （Modal） -->
	
	 <div id='popDialogue'class='sp-popup sp-popup-md'>
    <div class='sp-pop-header'>
    <div id="pop-logo" class="sp-pop-logo"><span>提示信息</span></div>
	<div class="sp-pop-exit"><a href="javascript:exitPop('popDialogue');"><img src='<%=path%>/img/cross.png'></a></div>
	</div>
	<div class='sp-pop-content'>
       <div class="sp-pop-title" style="color: red;">
          		{{errorMessage}}
       </div>
       <div class="sp-pop-btn">
           <input type="button" class="sp-btn sp-btn-gray-md " onclick="exitPop('popDialogue');" value="关闭">
       </div>
	</div>
  </div>
</body>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/category/category.js"></script>
</html>