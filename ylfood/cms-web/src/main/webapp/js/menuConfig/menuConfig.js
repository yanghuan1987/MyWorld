var app = angular.module('menuApp', ['dPagination']);
app.controller('menuctrl', function($scope,$http) {
	// 全局数字定义,在判断语句中传入变量即可,解除硬编码
	var numberZero = 0;
	var numberOne = 1;
	var numberTwo = 2;
	var numberThree = 3;
	var numberFive = 5;
	var numberEight = 8;
	
	// 显示菜单名
	$scope.level1 = "一级";
	$scope.level2 = "二级";
	$scope.level3 = "三级";
	// 控制二三级菜单显示初始化值
	$scope.showlevel2 = "";
	$scope.showlevel3 = "";
	// 分页数据初始化
	$scope.pageNum = 1;
	$scope.total = 1;
	$scope.pageSize = 5;
	

	// 更新分页
	$scope.updatePagination = function(){
		$scope.pagination.total = $scope.total; //更新表格行数
		$scope.pagination.pageSize = $scope.pageSize; //更新表格行数
		$scope.pagination.pageNum = $scope.pageNum; //更新页码
		// 初始化的商品编码和名称
		var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
		// 调分页方法跳转到指定页码
		$scope.serachPage($scope.pagination.pageNum,$scope.pagination.pageSize,commodityinfo);
	}
	
	// 前台分页的数据结构,通过接口获取商品数据
	$scope.pagination = {
	        pageNum: $scope.pageNum,
	        pageSize: $scope.pageSize,
	        total: $scope.total,
	        onChange: function(pageNum){
	        	// 初始化没定义直接结束方法
				if($scope.initd == undefined)
					return;
				// 初始化的商品编码和名称
				var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
				// 调分页方法跳转到指定页码
				$scope.serachPage($scope.pagination.pageNum,$scope.pagination.pageSize,commodityinfo);
			}
	};
	// 根据页码跳转到指定页
	$scope.serachPage = function(pageNumber,pageSize,bean){
		// 根据前台传过来的分页数据从后台查询数据
		var res = $http.post("getCommdityInfo?categoryCode="+$scope.categoryCode
				+"&pageNum="+pageNumber
				+"&pageSize="+pageSize,bean);
		res.success(function(data, status, headers, config) {
			if(data.status == 'success'){ // 成功对数据页面进行更新
				$scope.commodityList = data.result.result;
				$scope.pagination.currentPage = data.result.pageNum;
				$scope.pagination.totalItems = data.result.total;
				$scope.pagination.itemsPerPage = data.result.pageSize;
				// 每页条数
				var cb = numberFive ;
				// 如果条数不足5条,向数组不足5条数据
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;
				$scope.tempList = [cb];
				// 循环补足五条数据
				while(cb > numberZero){
					$scope.tempList.push([cb]);
					cb--;
				}
				$scope.tempList.pop();
			}else if(data.status == 'error'){
				// 没拿到数据,设置初始值为0,返回0条数据
				$scope.commodityList = null;
				$scope.pagination.pageNum = numberZero;
				$scope.pagination.total = numberZero;
				$scope.pagination.pageSize = numberZero;
				$scope.pageNum = numberZero;
				$scope.total = numberZero;
				$scope.pageSize = numberZero;
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('menupopHint',true);//弹出提示框
			}
		
		}).error(function(){
			
		});
	}
	
	// 初始化页面获取数据
	var res = $http.post("getMenuInfo");
	res.success(function(data,status,headers,config){
		if(data.status == 'success'){
			$scope.menulist = data.result;
			// 初始化数据，设置showflg与level默认值,控制按钮菜单显示
			for(i=0;i<$scope.menulist.length;i++){
				if($scope.menulist[i].menuCode.length == numberTwo){
					$scope.menulist[i] = 
					{menuCode:$scope.menulist[i].menuCode,
							name:$scope.menulist[i].name,
								visualable:$scope.menulist[i].visualable,
									displayOrder:$scope.menulist[i].displayOrder,
										parent:$scope.menulist[i].parent,
											commodityCode:$scope.menulist[i].commodityCode,
												showflg:true,
												level:"一级",isopen:false};
				}else if($scope.menulist[i].menuCode.length == numberFive){
					$scope.menulist[i] = 
					{menuCode:$scope.menulist[i].menuCode,
							name:$scope.menulist[i].name,
								visualable:$scope.menulist[i].visualable,
									displayOrder:$scope.menulist[i].displayOrder,
										parent:$scope.menulist[i].parent,
											commodityCode:$scope.menulist[i].commodityCode,
												showflg:true,
												level:"二级",isopen:false};
				}else{
					$scope.menulist[i] = 
					{menuCode:$scope.menulist[i].menuCode,
							name:$scope.menulist[i].name,
								visualable:$scope.menulist[i].visualable,
									displayOrder:$scope.menulist[i].displayOrder,
										parent:$scope.menulist[i].parent,
											commodityCode:$scope.menulist[i].commodityCode,
												showflg:true,
												level:"三级"};
				}
				// 根据数据库visualable值为0,checkbox默认不显示;1表示显示
				if($scope.menulist[i].visualable == numberZero){
					$scope.menulist[i] = 
					{menuCode:$scope.menulist[i].menuCode,
							name:$scope.menulist[i].name,
								visualable:$scope.menulist[i].visualable,
									displayOrder:$scope.menulist[i].displayOrder,
										parent:$scope.menulist[i].parent,
											commodityCode:$scope.menulist[i].commodityCode,
												showflg:$scope.menulist[i].showflg,
												level:$scope.menulist[i].level,
												showBox:false,
												check:false};
				}else{
					$scope.menulist[i] = 
					{menuCode:$scope.menulist[i].menuCode,
							name:$scope.menulist[i].name,
								visualable:$scope.menulist[i].visualable,
									displayOrder:$scope.menulist[i].displayOrder,
										parent:$scope.menulist[i].parent,
											commodityCode:$scope.menulist[i].commodityCode,
												showflg:$scope.menulist[i].showflg,
													level:$scope.menulist[i].level,
													showBox:true,
												check:true};
				}
			}
			//copy一个旧的对象用于取消时还原数据
			$scope.oldmenulist = angular.copy($scope.menulist);
			
		}else if(data.status == 'error'){
    		$scope.errorMessage = data.messages;//传递错误提示信息
    		showPopHint('menupopHint',true);//弹出提示框
		}
	}).error(function(){
		
	});
	
	//同步数据
	$scope.synchroCategoryInfo = function(){
		var res = $http.post("synchroCategoryInfo");
		res.success(function(data,status,headers,config){
			if(data.status == 'success'){
				$scope.menulist = data.result;
				//初始化数据，设置showflg与level默认值,控制按钮菜单显示
				for(i=0;i<$scope.menulist.length;i++){
					if($scope.menulist[i].menuCode.length == numberTwo){
						$scope.menulist[i] = 
						{menuCode:$scope.menulist[i].menuCode,
								name:$scope.menulist[i].name,
									visualable:$scope.menulist[i].visualable,
										displayOrder:$scope.menulist[i].displayOrder,
											parent:$scope.menulist[i].parent,
												commodityCode:$scope.menulist[i].commodityCode,
													showflg:true,
													level:"一级"};
					}else if($scope.menulist[i].menuCode.length == numberFive){
						$scope.menulist[i] = 
						{menuCode:$scope.menulist[i].menuCode,
								name:$scope.menulist[i].name,
									visualable:$scope.menulist[i].visualable,
										displayOrder:$scope.menulist[i].displayOrder,
											parent:$scope.menulist[i].parent,
												commodityCode:$scope.menulist[i].commodityCode,
													showflg:true,
													level:"二级"};
					}else{
						$scope.menulist[i] = 
						{menuCode:$scope.menulist[i].menuCode,
								name:$scope.menulist[i].name,
									visualable:$scope.menulist[i].visualable,
										displayOrder:$scope.menulist[i].displayOrder,
											parent:$scope.menulist[i].parent,
												commodityCode:$scope.menulist[i].commodityCode,
													showflg:true,
													level:"三级"};
					}
					// 根据数据库visualable值为0,checkbox默认不显示;1表示显示
					if($scope.menulist[i].visualable == numberZero){
						$scope.menulist[i] = 
						{menuCode:$scope.menulist[i].menuCode,
								name:$scope.menulist[i].name,
									visualable:$scope.menulist[i].visualable,
										displayOrder:$scope.menulist[i].displayOrder,
											parent:$scope.menulist[i].parent,
												commodityCode:$scope.menulist[i].commodityCode,
													showflg:$scope.menulist[i].showflg,
													level:$scope.menulist[i].level,
													showBox:false,
													check:false};
					}else{
						$scope.menulist[i] = 
						{menuCode:$scope.menulist[i].menuCode,
								name:$scope.menulist[i].name,
									visualable:$scope.menulist[i].visualable,
										displayOrder:$scope.menulist[i].displayOrder,
											parent:$scope.menulist[i].parent,
												commodityCode:$scope.menulist[i].commodityCode,
													showflg:$scope.menulist[i].showflg,
														level:$scope.menulist[i].level,
														showBox:true,
													check:true};
					}
				}
				

				//copy一个旧的对象用于取消时还原数据
				$scope.oldmenulist = angular.copy($scope.menulist);
				//收起所有菜单，初始化显示flg
				$scope.showlevel2 = "";
				$scope.showlevel3 = "";
				// 关闭显示的菜单
				for(i=0;i<$scope.menulist.length;i++){
					$scope.menulist[i].isopen = false;
				}
				
				$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('menupopHint',true);//弹出提示框
			}else if(data.status == 'error'){
	    		$scope.errorMessage = "同步失败";//传递错误提示信息
	    		showPopHint('menupopHint',true);//弹出提示框
			}
		}).error(function(){
			
		});
	}
	//编辑按钮
	$scope.selectmune = function(index){
		// 显示保存和取消按钮
		$scope.menulist[index].showflg = false;
		// 显示复选框
		$scope.menulist[index].showBox = true;
		// 缓存一个点击编辑时的状态,便于后面点击保存按钮使用
		$scope.showflgList=$scope.menulist;
	}
	// 确定变换的是几级菜单排序,不同的级数返回不同的值
	function menuLevel(index){
		if($scope.menulist[index].menuCode.length==numberTwo){
			return numberOne;
		}else if($scope.menulist[index].menuCode.length==numberFive){
			return numberTwo;
		}else if($scope.menulist[index].menuCode.length==numberEight){
			return numberThree;
		}
	}
	$scope.flg = false;
	//保存按钮
	$scope.savedata = function(index){
		if ($scope.flg) {
			return;
		}
		$scope.flg = true;
		// 缓存指定的索引,后面方法中使用
		$scope.tempIndex=index;
		// 验证,如果勾选商品编码为必填
		if ($scope.menulist[index].check && $scope.menulist[index].menuCode.length == numberTwo) {
			if (!$scope.menulist[index].commodityCode) {
				$scope.errorMessage = "请选择商品编码!";
				showPopHint('menuPopHint');
				$scope.flg = false;
				return;
			}
		}
		// 验证,父菜单不显示,子菜单不能显示
		if($scope.menulist[index].check){
			for(i=0;i<$scope.menulist.length;i++){
				if($scope.menulist[index].parent == $scope.menulist[i].menuCode){
					if($scope.menulist[i].visualable == numberZero || $scope.oldmenulist[i].visualable == numberZero){
						$scope.errorMessage = "父菜单为不显示,子菜单不能设置成显示!";
						showPopHint('menuPopHint');
						$scope.flg = false;
						return;
					}
				}
			}
		}
		// 验证,一级菜单如果显示,商品编码必填
		if($scope.menulist[index].menuCode.length == numberTwo && !$scope.menulist[index].commodityCode){
			showPopup('popDialogue',true);
			$scope.flg = false;
			return;
		}
		// checkbox赋值,选中赋值1,没选中赋值0
		if($scope.menulist[index].check){
			$scope.menulist[index].visualable = numberOne;
		}else{
			$scope.menulist[index].visualable = numberZero;
		}
		//更新对象赋值
		$scope.update = angular.copy($scope.menulist[index]);
		//更新数据
		var res = $http.post("updateMenu", $scope.update);
		res.success(function(data,status,headers,config){
			if(data.status == 'success'){
				// 重新获取数据
				var res = $http.post("getMenuInfo");
				res.success(function(data,status,headers,config){
					if(data.status == 'success'){
						// 初始化数据,考虑按钮的显示与复选框的显示来赋值
						for(i=0;i<data.result.length;i++){
							// 根据获得的数据,更新是否显示和复制的是否显示值
							$scope.menulist[i].visualable = data.result[i].visualable;
							$scope.oldmenulist[i].visualable = data.result[i].visualable;
							// 判断或的数据的显示状态给复选框的是否选中赋值
							if(data.result[i].visualable == numberZero){
								$scope.oldmenulist[i].check=false;
							}else{
								$scope.oldmenulist[i].check=true;
							}
							// 根据页面中的showflg状态给显示和选中复选框赋值
							if($scope.menulist[i].showflg){
								// 如果从后台获得的是否显示是0,那么设置选中和显示复选框的数据如下
								if (data.result[i].visualable == numberZero) {
									$scope.menulist[i].check = false;
									$scope.menulist[i].showBox=false;
								}
							}
						}
						// 根据返回的数据不显示visualable=0 的菜单
						if ($scope.menulist[index].visualable == numberZero) {
							$scope.menulist[index].showBox=false;
						} 
						$scope.menulist[index].showflg=true;
						//copy一个旧的对象用于取消时还原数据
						$scope.oldmenulist[index] = angular.copy($scope.menulist[index]);
						$scope.flg = false;
					}else if(data.status == 'error'){
			    		$scope.errorMessage = data.messages;//传递错误提示信息
			    		showPopHint('menuPopHint');//弹出提示框
					}
				}).error(function(){
					
				});
				
				//旧有LIST同步更新
				$scope.oldmenulist[index] = angular.copy($scope.menulist[index]); 
			}else if(data.status == 'error'){
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('menuPopHint');//弹出提示框
			}
		}).error(function(){
			
		});
		// 保存成功隐藏保存取消按钮
		$scope.menulist[index].showflg = true;
		
	}
	
	//取消按钮
	$scope.cancel = function(index){
		//取消时把旧的数据还原到使用中的list
		$scope.menulist[index] = angular.copy($scope.oldmenulist[index]);
		// 保存和取消按钮的不显示
		$scope.menulist[index].showflg = true;
		// 根据是否显示的值来设置复选框是否显示
		if ($scope.menulist[index].visualable == numberZero) {
			$scope.menulist[index].showBox = false;
		} else {
			$scope.menulist[index].showBox = true;
		}
	}
	
	//商品弹出框
	$scope.getCommodityinfo = function(index){
		// 初始化商品值
		$scope.commodityCode = null;
		$scope.commodityName = null;
		$scope.choseMenuIndex = index;
		$scope.categoryCode = $scope.menulist[index].menuCode;
		$scope.categoryName = $scope.menulist[index].name;
		// 给商品代码和商品名字赋值
		var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
		// 发送请求到后台拿到第一页的商品数据
		var res = $http.post("getCommdityInfo?categoryCode="+$scope.categoryCode
				+"&pageNum="+numberOne+"&pageSize="+numberFive, commodityinfo);
		res.success(function(data, status, headers, config) {
			if(data.status == 'success'){
				// 成功获得数据设置当前页分页数据
				$scope.pageNum = data.result.pageNum;
				$scope.total = data.result.total;
				$scope.pageSize = data.result.pageSize;
				// 给插件分页数据赋值
				$scope.pagination.pageNum = data.result.pageNum;
				$scope.pagination.total = data.result.total;
				$scope.pagination.pageSize = data.result.pageSize;
				$scope.commodityList = data.result.result;
				// 当前每页数量
				var cb = numberFive ;
				// 当前获得数据的列表不为空填满五条数据
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;
				$scope.tempList = [cb];
				// 循环填满五条数据
				while(cb > numberZero){
					$scope.tempList.push([cb]);
					cb--;
				}
				$scope.tempList.pop();
				$scope.initd = "0";
				// 弹出提示框
				var pop = showPopup('menupopUpHg',true);
			}else if(data.status == 'error'){
				// 没拿到数据,设置初始值为0,返回0条数据
				$scope.commodityList = null;
				$scope.pagination.pageNum = numberZero;
				$scope.pagination.total = numberZero;
				$scope.pagination.pageSize = numberZero;
				$scope.pageNum = numberZero;
				$scope.total = numberZero;
				$scope.pageSize = numberZero;
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('menuPopHint');//弹出提示框
			}
		}).error(function(){
			
		});
		
		
	};
	
	// 根据编码搜索商品信息，模糊匹配
	$scope.searchCommotyByCode = function(){
		// 初始化商品数据,编码和名称
		var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
		// 通过商品编码,商品信息,查询第一页数据
		var res = $http.post("getCommdityInfo?categoryCode="+$scope.categoryCode
				+"&pageNum="+numberOne+"&pageSize="+numberFive,commodityinfo);
		res.success(function(data, status, headers, config) {
			if(data.status == 'success'){
				// 把返回的页码赋值给页面
				$scope.pageNum = data.result.pageNum;
				$scope.total = data.result.total;
				$scope.pageSize = data.result.pageSize;
				// 返回页码赋值给插件
				$scope.pagination.pageNum = data.result.pageNum;
				$scope.pagination.total = data.result.total;
				$scope.pagination.pageSize = data.result.pageSize;
				// 返回的商品信息数据赋值
				$scope.commodityList = data.result.result;
				// 每页五条数据
				var cb = numberFive ;
				// 当前获得数据的列表不为空填满五条数据
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;
				$scope.tempList = [cb];
				// 循环填满五条数据
				while(cb > numberZero){
					$scope.tempList.push([cb]);
					cb--;
				}
				$scope.tempList.pop();
				// 定义初始化数据
				$scope.initd = "0";
			}else if(data.status == 'error'){
				// 没拿到数据,设置初始值为0,返回0条数据
				$scope.commodityList = null;
				$scope.pagination.pageNum = numberZero;
				$scope.pagination.total = numberZero;
				$scope.pagination.pageSize = numberZero;
				$scope.pageNum = numberZero;
				$scope.total = numberZero;
				$scope.pageSize = numberZero;
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('menuPopHint');//弹出提示框
			}
		});
	};
	
	//选择商品
	$scope.choseCommodity = function(index){
		// 把选定的商品编码赋值给菜单对象的商品编码字段
		$scope.menulist[$scope.choseMenuIndex].commodityCode = $scope.commodityList[index].commodityCode;
		// 关闭对话框
		var exit = exitPop('menupopUpHg');
	};
	
	//选择菜单等级-菜单树的显示
	$scope.choseLevel = function(index){
		//选择一级菜单时，显示二级，清空三级;判断是一级菜单执行if中的代码
		if($scope.menulist[index].menuCode.length == numberTwo){
			// 如果一级菜单是关闭的就打开
			if(!$scope.menulist[index].isopen){
				// 把一级菜单的编码赋值给showlevel2用于前台jsp页面ng-if是否显示用
				$scope.showlevel2 = $scope.menulist[index].menuCode;
				// 清空三级菜单
				$scope.showlevel3 = "";
				// 打开一级菜单
				$scope.menulist[index].isopen = true;
			}else{
				// 一级菜单是显示状态就不显示二级三级菜单
				$scope.showlevel2 = "";
				$scope.showlevel3 = "";
				// 循环关闭所有的菜单
				for(i=0;i<$scope.menulist.length;i++){
					$scope.menulist[i].isopen = false;
				}
			}
		}
		//选择二级菜单时，显示三级
		if($scope.menulist[index].menuCode.length == numberFive){
			// 判断二级菜单是否是关闭状态
			if(!$scope.menulist[index].isopen){
				// 关闭状态时,把二级菜单的编码赋值给showlevel3用于前台jsp页面ng-if是否显示用
				$scope.showlevel3 = $scope.menulist[index].menuCode;
				// 打开二级菜单
				$scope.menulist[index].isopen = true;
			}else{
				// 打开状态时;关闭三级
				$scope.showlevel3 = "";
				$scope.menulist[index].isopen = false;
			}
		}
	}
	
	// 弹出框确定按钮
	$scope.confirm=function(){
		// 根据目前复选框的状态给visualable,是否显示赋值
		if($scope.menulist[$scope.tempIndex].check){
			$scope.menulist[$scope.tempIndex].visualable = numberOne;
		}else{
			$scope.menulist[$scope.tempIndex].visualable = numberZero;
		}
		//更新对象赋值
		$scope.update = angular.copy($scope.menulist[$scope.tempIndex]);
		//更新数据
		var res = $http.post("updateMenu", $scope.update);
		res.success(function(data,status,headers,config){
			if(data.status == 'success'){
				var res = $http.post("getMenuInfo");
				res.success(function(data,status,headers,config){
					if(data.status == 'success'){
						// 初始化数据,考虑按钮的显示与复选框的显示来赋值
						for(i=0;i<data.result.length;i++){
							// 根据获得的数据,更新是否显示和复制的是否显示值
							$scope.menulist[i].visualable = data.result[i].visualable;
							$scope.oldmenulist[i].visualable = data.result[i].visualable;
							// 判断或的数据的显示状态给复选框的是否选中赋值
							if(data.result[i].visualable == numberZero){
								$scope.oldmenulist[i].check=false;
							}else{
								$scope.oldmenulist[i].check=true;
							}
							// 根据页面中的showflg状态给显示和选中复选框赋值
							if($scope.menulist[i].showflg){
								// 如果从后台获得的是否显示是0,那么设置选中和显示复选框的数据如下
								if (data.result[i].visualable == numberZero) {
									$scope.menulist[i].check = false;
									$scope.menulist[i].showBox=false;
								}
							}
						}
						// 根据返回的数据不显示visualable=0 的菜单
						if ($scope.menulist[$scope.tempIndex].visualable == numberZero) {
							$scope.menulist[$scope.tempIndex].showBox=false;
						} 
						$scope.menulist[$scope.tempIndex].showflg=true;
						//copy一个旧的对象用于取消时还原数据
						$scope.oldmenulist[$scope.tempIndex] = angular.copy($scope.menulist[$scope.tempIndex]);
					}else if(data.status == 'error'){
			    		$scope.errorMessage = data.messages;//传递错误提示信息
			    		showPopHint('menuPopHint');//弹出提示框
					}
				}).error(function(){
					
				});
				//旧有LIST同步更新
				$scope.oldmenulist[$scope.tempIndex] = angular.copy($scope.menulist[$scope.tempIndex]); 
			}else if(data.status == 'error'){
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('menuPopHint');//弹出提示框
			}
		}).error(function(){
			
		});
		// 保存成功隐藏保存和取消按钮
		$scope.menulist[$scope.tempIndex].showflg = true;
		// 关闭对话框
		exitPop('popDialogue');
	}
	
	// 保存前检查数据
	$scope.checkOrder=function(index){
		// 判断是几及菜单
		var num=menuLevel(index);
		// 拼接Id
		var a="#level"+num;
		// 拿到指定的级数DOM对象,并设置其值为空串
		angular.element(a+index).html("");
		// 拿到指定的保存DOM对象缓存起来
		var buttom=angular.element("#saveMenu"+index);
		// 把此按钮设置为不可用
		buttom.attr("disabled",true);
		// 缓存指定的索引,便于后面的方法使用
		$scope.tempIndex=index;
		
		// 显示状态,排序不为空且不等于空串进行检查
		if(numberOne == $scope.menulist[index].visualable 
				|| (null != $scope.menulist[index].displayOrder 
					&& "" != $scope.menulist[index].displayOrder)){
			
			// 排序数字ckeck
			if(isNaN($scope.menulist[index].displayOrder)){
				angular.element(a+index).html("排序必须为数字!");
				return;
			}
			
			// 排序不能为空
			if($scope.menulist[index].check&&""==$scope.menulist[index].displayOrder){
				angular.element(a+index).html("排序数字不能为空!");
				return;
				
			}
			//排序1-100之间
			if(Number($scope.menulist[index].displayOrder)<=numberZero || Number($scope.menulist[index].displayOrder)>100){
				$scope.errorMessage = "请输入1至100之间的正整数!";
				angular.element(a+index).html($scope.errorMessage);
				return;
			}
			
			// 排序不能是小数，只能是正整数
			var reg = /^[0-9]*[1-9][0-9]*$/;
			if(!reg.test($scope.menulist[index].displayOrder)){
				angular.element(a+index).html("请输入1至100之间的正整数！");
				return;
			}
			// 排序重复检查
			for(i=0;i<$scope.menulist.length;i++){
				// 点击对象输入的菜单编码和对象数组中编码不一样
				if($scope.menulist[index].menuCode != $scope.menulist[i].menuCode){
					// 父菜单编码一样,说明是一个父菜单的子菜单
					if($scope.menulist[index].parent == $scope.menulist[i].parent){
						// 输入的排序和缓存排序一样
						if($scope.menulist[index].displayOrder == $scope.menulist[i].displayOrder){
							angular.element(a+index).html("该排序已存在，请重新输入!");
							return;
						}
					}
				}
			}
		}
		// 禁用按钮
		buttom.attr("disabled",false);
	}
	
	// 排序验证,复选框为选择状态时,必须填入排序
	$scope.clickBox=function(index){
		// 拿到是几级菜单
		var num=menuLevel(index);
		// 拼接得到Id
		var a="#level"+num;
		// 拿到指定的DOM并设置按钮为不可用
		var buttom=angular.element("#saveMenu"+index);
		// checkbox赋值,选中赋值1,没选中赋值0
		if($scope.menulist[index].check){
			$scope.menulist[index].visualable = numberOne;
		}else{
			$scope.menulist[index].visualable = numberZero;
		}
		// 复选框是选中状态,执行if;排序不能为空
		if($scope.menulist[index].check){
			// 排序不为空执行if
			if(""==$scope.menulist[index].displayOrder||undefined==$scope.menulist[index].displayOrder){
				buttom.attr("disabled",true);
				angular.element(a+index).html("排序数字不能为空!");
			}
		}else{
			// 复选框不是选中状态,排序可以为空
			if("排序数字不能为空!"==angular.element(a+index).html()){
				angular.element(a+index).html("");
			}
			// 设置保存按钮可用
			buttom.attr("disabled",false);
		}
	}	
});

// 跳转页面
function loadSecMenu(url) {
	window.location.href = url;
}

// 窗体加载完事件对选择模块背景颜色的改变
onload=function(){
	angular.element(".menuConfig").css("background-color","#99c731");
}
