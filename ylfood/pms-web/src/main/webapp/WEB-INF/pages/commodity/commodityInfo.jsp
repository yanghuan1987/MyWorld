<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<div class="sp-menu-right" ng-show="show" ng-cloak>
	<div class="titlebar" style="height: 20px;">
		<i ></i>
	</div>
	<div class="sp-content-bg"
			style="background-color: white; width: 98%; 
			margin-left: 15px;overflow-x: hidden;">
		<!--面板开始-->
		<div class="sp-panel panel panel-default">
			<div class="sp-panel-body panel-body category-name-head">
				<p class="sp-p-title">
					商品-->{{categoryName}}</span>
					<button type="button" ng-click="showCommodityInfoEduts()"
						class="sp-btn sp-btn-gray-md" style="margin-top: -8px;">编辑</button>
				</p>
			</div>
			<!--面板内容开始-->

			<div class="sp-row-border row" style="padding: 5px 0px 18px 5px;">
				<!-- 选择产品st -->
				<div class="titlebar sp-mune-title">
					<i class="pms-icon-kz"></i> 选择产品
				</div>
				<div ng-repeat="prod in productList">
					<table class="table-nobreak" style="margin-left: 15px;">
						<tbody>
							<tr>
								<td class="td-nobreak" style="width: 65%;">
									{{prod.productName}}|{{prod.productSpecificationValue}}{{prod.productSpecificationUnitFirst}}/{{prod.productSpecificationUnitSecond}}
									<span ng-repeat="proper in prod.productProperties">{{proper.productPropertyValue}}|</span>
								</td>
								<td>{{commodity.commodityProductQuantity}}</td>
							</tr>
						</tbody>
					</table>
					<br>
				</div>
				<!-- 选择产品ed -->
				<!-- 商品属性st -->
				<div class="titlebar sp-mune-title">
					<i class="pms-icon-kz"></i> 商品属性
				</div>
				<div>
					<table
						style="width: 100%; height: 100%; white-space: nowrap; table-layout: fixed;">
						<tbody>
							<tr class="success" style="height: 30px;">
								<td class="td-right-view">商品编码：</td>
								<td colspan="5" class="ng-binding text-left td-nobreak">{{commodity.commodityCode}}</td>
							</tr>
							<tr class="success" style="height: 30px;">
								<td class="td-right-view">商品GS1编码：</td>
								<td colspan="5" class="ng-binding text-left td-nobreak">{{commodity.commodityGs1Code}}</td>
							</tr>
							<tr class="success" style="height: 30px;">
								<td class="td-right-view">商品名称：</td>
								<td colspan="5" class="ng-binding text-left td-nobreak">{{commodity.commodityName}}</td>
							</tr>
							<tr style="height: 30px;">
								<td class="td-right-view">商品说明：</td>
								<td colspan="5" class="ng-binding text-left td-nobreak">{{commodity.commodityComment}}</td>
							</tr>
							<tr style="height: 30px;">
								<td class="td-right-view">原价：</td>
								<td colspan="2" class="ng-binding text-left">{{commodity.commodityPrice}}&nbsp;元</td>
								<td class="td-right-view">现价：</td>
								<td colspan="2"class="ng-binding text-left">{{commodity.commoditySalesPrice}}&nbsp;元</td>
							</tr>
							<tr style="height: 30px;">
								<td class="td-right-view">重量：</td>
								<td colspan="2" class="ng-binding text-left">{{commodity.commodityWeight}}&nbsp;{{commodity.commodityWeightUnit}}</td>
								<td class="td-right-view">显示端：</td>
								<td colspan="2"class="ng-binding text-left">{{commodity.commodityShowPlace}}</td>
							</tr>
							<tr style="height: 30px;">
								<td class="td-right-view">商品评价：</td>
								<td colspan="2" class="ng-binding text-left">好评:{{commodityCommentGood}}&nbsp;&nbsp;&nbsp;&nbsp;差评:{{commodityCommentBad}}</td>
							</tr>
						</tbody>
					</table>
					<br>
				</div>
				<!-- 商品属性ed -->
				<!-- 可送达地区st -->
				<div class="titlebar sp-mune-title">
					<i class="pms-icon-kz"></i> 可送达地区
				</div>
				<div ng-repeat="citylist in divisionNode">
					<span>{{citylist.name}}</span>
					<div ng-repeat="city in citylist.children" style="margin-left: 100px;background-color: #ffffff">
						<span ng-if="citynow.code == city.code" ng-repeat="citynow in citylistDB">{{city.name}}</span>
					</div>
				</div>
				<!-- 可送达地区ed -->
				<!-- 头部图片st -->
				<div class="titlebar sp-mune-title">
					<i class="pms-icon-kz"></i> 头部图片
				</div>
				<!--  -->
				<div>
					<br>
					<!-- 采用angular循环的方式，对存入thumb的图片进行展示 -->
					<label ng-repeat="item in thumbHeader"
						style="width: 15%; margin-left: 1.5%; margin-top: 3%;display: inline-block;"><img
						ng-src="{{item.pictureAddress}}" class="sp-picture-title" /></label> <br>
				</div>
				<!-- 头部图片ed -->
					<br>
				<!-- 详情页图片st -->
				<div class="titlebar sp-mune-title">
					<i class="pms-icon-kz"></i> 详情页图片
				</div>
				<div>
					<label ng-repeat="item in thumbDetail" style="width: 100%;">
						<img ng-src="{{item.pictureAddress}}" class="sp-picture-detail" />
					</label>
				</div>
				<!-- 详情页图片ed -->
			</div>
		</div>
	</div>
</div>