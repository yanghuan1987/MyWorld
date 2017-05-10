<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<% String path = application.getInitParameter ("static-file"); %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>CMS-内容管理系统</title>
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
<body ng-app="catPromotePosNewApp" ng-cloak ng-controller="catPromotePosNewctrl">

	<jsp:include page="../common/head.jsp"></jsp:include>

	<div class="row">
		<jsp:include page="/WEB-INF/pages/common/menu.jsp"></jsp:include>
		<div class="sp-menu-right">
			<div id="content" class="sp-content">
				<div class="sp-page-title">
					<span>商品组明细</span>
				</div>
				<div class="sp-content-layout">
				<table>
					<tr>
						<td ><span style="color: red;">*</span>商品组图:</td>
						<td><img id="catPicture" style="width: 50px; height: 50px;"
						ng-src="{{item.pictureAddress}}" /></td>
						<td><input type="file" id="one-input"
						file-model="images" name="oneinput"
						onchange="angular.element(this).scope().img_upload(this.files)" style="color: white;"/></td>
						
						<td><span style="color: red;">*</span>组图名称:</td>
						<td><input type="text" class="sp-input sp-input-lg" maxlength="16" ng-model="catPromotePoslist.categoryName" required></td>
						<td><span style="color: red;">*</span>广告词:</td>
						<td><input type="text" class="sp-input sp-input-lg" maxlength="15" ng-model="catPromotePoslist.advert" required></td>
						<td><span style="color: red;">*</span>排序:</td>
						<td><input type="text" class="sp-input sp-input-md" maxlength="3" ng-model="mainDisplayOrder" ng-pattern="/^[1-9]\d*$/" required></td>
					</tr>
				</table>
				<br>
				<div>
					<table class="sp-table table-striped table-hover table-responsive">
						<tr>
							<th class="th-width-sm">序号</th>
							<th class="th-width-sm">商品编码</th>
							<th class="th-width-md">商品名称</th>
							<th class="th-width-sm">排序</th>
							<th class="th-width-sm">操作</th>
						</tr>
						<tr ng-repeat="x in catPromotePoslist.catPromotedCommodityList">
							<td>{{$index+1}}</td>
							<td>{{x.commodityCode}}</td>
							<td>{{x.commodityName}}</td>
							<td>{{x.displayOrder}}</td>
							<td><input type="button" class="sp-btn sp-btn-black-sm"
								value="编辑" ng-click="getCommodityinfo($index)"></td>
						</tr>
					</table>
				</div>
				<br>
				<br>
				<br>
				<br>
				<div style="text-align: center;">
					<input type="button" class="sp-btn sp-btn-gray-md" value="保存"
						ng-click="mainSavedate()" ng-disabled="saveButtom"> <input type="button"
						class="sp-btn sp-btn-gray-md" value="取消" ng-click="mainCancel()">
				</div>
				
			</div>
				
			</div>

		</div>
	</div>
	<script type="text/javascript" src="<%=path%>/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/lib/angular-1.5.9.js"></script>
    <script type="text/javascript"
        src="${pageContext.request.contextPath}/js/catPromotePos/catPromotePos.js"></script>
	<jsp:include page="./catPromotePosbottom.jsp"></jsp:include>

</body>

</html>