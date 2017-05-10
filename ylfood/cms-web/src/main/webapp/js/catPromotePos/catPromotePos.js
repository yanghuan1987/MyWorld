//商品组版面配置
var app = angular.module('catPromotePosApp',[]);
app.controller('catPromotePosctrl',function($scope,$http){
	//将广告词和组图名称中的<替换成&lt;
	function replaceToTag(){
		for(var i=0;i<$scope.catPromotePoslist.length;i++){
			$scope.catPromotePoslist[i].advert=$scope.catPromotePoslist[i].advert.replace(/&lt;/g,"<").replace(/&gt;/g,">");
			$scope.catPromotePoslist[i].categoryName=$scope.catPromotePoslist[i].categoryName.replace(/&lt;/g,"<").replace(/&gt;/g,">");
		}
	}
	//初始化查询条件，为0查询全部
	var catPromotePosid = "";
	var pageMain = "";
	var res = $http.post("getCatPromotePosInfo?pageState="+pageMain+"&catPromotePosid="+catPromotePosid);
	res.success(function(data,status,headers,config){
		if(data.status == 'success'){
			$scope.catPromotePoslist = data.result;
			replaceToTag();
		}else if(data.status == 'error'){
    		$scope.errorMessage = data.messages;//传递错误提示信息
    		showPopHint('catPromotePospopHint');//弹出提示框
		}
	});
	
	//新增商品组
	$scope.creatNew = function(){
		window.location.href = "showNewMain"
	}
	//刷新页面按钮
	$scope.refresh = function(){
	
		window.location.reload();
	}
	//编辑商品组
	$scope.choseEdit = function(index){
		var c = $scope.catPromotePoslist[index].id;
		window.location.href = "showEditMain?catPromotePosid="+c;
	}
	
	//删除商品组
	$scope.choseDelete = function(index){
		$scope.deleteIndex = index;
		showPopup('popDialogue',true);
	}
	
	$scope.deleteYes = function(){
		//删除数据
		var res = $http.post("deleteCatpromotePos",$scope.catPromotePoslist[$scope.deleteIndex]);
		res.success(function(data,status,headers,config){
			if(data.status == 'success'){
				$scope.catPromotePoslist.splice($scope.deleteIndex,1);
				exitPop('popDialogue');
			}else if(data.status == 'error'){
				exitPop('popDialogue');
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('catPromotePospopHint');//弹出提示框
			}
		}).error(function(){
			
		});
	}
});

