<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
  <title>CMS</title>
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon" href="" type="image/x-icon"/>
<script>
function onload() {
	setTimeout(function(){
		window.location.href = "<%=basePath%>/uias-web/";
		},2000)
}
</script>
</head>
<body onload="onload()">
    
<h1>当前操作未授权</h1>
</body>
</html>