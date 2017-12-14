/**
 * 
 */
(function() {
	var loginUser = $("#loginUser");
	var loginPasswd = $("#loginPasswd");
	var login = $("#login");
	var loginUser_mark = false;
	var loginPasswd_mark = false;
	loginUser.blur(function() {
		if (loginUser.val() == "") {
			loginUser.css("border", "3px solid #e87e7e");
		} else {
			if (!checkPhone()) {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('手机格式错误');
				});
				loginUser.css("border", "3px solid #e87e7e");
			} else {
				loginPasswd.css("border", "3px solid #ddd");
				loginUser_mark = true;
			}
		}
	}).focus(function() {
		loginUser.css("border", "3px solid #ddd");
	});
	loginPasswd.blur(function() {
		if (loginPasswd.val() == "")
			loginPasswd.css("border", "3px solid #e87e7e");else {
			loginPasswd_mark = true;
			loginPasswd.css("border", "3px solid #ddd");
		}
	}).focus(function() {
		loginPasswd.css("border", "3px solid #ddd");
	});
	login.click(function() {
		if (loginUser_mark && loginPasswd_mark) {
			login_request(function(data) {
				console.log(data[0]);
				if (data == 0 || data == -1) {
					loginUser.val("");
					loginPasswd.val("");
					layer.msg("账号或密码错误");
					//return false;
				} else {
					loginUser.val("");
					window.location.href = "../index.jsp";
				}
			})
		} else {
			/*loginUser.css("border", "3px solid #e87e7e");
			loginPasswd.css("border", "3px solid #e87e7e");*/
		}
	})
	function login_request(callback) {
		layui.use('layer', function() {
			$.ajax({
				type : "GET",
				url : "user/login.action",
				dataType : "json",
				data : "user_phone=" + loginUser.val() + "&user_password=" + loginPasswd.val(),
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
	function checkPhone() {
		var phone = loginUser.val();
		if (!(/^1[34578]\d{9}$/.test(phone))) {
			return false;
		}
		return true;
	}
})();