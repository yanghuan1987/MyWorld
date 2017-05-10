<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = application.getInitParameter("static-file");
%>
<div ng-cloak class="sp-menu-right sp-product-head"
	ng-controller="compositeProductDetailController" ng-show="showCproduct">
	<!-- 预览框 -->

	<div id='compositeProducteditpopUpHg' class='sp-popup sp-popup-hg'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<!-- <span>弹出框标题</span> -->
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('compositeProducteditpopUpHg');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content'>
			<div id="content" class="sp-content" style="border: 0;overflow-y: auto;overflow-x: hidden;height: 600px;">
				<div class="sp-page-title" style="margin-left: 0;">
					<div style="text-align: center; width: 100%;">产品预览</div>
				</div>
				<div class="sp-content-layout">
					<!-- 表头品类及搜索结果部分 结束-->
					<div class="titlebar sp-mune-title" style="margin-bottom: 6px;">
						<i class="pms-icon-kz"></i> 已选择产品
					</div>
					<div ng-repeat="productItem in selectedProductItems"
						style="padding: 5px;">
						<span>{{$index + 1}}</span> <span>{{productItem.product.productName}}</span>
						<span
							ng-repeat="property in productItem.product.productProperties">&nbsp;|&nbsp;{{property.productPropertyValue}}</span>
						<span> {{productItem.quantity}} </span>
					</div>
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品属性
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品名称：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{compositeProduct.product.productName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品编码：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{compositeProduct.product.productCode}}</label><br>
					</div>

					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品GS1编码：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{compositeProduct.product.productGs1Code}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">是否可售：</label>
						<label class="viewlabel text-left" style="width: 75%;"
							ng-if="compositeProduct.product.saleFlag==1">是</label> <label
							class="col-md-9 col-xs-7 text-left"
							ng-if="compositeProduct.product.saleFlag==0">否</label> <br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品规格：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{compositeProduct.product.productSpecificationValue}}
							{{compositeProduct.product.productSpecificationUnitFirst}} /
							{{compositeProduct.product.productSpecificationUnitSecond}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品状态：
						</label> <label ng-repeat="x in productStatus"
							ng-if="compositeProduct.product.productStatus == x.optionValue"
							class="viewlabel text-left" style="width: 75%;">{{x.optionName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">采购基本单位：
						</label> <label
							class="viewlabel text-left" style="width: 75%;">{{compositeProduct.product.productBuyUnitName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">温区：
						</label> <label
							class="viewlabel text-left" style="width: 75%;">{{compositeProduct.product.productTemperatureZoneName}}</label><br>
					</div>
					<!-- 产品属性显示 -->
					<div
						ng-repeat="productProperty in compositeProduct.product.productProperties"
						class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">{{productProperty.productPropertyName}}：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{productProperty.productPropertyValue}}</label><br>
					</div>
					<div class="titlebar" ng-repeat="packUnit in productPackUnit">
						<label class="viewlabel text-right" style="width: 25%;" ng-show="$index==0">包装单位： </label> 
						<label class="viewlabel text-right" style="width: 25%;" ng-show="$index!=0">  </label> 
						<label 
							class="viewlabel text-left" style="width: 75%;">{{packUnit.unitValue}}{{compositeProduct.product.productBuyUnitName}}/{{packUnit.unitName}}</label><br>
					</div>
					<!-- 产品图片显示 -->
					<!-- 产品详细显示结束 -->
					<!-- 头部图片st -->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 头部图片
					</div>
					<!--  -->
					<div>
						<br>
						<!-- 采用angular循环的方式，对存入productPictures的图片进行展示 -->
						<label ng-repeat="picture in thumbHeader"
							style="width: 15%; margin-left: 1.5%; margin-top: 3%;display: inline-block;"> <img
							ng-src="{{picture.pictureAddress}}" class="sp-picture-title" />
						</label> <br>
					</div>
					<!-- 头部图片ed -->
						<br>
					<!-- 详情页图片st -->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 详情页图片
					</div>
					<div>
						<label ng-repeat="picture in thumbDetail" style="width: 100%;">
							<img ng-src="{{picture.pictureAddress}}"
							class="sp-picture-detail" />
						</label>
					</div>
					<!-- 详情页图片ed -->
				</div>
			</div>
		</div>
	</div>

	<!-- 产品详情信息显示 -->
	<div ng-show="!editCProduct">
		<!-- 顶部按钮 -->
		<div class="titlebar" style="height: 20px;">
			<i ></i>
		</div>
		<!-- 顶部按钮 end -->
		<div class="sp-content-bg"
			style="background-color: white; width: 98%;margin-left: 15px;overflow-x: hidden;">
			<!--面板开始-->
			<div class="sp-panel panel panel-default">
				<div class="sp-panel-body panel-body category-name-head">
					<p class="sp-p-title">
						<span>产品-->{{compositeProduct.product.productName}}</span>
						<button type="button" ng-click="onEditClick()"
							class="sp-btn sp-btn-gray-md" style="margin-top: -8px;">编辑</button>
					</p>
				</div>
				<!--面板内容开始-->
				<div class="sp-row-border row" style="padding: 5px 0px 18px 5px;">
					<!-- 产品  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 已选择产品
					</div>
					<div ng-repeat="productItem in compositeProduct.productItems">
						<label>
							<div style="padding-left: 20px;">
								<span>{{$index+1}} </span><span>{{productItem.product.productName}}</span>
								<span
									ng-repeat="property in productItem.product.productProperties">&nbsp;|&nbsp;{{property.productPropertyValue}}</span>
								<span> *{{productItem.quantity}}</span>
							</div>
						</label>
					</div>
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品基本信息
					</div>
					<!-- 产品详细显示开始 -->
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品名称：</div>
						<div class="valueName" style="padding: 3px;">{{compositeProduct.product.productName}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品编码：</div>
						<div class="valueName" style="padding: 3px;">{{compositeProduct.product.productCode}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品GS1编码：</div>
						<div class="valueName" style="padding: 3px;">{{compositeProduct.product.productGs1Code}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">是否可售：</div>
						<div class="valueName" style="padding: 3px;">
							<span ng-if="compositeProduct.product.saleFlag==1">是</span> <span
								ng-if="compositeProduct.product.saleFlag==0">否</span>
						</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品规格：</div>
						<div class="valueName" style="padding: 3px;">
							{{compositeProduct.product.productSpecificationValue}}
							{{compositeProduct.product.productSpecificationUnitFirst}} /
							{{compositeProduct.product.productSpecificationUnitSecond}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品状态：</div>
						<div class="valueName" ng-repeat="x in productStatus"
							ng-if="compositeProduct.product.productStatus == x.optionValue"
							style="padding: 3px;">{{x.optionName}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">采购基本单位：</div>
						<div class="valueName"
							style="padding: 3px;">{{compositeProduct.product.productBuyUnitName}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">温区：</div>
						<div class="valueName"
							style="padding: 3px;">{{compositeProduct.product.productTemperatureZoneName}}</div>
					</div>
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品属性
					</div>
					<div class="titlebar">
						<div
							ng-repeat="property in compositeProduct.product.productProperties"
							class="viewproper">
							<div class="control-label"
								style="text-align: right; padding: 3px; width: 170px; float: left;">{{property.productPropertyName}}：</div>
							<div style="padding: 3px; float: left; width: 58.33333333%;">{{property.productPropertyValue}}</div>
						</div>
					</div>
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 包装单位
					</div>
					<div class="titlebar">
						<div class="viewproper"
							ng-repeat="packUnit in productPackUnit">
							<div class="control-label"
							style="text-align: right; padding: 3px; width: 170px; float: left;">
								{{packUnit.unitValue}}{{compositeProduct.product.productBuyUnitName}}/{{packUnit.unitName}}</div>
							<div style="padding: 3px; float: left; width: 58.33333333%;">
								 </div>
						</div>
					</div>

					<!-- 产品详细显示结束 -->
					<!-- 头部图片st -->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 头部图片
					</div>
					<!--  -->
					<div style="margin-left: 120px; width: 70%;">
						<br>
						<!-- 采用angular循环的方式，对存入productPictures的图片进行展示 -->
						<label
							ng-repeat="picture in compositeProduct.product.productPictures"
							style="width: 15%; margin-left: 1.5%; margin-top: 3%;display: inline-block;"> <img
							ng-show="picture.pictureShowPosition == 0"
							ng-src="{{picture.pictureAddress}}" class="sp-picture-title" /></label>
						<br>
					</div>
					<!-- 头部图片ed -->
					<!-- 详情页图片st -->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 详情页图片
					</div>
					<div>
						<label
							ng-repeat="picture in compositeProduct.product.productPictures"
							style="width: 100%; text-align: center;"> <img
							ng-show="picture.pictureShowPosition == 1"
							ng-src="{{picture.pictureAddress}}" class="sp-picture-detail" />
						</label>
					</div>
					<!-- 详情页图片ed -->
				</div>
			</div>
		</div>
	</div>

	<!-- 产品编辑页 -->
	<div ng-show="editCProduct">
		<!-- 顶部按钮 -->
		<div class="titlebar" style="height: 20px;">
			<i ></i>
		</div>
		<!-- 顶部按钮 end -->
		<div class="sp-content-bg"
			style="background-color: white; width: 98%;margin-left: 15px;overflow-x: hidden;">
			<!--面板开始-->
			<div class="sp-panel panel panel-default">
				<div class="sp-panel-body panel-body category-name-head">
					<p class="sp-p-title">
						产品--> {{compositeProduct.product.productName}}<input type="button"
							class="sp-btn sp-btn-black-sm" value="预 览"
							onclick="showPopup('compositeProducteditpopUpHg',true)">
					</p>
				</div>
				<form name="compositeProductDetailEditForm"
					enctype="multipart/form-data">
					<!--面板内容开始-->
					<div class="sp-row-border row" style="padding: 5px 0px 18px 5px;">
						<!-- 产品属性  start-->
						<div class="titlebar sp-mune-title">
							<i class="pms-icon-kz"></i> 产品属性
						</div>
						<!-- 产品选择器 -->
						<div class="titlebar">
							<select ng-model="category1" class="sp-select sp-float-left"
								style="height: 30px;"
								ng-options="m.categoryName for m in firstLevelCategories"
								ng-change="onFirstLevelCategorychange(category1)">
								<option value="">一级品类名称</option>
							</select> <select ng-model="category2" class="sp-select sp-float-left"
								style="height: 30px;"
								ng-options="m.categoryName for m in secondLevelCategories"
								ng-change="onSecondLevelCategorychange(category2)">
								<option value="">二级品类名称</option>
							</select> <select ng-model="category3" class="sp-select sp-float-left"
								style="height: 30px;"
								ng-options="m.categoryName for m in thirdLevelCategories"
								ng-change="onThirdLevelCategorychange(category3)">
								<option value="">三级品类名称</option>
							</select> <input type="button" value="查 询"
								ng-click="onSearchButtonClick()" class="sp-btn sp-btn-green-md"
								style="margin-top: 0px; padding-top: 5px;">
						</div>

						<div class="titlebar"
							style="background-color: white; width: 100%; padding: 0px;">
							<!--产品搜索面板开始-->
							<div class="sp-panel panel panel-default">
								<!-- 面板内容start -->
								<div class="sp-row-border row sp-product-tb" style="overflow-x: hidden;">
									<!-- 表格开始 -->
									<table class="sp-table" style="TABLE-LAYOUT: fixed">
										<!-- 表格头部start -->
										<tr style="background-color: #EBEBEB; font-size: 12px;">
											<!--表头，这只排序字段，-->
											<th class="th-width-md">名称</th>
											<th class="th-width-md">产品规格</th>
											<th class="th-width-md"
												ng-repeat="property in productCategoryPropertiesByCategory"
												style="padding: 0;"><select class="sp-select"
												style="height: 30px; width: 90%;"
												ng-show="property.selectFlag == 1"
												ng-model="property.selectedValue"
												ng-options="x.categoryPropertyValue for x in property.productCategoryPropertyValues"
												ng-change="onProductPropertyChange($index,property.categoryPropertyName, property.selectedValue.categoryPropertyValue)"
												style="width: 100%; height: 100%; background-color: #EBEBEB;border: 0;">
													<option value="">{{property.categoryPropertyName}}</option>
											</select></th>
										</tr>
										<!-- 表格头部end -->
										<!-- 表格具体内容start -->
										<tr ng-repeat="product in satisfiedProducts"
											ng-click="onProductSelectedClick($index)"
											style="font-size: 10px; cursor: pointer;">
											<td style="WORD-WRAP: break-word">{{product.productName}}</td>
											<td style="WORD-WRAP: break-word">{{product.productSpecificationValue}}{{product.productSpecificationUnitFirst}}/{{product.productSpecificationUnitSecond}}</td>
											<td style="WORD-WRAP: break-word"
												ng-repeat="property in productCategoryPropertiesByCategory">
												<span ng-repeat="property1 in product.productProperties"
												ng-show="property.categoryPropertyName == property1.productPropertyName">
													{{property1.productPropertyValue}} </span>
											</td>
										</tr>
										<!-- 表格具体内容end -->
									</table>
									<!-- 表格结束 -->
								</div>
								<!-- 面板内容end -->
							</div>
							<!--面板结束-->
						</div>
						<!-- 表头品类及搜索结果部分 开始-->

						<!-- 表头品类及搜索结果部分 结束-->
						<!-- 已选择产品 -->
						<div class="titlebar sp-mune-title" style="margin-bottom: 6px;">
							<i class="pms-icon-kz"></i> 已选择产品
							<span style="color: red" ng-show="!haveprodect">至少选择一个产品！</span>
						</div>
						<div class="titlebar">
							<div ng-repeat="productItem in selectedProductItems"
								style="padding: 5px; min-height: 20px;">
								<span>{{$index + 1}}</span> <span>{{productItem.product.productName}}&nbsp;|&nbsp;{{productItem.product.productSpecificationValue}}{{productItem.product.productSpecificationUnitFirst}}/{{productItem.product.productSpecificationUnitSecond}}</span>
								<span ng-repeat="property in productItem.product.productProperties">&nbsp;|&nbsp;{{property.productPropertyValue}}</span>
								<span> <i class="pms-icon-jhh"
							ng-click="onProductQuantityMinus($index)"> <i
								name="productProperty[$index]"
								ng-model="productItem.productProperty[$index]"
								style="width: 100%; height: 100%;" class="fade"></i></i>
							{{productItem.quantity}} <i class="pms-icon-jh"
							ng-click="onProductQuantityAdd($index)"> <i
								ng-model="add[$index]" style="width: 100%; height: 100%;"
								class="fade"></i></i>
								</span>
							</div>
						</div>
						<input type="hidden" ng-model="compositeProduct.product.id" ng-show="false">
						<div class="titlebar sp-mune-title">
							<i class="pms-icon-kz"></i> 产品基本信息
						</div>
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span>产品名称：
							</div>
							<div style="padding-left: 0px;">
								<input type="text" class="sp-input sp-input-lg"
									name="productName"
									ng-model="compositeProduct.product.productName" required
									placeholder="产品名称" /> <span style="color: red"
									ng-show="compositeProductDetailEditForm.productName.$error.required && !compositeProductDetailEditForm.productName.$pristine">产品名称不能为空！</span>
							</div>
						</div>
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">产品GS1编码：</div>
							<div style="padding-left: 0px;">
								<input type="text" class="sp-input sp-input-lg"
									name="productGs1Code"
									ng-model="compositeProduct.product.productGs1Code"
									placeholder="产品GS1编码" ng-blur="checkproductGs1Code()" /> <span
									ng-show="!serverGs1Code" style="color: red">产品GS1编码不正确，请重新输入！</span>
								<span ng-show="!Gs1Exist" style="color: red">已有该产品GS1编码，请重新输入！</span>
							</div>
						</div>
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">是否可售：</div>
							<div style="padding-left: 0px;">
								<label class="radio-inline"> <input name="saleFlag"
									type="radio" value="1"
									ng-model="compositeProduct.product.saleFlag" ng-checked="1"
									required> 是
								</label> <label class="radio-inline"> <input name="saleFlag"
									type="radio" value="0"
									ng-model="compositeProduct.product.saleFlag"> 否
								</label>
							</div>
						</div>
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span>产品规格：
							</div>
							<div style="padding-left: 0px;">
								<input type="number" name="productSpecificationValue"
									step="0.01" class="sp-input-sm sp-input"
									ng-model="compositeProduct.product.productSpecificationValue"
									style="float: left;" required /> <span style="color: red"
									ng-show="compositeProduct.product.productSpecificationValue <= 0 || compositeProductDetailEditForm.productSpecificationValue.$error.required 
										|| compositeProductDetailEditForm.productSpecificationValue.$invalid">*产品的规格值大于0且不能为空！</span>
								<select style="float: left; height: 30px;"
									class="sp-select sp-select-sm"
									ng-change = "productSpecificationUnitFirstChange(compositeProduct.product.productSpecificationUnitFirst)"
									ng-model="compositeProduct.product.productSpecificationUnitFirst">
									<option ng-repeat="x in specificationFirstValues"
										value="{{x.optionName}}">{{x.optionName}}</option>
								</select> <span style="float: left;">/</span> <select
									style="float: left; height: 30px;"
									class="sp-select sp-select-sm"
									ng-change = "productSpecificationUnitSecondChange(compositeProduct.product.productSpecificationUnitSecond)"
									ng-model="compositeProduct.product.productSpecificationUnitSecond">
									<option ng-repeat="x in specificationSecondValues"
										value="{{x.optionName}}">{{x.optionName}}</option>
								</select>
							</div>
						</div>

						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">产品状态：</div>
							<div ng-repeat="x in productStatus"
								ng-if="compositeProduct.product.productStatus == x.optionValue"
								style="padding-left: 0px;">
								<div style="padding: 3px;">{{x.optionName}}</div>
							</div>
						</div>

					<div class="titlebar">
						<span class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>采购基本单位：
						</span>
						<span>
							<select ng-model="compositeProduct.product.productBuyUnitName" ng-disabled = "UnitDisable"
								class="sp-select sp-select-md" required name="productBuyUnit"
								style="height: 30px;" placeholder="采购基本单位">
								<option ng-repeat="x in BasicPurchaseUnit"
										value="{{x.optionName}}">{{x.optionName}}</option>
							</select><span style="color: red">当产品规格第一单位为非重量单位时，该选项等于产品规格第二单位并且不可选</span>
								 <span style="color: red"
								ng-show="productDetailEditForm.productBuyUnit.$error.required && !productDetailEditForm.productBuyUnit.$pristine">采购基本单位不能为空！</span>
						</span>
					</div>

						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">温区：</div>
							<div style="padding-left: 0px;">
								<div style="padding: 3px;">{{compositeProduct.product.productTemperatureZoneName}}</div>
							</div>
						</div>
						<!-- 产品属性  start-->
						<div class="titlebar sp-mune-title">
							<i class="pms-icon-kz"></i> 产品属性
						</div>
						<div class="titlebar">
							<div ng-repeat="categoryProperty in productCategoryProperties"
								style="float: left; padding-left: 15px; width: 45%;">
								<div class="valueName control-label"
									style="text-align: right; padding: 3px; width: 170px;">
									{{categoryProperty.categoryPropertyName}}：</div>
								<div style="width: 58.33333333%;float: left;">
									<div
										ng-repeat="productProperty in compositeProduct.product.productProperties"
										ng-if="productProperty.customerDefineFlag != 1 
											&& productProperty.productPropertyName == categoryProperty.categoryPropertyName">
										<select class="propertyV1 sp-select sp-select-lg"
												style="height: 30px;"
											id="{{categoryProperty.categoryPropertyName}}">
											<option
												ng-repeat="x in categoryProperty.productCategoryPropertyValues"
												value="{{x.categoryPropertyValue}}"
												ng-if="productProperty.productPropertyValue == x.categoryPropertyValue">
												{{x.categoryPropertyValue}}</option>
											<option
												ng-repeat="x in categoryProperty.productCategoryPropertyValues"
												value="{{x.categoryPropertyValue}}"
												ng-if="productProperty.productPropertyValue != x.categoryPropertyValue">
												{{x.categoryPropertyValue}}</option>
										</select>
									</div>
									<div ng-repeat="productProperty in otherProperty"
										ng-if="productProperty.categoryPropertyName == categoryProperty.categoryPropertyName">
										<select class="propertyV1 sp-select sp-select-lg"
												style="height: 30px;"
											id="{{categoryProperty.categoryPropertyName}}">
											<option
												ng-repeat="x in categoryProperty.productCategoryPropertyValues"
												value="{{x.categoryPropertyValue}}">
												{{x.categoryPropertyValue}}</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						
					<!-- 产品详细显示结束 -->
					<!-- 包装单位 start -->
					<div class="titlebar sp-mune-title"
						style="height: 35px; margin-bottom: 10px; margin-top: 10px;">
						<i class="pms-icon-kz"></i> 包装单位
						<span style="color: red" ng-show="showProductBuyUnitEmpty">请先选择采购基本单位！</span>
						<button class="sp-btn sp-btn-black-sm"
							ng-click="onAddProductPackUnitClick(compositeProduct.product.productBuyUnitName)" 
							class="icon-left-bt" 
							style="height: 100%;">新&nbsp;增</button>
					</div>
					<!-- 包装单位展示框  start-->
					<div style="margin-left: 120px; margin-right: 120px;">
						<table ng-show="productPackUnit.length > 0"
							class="sp-table"
							style="TABLE-LAYOUT: fixed; text-align: center; margin-bottom: 10px;">
							<tbody>
								<tr
									style="background-color: #EBEBEB; font-size: 12px; text-align: center;">
									<td class="th-width-md">包装单位</td>
									<td class="th-width-md last-td">操作</td>
								</tr>
								<!-- 显示新添加的自定义属性名和属性值 -->
								<tr style="height: 30px;"
									ng-repeat="packUnit in productPackUnit">
									<td class="text-left" style="text-align: center;">{{packUnit.unitValue}}
									{{compositeProduct.product.productBuyUnitName}}/{{packUnit.unitName}}</td>
									<td  class="last-td" style="text-align: center;"><a
										ng-click="onAddProductPackUnitClick(compositeProduct.product.productBuyUnitName,packUnit,$index)"
										href="#"
										style="cursor: pointer; color: #99C731; text-decoration: underline;">编辑</a>
										<a ng-click="onRemoveProductPackUnitClick($index)"
										href="#"
										style="cursor: pointer; color: #99C731; text-decoration: underline;">删除</a>
									</td>
								</tr>
								<!-- 显示新添加的自定义属性名和属性值  end-->
							</tbody>
						</table>
					</div>
					<!-- 包装单位展示框  end-->
					<!-- 包装单位  end  -->
						
						<!-- 图片模板开始 -->
						<jsp:include page="../common/picture.jsp"></jsp:include>
						<!-- 图片模板结束 -->
						<!-- 表单提交 start -->
						<div style="margin-bottom: 60px;">
							<button ng-click="onSaveClick()"
								class="sp-input-button btn btn-default"
								ng-disabled="compositeProductDetailEditForm.productName.$error.required ||compositeProduct.product.productSpecificationValue <= 0 ||
								 isProductCodeDuplicated || compositeProductDetailEditForm.productSpecificationValue.$invalid ||
								 compositeProductDetailEditForm.productSpecificationValue.$error.required || !isHeaderPictureSatified ||!isDetailPictureSatified || 
								 compositeProductDetailEditForm.productSpecificationUnitFirst.$error.required || 
								 compositeProductDetailEditForm.productSpecificationUnitSecond.$error.required ||
                                 isProductPropertyMissed || !haveprodect ||
                                 !serverGs1Code || !Gs1Exist || doubleClick"
								style="width: 100%;">完成</button>
						</div>
						<!-- 表单提交 end -->
					</div>
				</form>
			</div>
		</div>
	</div>

</div>
