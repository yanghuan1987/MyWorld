<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<div class="sp-menu-left" style="background-color: white;overflow-y: auto;height: 560px;overflow-x: hidden;" ng-cloak>
	<p
		style="text-align: right; height: 23px; margin: 0px;; padding: 0px; border-bottom: 1px solid #ccc;">
		<span></span>
	</p>
	<ul class="sp-nav-menu autobreak" ng-repeat="cat1 in categoryList">
			  <li class="sp-li-line" ng-click="updateOne(cat1)" id="{{cat1.categoryName}}" ng-if ="category.categoryName==cat1.categoryName" Style="cursor:pointer;height:100%;background-color:{{colorone}};"><a><img src='<%=path%>/img/icon_2.png'>&nbsp;{{cat1.categoryName}}</a></li>
			  <li class="sp-li-line" ng-click="updateOne(cat1)" id="{{cat1.categoryName}}" ng-if ="category.categoryName!=cat1.categoryName" Style="cursor:pointer;height:100%;cursor:pointer"><a><img src='<%=path%>/img/icon_2.png'>&nbsp;{{cat1.categoryName}}</a></li>
			  <ul class="sp-nav-menu-sec sp-nav-menu-hide" ng-show="spMenuShow == {{cat1.id}}" ng-repeat="cat2 in cat1.productCategorys">
				<li id="pack" ng-click="updateTwo(cat1.categoryName,cat2)" ng-if ="category.categoryName==cat2.categoryName" Style="cursor:pointer;height:100%;background-color:{{colorone}}"><a><img src='<%=path%>/img/icon_2.png'>&nbsp;{{cat2.categoryName}}</a></li>
				<li id="pack" ng-click="updateTwo(cat1.categoryName,cat2)" ng-if ="category.categoryName!=cat2.categoryName" Style="cursor:pointer;height:100%;"><a><img src='<%=path%>/img/icon_2.png'>&nbsp;{{cat2.categoryName}}</a></li>	
				<ul class="sp-nav-menu-third sp-nav-menu-hide" id="fruit-third" ng-show="spMenuShowT == {{cat2.id}}" ng-repeat="cat3 in cat2.productCategorys">
					<li ng-click="updateThree(cat2.categoryName,cat3.id,cat3.categoryName,cat3.categoryCode,cat3.showlevel,cat3.corder)" ng-if ="category.categoryName==cat3.categoryName" Style="cursor:pointer;height:100%;background-color:{{colorone}}"><a>{{cat3.categoryName}}</a></li>
					<li ng-click="updateThree(cat2.categoryName,cat3.id,cat3.categoryName,cat3.categoryCode,cat3.showlevel,cat3.corder)" ng-if ="category.categoryName!=cat3.categoryName" Style="cursor:pointer;height:100%;"><a>{{cat3.categoryName}}</a></li>
				</ul>
				<button type="button" style="width: 100%;" ng-show="spMenuShowT == {{cat2.id}}" ng-click="addThree(cat1.categoryName,cat2.categoryName,cat2)" class="btn-block sp-menu-btn">新增三级分类</button>
			  </ul>
			  <button type="button" style="width: 100%;" ng-show="spMenuShow == {{cat1.id}}" ng-click="addTwo(cat1)" class="btn-block sp-menu-btn">新增二级分类</button>
	</ul>
	<button type="button" style="width: 100%;" ng-click="addOne()" class="btn-block sp-menu-btn">新增一级分类</button>
</div>
