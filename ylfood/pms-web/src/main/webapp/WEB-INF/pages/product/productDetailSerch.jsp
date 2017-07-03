<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="sp-menu-right" ng-show="showPage == 1" style="border-left: 5px solid #ebebeb;width: 80%;">
	<jsp:include page="../common/productHead.jsp"></jsp:include>
	<div style="width: 100%;" id="productTable">
		<div class="sp-content-bg">
			<!--面板开始-->
			<div class="sp-panel">
				<!-- 面板标题start -->
				<table
					style="width: 90%; word-break: break-all; word-wrap: break-word;">
					<tbody>
						<tr style="height: 40px;">
							<td style="width: 15%; text-align: right;">产品编码</td>
							<td style="text-align: left; width: 15%;"><input ng-keyup="enterKeyup($event,1)"
								ng-model="productCodes" class="sp-input sp-input-md "
								type="text"></td>
							<td style="width: 15%; text-align: right;">产品GS1编码</td>
							<td style="text-align: left; width: 15%;"><input ng-keyup="enterKeyup($event,1)"
								ng-model="productGs1Codes" class="sp-input sp-input-md "
								type="text"></td>
							<td style="width: 15%; text-align: right;">产品名称</td>
							<td style="text-align: left; width: 15%;"><input ng-keyup="enterKeyup($event,1)"
								ng-model="productNames" class="sp-input sp-input-md" type="text"></td>
						</tr>
						<tr style="height: 40px;">
							<td style="width: 15%; text-align: right;">产品是否可售</td>
							<td style="text-align: left; width: 15%;"><select
								class="sp-select"
								style="float: left; height: 30px; width: 100px;"
								ng-model="saleFlags">
									<option ng-repeat="x in saleList" value="{{x.optionValue}}">{{x.optionName}}</option>
							</select></td>
							<td style="width: 15%; text-align: right;">产品状态</td>
							<td style="text-align: left; width: 15%;"><select
								ng-model="productStatuss" class="sp-select sp-float-left"
								style="height: 30px; width: 100px;">
									<option ng-repeat="x in productStatus"
										value="{{x.optionValue}}">{{x.optionName}}</option>
							</select></td>
							<td style="width: 15%; text-align: right;">产品温区</td>
							<td style="text-align: left; width: 15%;"><select
								ng-model="productTemperatureZoneCodes"
								class="sp-select sp-float-left"
								style="height: 30px; width: 100px;">
									<option ng-repeat="x in severProductTemperatureZone"
										ng-if="x.optionValue >= 0" value="{{x.optionValue}}">{{x.optionName}}</option>
							</select></td>
						</tr>
						<tr style="height: 40px;">
							<td style="width: 15%; text-align: right;">是否拼装</td>
							<td style="text-align: left; width: 15%;"><select
								class="sp-select"
								style="float: left; height: 30px; width: 100px;"
								ng-model="compositeFlags">
									<option ng-repeat="x in compositeList" value="{{x.optionValue}}">{{x.optionName}}</option>
							</select></td>
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
						ng-click="searchByProductCode('detail')">查询</button>
					<button type="button" class="sp-btn sp-btn-gray-md"
						ng-click="clearDetail()">重置</button>
				</div>
				<div class="sp-panel-body"
					style="background-color: white; font-size = 14; color: black; font-weight: bold; height: 44px;">
				</div>
				<!-- 面板标题end -->
				<div ng-show="products != null">
					<!-- 面板内容start -->
					<div class="sp-content-layout">
						<!-- 表格开始 -->
						<table id="tbone" class="sp-table">
							<!-- 表格头部start -->
							<tr>
								<!--表头，这只排序字段，-->
								<th class="th-width-md">产品编码</th>
								<th class="th-width-md">产品名称</th>
								<th class="th-width-md">产品GS1编码</th>
								<th class="th-width-mini">可售</th>
								<th class="th-width-md">产品规格</th>
								<th class="th-width-mini">拼装</th>
								<th class="th-width-md">温区</th>
								<th class="th-width-md">状态</th>
							</tr>
							<!-- 表格头部end -->
							<!-- 表格具体内容start -->
							<tr ng-repeat="product in products" id="{{$index}}">
								<td style="height: 30px;"><a ng-click="rowClick($index)"
									href="#"
									style="cursor: pointer; color: #99C731; text-decoration: underline;"
									title="点击查看详情">{{product.productCode}}</a></td>
								<td class="autobreak" style="height: 30px;">{{product.productName}}</td>
								<td class="autobreak" style="height: 30px;">{{product.productGs1Code}}</td>
								<td ng-if="product.saleFlag == 1">是</td>
								<td ng-if="product.saleFlag == 0">否</td>
								<td ng-if="product.saleFlag == null">-</td>
								<td class="autobreak" style="height: 30px;" >{{product.productSpecificationValue}}
									{{product.productSpecificationUnitFirst}} /
									{{product.productSpecificationUnitSecond}}</td>
								<td ng-if="product.compositeFlag == 1">是</td>
								<td ng-if="product.compositeFlag == 0">否</td>
								<td class="autobreak" style="height: 30px;">{{product.productTemperatureZoneName}}</td>
								<td class="autobreak" style="height: 30px;" 
								ng-repeat="x in productStatus"
								ng-if="x.optionValue == product.productStatus">{{x.optionName}}</td>
							</tr>
							<tr ng-repeat="y in tempList">
								<td style="height: 30px"></td>
								<td style="height: 30px"></td>
								<td style="height: 30px"></td>
								<td style="height: 30px"></td>
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