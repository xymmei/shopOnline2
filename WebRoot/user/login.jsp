<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>XXXXXX系统登录</title>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="../assets/css/form-elements.css">
<link rel="stylesheet" href="../assets/css/style.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="stylesheet" href="../layui/css/layui.css">

<script type="text/javascript" src="../layui/layui.js"></script>
<style type="text/css">
.layui-form-label {
	width: 110px;
}

.layui-form-item .layui-input-inline {
	width: 410px;
}
</style>
</head>

<body>
	<!-- Top content -->
	<div class="top-content">
		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>XXXXX</strong>登录
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>欢迎使用XXXX系统</h3>
								<p>输入您的用户名和密码登录：</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"></i>
							</div>
						</div>
						<div class="form-bottom">
							<div class="form-group">
								<label class="sr-only" for="form-username">账号</label> <input
									id="loginUser" type="text" name="loginUser" placeholder="账号"
									class="form-username form-control">
							</div>
							<div class="form-group">
								<label class="sr-only" for="form-password">密码</label> <input
									id="loginPasswd" type="password" name="loginPasswd"
									placeholder="密码" class="form-password form-control"> <span
									id="regist">还没有账号，点这注册</span>
							</div>
							<button class="btn" id="login">确认</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="reg"
		style="display: none;width: 80%;height: auto;margin: 10px auto;">
		<div class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-inline">
					<input type="text" name="user_name" required lay-verify="required"
						placeholder="请输入用户名" autocomplete="off" class="layui-input"
						id="user_name">
				</div>
				<div class="layui-form-mid layui-word-aux" id="name_add">辅助文字</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">真实姓名：</label>
				<div class="layui-input-inline">
					<input type="text" name="user_realname" required
						lay-verify="required" placeholder="请输入真实姓名" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">辅助文字</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="user_password" required
						lay-verify="required" placeholder="请输入密码" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">辅助文字</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">确认密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="regConfirmPasswd" required
						lay-verify="required" placeholder="请确认密码" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">辅助文字</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">手机号：</label>
				<div class="layui-input-inline">
					<input type="text" name="user_phone" required
						lay-verify="required|phone|number" placeholder="请输入手机号"
						autocomplete="off" class="layui-input" id="user_phone">
				</div>
				<div class="layui-form-mid layui-word-aux" id="phone_add">辅助文字</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">身份证：</label>
				<div class="layui-input-inline">
					<input type="text" name="user_idcard" required
						lay-verify="required|identity" placeholder="请输入身份证号"
						autocomplete="off" class="layui-input" id="user_idcard">
				</div>
				<div class="layui-form-mid layui-word-aux" id="idcard_add">辅助文字</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">地址：</label>
				<div class="layui-input-inline">
					<select name="user_address" lay-verify="required">
						<option value=""></option>
						<option value="0">北京</option>
						<option value="1">上海</option>
						<option value="2">广州</option>
						<option value="3">深圳</option>
						<option value="4">杭州</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别：</label>
				<div class="layui-input-inline">
					<input type="radio" name="user_sex" value="男" title="男"> <input
						type="radio" name="user_sex" value="女" title="女" checked>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">验证码</label>
				<div class="layui-input-inline">
					<input type="text" name="code" required placeholder="验证码"
						autocomplete="off" class="layui-input" id="code">
					<button class="btn" id="yanzhengma">获取验证码</button>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-inline">
					<button class="layui-btn" lay-submit lay-filter="formDemo"
						style="margin-left: 200px;">立即提交</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Javascript -->
	<script src="../assets/js/jquery-1.11.1.min.js"></script>
	<script src="../assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="../assets/js/jquery.backstretch.min.js"></script>
	<script src="../assets/js/scripts.js"></script>
	<script language="javascript" type="text/javascript"
		src="../js/user/login.js"></script>
	<!--[if lt IE 10]>
            <script src="../assets/js/placeholder.js"></script>
        <![endif]-->

	<script type="text/javascript" src="../js/user/regist.js"></script>
</body>

</html>
