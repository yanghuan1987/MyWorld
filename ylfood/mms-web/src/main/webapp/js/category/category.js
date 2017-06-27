var app = angular.module('categoryApp', []);
app.controller('categoryCtrl', function($scope,$http) {
	//不能编辑
 	$scope.notEditAble = true;//显示当前页面品类-新增1级时
 	$scope.notEditProAble = true;//新增时显示必填提示符
 	$scope.showParentName = true;//显示上级品类标题
	$scope.newEditQuit = true;//新增页面是否放弃当前编辑FLG
	$scope.newEditCount = 0;//新增页面是否放弃当前编辑FLG
	$scope.oldEditQuit = true;//编辑页面是否放弃当前编辑FLG
 	$scope.colorone = "";//菜单背景颜色
 	$scope.showLevelOneAndTwo = false;//用于显示一级和二级品类详情
 	$scope.showLevelThree = false;//用于显示三级品类详情
 	$scope.notEditAbleThree = false;//用于防止三级品类多次提交
 	$scope.categoryBGC = "#99C731";
 	//获取一级品类列表
	$http({
        method:"GET",
        url:"GetCatagoryList"
    }).success(function(data) {
    	$scope.categoryList = data;
    }).error(function() {
		$scope.errorMessage = "获取数据失败";
		showPopup('popDialogue',true);
    });
	var serverPath = document.getElementById('server').value+"/uias-web/login/loginOut";
	//注销
	$scope.loginOut = function() {
		window.location.href = serverPath;
    };
    //点击编辑
    $scope.editAll = function(intn) {
    	$scope.buttonType = "1";//0：新增按钮点击（不显示），1：编辑按钮（显示），用于判断返回按钮是否显示
    	//三级目录编辑时
    	if(intn == 3){
        	if($scope.oldEditQuit){
        		$scope.oldEditQuit = false;//是否放弃当前编辑FLG
        	 	$scope.notEditAbleThree = false;//用于防止三级品类多次提交完成按钮distable
        	}
    	}
    	$scope.notEditAble = false;
    }
    
    $scope.returnCategoryForm = function(intn){
    	//三级目录编辑时
    	if(intn == 3){
    		//离开新增三级品类页面提示
        	if(!$scope.newEditQuit){
        		var con = confirm("您当前正处于新增状态，请确认是否放弃");
    	    	if(!con){
    	    		return false;
    	    	}else{
    	    		$scope.newEditQuit = true;//放弃当前编辑	
    	    		$scope.newEditCount = 0;//确认放弃，次数归0
    	    	}
        	}
        	//离开编辑三级品类页面提示
        	if(!$scope.oldEditQuit){
        		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
    	    	if(!con){
    	    		return false;
    	    	}else{
    	    		$scope.oldEditQuit = true;//放弃当前编辑	
    	    	}
        	}
        	if($scope.oldEditQuit){
        		$scope.oldEditQuit = true;//是否放弃当前编辑FLG
        	 	$scope.notEditAbleThree = true;//用于防止三级品类多次提交完成按钮distable
        	}
    	}
    	$scope.notEditAble = true;
    }
    
    //客户类型基础数据列表
	$http.get("getDisableCategoryPropertyName").
    success(function(data, status, headers, config) {
    	$scope.DisableCategoryPropertyName = data;
    });
	//新增1级品类
	$scope.addOne = function() {
		//离开新增三级品类页面提示
    	if(!$scope.newEditQuit){
    		var con = confirm("您当前正处于新增状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.newEditQuit = true;//放弃当前编辑	
	    		$scope.newEditCount = 0;//确认放弃，次数归0
	    	}
    	}
    	//离开编辑三级品类页面提示
    	if(!$scope.oldEditQuit){
    		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.oldEditQuit = true;//放弃当前编辑	
	    	}
    	}
    	$scope.buttonType = "0";//0：新增按钮点击（不显示），1：编辑按钮（显示），用于判断返回按钮是否显示
		$scope.showParentName = false;//不显示上级品类标题
		$scope.notEditAble = false;
		$scope.categoryNameIsHave = false;//开始时隐藏错误信息
		$scope.categoryNameSize = false;//开始时隐藏错误信息
		$scope.addOneCategory ="一级";
		$scope.categoryCname = '新建一级分类';
		//控制一二级品类查看页面新增按钮的按钮值
		$scope.categoryEditMessage = "";
		//控制一二级品类查看页面是否显示新增按钮,新增一级品类界面不显示新增按钮
    	$scope.checkCategoryEditMessage = false;
		$scope.category = {};
		$scope.addOrUpdate = "新增";
	 	$scope.showLevelOneAndTwo = true;//用于显示一级和二级品类详情
	 	$scope.showLevelThree = false;//用于显示三级品类详情
    };
  //新增2级品类
    $scope.addTwo = function(catOne) {
    	//离开新增三级品类页面提示
    	if(!$scope.newEditQuit){
    		var con = confirm("您当前正处于新增状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.newEditQuit = true;//放弃当前编辑	
	    		$scope.newEditCount = 0;//确认放弃，次数归0
	    	}
    	}
    	//离开编辑三级品类页面提示
    	if(!$scope.oldEditQuit){
    		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.oldEditQuit = true;//放弃当前编辑	
	    	}
    	}
    	$scope.buttonType = "0";//0：新增按钮点击（不显示），1：编辑按钮（显示），用于判断返回按钮是否显示
    	if(catOne == undefined || catOne == null)
    		catOne = $scope.categoryT;
    	//控制一二三级页面是否处于编辑状态
    	$scope.showParentName = true;//显示上级品类标题
    	$scope.notEditAble = false;
		$scope.categoryNameSize = false;//开始时隐藏错误信息
    	$scope.categoryNameIsHave = false;//开始时隐藏错误信息
    	$scope.category = {};
    	$scope.addOneCategory ="二级";
    	$scope.pcategoryName = catOne.categoryName;
    	$scope.categoryCname = '新建二级分类';
    	$scope.Twocategory = catOne;
    	//控制一二级品类查看页面新增按钮的按钮值
    	$scope.categoryEditMessage = "";
    	//控制一二级品类查看页面是否显示新增按钮,新增二级品类界面不显示新增按钮
    	$scope.checkCategoryEditMessage = false;
    	
    	var cName = '';
    	if(catOne.categoryName != undefined)
    		cName = catOne.categoryName;
    	var cCode = catOne.categoryCode;
    	var p = {categoryName:cName,categoryCode:cCode};
    	$scope.category = {parentCategory:p,categoryTypeFlag:catOne.categoryTypeFlag};
    	$scope.addOrUpdate = "新增";
	 	$scope.showLevelOneAndTwo = true;//用于显示一级和二级品类详情
	 	$scope.showLevelThree = false;//用于显示三级品类详情
    };
    //进入创建三级品类编辑界面
    $scope.addThree = function(cat1name,cat2name,catThree) {
    	//离开编辑三级品类页面提示
    	if(!$scope.oldEditQuit){
    		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.oldEditQuit = true;//放弃当前编辑	
	    	}
    	}
    	$scope.newEditCount = $scope.newEditCount + 1;
		//第二次点击新增
		if($scope.newEditCount > 1){
    		$scope.errorMessage = "您已在新增画面";
    		//模态框隐藏确认按钮
			showPopup('popDialogue',true);
			return false;
		}
    	if($scope.newEditQuit){
    		$scope.newEditQuit = false;//是否放弃当前编辑FLG
    	}
    	$scope.buttonType = "0";//0：新增按钮点击（不显示），1：编辑按钮（显示），用于判断返回按钮是否显示
    	if(catThree == undefined || catThree == null)
    		catThree = $scope.categoryT;

     	$scope.notEditAbleThree = false;//用于防止三级品类多次提交
    	//控制一二三级页面是否处于编辑状态
    	$scope.notEditAble = false;
		$scope.categoryNameSize = false;//开始时隐藏错误信息
    	$scope.categoryNameIsHave = false;//开始时隐藏错误信息
    	$scope.showParentName = true;//显示上级品类标题
    	//给三级品类赋值
    	$scope.Threecategory = catThree;
    	//给三级品类所对应的父品类赋值
    	$scope.pcategoryName = cat1name;
    	$scope.pcategory2Name = cat2name;
    	$scope.categoryCname = '新增三级分类';
    	
    	var cName = '';
    	if(catThree.categoryName != undefined)
    		cName = catThree.categoryName;
    	var cCode = catThree.categoryCode;
    	var p = {categoryName:cName,categoryCode:cCode};
    	$scope.category = {parentCategory:p,categoryTypeFlag:catThree.categoryTypeFlag};
    	
    	//控制一二级品类查看页面新增按钮的按钮值
    	$scope.categoryEditMessage = "";
    	//控制一二级品类查看页面是否显示新增按钮,新增三级品类界面不显示新增按钮
    	$scope.checkCategoryEditMessage = false;
	 	$scope.showLevelOneAndTwo = false;//用于显示一级和二级品类详情
	 	$scope.showLevelThree = true;//用于显示三级品类详情
    };
    //显示二级品类
    $scope.updateOne = function(updatecatone,search) {
    	//离开新增三级品类页面提示
    	if(!$scope.newEditQuit){
    		var con = confirm("您当前正处于新增状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.newEditQuit = true;//放弃当前编辑	
	    		$scope.newEditCount = 0;//确认放弃，次数归0
	    	}
    	}
    	//离开编辑三级品类页面提示
    	if(!$scope.oldEditQuit){
    		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.oldEditQuit = true;//放弃当前编辑	
	    	}
    	}
    	//点击一级品类处理
    	if(search == undefined){
	    	var response = $http.post("selectCategoryChildren?categoryCode="+updatecatone.categoryCode,{headers:{'Accept':'application/json'}});
			response.success(function(data, status, headers, config) {
				updatecatone.productCategorys = data;
			});
			//控制一二三级页面是否处于编辑状态
			$scope.notEditAble = true;
			$scope.categoryNameSize = false;//开始时隐藏错误信息
			$scope.categoryNameIsHave = false;//开始时隐藏错误信息
			$scope.showParentName = false;//不显示上级品类标题
			$scope.addOneCategory ="一级";
			$scope.colorone = "#99C731";//菜单背景颜色
			$scope.categoryCname = updatecatone.categoryName;
			$scope.categoryOneName = updatecatone.categoryName;
			$scope.category = {categoryName:updatecatone.categoryName,categoryCode:updatecatone.categoryCode,id:updatecatone.id};
			//赋予一个完整对象，用于二级品类添加后局部刷新
			$scope.categoryT = updatecatone;
			//控制一二级品类查看页面新增按钮的按钮值
			$scope.categoryEditMessage = "新增二级品类";
			$scope.clickedit = "addTwo()";
			//控制一二级品类查看页面是否显示新增按钮,进入查看一级品类界面时需要显示新增二级品类按钮
			$scope.checkCategoryEditMessage = true;
			$scope.addOrUpdate = "修改";
		 	$scope.showLevelOneAndTwo = true;//用于显示一级和二级品类详情
		 	$scope.showLevelThree = false;//用于显示三级品类详情
			if($scope.spMenuShow != updatecatone.id)
				$scope.spMenuShow = updatecatone.id;
			else
				$scope.spMenuShow = undefined;
    	}else{//更新二级品类后处理
    		//控制一二三级页面是否处于编辑状态
        	$scope.notEditAble = true;
    		$scope.categoryNameSize = false;//开始时隐藏错误信息
        	$scope.categoryNameIsHave = false;//开始时隐藏错误信息
        	$scope.showParentName = true;//显示上级品类标题
       	 	$scope.addOneCategory ="二级";
       	 	$scope.pcategory2Name ="";
        	//传递需要修改的信息
        	$scope.category = {categoryName:updatecatone.categoryName,categoryCode:updatecatone.categoryCode,id:updatecatone.id};
        	//赋予一个新的对象，用于三级品类修改
        	$scope.categoryT = updatecatone;
        	$scope.categoryCname = updatecatone.categoryName;
        	$scope.pcategoryName = $scope.categoryOneName;
        	//控制一二级品类查看页面新增按钮的按钮值
        	$scope.categoryEditMessage = "新增三级品类";
        	$scope.addOrUpdate = "修改";
    	 	$scope.showLevelOneAndTwo = true;//用于显示一级和二级品类详情
    	 	$scope.showLevelThree = false;//用于显示三级品类详情
     		if($scope.spMenuShowT != updatecatone.id)
     			$scope.spMenuShowT = updatecatone.id;
     		else
     			$scope.spMenuShowT = undefined;
    	}
    };
    //显示三级品类
    $scope.updateTwo = function(cat1name,updatecattwo) {
    	if(!$scope.newEditQuit){
    		var con = confirm("您当前正处于新增状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.newEditQuit = true;//放弃当前编辑	
	    		$scope.newEditCount = 0;//确认放弃，次数归0
	    	}
    	}
    	
    	if(!$scope.oldEditQuit){
    		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.oldEditQuit = true;//放弃当前编辑	
	    	}
    	}
    	//获取子品类菜单
    	var response = $http.post("selectCategoryChildren?categoryCode="+updatecattwo.categoryCode,{headers: { 'Content-Type': 'application/json'}});
		response.success(function(data, status, headers, config) {
			updatecattwo.productCategorys = data;
		});
    	//控制一二三级页面是否处于编辑状态
    	$scope.notEditAble = true;
		$scope.categoryNameSize = false;//开始时隐藏错误信息
    	$scope.categoryNameIsHave = false;//开始时隐藏错误信息
    	$scope.showParentName = true;//显示上级品类标题
   	 	$scope.addOneCategory ="二级";
   	 	$scope.pcategory2Name ="";
    	//传递需要修改的信息
    	$scope.category = {categoryName:updatecattwo.categoryName,categoryCode:updatecattwo.categoryCode,id:updatecattwo.id};
    	//赋予一个新的对象，用于三级品类修改
    	$scope.categoryT = updatecattwo;
    	$scope.categoryCname = updatecattwo.categoryName;
    	$scope.pcategoryName = cat1name;
    	//控制一二级品类查看页面新增按钮的按钮值
    	$scope.categoryEditMessage = "新增三级品类";
    	$scope.addOrUpdate = "修改";
	 	$scope.showLevelOneAndTwo = true;//用于显示一级和二级品类详情
	 	$scope.showLevelThree = false;//用于显示三级品类详情
 		if($scope.spMenuShowT != updatecattwo.id)
 			$scope.spMenuShowT = updatecattwo.id;
 		else
 			$scope.spMenuShowT = undefined;
    };
    //显示三级品类详细信息
    $scope.updateThree = function(cat2name,id,name,ccode,showlevel,corder) {
    	if(!$scope.newEditQuit){
    		var con = confirm("您当前正处于新增状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.newEditQuit = true;//放弃当前编辑	
	    		$scope.newEditCount = 0;//确认放弃，次数归0
	    	}
    	}
    	
    	if(!$scope.oldEditQuit){
    		var con = confirm("您当前正处于编辑状态，请确认是否放弃");
	    	if(!con){
	    		return false;
	    	}else{
	    		$scope.oldEditQuit = true;//放弃当前编辑	
	    	}
    	}
    	//控制一二三级页面是否处于编辑状态
    	$scope.notEditAble = true;
		$scope.categoryNameSize = false;//开始时隐藏错误信息
    	$scope.categoryNameIsHave = false;//开始时隐藏错误信息
    	$scope.showParentName = true;//显示上级品类标题
    	$scope.categoryEditMessage = "";

    	$scope.pcategory2Name = cat2name;
    	$scope.category = {id:id, 
    						categoryName:name,
    						categoryCode:ccode};
    	//面包屑导航
    	$scope.categoryCname = name;
 		var response = $http.post("inshowThreeCatagory", $scope.category);
			response.success(function(data, status, headers, config) {
				$scope.category = data;
			});
	 	$scope.showLevelOneAndTwo = false;//用于显示一级和二级品类详情
	 	$scope.showLevelThree = true;//用于显示三级品类详情
    };
    //改变品类名称时
    $scope.checkCategoryNameChange = function(){
		$scope.categoryNameIsHave = false;//内容变动时隐藏错误信息
		$scope.categoryNameSize = false;//内容变动时隐藏错误信息
    };
    //检查品类名称
    $scope.checkCategoryName = function(parm){
    	var limitSize = 128;
    	if(parm == 'Five'){
    		limitSize = 5;
    	}
    	
    	if($scope.category.categoryName.length >= limitSize){
    		$scope.categoryNameSize = true;
    	}else{
    		$scope.categoryNameSize = false;
    	}
    	if($scope.category.categoryName != null && $scope.category.categoryName != undefined && $scope.category.categoryName != ''){
    		//名字重复check
    		var response = $http.post("checkProductCategoryName",$scope.category);
    		response.success(function(data){
    			//data : true/false
    			if(data){//名称已存在，重复
    				$scope.categoryNameIsHave = true;
    			}else{//名称未重复
    				$scope.categoryNameIsHave = false;
    			}
    		});
    	}else{//名称未重复
			$scope.categoryNameIsHave = false;
    	}
    };
    //编码重复check
    $scope.checkCategoryCode = function(){
    	if($scope.category.categoryCode != null && $scope.category.categoryCode != undefined && $scope.category.categoryCode != ''){
    		
    		var response = $http.post("checkProductCategoryCode",$scope.category);
    		response.success(function(data){
    			//data : true/false
    			if(data){//名称已存在，重复
    				$scope.categoryInputCodeCheck = "编码已存在，请重新输入";
    				$scope.categoryCodeIsHave = true;
    			}else{//名称未重复
    				$scope.categoryCodeIsHave = false;
    				$scope.categoryInputCodeCheck = "";
    			}
    		});
    	}
    };
});
//品类一、二级页面--------------------------------------------start----
	 app.controller('categoryOneInfo', function($scope,$http) {
		 //提交按钮
		$scope.submitCategoryForm= function(){
			if($scope.category.categoryName == undefined || $scope.category.categoryName.length == 0){
				$scope.$parent.errorMessage = "名称不能为空";
				showPopup('popDialogue',true);
				return false;
			}
			if($scope.category.categoryName != undefined){
				if($scope.category.categoryName.length > 5){
					$scope.$parent.errorMessage = "长度不能超过5个字符";
					showPopup('popDialogue',true);
					return false;
				}
			}
			if($scope.categoryNameIsHave){
				$scope.$parent.errorMessage = "名称已存在";
				showPopup('popDialogue',true);
				return false;
			}
			if($scope.categoryCodeIsHave){
				$scope.$parent.errorMessage = "编码已存在";
				showPopup('popDialogue',true);
				return false;
			}
			$scope.$parent.notEditAble = true;
		    var urls;
		    if($scope.category.id == undefined)
		    	urls = "AddCategory";
		    else
		    	urls = "UpdateCategory";
				var response = $http.post(urls, $scope.category);
				response.success(function(data, status, headers, config) {
					//将传回来的品类id赋值给当前需要添加到左侧栏中的对象
					$scope.category.id = data.id;
					if(data.categoryCode != undefined && data.categoryCode != '')
						$scope.category.categoryCode = data.categoryCode;
					//变量c用于接收品类的父id
					var c ='';
					//判断品类是否有父类
					if(urls == 'AddCategory'){
						if($scope.category.parentCategory != null 
							&& $scope.category.parentCategory != undefined
							&& $scope.category.parentCategory.categoryCode != null
							&& $scope.category.parentCategory.categoryCode != undefined
							&& $scope.category.parentCategory.categoryCode != ''){
							c = $scope.category.parentCategory.categoryCode;
							if($scope.Twocategory.productCategorys == null || $scope.Twocategory.productCategorys == undefined){
								$scope.Twocategory.productCategorys = [$scope.category];
							}else{
								//将成功编辑的品类更新到左侧列表栏
								$scope.Twocategory.productCategorys.push($scope.category);
							}
						}else{
							$scope.categoryList.push($scope.category);
						}
					}else{
						bc:
						for(var i=0;i<$scope.categoryList.length;i++){
							if($scope.categoryList[i].id == data.id){
								$scope.categoryList[i].categoryName = $scope.category.categoryName;
								$scope.categoryList[i].categoryCode = $scope.category.categoryCode;
								break bc;
							}
							if($scope.categoryList[i].productCategorys != undefined)
							for(var j=0;j<$scope.categoryList[i].productCategorys.length;j++){
								if($scope.categoryList[i].productCategorys[j].id == data.id){
									$scope.categoryList[i].productCategorys[j].categoryName = $scope.category.categoryName;
									$scope.categoryList[i].productCategorys[j].categoryCode = $scope.category.categoryCode;
									break bc;
								}
							}
						}
					}
					//操作成功后进入查看这个品类界面
					if(c != ''){
						$scope.updateTwo($scope.pcategoryName,data);
					}
					else{
						//当提交目标是一级品类时
						if(data.categoryCode.length == 2){
							$scope.updateOne(data);
						}else{//当提交目标是二级品类时
							$scope.updateOne(data,'search');
						}
					}
					$scope.category.parentCategory = {categoryCode:c};
				});
	   };
	}); 
