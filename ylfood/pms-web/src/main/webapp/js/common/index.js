var app = angular.module('pmsMainApp',[]);
app.controller('pmsMainCtrl', function($scope,$http) {
	var serverPath = document.getElementById('server').value+"/uias-web/login/loginOut";
	//注销
	$scope.loginOut = function() {
		window.location.href = serverPath;
    };
});
//unauthorized.jsp 非授权用户跳转
function onload() {
	setTimeout(function(){
		window.location.href = "${pageContext.request.contextPath}/";
		},5000)
}
function loadSecMenu(url) {
	window.location.href = url;
}
