<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<!-- start container -->
<div class="sp-menu-right" ng-show="showEdit" ng-cloak>
	<form name="commodityAddForm" enctype="multipart/form-data">
	<!-- 预览popup -->	
	<div id='editpopUpHg' class='sp-popup sp-popup-hg'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<!-- <span>弹出框标题</span> -->
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('editpopUpHg');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content'>
			<div id="content" class="sp-content" style="border: 0;overflow-y: auto;overflow-x: hidden;height: 600px;">
				<div class="sp-page-title" style="margin-left: 0;">
					<div style="text-align: center; width: 100%;">商品预览</div>
				</div>
				<div class="sp-content-layout">
					<!--面板内容开始-->
						<!-- 选择产品st -->
						<div class="titlebar sp-mune-title">
							<i class="pms-icon-kz"></i> 选择产品
						</div>
						<div ng-repeat="prod in productList"
							ng-show="prod.productCode == commodity.product.productCode">
							<table class="table-nobreak" style="margin-left: 15px;">
								<tbody>
									<tr>
										<td class="td-nobreak" style="width: 65%;">
											{{prod.productName}}&nbsp;|&nbsp;{{prod.productSpecificationValue}}{{prod.productSpecificationUnitFirst}}/{{prod.productSpecificationUnitSecond}}
											<span ng-repeat="proper in prod.productProperties">&nbsp;|&nbsp;{{proper.productPropertyValue}}</span>
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
										<td class="td-right">商品名称：</td>
										<td colspan="5" class="ng-binding text-left td-nobreak">{{commodity.commodityName}}</td>
									</tr>
									<tr style="height: 30px;">
										<td class="td-right">商品说明：</td>
										<td colspan="5" class="ng-binding text-left td-nobreak">{{commodity.commodityComment}}</td>
									</tr>
									<tr style="height: 30px;">
										<td class="td-right">原价：</td>
										<td colspan="2" class="ng-binding text-left">{{commodity.commodityPrice}}&nbsp;元</td>
										<td class="td-right">现价：</td>
										<td colspan="2" class="ng-binding text-left">{{commodity.commoditySalesPrice}}&nbsp;元</td>
									</tr>
									<tr style="height: 30px;">
										<td class="td-right">重量：</td>
										<td colspan="2" class="ng-binding text-left">{{commodity.commodityWeight}}&nbsp;{{commodity.commodityWeightUnit}}</td>
										<td class="td-right">显示端：</td>
										<td colspan="2" class="ng-binding text-left">{{commodity.commodityShowPlace}}</td>
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
								<span ng-if="city.changeFlag">{{city.name}}</span>
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

	<div class="titlebar" style="height: 20px;">
		<i ></i>
	</div>
	<div class="sp-content-bg"
			style="background-color: white; width: 98%; 
			margin-left: 15px;overflow-x: hidden;">
		<div class="sp-panel panel panel-default">
			<div class="sp-panel-body panel-body category-name-head">
				<p class="sp-p-title">
					商品-->{{categoryName}}<input type="button"
							class="sp-btn sp-btn-black-sm" value="预 览"
							onclick="showPopup('editpopUpHg',true)">
				</p>
			</div>
			<!-- 面板开始 -->
			<div class="sp-row-border row" style="padding: 5px 0px 18px 5px;">
				<!-- 选择产品st -->
				<div class="titlebar sp-mune-title">
					<i class="pms-icon-kz"></i> 选择产品
				</div>

				<div class="titlebar" ng-show="flg">
					<table class="sp-table" id="table1">
						<caption>产品列表</caption>
						<tbody>
							<tr ng-repeat="prod in productList"
								ng-click="chooseProduct(prod)" id="{{prod.id}}">
								<td class="last-td">{{prod.productName}}&nbsp;|&nbsp;{{prod.productSpecificationValue}}{{prod.productSpecificationUnitFirst}}/{{prod.productSpecificationUnitSecond}}
									<span ng-repeat="proper in prod.productProperties">&nbsp;|&nbsp;{{proper.productPropertyValue}}</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- end product list -->
				<!-- start edit product -->
				<div class="sp-panel-body panel-body category-name-head">
					<p class="sp-p-title">
						已选择产品</span>
					</p>
				</div>
				<div ng-show="flg" class="sp-row-border row" style="padding: 5px 0px 18px 5px;">
				</div>
				<div class="titlebar">
					<div ng-show="flg" style="margin-top: 10px;">
						<span style="color: red;" ng-show="commodity.product == null">必须选择产品</span>
						<div ng-repeat="prod in productList"
							ng-show="prod.productCode == commodity.product.productCode">
							<table class="table-nobreak" style="margin-left: 15px;">
								<tbody>
									<tr>
										<td class="td-nobreak"
											style="width: 65%; background-color: #ccc;">
											{{prod.productName}}&nbsp;|&nbsp;{{prod.productSpecificationValue}}{{prod.productSpecificationUnitFirst}}/{{prod.productSpecificationUnitSecond}}
											<span ng-repeat="proper in prod.productProperties">&nbsp;|&nbsp;{{proper.productPropertyValue}}</span>
										</td>
										<td style="width: 25%;">
										<input size="3" type="text" style="width: 50px;" class="sp-input sp-input-lg" ng-model="commodity.commodityProductQuantity"/>
										<!-- <i class="pms-icon-jhh"
												ng-click="minusNumber()"></i>
											<span>{{commodity.commodityProductQuantity}}</span> <i
											class="pms-icon-jh" ng-click="addNumber()"></i> -->
										</td>
									</tr>
								</tbody>
							</table>
							<br>
						</div>
					</div>
					<div ng-show="!flg" style="margin-top: 10px;">
						<div ng-show="countzero">
							<div ng-repeat="prod in productList"
								ng-show="prod.productCode == commodity.product.productCode">
								<table class="table-nobreak" style="margin-left: 15px;">
									<tbody>
										<tr>
											<td class="td-nobreak"
												style="width: 65%; background-color: #ccc;"
												ng-show="choosePro.id == null && prod.productCode == commodity.product.productCode">
												{{prod.productName}}&nbsp;|&nbsp;{{prod.productSpecificationValue}}{{prod.productSpecificationUnitFirst}}/{{prod.productSpecificationUnitSecond}}
												<span ng-repeat="proper in prod.productProperties">&nbsp;|&nbsp;{{proper.productPropertyValue}}</span>
											</td>
											<td class="td-nobreak"
												style="width: 65%; background-color: #ccc;"
												ng-show="choosePro.id != null">
												{{prod.productName}}&nbsp;|&nbsp;{{prod.productSpecificationValue}}{{prod.productSpecificationUnitFirst}}/{{prod.productSpecificationUnitSecond}}
												<span style="background-color: #ccc;"
												ng-repeat="proper in choosePro.productProperties">&nbsp;|&nbsp;{{proper.productPropertyValue}}</span>
											<td style="width: 25%;">
												<span>{{commodity.commodityProductQuantity}}</span>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<br>
						</div>
					</div>
				</div>
				<!-- end edit product -->
				<!-- start base property -->
				<div class="titlebar sp-mune-title">
					<i class="pms-icon-kz"></i> 商品属性
				</div>
				<div class="titlebar"
					style="min-height: 120px; white-space: nowrap;">
					<table
						style="width: 100%; height: 100%; margin-top: 15px; table-layout: fixed;">
						<tbody>
							<tr class="success" style="height: 40px;">
								<td class="td-right"><span style="color: red">*</span>商品名称：</td>
								<td colspan="5" style="text-align: left;"><input
									size="10" maxlength="128" type="text" class="sp-input sp-input-lg"
									ng-model="commodity.commodityName"/></td>
							</tr>
							<tr style="height: 40px;">
								<td class="td-right"><span style="color: red">*</span>商品说明：</td>
								<td colspan="5" style="text-align: left;">
								<textarea size="10" maxlength="128"  rows="3" cols="3" class="sp-text"
									ng-model="commodity.commodityComment" style="width: 74%;"></textarea>
								</td>
							</tr>
							<tr style="height: 40px;">
								<td class="td-right"><span style="color: red">*</span>原价：</td>
								<td colspan="2"><input
									size="6" maxlength="10" type="text" ng-pattern="/^[0-9]{1,8}([.][0-9]{1,2})?$/" class="sp-input sp-input-lg" style="float: left;"
									ng-model="commodity.commodityPrice" name="commodityPrice" /><span style="float: left;margin: 5px;">元</span></td>
								<td class="td-right"><span style="color: red">*</span>现价：</td>
								<td colspan="2"><input
									size="6" maxlength="10" type="text" ng-pattern="/^[0-9]{1,8}([.][0-9]{1,2})?$/" class="sp-input sp-input-lg" style="float: left;"
									ng-model="commodity.commoditySalesPrice" name="commoditySalesPrice" /><span style="float: left;margin: 5px;">元</span></td>
							</tr>
							<tr style="height: 40px;">
								<td class="td-right"><span style="color: red">*</span>重量：</td>
								<td colspan="2"><input size="4" class="sp-input sp-input-lg" style="float: left;"
									maxlength="5" type="text" ng-model="commodity.commodityWeight" /></td>
								<td class="td-right"><span style="color: red">*</span>单位：</td>
								<td  colspan="2" style="float: left;">
								
								<select class="sp-select sp-select-lg"
									style="float: left; height: 30px;"
									ng-model="commodity.commodityWeightUnit">
									<option ng-repeat="x in basicWeightUnit"
										value="{{x.optionName}}">{{x.optionName}}</option>
								</select>
								</td>
							</tr>
							<tr style="height: 40px;">
								<td class="td-right"></td>
								<td colspan="2" class="ng-binding text-left"></td>
								<td class="td-right"><span style="color: red">*</span>显示端：</td>
								<td  colspan="2" style="float: left;">
								
								<select class="sp-select sp-select-lg"
									style="float: left; height: 30px;"
									ng-model="commodity.commodityShowPlace">
									<option ng-repeat="x in basicCustomerType"
										value="{{x.optionName}}">{{x.optionName}}</option>
								</select>
								</td>
							</tr>
						</tbody>
					</table>
					<br>
				</div>
				<!-- end base property -->


				<!-- 可送达地区st -->
				<div class="titlebar sp-mune-title">
					<i class="pms-icon-kz"></i> 可送达地区 
				</div>
				
				<div ng-show="flg">
					<div class="titlebar"
						style="min-height: 120px; white-space: nowrap;">
						<div style="float: left;width: 20%;">
							<div ng-repeat="citylist in divisionNode">
								<div style="margin-left: 10px;">
									<input type="button" value="{{citylist.name}}" ng-click="oncitylist($index)" class="sp-btn sp-btn-black-sm">
								</div>
							</div>
						</div>
						<div ng-if="showcity" style="position: absolute;margin-left: 92px;">
						 	<div ng-if="showcity" ng-repeat="city in selectcity.children" style="float: left;margin-left: 10px;">
								<input type="checkbox"
										id={{city.code}} name="{{city.name}}" ng-checked="city.changeFlag"
										ng-click="updateSelection($event,city.code,$index)" style="float: left;">{{city.name}}
							</div>
						</div>
					</div>
				</div>
				<div ng-show="!flg">
					<div class="titlebar"
						style="min-height: 120px; white-space: nowrap;">
						
						<div style="float: left;width: 20%;">
							<div ng-repeat="citylist in divisionNode">
								<div style="margin-left: 10px;">
									<input type="button" value="{{citylist.name}}" ng-click="oncitylist($index)" class="sp-btn sp-btn-black-sm">
								</div>
							</div>
						</div>
						<div ng-if="showcity" style="position: absolute;margin-left: 92px;">
						 	<div ng-if="showcity" ng-repeat="city in selectcity.children" style="float: left;margin-left: 10px;">
								<input type="checkbox" class="sp-btn sp-btn-black-sm"
										id={{city.code}} name="{{city.name}}" ng-checked="city.changeFlag"
										ng-click="updateSelection($event,city.code,$index)">{{city.name}}								
							</div>
						</div>
					</div>
				</div>
				<!-- 可送达地区ed -->
				
				<!-- 图片模板开始 -->
				<jsp:include page="../common/picture.jsp"></jsp:include>
				<!-- 图片模板结束-->
			</div>
			<div class="titlebar"
				style="min-height: 50px; margin-bottom: 60px;">
				<button ng-show="commodity.commodityCode == null"
					class="sp-input-button btn btn-default" ng-disabled = "doubleClick"
					style="width: 100%; margin-top: 10px;" ng-click="onAddSaveClick()">完成</button>
				<button ng-show="commodity.commodityCode != null"
					class="sp-input-button btn btn-default" ng-disabled = "doubleClick"
					style="width: 100%; margin-top: 10px;"
					ng-click="onAddSaveClickUpdate()">完成</button>
			</div>
			<!-- start picture -->
		</div>
	</div>
	</form>
</div>
<!-- end container -->
