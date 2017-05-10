<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = application.getInitParameter ("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>CMS-内容管理系统</title>
<link rel="SHORTCUT ICON" href="<%=path%>/img/cms/favicon.ico" />
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link href="<%=path%>/css/css.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/table.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/form.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/menu.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/head.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/layout.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/css/pagination.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<jsp:include page="./common/head.jsp"></jsp:include>

	<div class="row">
		<jsp:include page="./common/menu.jsp"></jsp:include>
		<div class="sp-menu-right">

			

		</div>
	</div>
	<jsp:include page="./common/bottom.jsp"></jsp:include>
	<script>
		function loadSecMenu(url) {
			window.location.href = url;
		}
	</script>
</body>

</html>