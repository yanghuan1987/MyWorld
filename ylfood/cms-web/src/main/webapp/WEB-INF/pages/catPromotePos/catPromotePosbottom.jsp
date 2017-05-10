<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<% String path = application.getInitParameter ("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id='popUp' class='sp-popup sp-popup-normal'>
	<div class='sp-pop-header'>
		<div id="pop-logo" class="sp-pop-logo">
			<span>弹出框标题</span>
		</div>
		<div class="sp-pop-exit">
			<a href="javascript:exitPop('catPromotePospopUp');"><img
				src='<%=path%>/img/cross.png'></a>
		</div>
	</div>
	<div class='sp-pop-content'>
		<input type='button' value='提交' class='sp-btn sp-btn-gray-sm'
			onclick="javascript:exitPop('catPromotePospopUp');" />
	</div>
</div>

<div id='catPromotePospopUpHg' class='sp-popup sp-popup-hg'style="overflow: auto;">
	<div class='sp-pop-header'>
	    <div id="pop-logo" class="sp-pop-logo"><span>编辑商品</span></div>
		<div id="pop-logo" class="sp-pop-logo">
			<!-- <span>弹出框标题</span> -->
		</div>
		<div class="sp-pop-exit">
			<a href="javascript:exitPop('catPromotePospopUpHg');"><img
				src='<%=path%>/img/cross.png'></a>
		</div>
	</div>
	<div class='sp-pop-content'>
		<div id="content" class="sp-content">
			<div class="sp-content-layout" >
				<form class="sp-form-horizontal sp-form-right">
					
					商品编码：<input type='textarea' ng-model="commodityCode" class="sp-input"/>
					商品名称:<input type='textarea' ng-model="commodityName" class="sp-input"/>	
					<input type="button" ng-click="searchCommotyByCode()" class="sp-btn sp-btn-search-md" value="查询"/>
						
				</form>
				<br>
				<table class="sp-table">
					<thead>
						<tr>
							<th class="th-width-mini">序号</th>
							<th class="th-width-sm">所属类别</th>
							<th class="th-width-sm">商品编码</th>
							<th class="th-width-md">商品名称</th>
							<th class="th-width-sm">商品重量</th>
							<th class="last-th th-width-sm">操作</th>
						</tr>
					</thead>
					<tbody ng-if="commodityList">
						<tr ng-repeat="x in commodityList">
							<td>{{$index+1}}</td>
							<td>{{x.category.categoryName}}</td>
							<td>{{x.commodityCode}}</td>
							<td>{{x.commodityName}}</td>
							<td>{{x.commodityWeight}}&nbsp;{{x.commodityWeightUnit}}</td>
							<td class="last-td"><input type="button"
								class="sp-btn sp-btn-gray-sm" value="选择"
								ng-click="choseCommodity($index)" /></td>
						</tr>
<!-- 						<tr ng-repeat="y in tempList">
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
							<td style="height: 30px"></td>
						</tr> -->
					</tbody>
				</table>
				<!-- 分页标签  -->
				<div>
					<div style="height: 10px;"></div>
					
					<div id="test_table"></div>
					<div style="clear: both;">&nbsp;</div>
					<spfood-pagination conf="pagination"></spfood-pagination>
				</div>
				<br>
				<br>
				<table style="width: 100%;table-layout: fixed;">
					<tr>
						<td style="width: 7%;">商品编码:</td>
						<td style="width: 20%;">{{choseCommodityCode}}</td>
						<td style="width: 6%;">商品名称:</td>
						<td style="width: 25%;">{{choseCommodityName}}</td>
						<td style="width: 4%;">排序:</td>
						<td style="width: 20%;"><input type="text" class="sp-input sp-input-md" 
						ng-model="displayOrder" maxlength="3" ng-pattern="/^[1-9]\d*$/" required></td>
					</tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input type="button" class="sp-btn sp-btn-gray-md" value="保存"
						ng-click="savedate()"> <input type="button"
						class="sp-btn sp-btn-gray-md" value="取消" ng-click="cancel()">
				</div>

			</div>
		</div>
	</div>
</div>
<div id='catPromotePospopHint' class='sp-popup sp-popup-sm'>
	   <div class="sp-pop-alert">{{errorMessage}}</div>
</div>
  <div id='popDialogue'class='sp-popup sp-popup-md'>
    <div class='sp-pop-header'>
    <div id="pop-logo" class="sp-pop-logo"><span>确认提示框</span></div>
	<div class="sp-pop-exit"><a href="javascript:exitPop('popDialogue');"><img src='<%=path%>/img/cross.png'></a></div>
	</div>
	<div class='sp-pop-content'>
	   <div class="sp-pop-title">你确定要删除吗?</div>
		<div class="sp-pop-btn">
            <input type='button' value='确定' class='sp-btn sp-btn-gray-md' ng-click="deleteYes()" />
            <input type="button" value='取消' class='sp-btn sp-btn-gray-md' onclick="exitPop('popDialogue');" />
        </div>
		
	</div>
  </div>

<script type="text/javascript" src="<%=path%>/js/popup.js"></script>

<script type="text/javascript" src="<%=path%>/js/pagination.js"></script>
