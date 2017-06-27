var app = angular.module('commodityApp', ['dPagination','pictureApp']);
app.controller('commodityCtrl', function($scope,$rootScope,$http) {
	var serverPath = document.getElementById('server').value+"/uias-web/login/loginOut";
	//注销
	$scope.loginOut = function() {
		window.location.href = serverPath;
	};
	

	/*
	 * showpage显示页面(页面均为互斥) 
	 * 0---全不显示
	 * 1---检索页面 
	 * 2---商品table
	 * 3---展示页面
	 * 4---新增 ,编辑 页面 
	 * 
	 */
	$rootScope.showPage = 0;//显示页面
	$rootScope.SourcePage = 0;//跳转前页面
	
	$rootScope.isProduct = false;//用于详细图片BUTTON显示，表示此为不是产品业务
 	$scope.colorone = "";//菜单背景颜色
 	$scope.colorones = "";//菜单背景颜色
	$scope.newEditQuit = true;//新增页面是否放弃当前编辑FLG
	$scope.newEditCount = 0;//新增页面是否放弃当前编辑FLG
	$scope.oldEditQuit = true;//编辑页面是否放弃当前编辑FLG
	$scope.doubleC = 0;//防止表单重复提交
	$scope.doubleClick = false;//防止表单重复提交
	$scope.pageNum = 1;//翻页页数
	$scope.total = 1;//总共条数
	$scope.pageSize = 15;//每页件数
	$rootScope.pictureCheck = true;//是否check图片
	$rootScope.showPage = 1;//显示页面
	$scope.commodityStatusList = [{Name:"上架",Value:"3"},{Name:"下架",Value:"4"}];
	$scope.showNewButton = false;//显示新增按钮
 	$scope.commodityBGC = "#99C731";
	// 前台分页的数据结构
	$scope.pagination = {
			pageNum : $scope.pageNum,//翻页页数
			total : $scope.total,//总共条数
			pageSize : $scope.pageSize,//每页件数
			onChange : function() {
				if($scope.initd == undefined)
					return;
				if($scope.type != null){
					var commodity = {commodityCode:$scope.commodityCodes,commodityGs1Code:$scope.commodityGs1Codes
							,commodityName:$scope.commodityNames,commodityShowPlace:$scope.commodityShowPlaces
							,commodityStatus:$scope.commodityStatuss};
					url = "/pms-web/pms/commodity/selectCommodityDetial"
				}else{
					var commodity = {category:$scope.category,commodityCode:$scope.commodityCode};
					url = "/pms-web/pms/commodity/selectCommodityList"
				}
				var res = $http.post(url+"?pageNum="+$scope.pagination.pageNum
						+"&pageSize="+$scope.pagination.pageSize,commodity);
				res.success(function(data, status, headers, config) {
					$scope.commodityList = data.result;//返回结果
					$scope.pagination.pageNum = data.pageNum;//返回结果页数
					$scope.pagination.total = data.total;//返回结果总共条数
					$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
					var cb = $scope.pageSize ;//为满足条数时填补空行
					if($scope.commodityList != undefined && $scope.commodityList != {})
						cb = cb - $scope.commodityList.length;//计算需要填补几行
					$scope.tempList = [cb];//占位
					while(cb > 0){
						$scope.tempList.push([cb]);//占位添加
						cb--;
					}
					$scope.tempList.pop();//去除最后一行
				});
			}
	};
	//监听 
	$scope.$watch('tempList',  function(newValue, oldValue) {
		if (newValue === oldValue) { return; } 
		$scope.selectAll = false;//翻页时清空前页checkbox值
	});
	//获取品类列表
	$http({
		method:"GET",
		url:"/pms-web/pms/category/GetCatagoryList"
	}).success(function(data) {
		$scope.categoryList = data;
	}).error(function() {
		$scope.message = "获取数据失败";
		//模态框隐藏确认按钮
		$scope.stateSure = false;
		showPopup('showInfo',true);
	});
	//客户类型基础数据列表
	$http.get("getBasicCustomerType").
	success(function(data, status, headers, config) {
		$scope.basicCustomerType = data;
	});
	//获取城市区域列表
	$http.get("getDivisionNode").
	success(function(data, status, headers, config) {
		$scope.divisionNode = data;
	});

	//获取税率
	$http.get("/pms-web/pms/product/getTaxRate")
	     .success(function(data) {
	    	 $rootScope.severTaxRate = data;
	     });
	//获取时间单位
	$http.get("/pms-web/pms/product/getDateUnit")
	     .success(function(data) {
	    	 $rootScope.dateUnits = data;
	     });
	$scope.showSearchPage = function(){
		//离开新增页面提示
		if(!$scope.newEditQuit){
			var con = confirm("您当前正处于新增状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.newEditQuit = true;//放弃当前编辑	
				$scope.newEditCount = 0;//确认放弃，次数归0
			}
		}
		//离开编辑页面提示
		if(!$scope.oldEditQuit){
			var con = confirm("您当前正处于编辑状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.oldEditQuit = true;//放弃当前编辑	
			}
		}
		$rootScope.showPage = 1;//显示页面
		$scope.commodityList = null;
		$scope.clearDetail();
	 	$scope.colorone = "";//菜单背景颜色
   	 	$scope.colorones = "#99C731";//菜单背景颜色
		
	}
	$scope.enterKeyup = function(e,pageNo){
		var keycode = window.event?e.keyCode:e.which;
		if(keycode == 13){
			if(pageNo == 1){
				$scope.searchCommotyByCode('detail');
			}
		}
	}
	$scope.returnPage = function(parm){
		//离开新增页面提示
		if(!$scope.newEditQuit){
			var con = confirm("您当前正处于新增状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.newEditQuit = true;//放弃当前编辑	
				$scope.newEditCount = 0;//确认放弃，次数归0
			}
		}
		//离开编辑页面提示
		if(!$scope.oldEditQuit){
			var con = confirm("您当前正处于编辑状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.oldEditQuit = true;//放弃当前编辑	
			}
		}
		if(!$scope.flg && $rootScope.showPage == 4){
			var returnPage = angular.copy($rootScope.SourcePageEdit);
			//编辑按钮
			$scope.showCommodityInfo($scope.ChoseCommodity);
		}else{
			var returnPage = angular.copy($rootScope.SourcePage);
		}
		$rootScope.showPage = returnPage;//显示页面
	}
	//点击一级菜单
	$scope.updateOne = function(updatecatone) {
		//离开新增页面提示
		if(!$scope.newEditQuit){
			var con = confirm("您当前正处于新增状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.newEditQuit = true;//放弃当前编辑	
				$scope.newEditCount = 0;//确认放弃，次数归0
			}
		}
		//离开编辑页面提示
		if(!$scope.oldEditQuit){
			var con = confirm("您当前正处于编辑状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.oldEditQuit = true;//放弃当前编辑	
			}
		}
		$rootScope.showPage = 2;
		$scope.commodityList = null;
		$scope.type = null;
		$scope.showNewButton = false;//显示新增按钮
		$scope.categoryName = null;//品类名字
		$scope.category = {categoryName:updatecatone.categoryName,id:updatecatone.id,categoryCode:updatecatone.categoryCode};
   	 	$scope.colorone = "#99C731";//菜单背景颜色
   	 	$scope.colorones = "";//菜单背景颜色
		var response = $http.post("/pms-web/pms/category/selectCategoryChildren?categoryCode="+updatecatone.categoryCode,{headers:{'Accept':'application/json'}});
		response.success(function(data, status, headers, config) {
			updatecatone.productCategorys = data;
			var commodity = {category:$scope.category,commodityCode:null};
			//获取商品列表
			var res = $http.post("/pms-web/pms/commodity/selectCommodityList?pageNum="+$scope.pageNum+"&pageSize="+$scope.pageSize,commodity);
			res.success(function(data, status, headers, config) {
				$scope.pagination.pageNum = data.pageNum;//返回结果页数
				$scope.pagination.total = data.total;//返回结果总共条数
				$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
				$scope.commodityList = data.result;//返回结果
				var cb = $scope.pageSize ;//为满足条数时填补空行
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;//计算需要填补几行
				$scope.tempList = [cb];//占位
				while(cb > 0){
					$scope.tempList.push([cb]);//占位添加
					cb--;
				}
				$scope.tempList.pop();//去除最后一行
				$scope.initd = "0";//第一次页面检索完成
			});
		});
		//收起或显示一级菜单
		if($scope.spMenuShow != updatecatone.id)
			$scope.spMenuShow = updatecatone.id;
		else
			$scope.spMenuShow = undefined;
	};
	//点击二级菜单
	$scope.updateTwo = function(cat1name,updatecattwo) {
		//离开新增页面提示
		if(!$scope.newEditQuit){
			var con = confirm("您当前正处于新增状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.newEditQuit = true;//放弃当前编辑	
				$scope.newEditCount = 0;//确认放弃，次数归0
			}
		}
		//离开编辑页面提示
		if(!$scope.oldEditQuit){
			var con = confirm("您当前正处于编辑状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.oldEditQuit = true;//放弃当前编辑	
			}
		}
		$rootScope.showPage = 2;
		$scope.commodityList = null;
		$scope.type = null;
	 	$scope.colorones = "";//菜单背景颜色
		$scope.showNewButton = false;//显示新增按钮
		$scope.categoryName = null;//品类名字
		$scope.category = {categoryName:updatecattwo.categoryName,id:updatecattwo.id,categoryCode:updatecattwo.categoryCode};
		var response = $http.post("/pms-web/pms/category/selectCategoryChildren?categoryCode="+updatecattwo.categoryCode,{headers: { 'Content-Type': 'application/json'}});
		response.success(function(data, status, headers, config) {
			updatecattwo.productCategorys = data;
			var commodity = {category:$scope.category,commodityCode:null};
			//获取商品列表
			var res = $http.post("/pms-web/pms/commodity/selectCommodityList?pageNum="+$scope.pageNum+"&pageSize="+$scope.pageSize,commodity);
			res.success(function(data, status, headers, config) {
				$scope.pagination.pageNum = data.pageNum;//返回结果页数
				$scope.pagination.total = data.total;//返回结果总共条数
				$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
				$scope.commodityList = data.result;//返回结果
				var cb = $scope.pageSize ;//为满足条数时填补空行
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;//计算需要填补几行
				$scope.tempList = [cb];//占位
				while(cb > 0){
					$scope.tempList.push([cb]);//占位添加
					cb--;
				}
				$scope.tempList.pop();//去除最后一行
				$scope.initd = "0";//第一次页面检索完成
			});
		});
		//收起或显示二级菜单
		if($scope.spMenuShowT != updatecattwo.id)
			$scope.spMenuShowT = updatecattwo.id;
		else
			$scope.spMenuShowT = undefined;
	};
	//点击三级菜单
	$scope.updateThree = function(id,name,ccode,showlevel,corder) {
		//离开新增页面提示
		if(!$scope.newEditQuit){
			var con = confirm("您当前正处于新增状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.newEditQuit = true;//放弃当前编辑	
				$scope.newEditCount = 0;//确认放弃，次数归0
			}
		}
		//离开编辑页面提示
		if(!$scope.oldEditQuit){
			var con = confirm("您当前正处于编辑状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.oldEditQuit = true;//放弃当前编辑	
			}
		}
		$scope.commodityList = null;
		$scope.type = null;
	 	$scope.colorones = "";//菜单背景颜色
		$scope.categoryName = name;//品类名字
		//获取品类属性
		$rootScope.showPage = 2;//显示页面
		$scope.categoryName = name;//获取选择品类名
		var categoryCode = ccode;//品类code，检索用
		$scope.tccode = ccode;//品类code，修改上下架后在检索用
		$scope.showNewButton = true;//显示新增按钮
		//获取品类属性
		var response = $http.post("/pms-web/pms/commodity/selectCategoryPropertyList?categoryCode="+categoryCode);
		response.success(function(data, status, headers, config) {
			$scope.categoryPropertys = data;
			$scope.category = {id:id,categoryName:name,categoryCode:ccode};
			var commodity = {category:$scope.category,commodityCode:null};
			//获取商品列表
			var res = $http.post("/pms-web/pms/commodity/selectCommodityList?pageNum="+$scope.pageNum+"&pageSize="+$scope.pageSize,commodity);
			res.success(function(data, status, headers, config) {
				$scope.pagination.pageNum = data.pageNum;//返回结果页数
				$scope.pagination.total = data.total;//返回结果总共条数
				$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
				$scope.commodityList = data.result;//返回结果
				var cb = $scope.pageSize ;//为满足条数时填补空行
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;//计算需要填补几行
				$scope.tempList = [cb];//占位
				while(cb > 0){
					$scope.tempList.push([cb]);//占位添加
					cb--;
				}
				$scope.tempList.pop();//去除最后一行
				$scope.initd = "0";//第一次页面检索完成
			});
		});
	};

	//根据编码搜索商品信息，模糊匹配
	$scope.searchCommotyByCode = function(type){
		if (type != undefined){	
			$rootScope.SourcePage = 1;//跳转前页面
			$scope.type = type;
			$scope.categoryName = "商品检索";
			var commodity = {commodityCode:$scope.commodityCodes,commodityGs1Code:$scope.commodityGs1Codes
					,commodityName:$scope.commodityNames,commodityShowPlace:$scope.commodityShowPlaces
					,commodityStatus:$scope.commodityStatuss};
			url = "/pms-web/pms/commodity/selectCommodityDetial"
		}else{
			$scope.type = null;
			var commodity = {category:$scope.category,commodityCode:$scope.commodityCode};
			url = "/pms-web/pms/commodity/selectCommodityList"
		}
		var res = $http.post(url+"?pageNum=1"
				+"&pageSize="+$scope.pagination.pageSize,commodity);
		res.success(function(data, status, headers, config) {
			$scope.commodityList = data.result;//返回结果
			$scope.pagination.pageNum = data.pageNum;//返回结果页数
			$scope.pagination.total = data.total;//返回结果总共条数
			$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
			var cb = $scope.pageSize ;//为满足条数时填补空行
			if($scope.commodityList != undefined && $scope.commodityList != {})
				cb = cb - $scope.commodityList.length;//计算需要填补几行
			$scope.tempList = [cb];//占位
			while(cb > 0){
				$scope.tempList.push([cb]);//占位添加
				cb--;
			}
			$scope.tempList.pop();//去除最后一行
			$scope.initd = "0";//第一次页面检索完成
		});
	};
	//增加选择产品数量
	$scope.addNumber = function(){
		if($scope.commodity.commodityProductQuantity == undefined)
			$scope.commodity.commodityProductQuantity = 1;
		else
			$scope.commodity.commodityProductQuantity = $scope.commodity.commodityProductQuantity + 1;
	}
	//减少选择产品数量
	$scope.minusNumber = function(){
		if($scope.commodity.commodityProductQuantity == undefined)
			$scope.commodity.commodityProductQuantity = 1;
		else
			$scope.commodity.commodityProductQuantity = $scope.commodity.commodityProductQuantity - 1;	   
		//产品数量小于1时去除选择产品颜色
		if($scope.commodity.commodityProductQuantity < 1){
			var trs = document.getElementById('table1').getElementsByTagName('tr');
			for(var i=0;i<trs.length;i++){
				trs[i].style.backgroundColor = '';
			}
			$scope.commodity.product = null;//清空选择产品
			$scope.countzero = false;//check是否选择产品用
		}
	}

	//选择单个商品上下架checkbox
	$scope.isSelected = function (currentrow) {  
		return currentrow;  
	}
	//编辑商品状态
	$scope.editSta = function (now,s) {
		$scope.allState = undefined;//不是批量
		if(s == 6){
			$scope.message = "确认删除商品";
		}else{
			$scope.message = "确认对商品的状态修改";
		}
		$scope.oldCommodity = now;//选择商品
		$scope.state = s;//选择状态
		//模态框显示确认按钮
		$scope.stateSure = true;
		showPopup('showInfo',true);
	};
	//修改商品状态
	$scope.updateState = function (){
		if($scope.allState != undefined){
			$scope.updateStateAll($scope.allState);//批量上架
			return;
		}
		var urls = "";
		//如果上架操作，发送上架的URL
		if($scope.state == 3){
			urls = "/pms-web/pms/commodity/updateCommodityStateUp";
		}
		//如果下架操作，发送下架的URL
		if($scope.state == 4){
			urls = "/pms-web/pms/commodity/updateCommodityStateDown";
		}
		//如果删除操作，发送删除的URL
		if($scope.state == 6){
			urls = "/pms-web/pms/commodity/updateCommodityStateDelete";
		}
		//获取需要修改的商品状态赋值给商品对象
		$scope.oldCommodity.commodityStatus = $scope.state;
		var response = $http.post(urls, $scope.oldCommodity);//调用controller
		response.success(function(data, status, headers, config) {
			//赋值当前商品为修改后的商品
			$scope.oldCommodity = data;
			//模态框显示确认按钮
			$scope.stateSure = true;
			exitPop('showInfo');
			if($scope.state == 6){
				if($scope.type != null){
					$scope.searchCommotyByCode('detail')
					$scope.selectAll = false;
				}else{
					$scope.updateThree(null,$scope.categoryName,$scope.tccode,null,null);
					$scope.selectAll = false;
				}
			}
		});
	}
	//批量修改商品状态
	$scope.updateStateAll = function (state){
		$scope.allState = undefined;//还原flg
		var urls = "";
		if(state == 3){
			urls = "/pms-web/pms/commodity/updateCommodityStateUps";
		}
		if(state == 4){
			urls = "/pms-web/pms/commodity/updateCommodityStateDowns";
		}
		//如果删除操作，发送删除的URL
		if(state == 6){
			urls = "/pms-web/pms/commodity/updateCommodityStateDeletes";
		}
		//添加选择的商品index
		var obj = document.getElementsByName("cbx");
		var check_val = [];
		for(k in obj){
			if(obj[k].checked)
				check_val.push(obj[k].value);
		}
		//提交选择商品的id
		var response = $http.post(urls, check_val);
		response.success(function(data, status, headers, config) {
			exitPop('showInfo');
			if(data == 'success'){
				if($scope.type != null){
					$scope.searchCommotyByCode('detail')
					$scope.selectAll = false;
				}else{
					$scope.updateThree(null,$scope.categoryName,$scope.tccode,null,null);
					$scope.selectAll = false;
				}
			}
		});
	};
	//确认是否修改商品状态
	$scope.updateStateEdit = function (state){
		//添加选择商品
		var obj = document.getElementsByName("cbx");
		var check_val = [];
		for(k in obj){
			if(obj[k].checked)
				check_val.push(obj[k].value);
		}
		//选择大于1件时可以批量更新
		if(check_val.length<1){
			$scope.message = "请选择需要修改的商品";
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		if(state == 6){
			$scope.message = "确认删除商品";
		}else{
			$scope.message = "确认对商品的状态修改";
		}
		$scope.allState = state;//批量更新
		$scope.stateSure = true;//更新状态
		showPopup('showInfo',true);
	};
	
	
	//点击商品列表中的商品，触发显示商品详情,传入选中的商品对象
	$scope.showCommodityInfo = function(commodity){
		//离开新增提示
		if(!$scope.newEditQuit){
			var con = confirm("您当前正处于新增状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.newEditQuit = true;//放弃当前编辑	
				$scope.newEditCount = 0;//确认放弃，次数归0
			}
		}
		//离开编辑页面提示
		if(!$scope.oldEditQuit){
			var con = confirm("您当前正处于编辑状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.oldEditQuit = true;//放弃当前编辑	
			}
		}
		$scope.ChoseCommodity = commodity;
		//获取商品对应的产品信息
		var response = $http.post("/pms-web/pms/product/getAllProductsByCriteria",{productCode : commodity.product.productCode},{headers:{'Accept':'application/json'}});
		response.success(function(data, status, headers, config) {
			$scope.productList = data;
		});
		//显示右侧详情div内容
		$rootScope.showPage = 3;//显示状态
		if($rootScope.SourcePage != 1){
			$rootScope.SourcePage = 2;//跳转前页面
		}
		//清空图片数组
		$rootScope.thumbHeader = [];//页面头部图片集合
		$rootScope.thumbDetail = [];//页面详细图片集合
		$rootScope.formHeader = [];//头部图片判断是否修改集合
		$rootScope.formDetail = [];//判断详细图片是否修改集合
		$scope.citylistDB = [];//DB中的市
		$scope.divisionlist = [];//含有该市的省
		
		// 通过商品编码获取好评,差评总数
		var response = $http.get("/pms-web/pms/commodity/getCommentTypeCount?commodityCode=" + commodity.commodityCode);
		response.success(function(data, status, headers, config) {
			$scope.commodityCommentGood = $scope.changeToString(data.commodityGoodCount);
			$scope.commodityCommentBad = $scope.changeToString(data.commodityBadCount);
		});
		
		//获取商品详情内容
		$scope.commodity ={commodityName:commodity.commodityName,commodityCode:commodity.commodityCode,
				commodityCity:commodity.commodityCity,commodityGs1Code:commodity.commodityGs1Code,
				category:commodity.category,
				product:commodity.product,commodityPrice:commodity.commodityPrice,
				commoditySalesPrice:commodity.commoditySalesPrice,salesAmount:commodity.salesAmount,
				defaultFlag:commodity.defaultFlag,commodityQuantity:commodity.commodityQuantity,
				commodityStatus:commodity.commodityStatus,commodityProductQuantity:commodity.commodityProductQuantity,
				commodityWeight:commodity.commodityWeight,commodityWeightUnit:commodity.commodityWeightUnit,
				commodityShowPlace:commodity.commodityShowPlace,commodityComment:commodity.commodityComment,
				commodityPropertys:commodity.commodityPropertys,commodityPictures:commodity.commodityPictures,
				id:commodity.id,taxRateName:commodity.taxRateName,taxRateValue:commodity.taxRateValue,
				shelfLife:commodity.shelfLife,shelfLifeName:commodity.shelfLifeName,
				shelfLifeValue:commodity.shelfLifeValue,saleDate:commodity.saleDate,
				saleDateName:commodity.saleDateName,saleDateValue:commodity.saleDateValue
		}; 
		var divisionflg = false;//有无该省flg
		var nowcitys = $scope.commodity.commodityCity;//已保存的城市
		var citysingle = nowcitys.split(",");
		//循环DB的城市
		for(i=0;i<citysingle.length;i++){
			var citycode = citysingle[i];//分离后的城市code
			//循环省
			for(j=0;j<$scope.divisionNode.length;j++){
				//循环市
				//初期化省flg
				divisionflg = false;
				for(k=0;k<$scope.divisionNode[j].children.length;k++){
					//DB城市与服务器城市相匹配时保存数组
					if(citycode == $scope.divisionNode[j].children[k].code){
						//放入市code和name
						var cityname = $scope.divisionNode[j].children[k].name;
						$scope.citylistDB.push({code: citycode, name: cityname});
						//有该省
						divisionflg = true;
					}
				}
				if(divisionflg){
					//放入省code和name
					var citylistname = $scope.divisionNode[j].name;
					var citylistcode = $scope.divisionNode[j].code;
					$scope.divisionlist.push({code: citylistcode,name: citylistname});//分离后的数组
				}
			}
		}
		//获取商品图片
		$scope.showPicture();
	};
	//获取商品图片
	$scope.showPicture = function(){
		var response = $http.post("/pms-web/pms/commodity/selectCommodityPicture", $scope.commodity);
		response.success(function(data, status, headers, config) {
			$scope.commodity.commodityPictures = data;//返回商品图片
			
			//更新图片信息,用于显示
            for(var i =0;i<$scope.commodity.commodityPictures.length;i++){
            	//头部图片
            	if($scope.commodity.commodityPictures[i].pictureShowPosition == 0){
            		$rootScope.thumbHeader.push({pictureAddress:data[i].pictureUrl,pictureShowPosition:data[i].pictureShowPosition,pictureShowOrder:data[i].pictureShowOrder});
            	} else {
            		//详情页图片
            		$rootScope.thumbDetail.push({pictureAddress:data[i].pictureUrl,pictureShowPosition:data[i].pictureShowPosition,pictureShowOrder:data[i].pictureShowOrder});
            	}
            }
    		//离最大图片size还差addSize个
    		var addSize = $rootScope.headerPictureSize - $rootScope.thumbHeader.length;
    		//补足图片数量
            for(var i =0;i<addSize;i++){
        		$rootScope.thumbHeader.push({pictureAddress: "",changeFlag: false});
            }
			
		});
	};


	$scope.oncitylist = function(index){

		$scope.showcity = true;//表示DB重的城市
		$scope.selectcity = $scope.divisionNode[index];//选中省的list
		$scope.selectcityIndex = index;//选中省的Index
	};


	//点击商品新增按钮，显示商品新增编辑界面
	$scope.showCommodityInfoEdut = function(){
		//离开编辑页面提示
		if(!$scope.oldEditQuit){
			var con = confirm("您当前正处于编辑状态，请确认是否放弃");
			if(!con){
				return false;
			}else{
				$scope.oldEditQuit = true;//放弃当前编辑	
			}
		}
		//表示已经进入新增页面
		$scope.newEditCount = $scope.newEditCount + 1;
		//第二次点击新增
		if($scope.newEditCount > 1){
			$scope.message = "您已在新增画面";
			//模态框隐藏确认按钮
			showPopup('showInfo',true);
			return false;
		}
		if($scope.newEditQuit){
			$scope.newEditQuit = false;//是否放弃当前编辑FLG
		}
		document.commodityAddForm.oneinput.value='';//上传详细图片文件名清除
		//新建页面城市显示flg
		$scope.citylistDB = [];//页面显示城市清空
		$scope.divisionlist = [];//页面显示省清空
		$scope.showcity = false;//不表示旧数据城市
		$scope.chonseProductOne = null;
		//显示右侧商品编辑div内容
		$rootScope.SourcePage = 2;//跳转前页面
		$rootScope.showPage = 4;//新增页面表示
		$scope.commodity = {};//清空商品
		$scope.flg=true;//新增表示内容，却别于编辑FLG
		$scope.showinfoshelfLife = false;//保质期提示信息
		$scope.showinfosaleDate = false;//货架期提示信息
		//返回所有的符合条件的产品
		var response = $http.post("/pms-web/pms/product/getAllProductsByCriteria",{categoryCode : $scope.category.categoryCode,saleFlag:1},{headers:{'Accept':'application/json'}});
		response.success(function(data, status, headers, config) {
			$scope.productList = data;
		});
		//重量单位基础数据
		$http.get("getBasicWeightUnit").
		success(function(data, status, headers, config) {
			$scope.basicWeightUnit = data;
		});

		//循环省，把显示FLG初始化
		for(j=0;j<$scope.divisionNode.length;j++){
			$scope.divisionNode[j] = {code: $scope.divisionNode[j].code,
					name: $scope.divisionNode[j].name,
					level: $scope.divisionNode[j].level,
					children: $scope.divisionNode[j].children,
					changeFlag: false};
			//循环市
			for(k=0;k<$scope.divisionNode[j].children.length;k++){
				$scope.divisionNode[j].children[k] = {code: $scope.divisionNode[j].children[k].code,
						name: $scope.divisionNode[j].children[k].name,
						level: $scope.divisionNode[j].children[k].level,
						children: $scope.divisionNode[j].children[k].children,
						changeFlag: false};
			}
		}



		$scope.selected = [];//市code存放
		$scope.selectedTags = [];//市name存放
		$scope.selectedS = [];//省ID存放
		$scope.selectedTagsS = [];//省name存放
		$scope.commodity.commodityCity = "";
		//清空图片数组
		$rootScope.thumbHeader = [];//页面头部图片集合
		$rootScope.thumbDetail = [];//页面详细图片集合
		$rootScope.formHeader = [];//头部图片判断是否修改集合
		$rootScope.formDetail = [];//判断详细图片是否修改集合

	};
	//选择配送城市
	$scope.updateSelection = function($event, id,index){
		var checkbox = $event.target;//以选择checkbox
		//checkbox被选中
		if(checkbox.checked){
			$scope.selectedS.push($scope.divisionNode[$scope.selectcityIndex].code);//省ID存放
			$scope.selectedTagsS.push($scope.divisionNode[$scope.selectcityIndex].name);//省name存放
			$scope.selected.push(id);//市ID
			$scope.selectedTags.push(checkbox.name);//市name
			//添加changeFlag
			$scope.divisionNode[$scope.selectcityIndex].children[index] = {code: $scope.divisionNode[$scope.selectcityIndex].children[index].code,
					name: $scope.divisionNode[$scope.selectcityIndex].children[index].name,
					level: $scope.divisionNode[$scope.selectcityIndex].children[index].level,
					children: $scope.divisionNode[$scope.selectcityIndex].children[index].children,
					changeFlag: true};
		}
		//checkbox没有被选中
		if(!checkbox.checked){
			var idx = $scope.selected.indexOf(id);//定位选择checkbox
			$scope.selectedS.splice(idx,1);//省ID存放
			$scope.selectedTagsS.splice(idx,1);//省name存放
			$scope.selected.splice(idx,1);//去除市
			$scope.selectedTags.splice(idx,1);//去除省
			//添加changeFlag
			$scope.divisionNode[$scope.selectcityIndex].children[index] = {code: $scope.divisionNode[$scope.selectcityIndex].children[index].code,
					name: $scope.divisionNode[$scope.selectcityIndex].children[index].name,
					level: $scope.divisionNode[$scope.selectcityIndex].children[index].level,
					children: $scope.divisionNode[$scope.selectcityIndex].children[index].children,
					changeFlag: false};
		}
	}
	//点击商品修改按钮，显示商品新增编辑界面
	$scope.showCommodityInfoEduts = function(){

		if($scope.oldEditQuit){
			$scope.oldEditQuit = false;//是否放弃当前编辑FLG
		}
		document.commodityAddForm.oneinput.value='';
		var divisionflg = false;
		//编辑页面城市显示flg
		$scope.showcity = false;
		//清空图片数组
		$rootScope.thumbHeader = [];//页面头部图片集合
		$rootScope.thumbDetail = [];//页面详细图片集合
		$rootScope.formHeader = [];//头部图片判断是否修改集合
		$rootScope.formDetail = [];//判断详细图片是否修改集合

		$scope.showinfoshelfLife = false;//保质期提示信息
		$scope.showinfosaleDate = false;//货架期提示信息
		//循环省
		for(j=0;j<$scope.divisionNode.length;j++){
			//添加changeFlag
			$scope.divisionNode[j] = {code: $scope.divisionNode[j].code,
					name: $scope.divisionNode[j].name,
					level: $scope.divisionNode[j].level,
					children: $scope.divisionNode[j].children,
					changeFlag: false};
			//循环市
			for(k=0;k<$scope.divisionNode[j].children.length;k++){
				//添加changeFlag
				$scope.divisionNode[j].children[k] = {code: $scope.divisionNode[j].children[k].code,
						name: $scope.divisionNode[j].children[k].name,
						level: $scope.divisionNode[j].children[k].level,
						children: $scope.divisionNode[j].children[k].children,
						changeFlag: false};
			}
		}

		//循环省
		for(j=0;j<$scope.divisionNode.length;j++){
			//循环市
			for(k=0;k<$scope.divisionNode[j].children.length;k++){
				//循环DB的城市
				for(i=0;i<$scope.citylistDB.length;i++){
					//DB城市与服务器城市相匹配时保存数组 
					if($scope.citylistDB[i].code == $scope.divisionNode[j].children[k].code){
						//放入市checked
						$scope.divisionNode[j].children[k] = {code: $scope.divisionNode[j].children[k].code,
								name: $scope.divisionNode[j].children[k].name,
								level: $scope.divisionNode[j].children[k].level,
								children: $scope.divisionNode[j].children[k].children,
								changeFlag: true};
						//有该省
						divisionflg = true;
					}
				}
			}
			if(divisionflg){
				//放入省checked
				$scope.divisionNode[j] = {code: $scope.divisionNode[j].code,
						name: $scope.divisionNode[j].name,
						level: $scope.divisionNode[j].level,
						children: $scope.divisionNode[j].children,
						changeFlag: true};
			}
		}

		//放入可选城市
		$scope.selected = [];
		$scope.selectedTags = [];
		
		$scope.selectedS = [];//省ID存放
		$scope.selectedTagsS = [];//省name存放
		var nowcitys = $scope.commodity.commodityCity;//目前以后城市
		var citysingle = nowcitys.split(",");
		$scope.selected = citysingle;//
		$scope.chkcity=true;//checked

		//详情列表图片验证通过
		$rootScope.isDetailPictureSatified = true;
		//头图验证通过
		$rootScope.isHeaderPictureSatified = true;
		//显示右侧商品编辑div内容
		$rootScope.showPage = 4;
		$rootScope.SourcePageEdit = 3;//跳转前页面
		//编辑按钮
		$scope.flg=false;
		//编辑时，计数器显示flg
		$scope.countzero = true;
		if(undefined != $scope.commodity.category){
			var categoryCode = $scope.commodity.category.categoryCode;
		}else{
			var categoryCode = $scope.category.categoryCode;
		}
		var response = $http.post("/pms-web/pms/product/getAllProductsByCriteria",{categoryCode : categoryCode},{headers:{'Accept':'application/json'}});
		response.success(function(data, status, headers, config) {
			$scope.productList = data;
		});
		$scope.showPicture();

		//重量单位基础数据
		$http.get("getBasicWeightUnit").
		success(function(data, status, headers, config) {
			$scope.basicWeightUnit = data;
		});
		//客户类型基础数据列表
		$http.get("getBasicCustomerType").
		success(function(data, status, headers, config) {
			$scope.basicCustomerType = data;
		});
	};

	$scope.showinfoshelfLife = false;
	//保质期check
	$scope.checkshelfLife = function(parm){
		if("" == parm){
			$scope.commodity.shelfLifeName = null;
		}else{
			$scope.showinfoshelfLife = false;
		}
	}
	$scope.shelfLifeUnitChange = function(parm){
		if(null == $scope.commodity.shelfLife || ""== $scope.commodity.shelfLife){
			$scope.showinfoshelfLife = true;
		}
	}
	$scope.showinfosaleDate = false;
	//保质期check
	$scope.checksaleDate = function(parm){
		if("" == parm){
			$scope.commodity.saleDateName = null;
		}else{
			$scope.showinfosaleDate = false;
		}
	}
	$scope.saleDateUnitChange = function(parm){
		if(null == $scope.commodity.saleDate || ""== $scope.commodity.saleDate){
			$scope.showinfosaleDate = true;
		}
	}
	//选择产品
	$scope.chooseProduct = function(product){
		
		$scope.chonseProductOne = product;
		$scope.commodity.shelfLife = $scope.chonseProductOne.shelfLife;
		$scope.commodity.shelfLifeName = $scope.chonseProductOne.shelfLifeName;
		$scope.commodity.saleDate = $scope.chonseProductOne.saleDate;
		$scope.commodity.saleDateName = $scope.chonseProductOne.saleDateName;
		//为选择tr加上背景色
		var trs = document.getElementById('table1').getElementsByTagName('tr');
		for( var i=0; i<trs.length; i++ ){
			trs[i].style.backgroundColor = '';
		}

		document.getElementById(product.id).style.backgroundColor = '#DFF0D8';

		//清空图片数组
		$rootScope.thumbHeader = [];//页面头部图片集合
		$rootScope.thumbDetail = [];//页面详细图片集合
		$rootScope.formHeader = [];//头部图片判断是否修改集合
		$rootScope.formDetail = [];//判断详细图片是否修改集合

		//编辑时，计数器显示flg
		$scope.countzero = true;
		//选择商品FLG-添加图片计算使用
		$scope.choseprod = true;
		//详情列表图片验证通过
		$rootScope.isDetailPictureSatified = true;
		//头图验证通过
		$rootScope.isHeaderPictureSatified = true;
		//获取产品图片信息
		$http({
			method:"GET",
			url:"/pms-web/pms/product/getProductPicture?productCode="+product.productCode
		}).success(function(data) {
			product.productPictures = data;
			//对选中的产品赋值
			$scope.choosePro = product;
			//给当前商品赋予产品对象
			$scope.commodity.product = product;
			$scope.commodity.commodityProductQuantity = 1;
			
			
			//当前商品份数初期话
			//将选中的产品对应的图片进行展示
			//获取品类列表
			if(product.productPictures != undefined && product.productPictures != null 
					&& product.productPictures.length != undefined && product.productPictures.length > 0){
				$scope.commodity.commodityPictures = [];
				for(var b=0;b<product.productPictures.length;b++){
					$scope.commodity.commodityPictures[b] = {pictureUrl : product.productPictures[b].pictureAddress};
				}
				
				//更新图片信息,用于显示
	            for(var i =0;i<product.productPictures.length;i++){
	            	//头部图片
	            	if(product.productPictures[i].pictureShowPosition == 0){
	            		$rootScope.thumbHeader.push({pictureAddress:product.productPictures[i].pictureAddress, changeFlag: false,pictureShowPosition:data[i].pictureShowPosition,pictureShowOrder:data[i].pictureShowOrder});
	            	} else {
	            		//详情页图片
	            		$rootScope.thumbDetail.push({pictureAddress:product.productPictures[i].pictureAddress, changeFlag: false,pictureShowPosition:data[i].pictureShowPosition,pictureShowOrder:data[i].pictureShowOrder});
	            	}
	            }
				
				//离最大图片size还差addSize个
				var addSize = $rootScope.headerPictureSize - $rootScope.thumbHeader.length;
				//补足图片数量
                for(var i =0;i<addSize;i++){
                	$rootScope.formHeader.push({pictureAddress: "", changeFlag: false});
            		$rootScope.thumbHeader.push({pictureAddress: "", changeFlag: false});
                }
                
			}
		});
	};
	//去除重复值
	$scope.replaceSameObj = function(target){
		//去除省中的重复项目
		var res = [], hash = {};
		for(var i=0, elem; (elem = target[i]) != null; i++)  {
			if (!hash[elem])
			{
				res.push(elem);
				hash[elem] = true;
			}
		}
		return res
	}
	//新增商品
	$scope.onAddSaveClick = function(addedProduct) {
		//放入选择城市，DB存储方式为code，code，code
		for(var i=0;i<$scope.selected.length;i++){
			if(i==0){
				$scope.commodity.commodityCity = $scope.commodity.commodityCity+$scope.selected[i];
			}else{
				$scope.commodity.commodityCity = $scope.commodity.commodityCity+","+$scope.selected[i];}
		}

		//去除省中的重复项目-code
		$scope.selectedS = $scope.replaceSameObj($scope.selectedS);


		//产品数量不能为空
		if($scope.commodity.commodityProductQuantity == null || $scope.commodity.commodityProductQuantity.length==0){
			$scope.message = "产品数量不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//产品数量数字ckeck
		if(isNaN($scope.commodity.commodityProductQuantity)){
			$scope.message = "产品数量必须为数字";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//产品数量范围check
		if(Number($scope.commodity.commodityProductQuantity)<= 0 ){
			$scope.message = "产品数量必须大于0";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//判断正整数  
/*	     var re = /^[1-9]+[0-9]*]*$/;
	     if (!re.test($scope.commodity.commodityProductQuantity)){
			$scope.message = "产品数量必须是正整数";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
	     }*/
		
		//没有选择产品
		if($scope.commodity.product == undefined){
			$scope.message = "必须选择产品";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//头部图片没有上传
		if(!$rootScope.isHeaderPictureSatified){
			$scope.message = "头部至少上传"+$rootScope.headerPictureCheckSize+"张图片！";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//详情页图片没有上传
		if(!$rootScope.isDetailPictureSatified){
			$scope.message = "详情页至少要有一张图片！";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//商品名称不能为空
		if($scope.commodity.commodityName == null || $scope.commodity.commodityName.length==0){
			$scope.message = "商品名称不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//商品名称不能为空
		if($scope.commodity.commodityComment == null || $scope.commodity.commodityComment.length==0){
			$scope.message = "商品说明不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//原价小数check
		if($scope.commodityAddForm.commodityPrice.$error.pattern){
			$scope.message = "请正确输入原价。(最多8位整数加2位小数)";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//原价不能为空
		if($scope.commodity.commodityPrice == null || $scope.commodity.commodityPrice.length==0){
			$scope.message = "原价不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//现价小数check
		if($scope.commodityAddForm.commoditySalesPrice.$error.pattern){
			$scope.message = "请正确输入现价。(最多8位整数加2位小数)";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//现价不能为空
		if($scope.commodity.commoditySalesPrice == null || $scope.commodity.commoditySalesPrice.length==0){
			$scope.message = "现价不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//重量不能为空
		if($scope.commodity.commodityWeight == null || $scope.commodity.commodityWeight.length==0){
			$scope.message = "重量不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//重量数字ckeck
		if(isNaN($scope.commodity.commodityWeight)){
			$scope.message = "重量必须为数字";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//原价范围check
		if(Number($scope.commodity.commodityWeight)<= 0 ){
			$scope.message = "重量必须大于0";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//为单位赋值
		$scope.commodity.commodityWeightUnit = $scope.chonseProductOne.productSpecificationUnitFirst;
		//请选择单位
//		if($scope.commodity.commodityWeightUnit == null || $scope.commodity.commodityWeightUnit.length==0){
//			$scope.message = "请选择单位";
//			//模态框隐藏确认按钮
//			$scope.stateSure = false;
//			showPopup('showInfo',true);
//			return;
//		}
		//请选择显示端
		if($scope.commodity.commodityShowPlace == null || $scope.commodity.commodityShowPlace.length==0){
			$scope.message = "请选择显示端";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//请选择税率
		if($scope.commodity.taxRateName == null || $scope.commodity.taxRateName.length==0){
			$scope.message = "请选择税率";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//保质期数字check
		if(undefined != $scope.commodity.shelfLife && null != $scope.commodity.shelfLife &&
				"" != $scope.commodity.shelfLife){
			var checkNumber = /^\d+$/;//正整数
			if(null == $scope.commodity.shelfLife.toString().match(checkNumber)){
				$scope.message = "保质期必须为正整数!";
				//模态框隐藏确认按钮
				$scope.stateSure = false;
				showPopup('showInfo',true);
				return;
			}
		}
		//请选择保质期
		if(undefined != $scope.commodity.shelfLife && null != $scope.commodity.shelfLife &&
				"" != $scope.commodity.shelfLife){
			if($scope.commodity.shelfLifeName == null || $scope.commodity.shelfLifeName.length==0){
				$scope.message = "保质期有值时单位不能为空！";
				//模态框隐藏确认按钮
				$scope.stateSure = false;
				showPopup('showInfo',true);
				return;
			}
		}
		//货架期数字check
		if(undefined != $scope.commodity.saleDate && null != $scope.commodity.saleDate &&
				"" != $scope.commodity.saleDate){
			var checkNumber = /^\d+$/;//正整数
			if(null == $scope.commodity.saleDate.toString().match(checkNumber)){
				$scope.message = "货架期必须为正整数!";
				//模态框隐藏确认按钮
				$scope.stateSure = false;
				showPopup('showInfo',true);
				return;
			}
		}
		//请选择货架期
		if(undefined != $scope.commodity.saleDate && null != $scope.commodity.saleDate &&
				"" != $scope.commodity.saleDate){
			if($scope.commodity.saleDateName == null || $scope.commodity.saleDateName.length==0){
				$scope.message = "货架期有值时单位不能为空！";
				//模态框隐藏确认按钮
				$scope.stateSure = false;
				showPopup('showInfo',true);
				return;
			}
		}
		//可送城市不能为空
		if($scope.selected.length == 0){
			$scope.message = "可送达地区不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		
		//防止表单重复提交
		$scope.doubleC = $scope.doubleC + 1;
		if($scope.doubleC > 0){
			$scope.doubleClick = true;
			$scope.message = "正在提交数据,请稍后,成功后自动关闭！";
			showPopup('submitInfo',true);
		}
		
		// 图片数据的处理
		var data = new FormData();
		var ic = 0;h1 = 0;h2 = 0;

		//头部图片信息
		for(var image in $rootScope.thumbHeader){
			//当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
			if($rootScope.thumbHeader[image].pictureAddress != undefined && $rootScope.thumbHeader[image].pictureAddress.length > 100){
				ic ++;
				h1 ++;
			}
		}
		//详情页图片信息
		for(var image in $rootScope.thumbDetail){
			//当详情页图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
			if($rootScope.thumbDetail[image].pictureAddress != undefined && $rootScope.thumbDetail[image].pictureAddress.length > 100){
				ic ++;
				h2 ++;
			}
		}

		var ib = 0;$scope.pic = [];
		//没有图片信息改变时
		if(ic == 0){
			$scope.addUpPic($scope.pic);
			return;
		}

		//有图片信息变化
		for(var i=0;i<ic;i++){
			var data = new FormData();
			if(i >= h1){
				//详情页图片
				for(var image in $rootScope.thumbDetail){
					//当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
					if($rootScope.thumbDetail[image].pictureAddress != undefined && $rootScope.thumbDetail[image].pictureAddress.length > 100){
						data.append("1,"+image,$rootScope.thumbDetail[image].file);
						$rootScope.thumbDetail[image].pictureAddress = undefined;
						break;
					}
				}
			}else{
				for(var image in $rootScope.thumbHeader){
					//当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
					if($rootScope.thumbHeader[image].pictureAddress != undefined && $rootScope.thumbHeader[image].pictureAddress.length > 100){
						data.append("0,"+image,$rootScope.thumbHeader[image].file);
						$rootScope.thumbHeader[image].pictureAddress = undefined;
						break;
					}
				}
			}
			//图片上传
			$http({
				method : 'post',
				url : "/pms-web/pms/product/imageUpload",
				data : data,
				headers : {
					'Content-Type' : undefined
				},
				transformRequest : angular.identity
			}).success(function(newdata) {
				$scope.pic[ib] = newdata[0];//清空图片
				ib ++;
				if(ib == ic)
					//图片服务器上传后页面提交
					$scope.addUpPic($scope.pic);
			});
		}
	};
	//去除头部图片的空位置
	$rootScope.deletBlank = function (data){
		if(null == data || data.length == 0){
			return data;
		}
		for(var i = data.length-1;i>=0;i--){
			if(data[i].pictureShowPosition == 0){
				if(undefined == data[i].pictureUrl || 
						null == data[i].pictureUrl || 
						"" == data[i].pictureUrl){
					data.splice(i, 1);//删除指定index
				}
			}
		}
		
		for(var i = 0;i<data.length;i++){
			if(data[i].pictureShowPosition == 0){
				data[i].pictureShowOrder = i;
			}
		}
		return data;
	}
	
	//新增上传图片
	$scope.addUpPic = function(data){
		// 返回数据的处理---产品的图片
		//头部图片信息
		var index = $rootScope.thumbHeader.length;
		$scope.commodity.commodityPictures = [];
		//把图片放入提交位置
		for(var image in $rootScope.thumbHeader){
			if($scope.commodity.commodityPictures[image] == undefined || $scope.commodity.commodityPictures[image] == null){
				$scope.commodity.commodityPictures.push({pictureUrl : $rootScope.thumbHeader[image].pictureAddress,pictureShowPosition:0,pictureShowOrder:image});//新图片添加数组
			}
			else{
				$scope.commodity.commodityPictures[image].pictureUrl = $rootScope.thumbHeader[image].pictureAddress;//头部图片地址
				$scope.commodity.commodityPictures[image].pictureShowPosition = 0;//头部图片，0固定
				$scope.commodity.commodityPictures[image].pictureShowOrder = image;//头部图片顺序
			}
			if(data != null)
				for(var i = 0;i<data.length;i++){
					//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
					if(data[i].pictureShowPosition == 0 && image == data[i].pictureShowOrder){
						$scope.commodity.commodityPictures[image].pictureUrl = data[i].pictureAddress;
						break;
					}
				}
		}
		//详情页图片信息
		for(var image in $rootScope.thumbDetail){
			if($scope.commodity.commodityPictures[index] == undefined || $scope.commodity.commodityPictures[index] == null)
				$scope.commodity.commodityPictures.push({pictureUrl : $rootScope.thumbDetail[image].pictureAddress,pictureShowPosition:1,pictureShowOrder:image});//新图片添加数组
			else{
				$scope.commodity.commodityPictures[index].pictureUrl = $rootScope.thumbDetail[image].pictureAddress;//详情图片地址
				$scope.commodity.commodityPictures[index].pictureShowPosition = 1;//详情图片，1固定
				$scope.commodity.commodityPictures[index].pictureShowOrder = image;//详情图片顺序
			}

			if(data != null)
				for(var i = 0;i<data.length;i++){
					//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
					if(data[i].pictureShowPosition == 1 && image == data[i].pictureShowOrder){
						$scope.commodity.commodityPictures[index].pictureUrl = data[i].pictureAddress;
						break;
					}
				}
			index ++;
		}
		//保质期Value赋值
		for(i in $rootScope.dateUnits){
			if($scope.commodity.shelfLifeName == $rootScope.dateUnits[i].optionName){
				$scope.commodity.shelfLifeValue = $rootScope.dateUnits[i].optionValue;
			}
		}
		//货架期Value赋值
		for(i in $rootScope.dateUnits){
			if($scope.commodity.saleDateName == $rootScope.dateUnits[i].optionName){
				$scope.commodity.saleDateValue = $rootScope.dateUnits[i].optionValue;
			}
		}
		//税率Value赋值
		for(i in $rootScope.severTaxRate){
			if($scope.commodity.taxRateName == $rootScope.severTaxRate[i].optionName){
				$scope.commodity.taxRateValue = $rootScope.severTaxRate[i].optionValue;
			}
		}
		//去除头部图片的空位置
		$scope.commodity.commodityPictures = $rootScope.deletBlank($scope.commodity.commodityPictures);
		//新增商品信息
		$http.post("addCommodity", $scope.commodity).success(
				function(data,
						status,
						headers,
						config) {
					$scope.commodity.commodityCode = data.commodityCode;//返回商品code
					$scope.commodity.commodityGs1Code = data.commodityGs1Code;//返回商品gs1code
					//新增完成。FLG重置
					if(!$scope.newEditQuit){
						$scope.newEditQuit = true;//放弃当前新增
						$scope.newEditCount = 0;//确认放弃，次数归0
					}
					$scope.doubleClick = false;//防止二次提交，完成提交重置
					exitPop('submitInfo');
					$scope.doubleC = 0;//防止二次提交，完成提交重置
					//新增成功后进入查看界面查看该商品信息
					$scope.showCommodityInfo($scope.commodity);
					//重新加载商品展示列表信息
					$scope.searchCommotyByCode();
				});
	};

	//修改商品信息
	$scope.onAddSaveClickUpdate = function(addedProduct) {
		//放入选择城市，DB存储方式为code，code，code
		$scope.commodity.commodityCity ="";
		for(var i=0;i<$scope.selected.length;i++){
			if(i==0){
				$scope.commodity.commodityCity = $scope.commodity.commodityCity+$scope.selected[i];
			}else{
				$scope.commodity.commodityCity = $scope.commodity.commodityCity+","+$scope.selected[i];}
		}

		//没有选择产品
		if($scope.commodity.product == undefined){
			$scope.message = "必须选择产品";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//头部图片没有上传
		if(!$rootScope.isHeaderPictureSatified){
			$scope.message = "头部至少上传"+$rootScope.headerPictureCheckSize+"张图片！";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//详情页图片没有上传
		if(!$rootScope.isDetailPictureSatified){
			$scope.message = "详情页至少要有一张图片！";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//商品名称不能为空
		if($scope.commodity.commodityName == null || $scope.commodity.commodityName.length==0){
			$scope.message = "商品名称不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//商品名称不能为空
		if($scope.commodity.commodityComment == null || $scope.commodity.commodityComment.length==0){
			$scope.message = "商品说明不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//原价小数check
		if($scope.commodityAddForm.commodityPrice.$error.pattern){
			$scope.message = "请正确输入原价。(最多8位整数加2位小数)";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//原价不能为空
		if($scope.commodity.commodityPrice == null || $scope.commodity.commodityPrice.length==0){
			$scope.message = "原价不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//现价小数check
		if($scope.commodityAddForm.commoditySalesPrice.$error.pattern){
			$scope.message = "请正确输入现价。(最多8位整数加2位小数)";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//现价不能为空
		if($scope.commodity.commoditySalesPrice == null || $scope.commodity.commoditySalesPrice.length==0){
			$scope.message = "现价不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//重量不能为空
		if($scope.commodity.commodityWeight == null || $scope.commodity.commodityWeight.length==0){
			$scope.message = "重量不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//重量数字ckeck
		if(isNaN($scope.commodity.commodityWeight)){
			$scope.message = "重量必须为数字";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//原价范围check
		if(Number($scope.commodity.commodityWeight)< 0 ){
			$scope.message = "重量必须大于0";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//请选择单位
//		if($scope.commodity.commodityWeightUnit == null || $scope.commodity.commodityWeightUnit.length==0){
//			$scope.message = "请选择单位";
//			//模态框隐藏确认按钮
//			$scope.stateSure = false;
//			showPopup('showInfo',true);
//			return;
//		}
		//请选择显示端
		if($scope.commodity.commodityShowPlace == null || $scope.commodity.commodityShowPlace.length==0){
			$scope.message = "请选择显示端";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//保质期数字check
		if(undefined != $scope.commodity.shelfLife && null != $scope.commodity.shelfLife &&
				"" != $scope.commodity.shelfLife){
			var checkNumber = /^\d+$/;//正整数
			if(null == $scope.commodity.shelfLife.toString().match(checkNumber)){
				$scope.message = "保质期必须为正整数!";
				//模态框隐藏确认按钮
				$scope.stateSure = false;
				showPopup('showInfo',true);
				return;
			}
		}
		//请选择保质期
		if(undefined != $scope.commodity.shelfLife && null != $scope.commodity.shelfLife &&
				"" != $scope.commodity.shelfLife){
			if($scope.commodity.shelfLifeName == null || $scope.commodity.shelfLifeName.length==0){
				$scope.message = "保质期有值时单位不能为空！";
				//模态框隐藏确认按钮
				$scope.stateSure = false;
				showPopup('showInfo',true);
				return;
			}
		}
		//货架期数字check
		if(undefined != $scope.commodity.saleDate && null != $scope.commodity.saleDate &&
				"" != $scope.commodity.saleDate){
			var checkNumber = /^\d+$/;//正整数
			if(null == $scope.commodity.saleDate.toString().match(checkNumber)){
				$scope.message = "货架期必须为正整数!";
				//模态框隐藏确认按钮
				$scope.stateSure = false;
				showPopup('showInfo',true);
				return;
			}
		}
		//请选择货架期
		if(undefined != $scope.commodity.saleDate && null != $scope.commodity.saleDate &&
				"" != $scope.commodity.saleDate){
			if($scope.commodity.saleDateName == null || $scope.commodity.saleDateName.length==0){
				$scope.message = "货架期有值时单位不能为空！";
				//模态框隐藏确认按钮
				$scope.stateSure = false;
				showPopup('showInfo',true);
				return;
			}
		}
		//请选择税率
		if($scope.commodity.taxRateName == null || $scope.commodity.taxRateName.length==0){
			$scope.message = "请选择税率";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		//可送城市不能为空
		if($scope.selected.length == 0){
			$scope.message = "可送达地区不能为空";
			//模态框隐藏确认按钮
			$scope.stateSure = false;
			showPopup('showInfo',true);
			return;
		}
		
		//防止表单重复提交
		$scope.doubleC = $scope.doubleC + 1;
		if($scope.doubleC > 0){
			$scope.doubleClick = true;
			$scope.message = "正在提交数据,请稍后,成功后自动关闭！";
			showPopup('submitInfo',true);
		}
		
		// 图片数据的处理
		var ic = 0;h1 = 0;h2 = 0;
		//头部图片信息
		for(var image in $rootScope.thumbHeader){
			//当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
			if($rootScope.thumbHeader[image].pictureAddress != undefined && $rootScope.thumbHeader[image].pictureAddress.length > 100){
				ic ++;
				h1 ++;
			}
		}
		//详情页图片信息
		for(var image in $rootScope.thumbDetail){
			//当详情页图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
			if($rootScope.thumbDetail[image].pictureAddress != undefined && $rootScope.thumbDetail[image].pictureAddress.length > 100){
				ic ++;
				h2 ++;
			}
		}

		var ib = 0;$scope.pic = [];
		//图片没有修改
		if(ic == 0){
			$scope.updateUpPic($scope.pic);
			return;
		}
		//有图片需要上传
		for(var i=0;i<ic;i++){
			var data = new FormData();

			if(i >= h1){
				//详情页图片
				for(var image in $rootScope.thumbDetail){
					//当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
					if($rootScope.thumbDetail[image].pictureAddress != undefined && $rootScope.thumbDetail[image].pictureAddress.length > 100){
						data.append("1,"+image,$rootScope.thumbDetail[image].file);
						$rootScope.thumbDetail[image].pictureAddress = undefined;
						break;
					}
				}
			}else{
				for(var image in $rootScope.thumbHeader){
					//当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
					if($rootScope.thumbHeader[image].pictureAddress != undefined && $rootScope.thumbHeader[image].pictureAddress.length > 100){
						data.append("0,"+image,$rootScope.thumbHeader[image].file);
						$rootScope.thumbHeader[image].pictureAddress = undefined;
						break;
					}
				}
			}
			//图片上传
			$http({
				method : 'post',
				url : "/pms-web/pms/product/imageUpload",
				data : data,
				headers : {
					'Content-Type' : undefined
				},
				transformRequest : angular.identity
			}).success(function(newdata) {
				$scope.pic[ib] = newdata[0];
				ib ++;
				if(ib == ic)
					$scope.updateUpPic($scope.pic);
			});
		}
	};

	//修改上传图片信息
	$scope.updateUpPic = function(data){
		//返回的图片信息
		//判断当前产品是否存在，若存在代表修改的时候重新选择了新的产品，则将编辑状态的商品对应的产品进行重新赋值
		if($scope.product != undefined)
			$scope.commodity.product = $scope.product;


		//头部图片信息
		var index = $rootScope.thumbHeader.length;
		$scope.commodity.commodityPictures = [];
		for(var image in $rootScope.thumbHeader){
			if($scope.commodity.commodityPictures[image] == undefined || $scope.commodity.commodityPictures[image] == null)
				$scope.commodity.commodityPictures.push({pictureUrl : $rootScope.thumbHeader[image].pictureAddress,pictureShowPosition:0,pictureShowOrder:image});
			else{
				$scope.commodity.commodityPictures[image].pictureUrl = $rootScope.thumbHeader[image].pictureAddress;//头部图片地址
				$scope.commodity.commodityPictures[image].pictureShowPosition = 0;//头部图片，0固定
				$scope.commodity.commodityPictures[image].pictureShowOrder = image;//头部图片顺序
			}
			if(data != null)
				for(var i = 0;i<data.length;i++){
					//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
					if(data[i].pictureShowPosition == 0 && image == data[i].pictureShowOrder){
						$scope.commodity.commodityPictures[image].pictureUrl = data[i].pictureAddress;
						break;
					}
				}
		}
		//详情页图片信息
		for(var image in $rootScope.thumbDetail){
			if($scope.commodity.commodityPictures[index] == undefined || $scope.commodity.commodityPictures[index] == null)
				$scope.commodity.commodityPictures.push({pictureUrl : $rootScope.thumbDetail[image].pictureAddress,pictureShowPosition:1,pictureShowOrder:image});
			else{
				$scope.commodity.commodityPictures[index].pictureUrl = $rootScope.thumbDetail[image].pictureAddress;//详情图片地址
				$scope.commodity.commodityPictures[index].pictureShowPosition = 1;//详情图片，1固定
				$scope.commodity.commodityPictures[index].pictureShowOrder = image;//详情图片顺序
			}

			if(data != null)
				for(var i = 0;i<data.length;i++){
					//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
					if(data[i].pictureShowPosition == 1 && image == data[i].pictureShowOrder){
						$scope.commodity.commodityPictures[index].pictureUrl = data[i].pictureAddress;
						break;
					}
				}
			index ++;
		}

		//保质期Value赋值
		for(i in $rootScope.dateUnits){
			if($scope.commodity.shelfLifeName == $rootScope.dateUnits[i].optionName){
				$scope.commodity.shelfLifeValue = $rootScope.dateUnits[i].optionValue;
			}
		}
		//货架期Value赋值
		for(i in $rootScope.dateUnits){
			if($scope.commodity.saleDateName == $rootScope.dateUnits[i].optionName){
				$scope.commodity.saleDateValue = $rootScope.dateUnits[i].optionValue;
			}
		}
		//税率Value赋值
		for(i in $rootScope.severTaxRate){
			if($scope.commodity.taxRateName == $rootScope.severTaxRate[i].optionName){
				$scope.commodity.taxRateValue = $rootScope.severTaxRate[i].optionValue;
			}
		}
		//去除头部图片的空位置
		$scope.commodity.commodityPictures = $rootScope.deletBlank($scope.commodity.commodityPictures);
		//修改商品数据
		$http.post("updateCommodity", $scope.commodity).success(
				function(data,
						status,
						headers,
						config) {
					$scope.oldEditQuit = true;//放弃当前编辑	
					$scope.doubleClick = false;//防止二次提交，完成提交重置
					exitPop('submitInfo');
					$scope.doubleC = 0;//防止二次提交，完成提交重置
					//修改成功后进入查看界面查看该商品信息
					$scope.showCommodityInfo($scope.commodity);
					//重新加载商品展示列表信息
					$scope.searchCommotyByCode();
				});
	};
	
	// 数字类型转化为字符串
	$scope.changeToString = function(count) {
		if (count == undefined || count == null) {
			return "0";
		}
		var changeCount = count.toString();
		var length = changeCount.length;
		if (length == 1) {
			return changeCount;
		} else if (length == 2) {
			return changeCount.substring(0, 1) + "0+";
		} else if (length == 3) {
			return changeCount.substring(0, 1) + "00+";
		} else if (length == 4) {
			return changeCount.substring(0, 2) + "00+";
		} else if (length == 5) {
			return changeCount.substring(0, 1) + "." + changeCount.substring(1, 2) + "万+";
		} else if (length == 6) {
			return changeCount.substring(0, 1) + "0万+";
		} else {
			return changeCount.substring(0, length - 5) + "0万+";
		}
	}
	$scope.clearDetail = function(){
		$scope.commodityCodes = null;
		$scope.commodityGs1Codes = null;
		$scope.commodityNames = null;
		$scope.commodityShowPlaces = null;
		$scope.commodityStatuss = null;
	}
});
function loadSecMenu(url) {
	window.location.href = url;
}