//商品组新增
var app = angular.module('catPromotePosNewApp',['dPagination']);
app.controller('catPromotePosNewctrl',function($scope,$http){
	//将广告词和组图名称中的<替换成&lt;
	function replaceTag( ){
		$scope.catPromotePoslist.categoryName=$scope.catPromotePoslist.categoryName.replace(/</g,"&lt;").replace(/>/g,"&gt;");
		$scope.catPromotePoslist.advert=$scope.catPromotePoslist.advert.replace(/</g,"&lt;").replace(/>/g,"&gt;");
	}
	//将广告词和组图名称中的&lt;替换成<
	function replaceToTag( ){
		$scope.catPromotePoslist.categoryName=$scope.catPromotePoslist.categoryName.replace(/&lt;/g,"<").replace(/&gt;/g,">");
		$scope.catPromotePoslist.advert=$scope.catPromotePoslist.advert.replace(/&lt;/g,"<").replace(/&gt;/g,">");
	}
	//初始化分页条件
	$scope.picChange = false;//标记是否发生更新
	$scope.pageNum = 1;//页码
	$scope.total = 1;//总记录数
	$scope.pageSize = 5;//每页记录数

	//分页条件赋值
	$scope.updatePagination = function(){
		$scope.pagination.total = $scope.total; //更新表格行数
		$scope.pagination.pageSize = $scope.pageSize; //更新表格行数
		$scope.pagination.pageNum = $scope.pageNum; //更新页码
		var commodityinfo = {category:$scope.categoryCode,commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
		$scope.serachPage($scope.pagination.pageNum,$scope.pagination.pageSize,commodityinfo);
	}
	//封装分页对象
	$scope.pagination = {
        pageNum: $scope.pageNum,
        pageSize: $scope.pageSize,
        total: $scope.total,
        onChange: function(pageNum){
			if($scope.initd == undefined)
				return;
			var commodityinfo = {category:$scope.categoryCode,commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
			$scope.serachPage($scope.pagination.pageNum,$scope.pagination.pageSize,commodityinfo);
        }
    };
	//翻页控件使用的检索方法
	$scope.serachPage = function(pageNumber,pageSize,bean){
		var res = $http.post("getCommodityInfo?&pageNum="+pageNumber
				+"&pageSize="+pageSize,bean);
		res.success(function(data, status, headers, config) {
			if(data.status == 'success'){
				$scope.commodityList = data.result.result;

				$scope.pageNum = data.result.pageNum;
				$scope.total = data.result.total;
				$scope.pageSize = data.result.pageSize;
				
				$scope.pagination.pageNum = data.result.pageNum;
				$scope.pagination.total = data.result.total;
				$scope.pagination.pageSize = data.result.pageSize;
				
				var cb = 5 ;
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;
				$scope.tempList = [cb];
				while(cb > 0){
					$scope.tempList.push([cb]);
					cb--;
				}
				$scope.tempList.pop();
			}else if(data.status == 'error'){
				$scope.commodityList = null;
				$scope.pagination.pageNum = 0;
				$scope.pagination.total = 0;
				$scope.pagination.pageSize = 0;
				$scope.pageNum = 0;
				$scope.total = 0;
				$scope.pageSize = 0;
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('catPromotePospopHint');//弹出提示框
			}
		
		}).error(function(){
			
		});;
	}
	//初始化编辑、新增页面商品数据
	$scope.catPromotedCommodityList = [
		{catPromotePosId:null,commodityCode:null,productCode:null,
			commodityName:null,commoditySpec:null,commodityImgUrl:null,displayOrder:null},
			{catPromotePosId:null,commodityCode:null,productCode:null,
				commodityName:null,commoditySpec:null,commodityImgUrl:null,displayOrder:null},
				{catPromotePosId:null,commodityCode:null,productCode:null,
					commodityName:null,commoditySpec:null,commodityImgUrl:null,displayOrder:null},
					{catPromotePosId:null,commodityCode:null,productCode:null,
						commodityName:null,commoditySpec:null,commodityImgUrl:null,displayOrder:null},
						{catPromotePosId:null,commodityCode:null,productCode:null,
							commodityName:null,commoditySpec:null,commodityImgUrl:null,displayOrder:null},
							{catPromotePosId:null,commodityCode:null,productCode:null,
								commodityName:null,commoditySpec:null,commodityImgUrl:null,displayOrder:null},
								{catPromotePosId:null,commodityCode:null,productCode:null,
									commodityName:null,commoditySpec:null,commodityImgUrl:null,displayOrder:null},
									{catPromotePosId:null,commodityCode:null,productCode:null,
										commodityName:null,commoditySpec:null,commodityImgUrl:null,displayOrder:null}];
	
	//初始化编辑、新增页面商品组和商品数据
	$scope.catPromotePoslist = {categoryName:null,imageUrl:null,advert:null,displayOrder:null,catPromotedCommodityList:$scope.catPromotedCommodityList};

	//商品弹出框
	$scope.getCommodityinfo = function(index){
		$scope.commodityCode = null;//初始化编辑、新增页面商品编号输入框内容
		$scope.commodityName = null;//初始化编辑、新增页面商品名称输入框内容
		$scope.displayOrder = null;//初始化编辑、新增页面商品排序输入框内容
		//选择以后商品code
		$scope.choseCommodityCode = null;
		//选择已有商品名称
		$scope.choseCommodityName = null;
		//选择商品信息index
		$scope.choseCommodityIndex = null;
		$scope.choseMenuIndex = index;//选择index
		//选择以后商品code
		$scope.choseCommodityCode = $scope.catPromotePoslist.catPromotedCommodityList[index].commodityCode;
		//选择已有商品名称
		$scope.choseCommodityName = $scope.catPromotePoslist.catPromotedCommodityList[index].commodityName;
		//选择已有商品名称
		$scope.displayOrder = $scope.catPromotePoslist.catPromotedCommodityList[index].displayOrder;
		$scope.categoryName = "NO";
		var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
		var res = $http.post("getCommodityInfo?pageNum=1&pageSize=5",commodityinfo);
		res.success(function(data, status, headers, config) {
			if(data.status == 'success'){
				//更新分页参数
				$scope.pageNum = data.result.pageNum;
				$scope.total = data.result.total;
				$scope.pageSize = data.result.pageSize;
				//更新分页参数
				$scope.pagination.pageNum = data.result.pageNum;
				$scope.pagination.total = data.result.total;
				$scope.pagination.pageSize = data.result.pageSize;
				//更新商品列表
				$scope.commodityList = data.result.result;
				var cb = 5 ;
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;
				$scope.tempList = [cb];
				while(cb > 0){
					$scope.tempList.push([cb]);
					cb--;
				}
				$scope.tempList.pop();
				$scope.initd = "0";
				var pop = showPopup('catPromotePospopUpHg',true);
			}else if(data.status == 'error'){
				$scope.commodityList = null;//清空商品列表
				//清空分页信息
				$scope.pagination.pageNum = 0;
				$scope.pagination.total = 0;
				$scope.pagination.pageSize = 0;
				$scope.pageNum = 0;
				$scope.total = 0;
				$scope.pageSize = 0;
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('catPromotePospopHint');//弹出提示框
			}
		})
	};
	//根据编码搜索商品信息，模糊匹配
	$scope.searchCommotyByCode = function(){
		var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
		var res = $http.post("getCommodityInfo?pageNum=1&pageSize=5",commodityinfo);
		res.success(function(data, status, headers, config) {
			if(data.status == 'success'){
				//更新分页信息
				$scope.pageNum = data.result.pageNum;
				$scope.total = data.result.total;
				$scope.pageSize = data.result.pageSize;
				
				$scope.pagination.pageNum = data.result.pageNum;
				$scope.pagination.total = data.result.total;
				$scope.pagination.pageSize = data.result.pageSize;
				
				$scope.commodityList = data.result.result;
				var cb = 5 ;
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;
				$scope.tempList = [cb];
				while(cb > 0){
					$scope.tempList.push([cb]);
					cb--;
				}
				$scope.tempList.pop();
				$scope.initd = "0";
			}else if(data.status == 'error'){
				//清空商品列表
				$scope.commodityList = null;
				//清空分页信息
				$scope.pagination.pageNum = 0;
				$scope.pagination.total = 0;
				$scope.pagination.pageSize = 0;
				$scope.pageNum = 0;
				$scope.total = 0;
				$scope.pageSize = 0;
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('catPromotePospopHint');//弹出提示框
			}
		})
	};
	//选择商品
	$scope.choseCommodity = function(index){
		//选择商品信息index
		$scope.choseCommodityIndex = index;
		//选择以后商品code
		$scope.choseCommodityCode = $scope.commodityList[index].commodityCode;
		//选择已有商品名称
		$scope.choseCommodityName = $scope.commodityList[index].commodityName;
	};
	//保存商品
	$scope.savedate = function(){
		//选择商品非空check
		if(undefined == $scope.choseCommodityCode || null == $scope.choseCommodityCode){
			$scope.errorMessage = "请选择一个商品";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			return false;
		}
		//输入排序非空check
		if(undefined == $scope.displayOrder){
			$scope.errorMessage = "请输入正确排序值";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			return false;
		}
		
		//排序1-100之间
		if(Number($scope.displayOrder)<=0 || Number($scope.displayOrder)>100){
			$scope.errorMessage = "请输入1至100之间的数字";
			showPopHint('catPromotePospopHint');//弹出提示框
			return;
		}
		//验证排序是否重复
		for(i=0;i<$scope.catPromotePoslist.catPromotedCommodityList.length;i++){
			if(i != $scope.choseMenuIndex){
				if($scope.displayOrder == $scope.catPromotePoslist.catPromotedCommodityList[i].displayOrder){
					$scope.errorMessage = "该数字重复，请重新输入";
					showPopHint('catPromotePospopHint');//弹出提示框
					return;
				}
			}
		}
		//更新与前台绑定的商品数据
		$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].displayOrder
		= $scope.displayOrder;
		$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].categoryName
		= $scope.categoryName;
		$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].advert
		= $scope.advert;
		//商品赋值，当有选择时赋值
		if(undefined != $scope.choseCommodityIndex){
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].commodityCode 
				= $scope.commodityList[$scope.choseCommodityIndex].commodityCode;
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].productCode 
				= $scope.commodityList[$scope.choseCommodityIndex].product.productCode;
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].commodityName 
				= $scope.commodityList[$scope.choseCommodityIndex].commodityName;
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].commoditySpec 
				= $scope.commodityList[$scope.choseCommodityIndex].commodityWeight
					+$scope.commodityList[$scope.choseCommodityIndex].commodityWeightUnit;
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].commodityImgUrl 
				= $scope.commodityList[$scope.choseCommodityIndex].imageUrl;
		}
		var exit = exitPop('catPromotePospopUpHg');
	}
	//取消选择商品
	$scope.cancel = function(){
		var exit = exitPop('catPromotePospopUpHg');
	}
	
	$scope.img_upload = function(files){

		$scope.reader = new FileReader(); // 创建一个FileReader接口
		//获取文件名
		var imgvalue = files[0].type;

		//支持的文件格式
		var fileext = ['image/gif','image/jpeg', 'image/jpg','image/png'];

		//判断FLG
		var filer = false;
		//循环判断
		for(var i = 0; i < fileext.length; i++)
		{
			//符合支持格式返回true
			if (imgvalue == fileext[i])
			{
				filer = true;
				break;
			}
		};
		//验证文件格式
		if(!filer){
			$scope.errorMessage = "仅可上传(gif,jpg,jpeg,png)类型图片，请重新选择文件上传！";
			alert($scope.errorMessage);
			return false;
		}
		//验证文件大小
		else if (files[0].size / 1024 > 200) {
			$scope.errorMessage = "图片的大小超过200KB，请重新选择文件上传！";
			alert($scope.errorMessage);
			return false;
		} 
		//验证图片是否有更改，如果发生了更新，执行上传
		else {
			$scope.pic = files[0];
			
			if (undefined != $scope.pic && null != $scope.pic) {
				$scope.isHeaderPictureSatified = true;
			}else{
				$scope.isHeaderPictureSatified = false;
			}
			
			$scope.reader.readAsDataURL(files[0]); // FileReader的方法，把图片转成base64
			$scope.reader.onload = function(ev) {
				$scope.$apply(function() {
					$scope.item = {
							pictureAddress : ev.target.result, // 接收base64
							file : files[0]
					};
				});
			};
		};
	};
	
	//新增商品取消
	$scope.mainCancel = function(){
		window.location.href = "showMain"
	}
	//获取图片实际宽高
	function getImgNaturalDimension(img, callback) {
	    var nWidth ;
	    var nHeight;
	    nWidth = img.naturalWidth;
	    nHeight = img.naturalHeight;
	    return [nWidth,nHeight];
	}
	
	//新增商品保存
	$scope.mainSavedate=function(){
		//禁用保存按钮
		$scope.all=true;
		
		//图片不能为空
		if(undefined == $scope.item || null == $scope.item){
			$scope.errorMessage = "请选择一张图片上传！";
			showPopHint('catPromotePospopHint');//弹出提示框
			$scope.saveButtom=false;
			return false;
		}
		
		var img = document.getElementById('catPicture');
		var arr = getImgNaturalDimension(img);
		var width = arr[0];
		var height = arr[1];
		
		if (height != 460 || width != 450) {
			$scope.errorMessage = "请选择分辨率为450*460的图片!";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			$scope.saveButtom=false;
			return;
		}

		//输入排序非空check
		if(undefined == $scope.mainDisplayOrder){
			$scope.errorMessage = "请输入排序数字";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			$scope.saveButtom=false;
			return false;
		}
		//广告词规则验证
		if(undefined==$scope.catPromotePoslist.advert){
			$scope.errorMessage = "请输入广告词";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			$scope.saveButtom=false;
			return false;
		}
		//组图名称规则验证
		if(undefined==$scope.catPromotePoslist.categoryName){
			$scope.errorMessage = "请输入组图名称";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			$scope.saveButtom=false;
			return false;
		}

		//排序1-100之间
		if(Number($scope.mainDisplayOrder)<=0 || Number($scope.mainDisplayOrder)>100){
			$scope.errorMessage = "请输入1至100之间的数字";
			showPopHint('catPromotePospopHint');//弹出提示框
			$scope.saveButtom=false;
			return;
		}

		//检查排序是否重复
		$scope.catPromotePoslist.displayOrder = $scope.mainDisplayOrder;
		replaceTag($scope.catPromotePoslist.advert,$scope.catPromotePoslist.categoryName);
		var res = $http.post("selectBydisplayOrderIsHave",$scope.catPromotePoslist);
		res.success(function(data,status,headers,config){
			if(data.status == 'success'){
				$scope.orderReuslt = data.result;
				if($scope.orderReuslt){
					$scope.errorMessage = "排序重复，请重新输入！";
					showPopHint('catPromotePospopHint');//弹出提示框
					$scope.saveButtom=false;
					return;
				}

				//检查商品组明细是否满足8条
				var count = 0;
				for(i=0;i<$scope.catPromotePoslist.catPromotedCommodityList.length;i++){
					if(null == $scope.catPromotePoslist.catPromotedCommodityList[i].commodityCode){
						count++;
					}
				}
				if(count > 0){
					$scope.errorMessage = "选择明细条数必须为8条！";
					showPopHint('catPromotePospopHint');//弹出提示框
					$scope.saveButtom=false;
					return;
				}
				var data = new FormData();
				data.append(1,$scope.item.file);
				//图片上传
				$http({
					method : 'post',
					url : "imageUpload",
					data : data,
					headers : {
						'Content-Type' : undefined
					},
					transformRequest : angular.identity
				}).success(function(newdata) {
					$scope.catPromotePoslist.imageUrl = newdata.result;
					$scope.updateUpPic($scope.pic);
				}).error(function(){
					$scope.item=undefined;
					angular.element("#catPicture").removeAttr("src");
					$scope.errorMessage = "图片不存在！";//传递错误提示信息
		    		showPopHint('catPromotePospopHint');//弹出提示框
		    		$scope.saveButtom=false;
				});
				
				//完成insert保存
				$scope.updateUpPic = function(data){
					$http.post("addCatPromotePosList",$scope.catPromotePoslist).success(
							function(data,status,headers,config){
								if(data.status == 'success'){
									//跳转会配置页面
									window.location.href = "showMain"
								}else if(data.status == 'error'){
						    		$scope.errorMessage = data.messages;//传递错误提示信息
						    		showPopHint('catPromotePospopHint');//弹出提示框
						    	}
								$scope.saveButtom=false;
							});
					
				}
				$scope.saveButtom=false;
			}else if(data.status == 'error'){
	    		$scope.errorMessage = "检查排序重复出错！";//传递错误提示信息
	    		showPopHint('catPromotePospopHint');//弹出提示框
	    		$scope.saveButtom=false;
			}
		}).error(function(){
			$scope.saveButtom=false;
		});
	}
});

