angular
		.module('rawMaterialsApp', [ 'dPagination', 'pictureApp' ])
		.controller(
				'rawMaterialsController',
				function($scope, $rootScope, $http) {

					/*
					 * showpage显示页面(页面均为互斥) 
					 * 0---全不显示
					 * 1---检索页面 
					 * 3---辅材产品table 
					 * 8---辅材新增 
					 * 9---辅材编辑
					 * 
					 */
					$rootScope.showPage = 0;//显示页面
					$rootScope.SourcePage = 0;//跳转前页面

					$scope.editRawMaterials = false;// 普通产品编辑页面不显示
					$scope.doubleC = 0;// 防止表单重复提交
					$scope.doubleClick = false;// 防止表单重复提交
					$scope.newEditQuit = true;// 新增页面是否放弃当前编辑FLG
					$scope.newEditCount = 0;// 新增页面是否放弃当前编辑FLG
					$scope.oldEditQuit = true;// 编辑页面是否放弃当前编辑FLG
					$scope.colorone = "";// 菜单背景颜色
					$scope.pageNum = 1;// 翻页页数
					$scope.total = 1;// 总共条数
					$scope.pageSize = 15;// 每页件数
					$rootScope.isProduct = true;// 用于详细图片BUTTON显示，表示此为产品业务
					$rootScope.pictureCheck = true;// 是否check图片
					$rootScope.UnitDisable = true;// 采购基本单位disable
					$rootScope.showPage = 1;// 详细检索页面
					$rootScope.SearchType = 0;// 详细检索页面
					$scope.showNewButton = false;// 显示新增按钮
					$scope.rawMaterialsBGC = "#99C731";
					// 获取品类菜单
					$http.get("GetCatagoryList").success(function(data) {
						$scope.categoryList = data;
					});

					// 获取所有的包装单位列表，例如瓶、袋、箱、框等等。
					$http.get("getBasicPackageUnit").success(function(data) {
						$rootScope.specificationSecondValues = data;
					});
					// 获取辅材基本单位
					$http.get("getSecondaryRawMaterialsUnit").success(function(data) {
						$rootScope.RawMaterialsUnit = data;
					});
					// 获取温区
					$http.get("getRawMaterialsTemperatureZone").success(
							function(data) {
								$scope.severProductTemperatureZone = data;
							});

					// 注销
					$scope.loginOut = function() {
						window.location.href = serverPath;
					};
					$scope.showSearchPage = function() {
						// 离开新增确认框
						if (!$scope.newEditQuit) {
							var con = confirm("您当前正处于新增状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.newEditQuit = true;// 放弃当前编辑
								$scope.newEditCount = 0;// 确认放弃，次数归0
							}
						}
						// 离开编辑要么确认
						if (!$scope.oldEditQuit) {
							var con = confirm("您当前正处于编辑状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.oldEditQuit = true;// 放弃当前编辑
							}
						}
						$rootScope.showPage = 1;// 详细检索页面
						$scope.RawMaterialses = null;
						$scope.colorones = "#99C731";// 菜单背景颜色
						$scope.colorone = "";// 菜单背景颜色
						$scope.clearDetail();
					}
					// 左侧二菜单显示
					$scope.updateOne = function(updatecatone) {
						// 离开新增确认框
						if (!$scope.newEditQuit) {
							var con = confirm("您当前正处于新增状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.newEditQuit = true;// 放弃当前编辑
								$scope.newEditCount = 0;// 确认放弃，次数归0
							}
						}
						// 离开编辑确认框
						if (!$scope.oldEditQuit) {
							var con = confirm("您当前正处于编辑状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.oldEditQuit = true;// 放弃当前编辑
							}
						}
						$rootScope.choseCategory = updatecatone;
						$rootScope.SourcePage = 0;//跳转前页面
						$scope.colorone = "#99C731";// 菜单背景颜色
						$scope.colorones = "";// 菜单背景颜色
						$rootScope.categoryBotton = 1;// 详细检索页面
						$rootScope.SearchType = 0;// 详细检索页面
						$scope.categoryCode = updatecatone.categoryCode;// 品类code
						$scope.rawMaterialsType = updatecatone.categoryTypeFlag;// 组合flg
						// 不是辅材
						if (updatecatone.categoryTypeFlag != 2) {
							$rootScope.showPage = 2;// 产品table显示
						} else {
							$rootScope.showPage = 3;// 辅材table
						}
						$scope.showNewButton = false;// 显示新增按钮
						$scope.categoryName = null;// 品类名字
						// 获取选择品类子品类信息
						$scope.category = {
							categoryName : updatecatone.categoryName,
							categoryCode : updatecatone.categoryCode,
							id : updatecatone.id
						};
						$http.post(
								"/pms-web/pms/category/selectCategoryChildren?categoryCode="
										+ updatecatone.categoryCode).success(
								function(data) {
									updatecatone.productCategorys = data;

									// 执行查询部分逻辑, 获取产品以及产品属性的信息的 初始查询
									$scope.productCategoryProperties = null;
									$scope.selectByPage($scope.pageNum,
											$scope.pageSize,
											updatecatone.categoryCode, null,
											null,
											updatecatone.categoryTypeFlag
											, null
											, null
											, null);
									$scope.initd = 0;
								});
						// 显示和收起菜单
						if ($scope.spMenuShow != updatecatone.id) {
							$scope.spMenuShow = updatecatone.id;
						} else {
							$scope.spMenuShow = undefined;
						}

					};
					// 三级菜单表示
					$scope.updateTwo = function(cat1name, updatecattwo) {
						// 离开新增确认框
						if (!$scope.newEditQuit) {
							var con = confirm("您当前正处于新增状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.newEditQuit = true;// 放弃当前编辑
								$scope.newEditCount = 0;// 确认放弃，次数归0
							}
						}
						// 离开编辑确认框
						if (!$scope.oldEditQuit) {
							var con = confirm("您当前正处于编辑状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.oldEditQuit = true;// 放弃当前编辑
							}
						}
						$rootScope.choseCategory = updatecattwo;
						// 获取选择品类子品类信息
						$scope.colorones = "";// 菜单背景颜色
						// 不是辅材
						if (updatecattwo.categoryTypeFlag != 2) {
							$rootScope.showPage = 2;// 产品table显示
						} else {
							$rootScope.showPage = 3;// 辅材table
						}
						$rootScope.categoryBotton = 2;// 详细检索页面
						$rootScope.SourcePage = 0;//跳转前页面
						$rootScope.SearchType = 0;// 详细检索页面
						$scope.showNewButton = false;// 显示新增按钮
						$scope.categoryName = null;// 品类名字
						$scope.rawMaterialsType = updatecattwo.categoryTypeFlag;// 组合flg
						$scope.categoryCode = updatecattwo.categoryCode;// 品类code
						$scope.category = {
							categoryName : updatecattwo.categoryName,
							categoryCode : updatecattwo.categoryCode,
							id : updatecattwo.id
						};
						$http.post(
								"/pms-web/pms/category/selectCategoryChildren?categoryCode="
										+ updatecattwo.categoryCode).success(
								function(data) {
									updatecattwo.productCategorys = data;

									// 执行查询部分逻辑, 获取产品以及产品属性的信息的 初始查询
									$scope.productCategoryProperties = null;
									$scope.selectByPage(
											$scope.pageNum,
											$scope.pageSize,
											updatecattwo.categoryCode, 
											null,
											null,
											null,
											null,
											null, 
											null);
									$scope.initd = 0;
								});
						// 显示和收起菜单
						if ($scope.spMenuShowT != updatecattwo.id) {
							$scope.spMenuShowT = updatecattwo.id;
						} else {
							$scope.spMenuShowT = undefined;
						}
					};
					// 依据条件查询产品列表信息
					$scope.selectByPage = function(pageNum, pageSize,
							categoryCode, rawMaterialsCode, rawMaterialsProperty,
							rawMaterialsType, rawMaterialsGs1Code,
							rawMaterialsName, 
							rawMaterialsTemperatureZoneCode) {
						if ($rootScope.SearchType == 1) {
							var temp = {
								pageNum : pageNum,// 翻页页数
								pageSize : pageSize,// 每页件数
								categoryCode : categoryCode,// 品类code
								rawMaterialsCode : rawMaterialsCode,// 产品code
								rawMaterialsProperty : null,// 产品属性
								rawMaterialsType : rawMaterialsType,// 是否组合
								rawMaterialsGs1Code : rawMaterialsGs1Code,// GS1编码
								rawMaterialsName : rawMaterialsName,// 产品名称
								rawMaterialsTemperatureZoneCode : rawMaterialsTemperatureZoneCode
							// 产品温区
							}
						} else {
							var temp = {
								pageNum : pageNum,// 翻页页数
								pageSize : pageSize,// 每页件数
								categoryCode : categoryCode,// 品类code
								rawMaterialsCode : rawMaterialsCode,// 产品code
								rawMaterialsProperty : rawMaterialsProperty,// 产品属性
								rawMaterialsType : rawMaterialsType
							// 是否组合
							}
						}
						$http.post("selectByPage", temp).success(
								function(data) {
									$scope.pagination.total = data.total;// 返回结果总共条数
									$scope.pagination.pageNum = data.pageNum;// 返回结果页数
									$scope.pagination.pageSize = data.pageSize;// 返回结果每页件数
									$scope.RawMaterialses = data.result;// 返回结果
									var cb = $scope.pageSize;// 为满足条数时填补空行
									if ($scope.RawMaterialses != undefined
											&& $scope.RawMaterialses != {})
										cb = cb - $scope.RawMaterialses.length;// 计算需要填补几行
									$scope.tempList = [ cb ];// 占位
									while (cb > 0) {
										$scope.tempList.push([ cb ]);// 占位添加
										cb--;
									}
									$scope.tempList.pop();// 去除最后一行
								});
					};

					$scope.rawMaterialsType = 0;// 组合产品
					// 点击左边三级品类时处理的事件：
					$scope.showThree = function(cat3) {
						// 离开新增确认框
						if (!$scope.newEditQuit) {
							var con = confirm("您当前正处于新增状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.newEditQuit = true;// 放弃当前编辑
								$scope.newEditCount = 0;// 确认放弃，次数归0
							}
						}
						// 离开编辑确认框
						if (!$scope.oldEditQuit) {
							var con = confirm("您当前正处于编辑状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.oldEditQuit = true;// 放弃当前编辑
							}
						}
						$rootScope.choseCategory = cat3;
						$rootScope.categoryBotton = 3;// 详细检索页面
						$rootScope.showRawMaterialsBuyUnitEmpty = false;// 包装单位错误表示
						$scope.doubleClick = false;// 二重点击还原
						exitPop('submitInfo');
						$rootScope.SourcePage = 0;//跳转前页面
						// 不是辅材
						if (cat3.categoryTypeFlag != 2) {
							$rootScope.showPage = 2;// 产品table显示
						} else {
							$rootScope.showPage = 3;// 辅材table
						}
						$rootScope.SearchType = 0;// 详细检索页面
						$scope.colorones = "";// 菜单背景颜色
						$scope.showNewButton = true;// 显示新增按钮
						// 检索参数
						$scope.category = {
							id : cat3.id,
							categoryName : cat3.categoryName,
							categoryCode : cat3.categoryCode
						};
						$scope.categoryName = cat3.categoryName;// 品类名字
						$scope.categoryCode = cat3.categoryCode;// 品类code
						$scope.rawMaterialsType = cat3.categoryTypeFlag;// 组合flg
						// 商品编辑页面状态
						$scope.editRawMaterials = false;
						$scope.selected = "default";// 可售默认
						$scope.rawMaterialsProperty = null;// 产品属性空

						// 头部信息查询
						$http.post("getRawMaterialsProperties", {
									categoryCode : $scope.categoryCode
								})
								.success(
										function(data) {
											$scope.productCategoryProperties = data.productCategoryPropertys;
										});

						// 执行查询部分逻辑, 获取产品以及产品属性的信息的 初始查询
						$scope.selectByPage($scope.pageNum, $scope.pageSize,
								$scope.categoryCode, null,
								$scope.rawMaterialsProperty,
								$scope.rawMaterialsType, null, null, null);
						$scope.initd = 0;
					};

					// 前台分页的数据结构
					$scope.pagination = {
						pageNum : $scope.pageNum,// 翻页页数
						total : $scope.total,// 总共条数
						pageSize : $scope.pageSize,// 每页件数
						onChange : function() {
							if ($scope.initd == undefined)
								return;
							if ($rootScope.SearchType == 1) {
								$scope.selectByPage($scope.pagination.pageNum,
										$scope.pagination.pageSize, null,
										$scope.rawMaterialsCodes, null,
										$scope.rawMaterialsGs1Codes,
										$scope.rawMaterialsNames,
										$scope.rawMaterialsTemperatureZoneCodes);
							} else {
								$scope.selectByPage($scope.pagination.pageNum,
										$scope.pagination.pageSize,
										$scope.categoryCode,
										$scope.rawMaterialsCode,
										$scope.rawMaterialsProperty,
										$scope.rawMaterialsType);
							}
						}
					};

					$scope.enterKeyup = function(e,pageNo){
						var keycode = window.event?e.keyCode:e.which;
						if(keycode == 13){
							if(pageNo == 1){
								$scope.searchByRawMaterialsCode('detail');
							}
						}
					}
					
					var RawMaterialses = $scope.RawMaterialses;// 选择产品
					var rawMaterialsCode = $scope.rawMaterialsCode;// 选择code
					$scope.returnPage = function(parm){
						// 离开新增确认框
						if (!$scope.newEditQuit) {
							var con = confirm("您当前正处于新增状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.newEditQuit = true;// 放弃当前编辑
								$scope.newEditCount = 0;// 确认放弃，次数归0
							}
						}
						// 离开编辑确认框
						if (!$scope.oldEditQuit) {
							var con = confirm("您当前正处于编辑状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.oldEditQuit = true;// 放弃当前编辑
							}
						}
						if(parm == 'edit'){
							var returnPage = angular.copy($rootScope.SourcePageEdit);
							$scope.editRawMaterials = false;
							$scope.rowClick($scope.nowRow);
						}else{
							var returnPage = angular.copy($rootScope.SourcePage);
						}
						$rootScope.showPage = returnPage;//显示页面
					}
					// 点击产品编码时触发的事件
					$scope.searchByRawMaterialsCode = function(rawMaterialsCode) {
						if (rawMaterialsCode == 'detail') {
							$rootScope.SourcePage = 1;//跳转前页面
							$rootScope.SearchType = 1;// 详细检索页面
							$scope.selectByPage($scope.pageNum,
									$scope.pageSize, null, $scope.rawMaterialsCodes,
									null,
									null,
									null,
									$scope.rawMaterialsNames,
									$scope.rawMaterialsTemperatureZoneCodes);
							$scope.initd = 0;
						} else {
							$rootScope.SearchType = 0;// 详细检索页面
							$scope.rawMaterialsCode = rawMaterialsCode;
							$scope.selectByPage($scope.pagination.pageNum,
									$scope.pagination.pageSize,
									$scope.categoryCode, $scope.rawMaterialsCode,
									$scope.rawMaterialsProperty, null,
									null,null,null);
						}
					};
					// 选择产品属性时触发的事件
					$scope.searchByProperty = function(index, propertyName,
							propertyValue) {
						$scope.selected = "";
						for (indexTemp in $scope.productCategoryProperties) {
							if (index != indexTemp) {
								$scope.productCategoryProperties[indexTemp].selectedValue = "";
							}
						}

						// 如果点击的是表头，则清空该搜索的条件
						if (propertyValue == undefined) {
							$scope.rawMaterialsProperty = null;
						} else
							$scope.rawMaterialsProperty = {
								rawMaterialsPropertyName : propertyName,
								rawMaterialsPropertyValue : propertyValue
							};
						// 检索翻页
						$scope.selectByPage($scope.pagination.pageNum,
								$scope.pagination.pageSize,
								$scope.categoryCode, null,
								$scope.rawMaterialsProperty,
								$scope.rawMaterialsType,null,null,null);
					};

					$scope.customerRawMaterialsProperties = [];
					$scope.customerRawMaterialsProperty = {};

					// 删除一个自定义属性及属性值
					$scope.onRemoveCustomerPropertyItemClick = function(index) {
						// 新增产品时，删除自定义属性
						if ($scope.customerRawMaterialsProperties != undefined)
							$scope.customerRawMaterialsProperties.splice(index, 1);
						// 编辑产品时，删除自定义属性
						if ($scope.rawMaterials != undefined
								&& $scope.rawMaterials.rawMaterialsProperties != undefined) {
							$scope.rawMaterials.rawMaterialsProperties.splice(index, 1);
						}
					};
					// 新增自定义属性触发的事件
					$scope.onAddCustomerPropertyClick = function(property,
							index) {
						$scope.showcustomerRawMaterialsProperty = false;
						var pop = showPopup('addNewPropertypopUp', true);
						$scope.propertyEdit = false;
						if (property != undefined) {
							$scope.customerRawMaterialsProperty = {
								rawMaterialsPropertyName : property.rawMaterialsPropertyName,
								rawMaterialsPropertyValue : property.rawMaterialsPropertyValue
							};
							$scope.propertyEdit = true;
							$scope.eindex = index;
						}
					};

					// 新添加一个自定义属性及属性值
					$scope.onAddCustomerPropertyItemClick = function(property) {
						if ($scope.customerRawMaterialsProperty.rawMaterialsPropertyName == undefined
								|| $scope.customerRawMaterialsProperty.rawMaterialsPropertyValue == undefined
								|| $scope.customerRawMaterialsProperty.rawMaterialsPropertyValue == ''
								|| $scope.customerRawMaterialsProperty.rawMaterialsPropertyName == '') {
							$scope.showcustomerRawMaterialsProperty = true;
							return;
						}
						$scope.showcustomerRawMaterialsProperty = false;
						property.customerDefineFlag = 1;

						// 编辑状态
						if ($scope.propertyEdit) {
							// 新增编辑
							for (var i = 0; i < $scope.customerRawMaterialsProperties.length; i++) {
								// 修改完成编辑的自定义属性
								if (i == $scope.eindex) {
									$scope.customerRawMaterialsProperties[i].rawMaterialsPropertyName = property.rawMaterialsPropertyName;// 属性名称
									$scope.customerRawMaterialsProperties[i].rawMaterialsPropertyValue = property.rawMaterialsPropertyValue;// 属性值
									$scope.customerRawMaterialsProperties[i].customerDefineFlag = 1;
									break;
								}
							}
							// 修改编辑
							if ($scope.rawMaterials != undefined
									&& $scope.rawMaterials.rawMaterialsProperties != undefined) {
								for (var i = 0; i < $scope.rawMaterials.rawMaterialsProperties.length; i++) {
									if (i == $scope.eindex) {
										$scope.rawMaterials.rawMaterialsProperties[i].rawMaterialsPropertyName = property.rawMaterialsPropertyName;// 属性名称
										$scope.rawMaterials.rawMaterialsProperties[i].rawMaterialsPropertyValue = property.rawMaterialsPropertyValue;// 属性值
										break;
									}
								}
							}
						} else {
							var p = {
								rawMaterialsPropertyName : property.rawMaterialsPropertyName,
								rawMaterialsPropertyValue : property.rawMaterialsPropertyValue,
								customerDefineFlag : property.customerDefineFlag
							};
							if ($scope.rawMaterials != undefined
									&& $scope.rawMaterials.rawMaterialsProperties != undefined) {

								$scope.rawMaterials.rawMaterialsProperties.push(p);
							}
							$scope.customerRawMaterialsProperties.push(property);// 新增属性
						}
						$scope.customerRawMaterialsProperty = {};
						exitPop('addNewPropertypopUp');
					};

					// 删除一个自定义属性及属性值
					$scope.onRemoveRawMaterialsPackUnitClick = function(index) {
						// 新增产品时，删除自定义属性
						if ($rootScope.rawMaterialsPackUnit != undefined)
							$rootScope.rawMaterialsPackUnit.splice(index, 1);
					};
					// 新增自定义属性触发的事件
					$scope.onAddRawMaterialsPackUnitClick = function(rawMaterialsBuyUnit,
							packUnit, index) {
						// 新增时 先选择产品基本单位
						if (undefined == rawMaterialsBuyUnit
								|| null == rawMaterialsBuyUnit
								|| "" == rawMaterialsBuyUnit) {
							$rootScope.showRawMaterialsBuyUnitEmpty = true;
							return;
						} else {
							if (undefined != rawMaterialsBuyUnit.optionName) {
								$rootScope.rawMaterialsBuyUnitShow = rawMaterialsBuyUnit.optionName;
							} else {
								$rootScope.rawMaterialsBuyUnitShow = rawMaterialsBuyUnit;
							}
							$rootScope.showRawMaterialsBuyUnitEmpty = false;
						}

						$scope.checknull = false;
						$scope.checkNumberIf = false;
						$scope.checkNumberOnepoint = false;
						$scope.checkNumberFormat = false;
						$scope.checkunitObjAdd = false;
						var pop = showPopup('addRawMaterialsPackUnit', true);
						$scope.packUnitEdit = false;
						if (packUnit != undefined) {
							$scope.unitValueAdd = packUnit.unitValue
							for (i in $rootScope.specificationSecondValues) {
								if (packUnit.unitCode == $rootScope.specificationSecondValues[i].optionValue) {
									$scope.unitObjAdd = $rootScope.specificationSecondValues[i];
								}
							}
							$scope.packUnitEdit = true;
							$scope.puindex = index;
						} else {
							$scope.unitValueAdd = null;
							$scope.unitObjAdd = null;
						}
					};

					// 新添加一个自定义属性及属性值
					$scope.onAddProductPackUnitItemClick = function(
							unitValueAdd, unitObjAdd) {
						var checkNumber = /^\d+$/;// 正整数
						var checkNumber2 = /^\d+\.\d$/;// 一位小数
						$scope.checknull = false;
						$scope.checkNumberIf = false;
						$scope.checkNumberOnepoint = false;
						$scope.checkNumberFormat = false;
						$scope.checkunitObjAdd = false;
						// 空check
						if ($scope.unitValueFrom.unitValueAdd.$error.required) {
							$scope.checknull = true;
							return;
						}
						// 数字check
						if ($scope.unitValueFrom.unitValueAdd.$error.number
								|| unitValueAdd <= 0) {
							$scope.checkNumberIf = true;
							return;
						}
						var c = unitValueAdd.toString().match(checkNumber);
						if (null == c
								&& null == unitValueAdd.toString().match(
										checkNumber2)
								&& !$rootScope.UnitDisable) {
							$scope.checkNumberOnepoint = true;
							return;
						}
						// 重量单位是小数check
						if (null == c && $rootScope.UnitDisable) {
							$scope.checkNumberFormat = true;
							return;
						}
						// 单位null check
						if (undefined == unitObjAdd || null == unitObjAdd
								|| "" == unitObjAdd) {
							$scope.checkunitObjAdd = true;
							return;
						}


						// 编辑状态
						if ($scope.packUnitEdit) {

							$rootScope.rawMaterialsPackUnit[$scope.puindex].unitName = unitObjAdd.optionName;
							$rootScope.rawMaterialsPackUnit[$scope.puindex].unitCode = unitObjAdd.optionValue;
							$rootScope.rawMaterialsPackUnit[$scope.puindex].unitValue = unitValueAdd;

						} else {
							var p = {
								unitName : unitObjAdd.optionName,
								unitCode : unitObjAdd.optionValue,
								unitValue : unitValueAdd
							};
							if ($rootScope.rawMaterialsPackUnit == undefined
									|| $rootScope.rawMaterialsPackUnit == null) {
								$rootScope.rawMaterialsPackUnit = [];
								$rootScope.rawMaterialsPackUnit.push(p);
							} else {
								$rootScope.rawMaterialsPackUnit.push(p);
							}
						}
						exitPop('addRawMaterialsPackUnit');
					};

					// 点击新增产品按钮是触发的事件
					$scope.onAddRawMaterialsClick = function() {
						if (!$scope.oldEditQuit) {
							var con = confirm("您当前正处于编辑状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.oldEditQuit = true;// 放弃当前编辑
							}
						}
						// 表示已经进入过新增页面
						$scope.newEditCount = $scope.newEditCount + 1;
						// 第二次点击新增
						if ($scope.newEditCount > 1) {
							$scope.message = "您已在新增画面";
							// 模态框隐藏确认按钮
							alert($scope.message);
							return false;
						}
						if ($scope.newEditQuit) {
							$scope.newEditQuit = false;// 是否放弃当前编辑FLG
						}
						// 清空tr背景色
						var trs = document.getElementById('tbone')
								.getElementsByTagName('tr');
						for (var i = 1; i < trs.length; i++) {
							trs[i].style.backgroundColor = '';
						}
						$rootScope.resutAllRawMaterials();
						// 清空图片信息
						$scope.serverGs1Code = true;// 产品GS1编码验证用FLG
						$scope.Gs1Exist = true;// 产品GS1存在FLG

						$rootScope.formHeader = [];// 头部图片判断是否修改集合
						$rootScope.thumbHeader = [];// 页面头部图片集合
						$scope.addedRawMaterials = [];// 清空产品基本信息
						// 清空包装单位
						$rootScope.rawMaterialsPackUnit = [];
						$rootScope.UnitDisable = true;// 采购基本单位disable

						// 自定义属性
						$scope.customerRawMaterialsProperties = [];
						// 图片上传验证
						$rootScope.isHeaderPictureSatified = false;
						$rootScope.isDetailPictureSatified = false;

						// 定义图片属性数据结构:$rootScope.headerPictureSize张头部图片
						// 头部固定数目图片上传
						// 默认图片路径
						pictureAddress = "";
						$rootScope.thumbHeader = new Array(
								$rootScope.headerPictureSize);
						for (var i = 0; i < $rootScope.headerPictureSize; i++) {
							$rootScope.thumbHeader[i] = {
								pictureAddress : pictureAddress
							};
						}

						$rootScope.thumbDetail = [];// 页面详细图片集合
						$rootScope.formDetail = [];// 判断详细图片是否修改集合

						// 组合产品和标准产品的信息显示不同。
						// 标准产品的显示
						if ($scope.rawMaterialsType == 0) {
							
						} else if ($scope.rawMaterialsType == 1) {
							
						} else if ($scope.rawMaterialsType == 2) {// 辅材
							// 清空辅材信息
							$scope.rawMaterials = {};
							$rootScope.showPage = 8;
							$rootScope.SourcePage = 3;//跳转前页面
						}
					};

					// 点击产品列表中的产品项是触发的事件
					$scope.rowClick = function(index,edited) {
						// 离开新增状态确认
						if (!$scope.newEditQuit) {
							var con = confirm("您当前正处于新增状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.newEditQuit = true;// 放弃当前编辑
								$scope.newEditCount = 0;// 确认放弃，次数归0
							}
						}
						// 离开编辑要么确认
						if (!$scope.oldEditQuit) {
							var con = confirm("您当前正处于编辑状态，请确认是否放弃");
							if (!con) {
								return false;
							} else {
								$scope.oldEditQuit = true;// 放弃当前编辑
							}
						}
						$scope.nowRow = index;
						if(undefined != edited && null != edited){
							var rawMaterials = edited;
						}else{
							var rawMaterials = $scope.RawMaterialses[index];// 选中产品
						}
						// 商品编辑页面状态
						$scope.editRawMaterials = false;
						// 清空图片信息
						$rootScope.formHeader = [];
						$rootScope.thumbHeader = [];
						$rootScope.thumbDetail = [];
						$rootScope.formDetail = [];
						// 图片变量
						var rawMaterialsPictures;
						// 包装单位
						var rawMaterialsPackUnit
						// 获取包装单位
						$http(
								{
									method : "GET",
									url : "getRawMaterialsPackUnit?RawMaterialsCode="
											+ rawMaterials.rawMaterialsCode
								}).success(function(data) {
							rawMaterialsPackUnit = data;
							$rootScope.rawMaterialsPackUnit = data;
						});
						// 获取图片列表
						$http(
								{
									method : "GET",
									url : "getRawMaterialsPicture?rawMaterialsCode="
											+ rawMaterials.rawMaterialsCode
								})
								.success(
										function(data) {
											rawMaterialsPictures = data;
											// 头部信息查询
											$http.post(
															"getRawMaterialsProperties",
															{
																categoryCode : rawMaterials.categoryCode
															})
													.success(
															function(data) {
																$scope.productCategoryProperties = data.productCategoryPropertys;
															});
											// 组合产品和标准产品的信息显示不同。
											// 标准产品的显示
											if (rawMaterials.rawMaterialsType == 0 ) {
												
											} else if (rawMaterials.rawMaterialsType == 1) {
												
											}else if(rawMaterials.rawMaterialsType == 2){
												$rootScope.showPage = 9;
												if($rootScope.SourcePage != 1){
													$rootScope.SourcePage = 3;//跳转前页面
												}
												rawMaterials.rawMaterialsPictures = rawMaterialsPictures;
												rawMaterials.rawMaterialsPackUnit = rawMaterialsPackUnit;
												$scope.rawMaterials = {
														id : rawMaterials.id,
														rawMaterialsName : rawMaterials.rawMaterialsName,
														rawMaterialsCode : rawMaterials.rawMaterialsCode,
														categoryCode : rawMaterials.categoryCode,
														rawMaterialsBuyUnitName : rawMaterials.rawMaterialsBuyUnitName,
														rawMaterialsBuyUnitCode : rawMaterials.rawMaterialsBuyUnitCode,
														rawMaterialsType : rawMaterials.rawMaterialsType,
														rawMaterialsTemperatureZoneName : rawMaterials.rawMaterialsTemperatureZoneName,
														rawMaterialsTemperatureZoneCode : rawMaterials.rawMaterialsTemperatureZoneCode,
														rawMaterialsSpecification : rawMaterials.rawMaterialsSpecification,
														rawMaterialsPictures : rawMaterials.rawMaterialsPictures,
														rawMaterialsPackUnit : rawMaterials.rawMaterialsPackUnit,
														rawMaterialsProperties : rawMaterials.rawMaterialsProperties
													};

											
											}
											// 更新图片信息,用于显示
											for (var i = 0; i < rawMaterialsPictures.length; i++) {
												// 头部图片
												if (rawMaterialsPictures[i].pictureShowPosition == 0) {
													$rootScope.formHeader
															.push({
																pictureAddress : rawMaterialsPictures[i].pictureAddress,
																changeFlag : false
															});
													$rootScope.thumbHeader
															.push({
																pictureAddress : rawMaterialsPictures[i].pictureAddress,
																changeFlag : false
															});
												} else {
													// 详情页图片
													$rootScope.formDetail
															.push({
																pictureAddress : rawMaterialsPictures[i].pictureAddress,
																changeFlag : false
															});
													$rootScope.thumbDetail
															.push({
																pictureAddress : rawMaterialsPictures[i].pictureAddress,
																changeFlag : false
															});
												}
											}

											// 离最大图片size还差addSize个
											var addSize = $rootScope.headerPictureSize
													- $rootScope.thumbHeader.length;
											// 补足图片数量
											for (var i = 0; i < addSize; i++) {
												$rootScope.formHeader.push({
													pictureAddress : "",
													changeFlag : false
												});
												$rootScope.thumbHeader.push({
													pictureAddress : "",
													changeFlag : false
												});
											}

										});
					};

					// 去除头部图片的空位置
					$rootScope.deletBlank = function(data) {
						if (null == data || data.length == 0) {
							return data;
						}
						for (var i = data.length - 1; i >= 0; i--) {
							if (data[i].pictureShowPosition == 0) {
								if (undefined == data[i].pictureAddress
										|| null == data[i].pictureAddress
										|| "" == data[i].pictureAddress) {
									data.splice(i, 1);// 删除指定index
								}
							}
						}

						for (var i = 0; i < data.length; i++) {
							if (data[i].pictureShowPosition == 0) {
								data[i].pictureShowOrder = i;
							}
						}
						return data;
					}

					$scope.clearDetail = function() {
						$scope.rawMaterialsCodes = null;
						$scope.rawMaterialsGs1Codes = null;
						$scope.rawMaterialsNames = null;
						$scope.rawMaterialsTemperatureZoneCodes = null;
					}
				})
		.controller(
				'rawMaterialsDetailAddController',// 普通产品新增Controller
				function($scope, $rootScope, $http) {

					$rootScope.resutAllRawMaterials = function(){
						$scope.addedRawMaterials = {};
						$scope.rawMaterialsDetailAddForm.$setPristine();
						$scope.rawMaterialsDetailAddForm.$setUntouched();
					    $scope.rawMaterialsDetailAddForm.$setValidity();
						document.rawMaterialsDetailAddForm.oneinput.value = '';// 详细图片name初始化
						document.getElementById("rawMaterialsDetailAddForm").reset();//清空辅材from
					}
					$scope.addedRawMaterials = {};
//					// 监听
//					$scope.$watch('addedRawMaterials', function(newValue, oldValue) {
//						if (newValue === oldValue) {
//							return;
//						} // AKA first run
//						$scope.addedRawMaterials = {};
//					});

					// 判断产品的属性是否都填写
					$scope.isProductPropertyMissed = true;
					$scope.onPropertyValueChange = function(addedRawMaterials) {
						var propertyLength = 0;
						// 判断图片是否上传完成
						for ( var p in addedRawMaterials) {
							propertyLength++;
						}
						// 大于1件时
						if (propertyLength < $scope.productCategoryProperties.length) {
							$scope.isProductPropertyMissed = true;
						} else {
							$scope.isProductPropertyMissed = false;
						}
					};
					// 图片上传
					$scope.rawMaterialsImageDetail = function(data) {
						var rawMaterials = {};
						// 返回数据的处理---基础数据
						rawMaterials.rawMaterialsName = $scope.addedRawMaterials.rawMaterialsName;
						rawMaterials.rawMaterialsCode = $scope.addedRawMaterials.rawMaterialsCode;
						// 标准产品
						rawMaterials.rawMaterialsType = 2;
						rawMaterials.categoryCode = $scope.categoryCode;
						rawMaterials.rawMaterialsSpecification = $scope.addedRawMaterials.rawMaterialsSpecification;
						// 产品售卖单位
						rawMaterials.rawMaterialsBuyUnitCode = $scope.addedRawMaterials.rawMaterialsBuyUnit.optionValue;
						rawMaterials.rawMaterialsBuyUnitName = $scope.addedRawMaterials.rawMaterialsBuyUnit.optionName;
						// 温区
						rawMaterials.rawMaterialsTemperatureZoneCode = $scope.addedRawMaterials.rawMaterialsTemperatureZone.optionValue;
						rawMaterials.rawMaterialsTemperatureZoneName = $scope.addedRawMaterials.rawMaterialsTemperatureZone.optionName;

						// 返回数据的处理---产品的属性
						var rawMaterialsProperties = [];
						for (var i = 0; i < $scope.productCategoryProperties.length; i++) {
							rawMaterialsProperty = {};
							rawMaterialsPropertyName = $scope.productCategoryProperties[i].categoryPropertyName;
							rawMaterialsPropertyValue = $scope.addedRawMaterials.rawMaterialsProperty[i].categoryPropertyValue;
							rawMaterialsProperty.rawMaterialsPropertyName = rawMaterialsPropertyName;
							rawMaterialsProperty.rawMaterialsCode = $scope.addedRawMaterials.rawMaterialsCode;
							rawMaterialsProperty.rawMaterialsPropertyValue = rawMaterialsPropertyValue;
							rawMaterialsProperties.push(rawMaterialsProperty);
						}
						// 返回数据的处理---产品的自定义属性
						Array.prototype.push.apply(rawMaterialsProperties,
								$scope.customerRawMaterialsProperties);

						rawMaterials.rawMaterialsProperties = rawMaterialsProperties;
						// 返回数据的处理---产品的图片
						// 头部图片信息
						var index = $rootScope.thumbHeader.length;
						rawMaterials.rawMaterialsPictures = [];
						for ( var image in $rootScope.thumbHeader) {
							if (rawMaterials.rawMaterialsPictures[image] == undefined
									|| rawMaterials.rawMaterialsPictures[image] == null)
								rawMaterials.rawMaterialsPictures
										.push({
											pictureAddress : $rootScope.thumbHeader[image].pictureAddress,
											pictureShowPosition : 0,
											pictureShowOrder : image
										});
							else {
								rawMaterials.rawMaterialsPictures[image].pictureAddress = $rootScope.thumbHeader[image].pictureAddress;
								rawMaterials.rawMaterialsPictures[image].pictureShowPosition = 0;// 头部图片，0固定
								rawMaterials.rawMaterialsPictures[image].pictureShowOrder = image;
							}
							if (data != null) {
								for (var i = 0; i < data.length; i++) {
									// 若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
									if (data[i].pictureShowPosition == 0
											&& image == data[i].pictureShowOrder) {
										rawMaterials.rawMaterialsPictures[image].pictureAddress = data[i].pictureAddress;
										break;
									}
								}
							}
						}
						// 详情页图片信息
						for ( var image in $rootScope.thumbDetail) {
							if (rawMaterials.rawMaterialsPictures[index] == undefined
									|| rawMaterials.rawMaterialsPictures[index] == null)
								rawMaterials.rawMaterialsPictures
										.push({
											pictureAddress : $rootScope.thumbDetail[image].pictureAddress,
											pictureShowPosition : 1,
											pictureShowOrder : image
										});
							else {
								rawMaterials.rawMaterialsPictures[index].pictureAddress = $rootScope.thumbDetail[image].pictureAddress;
								rawMaterials.rawMaterialsPictures[index].pictureShowPosition = 1;// 详情图片，1固定
								rawMaterials.rawMaterialsPictures[index].pictureShowOrder = image;
							}

							if (data != null) {
								for (var i = 0; i < data.length; i++) {
									// 若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
									if (data[i].pictureShowPosition == 1
											&& image == data[i].pictureShowOrder) {
										rawMaterials.rawMaterialsPictures[index].pictureAddress = data[i].pictureAddress;
										break;
									}
								}
							}
							index++;
						}

						// 更新图片的编码
						for (var i = 0; i < rawMaterials.rawMaterialsPictures.length; i++) {
							rawMaterials.rawMaterialsPictures[i].rawMaterialsCode = $scope.addedRawMaterials.rawMaterialsCode;
						}

						// 去除头部图片的空位置
						rawMaterials.rawMaterialsPictures = $rootScope
								.deletBlank(rawMaterials.rawMaterialsPictures);
						// 包装单位
						rawMaterials.rawMaterialsPackUnit = $rootScope.rawMaterialsPackUnit;
						$scope.addedRawMaterials = rawMaterials;
						// 页面新增数据上传
						$http.post("addRawMaterials", $scope.addedRawMaterials)
								.success(
										function(data) {
											// 新增完成。FLG重置
											if (!$scope.$parent.newEditQuit) {
												$scope.$parent.newEditQuit = true;// 放弃当前新增
												$scope.$parent.newEditCount = 0;// 确认放弃，次数归0
											}
											$scope.$parent.doubleClick = false;// 恢复完成按钮disable属性
											exitPop('submitInfo');
											$scope.$parent.doubleC = 0;// 完成提交初始化二重check
											$scope.$parent.rawMaterials = data;// 返回数据
											// 处理新增之后直接编辑的情况
											$rootScope.formHeader = [];
											$rootScope.thumbHeader = [];
											$rootScope.thumbDetail = [];
											$rootScope.formDetail = [];
											rawMaterialsPictures = $scope.rawMaterials.rawMaterialsPictures;
											if (undefined != rawMaterialsPictures) {
												for (var i = 0; i < rawMaterialsPictures.length; i++) {
													// 头部图片
													if (rawMaterialsPictures[i].pictureShowPosition === 0) {
														$rootScope.formHeader
																.push({
																	pictureAddress : rawMaterialsPictures[i].pictureAddress,
																	changeFlag : false
																});
														$rootScope.thumbHeader
																.push({
																	pictureAddress : rawMaterialsPictures[i].pictureAddress,
																	changeFlag : false
																});
													} else {
														// 详情页图片
														$rootScope.formHeader
																.push({
																	pictureAddress : rawMaterialsPictures[i].pictureAddress,
																	changeFlag : false
																});
														$rootScope.thumbDetail
																.push({
																	pictureAddress : rawMaterialsPictures[i].pictureAddress,
																	changeFlag : false
																});
													}
												}
											}
											$scope.selectByPage(
													$scope.pagination.pageNum,
													$scope.pagination.pageSize,
													$scope.categoryCode, null,
													$scope.rawMaterialsProperty,
													$scope.rawMaterialsType,
													null,
													null,
													null);
										});
						// 新增完成后进入查看界面显示该产品
						$rootScope.showPage = 9;// 普通产品编辑页面总体不显示
					}

					// 标准产品
					$scope.onAddSaveClick = function() {

						// 防止表单重复提交
						$scope.$parent.doubleC = $scope.$parent.doubleC + 1;
						if ($scope.$parent.doubleC > 0) {
							$scope.$parent.doubleClick = true;
							$rootScope.message = "正在提交数据,请稍后,成功后自动关闭！";
							showPopup('submitInfo', true);
						}

						// 图片数据的处理
						var data = new FormData();
						var ic = 0;
						h1 = 0;
						h2 = 0;

						// 头部图片信息
						for ( var image in $rootScope.thumbHeader) {
							// 当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
							if ($rootScope.thumbHeader[image].pictureAddress != undefined
									&& $rootScope.thumbHeader[image].pictureAddress.length > 100) {
								ic++;
								h1++;
							}
						}
						// 详情页图片信息
						for ( var image in $rootScope.thumbDetail) {
							// 当详情页图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
							if ($rootScope.thumbDetail[image].pictureAddress != undefined
									&& $rootScope.thumbDetail[image].pictureAddress.length > 100) {
								ic++;
								h2++;
							}
						}
						// 图片没有变更时，提交页面数据
						var ib = 0;
						$scope.pic = [];
						if (ic == 0) {
							$scope.rawMaterialsImageDetail($scope.pic);
							return;
						}
						// 处理图片位置信息
						var imageSize = 0;
						for (var i = 0; i < ic; i++) {
							var data = new FormData();

							if (i >= h1) {
								// 详情页图片
								for ( var image in $rootScope.thumbDetail) {
									// 当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
									if ($rootScope.thumbDetail[image].pictureAddress != undefined
											&& $rootScope.thumbDetail[image].pictureAddress.length > 100) {
										data
												.append(
														"1," + image,
														$rootScope.thumbDetail[image].file);
										$rootScope.thumbDetail[image].pictureAddress = undefined;
										break;
									}
								}
							} else {
								for ( var image in $rootScope.thumbHeader) {
									// 当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
									if ($rootScope.thumbHeader[image].pictureAddress != undefined
											&& $rootScope.thumbHeader[image].pictureAddress.length > 100) {
										data
												.append(
														"0," + image,
														$rootScope.thumbHeader[image].file);
										$rootScope.thumbHeader[image].pictureAddress = undefined;
										break;
									}
								}
							}
							// 图片上传
							$http({
								method : 'post',
								url : '/pms-web/pms/product/imageUpload',
								data : data,
								headers : {
									'Content-Type' : undefined
								},
								transformRequest : angular.identity
							}).success(function(newdata) {
								$scope.pic[ib] = newdata[0];
								ib++;
								if (ib == ic)
									$scope.rawMaterialsImageDetail($scope.pic);// 图片有变更时，提交页面数据
							});
						}
					};
				})
		.controller(
				'rawMaterialsDetailController',// 普通产品编辑controller
				function($scope, $rootScope, $http) {

					// 点击编辑按钮触发的事件
					$scope.onEditClick = function() {

						if ($scope.$parent.oldEditQuit) {
							$scope.$parent.oldEditQuit = false;// 是否放弃当前编辑FLG
						}

						$scope.otherProperty = [];
						if ($scope.productCategoryProperties != undefined)
							for (var i = 0; i < $scope.productCategoryProperties.length; i++) {
								var count = 0;
								if ($scope.rawMaterials != undefined
										&& $scope.rawMaterials.rawMaterialsProperties != undefined)
									for (var j = 0; j < $scope.rawMaterials.rawMaterialsProperties.length; j++) {
										if ($scope.rawMaterials.rawMaterialsProperties[j].rawMaterialsPropertyName == $scope.productCategoryProperties[i].categoryPropertyName) {
											count++;
											break;
										}
									}
								if (count == 0) {
									$scope.otherProperty
											.push($scope.productCategoryProperties[i]);
								}
							}
						// 离最大图片size还差addSize个
						var addSize = $rootScope.headerPictureSize
								- $rootScope.thumbHeader.length;
						// 补足图片数量
						for (var i = 0; i < addSize; i++) {
							$rootScope.formHeader.push({
								pictureAddress : "",
								changeFlag : false
							});
							$rootScope.thumbHeader.push({
								pictureAddress : "",
								changeFlag : false
							});
						}
						document.rawMaterialsEditForm.oneinput.value = '';// 详细图片name初始化
						$rootScope.SourcePageEdit = 9;//跳转前页面
						$scope.$parent.editRawMaterials = true;
						// 图片上传限制
						$rootScope.isHeaderPictureSatified = true;
						$rootScope.isDetailPictureSatified = true;
						$rootScope.showRawMaterialsBuyUnitEmpty = false;// 包装单位错误表示

					};

					// 标准产品图片编辑
					$scope.bzcpImage = function(data) {
						// 产品属性处理
						$scope.rawMaterials.rawMaterialsProperties = [];
						$(".propertyV2").each(function(i) {
							$scope.rawMaterials.rawMaterialsProperties.push({
								rawMaterialsPropertyName : this.id,
								rawMaterialsPropertyValue : this.value
							});
						});
						$(".propertyV3").each(function(i) {
							$scope.rawMaterials.rawMaterialsProperties.push({
								rawMaterialsPropertyName : this.name,
								rawMaterialsPropertyValue : this.value,
								customerDefineFlag : 1
							});
						});

						// 返回数据的处理---产品的图片
						// 头部图片信息
						var index = $rootScope.thumbHeader.length;
						$scope.rawMaterials.rawMaterialsPictures = [];
						for ( var image in $rootScope.thumbHeader) {
							if ($scope.rawMaterials.rawMaterialsPictures[image] == undefined
									|| $scope.rawMaterials.rawMaterialsPictures[image] == null)
								$scope.rawMaterials.rawMaterialsPictures
										.push({
											pictureAddress : $rootScope.thumbHeader[image].pictureAddress,
											pictureShowPosition : 0,
											pictureShowOrder : image
										});
							else {
								$scope.rawMaterials.rawMaterialsPictures[image].pictureAddress = $rootScope.thumbHeader[image].pictureAddress;
								$scope.rawMaterials.rawMaterialsPictures[image].pictureShowPosition = 0;// 头部图片，0固定
								$scope.rawMaterials.rawMaterialsPictures[image].pictureShowOrder = image;
							}
							if (data != null) {
								for (var i = 0; i < data.length; i++) {
									// 若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
									if (data[i].pictureShowPosition == 0
											&& image == data[i].pictureShowOrder) {
										$scope.rawMaterials.rawMaterialsPictures[image].pictureAddress = data[i].pictureAddress;
										break;
									}
								}
							}
						}
						// 详情页图片信息
						for ( var image in $rootScope.thumbDetail) {
							if ($scope.rawMaterials.rawMaterialsPictures[index] == undefined
									|| $scope.rawMaterials.rawMaterialsPictures[index] == null)
								$scope.rawMaterials.rawMaterialsPictures
										.push({
											pictureAddress : $rootScope.thumbDetail[image].pictureAddress,
											pictureShowPosition : 1,
											pictureShowOrder : image
										});
							else {
								$scope.rawMaterials.rawMaterialsPictures[index].pictureAddress = $rootScope.thumbDetail[image].pictureAddress;
								$scope.rawMaterials.rawMaterialsPictures[index].pictureShowPosition = 1;// 详情图片，1固定
								$scope.rawMaterials.rawMaterialsPictures[index].pictureShowOrder = image;
							}

							if (data != null) {
								for (var i = 0; i < data.length; i++) {
									// 若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
									if (data[i].pictureShowPosition == 1
											&& image == data[i].pictureShowOrder) {
										$scope.rawMaterials.rawMaterialsPictures[index].pictureAddress = data[i].pictureAddress;
										break;
									}
								}
							}
							index++;
						}
						// 去除头部图片的空位置
						$scope.rawMaterials.rawMaterialsPictures = $rootScope
								.deletBlank($scope.rawMaterials.rawMaterialsPictures);
						// 包装单位
						$scope.rawMaterials.rawMaterialsPackUnit = $rootScope.rawMaterialsPackUnit;
						// 更新页面数据
						$http.post("updateRawMaterials", $scope.rawMaterials)
								.success(
										function(data) {
											$scope.$parent.doubleClick = false;
											exitPop('submitInfo');
											$scope.$parent.doubleC = 0;
											$scope.$parent.oldEditQuit = true;// 放弃当前编辑
											//跳转前页面
											if($rootScope.SourcePage == 1){
												$scope.searchByRawMaterialsCode('detail');
											}else if($rootScope.SourcePage == 3){
												$scope.selectByPage(
														$scope.$parent.pagination.pageNum,
														$scope.$parent.pagination.pageSize,
														$scope.$parent.categoryCode,
														null,
														$scope.$parent.rawMaterialsProperty,
														null,
														null,
														null,
														null);
											}
											$scope.rowClick(
													$scope.$parent.nowRow,$scope.rawMaterials);
										});
					}

					// 标准产品编辑保存
					$scope.onSaveClick = function() {
						$scope.$parent.doubleC = $scope.$parent.doubleC + 1;// 点击过保存按钮disable
						if ($scope.$parent.doubleC > 0) {
							$scope.$parent.doubleClick = true;
							$rootScope.message = "正在提交数据,请稍后,成功后自动关闭！";
							showPopup('submitInfo', true);
						}

						// 图片数据的处理
						var data = new FormData();
						var ic = 0;
						h1 = 0;
						h2 = 0;

						// 头部图片信息
						for ( var image in $rootScope.thumbHeader) {
							// 当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
							if ($rootScope.thumbHeader[image].pictureAddress != undefined
									&& $rootScope.thumbHeader[image].pictureAddress.length > 100) {
								ic++;
								h1++;
							}
						}
						// 详情页图片信息
						for ( var image in $rootScope.thumbDetail) {
							// 当详情页图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
							if ($rootScope.thumbDetail[image].pictureAddress != undefined
									&& $rootScope.thumbDetail[image].pictureAddress.length > 100) {
								ic++;
								h2++;
							}
						}
						// 图片未修改，提交页面数据
						var ib = 0;
						$scope.pic = [];
						if (ic == 0) {
							$scope.bzcpImage($scope.pic);
							return;
						}
						var imageSize = 0;
						for (var i = 0; i < ic; i++) {
							var data = new FormData();
							if (i >= h1) {
								// 详情页图片
								for ( var image in $rootScope.thumbDetail) {
									// 当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
									if ($rootScope.thumbDetail[image].pictureAddress != undefined
											&& $rootScope.thumbDetail[image].pictureAddress.length > 100) {
										data
												.append(
														"1," + image,
														$rootScope.thumbDetail[image].file);
										$rootScope.thumbDetail[image].pictureAddress = undefined;
										break;
									}
								}
							} else {
								for ( var image in $rootScope.thumbHeader) {
									// 当头部图片集合中的图片地址长度大于100时，视为该图片为本地预览图片，未上传至服务器，故需上传至服务器
									if ($rootScope.thumbHeader[image].pictureAddress != undefined
											&& $rootScope.thumbHeader[image].pictureAddress.length > 100) {
										data
												.append(
														"0," + image,
														$rootScope.thumbHeader[image].file);
										$rootScope.thumbHeader[image].pictureAddress = undefined;
										break;
									}
								}
							}

							// 图片上传
							$http({
								method : 'post',
								url : '/pms-web/pms/product/imageUpload',
								data : data,
								headers : {
									'Content-Type' : undefined
								},
								transformRequest : angular.identity
							}).success(function(newdata) {
								$scope.pic[ib] = newdata[0];
								ib++;
								if (ib == ic)
									$scope.bzcpImage($scope.pic);// 完成图片上传，提交页面数据
							});
						}
					};
				});
function loadSecMenu(url) {
	window.location.href = url;
}