<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
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

<link href="<%=path%>/css/cms/cms.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="keywordModule" ng-controller="keywordCtrl">
	<jsp:include page="/WEB-INF/pages/common/head.jsp"></jsp:include>

	<div class="row">
		<jsp:include page="/WEB-INF/pages/common/menu.jsp"></jsp:include>
		<div class="sp-menu-right">

			<div id="content" class="sp-content">
				<div class="sp-page-title">
					<span>关键字配置</span>
				</div>
				<div class="sp-content-layout">
					<br>
				<div>
					<table class="sp-table-nohead" style="width: 800px">
					   <tbody>
						<tr ng-repeat="keyword in keywordList">
							<td style="width: 230px">
							<span ng-if="$index == 0" style="color: black">搜索框默认文字</span>
							<span ng-if="$index != 0" style="color: black">关键字{{$index}}</span>
							</td>
							<td>
								<input ng-model="keyword.keyword" ng-change="check($index)" id="keyword{{$index}}" type="text" ng-show="isedit{{$index}}" 
								class="sp-input sp-input-default" style="text-align:center;padding: 1px" />
								<span ng-show="!isedit{{$index}}" style="color: black">{{keyword.keyword}}</span>
								<span style="color:red" ng-show="check{{$index}}">关键字不能超过10字。</span>
							</td>
							<td class="last-td" style="width: 230px">
								<input type="button" ng-click="edit($index)" ng-show="!isedit{{$index}}" class="sp-btn sp-btn-black-sm" value="编辑" />
								<input type="button" ng-show="isedit{{$index}}" ng-click="save($index)" class="sp-btn sp-btn-gray-sm" value="保存" />
								<input type="button" ng-show="isedit{{$index}}" ng-click="cancel($index)" class="sp-btn sp-btn-red-sm" value="取消" />
							</td>
						</tr></tbody>
					</table>
				</div>
			</div>
		</div>
		</div>
	</div>

	<!---弹出框-提示框---->
	<div id="keywordPopHint" class="sp-popup sp-popup-sm sp-pop-alert" style="display: none; left: 687.5px; top: 372.5px; cursor: default;">
		<div class="sp-pop-content">
		  <span>{{errorMessage}}</span>
		</div>
  	</div>
  	
	<script type="text/javascript" src="<%=path%>/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/lib/angular-1.5.9.js"></script>
	<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/keyword/keyword.js"></script>
</body>

</html>