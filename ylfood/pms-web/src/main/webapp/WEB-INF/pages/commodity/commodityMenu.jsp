<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<div class="sp-menu-left" style="border-left: 5px solid #ebebeb;padding-left: 0px;width: 20%;">
	<jsp:include page="/WEB-INF/pages/common/menu.jsp"></jsp:include>
	<div class="sp-menu" style="overflow-x: hidden;width: 40%;background-color: #4b4b4b;float: left;">
		<ul class="sp-nav-menu autobreak">
			<li class="sp-li-line" ng-click="showSearchPage()"Style="cursor:pointer;height:100%;background-color:{{colorones}};"><a style="color: #fff;">&nbsp;条件查询</a></li>
		</ul>
		<ul class="sp-nav-menu autobreak" ng-repeat="cat1 in categoryList" style="background-color: #4b4b4b;">
				  <li class="sp-li-line" ng-click="updateOne(cat1)" ng-if ="category.categoryName==cat1.categoryName" Style="cursor:pointer;height:100%;background-color:{{colorone}};"><a style="color: #fff;"><img src='<%=path%>/img/pms/whiteDown.png'>&nbsp;{{cat1.categoryName}}</a></li>
				  <li class="sp-li-line" ng-click="updateOne(cat1)" ng-if ="category.categoryName!=cat1.categoryName" Style="cursor:pointer;height:100%;"><a style="color: #fff;"><img src='<%=path%>/img/pms/whiteDown.png'>&nbsp;{{cat1.categoryName}}</a></li>
		</ul>
	</div>
	<div class="sp-menu" style="overflow-x: hidden;width: 60%;">
		<ul class="sp-nav-menu autobreak" ng-repeat="cat1 in categoryList" style="background-color: #F5F5F5;">
				  <ul class="sp-nav-menu-sec sp-nav-menu-hide" ng-show="spMenuShow == {{cat1.id}}" ng-repeat="cat2 in cat1.productCategorys">
					<li id="pack" ng-click="updateTwo(cat1.categoryName,cat2)" ng-if ="category.categoryName==cat2.categoryName" Style="cursor:pointer;height:100%;background-color:{{colorone}};"><a><img src='<%=path%>/img/icon_2.png'>&nbsp;{{cat2.categoryName}}</a></li>			
					<li id="pack" ng-click="updateTwo(cat1.categoryName,cat2)" ng-if ="category.categoryName!=cat2.categoryName" Style="cursor:pointer;height:100%;"><a><img src='<%=path%>/img/icon_2.png'>&nbsp;{{cat2.categoryName}}</a></li>
					<ul class="sp-nav-menu-third sp-nav-menu-hide" id="fruit-third" ng-show="spMenuShowT == {{cat2.id}}" ng-repeat="cat3 in cat2.productCategorys" style="background-color: #F5F5F5;">
						<li ng-click="updateThree(cat3.id,cat3.categoryName,cat3.categoryCode,cat3.showlevel,cat3.corder)" ng-if ="category.categoryName==cat3.categoryName" Style="cursor:pointer;height:100%;background-color:{{colorone}};"><a>{{cat3.categoryName}}</a></li>
						<li ng-click="updateThree(cat3.id,cat3.categoryName,cat3.categoryCode,cat3.showlevel,cat3.corder)" ng-if ="category.categoryName!=cat3.categoryName" Style="cursor:pointer;height:100%;"><a>{{cat3.categoryName}}</a></li>					 
					</ul>
				  </ul>
		</ul>
	</div>
</div>