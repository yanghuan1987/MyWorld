<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>CMS-内容管理系统</title>
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

<link href="<%=path%>/css/cms/cms.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="advertisementModule" ng-controller="advertisementCtrl">
	<jsp:include page="/WEB-INF/pages/common/head.jsp"></jsp:include>

	<div class="row">
		<jsp:include page="/WEB-INF/pages/common/menu.jsp"></jsp:include>
		<div class="sp-menu-right">

			<div id="content" class="sp-content">
				<div class="sp-page-title">
					<span>Banner配置</span>
				</div>
				<div class="sp-content-layout">
					<div>
						<div style="float: left;width: 50%;margin-bottom: 20px;"><select class="sp-select sp-select-md" ng-model="defaultValue" ng-change="changeSlideAdsPosition()">
						    <option class="sp-option-select" ng-repeat="slideAdsPosition in slideAdsPositionList" value="{{slideAdsPosition.id}}">{{slideAdsPosition.name}}</option>  
						 </select></div>
						 <div style="text-align: right;"><input type="button" value="添加banner" style="background-color: #769a23" class='sp-btn sp-btn-green-sm'
							ng-click="add()" /></div>
					 </div>
					 <br>
				<div>
					<table class="sp-table">
					 <thead>
					    <tr>
						<th class="th-width-mini">序号</th>
						<th class="th-width-md">图片</th>
						<th class="th-width-hg">URL地址</th>
						<th class="th-width-md">排序</th>
						<th class="last-th th-width-md">操作</th>
						</tr>
					  </thead>
					   <tbody>
						<tr ng-repeat="advertisement in advertisementList">
							<td>{{$index+1}}</td>
							<td><img alt="没有图片" ng-src="{{advertisement.imageUrl}}" style="width: 230px; height: 50px;"></td>
							<td>{{advertisement.targetUrl}}</td>
							<td>{{advertisement.displayOrder}}</td>
							<td><input class="sp-btn sp-btn-black-sm" ng-click="edit($index)" type="button" value="编辑" />
								<input class="sp-btn sp-btn-red-sm" ng-click="deleteButton($index)" type="button" value="删除" />
							</td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>

	<!---弹出框-普通---->
	<div id='advertisementPopUp' class='sp-popup sp-popup-normal'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<span ng-if="!addAdvertisement.id">添加banner</span>
				<span ng-if="addAdvertisement.id">编辑banner</span>
			</div>
			<div class="sp-pop-exit">
				<a ng-click="close()"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>

		<div class='sp-pop-content'>
			<form enctype="multipart/form-data">
				<table class="sp-pop-table" style="margin-top: 30px;">
					<tr>
						<td class="left-td" style="height: 90px"><label>图片:</label></td>
						<td class="right-td">
							<div style="width: 150px;height: 40px;text-align: center;margin-bottom: 5px;">
								<span ng-if="!addAdvertisement.id"><img id="advertisementPicture" style="width: 200px;height: 50px;"
											ng-src="{{item.pictureAddress}}" /></span>
								<span ng-if="addAdvertisement.id"><img id="advertisementPicture" style="width: 200px;height: 50px;"
										ng-src="{{addAdvertisement.imageUrl}}" /></span></div>
							
						</td>
						<td style="text-align: left;"><input type="file" id="oneInput" file-model="images" name="oneinput"
								onchange="angular.element(this).scope().img_upload(this.files)" style="color: white;display: none;" />
							<input type="button" class="sp-btn sp-btn-black-sm" value="上传" onclick="oneInput.click()" />
						</td>
					</tr>
					<tr>
						<td class="left-td"><label>URL地址:</label></td>
						<td class="right-td" colspan="2"><input type="text" placeholder="URL地址"
							ng-model="addAdvertisement.targetUrl"
							class="sp-input sp-input-default" style="width: 320px" /></td>
					</tr>
					<tr>
						<td class="left-td"><label>排序:</label></td>
						<td class="right-td" colspan="2"><input type="text" placeholder="排序"
							ng-model="addAdvertisement.displayOrder"
							class="sp-input sp-input-default" style="width: 320px" /></td>
					</tr>
				</table>

				<div style="height: 70px;margin-top: 20px;text-align: center;">
					<input type='button' value='确定' class='sp-btn sp-btn-black-sm' ng-click="saveOrUpdate()" ng-disabled="isDisabled" /> 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value='取消' class='sp-btn sp-btn-black-sm' ng-click="cancel()" />
				</div>
			</form>
		</div>
	</div>

	<!---弹出框-提示框---->
	<div id="advertisementPopHint" class="sp-popup sp-popup-sm sp-pop-alert" style="display: none; left: 687.5px; top: 372.5px; cursor: default;">
		<div class="sp-pop-content">
		  <span>{{errorMessage}}</span>
		</div>
  	</div>
  	
	 <!---弹出对话框---->
	<div id='advertisementPopDialogue' class='sp-popup sp-popup-md'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<span>温馨提示</span>
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('advertisementPopDialogue');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content'>
			<div class="sp-pop-title">
				你确定要删除吗?
			</div>
			<div class='sp-pop-btn'>
				<input type='button' value='确定' class='sp-btn sp-btn-gray-md' ng-click="deleteAdvertisement()" />
				<input type="button" value='取消' class='sp-btn sp-btn-gray-md' onclick="exitPop('advertisementPopDialogue')" />
			</div>
		</div>
	</div>

	<script type="text/javascript" src="<%=path%>/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/lib/angular-1.5.9.js"></script>
	<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/advertisement/advertisement.js"></script>
</body>
</html>