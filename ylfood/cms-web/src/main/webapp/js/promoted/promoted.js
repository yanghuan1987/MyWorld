var module = angular.module('promotedModule', ['dPagination']);
module.controller('promotedCtrl', function($scope, $http) {
	
	$http.post(
			"AllPos"
	).success(function(msg){
		$scope.proList=msg.result;
		//默认下拉框选中第一条数据
		$scope.pos=msg.result[0].id.toString();
		//查询下拉框选中页面的推荐商品
		promotion($scope.pos);
	})
	//指定推荐位的推荐商品
	 function promotion(commPromotePos){
		
		$http.post(
				"promotion?commPromotePos="+commPromotePos
		).success(function(msg){
			//当前配置页最大显示条数
			var maxSlots;
			
			for(var i=0;i<$scope.proList.length;i++){
				if(commPromotePos==$scope.proList[i].id){
					maxSlots=$scope.proList[i].maxSlots;
				}
			}
			//实际数据小于改页最大显示条数，添加空对象
			if(msg.result.length<maxSlots){
				var em=maxSlots-msg.result.length;
				for(var i=0;i<em;i++){
					msg.result.push({});
				}
			}
			//接受该页所有推荐商品数据
			$scope.commodityList=msg.result;
		})
	}
	//定义分页查询条件
	$scope.pageNum=1;//初始化当前页码(第一次进入分页查询页面默认查第一页)
	$scope.pageSize;//每页条数
	$scope.total;//总条数
	
	//封装分页信息
	$scope.pagination = {
		pageNum: $scope.pageNum,
		pageSize: $scope.pageSize,
		total: $scope.total,
		//当前页码改变执行查询
        onChange: function(pageNum){
        	allCommodity(pageNum)
        }
    };

	//商品列表
	function allCommodity(pageNum){
		
		//商品名及商品编码查询
		var commodity={
				commodityCode:$scope.commodityCode,
				commodityName:$scope.commodityName
		}
		$http.post(
				"allCommodity?pageNum="+pageNum,
				commodity
		).success(function(msg){
			//查询失败打印后台提示信息
			if(msg.status=="error"){
				//分页信息
				$scope.pagination.total = 0;
				$scope.pagination.pageNum= 0;
				$scope.pagination.pageSize= 0;
				tip(msg.messages);
				$scope.pageInfo=msg.result;
			}else{
				$scope.pageInfo=msg.result;//商品信息
				//分页信息
				$scope.pagination.total = msg.result.total;
				$scope.pagination.pageNum= msg.result.pageNum;
				$scope.pagination.pageSize= msg.result.pageSize;
			}
			
		})
	}
	//切换下拉框
	$scope.change = function(){
		promotion($scope.pos);
	}
	//编辑按钮
	$scope.click=function(index){
		//清空查询条件
		$scope.commodityCode="";
		$scope.commodityName="";
		allCommodity($scope.pageNum);//执行查询
		$scope.commodity=$scope.commodityList[index];//当前选中的商品
		$scope.order=$scope.commodity.displayOrder;//当前选中商品的排序值
		$scope.tempIndex=index;//首次选中商品的索引
		$scope.tempCommodity=$scope.commodity;//首次选中的商品信息
		showPopup('popUpHg',true);//启用弹出框
	}
	//取消按钮
	$scope.cancel=function(){
		exitPop('popUpHg');//关闭弹出框
	}
	//提示框
	function tip(tip){
		$scope.tip=tip;
		showPopHint("popHint",true);
	}
	//验证输入的排序是否正确
	function checkOrder(order){
		if(order==undefined){
			tip("请输入正确的排序值");
			return false;
		}
		for(var j=0;j<$scope.commodityList.length;j++){
			if($scope.commodityList[j].displayOrder==order){
				tip("排序位置不能重复");
				return false;
			}
		}
		return true;
	}
	//封装推荐商品
	function encapsulation(commPromotePos,proCommodity,order){
		var promotedCommodity={
				id:$scope.tempCommodity.id,
				commPromotePos:commPromotePos,
				commodityCode:proCommodity.commodityCode,
				imageUrl:proCommodity.imageUrl,
				productCode:proCommodity.product.productCode,
				commodityName:proCommodity.commodityName,
				commoditySpec:proCommodity.commodityWeight+$scope.commodity.commodityWeightUnit,
				displayOrder:order
		}
		return promotedCommodity;
	}
	//验证当前选中商品是否已被加入到推荐商品列表
	function checkRepeat(){
		var isRepeat=false;
		if($scope.commodityList[$scope.tempIndex].commodityCode!=$scope.commodity.commodityCode){
			for (var i = 0; i < $scope.commodityList.length;i++) {
				if($scope.commodityList[i].commodityCode==$scope.commodity.commodityCode){
					isRepeat= true;
				}
			}	
		}
		return isRepeat;
	}
	
	//保存按钮
	$scope.save=function(order){
		if(checkRepeat()){//验证推荐商品是否已存在
			tip("推荐商品不能重复")
		}else{
			if($scope.commodity.id==undefined){
				tip("请选择正确的商品")
			}else{
				var isNewCommodity=true;//标记改操作是否为修改排序
				var isAllowed;//验证标记推荐商品排序是否符合规则
				//遍历推荐商品信息与提交的数据对比，判断属于那种操作(新增、修改商品、修改排序)
				for(var i=0;i<$scope.commodityList.length;i++){
					//如果选中商品的ID与商品列表中某个商品ID相同
					if($scope.commodityList[i].commodityCode==$scope.commodity.commodityCode){
						//如果排序值相同
						if($scope.commodityList[i].displayOrder==order){
							tip("未做任何修改");
						}
						//排序值不同，修改排序
						else{
							isAllowed=checkOrder(order);
							if(isAllowed){
								$scope.commodityList[i].displayOrder=order;//修改绑定数据
								$http.post(
										"changOrder?commdityId="+$scope.commodityList[i].id+"&newOrder="+order
								).success(function(msg){
									//打印提示信息
									tip(msg.messages);
									exitPop('popUpHg');
								})
							}
						}
						isNewCommodity=false;
					}
				}
				//新增或修改推荐商品
				if(isNewCommodity){
					//如果首次选中为空，执行新增
					if($scope.tempCommodity.id==null){
						isAllowed=checkOrder(order);
						if(isAllowed){
							var proCommodity=encapsulation($scope.pos,$scope.commodity,order);
							$http.post(
									"add",
									proCommodity
							).success(function(msg){
								if(msg.status=="success"){
									$scope.commodity.displayOrder=order;
									for(var i=0;i<$scope.commodityList.length;i++){
										if($scope.commodityList[i].id==undefined){
											$scope.commodityList[i]=msg.result;
											break;
										}
									}
									exitPop('popUpHg');
								}
								tip(msg.messages)
							})
						}
					}
					//商品编辑
					else{
						if(order==undefined){
							tip("请输入正确的排序值");
						}
						else{
							//如果当前商品与首次选中商品排序不相同
							if($scope.tempCommodity.displayOrder!=order){
								isAllowed=checkOrder(order);
							}else{
								isAllowed=true;
							}
							
							if(isAllowed){
								var proCommodity=encapsulation($scope.pos,$scope.commodity,order);
								$http.post(
										"update",
										proCommodity
								).success(function(msg){
									if(msg.status=="success"){
										$scope.commodityList[$scope.tempIndex]=proCommodity;//更改绑定数据
										exitPop('popUpHg');
									}
									tip(msg.messages);
								})
							}
						}
					}
				}
			}
		}
		
	}
	//选择按钮
	$scope.checkClick=function(index){
		$scope.commodity=$scope.pageInfo.result[index];//更改当前选中商品数据
	}

	//查询按钮
	$scope.select=function(){
		allCommodity($scope.pageNum)
	}
	
})
//DOM元素加载完毕更改对应菜单背景颜色
onload=function(){
	angular.element(".proCommodity").css("background-color","#99c731");
}
//跳转到指定页面
function loadSecMenu(url) {
		window.location.href = url;
}