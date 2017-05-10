<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%String path = application.getInitParameter("static-file");%>

<!---弹出框-超级大---->
<div id='popUpHg' class='sp-popup sp-popup-hg'>
	<div class='sp-pop-header'>
        <div id="pop-logo" class="sp-pop-logo"><span>编辑商品</span></div>
		<div class="sp-pop-exit">
			<a href="javascript:exitPop('popUpHg');"><img
				src='<%=path%>/img/cross.png'></a>
		</div>
	</div>

	<div class='sp-pop-content-lg' style="width: 96%; height: 90%; margin: 20px">
		<div class="showMsg2">
			商品编码：<input type='textarea' ng-model="commodityCode" class="sp-input" />&nbsp;&nbsp;&nbsp;&nbsp;
			商品名称:<input type='textarea' ng-model="commodityName" class="sp-input" />
			<input type="button" ng-click="select()"
				class="sp-btn sp-btn-search-md" value="查询" />

		</div>

		<table class="sp-table">
			<thead>
				<tr>
					<th class="th-width-mini">序号</th>
					<th class="th-width-sm">所属类别</th>
					<th class="th-width-sm">商品编码</th>
					<th class="th-width-hg">商品名称</th>
					<th class="th-width-mini">商品重量</th>
					<th class="last-th th-width-mini">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="commodity in pageInfo.result">
					<td>{{$index+1}}</td>
					<td>{{commodity.category.categoryName}}</td>
					<td>{{commodity.commodityCode}}</td>
					<td>{{commodity.commodityName}}</td>
					<td>{{commodity.commodityWeight}}{{commodity.commodityWeightUnit}}</td>
					<td><input ng-click="checkClick($index)" type="radio"
						name="commodity" /></td>
				</tr>
			</tbody>
		</table>
		<div>

			<div style="height: 10px;"></div>


			<div id="test_table"></div>
			<div style="clear: both;">&nbsp;</div>
			<spfood-pagination conf="pagination"></spfood-pagination>
			<div style="height: 10px;"></div>
		</div>

		<div class="sp-content-layout" style="margin-top:40px">
			<div class="showMsg" style="bottom: 80px">
				商品编码:<input type='text' class="sp-input sp-input-lg" readonly
					value="{{commodity.commodityCode}}" /> &nbsp;&nbsp;&nbsp; 商品名称:<input
					type='text' class="sp-input sp-input-lg" readonly
					value="{{commodity.commodityName}}" /> &nbsp;&nbsp;&nbsp; 排序:<input
					type='text' class="sp-input" ng-model="order" maxlength="2"
					ng-pattern="/^[1-9]\d*$/" required />
			</div>

			<br>
			<br>

			<div class="savaAndCancel">
				<input type="button" ng-click="save(order)"
					class="sp-btn sp-btn-gray-md" value="保存"> <input
					type="button" ng-click="cancel()" class="sp-btn sp-btn-gray-md"
					value="取消">
			</div>

		</div>
	</div>
</div>



<!--提示框-->
<div id='popHint' class='sp-popup sp-popup-sm'>
	<div class="sp-pop-alert" ng-model="tip">{{tip}}</div>
</div>

<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
<script type="text/javascript" src="<%=path%>/js/pagination.js"></script>