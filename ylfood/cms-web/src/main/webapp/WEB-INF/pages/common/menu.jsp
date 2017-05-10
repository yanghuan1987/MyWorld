<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<% String path = application.getInitParameter("static-file"); %>
<div class="sp-menu-left">
	<div class="sp-menu">
	  <ul class="sp-ul-menu">
	    <shiro:hasPermission name="cms:keyword:showPage">
	    	<li onclick="loadSecMenu('${pageContext.request.contextPath}/cms/keyword/main')" class="searchKeyword"><img src='<%=path%>/img/icon_2.png'/>关键字配置</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="cms:MenuConfig:showPage">
			<li onclick="loadSecMenu('${pageContext.request.contextPath}/cms/MenuConfig/showPage')" class="menuConfig"><img src='<%=path%>/img/icon_2.png'/>菜单配置</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="cms:advertisement:showPage">
			<li onclick="loadSecMenu('${pageContext.request.contextPath}/cms/advertisement/showPage')" class="advertisement"><img src='<%=path%>/img/icon_2.png'/>Banner配置</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="cms:promotedCommodity:showPage">
			<li onclick="loadSecMenu('${pageContext.request.contextPath}/cms/promotedCommodity/promotedMain')" class="proCommodity"><img src='<%=path%>/img/icon_2.png'/>推荐商品配置</li>	
		</shiro:hasPermission>
		<shiro:hasPermission name="cms:catPromotePos:showPage">
			<li onclick="loadSecMenu('${pageContext.request.contextPath}/cms/catPromotePos/showMain')" class="catPromotePos"><img src='<%=path%>/img/icon_2.png'/>商品组版面配置</li>
		</shiro:hasPermission>
	  </ul>
	</div>
</div>