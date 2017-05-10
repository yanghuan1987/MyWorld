<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String path = application.getInitParameter("static-file"); %>
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>  
<!--header-->
 <div class="row sp-head">
   <div class="sp-head-logo" style="font-size: 18px;color: #99c731;">
     <span>
     	<a href="${pageContext.request.contextPath}/pms/login/main">
     		<img src="<%=path%>/img/pms/PMS_logo.png"/>
     	</a>
    </span>
     <span style="padding-left:15px;">-</span>
     <span style="padding-left:10px;">产品设计</span>
   </div>
   <div class="sp-head-right">
    <span class="sp-head-user"><img src="<%=path%>/img/user.png"><shiro:principal property="account"/></span>
	    <span class="sp-head-exit" >
	    	<a href="<%=basePath%>/uias-web/login/loginOut" class="sp-head-logout"><img src="<%=path%>/img/exit.png">安全退出</a>
	    </span>
    </div>
  </div>
 <!--/header-->
