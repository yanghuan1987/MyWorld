<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<div ng-cloak class="sp-menu-right sp-product-head" style="width: 80%;"
	ng-controller="productSecondaryDetailAddController" ng-show="showPage == 8">

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
					<div style="text-align: center; width: 100%;">辅材预览</div>
				</div>
				<div class="sp-content-layout">
						<!-- 辅材属性  start-->
						<div class="titlebar sp-mune-title">
							<i class="pms-icon-kz"></i> 辅材属性
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">辅材名称：</label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productName}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">辅材编码：</label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productCode}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">辅材规格： </label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productSecondarySpecification}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">计量单位： </label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productBuyUnit.optionName}}</label><br>
						</div>
						<div class="titlebar">
							<label class="viewlabel text-right" style="width: 25%;">温区： </label> <label
								class="viewlabel text-left" style="width: 75%;">{{addedProduct.productTemperatureZone.optionName}}</label><br>
						</div>
						<!-- 辅材属性显示 -->

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
						<!-- 辅材图片显示 -->
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
					新增-->
					<input type="button" class="sp-btn sp-btn-black-sm" value="预 览" onclick="showPopup('newpopUpHg',true)">
						<button type="button" ng-click="returnPage('add')"
							class="sp-btn sp-btn-black-sm">返回</button>
				</p>
			</div>
			<form name="productSecondaryDetailAddForm" id="productSecondaryDetailAddForm"
				enctype="multipart/form-data">
				<!--面板内容开始-->
				<div class="sp-row-border">
					<!-- 辅材属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 辅材基本信息
					</div>
					<!-- 辅材详细显示开始 -->
					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span> 辅材名称：
						</div>
						<div style="float: left;">
							<input type="text" name="productName"
								class="sp-input sp-input-lg" ng-model="addedProduct.productName"
								required placeholder="辅材名称" /> <span style="color: red"
								ng-show="productSecondaryDetailAddForm.productName.$error.required && !productSecondaryDetailAddForm.productName.$pristine">辅材名称不能为空！</span>
						</div>
					</div>
					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">辅材编码：</div>
						<div style="float: left;">
							<input type="text" class="sp-input sp-input-lg"
								name="productGs1Code" 
								ng-model="addedProduct.productGs1Code" placeholder="系统自动生成" ng-disabled = "true"/>
						</div>
					</div>
					<div class="titlebar form-group">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>辅材规格：
						</div>
						<div style="float: left;">
							<input type="text" class="sp-input sp-input-lg"
								name="productSecondarySpecification"
								ng-model="addedProduct.productSecondarySpecification" required
								style="float: left; width: 80px;" placeholder="规格值" />
								<span style="color: red"
								ng-show="productSecondaryDetailAddForm.productSecondarySpecification.$error.required && !productSecondaryDetailAddForm.productSecondarySpecification.$pristine">辅材的规格值不能为空！</span>
						</div>
					</div>

					<div class="titlebar form-group">
						<span class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">
							<span style="color: red">*</span>计量单位：
						</span>
						<span>
							<select ng-model="addedProduct.productBuyUnit"
								class="sp-select sp-select-md" required name="productBuyUnit"
								ng-options="x.optionName for x in SecondaryProductUnit"
								style="height: 30px;" placeholder="计量单位">
							</select>
								 <span style="color: red"
								ng-show="productSecondaryDetailAddForm.productBuyUnit.$error.required && !productSecondaryDetailAddForm.productBuyUnit.$pristine">计量单位不能为空！</span>
						</span>
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
								(productSecondaryDetailAddForm.productTemperatureZone.$error.required && !productSecondaryDetailAddForm.productTemperatureZone.$pristine)">温区不能为空！</span>
						</div>
					</div>

					<!-- 辅材属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 辅材属性
					</div>
					<!-- 辅材详细显示开始 -->
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
									ng-show="productSecondaryDetailAddForm.productProperty{{$index}}.$error.required && !productSecondaryDetailAddForm.productProperty{{$index}}.$pristine">辅材的属性{{productCategoryProperty.categoryPropertyName}}不能为空！</span>
							</div>
						</div>
					</div>

					<!-- 辅材详细显示结束 -->
					<!-- 自定义属性 start -->
					<div class="titlebar sp-mune-title"
						style="height: 35px; margin-bottom: 10px; margin-top: 10px;">
						<i class="pms-icon-kz"></i> 辅材自定义属性
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
					
					<!-- 辅材详细显示结束 -->
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
							ng-disabled="productSecondaryDetailAddForm.productName.$error.required ||
								 productSecondaryDetailAddForm.productSecondarySpecification.$error.required || !isHeaderPictureSatified ||!isDetailPictureSatified || 
								 productSecondaryDetailAddForm.productBuyUnit.$error.required ||
                                 isProductPropertyMissed  || doubleClick"
							style="width: 100%;">完成</button>
					</div>
					<!-- 表单提交 end -->

				</div>
			</form>
		</div>

	</div>


</div>
