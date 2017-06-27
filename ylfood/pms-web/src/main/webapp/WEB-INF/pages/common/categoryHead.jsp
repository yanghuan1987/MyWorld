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
<div class="sp-head">
	<div class="sp-head-logo">
		<span> 
			<a href="${pageContext.request.contextPath}/pms/login/main"> 
			<img src="<%=path%>/img/pms/PMS_logo.png" />
			</a>
		</span> 
	</div>
   <div style="float: left;font-size: 30px;color: #99c731;padding-left: 8%;">PMS品类设计</div>
	<div class="sp-head-right">
		<span class="sp-head-user"><img src="<%=path%>/img/user.png">
		<shiro:principal property="account" /></span> <span class="sp-head-exit">
			<a href="<%=basePath%>/uias-web/login/loginOut" class="sp-head-logout">
				<img src="<%=path%>/img/exit.png">安全退出
			</a>
		</span>
	</div>
</div>
<!--/header-->
