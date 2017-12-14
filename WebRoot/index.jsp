<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!
DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>在线商城</title>
<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> 文件可能有错
	可以直接连接到网上，如下-->
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/index.css">
<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- 第一个参数：width=device-width，以设备的宽度为宽度。第二个参数：initial-scale=1,百分百缩放。第三个参数：最大的缩放，也是百分百。第四个参数：禁止用户缩放 。做响应式时，加上这句话。-->
<link
	href="http://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<style>
.modal-backdrop {
	z-index: -1;
}

#search {
	position: absolute;
	width: 680px;
	margin-top: 15px;
	clear: both;
	left: 50%;
	transform: translateX(-50%);
}

#search-wrap {
	position: absolute;
	margin-top: -29px; 　 <!-- 达到覆盖图片轮播底部的作用 --> 　 left : 50%; 　　　　　　 <!--
	这部分是搜索框居中显示的关键。 --> margin-left : -130px;
	width: 260px;
	　　　　 height: 36px;
	　　　　 border-radius: 18px;
	box-shadow: 0 0 3px rgba(0, 0, 0, .14);
	left: 60px;
	background-color: #FFF;
}

#search-wrap .search-content {
	box-shadow: none;
	border: 0 none;
	font-family: "Hiragino Sans GB", "Microsoft YaHei",
		"WenQuanYi Micro Hei", Arial, Helvetica, sans-serif;
	margin-top: 3px;
	margin-left: 18px;
	width: 190px;
	height: 30px;
}

#search-wrap .search-btn {
	border: 0px;
	float: right;
	width: 46px;
	height: 28px;
	border-radius: 14px;
	background-color: #2980b9;
	color: #FFF;
	text-align: center;
	line-height: 28px;
	margin-left: 100px;
	margin-top: -30px;
	z-index: 10000;
}
</style>

<link rel="stylesheet" href="layui/css/layui.css">
<script type="text/javascript" src="layui/layui.js"></script>
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

				<div id="search">
					<select name="city" lay-verify="" id="ser_type">
						<option value="店铺" >店铺</option>
						<option value="商品">商品</option>
					</select>
					<div id="search-wrap">
						<input class="search-content" type="text" placeholder="输入搜索内容"
							name="word" id="keywords">
						<button class="search-btn" id="ser">搜索</button>
