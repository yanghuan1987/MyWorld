<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>
<% String path = application.getInitParameter("static-file"); %>
<html lang="zh-CN">
<head>
<title>CMS-菜单配置</title>
<link rel="SHORTCUT ICON" href="<%=path%>/img/cms/favicon.ico" />
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link href="<%=path%>/css/css.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/table.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/form.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/menu.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/head.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/layout.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/pagination.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/cms/cms.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="menuApp" ng-cloak ng-controller="menuctrl">
	<jsp:include page="/WEB-INF/pages/common/head.jsp"></jsp:include>
	<div class="row">
		<jsp:include page="/WEB-INF/pages/common/menu.jsp"></jsp:include>
		<div class="sp-menu-right">
			<div id="content" class="sp-content">
				<div class="sp-page-title">
					<span>菜单配置</span>
				</div>
				

			<div class="sp-content-layout">
				<div style="text-align: right;">
					<input value="同步品类数据" type="button" class="sp-btn sp-btn-green-sm"
						ng-click="synchroCategoryInfo()">
				</div>
				<br>
				<div>
					<table class="sp-table">
						<tr><th class="th-width-md">菜单</th>
							<th class="th-width-sm">显示</th>
							<th class="th-width-sm">一级排序</th>
							<th class="th-width-sm">二级排序</th>
							<th class="th-width-sm">三级排序</th>
							<th class="th-width-md">商品编码</th>
							<th class="th-width-md last-th">操作</th></tr>
						<tr ng-repeat="x in menulist" ng-if="(!x.parent) || x.parent == showlevel2 || x.parent == showlevel3" style="border-bottom: 1px solid #fff;">
						<!-- ng-class="{'level1tr':x.level == level1,'level2tr':x.level == level2,'level3tr':x.level == level3}" -->
							<td ng-click="choseLevel($index)" style="text-align: left;" 
								ng-class="{'level1name':x.level == level1,'level2name':x.level == level2,'level3name':x.level == level3}"><img ng-if="!(x.parent == showlevel3 && x.parent)" src='<%=path%>/img/icon_2.png'/>&nbsp;&nbsp;<span>{{x.level}}</span>-{{x.name}}</td>
							<td style="text-align:center;"><input type="checkbox" ng-checked = "x.check" ng-model="x.check" name="showif" 
								ng-disabled="x.showflg" ng-show="x.showBox" ng-click="clickBox($index)" id="ckeck{{$index}}">
								</td>
								
							<td style="text-align:center;" id="combineTd1{{$index}}"><span ng-if="(!x.parent)"><input id="displayorder" type="text" ng-model="x.displayOrder" ng-class="{'sp-input sp-input-sm':!x.showflg}" 
								ng-disabled="x.showflg" size="4" style="background: none;text-align: center;" maxlength="3" ng-change="checkOrder($index)"></span></td>
							<td style="text-align:center;"><span ng-if="x.parent == showlevel2 && x.parent"><input id="displayorder" type="text" ng-model="x.displayOrder" ng-class="{'sp-input sp-input-sm':!x.showflg}" 
								ng-disabled="x.showflg" size="4" style="background: none;text-align: center;" maxlength="3" ng-change="checkOrder($index)"></span><span class="sp-tip" id="level1{{$index}}"></span></td>
							<td style="text-align:center;"><span  ng-if="x.parent == showlevel3 && x.parent"><input id="displayorder" type="text" ng-model="x.displayOrder" ng-class="{'sp-input sp-input-sm':!x.showflg}" 
								ng-disabled="x.showflg" size="4" style="background: none;text-align: center;" maxlength="3" ng-change="checkOrder($index)"></span><span class="sp-tip" id="level2{{$index}}"></span></td>
								
							<td style="text-align:center;"><span ng-show="!x.parent"><input type="button" ng-model="x.commodityCode" value="{{x.commodityCode}}" 
								ng-disabled="x.showflg" ng-class="{'sp-input sp-input-md':!x.showflg}" ng-click="getCommodityinfo($index)" style="background: none;"></span><span class="sp-tip" id="level3{{$index}}"></span></td>
							<td ng-show="!x.showflg"><input class="sp-btn sp-btn-gray-sm" id="saveMenu{{$index}}" type="button" value="保存" ng-click="savedata($index)"><input class="sp-btn sp-btn-red-sm" type="button" value="取消" ng-click="cancel($index)"></td>
							<td ng-show="x.showflg" class="last-td td-width-md"><input class="sp-btn sp-btn-black-sm" type="button" value="编辑" ng-click="selectmune($index)"></td>
						</tr>
					</table>
				</div>
			</div>

			</div>
		</div>
	</div>
	<jsp:include page="./menubottom.jsp"></jsp:include>
</body>

</html>