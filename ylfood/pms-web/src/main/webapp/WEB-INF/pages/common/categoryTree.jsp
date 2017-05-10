<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% String path = application.getInitParameter("static-file"); %>
<!-- navbar -->
<div class="navbar navbar-inverse" style="background-color: #4B4B4B;">
	<div class="navbar-inner pmx-top">
		<a class="brand" href="#"><img src="<%=path%>/img/pms/PMS_logo.png" style="margin-top: 35px;margin-left: 11%;"/></a><span class="headtitle">-产品设计模块</span>
		<div class="nav pull-right pms-top-zt">${sessionScope.userName}，欢迎进入PMS系统，<a href="#" style="color: white;font-size: 12px;" ng-click="loginOut()">退出</a></div>
	</div>
</div>
<!-- navbar end -->