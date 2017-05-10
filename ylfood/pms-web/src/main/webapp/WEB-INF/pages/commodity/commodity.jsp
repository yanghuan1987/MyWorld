<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>PMS-商品</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<!-- 全局样式 -->
<link rel="shortcut icon"
	href="<%=path%>/img/pms/PMS_logo.ico"
	type="image/x-icon" />

<link href="<%=path%>/css/css.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/popup.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/menu.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/head.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/pagination.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=path%>/css/pms/pms.css">
</head>
<body ng-app="commodityApp" ng-controller="commodityCtrl" ng-cloak style="overflow: hidden;" class="ng-cloak">
<input type="text" id="server" value="<%=path%>" style="display: none;"/>
	<div style="height: 100%; position: absolute; width: 100%;">
		<jsp:include page="../common/commodityHead.jsp"></jsp:include>

		<!-- main container -->
			<div class="row">
				<jsp:include page="commodityMenu.jsp"></jsp:include>
				
				<!-- commodity list start -->
				<div class="sp-menu-right tab-content"
					style="height: 90%; padding-left: 0px; padding-right: 0px;" ng-show="!show && !showEdit">
					
					<div class="titlebar" style="height: 20px;">
						<i ></i>
					</div>
					
				<div class="titlebar" ng-show="category.categoryCode != null">
					<div class="sp-content-bg" style="background-color: white; width: 101%;">
						<!--面板开始-->
						<div class="sp-panel panel panel-default">
							<!--面板标题/头部-->
							<div class="sp-panel-body panel-body"
								style="background-color: white; font-size =14; color: black; font-weight: bold; height: 40px;">
								<span style="padding-left: 15px;"> 商品 - {{categoryName}} </span>
									<button type="button" ng-click="showCommodityInfoEdut()"
										class="sp-btn sp-btn-gray-md" style="margin-top: -5px;">新增商品</button>
							<!-- 搜索按钮设置 -->
							<div style="float: right !important; margin-right: 15px;">
								<input type='text' class="sp-input sp-input-search-md"
									ng-model="commodityCode"
									ng-change="searchCommotyByCode()"
									placeholder="输入搜索商品编码..." /> <input type="button"
									class="sp-btn sp-btn-search-md"
									ng-click="searchCommotyByCode()" value="搜索" />
							</div>
							</div>
							<!--面板内容开始-->
							
							<div class="sp-content-layout">
								<!--按钮工具栏开始-->
								<div style="padding-bottom: 8px;">
									<input type="checkbox" id="ck_all" ng-model="selectAll" />全选
									<button type="button" class="sp-btn sp-btn-gray-sm"
										ng-click="updateStateEdit(3)" style="margin-left: 15px;">批量上架</button>
									<button type="button" class="sp-btn sp-btn-gray-sm"
										ng-click="updateStateEdit(4)">批量下架</button>
								</div>
								<!--按钮工具栏结束-->
								<!--绑定表格开始-->
								<table id="tb"
									class="sp-table">
									<tr>
										<!--表头，这只排序字段，-->
										<th class="th-width-mini"></th>
										<th class="th-width-md">编码</th>
										<th class="th-width-md">商品名称</th>
										<th class="th-width-md">商品说明</th>
										<th class="th-width-sm">原价</th>
										<th class="th-width-sm">现价</th>
										<th class="th-width-sm">重量</th>
										<th class="th-width-sm">显示端</th>
										<th class="th-width-sm">上/下架</th>
									</tr>
									<!--重复项开始，设置排序字段，数据源，names代表数据源，x表示names中遍历的每一个对象，id表示模糊搜索输入的内容，要与输入的input的ng-model一致，col表示排序名称，要与表头中一致-->
									<tr ng-repeat="x in commodityList | filter: id | orderBy:col:desc">
										<td style="height: 30px"><input type="checkbox"
											name="cbx" value="{{x.id}}" ng-click="isSelected(x)"
											ng-checked="selectAll" /></td>
										<td style="height: 30px">
										<a ng-click="showCommodityInfo(x)" href="#" style="cursor:pointer;color: #99C731;text-decoration: underline;" title="点击查看详情">
										{{x.commodityCode}}</a>
										</td>
										<td style="height: 30px" class="autobreak">{{x.commodityName}}</td>
										<td style="height: 30px" class="autobreak">{{x.commodityComment}}</td>
										<td style="height: 30px" class="autobreak">{{x.commodityPrice}}元</td>
										<td style="height: 30px" class="autobreak">{{x.commoditySalesPrice}}元</td>
										<td style="height: 30px" class="autobreak">{{x.commodityWeight}}&nbsp;{{x.commodityWeightUnit}}</td>
										<td style="height: 30px" class="autobreak">{{x.commodityShowPlace}}</td>
										<td style="height: 30px" class="autobreak">
											<button ng-click="editSta(x,3)"
												ng-if="x.commodityStatus != 3"
												class="sp-btn sp-btn-gray-sm">上架</button>
											<button ng-click="editSta(x,4)"
												ng-if="x.commodityStatus == 3"
												class="sp-btn sp-btn-gray-sm">下架</button>
										</td>
									</tr>
									<tr ng-repeat="y in tempList">
										<td style="height: 30px"></td>
										<td style="height: 30px"></td>
										<td style="height: 30px"></td>
										<td style="height: 30px"></td>
										<td style="height: 30px"></td>
										<td style="height: 30px"></td>
										<td style="height: 30px"></td>
										<td style="height: 30px"></td>
										<td style="height: 30px"></td>
									</tr>
								</table>
								<!--绑定表格结束-->
							</div>
							<!--面板内容开始-->
							<!--模态框弹窗-->
						</div>
						<!--面板结束-->

						<!-- 分页标签  -->
						<!-- <pagination conf="paginationConf"></pagination> -->
						<br>
				<spfood-pagination conf="pagination"></spfood-pagination>
					</div>
					</div>
				</div>
				<!-- commodity list end -->
				<!-- commodityInfo start -->
				<jsp:include page="commodityInfo.jsp"></jsp:include>
				<!-- commodityInfo end -->
				<!-- 商品新增/编辑界面 start -->
				<jsp:include page="commodityInfoEdit.jsp"></jsp:include>
				<!-- 商品新增/编辑界面 end -->
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
	<!-- 防止重复提交专用-->
	<div id='submitInfo' class='sp-popup sp-popup-md'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<span>提交中</span>
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('submitInfo');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content-normal' style="padding-top: 15%;">

			<div style="width: 100%; text-align: center;">{{message}}</div>
			<br>
		</div>
	</div>
	<!-- end main container -->
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angular/angular.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/picture.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commodity/commodity.js"></script>
<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
<script type="text/javascript" src="<%=path%>/js/pagination.js"></script>
</html>