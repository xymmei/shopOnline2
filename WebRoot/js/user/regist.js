layui.use('form', function() {
	var form = layui.form();
	var user_name_status = false;
	var user_phone_status = false;
	var user_idcard_status = false;
	$("#regist").click(function() {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.open({
				type : 1,
				title : '用户注册',
				area : '800px',
				content : $('#reg') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			});
		});
	});
	form.on('submit(formDemo)', function(data) {
		console.log(data.field)
		if (data.field.regConfirmPasswd == data.field.user_password) {
			if (user_name_status == true) {
				if (user_phone_status == true) {
					if(user_idcard_status == true){
						console.log(data.field);
						$.ajax({
							type : "POST",
							url : "user/reg.action",
							async : true,
							dataType : "json",
							data : data.field,
							success : function(data) {
								//console.log("状态码："+data);
								layer.closeAll('loading');
								layer.closeAll();
								if(data == 0){
									layer.msg("注册失败");
								}else if(data == 1){
									layer.msg("注册成功");
								}else{
									layer.msg("验证码错误");
								}
							},
							beforeSend : function(index) {
								layer.load();
							}
						});
					}else{
						layer.msg("身份证已被占用");
					}
					
				}else{
					layer.msg("手机号已被占用");
				}

			} else {
				layer.msg("用户名已被占用");
			}

		} else {
			layer.msg("两次密码不相同");
		}
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	$("#user_name").blur(function() {
		//alert($(this).val());
		if ($("#user_name").val() != "")
			request(function(data) {
				console.log(data);
				if (data == 1) {
					$("#name_add").html("<span style=\"color:green;\">用户名可用</span>");
					user_name_status = true;
				} else {
					$("#name_add").html("<span style=\"color:red;\">用户名已被占用</span>");
					user_name_status = false;
				}
			}, "user_name=" + $(this).val());
	})
	$("#user_phone").blur(function() {
		//alert($(this).val());
		if ($("#user_phone").val() != "")
			request(function(data) {
				console.log(data);
				if (data == 1) {
					$("#phone_add").html("<span style=\"color:green;\">手机号可用</span>");
					user_phone_status =true;
				} else {
					$("#phone_add").html("<span style=\"color:red;\">手机号已被占用</span>");
					user_phone_status = false;
				}
			}, "user_phone=" + $(this).val());
	})
	$("#user_idcard").blur(function() {
		//alert($(this).val());
		if ($("#user_idcard").val() != "")
			request(function(data) {
				console.log(data);
				if (data == 1) {
					$("#idcard_add").html("<span style=\"color:green;\">身份证可用</span>");
					user_idcard_status =true;
				} else {
					$("#idcard_add").html("<span style=\"color:red;\">身份证被占用</span>");
					user_idcard_status = false;
				}
			}, "user_idcard=" + $(this).val());
	})
	function request(callback, selectData) {
		layui.use('layer', function() {
			var layer = layui.layer;
			$.ajax({
				type : "POST",
				url : "user/Check_User.action",
				async : true,
				dataType : "json",
				data : selectData,
				success : function(data) {
					callback(data);
					layer.closeAll('loading');
				},
				beforeSend : function(index) {
					layer.load();
				}
			});
		});
	}
});

function checkPhone() {
	var user_phone = $("#user_phone").val();
	if (!(/^1[34578]\d{9}$/.test(user_phone))) {
		return false;
	}
	return true;
}
$("#yanzhengma").click(function() {
	var user_phone = $("#user_phone").val();
	console.log(user_phone);
	console.log(checkPhone());
	if (checkPhone()) {
		$.ajax({
			type : "POST",
			url : "user/createRegCode.action",
			async : true,
			dataType : "json",
			data : {
				"user_phone" : user_phone
			},
			success : function(data) {
				layer.closeAll('loading');
				layer.msg("验证码发送成功");
			},
			beforeSend : function(index) {
				layer.load();
			}
		});
	} else {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg("手机号格式错误");
		});
	}
})