<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter ("static-file"); %>
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
<body ng-app="catPromotePosApp" ng-cloak ng-controller="catPromotePosctrl">

	<jsp:include page="../common/head.jsp"></jsp:include>

	<div class="row">
		<jsp:include page="/WEB-INF/pages/common/menu.jsp"></jsp:include>
		<div class="sp-menu-right">

			<div id="content" class="sp-content">
				<div class="sp-page-title">
					<span>商品组版面配置</span>
				</div>

				<div class="sp-content-layout">
					<div style="text-align: right;">
					     <input type="button" class="sp-btn sp-btn-black-sm" ng-click="refresh()" value="刷新页面">

					    <input type="button" ng-click="creatNew()" style="background-color: #769a23" class="sp-btn sp-btn-green-sm" value="添加商品组">
					</div>
					<br>
					<div>
						<table class="sp-table table-striped table-hover table-responsive">
							<tr>
								<th class="th-width-md">商品组图</th>
								<th class="th-width-md">组图名称</th>
								<th class="th-width-md">广告词</th>
								<th class="th-width-sm">排序</th>
								<th class="th-width-sm last-th">操作</th>
							</tr>
							<tr ng-repeat="x in catPromotePoslist">
								<td style="height: 60px;"><img ng-src="{{x.imageUrl}}" class="picture-detail" /></td>
								<td>{{x.categoryName}}</td>
								<td>{{x.advert}}</td>
								<td>{{x.displayOrder}}</td>
								<td class="last-td"><input type="button" class="sp-btn sp-btn-black-sm"
									value="编辑" ng-click="choseEdit($index)"> <input
									type="button" class="sp-btn sp-btn-red-sm" value="删除"
									ng-click="choseDelete($index)"></td>
							</tr>
						</table>
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