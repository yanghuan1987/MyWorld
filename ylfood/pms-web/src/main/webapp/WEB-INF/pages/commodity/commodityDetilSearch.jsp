<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = application.getInitParameter("static-file");
%>
<!-- start container -->
<div class="sp-menu-right tab-content"
	style="height: 90%; padding-left: 0px; padding-right: 0px;width: 80%;"
	ng-show="showPage == 1">
	<div class="titlebar" style="height: 20px;">
		<i></i>
	</div>
	<div class="titlebar">
		<div class="sp-content-bg"
			style="background-color: white; width: 101%;">
			<table
				style="width: 90%; word-break: break-all; word-wrap: break-word;">
				<tbody>
					<tr style="height: 40px;">
						<td style="width: 15%; text-align: right;">商品编码</td>
						<td style="text-align: left; width: 15%;"><input ng-keyup="enterKeyup($event,1)"
							ng-model="commodityCodes" class="sp-input sp-input-md "
							type="text"></td>
						<td style="width: 15%; text-align: right;">商品GS1编码</td>
						<td style="text-align: left; width: 15%;"><input ng-keyup="enterKeyup($event,1)"
							ng-model="commodityGs1Codes" class="sp-input sp-input-md "
							type="text"></td>
						<td style="width: 15%; text-align: right;">商品名称</td>
						<td style="text-align: left; width: 15%;"><input ng-keyup="enterKeyup($event,1)"
							ng-model="commodityNames" class="sp-input sp-input-md"
							type="text"></td>
					</tr>
					<tr style="height: 40px;">
						<td style="width: 15%; text-align: right;">显示端</td>
						<td style="text-align: left; width: 15%;"><select
							class="sp-select"
							style="float: left; height: 30px; width: 100px;"
							ng-model="commodityShowPlaces">
								<option ng-repeat="x in basicCustomerType"
									value="{{x.optionName}}">{{x.optionName}}</option>
						</select></td>
						<td></td>
						<td></td>
						<td style="width: 15%; text-align: right;">商品状态</td>
						<td style="text-align: left; width: 15%;"><select
							ng-model="commodityStatuss" class="sp-select sp-float-left"
							style="height: 30px; width: 100px;">
								<option ng-repeat="x in commodityStatusList" value="{{x.Value}}">{{x.Name}}</option>
						</select>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="padding-left: 40%;">
				<button type="button" class="sp-btn sp-btn-search-md"
					style="padding: 8px 27px 8px 27px; margin-left: 7px; margin-top: 5px;"
					ng-click="searchCommotyByCode('detail')">查询</button>
				<button type="button" class="sp-btn sp-btn-gray-md"
					ng-click="clearDetail()">重置</button>
			</div>
			<div ng-show="commodityList != null">
				<!--面板开始-->
				<div class="sp-panel panel panel-default">
					<!--面板标题/头部-->
					<div class="sp-panel-body panel-body"
						style="background-color: white; color: black; font-weight: bold;">
					</div>
					<!--面板内容开始-->

					<div class="sp-content-layout">
						<!--按钮工具栏开始-->
						<div style="padding-bottom: 8px;">
							<input type="checkbox" id="ck_all" ng-model="selectAll" />全选
							<button type="button" class="sp-btn sp-btn-gray-sm"
								ng-click="updateStateEdit(3)" style="margin-left: 15px;">批量上架</button>
							<button type="button" class="sp-btn sp-btn-gray-sm"
								ng-click="updateStateEdit(4)">批量下架</button>
							<button type="button" class="sp-btn sp-btn-gray-sm"
								ng-click="updateStateEdit(6)">批量删除</button>
						</div>
						<!--按钮工具栏结束-->
						<!--绑定表格开始-->
						<table id="tb" class="sp-table">
							<tr>
								<!--表头，这只排序字段，-->
								<th class="th-width-mini"></th>
								<th class="th-width-sm">编码</th>
								<th class="th-width-md">商品名称</th>
								<th class="th-width-md">商品GS1编码</th>
								<th class="th-width-sm">原价</th>
								<th class="th-width-sm">现价</th>
								<th class="th-width-sm">重量</th>
								<th class="th-width-sm">显示端</th>
								<th class="th-width-sm">上/下架</th>
								<th class="th-width-sm">操作</th>
							</tr>
							<!--重复项开始，设置排序字段，数据源，names代表数据源，x表示names中遍历的每一个对象，id表示模糊搜索输入的内容，要与输入的input的ng-model一致，col表示排序名称，要与表头中一致-->
							<tr
								ng-repeat="x in commodityList | filter: id | orderBy:col:desc">
								<td style="height: 30px"><input type="checkbox" name="cbx"
									value="{{x.id}}" ng-click="isSelected(x)"
									ng-checked="selectAll" /></td>
								<td style="height: 30px"><a ng-click="showCommodityInfo(x)"
									href="#"
									style="cursor: pointer; color: #99C731; text-decoration: underline;"
									title="点击查看详情"> {{x.commodityCode}}</a></td>
								<td style="height: 30px" class="autobreak">{{x.commodityName}}</td>
								<td style="height: 30px" class="autobreak">{{x.commodityGs1Code}}</td>
								<td style="height: 30px" class="autobreak">{{x.commodityPrice}}元</td>
								<td style="height: 30px" class="autobreak">{{x.commoditySalesPrice}}元</td>
								<td style="height: 30px" class="autobreak">{{x.commodityWeight}}&nbsp;{{x.commodityWeightUnit}}</td>
								<td style="height: 30px" class="autobreak">{{x.commodityShowPlace}}</td>
								<td style="height: 30px" class="autobreak">
									<button ng-click="editSta(x,3)" ng-if="x.commodityStatus != 3"
										class="sp-btn sp-btn-gray-sm">上架</button>
									<button ng-click="editSta(x,4)" ng-if="x.commodityStatus == 3"
										class="sp-btn sp-btn-gray-sm">下架</button>
								</td>
								<td style="height: 30px" class="autobreak">
									<button type="button" ng-click="editSta(x,6)"
										class="sp-btn sp-btn-gray-sm" style="color: #ff0000;">删除</button>
								</td>
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
								<td style="height: 30px"></td>
								<td style="height: 30px"></td>
							</tr>
						</table>
						<!--绑定表格结束-->
					</div>
					<!--面板内容开始-->
					<!--模态框弹窗-->
				</div>
				<!--面板结束-->

				<!-- 分页标签  -->
				<!-- <pagination conf="paginationConf"></pagination> -->
				<br>
				<spfood-pagination conf="pagination"></spfood-pagination>
			</div>
		</div>
	</div>
</div>