<ul id="result">
						</ul>
					</div>
				</div>
				<ul class="nav navbar-nav navbar-right" style="margin-top:0px;">
					<li class="active"><a href="index.jsp"><span
							class="glyphicon glyphicon-home">首页</span></a></li>
					<li><c:if test="${sessionScope.SessionUser!=null }">
							<a href="user/index.jsp"><span
								class="glyphicon glyphicon-list">${SessionUser.user_name }</span></a>
						</c:if> <c:if test="${sessionScope.SessionUser==null }">
							<a href="user/login.jsp"><span
								class="glyphicon glyphicon-list">请登录</span></a>
						</c:if></li>
					<li><c:if test="${sessionScope.SessionUserHaveShop == -1 }">
							<a><span data-toggle="modal" data-target="#myModal"
								class="glyphicon glyphicon-question-sign">申请开店</span></a>
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">入驻申请</h4>
										</div>
										<div class="modal-body">
											<div class="form-horizontal">
												<div class="form-group">
													<label for="inputEmail3" class="col-sm-2 control-label">店铺名称</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="shop_name"
															placeholder="店铺名称">
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-2 control-label">店铺简介</label>
													<div class="col-sm-10">
														<textarea type="textarea" class="form-control"
															id="shop_describe" placeholder="店铺简介"></textarea>
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-2 control-label">店铺地址</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="shop_address"
															placeholder="店铺地址">
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-2 control-label">店铺类型</label>
													<div class="col-sm-10">
														<select class="form-control" id="shop_type">
															<option>1</option>
															<option>2</option>
															<option>3</option>
															<option>4</option>
															<option>5</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-2 control-label">联系电话</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="shop_phone"
															placeholder="联系电话">
													</div>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" id="right">确认</button>
											<button type="button" class="btn btn-primary" id="cancel">取消</button>
										</div>
									</div>
								</div>
							</div>
							<script type="text/javascript">
								$("#cancel").click(function() {
									$('#myModal').modal('hide');
								})
								$("#right").click(function() {
									//console.log(1);
									layui.use('layer', function() {
										var shop_name = $("#shop_name").val();
										var shop_describe = $("#shop_describe").val();
										var shop_address = $("#shop_address").val();
										var shop_phone = $("#shop_phone").val();
										var shop_type = $("#shop_type").val();
										console.log("用户名：" + shop_name);
										$.ajax({
											type : "POST",
											url : "shop/sq.action",
											async : true,
											data : {
												"shop_name" : shop_name,
												"shop_describe" : shop_describe,
												"shop_address" : shop_address,
												"shop_phone" : shop_phone,
												"shop_type" : shop_type
											},
											dataType : "json",
											success : function(data) {
												layer.closeAll('loading');
												console.log(data);
												if (data == -1) {
													layer.msg("商铺名已被占用");
												} else if (data == 0) {
													alert("申请失败，请重试");
												} else {
													layer.msg("申请成功，前往店铺管理页面....");
													$('#myModal').modal('hide');
													window.location.reload(true);
												}
											},
											beforeSend : function(index) {
												layer.load();
											}
										});
									});
								})
							</script>
						</c:if> <c:if test="${sessionScope.SessionUserHaveShop == 2 }">
							<a><span data-toggle="modal" data-target="#myModal"
								class="glyphicon glyphicon-question-sign">申请未通过</span></a>
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">入驻申请</h4>
										</div>
										<div class="modal-body">
											<div class="form-horizontal">
												<div class="form-group">
													<label for="inputEmail3" class="col-sm-2 control-label">店铺名称</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="shop_name"
															placeholder="店铺名称">
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-2 control-label">店铺简介</label>
													<div class="col-sm-10">
														<textarea type="textarea" class="form-control"
															id="shop_describe" placeholder="店铺简介"></textarea>
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-2 control-label">店铺地址</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="shop_address"
															placeholder="店铺地址">
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-2 control-label">店铺类型</label>
													<div class="col-sm-10">
														<select class="form-control" id="shop_type">
															<option>1</option>
															<option>2</option>
															<option>3</option>
															<option>4</option>
															<option>5</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-2 control-label">联系电话</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="shop_phone"
															placeholder="联系电话">
													</div>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" id="right">确认</button>
											<button type="button" class="btn btn-primary" id="cancel">取消</button>
										</div>
									</div>
								</div>
							</div>
							<script type="text/javascript">
								$("#cancel").click(function() {
									$('#myModal').modal('hide');
								})
								$("#right").click(function() {
									//console.log(1);
									layui.use('layer', function() {
										var shop_name = $("#shop_name").val();
										var shop_describe = $("#shop_describe").val();
										var shop_address = $("#shop_address").val();
										var shop_phone = $("#shop_phone").val();
										var shop_type = $("#shop_type").val();
										console.log("用户名：" + shop_name);
										$.ajax({
											type : "POST",
											url : "shop/sq.action",
											async : true,
											data : {
												"shop_name" : shop_name,
												"shop_describe" : shop_describe,
												"shop_address" : shop_address,
												"shop_phone" : shop_phone,
												"shop_type" : shop_type
											},
											dataType : "json",
											success : function(data) {
												layer.closeAll('loading');
												console.log(data);
												if (data == -1) {
													layer.msg("商铺名已被占用");
												} else if (data == 0) {
													alert("申请失败，请重试");
												} else {
													layer.msg("申请成功，前往店铺管理页面....");
													$('#myModal').modal('hide');
													window.location.reload(true);
												}
											},
											beforeSend : function(index) {
												layer.load();
											}
										});
									});
								})
							</script>
						</c:if> <c:if test="${sessionScope.SessionUserHaveShop == 1}">
							<a href="shop/shopadmin.jsp?shop_id=${sessionScope.ShopID}"><span
								class="glyphicon glyphicon-question-sign">管理店铺</span></a>
						</c:if> <c:if test="${sessionScope.SessionUserHaveShop == 0 }">
							<a href=""><span class="glyphicon glyphicon-question-sign">正在审核</span></a>
						</c:if></li>
					<li><a href="user/logout.action"><span
							class="glyphicon glyphicon-question-sign">登出</span></a></li>
				</ul>
			</div>
		</div>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<hgroup>
				<h1>阿里叔叔</h1>
				<h4>我们不是阿里巴巴，但请叫我们阿里叔叔</h4>
			</hgroup>
		</div>
	</div>
	<div id="information">
		<div class="container">
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8 info-left">
					<div class="container-fluid" style="padding:0px;">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title">
								<li class="tab layui-this">服饰鞋帽</li>
								<li class="tab">美妆个护</li>
								<li class="tab">休闲零食</li>
								<li class="tab">箱包奢品</li>
								<li class="tab">生鲜水果</li>
								<li class="tab">钟表首饰</li>
							</ul>
							<div class="layui-tab-content">
								<div class="layui-tab-item layui-show item"></div>
								<div class="layui-tab-item item"></div>
								<div class="layui-tab-item item"></div>
								<div class="layui-tab-item item"></div>
								<div class="layui-tab-item item"></div>
								<div class="layui-tab-item item"></div>
							</div>
							<a id="loadMore"
								style="display:block;width: 100%;text-align: center;cursor: pointer;">换一批</a>
						</div>
					</div>
				</div>


				<div class="col-md-4 info-right hidden-xs hidden-sm">
					<!-- hidden-xs hidden-sm 小屏幕影藏 -->
					<blockquote>
						<h2>热销物品TOP5</h2>
					</blockquote>

					<div class="container-fluid" id="hotGoods">

						<div class="row">
							<a href="single.jsp?goods_id=1">
								<div class="col-md-5 col-sm-5 col-xs-5"
									style="padding:0px;margin:12px 0;">
									<img src="img/info/3.jpg" alt="" class="img-responsive">
								</div>
								<div class="col-md-7 col-sm-7 col-xs-7"
									style="padding-right: 0;">
									<h4>个hop地方结果碰到个顺丰快递是</h4>
									<p>admin 17/05/27</p>
								</div>
							</a>
						</div>
					</div>
					<blockquote>
						<h2>热销商铺TOP5</h2>
					</blockquote>
					<div class="container-fluid" id="hotShop">

						<div class="row">
							<a href="">
								<div class="col-md-5 col-sm-5 col-xs-5"
									style="padding:0px;margin:12px 0;">
									<img src="img/info/3.jpg" alt="" class="img-responsive">
								</div>
								<div class="col-md-7 col-sm-7 col-xs-7"
									style="padding-right: 0;">
									<h4>个hop地方结果碰到个顺丰快递是</h4>
									<p>admin 17/05/27</p>
								</div>
							</a>
						</div>
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
		src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/index/index.js"></script>
</body>
</html>