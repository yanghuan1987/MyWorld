<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<% String path = application.getInitParameter ("static-file"); %>
<title>CMS-推荐商品配置</title>
<link rel="SHORTCUT ICON" href="<%=path%>/img/cms/favicon.ico" />
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link href="<%=path%>/css/css.css" rel="stylesheet"
    type="text/css">
<link href="<%=path%>/css/table.css" rel="stylesheet"
    type="text/css">
<link href="<%=path%>/css/popup.css" rel="stylesheet"
    type="text/css">
<link href="<%=path%>/css/form.css" rel="stylesheet"
    type="text/css">
<link href="<%=path%>/css/menu.css" rel="stylesheet"
    type="text/css">
<link href="<%=path%>/css/head.css" rel="stylesheet"
    type="text/css">
<link href="<%=path%>/css/layout.css" rel="stylesheet"
    type="text/css">
<link href="<%=path%>/css/pagination.css" rel="stylesheet"
    type="text/css">
<link href="<%=path%>/css/cms/cms.css"
    rel="stylesheet" type="text/css">



</head>
<body ng-app="promotedModule" ng-cloak ng-controller="promotedCtrl">
	<jsp:include page="/WEB-INF/pages/common/head.jsp"></jsp:include>

	<div class="row">
		<jsp:include page="/WEB-INF/pages/common/menu.jsp"></jsp:include>
		<div class="sp-menu-right">
				<div class="sp-menu-right sp-pro-body">
					<div id="content" class="sp-content">
						<div class="sp-page-title">
							<span>推荐商品配置</span>
						</div>
						<div class="sp-content-layout">
						<div class="showMsg2">
						      配置页面：
						      <select class="sp-select sp-select-md" ng-model="pos" ng-change="change()">
						      <option class="sp-option-select" ng-repeat="pro in proList" value="{{pro.id}}">{{pro.name}}</option>  
						      </select>
						</div>
							<table class="sp-table">
								<thead>
									<tr>
										<th class="th-width-mini">序号</th>
										<th class="th-width-sm">商品编码</th>
										<th class="th-width-md">商品名称</th>
										<th class="th-width-mini">排序</th>
										<th class="last-th th-width-mini">操作</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="commodity in commodityList">
									   <td>{{$index+1}}</td>
									   <td>{{commodity.commodityCode}}</td>
									   <td>{{commodity.commodityName}}</td>
									   <td>{{commodity.displayOrder}}</td>
									   <td>
									   <input type="button" ng-click="click($index)" class="sp-btn sp-btn-black-sm" value="编辑">
									   </td>
									</tr>
								</tbody>
							</table>
						</div>


					</div>
				</div>
			</div>
		</div>
<!-- 	</div> -->
<script type="text/javascript" src="<%=path%>/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/lib/angular-1.5.9.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/promoted/promoted.js"></script>

<jsp:include page="/WEB-INF/pages/promoted/promotedbottom.jsp"></jsp:include>
	
	
</body>

</html>