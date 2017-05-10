<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<% String path = application.getInitParameter("static-file"); %>
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>PMS-产品设计模块</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon" href="<%=path%>/img/pms/PMS_logo.ico" type="image/x-icon" />
<!-- 全局样式 -->
<link rel="stylesheet"
	href="<%=path%>/css/pms/pms.css">
</head>
<body ng-app="pmsMainApp" ng-controller="pmsMainCtrl" class="menuBody">
<input type="text" id="server" value="<%=path%>" style="display: none;"/>

   <div style="text-align: right;padding-right: 15px;">
    <span class="" style="color: #99c731;"><shiro:principal property="account"/>,欢迎进入PMS系统</span>
	    <span style="padding-left:10px;">
	    	<a href="<%=basePath%>/uias-web/login/loginOut" class="" style="color: white;">退出</a>
	    </span>
    </div>
	<div style="margin-top: 15%;">
		<span style="margin-left: 44%;"><shiro:principal property="account"/></span>
				<div class="sp-div-intervel" style="margin-left: 18%;">
					<div class="sp-pruduct-design" style="float: left;">
						<span> <a
							href="${pageContext.request.contextPath}/pms/product/showMain">产品设计</a>
						</span>
					</div>
					<div class="sp-pruduct-design">
						<span> <a
							href="${pageContext.request.contextPath}/pms/commodity/showMain">商品设计</a>
						</span>
					</div>
				</div>
				<div class="" style="margin-left: 27%;float: left;">
					<span><img
						src="<%=path%>/img/pms/dec.png"
						 /></span>
				</div>
					<div class="sp-text-left">
						<span class="sp-category-span" ><a
							href="${pageContext.request.contextPath}/pms/category/showMain">品类管理</a></span>
					</div>
					<div class="" style="margin-left: 63%;float: left;margin-top: -6%;">
						<span><img
							src="<%=path%>/img/pws/Menu.png"
							 /></span>
					</div>
					<div class="sp-pruduct-design" style="margin-top: -3%;margin-right: 15%;">
							<span> <a style="font-size: 36px;"
								href="${pageContext.request.contextPath}/pms/comment/showMain">评价管理</a>
							</span>
					</div>
	</div>
	<script type="text/javascript" src="<%=path%>/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/lib/angular-1.5.9.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/index.js"></script>
</body>
</html>