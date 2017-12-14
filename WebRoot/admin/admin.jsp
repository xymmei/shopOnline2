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
<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="./vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="./dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="./vendor/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="./vendor/font-awesome/css/font-awesome.min.css"
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
</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
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
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-user fa-fw"></i>
								${sessionScope.admin_name}</a></li>
						<li class="divider"></li>
						<li><a href="logout.action"><i
								class="fa fa-sign-out fa-fw"></i> Logout</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">

						<li><a href="#"><i class="fa fa-dashboard fa-fw"></i>
								平台数据</a></li>
						<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
								店铺申请列表</a>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<div class="layui-tab" lay-allowClose="true">
				<ul class="layui-tab-title">
					<li class="layui-this">数据分析</li>
					<li>申请列表</li>
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
											<div class="huge" id="shopNum">26</div>
											<div>商户数量</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-green">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-tasks fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge" id="userNum">12</div>
											<div>用户数量</div>
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
											<div class="huge" id="sales">124</div>
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
										<div class="col-sm-3">
											<form class="form-horizontal" role="form">
												<div class="form-group">
													<label class="col-sm-5 control-label">查询类型</label>
													<div class="col-sm-7">
														<select class="form-control" id="selectType">
															<option>请选择</option>
															<option>销售总额</option>
															<option>商户数量</option>
														</select>
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
																<th>销售额/商户数量</th>
															</tr>
														</thead>
														<tbody id="select1_tbody">
															
														</tbody>
													</table>
												</div>
												<!-- /.table-responsive -->
											</div>
											<!-- /.col-lg-4 (nested) -->
											<div class="col-lg-8">
												<div id="morris-bar-chart"></div>
											</div>
											<!-- /.col-lg-8 (nested) -->
										</div>
										<!-- /.row -->
									</div>
									<!-- /.panel-body -->
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
											<i class="fa fa-bar-chart-o fa-fw"></i> 店铺申请列表
										</div>
										<div class="col-sm-3">
											<form class="form-horizontal" role="form">
												<div class="form-group">
													<label class="col-sm-5 control-label">查询类型</label>
													<div class="col-sm-7">
														<select class="form-control" id="selectType2">
															<option>请选择</option>
															<option>待审核</option>
															<option>已通过</option>
															<option>未通过</option>
														</select>
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
																<th>日期</th>
																<th>店铺名称</th>
																<th>店主</th>
																<th>状态</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody id="select2_tbody">
															<tr>
																<td>1</td>
																<td>10/21/2013</td>
																<td>biubiubiu</td>
																<td>biubiu</td>
																<td>待审核</td>
																<td><a href="#">审核</a><a href="#">详细信息</a></td>
															</tr>
														</tbody>
													</table>
												</div>
												<!-- /.table-responsive -->
											</div>
											<!-- /.col-lg-4 (nested) -->
											<div class="col-lg-8">
												<div id="morris-bar-chart"></div>
											</div>
											<!-- /.col-lg-8 (nested) -->
										</div>
										<!-- /.row -->
									</div>
									<!-- /.panel-body -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->

	</div>
	</div>
	<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="./vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="./vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="./vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="./vendor/raphael/raphael.min.js"></script>
	<script type="text/javascript" src="../js/admin/admin.js"></script>
</body>

</html>
