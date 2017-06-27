<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = application.getInitParameter("static-file");
%>
<div class="sp-content-layout" ng-show="commentListPage == null">
	<!--按钮工具栏开始-->
	<div style="padding-bottom: 8px;">
		<table
			style="width: 100%; word-break: break-all; word-wrap: break-word;">
			<tbody>
				<tr>
					<td style="width: 5%;text-align: right;">商品名称</td>
					<td style="text-align: left;width: 5%;"><input ng-model="commodityName" ng-keyup="enterKeyup($event,2)"
						class="sp-input sp-input-md " type="text"></td>
					<td style="width: 5%;text-align: right;">商品分类</td>
					<td style="text-align: left;width: 20%;"><select ng-model="category1"
						class="sp-select sp-float-left"
						style="height: 30px; width: 100px;"
						ng-options="m.categoryName for m in firstLevelCategories"
						ng-change="onFirstLevelCategorychange(category1)">
							<option value="">一级品类名称</option>
					</select> <select ng-model="category2" class="sp-select sp-float-left"
						style="height: 30px; width: 100px;"
						ng-options="m.categoryName for m in secondLevelCategories"
						ng-change="onSecondLevelCategorychange(category2)">
							<option value="">二级品类名称</option>
					</select> <select ng-model="category3" class="sp-select sp-float-left"
						style="height: 30px; width: 100px;"
						ng-options="m.categoryName for m in thirdLevelCategories"
						ng-change="onThirdLevelCategorychange(category3)">
							<option value="">三级品类名称</option>
					</select></td>
					<td style="width: 5%;text-align: right;">评价时间</td>
					<td style="text-align: left;width: 15%;"><input id="createStartTime"
						readonly="readonly" ng-model="createStartTime"
						name="createStartTime" class="sp-input sp-input-default"
						datetimepicker type="text" style='width: 23%; margin-right: 2px;' />
						~ <input id="createEndTime" readonly="readonly"
						ng-model="createEndTime" name="createEndTime"
						class="sp-input sp-input-default" datetimepicker type="text"
						style='width: 23%; margin-right: 2px;' /></td>
				</tr>
				<tr>
					<td><!-- 商品ID --></td>
					<td style="text-align: left;"><!-- <input ng-model="commodityCode"
						class="sp-input sp-input-md" type="text"> --></td>
					<td><!-- 评分等级 --></td>
					<td style="text-align: left;"><!-- <select ng-model="commentLv" class="sp-select sp-float-left"
						style="height: 30px; width: 100px;"
						ng-options="m.Name for m in commentLevel">
					</select> -->
					</td>
					<td><!-- 评价关键字 --></td>
					<td style="text-align: left;"><!-- <input ng-model="commentKeyWordC"
						class="sp-input sp-input-md " type="text"> --></td>
				</tr>
				<tr>
					<td><!-- 手机号码 --></td>
					<td style="text-align: left;"><!-- <input ng-model="commentUserTel"
						class="sp-input sp-input-md " type="text"> --></td>
					<td><!-- 订单编号 --></td>
					<td style="text-align: left;"><!-- <input ng-model="commentOrderNoC"
						class="sp-input sp-input-md " type="text"> --></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<div style="padding-left: 35%;">
			<button type="button" class="sp-btn sp-btn-search-md"
			style="padding: 8px 27px 8px 27px;margin-left: 7px;margin-top: 5px;"
				ng-click="searchCount('10')">查询</button>
			<button type="button" class="sp-btn sp-btn-gray-md"
				ng-click="clearCount()">重置</button>
		</div>
	</div>
	<!--按钮工具栏结束-->
	<!--绑定表格开始-->
	<p style="color: red">*选择商品分类后，其它条件将不会生效！
	<!-- <input type="checkbox" id="selectTypr" ng-model="selectTypr" />选择查询类别(分类/详细) --></p>
	
	<table id="tb" class="sp-table">
		<tr>
			<!--表头，这只排序字段，-->
			<th class="th-width-md">商品分类</th>
			<th class="th-width-mini">序号</th>
			<th class="th-width-sm">配送好评
					<button ng-click="searchCount('1')" ng-if = "ExpressGoodStatus == 0"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/icon_2.png'>
					</button>
					<button ng-click="searchCount('2')" ng-if = "ExpressGoodStatus == 1"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/up.png'>
					</button>
			</th>
			<th class="th-width-sm">配送差评
					<button ng-click="searchCount('3')" ng-if = "ExpressBadStatus == 0"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/icon_2.png'>
					</button>
					<button ng-click="searchCount('4')" ng-if = "ExpressBadStatus == 1"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/up.png'>
					</button>
			</th>
			<th class="th-width-sm">包装好评
					<button ng-click="searchCount('5')" ng-if = "PackageGoodStatus == 0"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/icon_2.png'>
					</button>
					<button ng-click="searchCount('6')" ng-if = "PackageGoodStatus == 1"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/up.png'>
					</button>
			</th>
			<th class="th-width-sm">包装差评
					<button ng-click="searchCount('7')" ng-if = "PackageBadStatus == 0"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/icon_2.png'>
					</button>
					<button ng-click="searchCount('8')" ng-if = "PackageBadStatus == 1"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/up.png'>
					</button>
			</th>
			<th class="th-width-sm">商品好评
					<button ng-click="searchCount('9','0')" ng-if = "CommodityGoodStatus == 0"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/icon_2.png'>
					</button>
					<button ng-click="searchCount('10','0')" ng-if = "CommodityGoodStatus == 1"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/up.png'>
					</button>
			</th>
			<th class="th-width-sm">商品差评
					<button ng-click="searchCount('11','1')" ng-if = "CommodityBadStatus == 0"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/icon_2.png'>
					</button>
					<button ng-click="searchCount('12','1')" ng-if = "CommodityBadStatus == 1"
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/up.png'>
					</button>
			</th>
			<th class="th-width-sm" ng-show="commodityShow">操作</th>
		</tr>
		<!--品类分页，-->
		<tr ng-if="loading == 1"><td colspan="8" style="font-size: large;">读取中，请稍后....</td></tr>
		<tr ng-repeat="x in categoryListPage"
			ng-show="categoryShow && loading == 0">
			<td style="height: 30px">{{x.categoryName}}</td>
			<td style="height: 30px"><p ng-show="$index != null">{{(pageSize * (pagination.pageNum - 1))+ $index + 1}}</p></td>
			<td style="height: 30px" class="autobreak">{{x.commodityComment.expressGoodCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityComment.expressBadCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityComment.packageGoodCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityComment.packageBadCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityComment.commodityGoodCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityComment.commodityBadCount}}</td>
			</td>
		</tr>
		<!--商品分页，-->
		<tr ng-if="loading == 2"><td colspan="9" style="font-size: large;">读取中，请稍后....</td></tr>
		<tr ng-repeat="x in commodityListPage"
			ng-show="commodityShow && loading == 0">
			<td style="height: 30px">{{x.commodityName}}</td>
			<td style="height: 30px"><p ng-show="$index != null">{{(pageSize * (pagination.pageNum - 1))+ $index + 1}}</p></td>
			<td style="height: 30px" class="autobreak">{{x.commodityCommentDomain.expressGoodCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityCommentDomain.expressBadCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityCommentDomain.packageGoodCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityCommentDomain.packageBadCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityCommentDomain.commodityGoodCount}}</td>
			<td style="height: 30px" class="autobreak">{{x.commodityCommentDomain.commodityBadCount}}</td>
			<td style="height: 30px" class="autobreak">
				<input ng-if="x.commodityCommentDomain.expressGoodCount+x.commodityCommentDomain.expressBadCount+
				x.commodityCommentDomain.packageGoodCount+x.commodityCommentDomain.packageBadCount+
				x.commodityCommentDomain.commodityGoodCount+x.commodityCommentDomain.commodityBadCount != 0"
				class="sp-btn sp-btn-gray-sm" value="详细" type="button" ng-click="showCommentInfo(x)">
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
			<td style="height: 30px" ng-show="commodityShow"></td>
		</tr>
	</table>
	<!--绑定表格结束-->
</div>