<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="sp-menu-right" style="width: 80%;"
	ng-show="showPage == 2">
	<div style="height: 20px;width: 100%;padding-left: 15px;">
		<i class="showone"></i>
	</div>
	<div style="width: 100%;padding-left: 15px;" id="ProductSecondaryTable">

		<div class="sp-content-bg">
			<!--面板开始-->
			<div class="sp-panel">
				<!-- 面板标题start -->
				<div class="sp-panel-body"
					style="background-color: white; font-size = 14; color: black; font-weight: bold; height: 44px;">
					<span style="padding-left: 15px;"> 原料 - {{categoryName}} </span>
					<button type="button" ng-click="onAddRawMaterialsClick()" ng-show = "showNewButton" 
						class="sp-btn sp-btn-gray-md" style="margin-top: -5px;">新增辅材</button>
					<!-- 搜索按钮设置 -->
					<div style="float: right !important; margin-right: 15px;">
						<input type='text' class="sp-input sp-input-search-md"
							ng-model="rawMaterialsCode"
							ng-change="searchByRawMaterialsCode(rawMaterialsCode)"
							placeholder="输入搜索辅材编码..." /> <input type="button"
							class="sp-btn sp-btn-search-md"
							ng-click="searchByRawMaterialsCode(rawMaterialsCode)" value="搜索" />
					</div>
				</div>
				<!-- 面板标题end -->

				<!-- 面板内容start -->
				<div class="sp-content-layout">
					<!-- 表格开始 -->
					<table id="tbone" class="sp-table">
						<!-- 表格头部start -->
						<tr>
							<!--表头，这只排序字段，-->
							<th class="th-width-md">辅材名称</th>
							<th class="th-width-md">辅材编码</th>
							<th class="th-width-md">辅材规格</th>
							<th class="th-width-md">计量单位</th>
							<!-- <th class="th-width-md">包装单位</th> -->
							<th ng-repeat="property in productCategoryProperties" ng-if="categoryBotton == 3">
								<div ng-if="property.selectFlag == 1">
									<select class="sp-select" style="width: 88%;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;"
										ng-model="property.selectedValue"
										ng-options="x.categoryPropertyValue for x in property.productCategoryPropertyValues"
										ng-change="searchByProperty($index,property.categoryPropertyName, property.selectedValue.categoryPropertyValue)">
										<option value="">{{property.categoryPropertyName}}</option>
									</select>
								</div>
								<div ng-if="property.selectFlag == 0">{{property.categoryPropertyName}}</div>
							</th>
						</tr>
						<!-- 表格头部end -->
						<!-- 表格具体内容start -->
						<tr ng-repeat="rawMaterials in RawMaterialses" id="{{$index}}">
							<td class= "autobreak"
								style="height: 30px;">{{rawMaterials.rawMaterialsName}}</td>
							<td style="height: 30px;"><a ng-click="rowClick($index)"
								href="#"
								style="cursor: pointer; color: #99C731; text-decoration: underline;"
								title="点击查看详情">{{rawMaterials.rawMaterialsCode}}</a></td>
							<td class= "autobreak"
								style="height: 30px;">{{rawMaterials.rawMaterialsSpecification}}</td>
							<td class="autobreak"
								style="height: 30px;">{{rawMaterials.rawMaterialsBuyUnitName}}</td>
							<td ng-repeat="property in productCategoryProperties" ng-if="categoryBotton == 3"><span
								ng-repeat="propertyValue in rawMaterials.rawMaterialsProperties"
								ng-if="propertyValue.rawMaterialsPropertyName == property.categoryPropertyName">{{propertyValue.rawMaterialsPropertyValue}}</span>
							</td>
						</tr>
						<tr ng-repeat="y in tempList">
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px" ng-if="categoryBotton == 3"
								ng-repeat="property in productCategoryProperties"></td>
						</tr>
						<!-- 表格具体内容end -->
					</table>
					<!-- 表格结束 -->
				</div>
				<!-- 面板内容end -->
			</div>
			<!--面板结束-->
			<!-- 分页设计 -->
			<br>
				<spfood-pagination conf="pagination"></spfood-pagination>
		</div>
	</div>
</div>