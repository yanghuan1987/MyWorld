<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = application.getInitParameter("static-file");
%>
<div ng-cloak class="sp-menu-right sp-product-head" style="width: 80%;"
	ng-controller="compositeProductDetailAddController"
	ng-show="showPage == 6">

	<div id='compositenewpopUpHg' class='sp-popup sp-popup-hg'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<!-- <span>弹出框标题</span> -->
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('compositenewpopUpHg');"><img
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
						<label class="viewlabel text-left" style="width: 75%;">{{addedProduct.productName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品编码：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{addedProduct.productCode}}</label><br>
					</div>

					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品GS1编码：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{addedProduct.productGs1Code}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品规格：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{addedProduct.productSpecificationValue}}
							{{addedProduct.productSpecificationUnitFirst.optionName}} /
							{{addedProduct.productSpecificationUnitSecond.optionName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品状态：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{addedProduct.productStatus.optionName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">计量单位：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{addedProduct.productBuyUnit.optionName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品规格：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{addedProduct.shelfLife}}
							{{addedProduct.shelfLifeUnit.optionName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品规格：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{addedProduct.saleDate}}
							{{addedProduct.saleDateUnit.optionName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">温区：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{addedProduct.productTemperatureZone.optionName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">税率：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{addedProduct.taxRate.optionName}}</label><br>
					</div>
					<!-- 产品属性显示 -->
					<div
						ng-repeat="productCategoryProperty in addedProduct.productProperty"
						class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">{{productCategoryProperties[$index].categoryPropertyName}}：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{productCategoryProperty.categoryPropertyValue}}</label><br>
					</div>
					<div ng-repeat="property in customerProductProperties"
						class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">{{property.productPropertyName}}：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{property.productPropertyValue}}</label><br>
					</div>
					<div class="titlebar" ng-repeat="packUnit in productPackUnit">
						<label class="viewlabel text-right" style="width: 25%;" ng-show="$index==0">包装单位： </label> 
						<label class="viewlabel text-right" style="width: 25%;" ng-show="$index!=0">  </label> 
						<label 
							class="viewlabel text-left" style="width: 75%;">{{packUnit.unitValue}}{{addedProduct.productBuyUnit.optionName}}/{{packUnit.unitName}}</label><br>
					</div>
					<!-- 产品图片显示 -->
					<!-- 头部图片st -->
					<div class="titlebar col-xs-12 col-sm-12 sp-mune-title">
						<i class="pms-icon-kz"></i> 头部图片
					</div>
					<!--  -->
					<div>
						<br>
						<!-- 采用angular循环的方式，对存入productPictures的图片进行展示 -->
						<label ng-repeat="picture in thumbHeader"
							style="width: 15%; margin-left: 1.5%; margin-top: 3%;display: inline-block;"> <img
							ng-src="{{picture.pictureAddress}}" class="sp-picture-title" /></label>
						<br>
					</div>
					<!-- 头部图片ed -->
					<br>
					<!-- 详情页图片st -->
					<div class="titlebar col-xs-12 col-sm-12 sp-mune-title">
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

	<!-- 顶部按钮 -->
	<div class="titlebar" style="height: 20px;">
		<i ></i>
	</div>
	<!-- 顶部按钮 end -->

	<div ng-cloak class="sp-content-bg"
		style="background-color: white; width: 98%;margin-left: 15px;overflow-x: hidden;">
		<!--面板开始-->
		<div class="sp-panel panel panel-default">
			<div class="sp-panel-body panel-body category-name-head">
				<p class="sp-p-title">
					新增--> <input type="button" class="sp-btn sp-btn-black-sm"
						value="预 览" onclick="showPopup('compositenewpopUpHg',true)">
						<button type="button" ng-click="returnPage('add')"
							class="sp-btn sp-btn-black-sm">返回</button>
				</p>
			</div>
			<form name="compositeProductDetailAddForm"
				id="compositeProductDetailAddForm" enctype="multipart/form-data">
				<!--面板内容开始-->
				<div class="sp-row-border" style="padding: 5px 0px 18px 5px;">
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品属性
					</div>
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
						</select> <input type="button" value="查 询" ng-click="onSearchButtonClick()"
							class="sp-btn sp-btn-green-md"
							style="margin-top: 0px; padding-top: 5px;">
					</div>
					<div class="titlebar"
						style="background-color: white; width: 100%; padding: 0px;">
						<!--面板开始-->
						<div class="sp-panel panel panel-default">
							<!-- 面板内容start -->
							<div class="sp-row-border row sp-product-tb" style="overflow-x: hidden;">
								<!-- 表格开始 -->
								<table class="sp-table" style="TABLE-LAYOUT: fixed";">
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
											ng-if="property.categoryPropertyName == property1.productPropertyName">
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

					<!-- 表头品类及搜索结果部分 结束-->
					<div class="titlebar sp-mune-title" style="margin-bottom: 6px;">
						<i class="pms-icon-kz"></i> 已选择产品
							<span style="color: red" ng-show="!haveprodect">至少选择一个产品！</span>
					</div>
					<div ng-repeat="productItem in selectedProductItems"
						style="padding: 5px; min-height: 20px;">
						<span>{{$index + 1}}</span> <span>{{productItem.product.productName}}&nbsp;|&nbsp;{{productItem.product.productSpecificationValue}}{{productItem.product.productSpecificationUnitFirst}}/{{productItem.product.productSpecificationUnitSecond}}</span>
						<span
							ng-repeat="property in productItem.product.productProperties">&nbsp;|&nbsp;{{property.productPropertyValue}}</span>
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
					<!-- 表头品类及搜索结果部分 结束-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品基本信息
					</div>
					<!-- 产品详细显示开始 -->
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>产品名称：
						</div>
						<div style="float: left;">
							<input type="text" class="sp-input sp-input-lg"
								name="productName" ng-model="addedProduct.productName"
								placeholder="产品名称" required /> <span style="color: red"
								ng-show="compositeProductDetailAddForm.productName.$error.required && !compositeProductDetailAddForm.productName.$pristine">产品名称不能为空！</span>
						</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品GS1编码：</div>
						<div style="float: left;">
							<input type="text" class="sp-input sp-input-lg"
								name="productGs1Code" ng-model="addedProduct.productGs1Code"
								placeholder="产品GS1编码" ng-blur="checkproductGs1Code()" /> <span
								ng-show="!serverGs1Code" style="color: red">产品GS1编码不正确，请重新输入！</span>
							<span ng-show="!Gs1Exist" style="color: red">已有该产品GS1编码，请重新输入！</span>
						</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>是否可售：
						</div>
						<div style="float: left;">
							<label class="radio-inline"> <input name="saleFlag"
								type="radio" value="1" ng-model="addedProduct.saleFlag"
								ng-checked="1" required> 是
							</label> <label class="radio-inline"> <input name="saleFlag"
								type="radio" value="0" ng-model="addedProduct.saleFlag">否
							</label>
						</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>产品规格：
						</div>
						<div style="float: left;">
							<input type="number" ng-pattern="/^\d+$/" step="1"
								class="sp-input-sm sp-input" name="productSpecificationValue"
								style="float: left;" placeholder="规格值"
								ng-model="addedProduct.productSpecificationValue" required />
								<span style="color: red"
								ng-show="(compositeProductDetailAddForm.productSpecificationValue.$error.required && !compositeProductDetailAddForm.productSpecificationValue.$pristine)">产品的规格值不能为空！</span>
								<span style="color: red"
								ng-show="(compositeProductDetailAddForm.productSpecificationValue.$error.number && !compositeProductDetailAddForm.productSpecificationValue.$pristine)">产品的规格值必须为数字！</span>
								<span style="color: red"
								ng-show="(compositeProductDetailAddForm.productSpecificationValue.$error.pattern && !compositeProductDetailAddForm.productSpecificationValue.$pristine)">产品的规格值必须为正整数！</span>
							<select class="sp-select sp-select-sm"
								style="float: left; height: 30px;"
								ng-model="addedProduct.productSpecificationUnitFirst" required
								name="productSpecificationUnitFirst" ng-change = "productSpecificationUnitFirstChange(addedProduct.productSpecificationUnitFirst)"
								ng-options="x.optionName for x in specificationFirstValues">
							</select><span style="float: left;">/</span> <select
								class="sp-select sp-select-sm"
								style="float: left; height: 30px;"  ng-change = "productSpecificationUnitSecondChange(addedProduct.productSpecificationUnitSecond)"
								ng-model="addedProduct.productSpecificationUnitSecond" required
								name="productSpecificationUnitSecond"
								ng-options="x.optionName for x in specificationSecondValues">
							</select> <span style="color: red"
								ng-show="compositeProductDetailAddForm.productSpecificationUnitFirst.$error.required && !compositeProductDetailAddForm.productSpecificationUnitFirst.$pristine || 
										         compositeProductDetailAddForm.productSpecificationUnitSecond.$error.required && !compositeProductDetailAddForm.productSpecificationUnitSecond.$pristine">产品的规格单位不能为空！</span>
						</div>
						<div class="titlebar form-group" style="color: red;margin-left:10%;width:89%;">注:产品本身的属性之一，不是一种单位，不用于采购和加工的计量。组成形式：产品数值+计量单位/最小销售单位(此单位对于工业制品而言与最小采购单位一致)</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>产品状态：
						</div>
						<div style="float: left;">
							<select ng-model="addedProduct.productStatus"
								class="sp-select sp-select-md" required name="productStatus"
								style="height: 30px;"
								ng-options="x.optionName for x in productStatus"
								style="width: 100px;" placeholder="产品状态">
							</select> <span style="color: red"
								ng-show="compositeProductDetailAddForm.productStatus.$error.required && !compositeProductDetailAddForm.productStatus.$pristine">产品状态不能为空！</span>
						</div>
					</div>
					
					
					<div class="titlebar">
						<span class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>计量单位：
						</span>
						<span style="float: left;">
							<select ng-model="addedProduct.productBuyUnit" 
								class="sp-select sp-select-md" required name="productBuyUnit"
								style="height: 30px;"  ng-disabled = "UnitDisable"
								ng-options="x.optionName for x in BasicPurchaseUnit"
								style="width: 100px;" placeholder="计量单位">
							</select><span style="color: red">当产品规格第一单位为非重量单位时，该选项等于产品规格第二单位并且不可选</span>
							 <span style="color: red"
								ng-show="compositeProductDetailAddForm.productBuyUnit.$error.required && !compositeProductDetailAddForm.productBuyUnit.$pristine">计量单位不能为空！</span>
						</span>
						<div class="titlebar form-group" style="width:89%;color: red;margin-left:10%">注:作用于采购管理系统，用于计量不可分割的最小基本采购单元。请注意谨慎填写，一般情况下对于工业制品就是规格的后面一个单位(最小销售单位)；对与非工业产品一般情况下是以重量单位(电商平台大部分非工业品是无法以重量单位进行售卖的，产品标准化的一种体现，故与最小销售单位不能保持一致)进行采购，如斤、公斤等单位，在我公司平台中常应用于果蔬类和肉类。</div>
					</div>
					
					

					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							保质期：
						</div>
						<div style="float: left;">
							<input type="text" class="sp-input-sm sp-input"
								name="shelfLife" step="1"
								ng-model="addedProduct.shelfLife" ng-blur="checkshelfLife(addedProduct.shelfLife)"
								style="float: left; width: 80px;" placeholder="保质期" />
								<span style="color: red"
								ng-show="checkShelfLifeFromat">保质期必须为正整数！</span>
							<select ng-model="addedProduct.shelfLifeUnit"
								class="sp-select sp-select-sm" 
								style="float: left; height: 30px;" required
								name="shelfLifeUnit" ng-change = "shelfLifeUnitChange(addedProduct.shelfLifeUnit)"
								ng-options="x.optionName for x in dateUnits"
								style="width: 100px;" placeholder="时间单位">
							</select><span style="float: left;"><span style="color: red"
								ng-show="ShelfLifeUnitIsCheck && compositeProductDetailAddForm.shelfLifeUnit.$pristine">保质期有值时单位不能为空！</span>
								</span>
						</div>
						<div class="titlebar form-group" style="color: red;margin-left:10%;width:89%;">注:非必填项,保质期未输入的场合改单位不会被保存，但可正常操作！</div>
					</div>

					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							货架期：
						</div>
						<div style="float: left;">
							<input type="text" class="sp-input-sm sp-input"
								name="saleDate" step="1"
								ng-model="addedProduct.saleDate" ng-blur="checksaleDate(addedProduct.saleDate)"
								style="float: left; width: 80px;" placeholder="货架期" />
								<span style="color: red"
								ng-show="checkSaleDateFromat">货架期必须为正整数！</span>
							<select ng-model="addedProduct.saleDateUnit"
								class="sp-select sp-select-sm"
								style="float: left; height: 30px;" required
								name="saleDateUnit" ng-change = "saleDateUnitChange(addedProduct.saleDateUnit)"
								ng-options="x.optionName for x in dateUnits"
								style="width: 100px;" placeholder="时间单位">
							</select><span style="float: left;"><span style="color: red"
								ng-show="SaleDateUnitIsCheck && compositeProductDetailAddForm.saleDateUnit.$pristine">货架期有值时单位不能为空！</span>
						</div>
						<div class="titlebar form-group" style="color: red;margin-left:10%;width:89%;">注:非必填项,货架期未输入的场合改单位不会被保存，但可正常操作！</div>
					</div>
					
					
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>温区：
						</div>
						<div style="float: left;">
							<select ng-model="addedProduct.productTemperatureZone"
								class="sp-select sp-select-md" required name="productTemperatureZone"
								style="height: 30px;"
								ng-options="x.optionName for x in severProductTemperatureZone"
								style="width: 100px;" placeholder="温区">
							</select> <span style="color: red"
								ng-show=" (!(addedProduct.productTemperatureZone.optionValue >= 0) && addedProduct.productTemperatureZone.optionValue != null) ||
								(compositeProductDetailAddForm.productTemperatureZone.$error.required && !compositeProductDetailAddForm.productTemperatureZone.$pristine)">温区不能为空！</span>
						</div>
					</div>
					
					
					
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>税率：
						</div>
						<div style="float: left;">
							<select ng-model="addedProduct.taxRate"
								class="sp-select sp-select-md" required name="taxRate"
								style="height: 30px;"
								ng-options="x.optionName for x in severTaxRate"
								style="width: 100px;" placeholder="税率">
							</select> <span style="color: red"
								ng-show="(compositeProductDetailAddForm.taxRate.$error.required && !compositeProductDetailAddForm.taxRate.$pristine)">税率不能为空！</span>
						</div>
					</div>
					<!-- 产品详细显示结束 -->
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品属性
					</div>
					<!-- 产品详细显示开始 -->
					<div class="titlebar">
						<div
							ng-repeat="productCategoryProperty in productCategoryProperties"
							style="float: left; padding-left: 15px; width: 45%;">
							<div class="valueName control-label"
								style="text-align: right; padding: 3px; width: 170px;">
								<span style="color: red">*</span>{{productCategoryProperty.categoryPropertyName}}：
							</div>
							<div style="width: 58.33333333%;float: left;">
								<select ng-model="addedProduct.productProperty[$index]" required
									name="productProperty{{$index}}" class="sp-select sp-select-lg"
									style="height: 30px;"
									ng-options="x.categoryPropertyValue for x in productCategoryProperty.productCategoryPropertyValues"
									ng-change="onPropertyValueChange(addedProduct.productProperty)">
								</select> <span style="color: red"
									ng-show="compositeProductDetailAddForm.productProperty{{$index}}.$error.required && !compositeProductDetailAddForm.productProperty{{$index}}.$pristine">产品的属性{{productCategoryProperty.categoryPropertyName}}不能为空！</span>
							</div>
						</div>
					</div>
					
					<!-- 产品详细显示结束 -->
					<!-- 包装单位 start -->
					<div class="titlebar sp-mune-title"
						style="height: 35px; margin-bottom: 10px; margin-top: 10px;">
						<i class="pms-icon-kz"></i> 包装单位
						<span style="color: red" ng-show="showProductBuyUnitEmpty">请先选择计量单位！</span>
						<button class="sp-btn sp-btn-black-sm"
							ng-click="onAddProductPackUnitClick(addedProduct.productBuyUnit)" 
							class="icon-left-bt" 
							style="height: 100%;">新&nbsp;增</button>
					</div>
					<div class="titlebar sp-mune-title" style="background-color: white;color: red;height:auto;">注:原料加工&采购计算公式：
							 1)工业制品：加工领料出库(采购原料入库)数量 == 需要采购的份数(系统目前无法和包装单位进行转换)
							 2)非工业制品：加工领料出库(采购原料入库)数量 == 需要加工的份数*商品基数*产品规格的计量单位(系统会转换成更大的重量计算单位，便于查看)。
							成品的出入库仅仅以份为计算单位不牵涉到计算和转换，在此不做赘述。
					</div>
					<!-- 包装单位展示框  start-->
					<div style="margin-left: 120px; margin-right: 120px;height:auto;">
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
									{{addedProduct.productBuyUnit.optionName}}/{{packUnit.unitName}}</td>
									<td  class="last-td" style="text-align: center;"><a
										ng-click="onAddProductPackUnitClick(productBuyUnit,packUnit,$index)"
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
						<button ng-click="onAddSaveClick()"
							class="sp-input-button btn btn-default"
							ng-disabled="compositeProductDetailAddForm.productName.$error.required ||!(addedProduct.productTemperatureZone.optionValue >= 0) || compositeProductDetailAddForm.taxRate.$error.required ||
								 isProductCodeDuplicated ||addedProduct.productSpecificationValue<=0 || compositeProductDetailAddForm.productBuyUnit.$error.required ||
								 compositeProductDetailAddForm.productSpecificationValue.$error.required || !isHeaderPictureSatified ||!isDetailPictureSatified || 
								 compositeProductDetailAddForm.productSpecificationUnitFirst.$error.required || compositeProductDetailAddForm.productSpecificationValue.$error.number ||
								 compositeProductDetailAddForm.productSpecificationUnitSecond.$error.required || compositeProductDetailAddForm.productSpecificationValue.$error.pattern ||
                                 isProductPropertyMissed || !haveprodect || compositeProductDetailAddForm.productStatus.$error.required ||
                                 ShelfLifeUnitIsCheck && compositeProductDetailAddForm.shelfLifeUnit.$pristine || checkShelfLifeFromat ||
                                 SaleDateUnitIsCheck && compositeProductDetailAddForm.saleDateUnit.$pristine || checkSaleDateFromat ||
                                 !serverGs1Code || !Gs1Exist || doubleClick"
							style="width: 100%;">完成</button>
					</div>
					<!-- 表单提交 end -->
				</div>
			</form>
		</div>
	</div>
</div>
