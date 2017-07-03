<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = application.getInitParameter("static-file");
%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
%>
<!--header-->
<div style="width: 100%;height: 50px;">
   <div style="float: left;font-size: 30px;color: #99c731;padding-left: 8%;"></div>
	<div class="sp-head-right" style="margin-top: 10px;">
		<span style="height: 50px;vertical-align: middle;padding-right: 20px;">
		<shiro:principal property="account" /></span> <span class="sp-head-exit">
			<a href="<%=basePath%>/uias-web/login/loginOut" class="sp-head-logout">
				<img src="<%=path%>/img/exit.png">安全退出
			</a>
		</span>
	</div>
</div>
<!--/header-->