//品类一、二级页面--------------------------------------------end----
//品类三级页面----------------------------------------------start---
 app.controller('categoryThreeInfo', function($scope,$http) {
	//监听 
	$scope.$watch('categoryCname',  function(newValue, oldValue) {
		if (newValue === oldValue) { return; } // AKA first run
        $scope.propert = {};
    });
	 
 		//三级品类属性界面编辑
 		$scope.properAdd = function(){
 			
 			//属性名称不能为空
 			if($scope.threePropertyForm.pname.$error.required){
 				$scope.$parent.errorMessage = "属性名称不能为空";
 				showPopup('popDialogue',true);
 				return false;
 			}
 			//属性名称不能超过128字符
 			if($scope.threePropertyForm.pname.$error.maxlength){
 				$scope.$parent.errorMessage = "属性名称不能超过128个字符";
 				showPopup('popDialogue',true);
 				return false;
 			}
 			//属性名称不能为禁用字眼
 			for(var i in $scope.DisableCategoryPropertyName){
 				if($scope.propert.categoryPropertyName == $scope.DisableCategoryPropertyName[i].optionName){
 					$scope.$parent.errorMessage = "属性名称不能为【"+$scope.DisableCategoryPropertyName[i].optionName+"】,请修改";
 	 				showPopup('popDialogue',true);
 	 				return false;
 	 			}
 			}
 			//属性描述长度不能超过128个字符
 			if($scope.threePropertyForm.pdesc.$error.maxlength){
 				$scope.$parent.errorMessage = "属性描述长度不能超过128个字符";
 				showPopup('popDialogue',true);
 				return false;
 			}
 			//属性值长度不能超过128个字符
 			if($scope.threePropertyForm.cpvalues.$error.maxlength){
 				$scope.$parent.errorMessage = "属性值长度不能超过128个字符";
 				showPopup('popDialogue',true);
 				return false;
 			}
 			//筛选状态
 			if($scope.propert.selectFlag == undefined){
 				$scope.$parent.errorMessage = "请选择属性筛选状态";
 				showPopup('popDialogue',true);
 				return false;
 			}
 			//当属性值输入框中有值时，自动将该值创建成属性的属性值
 			if(!$scope.threePropertyForm.cpvalues.$error.required){
 				$scope.properAdds();
 			}else{//若输入框没值，又没有属性值在下方，则提示必须要有一个属性值
 				if($scope.propert.productCategoryPropertyValues == undefined || $scope.propert.productCategoryPropertyValues.length == 0){
 	 				$scope.$parent.errorMessage = "属性至少要求有一个属性值";
 	 				showPopup('popDialogue',true);
 	 				return false;
 	 			}
 			}
 			if($scope.category.productCategoryPropertys == undefined){
 				$scope.category.productCategoryPropertys = [$scope.propert];
 			}else{
 				//判断编辑栏的属性是新增的还是修改原来的，initadd=1代表是新增
 				if($scope.initadd != undefined && $scope.initadd == 1){
 	 				for(var i=0;i<$scope.category.productCategoryPropertys.length;i++){
 	 					if($scope.category.productCategoryPropertys[i].categoryPropertyName == $scope.propert.categoryPropertyName){
 	 						//属性已存在
 	 						$scope.$parent.errorMessage = "属性已存在";
 	 	 					showPopup('popDialogue',true);
 	 	 					return false;
 	 					}
 	 				}
 	 				$scope.category.productCategoryPropertys.unshift($scope.propert);
 	 			}else{
 	 				//修改前校验
 	 				for(var i=0;i<$scope.category.productCategoryPropertys.length;i++){
 	 					if($scope.category.productCategoryPropertys[i].categoryPropertyName == $scope.propert.categoryPropertyName
 	 							&& $scope.category.productCategoryPropertys[i] != $scope.tempPropert){
 	 						//属性已存在
 	 						$scope.$parent.errorMessage = "属性已存在";
 	 	 					showPopup('popDialogue',true);
 	 	 					return false;
 	 					}
 	 				}
 	 				//修改
 	 				for(var i=0;i<$scope.category.productCategoryPropertys.length;i++){
 	 					if($scope.category.productCategoryPropertys[i] == $scope.tempPropert){
 	 						$scope.category.productCategoryPropertys[i] = {categoryPropertyName:$scope.propert.categoryPropertyName,
 	 																		selectFlag:$scope.propert.selectFlag,
 	 																		categoryPropertyDescription:$scope.propert.categoryPropertyDescription,
 	 											 							productCategoryPropertyValues:$scope.propert.productCategoryPropertyValues}
 	 	 					break;
 	 					}
 	 				}
 	 				$scope.tempPropert = {};
 	 			}
 			}
 			exitPop('propertypopUpHg');
 			$scope.initadd = 1;
 			$scope.propert = {};
 			return true;
 		};
 		//三级品类属性值界面编辑添加按钮
 		$scope.properAdds = function(){
 			if($scope.newvalues == undefined || $scope.newvalues.length == 0){
 				//不能为空
					$scope.$parent.errorMessage = "属性值不能为空";
					showPopup('popDialogue',true);
					return;
 			}
 			if($scope.propert.productCategoryPropertyValues == undefined){
 				var cc = [{categoryPropertyValue:$scope.newvalues}];
 				$scope.propert.productCategoryPropertyValues = cc;
 			}else{
 				if(($scope.valueIndex != undefined && $scope.valueIndex != "") || $scope.valueIndex == 0){
 					//valueIndex 值存在，表明当前属于属性值的修改
 					$scope.propert.productCategoryPropertyValues[$scope.valueIndex].categoryPropertyValue = $scope.newvalues;
 					$scope.valueIndex = undefined;
 				}else{
 					for(var i=0;i<$scope.propert.productCategoryPropertyValues.length;i++){
 	 	 				if($scope.propert.productCategoryPropertyValues[i].categoryPropertyValue == $scope.newvalues){
 	 	 					//名称已存在，不能重复
 	 	 					$scope.$parent.errorMessage = "属性值已存在";
 	 	 					showPopup('popDialogue',true);
 	 	 					return;
 	 	 				}
 	 	 			}
 	 				$scope.propert.productCategoryPropertyValues.push({categoryPropertyValue:$scope.newvalues});
 				}
 			}
 			$scope.newvalues = '';
 		};
 		//确认删除属性
 		$scope.updateAttr = function(){

 			var valued = $scope.scpvaluec;
 			for(var i=0;i<$scope.category.productCategoryPropertys.length;i++){
				if($scope.category.productCategoryPropertys[i].categoryPropertyName == valued.categoryPropertyName){
					//删除
					$scope.category.productCategoryPropertys.splice(i,1);
 					break;
				}
			}

		    $scope.attrSure = false;
		    exitPop('popDialoguethree');
 		}
 		//删除属性
 		$scope.proAdds = function(valuec){

 			if(valuec != null && valuec.length !=0){
 				$scope.scpvaluec = valuec;
 		    	$scope.attrSure = true;
 				$scope.$parent.errorMessage = "是否删除该属性";
 				showPopup('popDialoguethree',true);
 				return false;
 			}
 		};
 		//商品品类属性的属性值
 		$scope.properMove = function(pvalue){
 			for(var i=0;i<$scope.propert.productCategoryPropertyValues.length;i++){
 				if($scope.propert.productCategoryPropertyValues[i].categoryPropertyValue == pvalue){
 					$scope.propert.productCategoryPropertyValues.splice(i,1);
 					break;
 				}
 			}
 		};
 		//编辑品类属性值
 		$scope.editPropertyValue = function(value,index){
 			$scope.newvalues = value;
 			$scope.valueIndex = index;
 		}
 		//判断该属性是新增还是修改
 		$scope.proclick = function(valuec){
 			
 			
 			//弹出编辑框
 			$scope.editProperty = true;
 			//设置弹出框
 			showPopup('propertypopUpHg',true);
 			//新增
 			if(valuec == null){
 				
 	 			//不可编辑FLG，新增状态下属性名和属性值能修改
 	 			$scope.disEdit = false;
 				$scope.initadd = 1;
 				$scope.propert = {categoryPropertyName:null,
							selectFlag:1,
							categoryPropertyDescription:null,
							productCategoryPropertyValues:null};
 			}else{

 	 			//不可编辑FLG，编辑状态下属性名和属性值不能修改
 	 			$scope.disEdit = true;
 				$scope.initadd = 0;
 				var temp = [];
 				if(valuec.productCategoryPropertyValues != undefined)
 				for(var i=0;i<valuec.productCategoryPropertyValues.length;i++){
 					temp.push({categoryPropertyValue:valuec.productCategoryPropertyValues[i].categoryPropertyValue});
 				}
 				$scope.propert = {categoryPropertyName:valuec.categoryPropertyName,
							selectFlag:valuec.selectFlag,
							categoryPropertyDescription:valuec.categoryPropertyDescription,
							productCategoryPropertyValues:temp};
 			}
 			$scope.tempPropert = valuec;
 		};
		//三级品类编辑提交
		$scope.submitCategoryThreeForm= function(){
			//品类名称不能为空
 			if($scope.category.categoryName == undefined || $scope.category.categoryName.length == 0){
 				$scope.$parent.errorMessage = "品类名称不能为空";
 				showPopup('popDialogue',true);
 				return;
 			}
 			//品类名称长度不能超过128个字符
 			if($scope.category.categoryName != undefined ){
 	 			if($scope.category.categoryName.length > 128){
 	 				$scope.$parent.errorMessage = "品类名称不能超过128个字符";
 	 				showPopup('popDialogue',true);
 	 				return;
 	 			}
 			}
 			//品类名称长度不能超过128个字符
 			if($scope.categoryNameIsHave){
 				$scope.$parent.errorMessage = "品类名称已存在";
 				showPopup('popDialogue',true);
 				return;
 			}
 			//品类名称别名长度不能超过128个字符
 			if($scope.category.categoryAnotherName != undefined && $scope.category.categoryAnotherName.length > 128){
 				$scope.$parent.errorMessage = "品类名称别名长度不能超过128个字符";
 				showPopup('popDialogue',true);
 				return;
 			}
 			//品类编码已存在
 			if($scope.categoryCodeIsHave){
 				$scope.$parent.errorMessage = "品类编码已存在";
 				showPopup('popDialogue',true);
 				return;
 			}

			//三级品类至少需要有一个属性
 			if($scope.category.productCategoryPropertys == undefined || $scope.category.productCategoryPropertys.length == 0){
 				$scope.$parent.errorMessage = "三级品类至少需要有一个属性";
 				showPopup('popDialogue',true);
 				return;
 			}
			

	 	$scope.$parent.notEditAbleThree = true;//用于防止三级品类多次提交
	    var urls;
	    if($scope.category.id == undefined)
	    	urls = "AddCategoryProperty";//添加三级品类
	    else
	    	urls = "UpdateCategoryProperty";//修改三级品类
	    
			var productURL = urls;
			var tempCategory = $scope.category;
			var response = $http.post(productURL, $scope.category);
			response.success(function(data, status, headers, config) {
				$scope.category.id = data;
				$scope.propert = {};
				//新增的三级品类在左侧改级下添加--添加在最上面
				if(urls == 'AddCategoryProperty'){
					//新增完成。FLG重置
			    	if(!$scope.$parent.newEditQuit){
			    		$scope.$parent.newEditQuit = true;//放弃当前新增
			    		$scope.$parent.newEditCount = 0;//确认放弃，次数归0
			    	}
					if($scope.Threecategory.productCategorys == null || $scope.Threecategory.productCategorys == undefined){
						$scope.Threecategory.productCategorys = [$scope.category];
					}else
						$scope.Threecategory.productCategorys.unshift($scope.category);
					var c ='';
					if($scope.category.parentCategory != null && $scope.category.parentCategory != undefined)
						c = $scope.category.parentCategory.categoryCode;
					$scope.category.parentCategory = {categoryCode:c};
				}else{
					$scope.$parent.oldEditQuit = true;//放弃当前编辑	
					//更新修改后的三级品类左侧展示
					n:
					for(var i=0;i<$scope.categoryList.length;i++){
						if($scope.categoryList[i].productCategorys != null && $scope.categoryList[i].productCategorys != undefined)
							for(var j=0;j<$scope.categoryList[i].productCategorys.length;j++){
								if($scope.categoryList[i].productCategorys[j].productCategorys != null && $scope.categoryList[i].productCategorys[j].productCategorys != undefined)
									for(var m=0;m<$scope.categoryList[i].productCategorys[j].productCategorys.length;m++){
										if($scope.categoryList[i].productCategorys[j].productCategorys[m].id == data){
											$scope.categoryList[i].productCategorys[j].productCategorys[m].categoryName = $scope.category.categoryName;
											break n;
										}
									}
							}
					}
				}
				//控制品类属性栏是编辑状态还是新增状态
				$scope.initadd = 0;
				//显示编辑后的品类
				$scope.updateThree($scope.pcategory2Name,data,tempCategory.categoryName,null,null,null);
			});
	   };
	}); 
//品类三级页面-----------------------------------------------end----
 function loadSecMenu(url) {
		window.location.href = url;
	}