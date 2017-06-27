<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="sp-menu-right" ng-show="showPage == 1" style="width: 80%;">
	<div style="height: 20px; width: 100%; padding-left: 15px;">
		<i class="showone"></i>
	</div>
	<div style="width: 100%; padding-left: 15px;" id="productTable">

		<div class="sp-content-bg">
			<!--面板开始-->
			<div class="sp-panel">
				<!-- 面板标题start -->
				<table
					style="width: 90%; word-break: break-all; word-wrap: break-word;">
					<tbody>
						<tr style="height: 40px;">
							<td style="width: 15%; text-align: right;">原料编码</td>
							<td style="text-align: left; width: 15%;"><input ng-keyup="enterKeyup($event,1)"
								ng-model="rawMaterialsCodes" class="sp-input sp-input-md "
								type="text"></td>
							<td style="width: 15%; text-align: right;">原料名称</td>
							<td style="text-align: left; width: 15%;"><input ng-keyup="enterKeyup($event,1)"
								ng-model="rawMaterialsNames" class="sp-input sp-input-md" type="text"></td>
							<td style="width: 15%; text-align: right;">原料温区</td>
							<td style="text-align: left; width: 15%;"><select
								ng-model="rawMaterialsTemperatureZoneCodes"
								class="sp-select sp-float-left"
								style="height: 30px; width: 100px;">
									<option ng-repeat="x in severProductTemperatureZone"
										ng-if="x.optionValue >= 0" value="{{x.optionValue}}">{{x.optionName}}</option>
							</select></td>
						</tr>
						<tr style="height: 40px;">
							<td style="width: 15%; text-align: right;"></td>
							<td style="text-align: left; width: 15%;"></td>
							<td style="width: 15%; text-align: right;"></td>
							<td style="text-align: left; width: 15%;"></td>
							<td style="width: 15%; text-align: right;"></td>
							<td style="text-align: left; width: 15%;"></td>
						</tr>
						<tr style="height: 40px;">
							<td style="width: 15%; text-align: right;"></td>
							<td style="text-align: left; width: 15%;"></td>
							<td style="width: 15%; text-align: right;"></td>
							<td style="text-align: left; width: 15%;"></td>
							<td style="width: 15%; text-align: right;"></td>
							<td style="text-align: left; width: 15%;"></td>
						</tr>
					</tbody>
				</table>
				<div style="padding-left: 40%;">
					<button type="button" class="sp-btn sp-btn-search-md"
						style="padding: 8px 27px 8px 27px; margin-left: 7px; margin-top: 5px;"
						ng-click="searchByRawMaterialsCode('detail')">查询</button>
					<button type="button" class="sp-btn sp-btn-gray-md"
						ng-click="clearDetail()">重置</button>
				</div>
				<div class="sp-panel-body"
					style="background-color: white; font-size = 14; color: black; font-weight: bold; height: 44px;">
				</div>
				<!-- 面板标题end -->
				<div ng-show="RawMaterialses != null">
					<!-- 面板内容start -->
					<div class="sp-content-layout">
						<!-- 表格开始 -->
						<table id="tbone" class="sp-table">
							<!-- 表格头部start -->
							<tr>
								<!--表头，这只排序字段，-->
								<th class="th-width-md">原料编码</th>
								<th class="th-width-md">原料名称</th>
								<th class="th-width-md">原料规格</th>
								<th class="th-width-md">温区</th>
							</tr>
							<!-- 表格头部end -->
							<!-- 表格具体内容start -->
							<tr ng-repeat="rawMaterials in RawMaterialses" id="{{$index}}">
								<td style="height: 30px;"><a ng-click="rowClick($index)"
									href="#"
									style="cursor: pointer; color: #99C731; text-decoration: underline;"
									title="点击查看详情">{{rawMaterials.rawMaterialsCode}}</a></td>
								<td class="autobreak" style="height: 30px;">{{rawMaterials.rawMaterialsName}}</td>
								<td class="autobreak" style="height: 30px;">{{rawMaterials.rawMaterialsSpecification}}</td>
								<td class="autobreak" style="height: 30px;">{{rawMaterials.rawMaterialsTemperatureZoneName}}</td>
							</tr>
							<tr ng-repeat="y in tempList">
								<td style="height: 30px"></td>
								<td style="height: 30px"></td>
								<td style="height: 30px"></td>
								<td style="height: 30px"></td>
							</tr>
							<!-- 表格具体内容end -->
						</table>
						<!-- 表格结束 -->
					</div>
					<!-- 分页设计 -->
					<br>
					<spfood-pagination conf="pagination"></spfood-pagination>

				</div>
				<!-- 面板内容end -->
			</div>
			<!--面板结束-->
		</div>
	</div>
</div>