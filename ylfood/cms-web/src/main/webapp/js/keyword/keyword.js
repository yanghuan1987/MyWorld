var module = angular.module('keywordModule', []);
module.controller('keywordCtrl', function($scope, $http) {
	// 获取关键字列表
	$http({
        method:"GET",
        url:"list"
    }).success(function(data) {
    	if(data.status == 'success'){
    		// 拿到数据赋值给页面列表,缓存
    		$scope.keywordList = data.result;
    		// 填满九条数据用于前台显示
    		for (i = 0; i < 9; i++) {
    			// 如果为空就放入空对象
    			if (!$scope.keywordList[i]) {
    				$scope.keywordList[i] = {};
    			} else {
    				// 如果关键字存在转义<>
    				if ($scope.keywordList[i].keyword) {
    					$scope.keywordList[i].keyword = $scope.keywordList[i].keyword.replace(/&lt;/g, "<").replace(/&gt;/g, ">");
    				}
    			}
    		}
    		
    		// 在展示数据的时候,根据后的排序顺序有小到大展示
    		for (i = $scope.keywordList.length; i > 0; i--) {
    			// 定义一个缓存排序的变量并赋值
    			var displayOrder = $scope.keywordList[i-1].displayOrder;
    			// 如果排序存在就和数据库的排序值交换
				if (displayOrder) {
					// 中间变量
    				var temp = $scope.keywordList[i-1];
    				$scope.keywordList[i-1] = $scope.keywordList[displayOrder];
    				$scope.keywordList[displayOrder] = temp;
    			}
    		}
    		// 复制一份用于取消保留原来数据
    		$scope.tempList = angular.copy($scope.keywordList);
    	}
    	if(data.status == 'error'){
    		alert(data.messages);//传递错误提示信息
    	}
    }).error(function() {
    });
	

	// 编辑按钮
	$scope.edit = function(index){
		// 点击编辑安扭拿到去空格保存后的数据
		$scope.keywordList[index] = angular.copy($scope.tempList[index]);
		// 显示指定索引的保存和取消按钮
		show(index);
		
	}
	
	// 保存按钮
	$scope.save = function(index) {
		// 拼接关键字字符串变量
		var keyword = "#keyword" + index;
		// 搜索框关键字不能为空
		if (index == 0) {
			if( $scope.keywordList[index].keyword == "" || null == $scope.keywordList[index].keyword){
				$scope.errorMessage = "搜索框文字不能为空！";
				showPopHint('keywordPopHint');//弹出提示框
				return false;
			}
		}
		// 判断关键字不能大于10个字符,若大于,不能提交
		if (!(angular.element(keyword).val().trim().length > 10)) {
			// 对前台传过来的< 转换为&lt;保存到数据库
			
			// 判断传过来的对象是否为空,为空就重新赋值(新增)
			if (!$scope.keywordList[index].id) {
				// 判断是否是搜索框,搜索框数据和非搜索框赋值分开
				if (index == 0) {
					$scope.update = {keyword:angular.element(keyword).val().replace(/</g, "&lt;").replace(/>/g, "&gt;"),
							isDefault:true,
							displayOrder:index};
				} else {
					$scope.update = {keyword:angular.element(keyword).val().replace(/</g, "&lt;").replace(/>/g, "&gt;"),
							isDefault:false,
							displayOrder:index};
				}
			} else { // 编辑状态
				// 存放后台传过来数据中的Id
				var id = $scope.keywordList[index].id;
				// 存放后台传过来数据中的是否默认值
				var isDefault = $scope.keywordList[index].isDefault;
				// 存放后台传过来数据中的排序值
				var displayOrder = $scope.keywordList[index].displayOrder;
				
				//更新对象赋值
				$scope.update = {id:$scope.keywordList[index].id,
						keyword:angular.element(keyword).val().replace(/</g, "&lt;").replace(/>/g, "&gt;"),
						isDefault:$scope.keywordList[index].isDefault,
						displayOrder:$scope.keywordList[index].displayOrder};
			}
			// 发送请求保存数据
			var resp = $http.post("update", $scope.update);
			resp.success(function(data,status,headers,config){
				if(data.status == 'success'){
					if (data.result) {
						// 更新保存成功后的数据
						$scope.keywordList[index] = data.result;
					}
					// 更新数据后再复制一份
					$scope.tempList[index] = angular.copy($scope.keywordList[index]);
					// 隐藏保存和取消按钮
					hidden(index);
				}else if(data.status == 'error'){
					$scope.errorMessage = data.messages;
					showPopHint('keywordPopHint');//弹出提示框
				}
			}).error(function(){
				
			});
			
		}
	}
	
	//取消按钮
	$scope.cancel = function(index){
		// 回到之前的数据
		$scope.keywordList[index] = angular.copy($scope.tempList[index]);
		// 隐藏保存和取消按钮
		hidden(index);
		// 验证信息不显示
		checkHidden(index);
	}
	
	// 验证数据长度不超过10个字符串
	$scope.check = function(index){
		var keyword = "#keyword" + index;
		// 超过十个显示提示信息,不超过不显示
		if (angular.element(keyword).val().trim().length > 10) {
			checkShow(index);
		} else {
			checkHidden(index);
		}
	}
	
	// 隐藏保存和取消按钮
	function hidden(index) {
		switch (index) {
		case 0:
			$scope.isedit0 = false;
			break;
		case 1:
			$scope.isedit1 = false;
			break;
		case 2:
			$scope.isedit2 = false;
			break;
		case 3:
			$scope.isedit3 = false;
			break;
		case 4:
			$scope.isedit4 = false;
			break;
		case 5:
			$scope.isedit5 = false;
			break;
		case 6:
			$scope.isedit6 = false;
			break;
		case 7:
			$scope.isedit7 = false;
			break;
		case 8:
			$scope.isedit8 = false;
			break;
		}
	}
	
	// 显示保存和取消按钮
	function show(index) {
		switch (index) {
		case 0:
			$scope.isedit0 = true;
			break;
		case 1:
			$scope.isedit1 = true;
			break;
		case 2:
			$scope.isedit2 = true;
			break;
		case 3:
			$scope.isedit3 = true;
			break;
		case 4:
			$scope.isedit4 = true;
			break;
		case 5:
			$scope.isedit5 = true;
			break;
		case 6:
			$scope.isedit6 = true;
			break;
		case 7:
			$scope.isedit7 = true;
			break;
		case 8:
			$scope.isedit8 = true;
			break;
		}
	}
	
	// 显示核对提示信息
	function checkShow(index) {
		switch (index) {
		case 0:
			$scope.check0 = true;
			break;
		case 1:
			$scope.check1 = true;
			break;
		case 2:
			$scope.check2 = true;
			break;
		case 3:
			$scope.check3 = true;
			break;
		case 4:
			$scope.check4 = true;
			break;
		case 5:
			$scope.check5 = true;
			break;
		case 6:
			$scope.check6 = true;
			break;
		case 7:
			$scope.check7 = true;
			break;
		case 8:
			$scope.check8 = true;
			break;
		}
	}
	
	// 隐藏核对提示信息
	function checkHidden(index) {
		switch (index) {
		case 0:
			$scope.check0 = false;
			break;
		case 1:
			$scope.check1 = false;
			break;
		case 2:
			$scope.check2 = false;
			break;
		case 3:
			$scope.check3 = false;
			break;
		case 4:
			$scope.check4 = false;
			break;
		case 5:
			$scope.check5 = false;
			break;
		case 6:
			$scope.check6 = false;
			break;
		case 7:
			$scope.check7 = false;
			break;
		case 8:
			$scope.check8 = false;
			break;
		}
	}
	
});

// 加载其他页面
function loadSecMenu(url) {
	window.location.href = url;
}

//窗体加载完事件对选择模块背景颜色的改变
onload=function(){
	angular.element(".searchKeyword").css("background-color","#99c731");
}