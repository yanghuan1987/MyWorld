<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>PMS-产品设计模块</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon"
	href="<%=path%>/img/pms/PMS_logo.ico"
	type="image/x-icon" />
<link href="<%=path%>/css/css.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/popup.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/menu.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/head.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/pagination.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=path%>/css/pms/pms.css">
</head>
<body ng-app="productApp" style="overflow: hidden;" ng-controller="productController" ng-cloak class="ng-cloak">
<input type="text" id="server" value="<%=path%>" style="display: none;"/>

	<!-- main container -->
	<div class="row">
		<jsp:include page="productMenu.jsp"></jsp:include>
		<jsp:include page="productTable.jsp"></jsp:include>
		<jsp:include page="productDetailSerch.jsp"></jsp:include>
		<!-- 自定义属性弹出编辑框  end-->
		<jsp:include page="compositeProductDetailAdd.jsp"></jsp:include>
		<jsp:include page="compositeProductDetailEditAndShow.jsp"></jsp:include>
		<jsp:include page="productDetailAdd.jsp"></jsp:include>
		<jsp:include page="productDetailEditAndShow.jsp"></jsp:include>
			<!-- 自定义属性弹出编辑框  start-->
			  <!---弹出框-普通---->
			  <div id='addNewPropertypopUp'class='sp-popup sp-popup-normal'>
			    <div class='sp-pop-header'>
			    <div id="pop-logo" class="sp-pop-logo"><span>自定义属性</span></div>
				<div class="sp-pop-exit"><a href="javascript:exitPop('addNewPropertypopUp');"><img src='<%=path%>/img/cross.png'></a></div>
				</div>
				<div class='sp-pop-content-normal' style="padding-top: 15%;overflow: hidden;">
						<div style="line-height: 30px;">
							<div style="width: 100%;padding-left: 25%;">
								<label><span
									style="color: red">*</span>属性名:</label> <label>
									<input type="text"
									name="productPropertyName" class="sp-input"
									ng-model="customerProductProperty.productPropertyName"
									placeholder="属性名" ng-maxlength="128" style="font-weight: normal;"/></label><br>
							</div>
							<div style="width: 100%;padding-left: 25%;">
								<label><span
									style="color: red">*</span>属性值:</label> <label>
									<input type="text"
									name="productProperty" class="sp-input"
									ng-model="customerProductProperty.productPropertyValue"
									placeholder="属性值" ng-maxlength="128" style="font-weight: normal;"/></label><br>
							</div>
						</div>
						<div ng-show="showcustomerProductProperty" style="width: 100%;padding-left: 25%;color:red">属性名和属性值不能为空！</div>
						<div style="text-align: center;">
							<button type="button" class="sp-btn sp-btn-gray-md"
								ng-click="onAddCustomerPropertyItemClick(customerProductProperty)">完成</button>
						</div>
				</div>
			  </div>
			<!-- 自定义属性弹出编辑框  end-->
			
			<!-- 自定义属性弹出编辑框  start-->
			  <!---弹出框-普通---->
			<form name="unitValueFrom" id="unitValueFrom"
				enctype="multipart/form-data">
			  <div id='addProductPackUnit'class='sp-popup sp-popup-normal'>
			    <div class='sp-pop-header'>
			    <div id="pop-logo" class="sp-pop-logo"><span>包装单位</span></div>
				<div class="sp-pop-exit"><a href="javascript:exitPop('addProductPackUnit');"><img src='<%=path%>/img/cross.png'></a></div>
				</div>
				<div class='sp-pop-content-normal' style="padding-top: 15%;overflow: hidden;">
						<table style="width: 100%;">
							<tbody>
								<tr>
									<td><span style="color: red">*</span> 包装单位:
									<input type="number" class="sp-input-sm sp-input"
										name="unitValueAdd" step="0.01" 
										ng-model="unitValueAdd" required 
										style="width: 80px;margin: 0;" placeholder="规格值" />
										{{productBuyUnitShow}}
									/
									<select ng-model="unitObjAdd" class="sp-select sp-select-sm" 
										name="unitObjAdd" style="height: 30px;"
										ng-options="x.optionName for x in specificationSecondValues"
										style="width: 100px;" placeholder="包装单位">
										</select></td>
								</tr>
							</tbody>
						</table>
						<div ng-show="checknull" style="width: 100%;padding-left: 25%;color:red">属性值不能为空！</div>
						<div ng-show="checkNumberIf" style="width: 100%;padding-left: 25%;color:red">属性值必须为大于0的数字！</div>
						<div ng-show="checkNumberOnepoint" style="width: 100%;padding-left: 25%;color:red">属性值最多为1位小数！</div>
						<div ng-show="checkNumberFormat" style="width: 100%;padding-left: 25%;color:red">基本采购单位是非重量单位时属性值必须为正整数！</div>
						<div ng-show="checkunitObjAdd" style="width: 100%;padding-left: 25%;color:red">请选择单位！</div>
						<br><br>
						<div style="text-align: center;">
							<button type="button" class="sp-btn sp-btn-gray-md"
								ng-click="onAddProductPackUnitItemClick(unitValueAdd,unitObjAdd)">完成</button>
						</div>
				</div>
			  </div>
			  </form>
	</div>
	<jsp:include page="../common/bottom.jsp"></jsp:include>
	<!-- 防止重复提交专用-->
	<div id='submitInfo' class='sp-popup sp-popup-md'>
		<div class='sp-pop-header'>
			<div id="pop-logo" class="sp-pop-logo">
				<span>提交中</span>
			</div>
			<div class="sp-pop-exit">
				<a href="javascript:exitPop('submitInfo');"><img
					src='<%=path%>/img/cross.png'></a>
			</div>
		</div>
		<div class='sp-pop-content-normal' style="padding-top: 15%;">

			<div style="width: 100%; text-align: center;">{{message}}</div>
			<br>
		</div>
	</div>
	<!-- end main container -->
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angular/angular.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/picture.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/product/product.js"></script>
<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
<script type="text/javascript" src="<%=path%>/js/pagination.js"></script>
</html>