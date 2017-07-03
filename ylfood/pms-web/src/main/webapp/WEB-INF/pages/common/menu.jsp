<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<% String path = application.getInitParameter("static-file"); %>
<div style="background-color: #4b4b4b;border-bottom: 3px solid #ebebeb;height: 200px;">
   <div style="padding-left: 50px;padding-top: 15px;">
     <span>
     	<img src="<%=path%>/img/pms/PMS_logo.png"/>
    </span>
   </div>
<%-- 	  <ul class="sp-ul-menu" style="color: #fff;">
	    	<li onclick="loadSecMenu('${pageContext.request.contextPath}/pms/category/showMain')" 
	    	style="background-color:{{categoryBGC}}"><img src='<%=path%>/img/icon_2.png'/>品类管理</li>
			<li onclick="loadSecMenu('${pageContext.request.contextPath}/pms/product/showMain')" 
			style="background-color:{{productBGC}}"><img src='<%=path%>/img/icon_2.png'/>产品设计</li>
			<li onclick="loadSecMenu('${pageContext.request.contextPath}/pms/commodity/showMain')" s
			tyle="background-color:{{commodityBGC}}"><img src='<%=path%>/img/icon_2.png'/>商品设计</li>
			<li onclick="loadSecMenu('${pageContext.request.contextPath}/pms/comment/showMain')" 
			style="background-color:{{commentBGC}}"><img src='<%=path%>/img/icon_2.png'/>评价管理</li>
	  </ul> --%>
	  <div style="margin-left: 10px;margin-top: 30px;">
	  		<a href='${pageContext.request.contextPath}/pms/category/showMain')' 
	  		style="display: block;width: 100px;height: 30px;text-align: center;font-size: 14px;float: left;
	  		line-height: 25px;cursor: pointer;border-radius: 20px;color: #fff;font-weight: bold;
			border: 2px solid #99C731;background-color:{{categoryBGC}}">品类管理</a>
			<a href='${pageContext.request.contextPath}/pms/product/showMain')' 
	  		style="display: block;width: 100px;height: 30px;text-align: center;font-size: 14px;float: left;
	  		line-height: 25px;cursor: pointer;border-radius: 20px;color: #fff;font-weight: bold;margin-left: 20px;
			border: 2px solid #99C731;background-color:{{productBGC}}">产品设计</a>
			<a href='${pageContext.request.contextPath}/pms/commodity/showMain')' 
	  		style="display: block;width: 100px;height: 30px;text-align: center;font-size: 14px;float: left;
	  		line-height: 25px;cursor: pointer;border-radius: 20px;color: #fff;font-weight: bold;margin-left: 20px;
			border: 2px solid #99C731;background-color:{{commodityBGC}}">商品设计</a>
			<a href='${pageContext.request.contextPath}/pms/comment/showMain')' 
	  		style="display: block;width: 100px;height: 30px;text-align: center;font-size: 14px;float: left;
	  		line-height: 25px;cursor: pointer;border-radius: 20px;color: #fff;font-weight: bold;
	  		margin-top: 20px;border: 2px solid #99C731;background-color:{{commentBGC}}">评价管理</a>
	  </div>
</div>