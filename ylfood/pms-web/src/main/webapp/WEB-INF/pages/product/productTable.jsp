<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="sp-menu-right"
	ng-show="showTable">
	<div style="height: 20px;width: 100%;padding-left: 15px;">
		<i class="showone"></i>
	</div>
	<div style="width: 100%;padding-left: 15px;" id="productTable">

		<div class="sp-content-bg">
			<!--面板开始-->
			<div class="sp-panel">
				<!-- 面板标题start -->
				<div class="sp-panel-body"
					style="background-color: white; font-size = 14; color: black; font-weight: bold; height: 44px;">
					<span style="padding-left: 15px;"> 产品 - {{categoryName}} </span>
					<button type="button" ng-click="onAddProductClick()" ng-show = "showNewButton" 
						class="sp-btn sp-btn-gray-md" style="margin-top: -5px;">新增产品</button>
					<!-- 搜索按钮设置 -->
					<div style="float: right !important; margin-right: 15px;">
						<input type='text' class="sp-input sp-input-search-md"
							ng-model="productCode"
							ng-change="searchByProductCode(productCode)"
							placeholder="输入搜索产品编码..." /> <input type="button"
							class="sp-btn sp-btn-search-md"
							ng-click="searchByProductCode(productCode)" value="搜索" />
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
							<th class="th-width-md">产品编码</th>
							<th class="th-width-md">产品名称</th>
							<th class="th-width-mini"><select class="sp-select"
								style="width: 90%;" ng-model="selected"
								ng-options="m.value for m in isSaleableValues"
								ng-change="searchByIsSalingFlag(selected.id)">
									<option label="可售" value="">可售</option>
							</select></th>
							<th class="th-width-md">产品规格</th>
							<th ng-repeat="property in productCategoryProperties">
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
						<tr ng-repeat="product in products" id="{{$index}}">
							<td style="height: 30px;"><a ng-click="rowClick($index)"
								href="#"
								style="cursor: pointer; color: #99C731; text-decoration: underline;"
								title="点击查看详情">{{product.productCode}}</a></td>
							<td class= "autobreak"
								style="height: 30px;">{{product.productName}}</td>
							<td ng-if="product.saleFlag == 1">是</td>
							<td ng-if="product.saleFlag == 0">否</td>
							<td class="autobreak"
								style="height: 30px;">{{product.productSpecificationValue}}
								{{product.productSpecificationUnitFirst}} /
								{{product.productSpecificationUnitSecond}}</td>
							<td ng-repeat="property in productCategoryProperties"><span
								ng-repeat="propertyValue in product.productProperties"
								ng-if="propertyValue.productPropertyName == property.categoryPropertyName">{{propertyValue.productPropertyValue}}</span>
							</td>
						</tr>
						<tr ng-repeat="y in tempList">
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"
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