<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = application.getInitParameter("static-file"); %>
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="Generator" content="Isidore Han®">
<meta name="Author" content="Isidore Han">
<meta name="Keywords" content="">
<meta name="Description" content="">
<link rel="stylesheet" href="../css/css.css">
<title>银犁食品互联网平台</title>
</head>
<body ng-app="loginApp" ng-controller="loginCtrl">
  <div class='spip-head'>
     <div class='head-logo'>
	  <img src='../img/icon.png'/>
	 </div>
	 <div class='head-login-infor'>
	  <img src='../img/user.png'/>
	  <span><shiro:principal property="account"/></span>
		 <a href='Javascript:void (0)'  ng-click="loginOut()">退出</a>
	 </div>
   </div>   
   <div class='spip-content'>
    <div class='spip-main'>
	    <div class='spip-layout'>
		   <div class='sp-sys-title'>
		   <span>前台系统</span>
		  </div>
		  <div class='sp-sys-lg'>
		   <a href='<%=basePath%>/oms-web/' target="_blank">
		   		<div>OMS</div>
		   		<div class="sp-sys-name">订单管理</div>
		   </a>
			
		  </div>
		  <div class='sp-sys-lg'>
		  <a href='#' target="_blank">
			  <div>TSS</div>
			  <div class="sp-sys-name">终端</div>
		  </a>
		  </div>
		  <div class='sp-sys-normal'>
		  <a href='<%=basePath%>/cms-web/cms/login/main' target="_blank">
		  	<div>CMS</div>
		  	<div class="sp-sys-name">内容管理</div>
		  	</a>
		  </div>
		  <!-- 
		  <div class='sp-sys-normal'>
		  <a href='#' target="_blank">P&amp;C</a>
		  </div>
		   -->
		  <div class='sp-sys-normal'>
		  <a href='<%=basePath%>/crm-web/main' target="_blank">
		  	<div>CRM</div>
		  	<div class="sp-sys-name">客户管理</div>
		  </a>
		  </div>
		  <div class='sp-sys-normal'>
		  <a href='<%=basePath%>/pms-web/pms/login/main' target="_blank">
			  <div>PMS</div>
			  <div class="sp-sys-name">产品管理</div>
		  </a>
		  </div>
		  <div class='sp-sys-normal'>
		  <a href='<%=basePath%>/mms-web/mms/login/main' target="_blank">
			  <div>MMS</div>
			  <div class="sp-sys-name">原料管理</div>
		  </a>
		  </div>
		  <!-- SPCMS 目前只有后台没有页面  页面由TS完成  -->
		  <div class='sp-normal-last'>
		  <a href='<%=basePath%>/spcms-web/card/home' target="_blank"><div>SPCMS</div><div class="sp-sys-name">银犁卡管理</div></a>
		  </div>
		</div>
		<div class='spip-layout'>
			<div class='sp-sys-title'>
				<span>中台系统</span>
			</div>
			<div class='sp-sys-lg'>
				<a href='<%=basePath%>/scs-web/scs/common/main' target="_blank"><div>SCS</div><div class="sp-sys-name">分拣控制</div></a>
			</div>
			<div class='sp-sys-lg'>
				<a href='<%=basePath%>/wms-web/wms/common/main' target="_blank"><div>WMS</div><div class="sp-sys-name">仓库管理</div></a>
			</div>
			<div class='sp-sys-lg'>
				<a href='<%=basePath%>/tms-web/' target="_blank"><div>TMS</div><div class="sp-sys-name">运输管理</div></a>
			</div>
			<div class='sp-sys-normal'>
				<a href='<%=basePath%>/pws-web/' target="_blank"><div>PWS</div><div class="sp-sys-name">加工工单</div></a>
			</div>
			<div class='sp-sys-normal'>
				<a href='#' target="_blank"><div>ITS</div><div class="sp-sys-name">检验检测</div></a>
			</div>
			<div class='sp-sys-normal'>
				<a href='#' target="_blank"><div>SPM</div><div class="sp-sys-name">生产管理</div></a>
			</div>
			<div class='sp-sys-normal'>
				<a href='<%=basePath%>/wos-web/' target="_blank"><div>WOS</div><div class="sp-sys-name">工单系统</div></a>
			</div>
			<div class='sp-normal-last'>
				<a href='<%=basePath%>/scm-web/scm/home/showHome' target="_blank"><div>SCM</div><div class="sp-sys-name">供应链管理</div></a>
			</div>
		</div>
		<div class='spip-layout spip-layout-narrow'>
			<div class='sp-sys-title'>
				<span>后台系统</span>
			</div>
			<div class='sp-sys-normal'>
				<a href='#' target="_blank"><div>FMS</div><div class="sp-sys-name">财务管理</div></a>
			</div>
			<div class='sp-sys-normal'>
				<a href='#' target="_blank"><div>ADS</div><div class="sp-sys-name">分析决策</div></a>
			</div>
			<div class='sp-sys-normal'>
				<a href='<%=basePath%>/pcs-web/' target="_blank"><div>PCS</div><div class="sp-sys-name">价格采集</div></a>
			</div>
			<div class='sp-normal-last'>
				<a href='<%=basePath%>/ocs-web/' target="_blank"><div>OCS</div><div class="sp-sys-name">运营控制</div></a>
			</div>
		</div>
	</div>
   </div>
</body>
<script src="<%=path%>/js/lib/jquery.min.js"></script>
<script src="<%=path%>/js/lib/angular-1.5.9.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
</html>