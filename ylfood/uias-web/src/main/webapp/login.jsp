<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = application.getInitParameter("static-file"); %>
<html  lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="Generator" content="Isidore Han®">
<meta name="Author" content="Isidore Han">
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>银犁食品互联网平台统一身份认证平台</title>
<link rel="stylesheet" href="<%=path%>/main-page/css/login.css">
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath}" id="webUrl"/>
<div class='sp-bg'></div>

<div class='sp-login-div sp-login-div-top sp-hide'  ng-app="loginApp" ng-controller="loginCtrl">
    <div class='sp-login-content'>
        <form>
            <p class='sp-login-title'>用户登录</p>

            <p class='sp-p-normal'><input type="text" ng-model="username" name="username" class="sp-input-text sp-input-user-bg sp-input-text-width" placeholder="用户名"/>
            <span style="color: red;" ng-show="errorShow1" ng-cloak>{{errorMessage1}}</span></p>

            <p class='sp-p-normal'><input type="password" ng-model="password" name="password" class="sp-input-text sp-input-lock-bg sp-input-text-width" placeholder="密码"/>
            <span style="color: red;" ng-show="errorShow2" ng-cloak>{{errorMessage2}}</span>
            </p>

            <p class='sp-login-qr'>
            <span><input type="text" ng-model="confromtext" name="confromtext" ng-keyup="enterEvent($event)" ng-change="initCheck()" class="sp-input-qr" style="width:29%;" placeholder="请输入校验码..."/>
            </span>
            <span><img class="sp-login-qr-img" src="{{imageUrl}}" /></span>
            <span><input type="button"  name="confromed" class="sp-chg-btn" ng-click="changeImage()" value="换一张"/></span>
                    <span style="color: red;" ng-show="errorShow" ng-cloak>{{errorMessage}}</span></p>

            <p class='sp-login-comments'><span><input type="checkbox" checked="checked" ng-model="rememberMe" value="true" class="sp-input-chkbox"/></span><span
                    style='vertical-align: text-top;'>记住用户名和密码</span></p>

            <p class='sp-login-btn'><input type="button" value="登录" class="sp-input-btn" ng-click="login()"/></p>
        </form>
    </div>
</div>
</body>
<script src="<%=path%>/js/lib/jquery.min.js"></script>
<script src="<%=path%>/js/lib/angular-1.5.9.js"></script>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
</html>