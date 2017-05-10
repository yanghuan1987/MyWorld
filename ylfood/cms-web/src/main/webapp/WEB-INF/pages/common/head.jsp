<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<% String path = application.getInitParameter ("static-file"); %>
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>  
<div class="row sp-head">
     <div class="sp-head-logo">
	   <img src="<%=path%>/img/cms/CMS-logo.png"/>
	 </div>
     <div class="sp-head-right">
	   <span class="sp-head-user"><img src="<%=path%>/img/user.png"><shiro:principal property="account"/></span>
	   <span class="sp-head-exit"><img src="<%=path%>/img/exit.png"><a href='<%=basePath%>/uias-web/login/loginOut'>安全退出</a></span>
	 </div>
  </div>