//商品组编辑
var app = angular.module('catPromotePosEditApp',['dPagination']);
app.controller('catPromotePosEditctrl',function($scope,$http){
	
	//初始化分页数据
	$scope.picChange = false;
	$scope.pageNum = 1;
	$scope.total = 1;
	$scope.pageSize = 5;

	$scope.updatePagination = function(){
		$scope.pagination.total = $scope.total; //更新表格行数
		$scope.pagination.pageSize = $scope.pageSize; //更新表格行数
		$scope.pagination.pageNum = $scope.pageNum; //更新页码
		var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
		$scope.serachPage($scope.pagination.pageNum,$scope.pagination.pageSize,commodityinfo);
	}
	//初始化分页数据
	$scope.pagination = {
        pageNum: $scope.pageNum,
        pageSize: $scope.pageSize,
        total: $scope.total,
        //当前页码改变执行查询
        onChange: function(pageNum){
			if($scope.initd == undefined)
				return;
			var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
			$scope.serachPage($scope.pagination.pageNum,$scope.pagination.pageSize,commodityinfo);
        }
    };
	//翻页控件使用的检索方法
	$scope.serachPage = function(pageNumber,pageSize,bean){
		var res = $http.post("getCommodityInfo?&pageNum="+pageNumber
				+"&pageSize="+pageSize,bean);
		res.success(function(data, status, headers, config) {
			if(data.status == 'success'){
				$scope.commodityList = data.result.result;
				$scope.pagination.currentPage = data.result.pageNum;
				$scope.pagination.totalItems = data.result.total;
				$scope.pagination.itemsPerPage = data.result.pageSize;
				var cb = 5 ;
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;
				$scope.tempList = [cb];
				while(cb > 0){
					$scope.tempList.push([cb]);
					cb--;
				}
				$scope.tempList.pop();
			}else if(data.status == 'error'){
				$scope.commodityList = null;
				$scope.pagination.pageNum = 0;
				$scope.pagination.total = 0;
				$scope.pagination.pageSize = 0;
				$scope.pageNum = 0;
				$scope.total = 0;
				$scope.pageSize = 0;
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('catPromotePospopHint');//弹出提示框
			}
		
		}).error(function(){
			
		});;
	}
	//将广告词和组图名称中的<替换成&lt;
	function replaceTag(){
		$scope.catPromotePoslist.categoryName=$scope.catPromotePoslist.categoryName.replace(/</g,"&lt;").replace(/>/g,"&gt;");
		$scope.catPromotePoslist.advert=$scope.catPromotePoslist.advert.replace(/</g,"&lt;").replace(/>/g,"&gt;");
	}
	//将广告词和组图名称中的&lt;替换成<
	function replaceToTag(){
		$scope.catPromotePoslist.categoryName=$scope.catPromotePoslist.categoryName.replace(/&lt;/g,"<").replace(/&gt;/g,">");
		$scope.catPromotePoslist.advert=$scope.catPromotePoslist.advert.replace(/&lt;/g,"<").replace(/&gt;/g,">");
	}
	
	//商品弹出框
	$scope.getCommodityinfo = function(index){
		//初始化商品编辑页面的商品编码、商品名、排序输入框的内容
		$scope.commodityCode = null;
		$scope.commodityName = null;
		$scope.displayOrder = null;
		//选择以后商品code
		$scope.choseCommodityCode = null;
		//选择已有商品名称
		$scope.choseCommodityName = null;
		//选择商品信息index
		$scope.choseCommodityIndex = null;
		$scope.choseMenuIndex = index;//选择index
		//选择以后商品code
		$scope.choseCommodityCode = $scope.catPromotePoslist.catPromotedCommodityList[index].commodityCode;
		//选择已有商品名称
		$scope.choseCommodityName = $scope.catPromotePoslist.catPromotedCommodityList[index].commodityName;
		//选择已有商品名称
		$scope.displayOrder = $scope.catPromotePoslist.catPromotedCommodityList[index].displayOrder;
		$scope.categoryName = "NO";
		var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
		var res = $http.post("getCommodityInfo?pageNum=1&pageSize=5",commodityinfo);
		res.success(function(data, status, headers, config) {
			if(data.status == 'success'){
				//更新分页数据
				$scope.pageNum = data.result.pageNum;
				$scope.total = data.result.total;
				$scope.pageSize = data.result.pageSize;
				
				$scope.pagination.pageNum = data.result.pageNum;
				$scope.pagination.total = data.result.total;
				$scope.pagination.pageSize = data.result.pageSize;
				$scope.commodityList = data.result.result;
				var cb = 5 ;
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;
				$scope.tempList = [cb];
				while(cb > 0){
					$scope.tempList.push([cb]);
					cb--;
				}
				$scope.tempList.pop();
				$scope.initd = "0";
				var pop = showPopup('catPromotePospopUpHg',true);
			}else if(data.status == 'error'){
				//清空商品列表
				$scope.commodityList = null;
				//清空分页数据
				$scope.pagination.pageNum = 0;
				$scope.pagination.total = 0;
				$scope.pagination.pageSize = 0;
				$scope.pageNum = 0;
				$scope.total = 0;
				$scope.pageSize = 0;
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('catPromotePospopHint');//弹出提示框
			}
		})
	};
	//获取选中的商品组ID
	var catPromotePosid = angular.element("#catPromotePosid").val();
	var pageMain = 1;
	var res = $http.post("getCatPromotePosInfo?pageState="+pageMain+"&catPromotePosid="+catPromotePosid);
	res.success(function(data,status,headers,config){
		if(data.status == 'success'){
			$scope.catPromotePoslist = data.result[0];
			replaceToTag($scope.catPromotePoslist.advert,$scope.catPromotePoslist.categoryName)
		}else if(data.status == 'error'){
    		$scope.errorMessage = data.messages;//传递错误提示信息
    		showPopHint('catPromotePospopHint');//弹出提示框
    		//1s后跳转到首页
    		setTimeout(function(){
    			window.location.href = "showMain"
    		},1000)
    		
		}
	}).error(function(){
		
	});
	
	//根据编码搜索商品信息，模糊匹配
	$scope.searchCommotyByCode = function(){
		var commodityinfo = {commodityCode:$scope.commodityCode,commodityName:$scope.commodityName};
		var res = $http.post("getCommodityInfo?pageNum=1&pageSize=5",commodityinfo);
		res.success(function(data, status, headers, config) {
			if(data.status == 'success'){
				//更新分页信息
				$scope.pageNum = data.result.pageNum;
				$scope.total = data.result.total;
				$scope.pageSize = data.result.pageSize;
				
				$scope.pagination.pageNum = data.result.pageNum;
				$scope.pagination.total = data.result.total;
				$scope.pagination.pageSize = data.result.pageSize;
				
				$scope.commodityList = data.result.result;
				var cb = 5 ;
				if($scope.commodityList != undefined && $scope.commodityList != {})
					cb = cb - $scope.commodityList.length;
				$scope.tempList = [cb];
				while(cb > 0){
					$scope.tempList.push([cb]);
					cb--;
				}
				$scope.tempList.pop();
				$scope.initd = "0";
			}else if(data.status == 'error'){
				//清空与前台绑定的商品数据
				$scope.commodityList = null;
				//清空分页数据
				$scope.pagination.pageNum = 0;
				$scope.pagination.total = 0;
				$scope.pagination.pageSize = 0;
				$scope.pageNum = 0;
				$scope.total = 0;
				$scope.pageSize = 0;
	    		$scope.errorMessage = data.messages;//传递错误提示信息
	    		showPopHint('catPromotePospopHint');//弹出提示框
			}
		 });
	};
	//选择商品
	$scope.choseCommodity = function(index){
		//选择商品信息index
		$scope.choseCommodityIndex = index;
		//选择以后商品code
		$scope.choseCommodityCode = $scope.commodityList[index].commodityCode;
		//选择已有商品名称
		$scope.choseCommodityName = $scope.commodityList[index].commodityName;
	};
	//保存商品
	$scope.savedate = function(){
		//选择商品非空check
		if(undefined == $scope.choseCommodityCode || null == $scope.choseCommodityCode){
			$scope.errorMessage = "请选择一个商品";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			return false;
		}
		//输入排序非空check
		if(undefined == $scope.displayOrder){
			$scope.errorMessage = "请输入正确排序值";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			return false;
		}
		
		//排序1-100之间
		if(Number($scope.displayOrder)<=0 || Number($scope.displayOrder)>100){
			$scope.errorMessage = "请输入1至100之间的数字";
			showPopHint('catPromotePospopHint');//弹出提示框
			return;
		}
		for(i=0;i<$scope.catPromotePoslist.catPromotedCommodityList.length;i++){
			if(i != $scope.choseMenuIndex){
				if($scope.displayOrder == $scope.catPromotePoslist.catPromotedCommodityList[i].displayOrder){
					$scope.errorMessage = "该数字重复，请重新输入";
					showPopHint('catPromotePospopHint');//弹出提示框
					return;
				}
			}
		}
		$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].displayOrder = $scope.displayOrder;
		//商品赋值，当有选择时赋值
		if(undefined != $scope.choseCommodityIndex){
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].commodityCode 
				= $scope.commodityList[$scope.choseCommodityIndex].commodityCode;
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].productCode 
				= $scope.commodityList[$scope.choseCommodityIndex].product.productCode;
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].commodityName 
				= $scope.commodityList[$scope.choseCommodityIndex].commodityName;
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].commoditySpec 
				= $scope.commodityList[$scope.choseCommodityIndex].commodityWeight
					+$scope.commodityList[$scope.choseCommodityIndex].commodityWeightUnit;
			$scope.catPromotePoslist.catPromotedCommodityList[$scope.choseMenuIndex].commodityImgUrl 
				= $scope.commodityList[$scope.choseCommodityIndex].imageUrl;
		}
		var exit = exitPop('catPromotePospopUpHg');
	}
	//取消选择商品
	$scope.cancel = function(){
		var exit = exitPop('catPromotePospopUpHg');
	}
	
	$scope.img_upload = function(files){

		$scope.reader = new FileReader(); // 创建一个FileReader接口
		//获取文件名
		var imgvalue = files[0].type;

		//支持的文件格式
		var fileext = ['image/gif','image/jpeg', 'image/jpg','image/png'];

		//判断FLG
		var filer = false;
		//循环判断
		for(var i = 0; i < fileext.length; i++)
		{
			//符合支持格式返回true
			if (imgvalue == fileext[i])
			{
				filer = true;
				break;
			}
		};
		//验证图片格式
		if(!filer){
			$scope.errorMessage = "仅可上传(gif,jpg,jpeg,png)类型图片，请重新选择文件上传！";
			alert($scope.errorMessage);
			return false;
		}
		//验证图片大小
		else if (files[0].size / 1024 > 200) {
			$scope.errorMessage = "图片的大小超过200KB，请重新选择文件上传！";
			alert($scope.errorMessage);
			return false;
		} 
		//验证图片是否有更改，如果发生了更新，执行上传
		else {
			$scope.pic = files[0];
			
			if (undefined != $scope.pic && null != $scope.pic) {
				$scope.picChange = true;
			}else{
				$scope.picChange = false;
			}
			
			$scope.reader.readAsDataURL(files[0]); // FileReader的方法，把图片转成base64
			$scope.reader.onload = function(ev) {
				$scope.$apply(function() {
					$scope.item = {
							pictureAddress : ev.target.result, // 接收base64
							file : files[0]
					};
					$scope.catPromotePoslist.imageUrl=$scope.item.pictureAddress;
				});
			};
		};

	};

	//新增商品取消
	$scope.mainCancel = function(){
		window.location.href = "showMain"
	}
	//获取图片实际宽高
	function getImgNaturalDimensions(img, callback) {
	    var nWidth ;
	    var nHeight;
	    nWidth = img.naturalWidth;
	    nHeight = img.naturalHeight;
	    return [nWidth,nHeight];
	}
	//新增商品保存
	$scope.mainSavedate = function(){
		var img = document.getElementById('catPicture');
		var arr = getImgNaturalDimensions(img);
		var width = arr[0];
		var height = arr[1];
		//验证图片分辨率
		if (height != 460 || width != 450) {
			$scope.errorMessage = "请选择分辨率为450*460的图片!";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			return;
		}
		//验证广告词
		if(undefined==$scope.catPromotePoslist.advert){
			$scope.errorMessage = "请输入广告词";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			return false;
		}
		//验证组图名称
		if(undefined==$scope.catPromotePoslist.categoryName){
			$scope.errorMessage = "请输入组图名称";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			return false;
		}
		//图片不能为空
		if("" == $scope.catPromotePoslist.imageUrl || null == $scope.catPromotePoslist.imageUrl){
			$scope.errorMessage = "请选择一张图片上传！";
			showPopHint('catPromotePospopHint');//弹出提示框
			return false;
		}
		//输入排序非空check
		if(undefined == $scope.catPromotePoslist.displayOrder){
			
			$scope.errorMessage = "请输入正确的排序值";//传递错误提示信息
			showPopHint('catPromotePospopHint');//弹出提示框
			return;
		}
		
		//排序1-100之间
		if(Number($scope.catPromotePoslist.displayOrder)<=0 || Number($scope.catPromotePoslist.displayOrder)>100){
			
			$scope.errorMessage = "请输入1至100之间的数字";
			showPopHint('catPromotePospopHint');//弹出提示框
			return;
		}

		//检查排序是否重复
		$scope.catPromotePoslist.displayOrder = $scope.catPromotePoslist.displayOrder;
		var res = $http.post("selectBydisplayOrderIsHave",$scope.catPromotePoslist);
		res.success(function(data,status,headers,config){
			if(data.status == 'success'){
				$scope.orderReuslt = data.result;
				if($scope.orderReuslt){
					
					$scope.errorMessage = "排序重复，请重新输入！";
					showPopHint('catPromotePospopHint');//弹出提示框
					return;
				}

				//检查商品组明细是否满足8条
				var count = 0;
				for(i=0;i<$scope.catPromotePoslist.catPromotedCommodityList.length;i++){
					if(null == $scope.catPromotePoslist.catPromotedCommodityList[i].commodityCode){
						count++;
					}
				}
				if(count > 0){
					$scope.errorMessage = "选择明细条数必须为8条！";
					showPopHint('catPromotePospopHint');//弹出提示框
					return;
				}
				//图片有变更时上传图片
				if($scope.picChange){

					var data = new FormData();
					data.append(1,$scope.item.file);
					//图片上传
					$http({
						method : 'post',
						url : "imageUpload",
						data : data,
						headers : {
							'Content-Type' : undefined
						},
						transformRequest : angular.identity
					}).success(function(newdata) {
						$scope.catPromotePoslist.imageUrl = newdata.result;
						$scope.updateUpPic($scope.pic);
					}).error(function(){
						$scope.item=undefined;
						angular.element("#catPicture").removeAttr("src");
						$scope.errorMessage = "图片不存在！";//传递错误提示信息
			    		showPopHint('catPromotePospopHint');//弹出提示框
					});
				}else{
					//更新
					replaceTag($scope.catPromotePoslist.advert,$scope.catPromotePoslist.categoryName);
					$http.post("updateCatPromotePos",$scope.catPromotePoslist).success(
							function(data,status,headers,config){
								if(data.status == 'success'){
									//跳转会配置页面
									window.location.href = "showMain"
								}else if(data.status == 'error'){
						    		$scope.errorMessage = data.messages;//传递错误提示信息
									showPopHint('catPromotePospopHint');//弹出提示框
								}
							});
				
				};
				
				//完成update保存
				$scope.updateUpPic = function(data){
					replaceTag($scope.catPromotePoslist.advert,$scope.catPromotePoslist.categoryName);
					$http.post("updateCatPromotePos",$scope.catPromotePoslist).success(
							function(data,status,headers,config){
								if(data.status == 'success'){
									//跳转会配置页面
									window.location.href = "showMain"
								}else if(data.status == 'error'){
						    		$scope.errorMessage = "新数据更新出错！";//传递错误提示信息
						    		showPopHint('catPromotePospopHint');//弹出提示框
								}
							});
				};
			}else if(data.status == 'error'){
	    		$scope.errorMessage = "检查排序重复出错！";//传递错误提示信息
	    		showPopHint('catPromotePospopHint');//弹出提示框
			}
		})
	};
	
});
//DOM元素加载完毕更改对应菜单背景颜色
onload=function(){
	angular.element(".catPromotePos").css("background-color","#99c731");
}
//跳转到指定页面
function loadSecMenu(url) {
	window.location.href = url;
}