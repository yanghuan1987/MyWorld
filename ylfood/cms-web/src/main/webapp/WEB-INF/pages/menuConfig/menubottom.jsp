<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>	
<div id='popUp' class='sp-popup sp-popup-normal'>
	<div class='sp-pop-header'>
		<div id="pop-logo" class="sp-pop-logo">
			<span>弹出框标题</span>
		</div>
		<div class="sp-pop-exit">
			<a href="javascript:exitPop('menupopUp');"><img
				src='<%=path%>/img/cross.png'></a>
		</div>
	</div>
	<div class='sp-pop-content'>
		<input type='button' value='提交' class='sp-btn sp-btn-gray-sm'
			onclick="javascript:exitPop('menupopUp');" />
	</div>
</div>

<div id='menupopUpHg' class='sp-popup sp-popup-hg'style="overflow: auto;">
	<div class='sp-pop-header'>
		<div id="pop-logo" class="sp-pop-logo">
			<span>选择商品</span>
		</div>
		<div class="sp-pop-exit">
			<a href="javascript:exitPop('menupopUpHg');"><img
				src='<%=path%>/img/cross.png'></a>
		</div>
	</div>
	<div class='sp-pop-content'>
		<div id="content" class="sp-content">
			<div class="sp-content-layout">
				<form class="sp-form-horizontal sp-form-right">
					商品编码:<input type='text' class="sp-input sp-input-md" ng-model="commodityCode" /> 商品名称:<input
						type='text' class="sp-input sp-input-md" ng-model="commodityName"/> <input type="button"
						class="sp-btn sp-btn-green-md" ng-click="searchCommotyByCode()" value="查询">
				</form>
				<br>
				<table class="sp-table">
					<thead>
						<tr>
							<th class="th-width-mini">序号</th>
							<th class="th-width-sm">所属类别</th>
							<th class="th-width-sm">商品编码</th>
							<th class="th-width-md">商品名称</th>
							<th class="th-width-sm">商品规格</th>
							<th class="th-width-sm">计量单位</th>
							<th class="last-th th-width-sm">操作</th>
						</tr>
					</thead>
					<tbody ng-if="commodityList">
					<tr ng-repeat="x in commodityList">
					<td>{{$index+1}}</td>
					<td>{{categoryName}}</td>
							<td>{{x.commodityCode}}</td>
							<td>{{x.commodityName}}</td>
							<td>{{x.commodityWeight}}&nbsp;{{x.commodityWeightUnit}}</td>
							<td>{{x.commodityWeightUnit}}</td>
							<td class="last-td"><input type="button"
								class="sp-btn sp-btn-gray-sm" value="选择" ng-click="choseCommodity($index)"/></td>
					</tr>
					</tbody>
				</table>
				
			<!-- 分页标签  -->
			<br>
			<div>
				<div style="height: 10px;"></div>
				<div style="clear: both;">
				</div>
				<div id="test_table"></div>
				<div style="clear: both;">&nbsp;</div>
				<spfood-pagination conf="pagination"></spfood-pagination>
			</div>
			</div>
		</div>
	</div>
</div>
   <div id='menupopHint'class='sp-popup sp-popup-sm'>
    
	<div class='sp-pop-content' style="text-align: center;">
	  <span>{{errorMessage}}</span>
	</div>
  </div>
    <div id='popDialogue' class='sp-popup sp-popup-md'>
        <div class='sp-pop-header'>
            <div id="pop-logo" class="sp-pop-logo">
                <span>温馨提示</span>
            </div>
            <div class="sp-pop-exit">
                <a href="javascript:exitPop('popDialogue');"><img
                    src='<%=path%>/img/cross.png'></a>
            </div>
        </div>
        <div class='sp-pop-content'>
            <div class="sp-pop-title">
           			 未添加商品编码<br>
                	你确定要保存吗?
            </div>
            <div class='sp-pop-btn'>
                <input type='button' value='确定' class='sp-btn sp-btn-gray-md' ng-click="confirm()" />
                <input type="button" value='取消' class='sp-btn sp-btn-gray-md' onclick="exitPop('popDialogue');" />
            </div>
        </div>
    </div>
    <!---弹出框-提示框---->
	<div id="menuPopHint" class="sp-popup sp-popup-sm sp-pop-alert" style="display: none; left: 687.5px; top: 372.5px; cursor: default;vertical-align:middle;">
		<div class="sp-pop-content">
		  <span>{{errorMessage}}</span>
		</div>
  	</div>
<script type="text/javascript"
	src="<%=path%>/js/lib/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/popup.js"></script>
<script type="text/javascript" src="<%=path%>/js/lib/angular-1.5.9.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menuConfig/menuConfig.js"></script>
<script type="text/javascript" src="<%=path%>/js/pagination.js"></script>