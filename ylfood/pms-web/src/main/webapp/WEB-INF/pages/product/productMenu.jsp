<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<div class="sp-menu-left" style="height: 500px;">
	<p style="text-align: right; height: 23px; margin: 0px;; padding: 0px; border-bottom: 1px solid #ccc;">
		<span></span>
	</p>
	<div class="sp-menu" style="height: 500px;overflow-x: hidden;">
		<ul class="sp-nav-menu autobreak">
			<li class="sp-li-line" ng-click="showSearchPage()"Style="cursor:pointer;height:100%;background-color:{{colorones}};"><a>&nbsp;条件查询</a></li>
		</ul>
		<ul class="sp-nav-menu autobreak" ng-repeat="cat1 in categoryList">
				  <li class="sp-li-line" ng-click="updateOne(cat1)" id="{{cat1.categoryName}}" ng-if ="category.categoryName==cat1.categoryName" Style="cursor:pointer;height:100%;background-color:{{colorone}}" ><a ng-cloak><img src='<%=path%>/img/icon_2.png'>&nbsp;{{cat1.categoryName}}</a></li>
				  <li class="sp-li-line" ng-click="updateOne(cat1)" id="{{cat1.categoryName}}" ng-if ="category.categoryName!=cat1.categoryName" Style="cursor:pointer;height:100%;"><a ng-cloak><img src='<%=path%>/img/icon_2.png'>&nbsp;{{cat1.categoryName}}</a></li>
				  <ul class="sp-nav-menu-sec sp-nav-menu-hide" ng-show="spMenuShow == {{cat1.id}}" ng-repeat="cat2 in cat1.productCategorys">
					<li id="pack" ng-click="updateTwo(cat1.categoryName,cat2)" ng-if ="category.categoryName==cat2.categoryName" Style="cursor:pointer;height:100%;background-color:{{colorone}}"><a><img src='<%=path%>/img/icon_2.png'>&nbsp;<span ng-cloak>{{cat2.categoryName}}</span></a></li>
					<li id="pack" ng-click="updateTwo(cat1.categoryName,cat2)" ng-if ="category.categoryName!=cat2.categoryName" Style="cursor:pointer;height:100%;"><a><img src='<%=path%>/img/icon_2.png'>&nbsp;<span ng-cloak>{{cat2.categoryName}}</span></a></li>		
					<ul class="sp-nav-menu-third sp-nav-menu-hide" id="fruit-third" ng-show="spMenuShowT == {{cat2.id}}" ng-repeat="cat3 in cat2.productCategorys">
						<li ng-click="showThree(cat3.id,cat3.categoryName,cat3.categoryCode,cat3.showlevel,cat3.corder,cat3.categoryTypeFlag)" ng-if ="category.categoryName==cat3.categoryName" Style="cursor:pointer;height:100%;background-color:{{colorone}}"><a href="#productTable"><span ng-cloak>{{cat3.categoryName}}</span></a></li>
						<li ng-click="showThree(cat3.id,cat3.categoryName,cat3.categoryCode,cat3.showlevel,cat3.corder,cat3.categoryTypeFlag)" ng-if ="category.categoryName!=cat3.categoryName" Style="cursor:pointer;height:100%;"><a href="#productTable"><span ng-cloak>{{cat3.categoryName}}</span></a></li>					 
					</ul>
				  </ul>
		</ul>
	</div>
</div>
