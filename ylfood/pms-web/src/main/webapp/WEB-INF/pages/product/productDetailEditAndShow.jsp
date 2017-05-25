<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = application.getInitParameter("static-file");
%>
<div ng-cloak class="sp-menu-right sp-product-head"
	ng-controller="productDetailController" ng-show="showP">

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
					<div style="text-align: center; width: 100%;">产品预览</div>
				</div>
				<div class="sp-content-layout">
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品属性
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品名称：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{product.productName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品编码：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{product.productCode}}</label><br>
					</div>

					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品GS1编码：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{product.productGs1Code}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品规格：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{product.productSpecificationValue}}
							{{product.productSpecificationUnitFirst}} /
							{{product.productSpecificationUnitSecond}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">产品状态：
						</label> <label class="viewlabel text-left" style="width: 75%;"ng-repeat="x in productStatus"
							ng-if="product.productStatus == x.optionValue">
							{{x.optionName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">采购基本单位：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{product.productBuyUnitName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">温区：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{product.productTemperatureZoneName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">税率：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{product.taxRateName}}</label><br>
					</div>
					<!-- 产品属性显示 -->
					<div ng-repeat="productProperty in product.productProperties"
						class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">{{productProperty.productPropertyName}}：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{productProperty.productPropertyValue}}</label><br>
					</div>
					<!-- 产品图片显示 -->
					<div class="titlebar" ng-repeat="packUnit in productPackUnit">
						<label class="viewlabel text-right" style="width: 25%;" ng-show="$index==0">包装单位： </label> 
						<label class="viewlabel text-right" style="width: 25%;" ng-show="$index!=0">  </label> 
						<label 
							class="viewlabel text-left" style="width: 75%;">{{packUnit.unitValue}}{{product.productBuyUnitName}}/{{packUnit.unitName}}</label><br>
					</div>
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
	<div ng-show="!editProduct">
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
						<span>产品-->{{product.productName}}</span>
						<button type="button" ng-click="onEditClick()"
							class="sp-btn sp-btn-gray-md" style="margin-top: -8px;">编辑</button>
					</p>
				</div>
				<!--面板内容开始-->
				<div class="sp-row-border row" style="padding: 5px 0px 18px 5px;">
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品基本信息
					</div>
					<!-- 产品详细显示开始 -->
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品名称：</div>
						<div class="valueName" style="padding-left: 0px;">{{product.productName}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品编码：</div>
						<div class="valueName" style="padding-left: 0px;">{{product.productCode}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品GS1编码：</div>
						<div class="valueName" style="padding-left: 0px;">{{product.productGs1Code}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">可售：</div>
						<div class="valueName" style="padding-left: 0px;">
							<span ng-if="product.saleFlag==1">是</span> <span
								ng-if="product.saleFlag==0">否</span>
						</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品规格：</div>
						<div class="valueName" style="padding-left: 0px;">
							{{product.productSpecificationValue}}
							{{product.productSpecificationUnitFirst}} /
							{{product.productSpecificationUnitSecond}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">产品状态：</div>
						<div class="valueName" style="padding-left: 0px;"
							ng-repeat="x in productStatus"
							ng-if="product.productStatus == x.optionValue">
							{{x.optionName}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">采购基本单位：</div>
						<div class="valueName" style="padding-left: 0px;">
							{{product.productBuyUnitName}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">温区：</div>
						<div class="valueName" style="padding-left: 0px;">
							{{product.productTemperatureZoneName}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">税率：</div>
						<div class="valueName" style="padding-left: 0px;">
							{{product.taxRateName}}</div>
					</div>
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品属性
					</div>
					<div class="titlebar">
						<div class="viewproper"
							ng-repeat="property in product.productProperties">
							<div class="control-label"
								style="text-align: right; padding: 3px; width: 170px; float: left;">
								{{property.productPropertyName}}：</div>
							<div style="padding: 3px; float: left; width: 58.33333333%;">
								{{property.productPropertyValue}}</div>
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
								{{packUnit.unitValue}}{{product.productBuyUnitName}}/{{packUnit.unitName}}</div>
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
					<div>
						<br>
						<!-- 采用angular循环的方式，对存入productPictures的图片进行展示 -->
						<label ng-repeat="picture in product.productPictures"
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
						<label ng-repeat="picture in product.productPictures"
							style="width: 100%; text-align: center;"> <img
							ng-show="picture.pictureShowPosition == 1"
							ng-src="{{picture.pictureAddress}}" class="sp-picture-detail" />
						</label>
					</div>
					<!-- 详情页图片ed -->
				</div>
			</div>
			<!-- 面板结束 -->
		</div>
	</div>



	<!-- 产品编辑页 -->
	<div ng-show="editProduct">
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
						产品-->{{product.productName}}</span> <input type="button"
							class="sp-btn sp-btn-black-sm" value="预 览"
							onclick="showPopup('editpopUpHg',true)">
					</p>
				</div>
				<!--面板内容开始-->
				<div class="sp-row-border row" style="padding: 5px 0px 18px 5px;">
					<!-- 产品属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 产品基本信息
					</div>
					<form name="productDetailEditForm" enctype="multipart/form-data">
						<!-- 产品详细显示开始 -->
						<!-- 产品详细显示开始 -->
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span> 产品名称：
							</div>
							<div class="valueName" style="padding-left: 0px;">
								<input type="text" name="productName"
									class="sp-input sp-input-lg" ng-model="product.productName"
									required placeholder="产品名称" /> <span style="color: red"
									ng-show="productDetailEditForm.productName.$error.required && !productDetailEditForm.productName.$pristine">产品名称不能为空！</span>
							</div>
						</div>
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">产品GS1编码：</div>
							<div class="valueName" style="padding-left: 0px;">
								<input type="text" name="productGs1Code"
									ng-model="product.productGs1Code" class="sp-input sp-input-lg"
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
							<div class="valueName" style="padding-left: 0px;">
								<label class="radio-inline"> <input name="saleFlag"
									type="radio" value="1" ng-model="product.saleFlag"
									ng-checked="1" required> 是
								</label> <label class="radio-inline"> <input name="saleFlag"
									type="radio" value="0" ng-model="product.saleFlag"> 否
								</label>
							</div>
						</div>
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span>产品规格：
							</div>
							<div class="valueName" style="padding-left: 0px;">
								<input type="number" name="productSpecificationValue"  ng-disabled="true"
									ng-patten="/^\d+$/" class="sp-input-sm sp-input" step="0.01"
									ng-model="product.productSpecificationValue" required
									style="float: left; width: 80px;" placeholder="规格值" /> <span
									style="color: red"
									ng-show="product.productSpecificationValue <= 0 || 
									productDetailEditForm.productSpecificationValue.$error.required || 
									productDetailEditForm.productSpecificationValue.$invalid">*产品的规格值大于0且不能为空！</span>
								<select class="sp-select sp-select-sm"  ng-disabled="true"
								ng-change = "productSpecificationUnitFirstChange(product.productSpecificationUnitFirst)"
									style="float: left; height: 30px;"
									ng-model="product.productSpecificationUnitFirst">

									<option ng-repeat="x in specificationFirstValues"
										value="{{x.optionName}}">{{x.optionName}}</option>
								</select><span style="float: left;">/</span> <select
									class="sp-select sp-select-sm"  ng-disabled="true"
									ng-change = "productSpecificationUnitSecondChange(product.productSpecificationUnitSecond)"
									style="float: left; height: 30px;"
									ng-model="product.productSpecificationUnitSecond">

									<option ng-repeat="x in specificationSecondValues"
										value="{{x.optionName}}">{{x.optionName}}</option>
								</select>
							</div>
						</div>
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span>产品状态：
							</div>
							<div class="valueName" style="padding-left: 0px;"
							ng-repeat="x in productStatus"
							ng-if="product.productStatus == x.optionValue">
							{{x.optionName}}</div>
						</div>
						
					<div class="titlebar">
						<span class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>采购基本单位：
						</span>
						<span>
							<select ng-model="product.productBuyUnitName" ng-disabled = "UnitDisable || true"
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
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span>温区：
							</div>
							<div class="valueName" style="padding-left: 0px;">{{product.productTemperatureZoneName}}</div>
						</div>

						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span>税率：
							</div>
							<div style="float: left;">
							<select style="float: left; height: 30px;"
										class="sp-select sp-select-sm"
										ng-model="product.taxRateName">
										<option ng-repeat="x in severTaxRate"
											value="{{x.optionName}}">{{x.optionName}}</option>
									</select>
							</div>
						</div>
						<!-- 产品属性  start-->
						<div class="titlebar sp-mune-title">
							<i class="pms-icon-kz"></i> 产品属性
						</div>
						<!-- 产品详细显示开始 -->
						<div class="titlebar">
							<div ng-repeat="categoryProperty in productCategoryProperties"
								style="float: left; padding-left: 15px; width: 45%;">
								<div class="valueName control-label"
									style="text-align: right; padding-top: 8px; width: 170px;">
									{{categoryProperty.categoryPropertyName}}：</div>
								<div style="width: 58.33333333%;float: left;">
									<div ng-repeat="productProperty in product.productProperties"
										ng-if="productProperty.customerDefineFlag != 1 
									&& productProperty.productPropertyName == categoryProperty.categoryPropertyName">
										<select class="propertyV sp-select sp-select-lg"
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
										<select class="propertyV sp-select sp-select-lg"
											id="{{categoryProperty.categoryPropertyName}}"
											style="height: 30px;" class="sp-select sp-select-lg">
											<option
												ng-repeat="x in categoryProperty.productCategoryPropertyValues"
												value="{{x.categoryPropertyValue}}">
												{{x.categoryPropertyValue}}</option>
										</select>
									</div>
								</div>
							</div>
						</div>

						<!-- 自定义属性 start -->
						<div class="titlebar sp-mune-title"
							style="height: 35px; margin-bottom: 10px; margin-top: 10px;">
							<i class="pms-icon-kz"></i> 产品自定义属性
							<button class="sp-btn sp-btn-black-sm"
								ng-click="onAddCustomerPropertyClick()" class="icon-left-bt"
								style="height: 100%;">新&nbsp;增</button>
						</div>
						<!-- 自定义属性展示框  start-->
						<div style="margin-left: 120px; margin-right: 120px;">
							<table ng-show="product.productProperties.length > 0 "
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
									<tr style="height: 30px; text-align: center;"
										ng-repeat="property in product.productProperties"
										ng-if="property.customerDefineFlag == 1">
										<input value="{{property.productPropertyValue}}"
											name="{{property.productPropertyName}}" class="propertyV1"
											type="hidden" />
										<td>{{property.productPropertyName}}</td>
										<td>{{property.productPropertyValue}}</td>
										<td class="last-td"><a
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
						
					<!-- 包装单位 start -->
					<div class="titlebar sp-mune-title"
						style="height: 35px; margin-bottom: 10px; margin-top: 10px;">
						<i class="pms-icon-kz"></i> 包装单位
						<span style="color: red" ng-show="showProductBuyUnitEmpty">请先选择采购基本单位！</span>
						<button class="sp-btn sp-btn-black-sm"
							ng-click="onAddProductPackUnitClick(product.productBuyUnitName)" 
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
									{{product.productBuyUnitName}}/{{packUnit.unitName}}</td>
									<td  class="last-td" style="text-align: center;"><a
										ng-click="onAddProductPackUnitClick(product.productBuyUnitName,packUnit,$index)"
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

						<!-- 产品详细显示结束 -->
						
						<!-- 图片模板开始 -->
						<jsp:include page="../common/picture.jsp"></jsp:include>
						<!-- 图片模板结束-->
						
						<!-- 表单提交 start -->
						<div style="margin-bottom: 60px;">
							<button ng-click="onSaveClick()"
								class="sp-input-button btn btn-default"
								ng-disabled="productDetailEditForm.productName.$error.required ||productDetailEditForm.productSpecificationValue.$invalid ||
								 productDetailEditForm.productSpecificationValue.$error.required || !isHeaderPictureSatified ||!isDetailPictureSatified || 
								 productDetailEditForm.productSpecificationUnitFirst.$error.required || 
								 productDetailEditForm.productSpecificationUnitSecond.$error.required ||
                                 isProductPropertyMissed  || product.productSpecificationValue <= 0||
                                 !serverGs1Code || !Gs1Exist || doubleClick"
								style="width: 100%;">完成</button>
						</div>
						<!-- 表单提交 end -->
					</form>
				</div>
			</div>
			<!-- 面板结束 -->
		</div>
	</div>



</div>



