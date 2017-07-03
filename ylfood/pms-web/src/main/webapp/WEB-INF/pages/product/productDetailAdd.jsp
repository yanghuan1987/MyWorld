<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<div ng-cloak class="sp-menu-right sp-product-head" style="border-left: 5px solid #ebebeb;width: 80%;"
	ng-controller="productDetailAddController" ng-show="showPage == 4">
	<jsp:include page="../common/productHead.jsp"></jsp:include>
	<div id='newpopUpHg' class='sp-popup sp-popup-hg'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<!-- <span>弹出框标题</span> -->
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('newpopUpHg');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content'>
			<div id="content" class="sp-content" style="border: 0;overflow-y: auto;overflow-x: hidden;height: 600px;">
				<div class="sp-page-title" style="margin-left: 0;">
					<div style="text-align: center; width: 100%;">产品预览</div>
				</div>
				<div class="sp-content-layout">
						<!-- 产品属性  start-->
						<div class="titlebar sp-mune-title">
							<i class="pms-icon-kz"></i> 产品属性
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">产品名称：</label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productName}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">产品编码：</label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productCode}}</label><br>
						</div>

						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">产品GS1编码：</label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productGs1Code}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">产品规格： </label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productSpecificationValue}}
								{{addedProduct.productSpecificationUnitFirst.optionName}} /
								{{addedProduct.productSpecificationUnitSecond.optionName}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">产品状态： </label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productStatus.optionName}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">采购基本单位： </label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productBuyUnit.optionName}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">保质期： </label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.shelfLife}}
								{{addedProduct.shelfLifeUnit.optionName}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">温区： </label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productTemperatureZone.optionName}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">税率： </label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.taxRate.optionName}}</label><br>
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
						<div class="titlebar sp-mune-title">
							<i class="pms-icon-kz"></i> 头部图片
						</div>
						<!--  -->
						<div>
							<br>
							<!-- 采用angular循环的方式，对存入productPictures的图片进行展示 -->
							<label ng-repeat="picture in thumbHeader"
								style="width: 15%; margin-left: 1.5%; margin-top: 3%;display: inline-block;">
								<img ng-src="{{picture.pictureAddress}}"
								class="sp-picture-title" />
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

	<div ng-cloak class="sp-content-bg"
		style="background-color: white; width: 100%;overflow-x: hidden;">
		<!--面板开始-->
		<div class="sp-panel panel panel-default">
			<div class="sp-panel-body panel-body category-name-head">
				<p class="sp-p-title">
					新增-->
					<input type="button" class="sp-btn sp-btn-black-sm" value="预 览" onclick="showPopup('newpopUpHg',true)">
						<button type="button" ng-click="returnPage('add')"
							class="sp-btn sp-btn-black-sm">返回</button>
				</p>
			</div>
			<form name="productDetailAddForm" id="productDetailAddForm"
				enctype="multipart/form-data">
				<!--面板内容开始-->
				<div class="sp-row-border">
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品基本信息
					</div>
					<!-- 产品详细显示开始 -->
					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span> 产品名称：
						</div>
						<div style="float: left;">
							<input type="text" name="productName"
								class="sp-input sp-input-lg" ng-model="addedProduct.productName"
								required placeholder="产品名称" /> <span style="color: red"
								ng-show="productDetailAddForm.productName.$error.required && !productDetailAddForm.productName.$pristine">产品名称不能为空！</span>
						</div>
					</div>
					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品GS1编码：</div>
						<div style="float: left;">
							<input type="text" class="sp-input sp-input-lg"
								name="productGs1Code" ng-blur="checkproductGs1Code()"
								ng-model="addedProduct.productGs1Code" placeholder="产品GS1编码" />
							<span ng-show="!serverGs1Code" style="color: red">产品GS1编码不正确，请重新输入！</span>
							<span ng-show="!Gs1Exist" style="color: red">已有该产品GS1编码，请重新输入！</span>
						</div>
					</div>
					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>是否可售：
						</div>
						<label class="radio-inline"> <input name="saleFlag"
							type="radio" value="1" ng-model="addedProduct.saleFlag"
							ng-checked="1" required> 是
						</label> <label class="radio-inline"> <input name="saleFlag"
							type="radio" value="0" ng-model="addedProduct.saleFlag">
							否
						</label>
					</div>
					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>产品规格：
						</div>
						<div style="float: left;">
							<input type="number" class="sp-input-sm sp-input"
								name="productSpecificationValue" step="1" ng-pattern="/^\d+$/"
								ng-model="addedProduct.productSpecificationValue" required
								style="float: left; width: 80px;" placeholder="规格值" />
								<span style="color: red"
								ng-show="productDetailAddForm.productSpecificationValue.$error.required && !productDetailAddForm.productSpecificationValue.$pristine">产品的规格值不能为空！</span>
								<span style="color: red"
								ng-show="productDetailAddForm.productSpecificationValue.$error.number && !productDetailAddForm.productSpecificationValue.$pristine">产品的规格值必须为数字！</span>
								<span style="color: red"
								ng-show="productDetailAddForm.productSpecificationValue.$error.pattern && !productDetailAddForm.productSpecificationValue.$pristine">产品的规格值必须为正整数！</span>
							<select ng-model="addedProduct.productSpecificationUnitFirst"
								class="sp-select sp-select-sm"
								style="float: left; height: 30px;" required
								name="productSpecificationUnitFirst" ng-change = "productSpecificationUnitFirstChange(addedProduct.productSpecificationUnitFirst)"
								ng-options="x.optionName for x in specificationFirstValues"
								style="width: 100px;" placeholder="计量单位">
							</select><span style="float: left;">/</span><select
								ng-model="addedProduct.productSpecificationUnitSecond"
								class="sp-select sp-select-sm" required
								name="productSpecificationUnitSecond"
								style="float: left; height: 30px;" ng-change = "productSpecificationUnitSecondChange(addedProduct.productSpecificationUnitSecond)"
								ng-options="x.optionName for x in specificationSecondValues"
								style="width: 100px;" placeholder="包装单位">
							</select> <span style="color: red"
								ng-show="productDetailAddForm.productSpecificationUnitFirst.$error.required && !productDetailAddForm.productSpecificationUnitFirst.$pristine || 
										         productDetailAddForm.productSpecificationUnitSecond.$error.required && !productDetailAddForm.productSpecificationUnitSecond.$pristine">产品的规格单位不能为空！</span>
						</div>
						<div class="titlebar form-group" style="color: red;margin-left:10%;width:89%;">注:产品本身的属性之一，不是一种单位，不用于采购和加工的计量。组成形式：产品数值+计量单位/最小销售单位(此单位对于工业制品而言与最小采购单位一致)</div>
					</div>
					
					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>产品状态：
						</div>
						<div style="float: left;">
							<select ng-model="addedProduct.productStatus"
								class="sp-select sp-select-md" required name="productStatus"
								ng-options="x.optionName for x in productStatus"
								style="height: 30px;" placeholder="产品状态">
							</select> <span style="color: red"
								ng-show="productDetailAddForm.productStatus.$error.required && !productDetailAddForm.productStatus.$pristine">产品状态不能为空！</span>
						</div>
					</div>


					<div class="titlebar form-group">
						<span class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>采购基本单位：
						</span>
						<span>
							<select ng-model="addedProduct.productBuyUnit" ng-disabled = "UnitDisable"
								class="sp-select sp-select-md" required name="productBuyUnit"
								ng-options="x.optionName for x in BasicPurchaseUnit"
								style="height: 30px;" placeholder="采购基本单位">
							</select><span style="color: red">当产品规格第一单位为非重量单位时，该选项等于产品规格第二单位并且不可选</span>
								 <span style="color: red"
								ng-show="productDetailAddForm.productBuyUnit.$error.required && !productDetailAddForm.productBuyUnit.$pristine">采购基本单位不能为空！</span>
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
								ng-show="ShelfLifeUnitIsCheck && productDetailAddForm.shelfLifeUnit.$pristine">保质期有值时单位不能为空！</span>
								</span>
						</div>
						<div class="titlebar form-group" style="color: red;margin-left:10%;width:89%;">注:非必填项,保质期未输入的场合改单位不会被保存，但可正常操作！</div>
					</div>

					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>温区：
						</div>
						<div style="float: left;">
							<select ng-model="addedProduct.productTemperatureZone"
								class="sp-select sp-select-md" required name="productTemperatureZone"
								ng-options="x.optionName for x in severProductTemperatureZone"
								style="height: 30px;" placeholder="温区">
							</select> <span style="color: red"
								ng-show="(!(addedProduct.productTemperatureZone.optionValue >= 0) && addedProduct.productTemperatureZone.optionValue != null) ||
								(productDetailAddForm.productTemperatureZone.$error.required && !productDetailAddForm.productTemperatureZone.$pristine)">温区不能为空！</span>
						</div>
					</div>


					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>税率：
						</div>
						<div style="float: left;">
							<select ng-model="addedProduct.taxRate"
								class="sp-select sp-select-md" required name="taxRate"
								ng-options="x.optionName for x in severTaxRate"
								style="height: 30px;" placeholder="温区">
							</select> <span style="color: red"
								ng-show="(productDetailAddForm.taxRate.$error.required && !productDetailAddForm.taxRate.$pristine)">税率不能为空！</span>
						</div>
					</div>


					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品属性
					</div>
					<!-- 产品详细显示开始 -->
					<div class="titlebar">
						<div
							ng-repeat="productCategoryProperty in productCategoryProperties"
							style="padding-left: 15px; width: 45%;float: left;">
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
									ng-show="productDetailAddForm.productProperty{{$index}}.$error.required && !productDetailAddForm.productProperty{{$index}}.$pristine">产品的属性{{productCategoryProperty.categoryPropertyName}}不能为空！</span>
							</div>
						</div>
					</div>

					<!-- 产品详细显示结束 -->
					<!-- 自定义属性 start -->
					<div class="titlebar sp-mune-title"
						style="height: 35px; margin-bottom: 10px; margin-top: 10px;">
						<i class="pms-icon-kz"></i> 产品自定义属性
						<button class="sp-btn sp-btn-black-sm"
							ng-click="onAddCustomerPropertyClick()" 
							class="icon-left-bt" 
							style="height: 100%;">新&nbsp;增</button>
					</div>
					<!-- 自定义属性展示框  start-->
					<div style="margin-left: 120px; margin-right: 120px;">
						<table ng-show="customerProductProperties.length > 0"
							class="sp-table"
							style="TABLE-LAYOUT: fixed; text-align: center; margin-bottom: 10px;">
							<tbody>
								<tr
									style="background-color: #EBEBEB; font-size: 12px; text-align: center;">
									<td class="th-width-md">属性名称</td>
									<td class="th-width-md">属性值</td>
									<td class="th-width-md last-td">操作</td>
								</tr>
								<!-- 显示新添加的自定义属性名和属性值 -->
								<tr style="height: 30px;"
									ng-repeat="property in customerProductProperties">
									<td class="text-left" style="text-align: center;">{{property.productPropertyName}}</td>
									<td class="text-left" style="text-align: center;">{{property.productPropertyValue}}</td>
									<td  class="last-td" style="text-align: center;"><a
										ng-click="onAddCustomerPropertyClick(property,$index)"
										href="#"
										style="cursor: pointer; color: #99C731; text-decoration: underline;">编辑</a>
										<a ng-click="onRemoveCustomerPropertyItemClick($index)"
										href="#"
										style="cursor: pointer; color: #99C731; text-decoration: underline;">删除</a>
									</td>
								</tr>
								<!-- 显示新添加的自定义属性名和属性值  end-->
							</tbody>
						</table>
					</div>
					<!-- 自定义属性展示框  end-->
					<!-- 自定义属性  end  -->
					
					<!-- 产品详细显示结束 -->
					<!-- 包装单位 start -->
					<div class="titlebar sp-mune-title"
						style="height: 35px; margin-bottom: 10px; margin-top: 10px;">
						<i class="pms-icon-kz"></i> 包装单位
						<span style="color: red" ng-show="showProductBuyUnitEmpty">请先选择采购基本单位！</span>
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
										ng-click="onAddProductPackUnitClick(addedProduct.productBuyUnit,packUnit,$index)"
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
							ng-disabled="productDetailAddForm.productName.$error.required ||addedProduct.productSpecificationValue <= 0 ||productDetailAddForm.taxRate.$error.required ||
								 productDetailAddForm.productSpecificationValue.$error.required || !(addedProduct.productTemperatureZone.optionValue >= 0) ||
								 productDetailAddForm.productSpecificationUnitFirst.$error.required ||  !isHeaderPictureSatified ||!isDetailPictureSatified || 
								 productDetailAddForm.productSpecificationUnitSecond.$error.required || productDetailAddForm.productBuyUnit.$error.required ||
                                 isProductPropertyMissed  || productDetailAddForm.productStatus.$error.required|| productDetailAddForm.productSpecificationValue.$error.number||
                                 productDetailAddForm.productSpecificationValue.$error.pattern ||
                                 ShelfLifeUnitIsCheck && productDetailAddForm.shelfLifeUnit.$pristine || checkShelfLifeFromat ||
                                 !serverGs1Code || !Gs1Exist|| doubleClick"
							style="width: 100%;">完成</button>
					</div>
					<!-- 表单提交 end -->

				</div>
			</form>
		</div>

	</div>


</div>
