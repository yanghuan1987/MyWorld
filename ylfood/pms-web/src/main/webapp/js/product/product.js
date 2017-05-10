angular
		.module('productApp', [ 'dPagination','pictureApp'])
		.controller(
				'productController',
				function($scope,$rootScope,$http) {
					$scope.editProduct = false;//普通产品编辑页面不显示
					$scope.addProduct = false;//普通产品新增页面不显示
					$scope.showP = false;//普通产品编辑页面总体不显示
					$scope.showCproduct = false;//组合产品新增页面不显示
					$scope.doubleC = 0;//防止表单重复提交
					$scope.doubleClick = false;//防止表单重复提交
					$scope.newEditQuit = true;//新增页面是否放弃当前编辑FLG
					$scope.newEditCount = 0;//新增页面是否放弃当前编辑FLG
					$scope.oldEditQuit = true;//编辑页面是否放弃当前编辑FLG
					$scope.haveprodect = false;//拼装没有选择产品时FALSE
				 	$scope.colorone = "";//菜单背景颜色
				 	$scope.showTable = false;//产品table不显示
					$scope.pageNum = 1;//翻页页数
					$scope.total = 1;//总共条数
					$scope.pageSize = 15;//每页件数
					$rootScope.isProduct = true;//用于详细图片BUTTON显示，表示此为产品业务
					$rootScope.pictureCheck = true;//是否check图片
					$rootScope.UnitDisable = true;//采购基本单位disable
					$rootScope.weightU = [];//重量单位
					$rootScope.weightN = [];//重量单位
				 	//获取品类菜单
					$http.get("GetCatagoryList")
					     .success(function(data) {
						     $scope.categoryList = data;
						 });
					
					//产品的规格基础数据
					$http.get("getAllBasicMeasureUnit")
					     .success(function(data) {
				    	      $scope.specificationFirstValues = data;
				         });
					//获取所有的包装单位列表，例如瓶、袋、箱、框等等。
					$http.get("getBasicPackageUnit")
					     .success(function(data) {
					        $rootScope.specificationSecondValues = data;
					     });
					//获取所有的基本单位。
					$http.get("getBasicPurchaseUnit")
					     .success(function(data) {
					    	var BasicPurchaseUnit = data;
					        $rootScope.BasicPurchaseUnit = [];
							//重量单位
							$http.get("getBasicWeightUnit")
							     .success(function(data) {
							    	var BasicWeightUnit = data
							        $scope.BasicWeightUnit = data;
							        for(x in $scope.BasicWeightUnit){
							        	$rootScope.weightU.push($scope.BasicWeightUnit[x].optionValue);
							        	$rootScope.weightN.push($scope.BasicWeightUnit[x].optionName);
							        }
							        //过滤重量单位意外的单位
							        for(i in BasicWeightUnit){
										var match = BasicPurchaseUnit.filter(function(e) {
									        return e.optionName == BasicWeightUnit[i].optionName;
										});
										if(match.length != 0){
											if(match[0].optionValue != 'g'){
												$rootScope.BasicPurchaseUnit.push(match[0]);
											}
										}
							        }
							        $rootScope.BasicPurchaseUnitCopy = angular.copy($rootScope.BasicPurchaseUnit);
							     });
					     });
					//获取所有的包装单位列表，例如瓶、袋、箱、框等等。
					$http.get("getProductStatus")
					     .success(function(data) {
					        $scope.productStatus = data;
					     });
					//获取温区
					$http.get("getProductTemperatureZone")
					     .success(function(data) {
					        $scope.severProductTemperatureZone = data;
					     });
					
					// 定义是否可售的下拉列表值
					$scope.isSaleableValues = [ {
						id : 1,
						value : '是'
					}, {
						id : 0,
						value : '否'
					} ];

					$scope.compositeProduct = {};//清空组合产品
					$scope.satisfiedProducts = [];//清空产品属性
					$scope.selectedProductItems = [];//清空以选择产品
					//注销
					$scope.loginOut = function() {
						window.location.href = serverPath;
				    };
					
					//左侧二菜单显示
					$scope.updateOne = function(updatecatone) {
				   	 	$scope.colorone = "#99C731";//菜单背景颜色
				   	 	//获取选择品类子品类信息
				   	 $scope.category = {categoryName:updatecatone.categoryName,categoryCode:updatecatone.categoryCode,id:updatecatone.id};
						$http.post("/pms-web/pms/category/selectCategoryChildren?categoryCode="+updatecatone.categoryCode)
							.success(function(data) {
								updatecatone.productCategorys = data;
							});
						//显示和收起菜单
						if($scope.spMenuShow != updatecatone.id) {
							$scope.spMenuShow = updatecatone.id;
						} else {
							$scope.spMenuShow = undefined;
						}
				 			
					};
					//三级菜单表示
					$scope.updateTwo = function(cat1name, updatecattwo) {
						//获取选择品类子品类信息
				    	$scope.category = {categoryName:updatecattwo.categoryName,categoryCode:updatecattwo.categoryCode,id:updatecattwo.id};
						$http.post("/pms-web/pms/category/selectCategoryChildren?categoryCode="+updatecattwo.categoryCode)
							 .success(function(data) {
								updatecattwo.productCategorys = data;
							});
						//显示和收起菜单
						if ($scope.spMenuShowT != updatecattwo.id) {
							$scope.spMenuShowT = updatecattwo.id;
						} else {
							$scope.spMenuShowT = undefined;
						}
					};

					//依据条件查询产品列表信息
					$scope.selectByPage = function(pageNum, pageSize, categoryCode, productCode, productProperty, saleFlag, compositeFlag) {
						$http.post("selectByPage", {
							pageNum : pageNum,//翻页页数
							pageSize : pageSize,//每页件数
							categoryCode : categoryCode,//品类code
							productCode : productCode,//产品code
							productProperty : productProperty,//产品属性
							saleFlag: saleFlag,//是否可售
							compositeFlag : compositeFlag//是否组合
						}).success(function(data) {
							$scope.pagination.total = data.total;//返回结果总共条数
							$scope.pagination.pageNum = data.pageNum;//返回结果页数
							$scope.pagination.pageSize = data.pageSize;//返回结果每页件数
							$scope.products = data.result;//返回结果
							var cb = $scope.pageSize ;//为满足条数时填补空行
							if($scope.products != undefined && $scope.products != {})
								cb = cb - $scope.products.length;//计算需要填补几行
							$scope.tempList = [cb];//占位
							while(cb > 0){
								$scope.tempList.push([cb]);//占位添加
								cb--;
							}
							$scope.tempList.pop();//去除最后一行
						});
					};
					
					$scope.compositeFlag = 0;//组合产品
					// 点击左边三级品类时处理的事件：
					$scope.showThree = function(id, name, ccode, showlevel,
							corder, categoryTypeFlag) {
						//离开新增确认框
				    	if(!$scope.newEditQuit){
				    		var con = confirm("您当前正处于新增状态，请确认是否放弃");
					    	if(!con){
					    		return false;
					    	}else{
					    		$scope.newEditQuit = true;//放弃当前编辑	
					    		$scope.newEditCount = 0;//确认放弃，次数归0
					    	}
				    	}
				    	//离开编辑确认框
				    	if(!$scope.oldEditQuit){
				    		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
					    	if(!con){
					    		return false;
					    	}else{
					    		$scope.oldEditQuit = true;//放弃当前编辑	
					    	}
				    	}						
				    	$rootScope.showProductBuyUnitEmpty = false;// 包装单位错误表示
						$scope.doubleClick = false;//二重点击还原
						exitPop('submitInfo');
					 	$scope.showTable = true;//产品table显示
					 	//检索参数
				    	$scope.category = {id:id, 
	    						categoryName:name,
	    						categoryCode:ccode};
						$scope.categoryName = name;//品类名字
						$scope.categoryCode = ccode;//品类code
						$scope.compositeFlag = categoryTypeFlag;//组合flg
						//商品编辑页面状态
						$scope.editProduct = false;
						//商品新增页面状态
						$scope.addProduct = false;//普通产品新增页面不显示
						$scope.showP = false;//普通产品编辑页面总体不显示
						$scope.showCproduct = false;//组合产品新增页面不显示
						$scope.addCProduct = false;//组合新增不显示
						$scope.selected = "default";//可售默认
						$scope.productProperty = null;//产品属性空

						// 头部信息查询
						$http.post("getProductProperties", {
							      categoryCode : $scope.categoryCode
							}).success(function(data) {
								$scope.productCategoryProperties = data.productCategoryPropertys;
							});

						// 执行查询部分逻辑, 获取产品以及产品属性的信息的 初始查询
						$scope.selectByPage($scope.pageNum,$scope.pageSize,$scope.categoryCode,null,$scope.productProperty,null,$scope.compositeFlag);
						$scope.initd = 0;
					};

					// 前台分页的数据结构
					$scope.pagination = {
						pageNum : $scope.pageNum,//翻页页数
						total : $scope.total,//总共条数
						pageSize : $scope.pageSize,//每页件数
						onChange : function() {
							if($scope.initd == undefined)
								return;
							$scope.selectByPage($scope.pagination.pageNum,$scope.pagination.pageSize,$scope.categoryCode,$scope.productCode,$scope.productProperty,$scope.saleFlag,$scope.compositeFlag);
						}
					};

					var products = $scope.products;//选择产品
					var productCode = $scope.productCode;//选择code

					// 点击产品编码时触发的事件
					$scope.searchByProductCode = function(productCode) {
						$scope.productCode = productCode;
						$scope.selectByPage($scope.pagination.pageNum,$scope.pagination.pageSize,$scope.categoryCode,$scope.productCode,$scope.productProperty,null,$scope.compositeFlag);
					};
					// 选择产品属性时触发的事件
					$scope.searchByProperty = function(index,propertyName, propertyValue) {
						$scope.selected = "";
						for(indexTemp in $scope.productCategoryProperties){
							if(index != indexTemp){
								$scope.productCategoryProperties[indexTemp].selectedValue ="";
							}
						}

						//如果点击的是表头，则清空该搜索的条件
						if(propertyValue == undefined){
							$scope.productProperty = null;
						}else
							$scope.productProperty = {productPropertyName : propertyName,productPropertyValue : propertyValue};
						//检索翻页
						$scope.selectByPage($scope.pagination.pageNum,$scope.pagination.pageSize,$scope.categoryCode,null,$scope.productProperty,null,$scope.compositeFlag);
					};

					// 是否可售选择条件
					$scope.searchByIsSalingFlag = function(saleFlag) {
						for(index in $scope.productCategoryProperties){
							$scope.productCategoryProperties[index].selectedValue ="";
						}
						$scope.saleFlag = saleFlag;
						$scope.selectByPage($scope.pagination.pageNum,$scope.pagination.pageSize,$scope.categoryCode,null,null,saleFlag,$scope.compositeFlag);
					}
				
					// 验证产品编码是否存在
					$scope.isProductCodeDuplicated = false;
					$scope.validateProductCodeExist = function(productCode) {
						if(productCode != undefined){
							$http.post("isProductCodeDuplicated", {
								productCode : productCode
							}).success(function(data, status, headers, config) {
								$scope.isProductCodeDuplicated = data;
							});	
						}
					};
					$scope.customerProductProperties = [];
					$scope.customerProductProperty = {};

					// 删除一个自定义属性及属性值
					$scope.onRemoveCustomerPropertyItemClick = function(index) {
						//新增产品时，删除自定义属性
						if($scope.customerProductProperties != undefined)
							$scope.customerProductProperties.splice(index, 1);
						//编辑产品时，删除自定义属性
						if($scope.product != undefined && $scope.product.productProperties != undefined){
							$scope.product.productProperties.splice(index, 1);
						}
					};
					// 新增自定义属性触发的事件
					$scope.onAddCustomerPropertyClick = function(property,index) {
						$scope.showcustomerProductProperty=false;
						var pop = showPopup('addNewPropertypopUp',true);
						$scope.propertyEdit = false;
						if(property != undefined){
							$scope.customerProductProperty = {productPropertyName:property.productPropertyName,productPropertyValue:property.productPropertyValue};
							$scope.propertyEdit = true;
							$scope.eindex = index;
						}
					};
					
					// 新添加一个自定义属性及属性值
					$scope.onAddCustomerPropertyItemClick = function(property) {
						if($scope.customerProductProperty.productPropertyName == undefined ||
								$scope.customerProductProperty.productPropertyValue == undefined ||
								$scope.customerProductProperty.productPropertyValue == '' ||
								$scope.customerProductProperty.productPropertyName == ''){
							$scope.showcustomerProductProperty=true;
							return;
						}
						$scope.showcustomerProductProperty=false;
						property.customerDefineFlag = 1;

						//编辑状态
						if($scope.propertyEdit){
							//新增编辑
							for(var i=0;i<$scope.customerProductProperties.length;i++){
								//修改完成编辑的自定义属性
								if(i == $scope.eindex){
									$scope.customerProductProperties[i].productPropertyName = property.productPropertyName;//属性名称
									$scope.customerProductProperties[i].productPropertyValue = property.productPropertyValue;//属性值
									$scope.customerProductProperties[i].customerDefineFlag = 1;
									break;
								}
							}
							//修改编辑
							if($scope.product != undefined && $scope.product.productProperties != undefined){
								for(var i=0;i<$scope.product.productProperties.length;i++){
									if(i == $scope.eindex){
										$scope.product.productProperties[i].productPropertyName = property.productPropertyName;//属性名称
										$scope.product.productProperties[i].productPropertyValue = property.productPropertyValue;//属性值
										break;
									}
								}
							}
						}else{
							var p = {productPropertyName:property.productPropertyName,productPropertyValue:property.productPropertyValue,customerDefineFlag:property.customerDefineFlag};
							if($scope.product != undefined && $scope.product.productProperties != undefined){
								
								$scope.product.productProperties.push(p);
							}
							$scope.customerProductProperties.push(property);//新增属性
						}
						$scope.customerProductProperty = {};
						exitPop('addNewPropertypopUp');
					};
					
					// 删除一个自定义属性及属性值
					$scope.onRemoveProductPackUnitClick = function(index) {
						//新增产品时，删除自定义属性
						if($rootScope.productPackUnit != undefined)
							$rootScope.productPackUnit.splice(index, 1);
					};
					// 新增自定义属性触发的事件
					$scope.onAddProductPackUnitClick = function(productBuyUnit,packUnit,index) {
						//新增时 先选择产品基本单位
						if(undefined == productBuyUnit ||
								null == productBuyUnit ||
								"" == productBuyUnit){
							$rootScope.showProductBuyUnitEmpty = true;
							return;
						}else{
							if(undefined != productBuyUnit.optionName){
								$rootScope.productBuyUnitShow = productBuyUnit.optionName;
							}else{
								$rootScope.productBuyUnitShow = productBuyUnit;
							}
							$rootScope.showProductBuyUnitEmpty = false;
						}

						$scope.showProductPackUnit=false;
						var pop = showPopup('addProductPackUnit',true);
						$scope.packUnitEdit = false;
						if(packUnit != undefined){
							$scope.unitValueAdd = packUnit.unitValue
							for(i in $rootScope.specificationSecondValues){
								if(packUnit.unitCode == $rootScope.specificationSecondValues[i].optionValue){
									$scope.unitObjAdd = $rootScope.specificationSecondValues[i];
								}
							}
							$scope.packUnitEdit = true;
							$scope.puindex = index;
						}else{
							$scope.unitValueAdd =null;
							$scope.unitObjAdd = null;
						}
					};
					
					// 新添加一个自定义属性及属性值
					$scope.onAddProductPackUnitItemClick = function(unitValueAdd,unitObjAdd) {
						

						if(undefined == unitValueAdd || null == unitValueAdd || "" == unitValueAdd
								|| undefined == unitObjAdd || null == unitObjAdd || "" == unitObjAdd
								|| unitValueAdd <=0){
								$scope.showProductPackUnit = true;
								return;
						}

						$scope.showProductPackUnit=false;

						//编辑状态
						if($scope.packUnitEdit){
							
							$rootScope.productPackUnit[$scope.puindex].unitName = unitObjAdd.optionName;
							$rootScope.productPackUnit[$scope.puindex].unitCode = unitObjAdd.optionValue;
							$rootScope.productPackUnit[$scope.puindex].unitValue = unitValueAdd;
							
						}else{
							var p = {unitName:unitObjAdd.optionName,unitCode:unitObjAdd.optionValue,unitValue:unitValueAdd};
							if($rootScope.productPackUnit == undefined || $rootScope.productPackUnit == null){
								$rootScope.productPackUnit = [];
								$rootScope.productPackUnit.push(p);
							}else{
								$rootScope.productPackUnit.push(p);
							}
						}
						exitPop('addProductPackUnit');
					};
					
					
					// 点击新增产品按钮是触发的事件
					$scope.onAddProductClick = function() {
				    	if(!$scope.oldEditQuit){
				    		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
					    	if(!con){
					    		return false;
					    	}else{
					    		$scope.oldEditQuit = true;//放弃当前编辑	
					    	}
				    	}
				    	//表示已经进入过新增页面
				    	$scope.newEditCount = $scope.newEditCount + 1;
			    		//第二次点击新增
			    		if($scope.newEditCount > 1){
				    		$scope.message = "您已在新增画面";
				    		//模态框隐藏确认按钮
							alert($scope.message);
							return false;
			    		}
				    	if($scope.newEditQuit){
				    		$scope.newEditQuit = false;//是否放弃当前编辑FLG
				    	}
				    	$scope.showTable = false;//产品table显示
						//清空tr背景色
				    	var trs = document.getElementById('tbone').getElementsByTagName('tr');
			    	    for( var i=1; i<trs.length; i++ ){
			    	    	trs[i].style.backgroundColor = '';
			    	    }
						//清空图片信息
						$scope.serverGs1Code = true;//产品GS1编码验证用FLG
						$scope.Gs1Exist = true;//产品GS1存在FLG
						document.compositeProductDetailAddForm.oneinput.value='';//详细图片name初始化
						document.productDetailAddForm.oneinput.value='';//详细图片name初始化
						document.getElementById("compositeProductDetailAddForm").reset();
						$rootScope.formHeader = [];//头部图片判断是否修改集合
						$rootScope.thumbHeader = [];//页面头部图片集合
						$scope.satisfiedProducts = [];//清空产品属性
						$scope.selectedProductItems = [];//清空以选择产品
						$scope.addedProduct = [];//清空产品基本信息
						$scope.addedCompositeProduct = [];//清空组合产品
						// 查询所有的品类和属性，实现三个级别的联动
						$scope.firstLevelCategories = {};
						$scope.secondLevelCategories = {};
						$scope.thirdLevelCategories = {};
						$scope.productCategoryPropertiesByCategory = {};
						//清空包装单位
						$rootScope.productPackUnit = [];
						$rootScope.UnitDisable = true;//采购基本单位disable

						// 初始化所有的一级标准品类的数据
						$http.get("getAllFirstLevelCategories", {
							categoryTypeFlag: 0
						}).success(function(data, status, headers, config) {
									$scope.firstLevelCategories = data;
	    				});
						
						//自定义属性
						$scope.customerProductProperties = [];
						//图片上传验证
						$rootScope.isHeaderPictureSatified = false;
						$rootScope.isDetailPictureSatified = false;
						
						// 定义图片属性数据结构:$rootScope.headerPictureSize张头部图片
						// 头部固定数目图片上传
						// 默认图片路径
						pictureAddress = "";
						$rootScope.thumbHeader = new Array($rootScope.headerPictureSize);
						for(var i=0;i<$rootScope.headerPictureSize;i++){
							$rootScope.thumbHeader[i] = {pictureAddress : pictureAddress};
						}
						
						$rootScope.thumbDetail = [];//页面详细图片集合
						$rootScope.formDetail = [];//判断详细图片是否修改集合

						// 组合产品和标准产品的信息显示不同。
						// 标准产品的显示
						if ($scope.compositeFlag == 0) {
							// 1.清空产品信息
							$scope.product = {};
							//商品新增页面状态
							$scope.addProduct = true;
						} else if ($scope.compositeFlag == 1) {
							// 1.清空产品信息
							$scope.compositeProduct = {};
							$scope.addCProduct = true;
							$scope.addProduct = false;
						}
					};

					// 点击产品列表中的产品项是触发的事件
					$scope.rowClick = function(index,newProduct) {
						//离开新增状态确认
				    	if(!$scope.newEditQuit){
				    		var con = confirm("您当前正处于新增状态，请确认是否放弃");
					    	if(!con){
					    		return false;
					    	}else{
					    		$scope.newEditQuit = true;//放弃当前编辑	
					    		$scope.newEditCount = 0;//确认放弃，次数归0
					    	}
				    	}
				    	//离开编辑要么确认
				    	if(!$scope.oldEditQuit){
				    		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
					    	if(!con){
					    		return false;
					    	}else{
					    		$scope.oldEditQuit = true;//放弃当前编辑	
					    	}
				    	}
				    	$scope.showTable = false;//产品table显示
						var product = $scope.products[index];//选中产品
						if(newProduct != undefined)
							product = newProduct;
						$scope.nowRow = index;
						//商品编辑页面状态
						$scope.editProduct = false;
						//商品新增页面状态
						$scope.addProduct = false;
						//清空图片信息
						$rootScope.formHeader = [];
						$rootScope.thumbHeader = [];
						$rootScope.thumbDetail = [];
						$rootScope.formDetail = [];
						//图片变量
						var productPictures;
						//获取产品状态名称
						for(i=0;i<$scope.productStatus.length;i++){
							if(product.productStatus == $scope.productStatus[i].optionValue){
								var productStatusNameTemp = $scope.productStatus[i].optionName
							}
						}
						//包装单位
						var productPackUnit
						//获取包装单位
						$http({
					        method:"GET",
					        url:"getProductPackUnit?productCode="+product.productCode
					    }).success(function(data) {
					    	productPackUnit = data;
					    	$rootScope.productPackUnit = data;
					    });
						//获取图片列表
						$http({
					        method:"GET",
					        url:"getProductPicture?productCode="+product.productCode
					    }).success(function(data) {
					    	productPictures = data;
						
						// 组合产品和标准产品的信息显示不同。
						// 标准产品的显示
						if ($scope.compositeFlag == 0) {
							$scope.showP = true;
							product.productPictures = productPictures;
							product.productPackUnit = productPackUnit;
							$scope.product = {
											id:product.id,
											productName:product.productName,
											productCode:product.productCode,
											categoryCode:product.categoryCode,
											productGs1Code:product.productGs1Code,
											saleFlag:product.saleFlag,
											productStatus:product.productStatus,
											productStatusName:productStatusNameTemp,
											productBuyUnitName:product.productBuyUnitName,
											productBuyUnitCode:product.productBuyUnitCode,
											productTemperatureZoneName:product.productTemperatureZoneName,
											productTemperatureZoneCode:product.productTemperatureZoneCode,
											compositeFlag:product.compositeFlag,
											productSpecificationValue:product.productSpecificationValue,
											productSpecificationUnitFirst:product.productSpecificationUnitFirst,
											productSpecificationUnitSecond:product.productSpecificationUnitSecond,
											productSpecificationUnitFirstValue:product.productSpecificationUnitFirstValue,
											productSpecificationUnitSecondValue:product.productSpecificationUnitSecondValue,
											productPictures:product.productPictures,
											productPackUnit:product.productPackUnit,
											productProperties:product.productProperties};

						} else if ($scope.compositeFlag == 1) {
							$scope.addCProduct = false;
							$scope.showCproduct = true;
							// 组合产品的显示
							$http.post("getCompositeProduct", {
								id : product.id
							}).success(function(data, status, headers, config) {
								$scope.compositeProduct = data;
								$scope.editCProduct = false;

								$scope.compositeProduct.product.productPictures = productPictures;
								$scope.compositeProduct.product.productPackUnit = productPackUnit;
							});
						}
						//更新图片信息,用于显示
                        for(var i =0;i<productPictures.length;i++){
                        	//头部图片
                        	if(productPictures[i].pictureShowPosition == 0){
                        		$rootScope.formHeader.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
                        		$rootScope.thumbHeader.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
                        	} else {
                        		//详情页图片
                        		$rootScope.formDetail.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
                        		$rootScope.thumbDetail.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
                        	}
                        }

						//离最大图片size还差addSize个
						var addSize = $rootScope.headerPictureSize - $rootScope.thumbHeader.length;
						//补足图片数量
                        for(var i =0;i<addSize;i++){
                        	$rootScope.formHeader.push({pictureAddress: "", changeFlag: false});
                    		$rootScope.thumbHeader.push({pictureAddress: "", changeFlag: false});
                        }
                        
						});
					};
					
					// 查询所有的品类和属性，实现三个级别的联动
					$scope.firstLevelCategories = {};
					$scope.secondLevelCategories = {};
					$scope.thirdLevelCategories = {};
					$scope.productCategoryPropertiesByCategory = {};
					
					// 初始化所有的一级标准品类的数据
					$http.get("getAllFirstLevelCategories", {
						categoryTypeFlag: 0
					}).success(function(data, status, headers, config) {
								$scope.firstLevelCategories = data;
    				});
				// 一级品类改变的时候触发的事件:级联查询二级品类
				$scope.onFirstLevelCategorychange = function(category) {
					//当没有品类code时不会进行查询
					if(undefined == category || null == category){
						return;
					}
					$http.post("getAllCategoriesByCategortyCode", {
						categoryCode : category.categoryCode
					}).success(function(data, status, headers, config) {
						$scope.secondLevelCategories = data;
						$scope.thirdLevelCategories = {};
					});
				};
				// 二级品类改变的时候触发的事件：级联查询三级品类
				$scope.onSecondLevelCategorychange = function(category) {
					if(category != null){
						$http.post("getAllCategoriesByCategortyCode", {
							categoryCode : category.categoryCode
						}).success(function(data, status, headers, config) {
							$scope.thirdLevelCategories = data;
						});
					}
				};

				$scope.searchCategoryCode = null;
				// 三级品类改变的时候触发的事件：级联查询三级品类的属性
				$scope.onThirdLevelCategorychange = function(category) {
					if(category != null){
						$scope.searchCategoryCode = category.categoryCode;
						$http.post("getProductProperties", {
							categoryCode : category.categoryCode
						}).success(
						function(data, status, headers, config) {
						 	$scope.productCategoryPropertiesByCategory = data.productCategoryPropertys;
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
							if(undefined == data[i].pictureAddress || 
									null == data[i].pictureAddress || 
									"" == data[i].pictureAddress){
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
				
				})
		.controller(
				'productDetailAddController',//普通产品新增Controller
				function($scope,$rootScope,$http) {
					$scope.addedProduct = {};
					
					//监听 
					$scope.$watch('addProduct',  function(newValue, oldValue) {
						if (newValue === oldValue) { return; } // AKA first run
						$scope.productDetailAddForm.$setPristine();
				        $scope.productDetailAddForm.$setUntouched();
						$scope.addedProduct = {};
				    });
					
					// 判断产品的属性是否都填写
					$scope.isProductPropertyMissed = true;
					$scope.onPropertyValueChange = function(addedProduct) {
						var propertyLength = 0;
						// 判断图片是否上传完成
						for ( var p in addedProduct) {
							propertyLength++;
						}
						//大于1件时
						if (propertyLength < $scope.productCategoryProperties.length) {
							$scope.isProductPropertyMissed = true;
						} else {
							$scope.isProductPropertyMissed = false;
						}
					};
					$scope.productSpecificationUnitFirstChange = function(value){
						var i = 0;
						for( wight in $rootScope.weightU){
							if(value.optionValue == $rootScope.weightU[wight]){
								i++;
							}
						}
						if(i == 0){
							$rootScope.UnitDisable = true;
						}else{
							$rootScope.UnitDisable = false;
						}
					}
					//产品规格二级单位改变时，为采购基本单位赋值
					$scope.productSpecificationUnitSecondChange = function(value){
						$rootScope.BasicPurchaseUnit = angular.copy($rootScope.BasicPurchaseUnitCopy);
						$rootScope.BasicPurchaseUnit.push(value);
						$scope.addedProduct.productBuyUnit = value;
					}
					//图片上传
					$scope.productImageDetail = function(data){
						var product = {};
						// 返回数据的处理---基础数据
						product.productName = $scope.addedProduct.productName;
						product.productCode = $scope.addedProduct.productCode;
						product.productGs1Code = $scope.addedProduct.productGs1Code;
						if ($scope.addedProduct.saleFlag == undefined || $scope.addedProduct.saleFlag == null) {
							$scope.addedProduct.saleFlag = 1;
						}
						product.saleFlag = $scope.addedProduct.saleFlag;
						// 标准产品
						product.compositeFlag = 0;
						product.categoryCode = $scope.categoryCode;
						product.productSpecificationValue = $scope.addedProduct.productSpecificationValue;
						product.productSpecificationUnitFirst = $scope.addedProduct.productSpecificationUnitFirst.optionName;
						product.productSpecificationUnitSecond = $scope.addedProduct.productSpecificationUnitSecond.optionName;
						product.productSpecificationUnitFirstValue = $scope.addedProduct.productSpecificationUnitFirst.optionValue;
						product.productSpecificationUnitSecondValue = $scope.addedProduct.productSpecificationUnitSecond.optionValue;
						//产品状态
						product.productStatus = $scope.addedProduct.productStatus.optionValue;
						//产品售卖单位
						product.productBuyUnitCode = $scope.addedProduct.productBuyUnit.optionValue;
						product.productBuyUnitName = $scope.addedProduct.productBuyUnit.optionName;
						//温区
						product.productTemperatureZoneCode = $scope.addedProduct.productTemperatureZone.optionValue;
						product.productTemperatureZoneName = $scope.addedProduct.productTemperatureZone.optionName;
						// 返回数据的处理---产品的属性
						var productProperties = [];
						for (var i = 0; i < $scope.productCategoryProperties.length; i++) {
							productProperty = {};
							productPropertyName = $scope.productCategoryProperties[i].categoryPropertyName;
							productPropertyValue = $scope.addedProduct.productProperty[i].categoryPropertyValue;
							productProperty.productPropertyName = productPropertyName;
							productProperty.productCode = $scope.addedProduct.productCode;
							productProperty.productPropertyValue = productPropertyValue;
							productProperties.push(productProperty);
						}
						// 返回数据的处理---产品的自定义属性
						Array.prototype.push.apply(productProperties, $scope.customerProductProperties);

						product.productProperties = productProperties;
						// 返回数据的处理---产品的图片
						//头部图片信息
				    	var index = $rootScope.thumbHeader.length;
				    	product.productPictures = [];
						for(var image in $rootScope.thumbHeader){
							if(product.productPictures[image] == undefined || product.productPictures[image] == null)
								product.productPictures.push({pictureAddress : $rootScope.thumbHeader[image].pictureAddress,pictureShowPosition:0,pictureShowOrder:image});
							else{
								product.productPictures[image].pictureAddress = $rootScope.thumbHeader[image].pictureAddress;
								product.productPictures[image].pictureShowPosition = 0;//头部图片，0固定
								product.productPictures[image].pictureShowOrder = image;
							}
							if(data != null){
								for(var i = 0;i<data.length;i++){
									//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
									if(data[i].pictureShowPosition == 0 && image == data[i].pictureShowOrder){
										product.productPictures[image].pictureAddress = data[i].pictureAddress;
										break;
									}
								}
							}
						}
						//详情页图片信息
						for(var image in $rootScope.thumbDetail){
							if(product.productPictures[index] == undefined || product.productPictures[index] == null)
								product.productPictures.push({pictureAddress : $rootScope.thumbDetail[image].pictureAddress,pictureShowPosition:1,pictureShowOrder:image});
							else{
								product.productPictures[index].pictureAddress = $rootScope.thumbDetail[image].pictureAddress;
								product.productPictures[index].pictureShowPosition = 1;//详情图片，1固定
								product.productPictures[index].pictureShowOrder = image;
							}
							
							if(data != null){
								for(var i = 0;i<data.length;i++){
									//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
									if(data[i].pictureShowPosition == 1 && image == data[i].pictureShowOrder){
										product.productPictures[index].pictureAddress = data[i].pictureAddress;
										break;
									}
								}
							}
							index ++;
						}
						
                        //更新图片的编码
						for(var i=0; i<product.productPictures.length;i++){
							product.productPictures[i].productCode = $scope.addedProduct.productCode;
						}

						//去除头部图片的空位置
						product.productPictures = $rootScope.deletBlank(product.productPictures);
						//包装单位
						product.productPackUnit = $rootScope.productPackUnit;
						$scope.addedProduct = product;
						//页面新增数据上传
						$http.post("addProduct", $scope.addedProduct)
						.success(
								function(data) {
									//新增完成。FLG重置
							    	if(!$scope.$parent.newEditQuit){
							    		$scope.$parent.newEditQuit = true;//放弃当前新增
							    		$scope.$parent.newEditCount = 0;//确认放弃，次数归0
							    	}
									$scope.$parent.doubleClick = false;//恢复完成按钮disable属性
									exitPop('submitInfo');
									$scope.$parent.doubleC = 0;//完成提交初始化二重check
									$scope.$parent.product = data;//返回数据
									//处理新增之后直接编辑的情况
									$rootScope.formHeader = [];
									$rootScope.thumbHeader = [];
									$rootScope.thumbDetail = [];
									$rootScope.formDetail = [];
									productPictures = $scope.product.productPictures;
									if(undefined != productPictures){
										for(var i =0;i<productPictures.length;i++){
											//头部图片
											if(productPictures[i].pictureShowPosition === 0){
												$rootScope.formHeader.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
												$rootScope.thumbHeader.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
											} else {
												//详情页图片
												$rootScope.formHeader.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
												$rootScope.thumbDetail.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
											}
										}
									}
									$scope.selectByPage($scope.pagination.pageNum,$scope.pagination.pageSize,$scope.categoryCode,null,$scope.productProperty,null,$scope.compositeFlag);
								});
						//新增完成后进入查看界面显示该产品
						$scope.$parent.addProduct = false;//组合新增不显示
						$scope.$parent.showP = true;//普通产品编辑页面总体不显示
						$scope.$parent.editProduct = false;//普通产品编辑页面不显示
					}
					
					$scope.checkproductGs1Code = function(){
						//产品GS1编码验证
						if($scope.addedProduct.productGs1Code != undefined 
								&& $scope.addedProduct.productGs1Code.length != undefined){
							//输入gs1编码时
							if($scope.addedProduct.productGs1Code.length > 0){
								//查询gs1编码格式正确性
								var response = $http.post("/pms-web/pms/validatecode/checkvalidateGS1Code?code="+$scope.addedProduct.productGs1Code);
								response.success(function(data, status, headers, config) {
									$scope.$parent.serverGs1Code = data;
								});
								//查询数据库gs1编码格是否存在
								var res = $http.post("checkGS1IsExist",$scope.addedProduct);
								res.success(function(data, status, headers, config) {
									$scope.$parent.Gs1Exist = data;
								});
								//服务器验证失败时优先显示服务器信息
								if(!$scope.$parent.serverGs1Code){
									$scope.$parent.Gs1Exist = true;//GS1编码本地验证
								}
							}else{//未输入不check
								$scope.$parent.serverGs1Code = true;//GS1编码服务器验证
								$scope.$parent.Gs1Exist = true;//GS1编码本地验证
							}
						}
					}
					//标准产品
					$scope.onAddSaveClick = function() {

						//防止表单重复提交
						$scope.$parent.doubleC = $scope.$parent.doubleC + 1;
						if($scope.$parent.doubleC > 0){
							$scope.$parent.doubleClick = true;
							$rootScope.message = "正在提交数据,请稍后,成功后自动关闭！";
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
						//图片没有变更时，提交页面数据
						var ib = 0;$scope.pic = [];
						if(ic == 0){
							$scope.productImageDetail($scope.pic);
							return;
						}
						//处理图片位置信息
						var imageSize = 0;
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
								url : 'imageUpload',
								data : data,
								headers : {
									'Content-Type' : undefined
								},
								transformRequest : angular.identity
							}).success(function(newdata) {
								$scope.pic[ib] = newdata[0];
								ib ++;
								if(ib == ic)
									$scope.productImageDetail($scope.pic);//图片有变更时，提交页面数据
							});
						}
					};
				})
		.controller(
				'productDetailController',//普通产品编辑controller
				function($scope,$rootScope,$http) {
					
					$scope.checkproductGs1Code = function(){
						//产品GS1编码验证
						if($scope.product.productGs1Code != undefined 
								&& $scope.product.productGs1Code.length != undefined){
							if($scope.product.productGs1Code.length > 0){
								var response = $http.post("/pms-web/pms/validatecode/checkvalidateGS1Code?code="+$scope.product.productGs1Code);
								response.success(function(data, status, headers, config) {
									$scope.$parent.serverGs1Code = data;
								});
								var res = $http.post("checkGS1IsExist",$scope.product);
								res.success(function(data, status, headers, config) {
									$scope.$parent.Gs1Exist = data;
								});
								//服务器验证失败时优先显示服务器信息
								if(!$scope.$parent.serverGs1Code){
									$scope.$parent.Gs1Exist = true;//GS1编码本地验证
								}
							}else{
								$scope.$parent.serverGs1Code = true;
								$scope.$parent.Gs1Exist = true;
							}
						}
					}
					
					$scope.productSpecificationUnitFirstChange = function(value){
						var i = 0;
						for( wight in $rootScope.weightN){
							if(value == $rootScope.weightN[wight]){
								i++;
							}
						}
						if(i == 0){
							$rootScope.UnitDisable = true;
						}else{
							$rootScope.UnitDisable = false;
						}
					}
					//产品规格二级单位改变时，为采购基本单位赋值
					$scope.productSpecificationUnitSecondChange = function(value){
						var match = $rootScope.specificationSecondValues.filter(function(e) {
					        return e.optionName == value;
						});
						$rootScope.BasicPurchaseUnit = angular.copy($rootScope.BasicPurchaseUnitCopy);
						$rootScope.BasicPurchaseUnit.push(match[0]);
						$scope.product.productBuyUnitName = match[0].optionName;
						$scope.product.productBuyUnitCode = match[0].optionValue;
					}
					
					// 点击编辑按钮触发的事件
					$scope.onEditClick = function() {
						
				    	if($scope.$parent.oldEditQuit){
				    		$scope.$parent.oldEditQuit = false;//是否放弃当前编辑FLG
				    	}
						
				    	$scope.otherProperty = [];
						if($scope.productCategoryProperties != undefined)
						for(var i=0;i<$scope.productCategoryProperties.length;i++){
							var count = 0;
							if($scope.product != undefined && $scope.product.productProperties != undefined)
							for(var j=0;j<$scope.product.productProperties.length;j++){
								if($scope.product.productProperties[j].productPropertyName == $scope.productCategoryProperties[i].categoryPropertyName){
									count ++;
									break;
								}
							}
							if(count == 0){
								$scope.otherProperty.push($scope.productCategoryProperties[i]);
							}
						}
						//离最大图片size还差addSize个
						var addSize = $rootScope.headerPictureSize - $rootScope.thumbHeader.length;
						//补足图片数量
                        for(var i =0;i<addSize;i++){
                        	$rootScope.formHeader.push({pictureAddress: "", changeFlag: false});
                    		$rootScope.thumbHeader.push({pictureAddress: "", changeFlag: false});
                        }
						document.productDetailEditForm.oneinput.value='';//详细图片name初始化
						$scope.$parent.serverGs1Code = true;//产品GS1编码验证用FLG
						$scope.$parent.Gs1Exist = true;
						$scope.$parent.editProduct = true;
						//图片上传限制
						$rootScope.isHeaderPictureSatified = true;
						$rootScope.isDetailPictureSatified = true;
						$rootScope.showProductBuyUnitEmpty = false;// 包装单位错误表示

						var i = 0;
						for( wight in $rootScope.weightU){
							if($scope.product.productSpecificationUnitFirstValue == $rootScope.weightU[wight]){
								i++;
							}
						}
						if(i == 0){
							$rootScope.UnitDisable = true;
						}else{
							$rootScope.UnitDisable = false;
						}
						var match = $rootScope.specificationSecondValues.filter(function(e) {
					        return e.optionValue == $scope.product.productSpecificationUnitSecondValue;
						});
						$rootScope.BasicPurchaseUnit = angular.copy($rootScope.BasicPurchaseUnitCopy);
						$rootScope.BasicPurchaseUnit.push(match[0]);
						
					};
					
					//标准产品图片编辑
					$scope.bzcpImage = function(data){
						//产品属性处理
						$scope.product.productProperties = [];
						$(".propertyV").each(function (i){
							$scope.product.productProperties.push({productPropertyName:this.id,productPropertyValue:this.value});
						});
						$(".propertyV1").each(function (i){
							$scope.product.productProperties.push({productPropertyName:this.name,productPropertyValue:this.value,customerDefineFlag:1});
						});
						//第一单位Value赋值
						for(i in $scope.specificationFirstValues){
							if($scope.product.productSpecificationUnitFirst == $scope.specificationFirstValues[i].optionName){
								$scope.product.productSpecificationUnitFirstValue = $scope.specificationFirstValues[i].optionValue;
							}
						}
						//第二单位Value赋值
						for(i in $rootScope.specificationSecondValues){
							if($scope.product.productSpecificationUnitSecond == $rootScope.specificationSecondValues[i].optionName){
								$scope.product.productSpecificationUnitSecondValue = $rootScope.specificationSecondValues[i].optionValue;
							}
						}
						
						// 返回数据的处理---产品的图片
						//头部图片信息
				    	var index = $rootScope.thumbHeader.length;
				    	$scope.product.productPictures = [];
						for(var image in $rootScope.thumbHeader){
							if($scope.product.productPictures[image] == undefined || $scope.product.productPictures[image] == null)
								$scope.product.productPictures.push({pictureAddress : $rootScope.thumbHeader[image].pictureAddress,pictureShowPosition:0,pictureShowOrder:image});
							else{
								$scope.product.productPictures[image].pictureAddress = $rootScope.thumbHeader[image].pictureAddress;
								$scope.product.productPictures[image].pictureShowPosition = 0;//头部图片，0固定
								$scope.product.productPictures[image].pictureShowOrder = image;
							}
							if(data != null){
								for(var i = 0;i<data.length;i++){
									//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
									if(data[i].pictureShowPosition == 0 && image == data[i].pictureShowOrder){
										$scope.product.productPictures[image].pictureAddress = data[i].pictureAddress;
										break;
									}
								}
							}
						}
						//详情页图片信息
						for(var image in $rootScope.thumbDetail){
							if($scope.product.productPictures[index] == undefined || $scope.product.productPictures[index] == null)
								$scope.product.productPictures.push({pictureAddress : $rootScope.thumbDetail[image].pictureAddress,pictureShowPosition:1,pictureShowOrder:image});
							else{
								$scope.product.productPictures[index].pictureAddress = $rootScope.thumbDetail[image].pictureAddress;
								$scope.product.productPictures[index].pictureShowPosition = 1;//详情图片，1固定
								$scope.product.productPictures[index].pictureShowOrder = image;
							}
							
							if(data != null){
								for(var i = 0;i<data.length;i++){
									//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
									if(data[i].pictureShowPosition == 1 && image == data[i].pictureShowOrder){
										$scope.product.productPictures[index].pictureAddress = data[i].pictureAddress;
										break;
									}
								}
							}
							index ++;
						}
						//去除头部图片的空位置
						$scope.product.productPictures = $rootScope.deletBlank($scope.product.productPictures);
						//包装单位
						$scope.product.productPackUnit = $rootScope.productPackUnit;
						//更新页面数据
						$http.post("updateProduct",	$scope.product)
							.success(function(data) {
								$scope.$parent.doubleClick = false;
								exitPop('submitInfo');
								$scope.$parent.doubleC = 0;
								$scope.$parent.oldEditQuit = true;//放弃当前编辑	
								$scope.selectByPage($scope.$parent.pagination.pageNum,$scope.$parent.pagination.pageSize,$scope.$parent.categoryCode,null,$scope.$parent.productProperty,null,$scope.$parent.compositeFlag);
								$scope.rowClick($scope.$parent.nowRow,$scope.$parent.product);
							});
					}
					
					//标准产品编辑保存
					$scope.onSaveClick = function() {
						$scope.$parent.doubleC = $scope.$parent.doubleC + 1;//点击过保存按钮disable
						if($scope.$parent.doubleC > 0){
							$scope.$parent.doubleClick = true;
							$rootScope.message = "正在提交数据,请稍后,成功后自动关闭！";
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
						//图片未修改，提交页面数据
						var ib = 0;$scope.pic = [];
						if(ic == 0){
							$scope.bzcpImage($scope.pic);
							return;
						}
						var imageSize = 0;
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
								url : 'imageUpload',
								data : data,
								headers : {
									'Content-Type' : undefined
								},
								transformRequest : angular.identity
							}).success(function(newdata) {
								$scope.pic[ib] = newdata[0];
								ib ++;
								if(ib == ic)
									$scope.bzcpImage($scope.pic);//完成图片上传，提交页面数据
							});
						}
					};

					// 产品属性的修改的记录
					$scope.changedProductproperties = [];
					$scope.productPropertyChange = function(name, value) {
						$scope.changedProductproperties.push({
							productPropertyName : name.productPropertyName,
							productPropertyValue : value.categoryPropertyValue
						});
					};
				})
		.controller('compositeProductDetailController',//组合产品编辑
				function($scope,$rootScope,$http) {
			
					$scope.checkproductGs1Code = function(){
						//产品GS1编码验证
						if($scope.compositeProduct.product.productGs1Code != undefined 
								&& $scope.compositeProduct.product.productGs1Code.length != undefined){
							//输入gs1编码时
							if($scope.compositeProduct.product.productGs1Code.length > 0){
								//查询gs1编码格式正确性
								var response = $http.post("/pms-web/pms/validatecode/checkvalidateGS1Code?code="+$scope.compositeProduct.product.productGs1Code);
								response.success(function(data, status, headers, config) {
									$scope.$parent.serverGs1Code = data;
								});
								//查询数据库gs1编码格是否存在
								var res = $http.post("checkGS1IsExist",$scope.compositeProduct.product);
								res.success(function(data, status, headers, config) {
									$scope.$parent.Gs1Exist = data;
								});
								//服务器验证失败时优先显示服务器信息
								if(!$scope.$parent.serverGs1Code){
									$scope.$parent.Gs1Exist = true;//GS1编码本地验证
								}
							}else{
								$scope.$parent.serverGs1Code = true;//GS1编码服务器验证
								$scope.$parent.Gs1Exist = true;//GS1编码本地验证
							}
						}
					}
								
					$scope.productSpecificationUnitFirstChange = function(value){
						var i = 0;
						for( wight in $rootScope.weightN){
							if(value == $rootScope.weightN[wight]){
								i++;
							}
						}
						if(i == 0){
							$rootScope.UnitDisable = true;
						}else{
							$rootScope.UnitDisable = false;
						}
					}
					//产品规格二级单位改变时，为采购基本单位赋值
					$scope.productSpecificationUnitSecondChange = function(value){
						var match = $rootScope.specificationSecondValues.filter(function(e) {
					        return e.optionName == value;
						});
						$rootScope.BasicPurchaseUnit = angular.copy($rootScope.BasicPurchaseUnitCopy);
						$rootScope.BasicPurchaseUnit.push(match[0]);
						$scope.compositeProduct.product.productBuyUnitName = match[0].optionName;
						$scope.compositeProduct.product.productBuyUnitCode = match[0].optionValue;
					}
					
					// 点击编辑按钮触发的事件
					$scope.onEditClick = function() {

				    	if($scope.$parent.oldEditQuit){
				    		$scope.$parent.oldEditQuit = false;//是否放弃当前编辑FLG
				    	}
						// 查询所有的品类和属性，实现三个级别的联动
						$scope.$parent.firstLevelCategories = {};
						$scope.$parent.secondLevelCategories = {};
						$scope.$parent.thirdLevelCategories = {};
						$scope.$parent.productCategoryPropertiesByCategory = {};
						
						// 初始化所有的一级标准品类的数据
						$http.get("getAllFirstLevelCategories", {
							categoryTypeFlag: 0
						}).success(function(data, status, headers, config) {
									$scope.$parent.firstLevelCategories = data;
	    				});
						
						document.compositeProductDetailEditForm.oneinput.value='';//详细图片name初始化
						$scope.$parent.editCProduct = true;//组合新增显示
						$scope.$parent.serverGs1Code = true;//产品GS1编码验证用FLG
						$scope.$parent.Gs1Exist = true;//gs1是否重复
						$rootScope.showProductBuyUnitEmpty = false;// 包装单位错误表示
						$scope.otherProperty = [];
						if($scope.productCategoryProperties != undefined)
							//添加商品属性
						for(var i=0;i<$scope.productCategoryProperties.length;i++){
							var count = 0;
							if($scope.compositeProduct.product != undefined && $scope.compositeProduct.product.productProperties != undefined)
							for(var j=0;j<$scope.compositeProduct.product.productProperties.length;j++){
								if($scope.compositeProduct.product.productProperties[j].productPropertyName == $scope.productCategoryProperties[i].categoryPropertyName){
									count ++;
									break;
								}
							}
							//添加品类追加属性
							if(count == 0){
								$scope.otherProperty.push($scope.productCategoryProperties[i]);
							}
						}
						//离最大图片size还差addSize个
						var addSize = $rootScope.headerPictureSize - $rootScope.thumbHeader.length;
						//补足图片数量
                        for(var i =0;i<addSize;i++){
                        	$rootScope.formHeader.push({pictureAddress: "", changeFlag: false});
                    		$rootScope.thumbHeader.push({pictureAddress: "", changeFlag: false});
                        }
						//图片上传限制
						$rootScope.isHeaderPictureSatified = true;
						$rootScope.isDetailPictureSatified = true;
						$scope.$parent.haveprodect = true;//已选产品，完成BOTTON可点
    					$scope.$parent.satisfiedProducts = {};
    					$scope.$parent.selectedProductItems = [];
						for(var i=0;i<$scope.compositeProduct.productItems.length;i++){
							$scope.$parent.selectedProductItems.push($scope.compositeProduct.productItems[i]);
						}

						var i = 0;
						for( wight in $rootScope.weightU){
							if($scope.compositeProduct.product.productSpecificationUnitFirstValue == $rootScope.weightU[wight]){
								i++;
							}
						}
						if(i == 0){
							$rootScope.UnitDisable = true;
						}else{
							$rootScope.UnitDisable = false;
						}
						var match = $rootScope.specificationSecondValues.filter(function(e) {
					        return e.optionValue == $scope.compositeProduct.product.productSpecificationUnitSecondValue;
						});
						$rootScope.BasicPurchaseUnit = angular.copy($rootScope.BasicPurchaseUnitCopy);
						$rootScope.BasicPurchaseUnit.push(match[0]);
					};
					
					//产品的属性改变是触发的事件
					$scope.propertySearchCriteria = null;
					$scope.onProductPropertyChange = function(index,propertyName, propertyValue){
						for(indexTemp in $scope.productCategoryPropertiesByCategory){
							if(index != indexTemp){
								$scope.productCategoryPropertiesByCategory[indexTemp].selectedValue ="";
							}
						}
						if(propertyValue == undefined || propertyName == undefined){
							$scope.propertySearchCriteria = null;
						}
						else{
							$scope.propertySearchCriteria = {productPropertyName: propertyName, productPropertyValue: propertyValue};
						}
					}
					// 点击搜索按钮的时候对应的标准产品用于筛选
					$scope.onSearchButtonClick = function(property) {
						$http.post("getAllProductsByCriteria", {
							productProperty: $scope.propertySearchCriteria,
							categoryCode : $scope.searchCategoryCode,
							compositeFlag: 0
							}).success(
							function(data) {
								$scope.$parent.satisfiedProducts = data;
							});
					};

					//选择产品时触发的事件
					$scope.onProductSelectedClick = function(index) {
						$scope.$parent.haveprodect = true;//已选产品，完成BOTTON可点
						var addedProduct = $scope.$parent.satisfiedProducts[index];
						var quantity = 1;
						var productAlreadyAddedindex = -1;
						for(var i=0; i<$scope.$parent.selectedProductItems.length; i++){
							if($scope.$parent.selectedProductItems[i].product === addedProduct){
								productAlreadyAddedindex = i;
								break;
							}
						}
						//第一次添加
						if(productAlreadyAddedindex == -1) {
							//设置产品的默认数量为1
							$scope.$parent.selectedProductItems.push({product: addedProduct, quantity: quantity});
						} else {
							//重复选择
							quantity = $scope.$parent.selectedProductItems[productAlreadyAddedindex].quantity + 1;
							$scope.$parent.selectedProductItems[productAlreadyAddedindex].quantity = quantity;
						}
					};

					// 产品数量改变所触发的事件
					$scope.onProductQuantityMinus = function(index) {
						var quantity = $scope.$parent.selectedProductItems[index].quantity;
						//当产品的数量为0的时候，删除该产品
						if(quantity==1){
							$scope.$parent.selectedProductItems.splice(index,1);
							//没有选择任何产品时，完成BOTTON不可点
							if($scope.selectedProductItems.length == 0){
								$scope.$parent.haveprodect = false;
							}else{
								$scope.$parent.haveprodect = true;
							}
						}else
							$scope.$parent.selectedProductItems[index].quantity = $scope.$parent.selectedProductItems[index].quantity-1;
					};
					// 产品数量改变所触发的事件
					$scope.onProductQuantityAdd = function(index) {
						$scope.$parent.selectedProductItems[index].quantity = $scope.$parent.selectedProductItems[index].quantity+1;
					};
					
					//组合产品上传控制
					$scope.cImagef = function(data){
						$scope.compositeProduct.product.id = $scope.$parent.compositeProduct.product.id;
						$scope.compositeProduct.product.productProperties = [];
						$(".propertyV1").each(function (i){
							$scope.compositeProduct.product.productProperties.push({productPropertyName:this.id,productPropertyValue:this.value});
						});

							// 返回数据的处理---产品的图片
							//头部图片信息
					    	var index = $rootScope.thumbHeader.length;
					    	$scope.compositeProduct.product.productPictures = [];
							for(var image in $rootScope.thumbHeader){
								if($scope.compositeProduct.product.productPictures[image] == undefined || $scope.product.productPictures[image] == null)
									$scope.compositeProduct.product.productPictures.push({pictureAddress : $rootScope.thumbHeader[image].pictureAddress,pictureShowPosition:0,pictureShowOrder:image});
								else{
									$scope.compositeProduct.product.productPictures[image].pictureAddress = $rootScope.thumbHeader[image].pictureAddress;
									$scope.compositeProduct.product.productPictures[image].pictureShowPosition = 0;
									$scope.compositeProduct.product.productPictures[image].pictureShowOrder = image;
								}
								if(data != null){
									for(var i = 0;i<data.length;i++){
										//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
										if(data[i].pictureShowPosition == 0 && image == data[i].pictureShowOrder){
											$scope.compositeProduct.product.productPictures[image].pictureAddress = data[i].pictureAddress;
											break;
										}
									}
								}
							}
							//详情页图片信息
							for(var image in $rootScope.thumbDetail){
								if($scope.compositeProduct.product.productPictures[index] == undefined || $scope.compositeProduct.product.productPictures[index] == null)
									$scope.compositeProduct.product.productPictures.push({pictureAddress : $rootScope.thumbDetail[image].pictureAddress,pictureShowPosition:1,pictureShowOrder:image});
								else{
									$scope.compositeProduct.product.productPictures[index].pictureAddress = $rootScope.thumbDetail[image].pictureAddress;
									$scope.compositeProduct.product.productPictures[index].pictureShowPosition = 1;
									$scope.compositeProduct.product.productPictures[index].pictureShowOrder = image;
								}
								
								if(data != null){
									for(var i = 0;i<data.length;i++){
										//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
										if(data[i].pictureShowPosition == 1 && image == data[i].pictureShowOrder){
											$scope.compositeProduct.product.productPictures[index].pictureAddress = data[i].pictureAddress;
											break;
										}
									}
								}
								index ++;
							}
							

							//第一单位Value赋值
							for(i in $scope.specificationFirstValues){
								if($scope.compositeProduct.product.productSpecificationUnitFirst == $scope.specificationFirstValues[i].optionName){
									$scope.compositeProduct.product.productSpecificationUnitFirstValue = $scope.specificationFirstValues[i].optionValue;
								}
							}
							//第二单位Value赋值
							for(i in $rootScope.specificationSecondValues){
								if($scope.compositeProduct.product.productSpecificationUnitSecond == $rootScope.specificationSecondValues[i].optionName){
									$scope.compositeProduct.product.productSpecificationUnitSecondValue = $rootScope.specificationSecondValues[i].optionValue;
								}
							}
							//去除头部图片的空位置
							$scope.compositeProduct.product.productPictures = $rootScope.deletBlank($scope.compositeProduct.product.productPictures);				
							$scope.compositeProduct.productItems = $scope.$parent.selectedProductItems;
							//包装单位
							$scope.compositeProduct.product.productPackUnit = $rootScope.productPackUnit;
							$http.post("updateCompositeProduct",$scope.compositeProduct)
								.success(function(data) {
									$scope.$parent.doubleClick = false;
									exitPop('submitInfo');
									$scope.$parent.doubleC = 0;
									$scope.$parent.oldEditQuit = true;//放弃当前编辑	
			                        $scope.$parent.editCProduct = false;//隐藏编辑画面
			                        $scope.$parent.showCproduct = true;//显示表示画面
									$scope.selectByPage($scope.$parent.pagination.pageNum,$scope.$parent.pagination.pageSize,$scope.$parent.categoryCode,null,$scope.$parent.productProperty,null,$scope.$parent.compositeFlag);
									$scope.rowClick($scope.$parent.nowRow,$scope.compositeProduct.product);
									});
					};
					
					//组合产品
					$scope.onSaveClick = function() {
						
						$scope.$parent.doubleC = $scope.$parent.doubleC + 1;
						if($scope.$parent.doubleC > 0){
							$scope.$parent.doubleClick = true;
							$rootScope.message = "正在提交数据,请稍后,成功后自动关闭！";
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
						//图片未修改提交数据
						var ib = 0;$scope.pic = [];
						if(ic == 0){
							$scope.cImagef($scope.pic);
							return;
						}
						var imageSize = 0;
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
								url : 'imageUpload',
								data : data,
								headers : {
									'Content-Type' : undefined
								},
								transformRequest : angular.identity
							}).success(function(newdata) {
								$scope.pic[ib] = newdata[0];
								ib ++;
								if(ib == ic)
									$scope.cImagef($scope.pic);//修改过图片提交数据
							});
						}
					};

					// 产品属性的修改的记录
					$scope.changedProductproperties = [];
					$scope.compositeProductPropertyChange = function(name, value) {
					$scope.changedProductproperties.push({
									compositeProductPropertyName : name.compositeProductPropertyName,
									compositeProductPropertyValue : value.categoryPropertyValue
								});
					};

				}).controller(
						'compositeProductDetailAddController',//组合产品新增
						function($scope,$rootScope,$http) {

							//保存临时的选择的产品项
							$scope.$parent.selectedProductItems =[];
							//搜索满足条件的标准产品
							$scope.$parent.satisfiedProducts = [];
							$scope.addedProduct = {};
							$scope.addedCompositeProduct = {};
							//监听 
							$scope.$watch('addCProduct',  function(newValue, oldValue) {
								if (newValue === oldValue) { return; } // AKA first run
								$scope.compositeProductDetailAddForm.$setPristine();
						        $scope.compositeProductDetailAddForm.$setUntouched();
								$scope.addedProduct = {};
						    });
							$scope.$parent.serverGs1Code =true;
							$scope.checkproductGs1Code = function(){
								//产品GS1编码验证
								if($scope.addedProduct.productGs1Code != undefined 
										&& $scope.addedProduct.productGs1Code.length != undefined){
									//输入gs1编码时
									if($scope.addedProduct.productGs1Code.length > 0){
										//查询gs1编码格式正确性
										var response = $http.post("/pms-web/pms/validatecode/checkvalidateGS1Code?code="+$scope.addedProduct.productGs1Code);
										response.success(function(data, status, headers, config) {
											$scope.$parent.serverGs1Code = data;
										});
										//查询数据库gs1编码格是否存在
										var res = $http.post("checkGS1IsExist",$scope.addedProduct);
										res.success(function(data, status, headers, config) {
											$scope.$parent.Gs1Exist = data;
										});
										//服务器验证失败时优先显示服务器信息
										if(!$scope.$parent.serverGs1Code){
											$scope.$parent.Gs1Exist = true;//GS1编码本地验证
										}
									}else{
										$scope.$parent.serverGs1Code =true;//GS1编码服务器验证
										$scope.$parent.Gs1Exist = true;//GS1编码本地验证
									}
								}
							}						
							

							//产品的属性改变是触发的事件
							$scope.propertySearchCriteria = null;
							$scope.onProductPropertyChange = function(index,propertyName, propertyValue) {
								for(indexTemp in $scope.productCategoryPropertiesByCategory){
									if(index != indexTemp){
										$scope.productCategoryPropertiesByCategory[indexTemp].selectedValue ="";
									}
								}
								if(propertyValue == undefined || propertyName == undefined){
									$scope.propertySearchCriteria = null;
								}
								else{
									$scope.propertySearchCriteria = {productPropertyName: propertyName, productPropertyValue: propertyValue};
								}
							}

						$scope.productSpecificationUnitFirstChange = function(value){
							var i = 0;
							for( wight in $rootScope.weightU){
								if(value.optionValue == $rootScope.weightU[wight]){
									i++;
								}
							}
							if(i == 0){
								$rootScope.UnitDisable = true;
							}else{
								$rootScope.UnitDisable = false;
							}
						}
						//产品规格二级单位改变时，为采购基本单位赋值
						$scope.productSpecificationUnitSecondChange = function(value){
							$rootScope.BasicPurchaseUnit = angular.copy($rootScope.BasicPurchaseUnitCopy);
							$rootScope.BasicPurchaseUnit.push(value);
							$scope.addedProduct.productBuyUnit = value;
							}
						// 点击搜索按钮的时候对应的标准产品用于筛选
						$scope.onSearchButtonClick = function(property) {
							if($scope.searchCategoryCode != undefined && $scope.searchCategoryCode != ''){
								$http.post("getAllProductsByCriteria", {
									productProperty: $scope.propertySearchCriteria,
									categoryCode : $scope.searchCategoryCode,
									compositeFlag: 0
									}).success(
									function(data, status, headers, config) {
										$scope.$parent.satisfiedProducts = data;
									});
							}
						};

						//选择产品时触发的事件
						$scope.onProductSelectedClick = function(index) {
							$scope.$parent.haveprodect = true;//已选产品，完成BOTTON可点
							var addedProduct = $scope.$parent.satisfiedProducts[index];
							var quantity = 1;
							var productAlreadyAddedindex = -1;
							for(var i=0; i<$scope.$parent.selectedProductItems.length; i++){
								if($scope.$parent.selectedProductItems[i].product === addedProduct){
									productAlreadyAddedindex = i;
									break;
								}
							}
							//第一次添加
							if(productAlreadyAddedindex === -1) {
								//设置产品的默认数量为1
								$scope.$parent.selectedProductItems.push({product: addedProduct, quantity: quantity});
							} else {
								//重复选择
								quantity = $scope.$parent.selectedProductItems[productAlreadyAddedindex].quantity + 1;
								$scope.$parent.selectedProductItems[productAlreadyAddedindex].quantity = quantity;
							}
						};

						// 产品数量改变所触发的事件
						$scope.onProductQuantityMinus = function(index) {
							var quantity = $scope.$parent.selectedProductItems[index].quantity;
							//当产品的数量为0的时候，删除该产品
							if(quantity==1){
								$scope.$parent.selectedProductItems.splice(index,1);
								//没有选择任何产品时，完成BOTTON不可点
								if($scope.selectedProductItems.length == 0){
									$scope.$parent.haveprodect = false;
								}else{
									$scope.$parent.haveprodect = true;
								}
							}else
								$scope.$parent.selectedProductItems[index].quantity = $scope.$parent.selectedProductItems[index].quantity-1;
						};
						// 产品数量改变所触发的事件
						$scope.onProductQuantityAdd = function(index) {
							$scope.$parent.selectedProductItems[index].quantity = $scope.$parent.selectedProductItems[index].quantity+1;
						};
						
							// 判断产品的属性是否都填写
							$scope.isProductPropertyMissed = true;
							$scope.onPropertyValueChange = function(addedCompositeProduct) {
								var propertyLength = 0;
								// 判断图片是否上传完成
								for ( var p in addedCompositeProduct) {
									propertyLength++;
								}
								if (propertyLength < $scope.productCategoryProperties.length) {
									$scope.isProductPropertyMissed = true;
								} else {
									$scope.isProductPropertyMissed = false;
								}
							};
							
							//组合产品新增图片信息
							$scope.cImageUp = function(data){
								var product = {};
								// 返回数据的处理---基础数据
								product.productName = $scope.addedProduct.productName;
								product.productCode = $scope.addedProduct.productCode;
								product.productGs1Code = $scope.addedProduct.productGs1Code;
								if ($scope.addedProduct.saleFlag == undefined || $scope.addedProduct.saleFlag == null) {
									$scope.addedProduct.saleFlag = 1;
								}
								product.saleFlag = $scope.addedProduct.saleFlag;
								// 标准产品
								product.compositeFlag = 1;
								product.categoryCode = $scope.categoryCode;
								product.productSpecificationValue = $scope.addedProduct.productSpecificationValue;
								product.productSpecificationUnitFirst = $scope.addedProduct.productSpecificationUnitFirst.optionName;
								product.productSpecificationUnitSecond = $scope.addedProduct.productSpecificationUnitSecond.optionName;
								product.productSpecificationUnitFirstValue = $scope.addedProduct.productSpecificationUnitFirst.optionValue;
								product.productSpecificationUnitSecondValue = $scope.addedProduct.productSpecificationUnitSecond.optionValue;
								//产品状态
								product.productStatus = $scope.addedProduct.productStatus.optionValue;
								//产品售卖单位
								product.productBuyUnitCode = $scope.addedProduct.productBuyUnit.optionValue;
								product.productBuyUnitName = $scope.addedProduct.productBuyUnit.optionName;
								//温区
								product.productTemperatureZoneCode = $scope.addedProduct.productTemperatureZone.optionValue;
								product.productTemperatureZoneName = $scope.addedProduct.productTemperatureZone.optionName;
								// 返回数据的处理---产品的属性
								var productProperties = [];
								for (var i = 0; i < $scope.productCategoryProperties.length; i++) {
									productProperty = {};
									productPropertyName = $scope.productCategoryProperties[i].categoryPropertyName;
									productPropertyValue = $scope.addedProduct.productProperty[i].categoryPropertyValue;
									productProperty.productPropertyName = productPropertyName;
									productProperty.productPropertyValue = productPropertyValue;
									productProperty.productCode = $scope.addedProduct.productCode;
									productProperties.push(productProperty);
								}
								// 返回数据的处理---产品的自定义属性
								product.productProperties = productProperties;

								//头部图片信息
						    	var index = $rootScope.thumbHeader.length;
						    	product.productPictures = [];
								for(var image in $rootScope.thumbHeader){
									if(product.productPictures[image] == undefined || product.productPictures[image] == null)
										product.productPictures.push({pictureAddress : $rootScope.thumbHeader[image].pictureAddress,pictureShowPosition:0,pictureShowOrder:image});
									else{
										product.productPictures[image].pictureAddress = $rootScope.thumbHeader[image].pictureAddress;
										product.productPictures[image].pictureShowPosition = 0;//头部图片，0固定
										product.productPictures[image].pictureShowOrder = image;
									}
									if(data != null){
										for(var i = 0;i<data.length;i++){
											//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
											if(data[i].pictureShowPosition == 0 && image == data[i].pictureShowOrder){
												product.productPictures[image].pictureAddress = data[i].pictureAddress;
												break;
											}
										}
									}
								}
								//详情页图片信息
								for(var image in $rootScope.thumbDetail){
									if(product.productPictures[index] == undefined || product.productPictures[index] == null)
										product.productPictures.push({pictureAddress : $rootScope.thumbDetail[image].pictureAddress,pictureShowPosition:1,pictureShowOrder:image});
									else{
										product.productPictures[index].pictureAddress = $rootScope.thumbDetail[image].pictureAddress;
										product.productPictures[index].pictureShowPosition = 1;//详情图片，1固定
										product.productPictures[index].pictureShowOrder = image;
									}
									
									if(data != null){
										for(var i = 0;i<data.length;i++){
											//若页面图片位置与传回来图片位置一致，则使用传回来的图片地址
											if(data[i].pictureShowPosition == 1 && image == data[i].pictureShowOrder){
												product.productPictures[index].pictureAddress = data[i].pictureAddress;
												break;
											}
										}
									}
									index ++;
								}
								
                                //更新图片的编码
								for(var i=0; i<product.productPictures.length;i++){
									product.productPictures[i].productCode = $scope.addedProduct.productCode;
								}
								//去除头部图片的空位置
								product.productPictures = $rootScope.deletBlank(product.productPictures);
								//包装单位
								product.productPackUnit = $rootScope.productPackUnit;
								$scope.addedProduct = product;
								$scope.addedCompositeProduct.product = $scope.addedProduct;
								$scope.addedCompositeProduct.productItems = $scope.$parent.selectedProductItems;
								//新增组合产品
								$http.post("addCompositeProduct", $scope.addedCompositeProduct)
										.success(function(data) {

											$scope.$parent.doubleClick = false;
											exitPop('submitInfo');
											$scope.$parent.doubleC = 0;
											//新增完成。FLG重置
									    	if(!$scope.$parent.newEditQuit){
									    		$scope.$parent.newEditQuit = true;//放弃当前新增
									    		$scope.$parent.newEditCount = 0;//确认放弃，次数归0
									    	}
											
											$scope.$parent.compositeProduct = $scope.addedCompositeProduct;
											$scope.$parent.compositeProduct.product = data.product;
											//处理新增之后直接编辑的情况
											$rootScope.formHeader = [];//头部图片判断是否修改集合
											$rootScope.thumbHeader = [];//页面头部图片集合
											$rootScope.thumbDetail = [];//页面详细图片集合
											$rootScope.formDetail = [];//判断详细图片是否修改集合
											productPictures = $scope.compositeProduct.product.productPictures;
											if(undefined != productPictures){
												for(var i =0;i<productPictures.length;i++){
													//头部图片
													if(productPictures[i].pictureShowPosition == 0){
														$rootScope.formHeader.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
														$rootScope.thumbHeader.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
													} else {
														//详情页图片
														$rootScope.formHeader.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
														$rootScope.thumbDetail.push({pictureAddress: productPictures[i].pictureAddress, changeFlag: false});
													}
												}
											}
					                        $scope.$parent.addCProduct = false;//普通产品新增页面不显示
					                        $scope.$parent.showCproduct = true;//组合产品新增页面不显示
											//清空查询数据
											$scope.$parent.firstLevelCategories = {};
											$scope.$parent.secondLevelCategories = {};
											$scope.$parent.thirdLevelCategories = {};
											$scope.selectByPage($scope.pagination.pageNum,$scope.pagination.pageSize,$scope.categoryCode,null,$scope.productProperty,null,$scope.compositeFlag);
										});
							};
							
							//组合产品
							$scope.onAddSaveClick = function() {
								
								//防止表单重复提交
								$scope.$parent.doubleC = $scope.$parent.doubleC + 1;
								if($scope.$parent.doubleC > 0){
									$scope.$parent.doubleClick = true;
									$rootScope.message = "正在提交数据,请稍后,成功后自动关闭！";
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
								//图片未修改提交数据
								var ib = 0;$scope.pic = [];
								if(ic == 0){
									$scope.cImageUp($scope.pic);
									return;
								}
								var imageSize = 0;
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
										url : 'imageUpload',
										data : data,
										headers : {
											'Content-Type' : undefined
										},
										transformRequest : angular.identity
									}).success(function(newdata) {
										$scope.pic[ib] = newdata[0];
										ib ++;
										if(ib == ic)
											$scope.cImageUp($scope.pic);//图片服务器上传成功后提交数据
									});
								}
							};
						});