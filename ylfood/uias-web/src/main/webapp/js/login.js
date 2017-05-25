var app = angular.module('loginApp', []);
app.controller('loginCtrl', function($scope,$http) {
	
	$scope.imageUrl = $("#webUrl").val() + "/validatecode/creatvalidatecode";
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
		
		var response = $http.post("./validatecode/checkvalidatecode?code="+$scope.confromtext);
		response.success(function(data, status, headers, config) {
			if(data != null && data.status != 'success'){
				$scope.errorShow = true;
				$scope.errorMessage = data.messages;
				return false;
			}
		
			$scope.employeeDTO = {account:$scope.username,password:new Base64().encode($scope.password),rememberMe:$scope.rememberMe};
		    var response = $http.post("./login/login", $scope.employeeDTO);
			response.success(function(data, status, headers, config) {
				if(data != null && data.status != 'success'){
					$scope.errorMessage = data.messages; 
					$scope.errorShow = true;
				}else{
					window.location.href = "./main/page";
				}
			});
			response.error(function(data, status, headers, config) {
			});
		});
    };
    
  //注销
	$scope.loginOut = function() {
		window.location.href = "../login/loginOut";
    };
    
    $scope.changeImage = function() {
    	$scope.imageUrl = $("#webUrl").val() + 
    		"/validatecode/creatvalidatecode?"+Math.random();
    };
    
    
    
    $scope.initCheck = function(){
    	$scope.errorShow = false;
    	$scope.errorShow1 = false;
    	$scope.errorShow2 = false;
    }
    
});
