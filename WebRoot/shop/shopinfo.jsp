<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>资讯</title>
<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> 文件可能有错
	可以直接连接到网上，如下-->
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/index.css">
<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="../layui/css/layui.css">
<script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body>
	<nav class="nav navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="index.jsp" class="navbar-brand" style="padding:0;"> <img
					src="img/3.jpg" style="height:50px;width:80px;margin:auto;">
					<!-- <span>项目实战</span> -->
				</a>
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse">
					<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<ul class="nav navbar-nav navbar-right" style="margin-top:0px;">
					<li class="active"><a href="index.jsp"><span
							class="glyphicon glyphicon-home">首页</span></a></li>
					<li><c:if test="${sessionScope.SessionUser!=null }">
							<a href="user/index.jsp"><span
								class="glyphicon glyphicon-list">${SessionUser.user_name
									}</span></a>
						</c:if> <c:if test="${sessionScope.SessionUser==null }">
							<a href="user/login.jsp"><span
								class="glyphicon glyphicon-list">请登录</span></a>
						</c:if></li>
					<li><c:if test="${sessionScope.SessionUserHaveShop == 0 }">
							<a href="about.html"><span
								class="glyphicon glyphicon-question-sign">申请开店</span></a>
						</c:if> <c:if
							test="${sessionScope.SessionUserHaveShop != 1&&sessionScope.SessionUserHaveShop != null }">
							<a href="about.html"><span
								class="glyphicon glyphicon-question-sign">管理店铺</span></a>
						</c:if> <c:if test="${sessionScope.SessionUserHaveShop == null }">

						</c:if></li>
					<li><a href="user/logout.action"><span
							class="glyphicon glyphicon-question-sign">登出</span></a></li>
				</ul>
			</div>
		</div>
		</div>
	</nav>
	<style>
</style>
	<div class="jumbotron">
		<div class="container">
			<hgroup>
				<h1>案例</h1>
				<h4>是否打算方法时的观点是vs的刚刚发生的风格 都是；</h4>
			</hgroup>
		</div>
	</div>
	<div id="case">
		<div class="container">
			<div class="row" id="shop_goods_list">
				<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
					<div class="thumbnail">
						<img src="../img/case/2.jpg" alt="">
					</div>
					<div class="caption">
						<h4>地方撒的发三</h4>
						<p>东方时空粉丝而广东省水电费是大哥</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer id="footer" class="text-muted">
		<div class="container">
			<p>企业培训|合作事宜|版权控诉</p>
			<p>苏ICP 备12345678.@2009-2016 瓢dasdasdasf 是的法式咖啡</p>
		</div>
	</footer>
	<script
		src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/shop/shop.js"></script>
</body>
</html>