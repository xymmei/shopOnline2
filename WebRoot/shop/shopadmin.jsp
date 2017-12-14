<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>XXXX平台管理</title>
<!-- Bootstrap Core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="./vendor/morrisjs/morris.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="../vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
                            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
                            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
                        <![endif]-->
<link rel="stylesheet" href="../layui/css/layui.css">
<script type="text/javascript" src="../layui/layui.js"></script>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css"
	rel="stylesheet">

<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">管理系统</a>
			</div>
			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-user fa-fw"></i>
								${sessionScope}</a></li>
						<li class="divider"></li>
						<li><a href="logout.action"><i
								class="fa fa-sign-out fa-fw"></i>Logout</a></li>
					</ul></li>
			</ul>
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="#"><i class="fa fa-dashboard fa-fw"></i>
								店铺数据</a></li>
						<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
								店铺管理</a>
					</ul>
				</div>
			</div>
		</nav>
		<div id="page-wrapper">
			<div class="layui-tab" lay-allowClose="true" class="col-xs-12">
				<ul class="layui-tab-title">
					<li class="layui-this">数据分析</li>
					<li>店铺管理</li>
					<li>订单管理</li>
					<li>商品管理</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-comments fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge" id="exchange_num">26</div>
											<div>成交记录</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-yellow">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-shopping-cart fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge" id="sale_sum">124</div>
											<div>销售额</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<!-- /.panel -->
								<div class="panel panel-default">
									<div class="panel-heading clearfix">
										<div class="col-sm-2">
											<i class="fa fa-bar-chart-o fa-fw"></i> 数据分析
										</div>
										<div class="col-sm-3">
											<form class="form-horizontal" role="form">
												<div class="form-group">
													<label class="col-sm-5 control-label">查询类型</label>
													<div class="col-sm-7">
														<select class="form-control" id="selectType">
															<option>交易数量</option>
														</select>
													</div>
												</div>
											</form>
										</div>
										<div class="col-sm-3">
											<form class="form-horizontal" role="form">
												<div class="form-group">
													<label class="col-sm-3 control-label">起始年月</label>
													<div class="col-sm-9">
														<input id="myDate1" class="form-control" type="month">
													</div>
												</div>
											</form>
										</div>
										<div class="col-sm-3">
											<form class="form-horizontal" role="form">
												<div class="form-group">
													<label class="col-sm-3 control-label">结束年月</label>
													<div class="col-sm-9">
														<input id="myDate2" class="form-control" type="month">
													</div>
												</div>
											</form>
										</div>
									</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<div class="row">
											<div class="col-lg-12">
												<div class="table-responsive">
													<table
														class="table table-bordered table-hover table-striped">
														<thead>
															<tr>
																<th>序号</th>
																<th>时间</th>
																<th>订单数</th>
															</tr>
														</thead>
														<tbody id="shop_tongji">
															
														</tbody>
													</table>
												</div>
											</div>
											<div class="col-lg-8">
												<div id="morris-bar-chart"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="layui-tab-item">
						<div class="row">
							<div class="col-lg-12">
								<!-- /.panel -->
								<div class="panel panel-default">
									<div class="panel-heading clearfix">
										<div class="col-sm-2">
											<i class="fa fa-bar-chart-o fa-fw"></i> 店铺管理
										</div>
										<div class="col-sm-2 pull-right">
											<button class="btn btn-primary" id="shop_update"
												data-toggle="modal" data-target="#myModal1">更新店铺信息</button>
											<div class="modal fade" id="myModal1" tabindex="-1"
												role="dialog" aria-labelledby="myModalLabel">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
															<h4 class="modal-title" id="myModalLabel">更新店铺信息</h4>
														</div>
														<div class="modal-body">
															<form class="form-horizontal">
																<div class="form-group">
																	<label for="inputEmail3" class="col-sm-2 control-label">店铺名称</label>
																	<div class="col-sm-10">
																		<input type="email" class="form-control"
																			id="shop_name" placeholder="店铺名称">
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">店铺类型</label>
																	<div class="col-sm-10">
																		<input type="text" class="form-control"
																			id="shop_type" placeholder="店铺类型">
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">店铺简介</label>
																	<div class="col-sm-10">
																		<textarea class="form-control" id="shop_describe"></textarea>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">店铺地址</label>
																	<div class="col-sm-10">
																		<input type="text" class="form-control"
																			id="shop_address" placeholder="店铺地址">
																	</div>
																</div>
															</form>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-default"
																data-dismiss="modal" id="shop_right">确认</button>
															<button type="button" class="btn btn-primary"
																id="shop_cancel">取消</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<div class="row">
											<div class="col-lg-12">
												<div class="layui-form layui-form-pane" action="">
													<div class="layui-form-item" pane>
														<label class="layui-form-label">店铺名称</label>
														<div class="layui-input-block">
															<input type="text" name="title" required
																lay-verify="required" placeholder="店铺名称"
																autocomplete="off" class="layui-input" value="李锐的店"
																readonly="readonly" id="shop_name1">
														</div>
													</div>
													<div class="layui-form-item" pane>
														<label class="layui-form-label">图片展示</label>
														<div class="layui-input-block">
															<img alt="" src="../img/6.jpg" class="img-responsive"
																id="shop_img1">
														</div>
													</div>
													<div class="layui-form-item" pane>
														<label class="layui-form-label">店铺地址</label>
														<div class="layui-input-block">
															<input type="text" name="title" required
																lay-verify="required" placeholder="店铺地址"
																autocomplete="off" class="layui-input" value="店铺地址"
																readonly="readonly" id="shop_type1">
														</div>
													</div>
													<div class="layui-form-item" pane>
														<label class="layui-form-label">店铺类型</label>
														<div class="layui-input-block">
															<input type="text" name="title" required
																lay-verify="required" placeholder="店铺名称"
																autocomplete="off" class="layui-input" value="青岛市"
																readonly="readonly" id="shop_address1">
														</div>
													</div>
													<div class="layui-form-item" pane>
														<label class="layui-form-label">店铺简介</label>
														<div class="layui-input-block">
															<textarea rows="" cols="" class="layui-textarea"
																placeholder="简介" readonly="readonly" id="shop_describe1"></textarea>
														</div>
													</div>
												</div>
											</div>
											<div class="col-lg-8">
												<div id="morris-bar-chart"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="layui-tab-item">

						<div class="col-sm-3">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label class="col-sm-5 control-label">查询类型</label>
									<div class="col-sm-7">
										<select class="form-control" id="order_search">
											<option>请选择</option>
											<option>待发货</option>
											<option>待签收</option>
											<option>已签收</option>
											<option>全部订单</option>
										</select>
									</div>
								</div>
							</form>
						</div>
						<table class="layui-table">
							<colgroup>
								<col width="150">
								<col width="200">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>序号</th>
									<th>用户名</th>
									<th>订单号</th>
									<th>商品</th>
									<th>数量</th>
									<th>单价</th>
									<th>总价</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody id="orders_select">
								<tr>
									<td>1</td>
									<td>kirin</td>
									<td>1</td>
									<td>鞋</td>
									<td>2</td>
									<td>1</td>
									<td>2</td>
									<td><button class="layui-btn layui-btn-normal">已发货</button></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="layui-tab-item">
						<div class="panel panel-default">
							<div class="panel-heading clearfix">
								<div class="col-sm-2">
									<i class="fa fa-bar-chart-o fa-fw"></i> 商品管理
								</div>
								<div class="col-sm-2 pull-right">
									<button class="btn btn-primary" id="goods_add"
										data-toggle="modal" data-target="#myModal">增加商品</button>
									<div class="modal fade" id="myModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
													<h4 class="modal-title" id="myModalLabel">增加商品</h4>
												</div>
												<div class="modal-body">
													<form class="form-horizontal">
														<div class="form-group">
															<label for="inputEmail3" class="col-sm-2 control-label">商品名称</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="goods_name"
																	placeholder="商品名称">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">商品类型</label>
															<div class="col-sm-10">
																<select class="form-control" id="goods_type">
																	<option>美妆个护</option>
																	<option>箱包奢品</option>
																	<option>生鲜水果</option>
																	<option>休闲零食</option>
																	<option>体育用品</option>
																	<option>服饰鞋帽</option>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label for="inputEmail3" class="col-sm-2 control-label">商品单价</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" id="goods_price"
																	placeholder="商品单价">
															</div>
														</div>
														<div class="form-group">
															<label for="inputPassword3"
																class="col-sm-2 control-label">商品简介</label>
															<div class="col-sm-10">
																<textarea class="form-control" id="goods_describe"></textarea>
															</div>
														</div>
														<div class="form-group">
															<label for="inputPassword3"
																class="col-sm-2 control-label">库存量</label>
															<div class="col-sm-10">
																<input type="text" class="form-control"
																	id="goods_sum" placeholder="库存量">
															</div>
														</div>
													</form>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal" id="goods_right">确认</button>
													<button type="button" class="btn btn-primary"
														id="goods_cancel">取消</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-12">
										<div class="layui-form layui-form-pane" action="">
											<table class="layui-table">
												<colgroup>
													<col width="150">
													<col width="200">
													<col>
												</colgroup>
												<thead>
													<tr>
														<th>序号</th>
														<th>商品名称</th>
														<th>单价</th>
														<th>商品简介</th>
														<th>库存量</th>
														<th>销售量</th>
														<th>成交金额</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody id="good_list">
													<tr>
														<td>1</td>
														<td>kirin</td>
														<td>1</td>
														<td>鞋</td>
														<td>2</td>
														<td>1</td>
														<td>2</td>
														<td><button class="layui-btn layui-btn-normal">下架</button></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<div class="col-lg-8">
										<div id="morris-bar-chart"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../vendor/jquery/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script src="../vendor/metisMenu/metisMenu.min.js"></script>
	<script type="text/javascript" src="../js/shop/admin.js"></script>
	<!-- Morris Charts JavaScript -->
	<!-- <script src="../vendor/raphael/raphael.min.js"></script> -->
</body>

</html>