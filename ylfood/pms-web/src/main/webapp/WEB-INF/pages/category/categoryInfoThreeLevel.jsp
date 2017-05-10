<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = application.getInitParameter("static-file");
%>
<div style="padding-left: 15px;width: 100%;" id="threeCategory"
	ng-controller="categoryThreeInfo" ng-show="showLevelThree">
	<div class="sp-content-bg" style="width: 99%;">
		<div class="sp-panel">
			<div style="padding: 10px 15px;border-bottom: 1px solid transparent;background-color: #f5f5f5;border-color: #ddd;"></div>
			<div class="sp-panel-body">
				<p class="sp-p-title">
					<span>当前页面:{{categoryCname}}</span>
					<button ng-show="notEditAble" type="button"
						class="sp-btn sp-btn-gray-md" style="margin-top: -8px;"
						ng-click="editAll(3)">编辑</button>
				</p>
				<div class="sp-row-border row">
					<div style="margin-left: 33%;width: 33%;">
						<form class="form-horizontal" role="form" name="threeForm">
						
						<table class="autobreak" style="table-layout: fixed;">
							<tbody>
								<tr>
									<td style="width: 20%;text-align: right;">上级品类:</td>
									<td style="text-align: left;text-indent: 10px;width: 85%;">{{pcategoryName}}--{{pcategory2Name}}-->{{category.categoryName}}</td>
								</tr>
								<tr>
									<td style="width: 20%;text-align: right;"><span ng-show="!notEditAble" style="color: red;">*</span>品类名称:</td>
									<td style="text-align: left;text-indent: 10px;width: 85%;" ng-show="!notEditAble">
										<input type="text" class="sp-input sp-input-lg" name="cname"
										ng-blur="checkCategoryName()" ng-model="category.categoryName"
										placeholder="品类名称" ng-disabled="notEditAble" /> <br>
					 					<span style="color: red;margin-left: 15px;" ng-show="categoryNameSize">品类名称长度大于128个字符</span>
										<span style="color: red;margin-left: 15px;" ng-show="categoryNameIsHave">品类名称已存在</span>
									</td>
									<td style="text-align: left;text-indent: 10px;width: 85%;" ng-show="notEditAble">{{category.categoryName}}</td>
								</tr>
								<tr>
									<td style="width: 20%;text-align: right;"><span style="color: red;"></span>品类别名:</td>
									<td style="text-align: left;text-indent: 10px;width: 85%;" ng-show="!notEditAble">
										<input type="text" class="sp-input sp-input-lg" name="acname"
										ng-model="category.categoryAnotherName" placeholder="品类别名"
										ng-maxlength="128" ng-disabled="notEditAble" /> <br>
										<span style="color: red;margin-left: 15px;" ng-show="threeForm.acname.$error.maxlength">品类别名长度大于128个字符</span>
									</td>
									<td style="text-align: left;text-indent: 10px;width: 85%;" ng-show="notEditAble">{{category.categoryAnotherName}}</td>
								</tr>
								<tr>
									<td style="width: 20%;text-align: right;">编码:</td>
									<td style="text-align: left;text-indent: 10px;width: 85%;" ng-show="notEditAble || category.id != null">{{category.categoryCode}}</td>
								</tr>
								<tr ng-show="!notEditAble">
								<td colspan="2">
									<button type="button" ng-click="proclick(null)"
										class="sp-btn sp-btn-gray-md"
										style="width: 88%;">新增属性</button></td>
								</tr>
							</tbody>
						</table>
						</form>
						<br>
					</div>
				</div>
				<br>
				<br>
				<div class="row sp-div-none">
					<table class="sp-table">
						<thead>
							<th class="th-width-mini">序号</th>
							<th class="th-width-md">属性名</th>
							<th class="th-width-md">属性描述</th>
							<th class="th-width-sm">筛选条件</th>
							<th class="th-width-md">属性值</th>
							<th class="th-width-sm last-th">操作</th>
						</thead>
						<tbody>
							<tr ng-repeat="pca in category.productCategoryPropertys">
								<td>{{$index+1}}</td>
								<td>{{pca.categoryPropertyName}}</td>
								<td>{{pca.categoryPropertyDescription}}</td>
								<td><span ng-if="pca.selectFlag == 1">是</span><span
									ng-if="pca.selectFlag == 0">否</span></td>
								<td><span
									ng-repeat="pcvalue in pca.productCategoryPropertyValues"><span
										ng-if="$index>0">、</span>{{pcvalue.categoryPropertyValue}}</span></td>
								<td><button type="button" ng-show="!notEditAble"
										class="sp-btn sp-btn-gray-md" ng-click="proclick(pca)">编辑</button>
									<button type="button" ng-show="!notEditAble"
										class="sp-btn sp-btn-gray-md" ng-click="proAdds(pca)">删除</button>
								</td>
							</tr>
						</tbody>
					</table>
					<br>
					<button type="button" ng-show="!notEditAble"
						class="sp-btn sp-btn-gray-md"
						style="margin-left: 40%;width: 20%;" ng-click="submitCategoryThreeForm()" ng-disabled="notEditAbleThree">完成</button>
				</div>
			</div>
		</div>
	</div>


	<!-- （Modal） -->
	<div id='propertypopUpHg' class='sp-popup sp-popup-lg'
		style="overflow: auto;">
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<!-- <span>弹出框标题</span> -->
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('propertypopUpHg');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content'>
			<div id="content" class="sp-content" style="border: 0;">
				<div class="sp-page-title" style="margin-left: 0;">
					<div style="text-align: center; width: 100%;">属性面板</div>
				</div>
				<div class="sp-content-layout">
					<!--面板内容开始-->
					<form role="form" name="threePropertyForm">
					
					<table style="width: 100%;">
						<tbody>
							<tr style="height: 50px;">
								<td>
									<span style="color: red" ng-show="!notEditAble || !notEditProAble">*</span>
										属性名:
								</td>
								<td ng-show="!notEditAble || !notEditProAble" style="text-align: left;">
									<input type="text" class="sp-input sp-input-lg"
										style="width: 70%; margin-left: 0px;" name="pname"
										ng-model="propert.categoryPropertyName" required
										placeholder="属性名" ng-maxlength="128" ng-disabled="disEdit" />
									<span style="color: red"
										ng-show="threePropertyForm.pname.$error.maxlength">长度小于128个字符</span>
								</td>
								<td ng-show="notEditAble && notEditProAble">{{propert.categoryPropertyName}}</td>
							</tr>
							<tr>
								<td>属性描述:</td>
								<td ng-show="!notEditAble || !notEditProAble" style="text-align: left;">
									<textarea class="sp-text" style="width: 70%;"
										class="form-control" name="pdesc"
										ng-model="propert.categoryPropertyDescription"
										placeholder="属性描述" ng-maxlength="128"></textarea>
									<span style="color: red"
										ng-show="threePropertyForm.pdesc.$error.maxlength">长度小于128个字符</span>
								</td>
								<td ng-show="notEditAble && notEditProAble">{{propert.categoryPropertyDescription}}</td>
							</tr>
							<tr>
								<td> 
									<span style="color: red" ng-show="!notEditAble || !notEditProAble">*</span>
									筛选条件:
								</td>
								<td style="text-align: left;">
									<input name="selectFlag" type="radio" value="1" ng-model="propert.selectFlag"
										 class="sp-radio" />是
									<input name="selectFlag" type="radio" value="0"
										ng-model="propert.selectFlag" class="sp-radio"/>否
								</td>
							</tr>
							<tr style="height: 50px;">
								<td>
									<span style="color: red" ng-show="!notEditAble || !notEditProAble">*</span>
									属性值:
								</td>
								<td ng-show="!notEditAble || !notEditProAble" style="text-align: left;">
								<input type="text" class="sp-input sp-input-lg sp-input-left"
										style="width: 70%; margin-left: 0px;" name="cpvalues"
										ng-model="newvalues" required placeholder="属性值"
										ng-maxlength="128" /> 
									<i class="pms-icon-jh" style="margin-left: 5px;"> 
									<input type="button" style="margin: 0px;width: 100%; height: 100%;opacity: 0;"
										ng-click="properAdds()"
										ng-disabled="threeForm.cpvalues.$invalid" /></i> <span
										style="color: red"
										ng-show="threePropertyForm.cpvalues.$error.maxlength">长度小于128个字符</span></td>
							</tr>
							<tr ng-repeat="pcvalue in propert.productCategoryPropertyValues" style="height: 35px;">
								<td></td>
								<td style="text-align: left;" >
									<input type="button" class="sp-input sp-input-lg sp-input-left"
										style="width: 70%; margin-left: 0px;"
										value="{{pcvalue.categoryPropertyValue}}"
										ng-click="editPropertyValue(pcvalue.categoryPropertyValue,$index)"
										ng-disabled="disEdit" /> 
										
										<i class="pms-icon-jhh" style="margin-left: 5px;"> 
										<input type="button" style="margin: 0px;width: 100%; height: 100%;opacity: 0;"
										ng-disabled="notEditAble && notEditProAble"
										ng-click="properMove(pcvalue.categoryPropertyValue)" /></i></td>
							</tr>
						</tbody>
					</table>
					</form>
					<div style="text-align: center;">
						<button type="button" class="sp-btn sp-btn-gray-md "
							ng-click="properAdd()">完成</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 模态框（Modal） -->
	<div id='popDialoguethree' class='sp-popup sp-popup-md'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<span>提示信息</span>
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('popDialoguethree');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content'>
			<div class="sp-pop-title" style="color: red;">{{errorMessage}}
			</div>
			<div class="sp-pop-btn">
				<input type="button" class="sp-btn sp-btn-gray-md "
					onclick="exitPop('popDialoguethree');" value="关闭"> <input
					type="button" class="sp-btn sp-btn-gray-md "
					ng-click="updateAttr()" value="确认">
			</div>
		</div>
	</div>
</div>