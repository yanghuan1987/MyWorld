<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = application.getInitParameter("static-file");
%>
<div class="sp-content-layout">
	<!--按钮工具栏开始-->
	<div style="padding-bottom: 8px;">
		<table
			style="width: 100%; word-break: break-all; word-wrap: break-word;">
			<tbody>
				<tr>
					<td style="text-align: right;">商品名称</td>
					<td style="text-align: left;">{{commodityNameChoise}}</td>
					<td></td>
					<td style="text-align: left;"></td>
					<td></td>
					<td style="text-align: left;"></td>
					<td></td>
					<td style="text-align: left;"></td>
				</tr>
				<tr>
					<td style="text-align: right;">手机号码</td>
					<td style="text-align: left;"><input ng-model="commentUserTelD"
						class="sp-input sp-input-md " type="text"></td>
					<td style="text-align: right;">评价时间</td>
					<td style="text-align: left;"><input id="createStartTimeD"
						readonly="readonly" ng-model="createStartTimeD"
						name="createStartTimeD" class="sp-input sp-input-default"
						datetimepicker type="text" style='width: 20%; margin-right: 2px;' />
						~ <input id="createEndTimeD" readonly="readonly"
						ng-model="createEndTimeD" name="createEndTimeD"
						class="sp-input sp-input-default" datetimepicker type="text"
						style='width: 20%; margin-right: 2px;' /></td>
					<td><!-- 订单编号 --></td>
					<td style="text-align: left;"><!-- <input ng-model="commentOrderNoD"
						class="sp-input sp-input-md " type="text"> --></td>
					<td style="text-align: right;">评价关键字</td>
					<td style="text-align: left;"><input ng-model="commentKeyWordD"
						class="sp-input sp-input-md " type="text"></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align: left;"></td>
					<td></td>
					<td style="text-align: left;"></td>
					<td></td>
					<td style="text-align: left;"></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<div style="padding-left: 35%;">
			<button type="button" class="sp-btn sp-btn-search-md"
			style="padding: 8px 27px 8px 27px;margin-left: 7px;margin-top: 5px;"
				ng-click="searchCountD('99')">查询</button>
			<button type="button" class="sp-btn sp-btn-gray-md "
				ng-click="clearCountD()">重置</button>
		</div>
	</div>
	<!--按钮工具栏结束-->
	<!--绑定表格开始-->
	<div><label>总评论：{{commentCount.commodityGoodCount+commentCount.commodityMidCount+commentCount.commodityBadCount}}</label>
	<label style="padding-left: 20px;">好评：{{commentCount.commodityGoodCount}}</label>
	<label style="padding-left: 10px;">中评：{{commentCount.commodityMidCount}}</label>
	<label style="padding-left: 10px;">差评：{{commentCount.commodityBadCount}}</label></div>
	<table id="tb" class="sp-table">
		<!--评论分页，-->
		<tr>
			<!--表头，这只排序字段，-->
			<th class="th-width-md">商品名称</th>
			<th class="th-width-mini">序号</th>
			<th class="th-width-sm">商品编码</th>
			<th class="th-width-sm">配送评价
					<button ng-click="searchCountD('13')" ng-if = "ExpressStatus == 0" 
					style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/icon_2.png'>
					</button>
					<button ng-click="searchCountD('14')" ng-if = "ExpressStatus == 1" 
					 style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/up.png'>
					</button>
			</th>
			<th class="th-width-sm">包装评价
					<button ng-click="searchCountD('15')" ng-if = "PackageStatus == 0" 
					  style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/icon_2.png'>
					</button>
					<button ng-click="searchCountD('16')" ng-if = "PackageStatus == 1" 
					  style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/up.png'>
					</button>
			</th>
			<th class="th-width-sm">商品评价
					<button ng-click="searchCountD('17')" ng-if = "CommodityStatus == 0" 
					  style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/icon_2.png'>
					</button>
					<button ng-click="searchCountD('18')" ng-if = "CommodityStatus == 1" 
					  style="background-color: white;height: 15px;">
						<img src='<%=path%>/img/up.png'>
					</button>
			</th>
			<th class="th-width-sm">评价时间</th>
			<th class="th-width-sm">评价人</th>
			<th class="th-width-sm">具体评价</th>
			<th class="th-width-sm">操作</th>
		</tr>
		<tr ng-if="loading == 3"><td colspan="10" style="font-size: large;">读取中，请稍后....</td></tr>
		<tr ng-class="{'disableOn': x.commentStatus == 0}" ng-repeat="x in commentListPage" ng-show="commentShow && loading == 0">
			<td style="height: 30px">{{commodityNameChoise}}</td>
			<td style="height: 30px"><p ng-show="$index != null">{{(pageSize * (pagination.pageNum - 1))+ $index + 1}}</p></td>
			<td style="height: 30px" class="autobreak">{{x.commodityCode}}</td>
			
			<td style="height: 30px" class="autobreak">
			<img src='<%=path%>/img/pms/OneStar.png' ng-if="x.expressGrade == 1">
			<img src='<%=path%>/img/pms/TwoStars.png'  ng-if="x.expressGrade == 2">
			<img src='<%=path%>/img/pms/ThreeStars.png'  ng-if="x.expressGrade == 3">
			<img src='<%=path%>/img/pms/FourStars.png'  ng-if="x.expressGrade == 4">
			<img src='<%=path%>/img/pms/FiveStars.png'  ng-if="x.expressGrade == 5">
			</td>
			
			<td style="height: 30px" class="autobreak">
			<img src='<%=path%>/img/pms/OneStar.png' ng-if="x.packageGrade == 1">
			<img src='<%=path%>/img/pms/TwoStars.png'  ng-if="x.packageGrade == 2">
			<img src='<%=path%>/img/pms/ThreeStars.png'  ng-if="x.packageGrade == 3">
			<img src='<%=path%>/img/pms/FourStars.png'  ng-if="x.packageGrade == 4">
			<img src='<%=path%>/img/pms/FiveStars.png'  ng-if="x.packageGrade == 5">
			</td>
			
			<td style="height: 30px" class="autobreak">
			<img src='<%=path%>/img/pms/OneStar.png' ng-if="x.commodityGrade == 1">
			<img src='<%=path%>/img/pms/TwoStars.png'  ng-if="x.commodityGrade == 2">
			<img src='<%=path%>/img/pms/ThreeStars.png'  ng-if="x.commodityGrade == 3">
			<img src='<%=path%>/img/pms/FourStars.png'  ng-if="x.commodityGrade == 4">
			<img src='<%=path%>/img/pms/FiveStars.png'  ng-if="x.commodityGrade == 5">
			</td>
			
			<td style="height: 30px" class="autobreak">{{x.commentTime}}</td>
			<td style="height: 30px" class="autobreak">{{x.commentUserTel}}</td>
			<td style="height: 30px" class="autobreak" ng-if="x.showNewCommentContent == 2">{{x.commentContent}}</td>
			<td style="height: 30px" class="autobreak" ng-show="x.showNewCommentContent == 1">{{x.newCommentContent}}
			<a ng-click="showAll($index,'3')" href="#"
				style="cursor: pointer; color: #99C731; text-decoration: underline;"
				title="点击查看详情">[展开]</a>
			</td>
			<td style="height: 30px" class="autobreak" ng-show="x.showNewCommentContent == 3">{{x.commentContent}}
			<a ng-click="showAll($index,'1')" href="#"
				style="cursor: pointer; color: #99C731; text-decoration: underline;"
				title="点击查看详情">[收起]</a>
			</td>
			<td style="height: 30px" class="autobreak">
				<input class="sp-btn sp-btn-black-sm" value="屏蔽" type="button" 
				ng-if="x.commentStatus == 1" ng-click="editSta(x,0)" >
				
				<input class="sp-btn sp-btn-black-sm" value="已屏蔽" type="button" 
				ng-if="x.commentStatus == 0">
				
				<!-- <input class="sp-btn sp-btn-black-sm" value="回复" type="button" > -->
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