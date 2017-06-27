var app = angular.module('commentApp', ['dPagination','datetimepicker']);
app.controller('commentCtrl', function($scope,$rootScope,$http) {
	var serverPath = document.getElementById('server').value+"/uias-web/login/loginOut";
	//注销
	$scope.loginOut = function() {
		window.location.href = serverPath;
	};
	$scope.commentLevel = [{Name:"好评",Value:"3"},{Name:"差评",Value:"1"}];
	$scope.commentLv = null;//默认值
	$scope.pageNum = 1;//翻页页数
	$scope.total = 1;//总共条数
	$scope.pageSize = 15;//每页件数
	$scope.category = {};
	$scope.categoryForPage = null;//初始化检索父类code
	$scope.categoryListPage = null;//品类分页
	$scope.commodityListPage = null;//商品分页
	$scope.pageFlg = 1;//区别业务分页
	$scope.createStartTime = '';//检索开始时间
	$scope.createEndTime = '';//检索结束时间
	$scope.createStartTimeD = '';//检索开始时间评论详细
	$scope.createEndTimeD = '';//检索结束时间评论详细
	$scope.showCategoryAndCommodityTable = true;//显示品类和商品表格
	$scope.showCommodityCommentTable = false;//显示评论表格
	$scope.CommodityBadStatus = 0;//配送好评升序
	$scope.CommodityGoodStatus = 0;//配送差评升序
	$scope.PackageBadStatus = 0;//包装好评升序
	$scope.PackageGoodStatus = 0;//包装差评升序
	$scope.ExpressBadStatus = 0;//商品好评升序
	$scope.ExpressGoodStatus = 0;//商品差评升序
	$scope.ExpressStatus = 0;//配送升序
	$scope.PackageStatus = 0;//包装升序
	$scope.CommodityStatus = 0;//商品升序
	$scope.categoryShow = true;//品类统计显示
	$scope.commodityShow = false;//商品统计显示
	$scope.commentShow = false;//评价详细显示
	$scope.loading = 1;//数据读取中表示
 	$scope.commentBGC = "#99C731";
	// 前台分页的数据结构-品类
	$scope.pagination = {
			pageNum : $scope.pageNum,//翻页页数
			total : $scope.total,//总共条数
			pageSize : $scope.pageSize,//每页件数
			onChange : function() {
				if($scope.initd == undefined)
					return;
				var paginationInfo = null;
				var url = null;
				if($scope.pageFlg == 1){//品类自分页查询
					$scope.categoryShow = true;//品类统计显示
					$scope.commodityShow = false;//商品统计显示
					$scope.commentShow = false;//评价详细显示
					$scope.loading = 1;//数据读取中表示
					url = "/pms-web/pms/comment/selectCategoryCommentTotal?pageNum="
					$scope.pageNum = 1;
					if($scope.categoryForPage == null){
						paginationInfo = $scope.category;
					}else{
						paginationInfo = $scope.categoryForPage;
					}
				}else if($scope.pageFlg == 2){//商品自分页查询
					$scope.categoryShow = false;//品类统计显示
					$scope.commodityShow = true;//商品统计显示
					$scope.commentShow = false;//评价详细显示
					$scope.loading = 2;//数据读取中表示
					paginationInfo = $scope.commodityS;
					url = "/pms-web/pms/comment/selectCommodityCommentTotal?pageNum="
					$scope.pageNum = 1;
				}else if($scope.pageFlg == 3){//评论自分页查询
					$scope.categoryShow = false;//品类统计显示
					$scope.commodityShow = false;//商品统计显示
					$scope.commentShow = true;//评价详细显示
					$scope.loading = 3;//数据读取中表示
					paginationInfo = $scope.commodityComment;
					url = "/pms-web/pms/comment/selectCommodityCommentDetail?pageNum="
					$scope.pageNum = $scope.pagination.pageNum;
				}
				var res = $http.post(url+$scope.pagination.pageNum
						+"&pageSize="+$scope.pagination.pageSize,paginationInfo);
				res.success(function(data, status, headers, config) {
					
					if($scope.pageFlg == 1){//品类自分页查询
						$scope.loading = 0;//数据读取中表示
						$scope.categoryListPage = data.result;//返回结果
					}else if($scope.pageFlg == 2){//商品自分页查询
						$scope.loading = 0;//数据读取中表示
						$scope.commodityListPage = data.result;//返回结果
					}else if($scope.pageFlg == 3){//评论自分页查询
						$scope.loading = 0;//数据读取中表示
						$scope.commentListPage = data.result;//返回结果
						//增加屏蔽按钮表示逻辑
						for(i=0;i<$scope.commentListPage.length;i++){
							var newCommentContentT = null;//原评论
							var showNewCommentContentT = 2;//正常评论表示status
							if($scope.commentListPage[i].commentContent.length > 50){
								newCommentContentT = $scope.subStringObject($scope.commentListPage[i].commentContent,50);
								showNewCommentContentT = 1;//省略屏蔽表示status
							}
							$scope.commentListPage[i] = {
									id:$scope.commentListPage[i].id,
								    commentUsercode: $scope.commentListPage[i].commentUsercode,
								    commentUserTel: $scope.commentListPage[i].commentUserTel,
								    commodityCode: $scope.commentListPage[i].commodityCode,
								    categoryCode: $scope.commentListPage[i].categoryCode,
								    commentContent: $scope.commentListPage[i].commentContent,
								    orderNo: $scope.commentListPage[i].orderNo,
								    commentTime: $scope.commentListPage[i].commentTime,
								    commentStatus: $scope.commentListPage[i].commentStatus,
								    expressGrade: $scope.commentListPage[i].expressGrade,
								    packageGrade: $scope.commentListPage[i].packageGrade,
								    commodityGrade: $scope.commentListPage[i].commodityGrade,
								    showNewCommentContent:showNewCommentContentT,
								    newCommentContent:newCommentContentT
							}
						}
					}
					$scope.pagination.pageNum = data.pageNum;//返回结果页数
					$scope.pagination.total = data.total;//返回结果总共条数
					$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
					var cb = $scope.pageSize ;//为满足条数时填补空行
					if(data.result != undefined && data.result != {})
						cb = cb - data.result.length;//计算需要填补几行
					$scope.tempList = [cb];//占位
					while(cb > 0){
						$scope.tempList.push([cb]);//占位添加
						cb--;
					}
					$scope.tempList.pop();//去除最后一行
				});
			}
	};

	//获取所有评论类型
	$http.get("selectAllInfoType").
	success(function(data, status, headers, config) {
		$scope.commentType = data;
	});
	
	
		// 查询所有的品类和属性，实现三个级别的联动
		$scope.firstLevelCategories = {};
		$scope.secondLevelCategories = {};
		$scope.thirdLevelCategories = {};
		
		// 初始化所有的一级标准品类的数据
		$http.get("/pms-web/pms/product/getAllFirstLevelCategories", {
			categoryTypeFlag: 0
		}).success(function(data, status, headers, config) {
					$scope.firstLevelCategories = data;
		});
						
	// 一级品类改变的时候触发的事件:级联查询二级品类
	$scope.onFirstLevelCategorychange = function(category) {

		if(null == $scope.category1){
			$scope.category2 = "";
			$scope.category3 = "";
			$scope.secondLevelCategories = null;
			$scope.thirdLevelCategories = null;
		}
		//当没有品类code时不会进行查询
		if(undefined == category || null == category){
			return;
		}
		$http.post("/pms-web/pms/product/getAllCategoriesByCategortyCode", {
			categoryCode : category.categoryCode
		}).success(function(data, status, headers, config) {
			$scope.secondLevelCategories = data;
			$scope.thirdLevelCategories = {};
		});
	};
	// 二级品类改变的时候触发的事件：级联查询三级品类
	$scope.onSecondLevelCategorychange = function(category) {
		if(null == $scope.category2){
			$scope.category3 = "";
			$scope.thirdLevelCategories = null;
		}
		if(category != null){
			$http.post("/pms-web/pms/product/getAllCategoriesByCategortyCode", {
				categoryCode : category.categoryCode
			}).success(function(data, status, headers, config) {
				$scope.thirdLevelCategories = data;
			});
		}
	};
	
	
	
	//获取品类列表
	$http({
		method:"GET",
		url:"/pms-web/pms/category/GetCatagoryList"
	}).success(function(data) {
		$scope.categoryList = data;
		$scope.categoryName = "一级分类";
		$scope.category = {categoryName:"一级分类"};
		$scope.selectForcategory($scope.category);
	}).error(function() {
		$scope.message = "获取数据失败";
		//模态框隐藏确认按钮
		$scope.stateSure = false;
		showPopup('showInfo',true);
	});

	
	//获取品类评论数量
	$scope.selectForcategory = function(category) {
		$scope.categoryShow = true;//品类统计显示
		$scope.commodityShow = false;//商品统计显示
		$scope.commentShow = false;//评价详细显示
		$scope.pageNum = 1;
		$scope.commodityListPage = null;//查询品类时，商品分页为NULL
		$scope.commentListPage = null;//查询商品时，评论分页为NULL
		var res = $http.post("/pms-web/pms/comment/selectCategoryCommentTotal?pageNum="+$scope.pageNum+"&pageSize="+$scope.pageSize,category);
		res.success(function(data, status, headers, config) {
			$scope.loading = 0;//数据读取中表示
			$scope.pagination.pageNum = data.pageNum;//返回结果页数
			$scope.pagination.total = data.total;//返回结果总共条数
			$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
			$scope.categoryListPage = data.result;//返回结果
			var cb = $scope.pageSize ;//为满足条数时填补空行
			if($scope.categoryListPage != undefined && $scope.categoryListPage != {})
				cb = cb - $scope.categoryListPage.length;//计算需要填补几行
			$scope.tempList = [cb];//占位
			while(cb > 0){
				$scope.tempList.push([cb]);//占位添加
				cb--;
			}
			$scope.tempList.pop();//去除最后一行
			$scope.initd = "0";//第一次页面检索完成
		});
	}
	
	
	//获取商品评论数量
	$scope.selectForCommodity = function(commodity) {
		$scope.categoryShow = false;//品类统计显示
		$scope.commodityShow = true;//商品统计显示
		$scope.commentShow = false;//评价详细显示
		$scope.pageNum = 1;
		$scope.categoryListPage = null;//查询商品时，品类分页为NULL
		$scope.commentListPage = null;//查询商品时，评论分页为NULL
		var res = $http.post("/pms-web/pms/comment/selectCommodityCommentTotal?pageNum="+$scope.pageNum+"&pageSize="+$scope.pageSize,commodity);
		res.success(function(data, status, headers, config) {
			$scope.loading = 0;//数据读取中表示
			$scope.pagination.pageNum = data.pageNum;//返回结果页数
			$scope.pagination.total = data.total;//返回结果总共条数
			$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
			$scope.commodityListPage = data.result;//返回结果
			var cb = $scope.pageSize ;//为满足条数时填补空行
			if($scope.commodityListPage != undefined && $scope.commodityListPage != {})
				cb = cb - $scope.commodityListPage.length;//计算需要填补几行
			$scope.tempList = [cb];//占位
			while(cb > 0){
				$scope.tempList.push([cb]);//占位添加
				cb--;
			}
			$scope.tempList.pop();//去除最后一行
			$scope.initd = "0";//第一次页面检索完成
		});
	}
	
	//获取商品评论数量
	$scope.showCommentInfoDteail = function(commodityComment) {
		$scope.categoryShow = false;//品类统计显示
		$scope.commodityShow = false;//商品统计显示
		$scope.commentShow = true;//评价详细显示
		var res = $http.post("/pms-web/pms/comment/selectCommodityCommentDetail?pageNum="+$scope.pageNum+"&pageSize="+$scope.pageSize,commodityComment);
		res.success(function(data, status, headers, config) {
			$scope.loading = 0;//数据读取中表示
			$scope.pagination.pageNum = data.pageNum;//返回结果页数
			$scope.pagination.total = data.total;//返回结果总共条数
			$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
			$scope.commentListPage = data.result;//返回结果
			if(null != $scope.commentListPage){
				for(i=0;i<$scope.commentListPage.length;i++){
					var newCommentContentT = null;
					var showNewCommentContentT = 2;
					if($scope.commentListPage[i].commentContent.length > 50){
						newCommentContentT = $scope.subStringObject($scope.commentListPage[i].commentContent,50);
						showNewCommentContentT = 1;
					}
					$scope.commentListPage[i] = {
							id:$scope.commentListPage[i].id,
						    commentUsercode: $scope.commentListPage[i].commentUsercode,
							commentUserTel: $scope.commentListPage[i].commentUserTel,
							commodityCode: $scope.commentListPage[i].commodityCode,
							categoryCode: $scope.commentListPage[i].categoryCode,
							commentContent: $scope.commentListPage[i].commentContent,
							orderNo: $scope.commentListPage[i].orderNo,
							commentTime: $scope.commentListPage[i].commentTime,
							commentStatus: $scope.commentListPage[i].commentStatus,
							expressGrade: $scope.commentListPage[i].expressGrade,
							packageGrade: $scope.commentListPage[i].packageGrade,
							commodityGrade: $scope.commentListPage[i].commodityGrade,
							showNewCommentContent:showNewCommentContentT,
							newCommentContent:newCommentContentT,
							commodityCommentPictures: $scope.commentListPage[i].commodityCommentPictures
					}
				}
			}
			var cb = $scope.pageSize ;//为满足条数时填补空行
			if($scope.commentListPage != undefined && $scope.commentListPage != {})
				cb = cb - $scope.commentListPage.length;//计算需要填补几行
			$scope.tempList = [cb];//占位
			while(cb > 0){
				$scope.tempList.push([cb]);//占位添加
				cb--;
			}
			$scope.tempList.pop();//去除最后一行
			$scope.initd = "0";//第一次页面检索完成
		});
	}
	$scope.enterKeyup = function(e,pageNo){
		var keycode = window.event?e.keyCode:e.which;
		if(keycode == 13){
			if(pageNo == 1){
				$scope.searchCountD('99');
			}else if(pageNo == 2){
				$scope.searchCount('10');
			}
		}
	}

	//获取商品评论数量
	$scope.getCountCommoditycomment = function(commodityComment) {
		var res = $http.post("/pms-web/pms/comment/selectCommodityCommentCount",commodityComment);
		res.success(function(data, status, headers, config) {
			$scope.commentCount = data;//返回结果
		});
	}
	$scope.returnPage = function(){
		$scope.showCategoryAndCommodityTable = true;//显示品类和商品表格
		$scope.showCommodityCommentTable = false;//显示评论表格

		//后去商品品类分页
		$scope.selectForCommodity($scope.commodityS);
	}
	//点击商品详情获取商品评论数量
	$scope.showCommentInfo = function(commodity) {
		$scope.loading = 3;//数据读取中表示
		$scope.clearCountD();
		$scope.pageNum = 1;
		$scope.pageFlg = 3;//评论分页，点击页数用
		$scope.showCategoryAndCommodityTable = false;//显示品类和商品表格
		$scope.showCommodityCommentTable = true;//显示评论表格
//		$scope.clearCount();
		$scope.categoryListPage = null;//查询商品时，品类分页为NULL
		$scope.commodityListPage = null;//查询品类时，商品分页为NULL
		$scope.commodityNameChoise = commodity.commodityName;
		$scope.category.categoryName = commodity.commodityName;
		//判断标题路径显示类型
		if(commodity.category.categoryCode == $scope.tccode &&
			commodity.category.categoryCode.substring(0,2) == $scope.categoryCodeOne &&
			commodity.category.categoryCode.substring(0,5) == $scope.categoryCodeTwo){
			$scope.categoryName = $scope.categoryNameOne + "  —  " + 
			$scope.categoryNameTwo + "  —  " + $scope.categoryNameThree + "  —  " + $scope.commodityNameChoise;
		}else{
			$scope.categoryName = "查询结果" + "  —  " + $scope.commodityNameChoise;
		}
		$scope.commodityComment = {commodityCode:commodity.commodityCode,orderBy:99}
		$scope.commoditySelectCode = commodity.commodityCode;//商品ID
		$scope.showCommentInfoDteail($scope.commodityComment);//获取商品评论数量
		$scope.getCountCommoditycomment($scope.commodityComment);
	}
	
	//点击一级菜单
	$scope.updateOne = function(updatecatone,index) {
		$scope.loading = 1;//数据读取中表示
		$scope.CommodityBadStatus = 0;//配送好评升序
		$scope.CommodityGoodStatus = 0;//配送差评升序
		$scope.PackageBadStatus = 0;//包装好评升序
		$scope.PackageGoodStatus = 0;//包装差评升序
		$scope.ExpressBadStatus = 0;//商品好评升序
		$scope.ExpressGoodStatus = 0;//商品差评升序
		$scope.showCategoryAndCommodityTable = true;//显示品类和商品表格
		$scope.showCommodityCommentTable = false;//显示评论表格
		$scope.pageFlg = 1;//品类分页，点击页数用
		$scope.category1 = $scope.firstLevelCategories[index];//下拉框赋值
		$scope.onFirstLevelCategorychange($scope.category1);//查询二级
		$scope.category = {categoryName:updatecatone.categoryName,id:updatecatone.id};
		$scope.categoryCodeOne = updatecatone.categoryCode;
		$scope.categoryNameOne = updatecatone.categoryName;
		$scope.categoryName = $scope.categoryNameOne;
		$scope.categoryForPage = {parentCode:updatecatone.categoryCode};
   	 	$scope.colorone = "#99C731";//菜单背景颜色
		var response = $http.post("/pms-web/pms/category/selectCategoryChildren?categoryCode="+updatecatone.categoryCode,{headers:{'Accept':'application/json'}});
		response.success(function(data, status, headers, config) {
			updatecatone.productCategorys = data;
			//评价数量
			$scope.selectForcategory($scope.categoryForPage);
		});
		//收起或显示一级菜单
		if($scope.spMenuShow != updatecatone.id)
			$scope.spMenuShow = updatecatone.id;
		else
			$scope.spMenuShow = undefined;
	};
	//点击二级菜单
	$scope.updateTwo = function(cat1name,updatecattwo,index) {
		$scope.loading = 1;//数据读取中表示
		$scope.CommodityBadStatus = 0;//配送好评升序
		$scope.CommodityGoodStatus = 0;//配送差评升序
		$scope.PackageBadStatus = 0;//包装好评升序
		$scope.PackageGoodStatus = 0;//包装差评升序
		$scope.ExpressBadStatus = 0;//商品好评升序
		$scope.ExpressGoodStatus = 0;//商品差评升序
		$scope.showCategoryAndCommodityTable = true;//显示品类和商品表格
		$scope.showCommodityCommentTable = false;//显示评论表格
		$scope.pageFlg = 1;//品类分页，点击页数用
		$scope.category2 = $scope.secondLevelCategories[index];//下拉框赋值
		$scope.onSecondLevelCategorychange($scope.category2);//查询二级
		$scope.category = {categoryName:updatecattwo.categoryName,id:updatecattwo.id};
		$scope.categoryNameTwo  = updatecattwo.categoryName;
		$scope.categoryCodeTwo = updatecattwo.categoryCode;
		$scope.categoryName = $scope.categoryNameOne + "  —  " + $scope.categoryNameTwo;
		$scope.categoryForPage = {parentCode:updatecattwo.categoryCode};
		var response = $http.post("/pms-web/pms/category/selectCategoryChildren?categoryCode="+updatecattwo.categoryCode,{headers: { 'Content-Type': 'application/json'}});
		response.success(function(data, status, headers, config) {
			updatecattwo.productCategorys = data;
			//评价数量
			$scope.selectForcategory($scope.categoryForPage);
		});
		//收起或显示二级菜单
		if($scope.spMenuShowT != updatecattwo.id)
			$scope.spMenuShowT = updatecattwo.id;
		else
			$scope.spMenuShowT = undefined;
	};
	
	//点击三级菜单
	$scope.updateThree = function(id,name,ccode,showlevel,corder,cat3,index) {
		$scope.loading = 2;//数据读取中表示
		$scope.CommodityBadStatus = 0;//配送好评升序
		$scope.CommodityGoodStatus = 0;//配送差评升序
		$scope.PackageBadStatus = 0;//包装好评升序
		$scope.PackageGoodStatus = 0;//包装差评升序
		$scope.ExpressBadStatus = 0;//商品好评升序
		$scope.ExpressGoodStatus = 0;//商品差评升序
		$scope.showCategoryAndCommodityTable = true;//显示品类和商品表格
		$scope.showCommodityCommentTable = false;//显示评论表格
		$scope.pageFlg = 2;//品类分页，点击页数用
		$scope.category3 = $scope.thirdLevelCategories[index];//下拉框赋值
		$scope.categoryName = name;//品类名字
		//获取品类属性
		$scope.show = false;//不显示展示页面
		$scope.showEdit = false;//不显示编辑页面
		$scope.categoryName = name;//获取选择品类名
		var categoryCode = ccode;//品类code，检索用
		$scope.tccode = ccode;//品类code，修改上下架后在检索用
		$scope.category = {id:id,categoryName:name,categoryCode:ccode};
		$scope.categoryNameThree  = name;
		$scope.categoryName = $scope.categoryNameOne + "  —  " + 
			$scope.categoryNameTwo + "  —  " + $scope.categoryNameThree;
		$scope.commodityS = {category:$scope.category,commodityCode:null};//检索条件
		//后去商品品类分页
		$scope.selectForCommodity($scope.commodityS);
	};
	//品类商品查询
	$scope.searchCount = function(order,button){
		$scope.categoryName = "查询结果";
		//以下用于界面升降箭头图标切换
		if(order == 1){
			$scope.ExpressGoodStatus = 1;//配送好评升序
		}else if(order == 2){
			$scope.ExpressGoodStatus = 0;
		}else if(order == 3){
			$scope.ExpressBadStatus = 1;//配送差评升序
		}else if(order == 4){
			$scope.ExpressBadStatus = 0;
		}else if(order == 5){
			$scope.PackageGoodStatus = 1;//包装好评升序
		}else if(order == 6){
			$scope.PackageGoodStatus = 0;
		}else if(order == 7){
			$scope.PackageBadStatus = 1;//包装差评升序
		}else if(order == 8){
			$scope.PackageBadStatus = 0;
		}else if(order == 9){
			if(null != $scope.commentLv && button == "0"){
				if($scope.commentLv.Value == 1){
					$scope.message = "当前检索条件为差评，好评排序不可用！";
					//模态框隐藏确认按钮
					$scope.stateSure = false;
					showPopup('showInfo',true);
					return;
				}
			}
			$scope.CommodityGoodStatus = 1;//商品好评升序
		}else if(order == 10){
			if(null != $scope.commentLv && button == "0"){
				if($scope.commentLv.Value == 1){
					$scope.message = "当前检索条件为差评，好评排序不可用！";
					//模态框隐藏确认按钮
					$scope.stateSure = false;
					showPopup('showInfo',true);
					return;
				}
			}
			$scope.CommodityGoodStatus = 0;
		}else if(order == 11){
			if(null != $scope.commentLv && button == "1"){
				if($scope.commentLv.Value == 3){
					$scope.message = "当前检索条件为好评，差评排序不可用！";
					//模态框隐藏确认按钮
					$scope.stateSure = false;
					showPopup('showInfo',true);
					return;
				}
			}
			$scope.CommodityBadStatus = 1;//商品差评升序
		}else if(order == 12){
			if(null != $scope.commentLv && button == "1"){
				if($scope.commentLv.Value == 3){
					$scope.message = "当前检索条件为好评，差评排序不可用！";
					//模态框隐藏确认按钮
					$scope.stateSure = false;
					showPopup('showInfo',true);
					return;
				}
			}
			$scope.CommodityBadStatus = 0;
		}
		//选择商品分类后，其它条件将不会生效！
		//有三级品类检索商品
		if($scope.category3 != undefined && $scope.category3 != null && $scope.category3 != ''){
			$scope.commoditycommentd = {orderBy:order};
			$scope.category = {id:$scope.category3.id,categoryName:$scope.category3.categoryName,
					categoryCode:$scope.category3.categoryCode};
			$scope.commodityS = {category:$scope.category,commodityCode:null
					,commodityCommentDomain:$scope.commoditycommentd};//检索条件
			//后去商品品类分页
			$scope.pageFlg = 2;//品类分页，点击页数用
			$scope.loading = 2;//数据读取中表示
			$scope.selectForCommodity($scope.commodityS);
			return;
			//有二级品类检索三级
		}else if($scope.category2 != undefined && $scope.category2 != null && $scope.category2 != ''){
			$scope.commoditycommentd = {orderBy:order};
			$scope.pageFlg = 1;//品类分页，点击页数用
			$scope.categoryForPage = {parentCode:$scope.category2.categoryCode,commodityComment:$scope.commoditycommentd};
			$scope.loading = 1;//数据读取中表示
			$scope.selectForcategory($scope.categoryForPage);
			return;
			//有一级品类检索二级
		}else if($scope.category1 != undefined && $scope.category1 != null && $scope.category1 != ''){
			$scope.commoditycommentd = {orderBy:order};
			$scope.pageFlg = 1;//品类分页，点击页数用
			$scope.categoryForPage = {parentCode:$scope.category1.categoryCode,commodityComment:$scope.commoditycommentd};
			$scope.loading = 1;//数据读取中表示
			$scope.selectForcategory($scope.categoryForPage);
			return;
			//都为空是查询一级品类
		}else if('' == $scope.createStartTime && '' == $scope.createEndTime &&
				$scope.commentLv == null 
				&& (undefined == $scope.commentUserTel || '' == $scope.commentUserTel)
				&& (undefined == $scope.commodityCode || '' == $scope.commodityCode)
				&& (undefined == $scope.commentKeyWordC || '' == $scope.commentKeyWordC)
				&& (undefined == $scope.commentOrderNoC || '' == $scope.commentOrderNoC)
				&& (undefined == $scope.commodityName || '' == $scope.commodityName)
		){
			$scope.commoditycommentd = {orderBy:order};
			$scope.pageFlg = 1;//品类分页，点击页数用
			$scope.loading = 1;//数据读取中表示
			$scope.category = {categoryName:"一级分类",commodityComment:$scope.commoditycommentd};
			$scope.selectForcategory($scope.category);
		}else{
			//开始时间和结束时间必须同时存在
			if('' == $scope.createStartTime || null == $scope.createStartTime){
				var createStartTime = '0000-01-01';
			}else{
				var createStartTime = $scope.createStartTime;
			}
			if('' == $scope.createEndTime || null == $scope.createEndTime){
				var createEndTime = '9999-12-30';
			}else{
				var createEndTime = $scope.createEndTime;
			}
			//取得评价等级
			var level = null;
			if($scope.commentLv != null){
				level = $scope.commentLv.Value;
			}
			//重置时间
			if($scope.createStartTime =='' &&
					$scope.createEndTime ==''){
				$scope.createStartTime = null;
				$scope.createEndTime = null;
			}
			$scope.commodityCommentDomain={
					commentUserTel:$scope.commentUserTel,
					commodityCode:$scope.commodityCode,
					commentContent:$scope.commentKeyWordC,
					orderNo:$scope.commentOrderNoC,
					commodityCommentDateSt:createStartTime,
					commodityCommentDateEd:createEndTime,
					commodityCommentLevel:level,orderBy:order
			};
			$scope.pageFlg = 2;//品类分页，点击页数用
			$scope.commodityS = {commodityName:$scope.commodityName,commodityCommentDomain:$scope.commodityCommentDomain};
			$scope.loading = 2;//数据读取中表示
			//后去商品品类分页
			$scope.selectForCommodity($scope.commodityS);
		}
	}
	//品类商品重置
	$scope.clearCount= function(){
		//清空选项
		$scope.commentUserTel = null;
		$scope.commodityCode = null
		$scope.commentKeyWordC = null;
		$scope.commentOrderNoC = null;
		$scope.createStartTime = '';
		$scope.createEndTime = '';
		$scope.commentLv = null;
		$scope.commodityName = null;
		$scope.category1 = "";
		$scope.category2 = "";
		$scope.category3 = "";
		$scope.categoryForPage = null;
	}
	

	//品类商品查询
	$scope.searchCountD = function(order){
		if(order == 99){//条件查询时返回第一页显示
			$scope.pageNum = 1;
		}
		//开始时间和结束时间必须同时存在
		if('' == $scope.createStartTimeD || null == $scope.createStartTimeD){
			var createStartTimeD = '0000-01-01';
		}else{
			var createStartTimeD = $scope.createStartTimeD;
		}
		if('' == $scope.createEndTimeD || null == $scope.createEndTimeD){
			var createEndTimeD = '9999-12-30';
		}else{
			var createEndTimeD = $scope.createEndTimeD;
		}
		//重置时间
		if($scope.createStartTimeD =='' &&
				$scope.createEndTimeD ==''){
			$scope.createStartTimeD = null;
			$scope.createEndTimeD = null;
		}
		$scope.commodityComment={
				commodityCode:$scope.commoditySelectCode,
				commentUserTel:$scope.commentUserTelD,
				commentContent:$scope.commentKeyWordD,
				orderNo:$scope.commentOrderNoD,
				commodityCommentDateSt:createStartTimeD,
				commodityCommentDateEd:createEndTimeD,
				orderBy:order
		};
	
		$scope.pageFlg = 3;//评论分页，点击页数用
		$scope.loading = 3;//数据读取中表示
		//后去商品品类分页
		$scope.showCommentInfoDteail($scope.commodityComment);//获取商品评论数量

		$scope.getCountCommoditycomment($scope.commodityComment);//获取商品评价数量统计
		//以下用于界面升降箭头图标切换
		if(order == 13){
			$scope.ExpressStatus = 1;//配送升序
		}else if(order == 14){
			$scope.ExpressStatus = 0;
		}else if(order == 15){
			$scope.PackageStatus = 1;//包装升序
		}else if(order == 16){
			$scope.PackageStatus = 0;
		}else if(order == 17){
			$scope.CommodityStatus = 1;//商品升序
		}else if(order == 18){
			$scope.CommodityStatus = 0;
		}
	}
	//品类商品重置
	$scope.clearCountD= function(){
		//清空选项
		$scope.commentUserTelD = null;
		$scope.commentKeyWordD = null;
		$scope.commentOrderNoD = null;
		$scope.createStartTimeD = '';
		$scope.createEndTimeD = '';
	};

	//编辑商品状态
	$scope.editSta = function (now,status) {
		$scope.message = "确认对该评论的状态修改";
		$scope.oldComment = now;//选择商品
		//copy一个旧的对象用于取消时还原数据
		$scope.copyoldComment = angular.copy($scope.oldComment);
		$scope.copyoldComment.commentStatus = status;
		//模态框显示确认按钮
		$scope.stateSure = true;
		showPopup('showInfo',true);
	};
	$scope.updateState = function(){
		//模态框显示确认按钮
		exitPop('showInfo');
		$scope.stateSure = false;
		var res = $http.post("/pms-web/pms/comment/updateCommodityCommentCountStatus",$scope.copyoldComment);
		res.success(function(data, status, headers, config) {
			$scope.updateResult = data;//返回结果
			if($scope.updateResult){
				$scope.oldComment.commentStatus = $scope.copyoldComment.commentStatus ;
			}else{
				$scope.stateSure = false;
				$scope.message = "更新失败！";
				showPopup('showInfo',true);
			}
		});
	}
	//显示全评论
	$scope.showAll = function(index,sta) {
		$scope.commentListPage[index].showNewCommentContent = sta;
	}
	//截取文字列位数
	$scope.subStringObject = function(text,size) {
		if(text == null && size == null){
			return;
		}
		if(isNaN(size)){
			return
		}
		if(text.length < size){
			size = text.length;
		}
		return text.substring(0,size);
	}
	$scope.showPicture = function(url){
		$scope.chosePictureUrl = url;
		showPopup('showPic',true);
	}
});
function loadSecMenu(url) {
	window.location.href = url;
}