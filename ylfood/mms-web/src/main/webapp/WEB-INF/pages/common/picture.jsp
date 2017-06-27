<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<div ng-app="pictureApp" ng-controller="pictureCtrl">
	<!-- 头部图片st -->
	<div class="titlebar sp-mune-title">
		<i class="pms-icon-kz"></i><span style="color: red">*</span>头部图片上传
		<span style="color: red" ng-show="!isHeaderPictureSatified">头部至少上传{{headerPictureCheckSize}}张图片！</span>
	</div>
	<!--  -->
	<div style="margin-left: 120px; width: 70%;">
		<br>
		<!-- 采用angular循环的方式，对存入productPictures的图片进行展示 -->
		<div ng-repeat="item in thumbHeader"
			style="width: 15%; margin-left: 1.5%; margin-top: 3%;display: inline-block;">
				<span ng-if="item.pictureAddress">
					<button ng-click="img_del($index)" style="background-color: white;">
						<img src='<%=path%>/img/cross.png'>
					</button>
				</span>
			<img ng-src="{{item.pictureAddress}}" class="sp-picture-title" /> 
			<a href='javascript:void(0);'
			style="display: block;width: 100px;height: 25px;
			text-align: center;line-height: 21px;cursor: pointer;border-radius: 4px;margin-top: 5px;
			border: 1px solid #dedede;background: linear-gradient(#fff, #f4f4f4);">上传图片</a>
			<input type="file" id="one-input" name="{{$index}}" style="display: block;
			width: 100px;height: 25px;opacity: 0;margin-top: -30px;"
			onchange="angular.element(this).scope().img_upload(this)" />
		</div> 
	</div>
	<!-- 头部图片ed -->
	<br>
	<!-- 详情页图片st -->
	<div class="titlebar sp-mune-title">
		<i class="pms-icon-kz"></i> <span style="color: red">*</span>详情页图片上传
		<span style="color: red" ng-show="!isDetailPictureSatified">详情页至少要有一张图片！</span>
	</div>
	<div>
		<label ng-repeat="item in thumbDetail" style="width: 100%;">
			<span ng-if="item.pictureAddress" 
			ng-click="img_del_detail($index)" style="margin-top: 1%;margin-left: 77%;"><img style="height: 20px;" src='<%=path%>/img/cross.png'></span>
			<img ng-src="{{item.pictureAddress}}" class="sp-picture-detail" />
			<span ng-click="img_moveUp_detail($index)">向上移动</span> 
			<span ng-if="item.pictureAddress" ng-click="img_moveDown_detail($index)">向下移动</span>
			<br> <br> <br>
		</label>
		<a href='javascript:void(0);'style="display: block;width: 100px;height: 25px;
			text-align: center;line-height: 21px;cursor: pointer;border-radius: 4px;margin-top: 40px;
			border: 1px solid #dedede;background: linear-gradient(#fff, #f4f4f4);"
			ng-show="commodity.product != null || isProduct">上传图片</a>
		<input type="file" id="oneinput" name="oneinput" ng-show="commodity.product != null || isProduct"
			file-model="images" style="display: block;
			width: 100px;height: 25px;opacity: 0;margin-top: -30px;"
			onchange="angular.element(this).scope().img_upload_detail(this.files)" />
	</div>
	<!-- 详情页图片ed -->
</div>