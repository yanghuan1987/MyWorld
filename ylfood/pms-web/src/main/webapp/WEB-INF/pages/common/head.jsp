<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>  
<% String path = application.getInitParameter("static-file"); %>
<!-- navbar -->
<div class="navbar navbar-inverse" style="background-color: #4B4B4B;">
	<div class="navbar-inner pmx-top">
		<a class="brand" href="#"><img src="<%=path%>/img/pms/PMS_logo.png" style="margin-top: 35px;margin-left: 11%;"/></a><span class="headtitle">-产品设计模块</span>
		<div class="nav pull-right pms-top-zt"><shiro:principal property="account"/>，欢迎进入PMS系统，<a href="<%=basePath%>/uias-web/login/loginOut" style="color: white;font-size: 12px;">退出</a></div>
	</div>
</div>
<!-- navbar end -->