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
<title>资讯</title>
<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> 文件可能有错
	可以直接连接到网上，如下-->
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/index.css">
<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- 第一个参数：width=device-width，以设备的宽度为宽度。第二个参数：initial-scale=1,百分百缩放。第三个参数：最大的缩放，也是百分百。第四个参数：禁止用户缩放 。做响应式时，加上这句话。-->
<style>
		.modal-backdrop {
	z-index: -1;
}
		
	</style>

<link rel="stylesheet" href="layui/css/layui.css">
<script type="text/javascript" src="layui/layui.js"></script>
<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!-- Custom Theme files -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/animate.min.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- animation -->
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all" />
<!-- menu style -->
<link href="css/owl.carousel.css" rel="stylesheet" type="text/css"
	media="all">
<!-- carousel slider -->
<!-- //Custom Theme files -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- js -->
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/owl.carousel.js"></script>
<script src="js/bootstrap.js"></script>
<!--flex slider-->
<script defer src="js/jquery.flexslider.js"></script>
<link rel="stylesheet" href="css/flexslider.css" type="text/css"
	media="screen" />
<script>
	// Can also be used with $(document).ready()
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			controlNav : "thumbnails"
		});
	});
</script>
<!--flex slider-->
<script src="js/imagezoom.js"></script>
<script src="js/jquery-scrolltofixed-min.js" type="text/javascript"></script>

<!-- //scroll to fixed-->
<!-- start-smooth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
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
				<div class="single-page">
					<div class="single-page-row" id="detail-21">
						<div class="col-md-6 single-top-left">
							<div class="flexslider">

								<ul class="slides">
									<li data-thumb="images/s1.jpg">
										<div class="thumb-image detail_images">
											<img src="images/s1.jpg" data-imagezoom="true"
												class="img-responsive" alt="">
										</div>
									</li>
								</ul>
								<ol class="flex-control-nav flex-control-thumbs">
									<li><img src="images/s1.jpg" class="" draggable="false"></li>
								</ol>
								<ul class="flex-direction-nav">
									<li class="flex-nav-prev"><a class="flex-prev" href="#">Previous</a></li>
									<li class="flex-nav-next"><a class="flex-next" href="#">Next</a></li>
								</ul>
							</div>
						</div>
						<div class="col-md-6 single-top-right">
							<h3 class="item_name" id="goods_name">阿玛尼素颜霜</h3>
							<p id="shop_name">店铺： 阿玛尼专卖店</p>
							<!-- <div class="single-rating">
								<ul>
									<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li class="rating">20 reviews</li>
									<li><a href="#">Add your review</a></li>
								</ul>
							</div> -->
							<div class="single-price">
								<ul>
									<li><span>￥</span><span id="goods_price">540</span></li>
									<li><del>￥600</del></li>
									<!-- <li><span class="w3off">10% OFF</span></li>
									<li>Ends on: June,5th</li>
									<li><a href="#"><i class="fa fa-gift"
											aria-hidden="true"></i> Coupon</a></li> -->
								</ul>
							</div>
							<p class="single-price-text" id="goods_describe">阿玛尼 黑钥匙/黑曜石 光影美肤修颜霜 粉底霜50ml
								 Crema Nuda ~</p>

							<div class="single-price">
								<ul>
									<button class="w3ls-cart" style="width:20%;margin-bottom: 15px;" id="buy_add">
										</i> +
									</button>
									<div class="w3ls-cart" style="background: gray;width:18%" id="buy_sum">1</div>
									<button class="w3ls-cart" style="width:20%;margin-bottom: 15px;" id="buy_des" disabled="disabled">
										</i> -
									</button>
								</ul>
							</div>
							<input type="hidden" name="cmd" value="_cart"> <input
								type="hidden" name="add" value="1"> <input type="hidden"
								name="w3ls_item" value="Snow Blower"> <input
								type="hidden" name="amount" value="540.00">

							<button class="w3ls-cart">
								<i class="fa fa-cart-plus" aria-hidden="true"></i> 添加到购物车
							</button>
							<button class="w3ls-cart w3ls-cart-like" id="buy">
								<i class="fa fa-heart-o" aria-hidden="true"></i>立即购买
							</button>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="single-page-icons social-icons"></div>
				</div>
			</div>
		</div>
		<footer id="footer" class="text-muted">
			<div class="container">
				<p>企业培训|合作事宜|版权控诉</p>
				<p>苏ICP 备12345678.@2009-2016 瓢dasdasdasf 是的法式咖啡</p>
			</div>
		</footer>
		<script src="js/minicart.js"></script>
		<script>
			w3ls.render();
		
			w3ls.cart.on('w3sb_checkout', function(evt) {
				var items,
					len,
					i;
		
				if (this.subtotal() > 0) {
					items = this.items();
		
					for (i = 0, len = items.length; i < len; i++) {
						items[i].set('shipping', 0);
						items[i].set('shipping2', 0);
					}
				}
			});
		</script>
		<!-- //cart-js -->
		<!-- menu js aim -->
		<script type="text/javascript" src="./js/index/single.js"></script>
		<script src="js/jquery.menu-aim.js"> </script>
		<script src="js/main.js"></script>
		<script
			src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script
			src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>