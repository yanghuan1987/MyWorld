<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>PMS-商品评价</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<!-- 全局样式 -->
<link rel="shortcut icon" href="<%=path%>/img/pms/PMS_logo.ico"
	type="image/x-icon" />

<link href="<%=path%>/css/css.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/popup.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/menu.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/head.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/pagination.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="<%=path%>/css/datetimepicker.css"
	type="text/css">
<link rel="stylesheet" href="<%=path%>/css/pms/pms.css">
</head>
<body ng-app="commentApp" ng-controller="commentCtrl" ng-cloak
	style="overflow: hidden;" class="ng-cloak">
	<input type="text" id="server" value="<%=path%>" style="display: none;" />
	<div style="height: 100%; position: absolute; width: 100%;">

		<!-- main container -->
		<div class="row">
			<jsp:include page="commentMenu.jsp"></jsp:include>
			<!-- comment list start -->
			<div class="sp-menu-right tab-content"
				style="height: 90%; padding-left: 0px; padding-right: 0px; border-left: 5px solid #ebebeb;width: 80%;">
			<jsp:include page="../common/commentHead.jsp"></jsp:include>
				<!-- 表格 -->
				<div class="titlebar" style="padding-left: 0px;">
					<div class="sp-content-bg"
						style="background-color: white; width: 101%;">
						<!--面板开始-->
						<div class="sp-panel panel panel-default">
							<!--面板标题/头部-->
							<div class="sp-panel-body panel-body"
								style="background-color: white; font-size =14; color: black; font-weight: bold; height: 40px;">
								<span style="padding-left: 15px;"> 商品评价 —
									{{categoryName}} </span>
							</div>
							<div class="sp-row-border row">
								<!--面板内容开始-->
								<div ng-show="showCategoryAndCommodityTable">
									<jsp:include page="commentCategoryAndCommodity.jsp"></jsp:include>
								</div>
								<div ng-show="showCommodityCommentTable">
									<jsp:include page="commentInfo.jsp"></jsp:include>
								</div>
								<!--面板结束-->
								<!-- 分页标签  -->
								<!-- <pagination conf="paginationConf"></pagination> -->
								<br>
								<spfood-pagination conf="pagination"></spfood-pagination>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>


	</div>
	<!-- 模态框（Modal） -->
	<div id='showInfo' class='sp-popup sp-popup-md'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<span>提示框</span>
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('showInfo');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content-normal' style="padding-top: 15%;">

			<div style="width: 100%; text-align: center;">{{message}}</div>
			<br>
			<div style="width: 100%; text-align: center;">
				<button type="button" class="sp-btn sp-btn-red-sm"
					onclick="exitPop('showInfo')">关闭</button>
				<button ng-show="stateSure" type="button"
					class="sp-btn sp-btn-gray-sm" ng-click="updateState()">确认</button>
			</div>
		</div>
	</div>
	<!-- end main container -->
	<!---弹出框-超级大---->
	<div id='showPic' class='sp-popup sp-popup-hg'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<span>大图显示</span>
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('showPic');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content-lg'>
			<div style='width: 100%; height: 580px;'>
				<img ng-src="{{chosePictureUrl}}" style="width: 100%;float: left;height:100%" />
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angular/angular.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/comment/comment.js"></script>
<script type="text/javascript" src="<%=path%>/js/lib/datetimepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
<script type="text/javascript" src="<%=path%>/js/pagination.js"></script>
</html>