/**
* 分页控件
*/

(function(window, angular) {
	
	'use strict';

var dTableModule = angular.module('dPagination', ['ng']).
                   directive('spfoodPagination', $Pagination),
    $routeMinErr = angular.$$minErr('dPagination');

function $Pagination(){
	return {
        restrict: 'EA',
        template:'<div class="sp-pagination" style="clear:both;">'+
      	  '<p class="sp-page-show">'+
      	  '  共&nbsp;<span ng-bind="conf.total"></span>&nbsp;条记录'+
      	  '</p>'+
      	  '<div class="sp-page-num">'+
      	  '<ul>'+
      	    '<li><a class="sp-a-first" style="cursor:pointer;" ng-click="goPage(\'first\')">首页</a></li>'+
      	    '<li ng-repeat="item in pageList track by $index">'+
      	    	'<a ng-bind="item" style="cursor:pointer;" class="sp-a-normal" ng-class="{\'sp-a-normal\':item!=conf.pageNum,\'sp-a-active\':item==conf.pageNum}" ng-click="changePageNum(item)"></a>'+
      	    '</li>' +
      		'<li><a class="sp-a-end" ng-click="goPage(\'last\')" style="cursor:pointer;">尾页</a></li>'+
      		'<li class="sp-li-center"><span style="display:inline-block;">跳转到<input ng-model="pageNo" ng-keypress="pageInputKeypress($event)" type="text" style="display:inline-block;"/>页</span></li>'+
      	  '</ul>'+
      	  '</div>'+
      	'</div>',
        replace: true,
        scope: {
            conf: '='
        },
        link: function(scope, element, attrs){
        	
            scope.changePageNum = function(item){
                if(item == '...'){
                    return;
                }else{
                    scope.conf.pageNum = item;
                }
                scope.conf.onChange(item);
                getPagination();
            };
            
            scope.goPage = function(item){
                if(item == 'last'){
                	var pageNum = getLastPage();
                	scope.changePageNum(pageNum);
                }else if(item == 'first'){
                	var pageNum = getFirstPage();
                	if(pageNum <= 0) return;
                	scope.changePageNum(pageNum);
                }
            };
            
            scope.pageInputKeypress = function(e){
                var keycode = window.event ? e.keyCode: e.which;
                if(keycode==13){
                	var pageNo = parseInt(scope.pageNo);
					if(isNaN(pageNo)){
						e.preventDefault();
						alert("页码输入格式错误，请重新输入!");
						var ele = e.target || e.srcElement;
						ele.value = "";
						return;
					}
                	var pageNumbers = getLastPage();
                	if(pageNo > pageNumbers || pageNo < 1){
                		e.preventDefault();
                		alert("页码已超出范围，请重新输入!");
                		return;
                	}
                	scope.changePageNum(pageNo);
                }else if(keycode >= 48 && keycode <= 57){
                	;
                }else if(keycode == 0 || keycode == 8 ||
					(37<= keycode && keycode <= 40)){
                	;
                }else{
                	e.preventDefault();
                }
          };
            
            function getFirstPage(){
            	var pageNumbers = getLastPage();
            	if(pageNumbers <= 0){
            		return 0;
            	}else{
            		return 1;
            	}
            }
            
            function getLastPage(){
            	var total = parseInt(scope.conf.total);
            	var pageNum = parseInt(scope.conf.pageNum);
            	var pageSize = parseInt(scope.conf.pageSize);
            	if(pageSize <= 0 ){pageSize = 1;}
            	var pageNumbers = (total % pageSize == 0) ? (total / pageSize) :(Math.ceil(total / pageSize));
            	return pageNumbers;
            }

          
            function getPagination(){
            	
            	var pageNumbers = getLastPage();
            	if(scope.conf.pageNum > pageNumbers){scope.conf.pageNum = pageNumbers;}
                
            	scope.pageList = [];
                
                if(pageNumbers <= 6){
                	for(var i=1; i<=pageNumbers; i++){
                		scope.pageList.push(i);
                	}
	          	}else{
	          		
	          		for(var i=1; i<=3;i++){
	          			scope.pageList.push(i);
	          		}
					
	          		var middleStart = parseInt(scope.conf.pageNum) - 1;
		      		var middleEnd = parseInt(scope.conf.pageNum) + 1;
		      		
		      		if(middleStart - 3 > 1){
		      			scope.pageList.push("...");
		      		}
		      		
		      		for(var i=middleStart; i<=middleEnd; i++){
		      			if(i<=3 || i>= pageNumbers-2) continue;
		      			scope.pageList.push(i);
		      		}
		      		
		      		if(pageNumbers - 2 - middleEnd > 1){
		      			scope.pageList.push("...");
		      		}
		      		
		      		for(var i=pageNumbers-2; i <= pageNumbers;i++){
		      			scope.pageList.push(i);
		      		}
	          		
	          	}
                
            }
			
            var list = ['total','pageSize','pageNum'];
			for(var i=0; i<list.length; i++){
				scope.$watch('conf.'+list[i], getPagination);
			}

        }
        
	};
	
} 

})(window, window.angular);