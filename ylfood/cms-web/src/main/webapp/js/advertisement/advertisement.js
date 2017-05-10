var module = angular.module('advertisementModule', []);
module.controller('advertisementCtrl', function($scope, $http) {
	//获取轮播图列表
	$http({
        method:"GET",
        url:"list"
    }).success(function(data) {
    	if(data.status == 'success'){
    		// 拿到所有广告位数据
    		$scope.slideAdsPositionList = data.result;
    		// 设置第一条数据为默认数据
    		$scope.defaultValue = "" + data.result[0].id;
    		// 最大广告位数量
    		$scope.maxSlot = data.result[0].maxSlot;
    		// 拿到第一条数据缓存起来作为默认展示
    		$scope.advertisementList = data.result[0].advertisements;
    		// 拿到广告位的Id缓存起来,后面方法使用
    		$scope.tempSlideAdsPosId = data.result[0].id;
    	}
    	if(data.status == 'error'){
    		alert(data.messages);//传递错误提示信息
    	}
    }).error(function() {
    });
	
	// 变换广告位
	$scope.changeSlideAdsPosition = function() {
		// 通过改变的广告位id拿到变换后的广告位
		for (i = 0; i < $scope.slideAdsPositionList.length; i++) {
			// 前台select中变换后的id等于查询出来广告位的id,就重新赋值展示广告位页面
			if ($scope.defaultValue == $scope.slideAdsPositionList[i].id) {
				$scope.advertisementList = $scope.slideAdsPositionList[i].advertisements;
				// 拿到广告位的Id缓存起来,后面方法使用
				$scope.tempSlideAdsPosId = $scope.slideAdsPositionList[i].id;
				// 缓存最大的图片数量
				$scope.maxSlot = $scope.slideAdsPositionList[i].maxSlot;
				return;
			}
		}
	}
	// 点击添加按钮
	$scope.add = function() {
		// 轮播图不能超过10张,超过不能添加
		if ($scope.advertisementList.length == $scope.maxSlot) {
			$scope.errorMessage = "banner不能超过"+ $scope.maxSlot +"个!";//传递错误提示信息
			showPopHint('advertisementPopHint');//弹出提示框
			return;
		}
		// 弹出新增对话框
		showPopup("advertisementPopUp", true);
		// 初始化轮播图对象,用于新增的回显
		$scope.addAdvertisement = {imageUrl:null,
				targetUrl:null,
				displayOrder:null};
	}
	// 编辑按钮
	$scope.edit = function(index){
		// 弹出编辑对话框
		showPopup('advertisementPopUp',true);
		// 回显数据
		$scope.addAdvertisement = {id:$scope.advertisementList[index].id,
				slideAdsPosId:$scope.advertisementList[index].slideAdsPosId,
				imageUrl:$scope.advertisementList[index].imageUrl,
				targetUrl:$scope.advertisementList[index].targetUrl,
				displayOrder:$scope.advertisementList[index].displayOrder,index:index};
	}
	
	// 保存前的检测
	$scope.saveCheck = function() {
		// 拿到图片上传控件
		var obj = document.getElementById('oneInput') ;
		// 图片不能为空
		if("" == $scope.addAdvertisement.imageUrl || null == $scope.addAdvertisement.imageUrl){
			$scope.errorMessage = "请选择一张图片上传！";
			showPopHint('advertisementPopHint');//弹出提示框
			// 谷歌兼容(不能选择之前选择的图片)
			obj.outerHTML=obj.outerHTML;
			return false;
		}
		// 图片规定尺寸限定
		// 图片DOM对象
		var img = document.getElementById('advertisementPicture');
		// 拿到图片的真是尺寸,返回一个数组
		var arr = getImgNaturalDimensions(img);
		// 图片宽高
		var width = arr[0];
		var height = arr[1];
		// 确定是手机端还是pc端,拿到广告位名称
		var slideAdsPositionName;
		// 循环找到广告位名称
		for (i = 0; i < $scope.slideAdsPositionList.length; i++) {
			// 通过select框传过来的数据确定名称
			if ($scope.slideAdsPositionList[i].id == $scope.defaultValue) {
				slideAdsPositionName = $scope.slideAdsPositionList[i].name;
			}
		}
		// 手机端和PC端不同的图片尺寸
		if (slideAdsPositionName == "手机端") {
			// 限定图片尺寸
			if (height != 607 || width != 1080) {
				$scope.errorMessage = "请选择分辨率为1080*607的图片!";//传递错误提示信息
				showPopHint('advertisementPopHint');//弹出提示框
				// 谷歌兼容(不能选择之前选择的图片)
				obj.outerHTML=obj.outerHTML;
				return false;
			}
		} else {
			// 限定图片尺寸
			if (height != 360 || width != 1920) {
				$scope.errorMessage = "请选择分辨率为1920*360的图片!";//传递错误提示信息
				showPopHint('advertisementPopHint');//弹出提示框
				// 谷歌兼容(不能选择之前选择的图片)
				obj.outerHTML=obj.outerHTML;
				return false;
			}
		}
		// 点击图片跳转的路路径不能为空
		if("" == $scope.addAdvertisement.targetUrl || null == $scope.addAdvertisement.targetUrl){
			$scope.errorMessage = "请输入点击图片要跳转的路径!";//传递错误提示信息
			showPopHint('advertisementPopHint');//弹出提示框
			// 谷歌兼容(不能选择之前选择的图片)
			obj.outerHTML=obj.outerHTML;
			return false;
		}
		// URL路径不能超过两百个字符串
		if ($scope.addAdvertisement.targetUrl.length > 200) {
			$scope.errorMessage = "输入的URL地址最多支持200个字符!";//传递错误提示信息
			showPopHint('advertisementPopHint');//弹出提示框
			// 谷歌兼容(不能选择之前选择的图片)
			obj.outerHTML=obj.outerHTML;
			return false;
		}
		// 检查点击图片要跳转的目标路径不能重复
		for (i = 0; i < $scope.advertisementList.length; i++) {
			if(($scope.addAdvertisement.index != i) && ($scope.addAdvertisement.targetUrl == $scope.advertisementList[i].targetUrl)){
				$scope.errorMessage = "URL路径已存在,请重新输入!";
				showPopHint('advertisementPopHint');//弹出提示框
				// 谷歌兼容(不能选择之前选择的图片)
				obj.outerHTML=obj.outerHTML;
				return false;
			}
		}
		// 输入排序非空check
		if("" == $scope.addAdvertisement.displayOrder || null == $scope.addAdvertisement.displayOrder){
			$scope.errorMessage = "请输入排序数字!";//传递错误提示信息
			showPopHint('advertisementPopHint');//弹出提示框
			// 谷歌兼容(不能选择之前选择的图片)
			obj.outerHTML=obj.outerHTML;
			return false;
		}
		// 排序不能是字符串
		if(isNaN($scope.addAdvertisement.displayOrder)){
			$scope.errorMessage = "排序只能是正整数!";//传递错误提示信息
			showPopHint('advertisementPopHint');//弹出提示框
			// 谷歌兼容(不能选择之前选择的图片)
			obj.outerHTML=obj.outerHTML;
			return false;
		}

		// 排序数字ckeck
		var reg = /^[0-9]*[1-9][0-9]*$/;
		if(!reg.test($scope.addAdvertisement.displayOrder)){
			$scope.errorMessage = "排序不能是小数,必须为正整数!";
			showPopHint('advertisementPopHint');//弹出提示框
			// 谷歌兼容(不能选择之前选择的图片)
			obj.outerHTML=obj.outerHTML;
			return false;
		}
		// 排序1-100之间
		if($scope.addAdvertisement.displayOrder <=0 || $scope.addAdvertisement.displayOrder > 100){
			$scope.errorMessage = "请输入1至100之间的数字";
			showPopHint('advertisementPopHint');//弹出提示框
			// 谷歌兼容(不能选择之前选择的图片)
			obj.outerHTML=obj.outerHTML;
			return false;
		}
		// 检查排序不重复
		for (i = 0; i < $scope.advertisementList.length; i++) {
			// 和页面已经存在的排序不能重复
			if(($scope.addAdvertisement.index != i) && ($scope.addAdvertisement.displayOrder == $scope.advertisementList[i].displayOrder)){
				$scope.errorMessage = "排序数字重复,请重新输入!";
				showPopHint('advertisementPopHint');//弹出提示框
				// 谷歌兼容(不能选择之前选择的图片)
				obj.outerHTML=obj.outerHTML;
				return false;
			}
		}
		return true;
	}
	
	// 提交保存按钮
	$scope.saveOrUpdate = function() {
		// 防重复提交
		if ($scope.isDisabled) {
			return;
		}
		// 验证通过执行下面的代码,不通过结束方法
		var checkFlg = $scope.saveCheck();
		if (!checkFlg) {
			return;
		}
		// 验证通过后保存按钮失效
		$scope.isDisabled = true;
		// 定义要保存到数据库中的对象
		var addAdvertisement;
		// 判断是新增还是编辑
		if ($scope.addAdvertisement.id) { // 编辑
			// 给对象赋值
			addAdvertisement = {id:$scope.addAdvertisement.id,
					imageUrl:$scope.addAdvertisement.imageUrl,
					slideAdsPosId:$scope.addAdvertisement.slideAdsPosId,
					targetUrl:$scope.addAdvertisement.targetUrl,
					displayOrder:$scope.addAdvertisement.displayOrder};
			// 判断图片是否改变
			if ($scope.addAdvertisement.imageUrl == $scope.advertisementList[$scope.addAdvertisement.index].imageUrl) { // 图片不变
				// 直接发送请求保存数据到数据库
				var resp = $http.post("saveOrUpdate", addAdvertisement);
				resp.success(function(data,status,headers,config){
					if(data.status == 'success'){
						// 关闭对话框
						exitPop('advertisementPopUp');
						// 保存按钮不可用
						$scope.isDisabled = false;
						// 更新保存成功后的对象到列表
						$scope.advertisementList[$scope.addAdvertisement.index] = addAdvertisement;
						// 清空对话框中的数据
						$scope.addAdvertisement = null;
					} else if(data.status == 'error'){
						// 关闭对话框
						exitPop('advertisementPopUp');
						$scope.errorMessage = data.messages;
						showPopHint('advertisementPopHint');//弹出提示框
					}
				}).error(function(){
				});
			} else { // 图片改变
				// 创建对象添加图片信息
				var dataPic = new FormData();
				dataPic.append(1,$scope.item.file);
				// 保存图片请求
				$http({
					method:"POST",
					url : "imageUpload",
					data : dataPic,
					headers : {
						'Content-Type' : undefined
					},
					transformRequest : angular.identity
				}).success(function(newdata) { 
					// 图片上传成功返回路径
					addAdvertisement.imageUrl = newdata.result;
					// 第二次发送请求保存数据到数据库
					var resp = $http.post("saveOrUpdate", addAdvertisement);
					resp.success(function(data,status,headers,config){
						if(data.status == 'success'){
							// 保存成功后关闭对话框
							exitPop('advertisementPopUp');
							// 保存按钮失效
							$scope.isDisabled = false;
							// 数据库数据和页面数据同步更新
							$scope.advertisementList[$scope.addAdvertisement.index] = addAdvertisement;
							// 清空对话框中数据
							$scope.addAdvertisement = null;
							// 谷歌不能重复选择相同图片
							var obj = document.getElementById('oneInput'); 
							obj.outerHTML=obj.outerHTML;
						} else if(data.status == 'error'){
							exitPop('advertisementPopUp');
							$scope.errorMessage = data.messages;
							showPopHint('advertisementPopHint');//弹出提示框
						}
					}).error(function(){
					});
				}).error(function() {
					$scope.errorMessage = "图片不存在!";
					showPopHint('advertisementPopHint');//弹出提示框
					// 移除URL避免第二次选不了同一张图片
					$scope.addAdvertisement.imageUrl = null;
					$scope.isDisabled = false;
				});
			}
		} else { // 新增
			// 先上传图片在保存数据
			// 定义对象添加图片信息
			var dataPic = new FormData();
			dataPic.append(1,$scope.item.file);
			// 发送请求上传图片
			$http({
				method:"POST",
				url : "imageUpload",
				data : dataPic,
				headers : {
					'Content-Type' : undefined
				},
				transformRequest : angular.identity
			}).success(function(newdata) {
				// 要保存的对象赋值,添加图片返回的路径
				addAdvertisement = {slideAdsPosId:$scope.tempSlideAdsPosId,
						imageUrl:newdata.result,
						targetUrl:$scope.addAdvertisement.targetUrl,
						displayOrder:$scope.addAdvertisement.displayOrder};
				// 发送请求保存数据
				var resp = $http.post("saveOrUpdate", addAdvertisement);
				resp.success(function(data,status,headers,config){
					if(data.status == 'success'){
						// 页面数据和数据库数据同步,把新增的数据添加到缓存对象列表中
						$scope.advertisementList.push(data.result); 
						// 关闭对话框
						exitPop('advertisementPopUp');
						// 保存按钮失效,避免重复提交
						$scope.isDisabled = false;
						// 清空弹出框数据
						$scope.addAdvertisement = null;
						// 谷歌不能重复选择相同图片
						var obj = document.getElementById('oneInput');
						obj.outerHTML=obj.outerHTML;
					} else if(data.status == 'error'){
						// 关闭对话框
						exitPop('advertisementPopUp');
						$scope.errorMessage = data.messages;
						showPopHint('advertisementPopHint');//弹出提示框
					}
				}).error(function(){
				});
			}).error(function() {
				$scope.errorMessage = "图片不存在!";
				showPopHint('advertisementPopHint');//弹出提示框
				$scope.isDisabled = false;
				$scope.addAdvertisement.imageUrl = null;
			});
		}
		// 清空加载图片留下的数据
		$scope.item = null;
		// 清除图片属性,使图片再下一次新增时不显示
		angular.element("#advertisementPicture").removeAttr("src");
	}

	
	// 关闭对话框
	$scope.close = function() {
		exitPop('advertisementPopUp');
		// 清除图片属性,使图片再下一次新增时不显示
		angular.element("#advertisementPicture").removeAttr("src");
		// 保存按钮生效
		$scope.isDisabled = false;
	}
	
	// 删除按钮
	$scope.deleteButton = function(index) {
		// 弹出提示询问框
		showPopup('advertisementPopDialogue',true);
		// 缓存点击指定删除按钮的索引
		$scope.deleteIndex = index;
	}
	
	// 确认删除
	$scope.deleteAdvertisement = function(){
		// 拿到缓存的索引
		var index = $scope.deleteIndex;
		// 发送删除请求
		var resp = $http.post("delete?id="+$scope.advertisementList[index].id);
		resp.success(function(data,status,headers,config){
			if(data.status == 'success'){
				// 成功后同步页面数据和数据库数据
				$scope.advertisementList.splice(index,1);
				// 关闭对话框
				exitPop('advertisementPopDialogue');
			}else if(data.status == 'error'){
				alert(data.messages);//传递错误提示信息
			}
		}).error(function(){
			
		});
	}
	
	// 取消按钮
	$scope.cancel = function() {
		// 拿到图片上传DOM对象
		var obj = document.getElementById('oneInput') ;
		// 谷歌兼容(不能选择之前选择的图片)
		obj.outerHTML=obj.outerHTML;
		// 关闭对话框
		exitPop('advertisementPopUp');
		// 清空对话框的数据
		$scope.addAdvertisement = null;
		// 清空加载图片的数据
		$scope.item = null;
		// 清除图片src属性,使图片再下一次新增时不显示
		angular.element("#advertisementPicture").removeAttr("src");
		// 保存按钮生效
		$scope.isDisabled = false;
		// 谷歌不能重复选择相同图片
		var obj = document.getElementById('oneInput'); 
		obj.outerHTML=obj.outerHTML;
	}
	
	// 加载图片
	$scope.img_upload = function(files){
		// 谷歌不能重复选择相同图片
		var obj = document.getElementById('oneInput'); 
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
		// 拿到图片上传DOM对象
		var obj = document.getElementById('oneInput') ;
		if(!filer){ // 图片格式判断
			// 谷歌兼容(不能选择之前选择的图片)
			obj.outerHTML=obj.outerHTML;
			alert("仅可上传(gif,jpg,jpeg,png)类型图片，请重新选择文件上传！");
			return false;
		}else if (files[0].size / 1024 > 2048) { // 图片大小限定
			// 谷歌兼容(不能选择之前选择的图片)
			obj.outerHTML=obj.outerHTML;
			alert("图片的大小超过2MB，请重新选择文件上传！");
			return false;
		} else { // 选择验证通过
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
					// 获得选择后的路径缓存
					$scope.addAdvertisement.imageUrl = $scope.item.pictureAddress;
				});
			};
		};
	}
	
});

// 拿到图片的真实尺寸
function getImgNaturalDimensions(img, callback) {
	// 定义图片的宽高;
    var nWidth ;
    var nHeight;
    // 拿到真是宽高返回数组
    nWidth = img.naturalWidth;
    nHeight = img.naturalHeight;
    return [nWidth,nHeight];
}

//加载其他页面
function loadSecMenu(url) {
	window.location.href = url;
}

//窗体加载完事件对选择模块背景颜色的改变
onload=function(){
	angular.element(".advertisement").css("background-color","#99c731");
}