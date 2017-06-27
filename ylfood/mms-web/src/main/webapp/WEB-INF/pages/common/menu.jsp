<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<% String path = application.getInitParameter("static-file"); %>
<div class="sp-menu-left" style="width: 7%;">
	<div class="sp-menu" style="height: 523px;">
	  <ul class="sp-ul-menu">
	    	<li onclick="loadSecMenu('${pageContext.request.contextPath}/mms/category/showMain')" style="background-color:{{categoryBGC}}"><img src='<%=path%>/img/icon_2.png'/>品类管理</li>
			<li onclick="loadSecMenu('${pageContext.request.contextPath}/mms/rawMaterials/showMain')" style="background-color:{{rawMaterialsBGC}}"><img src='<%=path%>/img/icon_2.png'/>原料设计</li>	
	  </ul>
	</div>
</div>