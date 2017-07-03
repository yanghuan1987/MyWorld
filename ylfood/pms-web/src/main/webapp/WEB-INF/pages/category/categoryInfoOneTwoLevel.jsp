<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div style="width: 100%;" id="oneCategory" ng-show="showLevelOneAndTwo">
	<div class="sp-content-bg">
		<div class="sp-panel">
			<div class="sp-panel-body">
				<p class="sp-p-title">
					<span ng-show="notEditAble">当前页面:查看{{addOneCategory}}品类</span>
					<span ng-show="!notEditAble">当前页面:{{addOrUpdate}}{{addOneCategory}}品类</span>
					<button ng-show="notEditAble" type="button" class="sp-btn sp-btn-gray-md" style="margin-top: -8px;" ng-click="editAll()">编辑</button>
				</p>
				<div class="sp-row-border row" style="min-height: 500px;border-bottom: 0;">
					<div style="margin-left: 33%;width: 33%;">
						<form ng-controller="categoryOneInfo" name="categoryForm" class="form-horizontal" role="form">
							<table class="autobreak" style="table-layout: fixed;">
								<tbody>
									<tr ng-show="showParentName && pcategoryName">
										<td style="width: 20%;text-align: right;">上级品类:</td>
										<td style="text-align: left;text-indent: 10px;width: 85%;">{{pcategoryName}}</td>
									</tr>
									<tr ng-show="notEditAble && !pcategoryName">
										<td style="width: 20%;text-align: right;"><span style="color: red" ng-show="!notEditAble">*</span> 品类名称:</td>
										<td ng-show="notEditAble" style="text-align: left;text-indent: 10px;width: 85%;">{{category.categoryName}}</td>
									</tr>
									<tr ng-show="notEditAble && pcategoryName">
										<td style="width: 20%;text-align: right;"><span style="color: red" ng-show="!notEditAble">*</span> 品类名称:</td>
										<td ng-show="notEditAble" style="text-align: left;text-indent: 10px;width: 85%;">{{category.categoryName}}</td>
									</tr>
									<tr ng-show="!notEditAble">
										<td style="width: 20%;text-align: right;"><span style="color: red" ng-show="!notEditAble">*</span> 品类名称:</td>
										<td ng-show="!notEditAble" style="text-align: left;text-indent: 10px;width: 85%;">
											<input type="text" class="sp-input sp-input-lg" name="cname" ng-blur="checkCategoryName(Five)" ng-change="checkCategoryNameChange()" ng-model="category.categoryName" placeholder="品类名称" style="margin-top: 3px; margin-left: 0px;" ng-disabled="notEditAble" />
											<span style="color: red" ng-show="categoryNameSize">长度不能超过5个字符</span>
											<span style="color: red" ng-show="categoryNameIsHave">名称已存在</span>
										</td>
										<td ng-show="notEditAble" style="text-align: left;text-indent: 10px;width: 85%;">{{category.categoryName}}</td>
									</tr>
									<tr>
										<td style="width: 20%;text-align: right;">
											<span style="color: red" ng-show="!notEditAble">*</span> 品类编码:
										</td>
										<td ng-show="!notEditAble && category.id == null" style="text-align: left;text-indent: 10px;width: 85%;"><div style="color: red">系统自动生成</div></td>
										<td ng-show="notEditAble || category.id != null" style="text-align: left;text-indent: 10px;width: 85%;">{{category.categoryCode}}</td>
									</tr>
								</tbody>
							</table>
							<div ng-show="!notEditAble && buttonType == 1" class="form-group">
								<div style="margin-top: 100px;">
									<button type="button" class="sp-btn sp-btn-gray-md" ng-click="submitCategoryForm()" ng-disabled="notEditAble">确定</button>
									<button type="button" class="sp-btn sp-btn-gray-md" ng-click="returnCategoryForm()">返回</button>
								</div>
							</div>

							<div ng-show="!notEditAble && buttonType == 0" class="form-group">
								<div style="margin-top: 100px;margin-left: 5%">
									<button type="button" class="sp-btn sp-btn-gray-md" ng-click="submitCategoryForm()" ng-disabled="notEditAble">确定</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>