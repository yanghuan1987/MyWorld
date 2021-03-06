<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = application.getInitParameter("static-file");
%>
<div ng-cloak class="sp-menu-right sp-product-head" style="width: 80%;"
	ng-controller="rawMaterialsDetailController" ng-show="showPage == 9">

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
			<div id="content" class="sp-content"
				style="border: 0; overflow-y: auto; overflow-x: hidden; height: 600px;">
				<div class="sp-page-title" style="margin-left: 0;">
					<div style="text-align: center; width: 100%;">辅材预览</div>
				</div>
				<div class="sp-content-layout">
					<!-- 辅材属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 辅材属性
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">辅材名称：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{rawMaterials.rawMaterialsName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">辅材编码：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{rawMaterials.rawMaterialsCode}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">辅材规格：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{rawMaterials.rawMaterialsSpecification}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">计量单位：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{rawMaterials.rawMaterialsBuyUnitName}}</label><br>
					</div>
					<div class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">温区：
						</label> <label class="viewlabel text-left" style="width: 75%;">{{rawMaterials.rawMaterialsTemperatureZoneName}}</label><br>
					</div>
					<!-- 辅材属性显示 -->
					<div ng-repeat="rawMaterialsProperty in rawMaterials.rawMaterialsProperties"
						class="titlebar">
						<label class="viewlabel text-right" style="width: 25%;">{{rawMaterialsProperty.rawMaterialsPropertyName}}：</label>
						<label class="viewlabel text-left" style="width: 75%;">{{rawMaterialsProperty.rawMaterialsPropertyValue}}</label><br>
					</div>
					<!-- 辅材图片显示 -->
					<div class="titlebar" ng-repeat="packUnit in rawMaterialsPackUnit">
						<label class="viewlabel text-right" style="width: 25%;"
							ng-show="$index==0">包装单位： </label> <label
							class="viewlabel text-right" style="width: 25%;"
							ng-show="$index!=0"> </label> <label class="viewlabel text-left"
							style="width: 75%;">{{packUnit.unitValue}}{{rawMaterials.rawMaterialsBuyUnitName}}/{{packUnit.unitName}}</label><br>
					</div>
					<!-- 辅材详细显示结束 -->
					<!-- 头部图片st -->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 头部图片
					</div>
					<!--  -->
					<div>
						<br>
						<!-- 采用angular循环的方式，对存入productPictures的图片进行展示 -->
						<label ng-repeat="picture in thumbHeader"
							style="width: 15%; margin-left: 1.5%; margin-top: 3%; display: inline-block;">
							<img ng-src="{{picture.pictureAddress}}" class="sp-picture-title" />
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

	<!-- 辅材详情信息显示 -->
	<div ng-show="!editRawMaterials">
		<!-- 顶部按钮 -->
		<div class="titlebar" style="height: 20px;">
			<i></i>
		</div>
		<!-- 顶部按钮 end -->
		<div class="sp-content-bg"
			style="background-color: white; width: 98%; margin-left: 15px; overflow-x: hidden;">

			<!--面板开始-->
			<div class="sp-panel panel panel-default">
				<div class="sp-panel-body panel-body category-name-head">
					<p class="sp-p-title">
						<span>辅材-->{{rawMaterials.rawMaterialsName}}</span>
						<button type="button" ng-click="onEditClick()"
							class="sp-btn sp-btn-gray-md" style="margin-top: -8px;">编辑</button>
						<button type="button" ng-click="returnPage()"
							class="sp-btn sp-btn-gray-md" style="margin-top: -8px;">返回</button>
					</p>
				</div>
				<!--面板内容开始-->
				<div class="sp-row-border row" style="padding: 5px 0px 18px 5px;">
					<!-- 辅材属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 辅材基本信息
					</div>
					<!-- 辅材详细显示开始 -->
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">辅材名称：</div>
						<div class="valueName" style="padding-left: 0px;">{{rawMaterials.rawMaterialsName}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">辅材编码：</div>
						<div class="valueName" style="padding-left: 0px;">{{rawMaterials.rawMaterialsCode}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">辅材规格：</div>
						<div class="valueName" style="padding-left: 0px;">
							{{rawMaterials.rawMaterialsSpecification}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">计量单位：</div>
						<div class="valueName" style="padding-left: 0px;">
							{{rawMaterials.rawMaterialsBuyUnitName}}</div>
					</div>
					<div class="titlebar">
						<div class="valueName control-label sp-input-p"
							style="text-align: right; padding: 3px;">温区：</div>
						<div class="valueName" style="padding-left: 0px;">
							{{rawMaterials.rawMaterialsTemperatureZoneName}}</div>
					</div>
					<!-- 辅材属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 辅材属性
					</div>
					<div class="titlebar">
						<div class="viewproper"
							ng-repeat="property in rawMaterials.rawMaterialsProperties">
							<div class="control-label"
								style="text-align: right; padding: 3px; width: 170px; float: left;">
								{{property.rawMaterialsPropertyName}}：</div>
							<div style="padding: 3px; float: left; width: 58.33333333%;">
								{{property.rawMaterialsPropertyValue}}</div>
						</div>
					</div>
					<!-- 辅材属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 包装单位
					</div>
					<div class="titlebar">
						<div class="viewproper" ng-repeat="packUnit in rawMaterialsPackUnit">
							<div class="control-label"
								style="text-align: right; padding: 3px; width: 170px; float: left;">
								{{packUnit.unitValue}}{{rawMaterials.rawMaterialsBuyUnitName}}/{{packUnit.unitName}}</div>
							<div style="padding: 3px; float: left; width: 58.33333333%;">
							</div>
						</div>
					</div>
					<!-- 辅材详细显示结束 -->
					<!-- 头部图片st -->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 头部图片
					</div>
					<!--  -->
					<div>
						<br>
						<!-- 采用angular循环的方式，对存入productPictures的图片进行展示 -->
						<label ng-repeat="picture in rawMaterials.rawMaterialsPictures"
							style="width: 15%; margin-left: 1.5%; margin-top: 3%; display: inline-block;">
							<img ng-show="picture.pictureShowPosition == 0"
							ng-src="{{picture.pictureAddress}}" class="sp-picture-title" />
						</label> <br>
					</div>
					<!-- 头部图片ed -->
					<!-- 详情页图片st -->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 详情页图片
					</div>
					<div>
						<label ng-repeat="picture in rawMaterials.rawMaterialsPictures"
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



	<!-- 辅材编辑页 -->
	<div ng-show="editRawMaterials">
		<!-- 顶部按钮 -->
		<div class="titlebar" style="height: 20px;">
			<i></i>
		</div>
		<!-- 顶部按钮 end -->
		<div class="sp-content-bg"
			style="background-color: white; width: 98%; margin-left: 15px; overflow-x: hidden;">

			<!--面板开始-->
			<div class="sp-panel panel panel-default">
				<div class="sp-panel-body panel-body category-name-head">
					<p class="sp-p-title">
						辅材-->{{rawMaterials.rawMaterialsName}}</span> <input type="button"
							class="sp-btn sp-btn-black-sm" value="预 览"
							onclick="showPopup('editpopUpHg',true)">
						<button type="button" ng-click="returnPage('edit')"
							class="sp-btn sp-btn-black-sm">返回</button>
					</p>
				</div>
				<!--面板内容开始-->
				<div class="sp-row-border row" style="padding: 5px 0px 18px 5px;">
					<!-- 辅材属性  start-->
					<div class="titlebar sp-mune-title">
						<i class="pms-icon-kz"></i> 辅材基本信息
					</div>
					<form name="rawMaterialsEditForm" enctype="multipart/form-data">
						<!-- 辅材详细显示开始 -->
						<!-- 辅材详细显示开始 -->
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span> 辅材名称：
							</div>
							<div class="valueName" style="padding-left: 0px;">
								<input type="text" name="rawMaterialsName"
									class="sp-input sp-input-lg" ng-model="rawMaterials.rawMaterialsName"
									required placeholder="辅材名称" /> <span style="color: red"
									ng-show="rawMaterialsEditForm.rawMaterialsName.$error.required && !rawMaterialsEditForm.rawMaterialsName.$pristine">辅材名称不能为空！</span>
							</div>
						</div>
						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span>辅材规格：
							</div>
							<div class="valueName" style="padding-left: 0px;">
								<input type="text" name="rawMaterialsSpecification"
									class="sp-input-sm sp-input"
									ng-model="rawMaterials.rawMaterialsSpecification" required
									style="float: left;" placeholder="规格值" /> <span
									style="color: red"
									ng-show="rawMaterialsEditForm.rawMaterialsSpecification.$error.required || 
									rawMaterialsEditForm.rawMaterialsSpecification.$invalid">*辅材的规格值不能为空！</span>
							</div>
						</div>

						<div class="titlebar">
							<span class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;"> <span
								style="color: red">*</span>计量单位：
							</span> <span> <select ng-model="rawMaterials.rawMaterialsBuyUnitName"
								class="sp-select sp-select-md"
								required name="rawMaterialsBuyUnit" style="height: 30px;"
								placeholder="计量单位">
									<option ng-repeat="x in RawMaterialsUnit"
										value="{{x.optionName}}">{{x.optionName}}</option>
							</select>
								<span style="color: red"
								ng-show="rawMaterialsEditForm.rawMaterialsBuyUnit.$error.required && !rawMaterialsEditForm.rawMaterialsBuyUnit.$pristine">计量单位不能为空！</span>
							</span>
						</div>

						<div class="titlebar">
							<div class="valueName control-label sp-input-p"
								style="text-align: right; padding: 3px;">
								<span style="color: red">*</span>温区：
							</div>
							<div class="valueName" style="padding-left: 0px;">{{rawMaterials.rawMaterialsTemperatureZoneName}}</div>
						</div>
						<!-- 辅材属性  start-->
						<div class="titlebar sp-mune-title">
							<i class="pms-icon-kz"></i> 辅材属性
						</div>
						<!-- 辅材详细显示开始 -->
						<div class="titlebar">
							<div ng-repeat="categoryProperty in productCategoryProperties"
								style="float: left; padding-left: 15px; width: 45%;">
								<div class="valueName control-label"
									style="text-align: right; padding-top: 8px; width: 170px;">
									{{categoryProperty.categoryPropertyName}}：</div>
								<div style="width: 58.33333333%; float: left;">
									<div ng-repeat="rawMaterialsProperty in rawMaterials.rawMaterialsProperties"
										ng-if="rawMaterialsProperty.customerDefineFlag != 1 
									&& rawMaterialsProperty.rawMaterialsPropertyName == categoryProperty.categoryPropertyName">
										<select class="propertyV2 sp-select sp-select-lg"
											style="height: 30px;"
											id="{{categoryProperty.categoryPropertyName}}">
											<option
												ng-repeat="x in categoryProperty.productCategoryPropertyValues"
												value="{{x.categoryPropertyValue}}"
												ng-if="rawMaterialsProperty.rawMaterialsPropertyValue == x.categoryPropertyValue">
												{{x.categoryPropertyValue}}</option>
											<option
												ng-repeat="x in categoryProperty.productCategoryPropertyValues"
												value="{{x.categoryPropertyValue}}"
												ng-if="rawMaterialsProperty.rawMaterialsPropertyValue != x.categoryPropertyValue">
												{{x.categoryPropertyValue}}</option>
										</select>
									</div>
									<div ng-repeat="rawMaterialsProperty in otherProperty"
										ng-if="rawMaterialsProperty.categoryPropertyName == categoryProperty.categoryPropertyName">
										<select class="propertyV2 sp-select sp-select-lg"
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
							<i class="pms-icon-kz"></i> 辅材自定义属性
							<button class="sp-btn sp-btn-black-sm"
								ng-click="onAddCustomerPropertyClick()" class="icon-left-bt"
								style="height: 100%;">新&nbsp;增</button>
						</div>
						<!-- 自定义属性展示框  start-->
						<div style="margin-left: 120px; margin-right: 120px;">
							<table ng-show="rawMaterials.rawMaterialsProperties.length > 0 "
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
										ng-repeat="property in rawMaterials.rawMaterialsProperties"
										ng-if="property.customerDefineFlag == 1">
										<input value="{{property.rawMaterialsPropertyValue}}"
											name="{{property.rawMaterialsPropertyName}}" class="propertyV3"
											type="hidden" />
										<td>{{property.rawMaterialsPropertyName}}</td>
										<td>{{property.rawMaterialsPropertyValue}}</td>
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
							<i class="pms-icon-kz"></i> 包装单位 <span style="color: red"
								ng-show="showRawMaterialsBuyUnitEmpty">请先选择计量单位！</span>
							<button class="sp-btn sp-btn-black-sm"
								ng-click="onAddRawMaterialsPackUnitClick(rawMaterials.rawMaterialsBuyUnitName)"
								class="icon-left-bt" style="height: 100%;">新&nbsp;增</button>
						</div>
						<!-- 包装单位展示框  start-->
						<div style="margin-left: 120px; margin-right: 120px;">
							<table ng-show="rawMaterialsPackUnit.length > 0" class="sp-table"
								style="TABLE-LAYOUT: fixed; text-align: center; margin-bottom: 10px;">
								<tbody>
									<tr
										style="background-color: #EBEBEB; font-size: 12px; text-align: center;">
										<td class="th-width-md">包装单位</td>
										<td class="th-width-md last-td">操作</td>
									</tr>
									<!-- 显示新添加的自定义属性名和属性值 -->
									<tr style="height: 30px;"
										ng-repeat="packUnit in rawMaterialsPackUnit">
										<td class="text-left" style="text-align: center;">{{packUnit.unitValue}}
											{{rawMaterials.rawMaterialsBuyUnitName}}/{{packUnit.unitName}}</td>
										<td class="last-td" style="text-align: center;"><a
											ng-click="onAddRawMaterialsPackUnitClick(rawMaterials.rawMaterialsBuyUnitName,packUnit,$index)"
											href="#"
											style="cursor: pointer; color: #99C731; text-decoration: underline;">编辑</a>
											<a ng-click="onRemoveRawMaterialsPackUnitClick($index)" href="#"
											style="cursor: pointer; color: #99C731; text-decoration: underline;">删除</a>
										</td>
									</tr>
									<!-- 显示新添加的自定义属性名和属性值  end-->
								</tbody>
							</table>
						</div>
						<!-- 包装单位展示框  end-->
						<!-- 包装单位  end  -->

						<!-- 辅材详细显示结束 -->

						<!-- 图片模板开始 -->
						<jsp:include page="../common/picture.jsp"></jsp:include>
						<!-- 图片模板结束-->

						<!-- 表单提交 start -->
						<div style="margin-bottom: 60px;">
							<button ng-click="onSaveClick()"
								class="sp-input-button btn btn-default"
								ng-disabled="rawMaterialsEditForm.rawMaterialsName.$error.required ||rawMaterialsEditForm.rawMaterialsSpecification.$invalid ||
								 rawMaterialsEditForm.rawMaterialsSpecification.$error.required || !isHeaderPictureSatified ||!isDetailPictureSatified || 
                                 isProductPropertyMissed  || doubleClick"
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



