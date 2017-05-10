angular.module('pictureApp',[]).controller('pictureCtrl',function($scope,$rootScope,$http){

	//头部默认为6张图片
	$rootScope.headerPictureSize = 6;
	
	//头部限定最少图片数量
	$rootScope.headerPictureCheckSize = 4;
	
	//用于保存图片信息
	$rootScope.thumbHeader = [];//页面头部图片集合
	$rootScope.formHeader = [];//头部图片判断是否修改集合

	$rootScope.thumbDetail = [];//页面详细图片集合
	$rootScope.formDetail = [];//判断详细图片是否修改集合

	$scope.reader = new FileReader(); // 创建一个FileReader接口


	//判断图片上传的张数是否满足条件
	$rootScope.isHeaderPictureSatified = false;//头部图片判断flg
	$rootScope.isDetailPictureSatified = false;//详细图片判断flg

	//图片处理的函数
	$scope.img_upload = function(element) {

		//获取文件名
		var imgvalue = element.value;
		//支持的文件格式
		var fileext = ['gif', 'jpg','png'];
		//获取文件名后三位
		var slower = imgvalue.toLowerCase().substring(imgvalue.length-3,imgvalue.length);
		//判断FLG
		var filer = false;
		//循环判断
		for(var i = 0; i < fileext.length; i++)
		{
			//符合支持格式返回true
			if (slower == fileext[i])
			{
				filer = true;
				break;
			}
		} 

		if(!filer){
			//文件类型不对
			alert("仅可上传(gif,jpg,png)类型图片，请重新选择文件上传！");
			return false;
		} else {
			//图片被修改赋值
			$rootScope.formHeader[element.name] = {
					id : 0,
					file : element.files[0],
					changeFlag: true,
					pictureShowOrder:element.name
			};
			
			$scope.reader.readAsDataURL(element.files[0]); // FileReader的方法，把图片转成base64
			$scope.reader.onload = function(ev) {
				
				var image = new Image();
				//防止src未读取时出现异步
				image.src = ev.target.result;//图片流赋值
				image.onload = function(){//防止异步
					var height = image.height;
					var width = image.width;
					//宽高判断
					if (height != 840 || width != 840) {
						alert("请选择分辨率为840*840的图片!");
						return;
					}
					//用于强制刷新scope
					$scope.$apply(function() {
						//为页面显示赋值
						$rootScope.thumbHeader[element.name] = {
								pictureAddress : ev.target.result, // 接收base64
								pictureShowOrder:element.name,//顺序
								file : element.files[0]
						}
						checkSize();//删除时判断不满足个数
					});
				}
			};
		}
	};
	$scope.img_del = function(key) { // 删除，删除的时候thumb和form里面的图片数据都要删除，避免提交不必要的
		var guidArr = [];//创建一个空的数组
		for ( var p in $rootScope.thumbHeader) {
			guidArr.push(p);//占位
		}
		//清空页面图片流
		$rootScope.thumbHeader[guidArr[key]] = {
				pictureAddress : ""
		};
		//清空判断数组位置
		$rootScope.formHeader[guidArr[key]] = {
				id : 0,
				file : undefined
		};
		checkSize();//删除时判断不满足个数
	};

	function checkSize(){

		var i = 0;
		// 判断图片是否上传完成
		for ( var p in $rootScope.thumbHeader) {
			if($rootScope.thumbHeader[p] != undefined && $rootScope.thumbHeader[p].pictureAddress != undefined && $rootScope.thumbHeader[p].pictureAddress != ''){
				i++;
			}
		}
		//满足个数判断
		if (i >= $rootScope.headerPictureCheckSize) {
			$rootScope.isHeaderPictureSatified = true;
		}else{
			$rootScope.isHeaderPictureSatified = false;
		}
		if(!$rootScope.pictureCheck){
			$rootScope.isHeaderPictureSatified = true;
		}
	}

	// 用于存放图片的base64
	$scope.img_upload_detail = function(files) { // 单次提交图片的函数
		//获取文件名
		var imgvalue = files[0].name;
		//支持的文件格式
		var fileext = ['gif', 'jpg','png'];
		//获取文件名后三位
		var slower = imgvalue.toLowerCase().substring(imgvalue.length-3,imgvalue.length);
		//判断FLG
		var filer = false;
		//循环判断
		for(var i = 0; i < fileext.length; i++){
			//符合支持格式返回true
			if (slower == fileext[i]){
				filer = true;
				break;
			}
		} 
		if(!filer) {
			//不满足文件类型
			alert("仅可上传(gif,jpg,png)类型图片，请重新选择文件上传！");
			return false;
		} else{
			//计算页面图片个数
			var pictureSize = $rootScope.thumbDetail.length;
			//向数组添加一个元素
			$rootScope.formDetail.push({
				id : 1,
				file : files[0],
				changeFlag: true
			});
			//向数组添加一个元素
			$rootScope.thumbDetail.push({
				id : 1,
				file : files[0]
			});
			$scope.reader.readAsDataURL(files[0]); // FileReader的方法，把图片转成base64
			$scope.reader.onload = function(ev) {
				$scope.$apply(function() {//刷新scope
					//为画面显示赋值
					$rootScope.thumbDetail[pictureSize] = {
							pictureAddress : ev.target.result,
							pictureShowOrder:pictureSize,
							file : files[0]
					}
				});
			};
			//至少大于1张图片
			if ($rootScope.formDetail.length > 0) {
				$rootScope.isDetailPictureSatified = true;
			}
		}
	};
	$scope.img_del_detail = function(key) { // 删除
		$rootScope.formDetail.splice(key, 1);//删除指定index
		$rootScope.thumbDetail.splice(key, 1);//删除指定index
		//商品业务时删除商品指定位置图片
		if(undefined != $scope.$parent.commodity && null != $scope.$parent.commodity){
			$scope.$parent.commodity.commodityPictures.splice(key+$rootScope.headerPictureSize, 1);
		}
		//普通产品业务室删除普通产品位置图片
		if($scope.$parent.$parent.product != undefined && $scope.$parent.$parent.product.productPictures != undefined){
			$scope.$parent.$parent.product.productPictures.splice(key+$rootScope.headerPictureSize, 1);
		}
		//组合产品业务室删除普通产品位置图片
		if($scope.$parent.$parent.compositeProduct != undefined && $scope.$parent.$parent.compositeProduct.product != undefined && $scope.$parent.$parent.compositeProduct.product.productPictures != undefined){
			$scope.$parent.$parent.compositeProduct.product.productPictures.splice(key+$rootScope.headerPictureSize, 1);
		}
		//详细至少1张
		if ($rootScope.thumbDetail.length > 0) {
			$rootScope.isDetailPictureSatified = true;
		} else {
			$rootScope.isDetailPictureSatified = false;
		}
		if(!$rootScope.pictureCheck){
			$rootScope.isDetailPictureSatified = true;
		}
	};

	$scope.img_moveUp_detail = function(key) { // 交换位置，向上移动一个位置
		var pictureSize = $rootScope.thumbDetail.length;
		if(pictureSize < 2)//位置调整至少需要两种图片
			return;
		// 分两种种情况移动图片：
		if (key == 0) {
			// 如果是第一张，直接移动到最后的位置
			var temp2 = $rootScope.thumbDetail[pictureSize - 1];
			$rootScope.thumbDetail[pictureSize - 1] = $rootScope.thumbDetail[key];
			$rootScope.thumbDetail[key] = temp2;
		} else {
			//交换位置
			var temp2 = $rootScope.thumbDetail[key - 1];
			$rootScope.thumbDetail[key - 1] = $rootScope.thumbDetail[key];
			$rootScope.thumbDetail[key] = temp2;
		}
	};
	$scope.img_moveDown_detail = function(key) { // 交换位置，向下移动一个位置
		var pictureSize = $rootScope.thumbDetail.length;
		if(pictureSize < 2)//位置调整至少需要两种图片
			return;
		// 分两种种情况移动图片：
		if (key == pictureSize - 1) {
			// 如果是最后一张，直接移动到第一张的位置
			var temp2 = $rootScope.thumbDetail[0];
			$rootScope.thumbDetail[0] = $rootScope.thumbDetail[key];
			$rootScope.thumbDetail[key] = temp2;
		} else {
			//交换位置
			var temp2 = $rootScope.thumbDetail[key + 1];
			$rootScope.thumbDetail[key + 1] = $rootScope.thumbDetail[key];
			$rootScope.thumbDetail[key] = temp2;
		}
	};
	
});