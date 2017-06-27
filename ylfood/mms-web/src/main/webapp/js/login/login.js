var app = angular.module('loginApp', []);
app.controller('loginCtrl', function($scope,$http) {
	
	$scope.imageUrl = $("#webUrl").val() + "/mms/validatecode/creatvalidatecode";
	//登录
	
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.login();
        }
    }
    
	$scope.login = function() {
		
		if($scope.username == undefined || $scope.username.length == 0){
			$scope.errorShow1 = true;
			$scope.errorMessage1 = "用户名不能为空";
			return false;
		}
		
		if($scope.password == undefined || $scope.password.length == 0){
			$scope.errorShow2 = true;
			$scope.errorMessage2 = "密码不能为空";
			return false;
		}
		
		
		if($scope.confromtext == undefined){
			$scope.errorShow = true;
			$scope.errorMessage = "验证码不能为空";
			return false;
		}
		
		if($scope.confromtext.length != 4){
			$scope.errorShow = true;
			$scope.errorMessage = "验证码不正确";
			return false;
		}
		
		var response = $http.post("./mms/validatecode/checkvalidatecode?code="+$scope.confromtext);
		response.success(function(data, status, headers, config) {
			if(data != null && data != 'success'){
				$scope.errorShow = true;
				$scope.errorMessage = data;
				return false;
			}
		
			$scope.employeeDTO = {account:$scope.username,password:$scope.password,rememberMe:$scope.rememberMe};
		    var response = $http.post("./mms/login/login", $scope.employeeDTO);
			response.success(function(data, status, headers, config) {
				if(data != null && data != 'success'){
					var errorcode = data;
					if(errorcode == 1){
						$scope.errorShow1 = true;
						$scope.errorMessage1 = "您输入的用户名不存在"; 
					}else if(errorcode == 2){
						$scope.errorShow2 = true;
						$scope.errorMessage2 = "您输入的密码不正确"; 
					}else{
						$scope.errorShow = true;
						$scope.errorMessage = "其他错误"; 
					}
				}else{
					window.location = "./mms/login/main";
				}
			});
			response.error(function(data, status, headers, config) {
			});
		});
    };
    
    $scope.changeImage = function() {
    	$scope.imageUrl = $("#webUrl").val() + 
    		"/mms/validatecode/creatvalidatecode?"+Math.random();
    };
    
    
    
    $scope.initCheck = function(){
    	$scope.errorShow = false;
    	$scope.errorShow1 = false;
    	$scope.errorShow2 = false;
    }
    
});
