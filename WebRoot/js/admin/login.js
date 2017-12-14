/**
 * 
 */
(function() {
	var admin_name = $("#admin_name");
	var admin_password = $("#admin_password");
	var login = $("#login");
	var admin_name_mark = false;
	var admin_password_mark = false;
	function login_request(callback) {
		layui.use('layer', function() {
			var layer = layui.layer;
			$.ajax({
				type : "POST",
				url : "login.action",
				async : true,
				dataType : "json",
				data : {"admin_name" : admin_name.val(), "admin_password" : admin_password.val()},
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
		var phone = admin_name.val();
		if (!(/^1[34578]\d{9}$/.test(phone))) {
			return false;
		}
		return true;
	}
	layui.use('layer', function() {
		var layer = layui.layer;
		var admin_name = $("#admin_name");
		var admin_password = $("#admin_password");
		var login = $("#login");
		var admin_name_mark = false;
		var admin_password_mark = false;
		admin_name.blur(function() {
			if (admin_name.val() == "") {
				admin_name.css("border", "3px solid #e87e7e");
			} else {
				if (!checkPhone()) {
					layer.msg('手机格式错误');
					admin_name.css("border", "3px solid #e87e7e");
				} else {
					admin_password.css("border", "3px solid #ddd");
					admin_name_mark = true;
				}
			}
		}).focus(function() {
			admin_name.css("border", "3px solid #ddd");
		});
		admin_password.blur(function() {
			if (admin_password.val() == "")
				admin_password.css("border", "3px solid #e87e7e");
			else {
				admin_password_mark = true;
				admin_password.css("border", "3px solid #ddd");
			}
		}).focus(function() {
			admin_password.css("border", "3px solid #ddd");
		});
		login.click(function() {
			//console.log(admin_name.val() + admin_password.val());
			//console.log(admin_name_mark+" "+admin_password_mark);
			if (admin_name_mark && admin_password_mark) {
				login_request(function(data) {
					console.log(data);
					if(data[0].loginStatus == 1){
						//admin_name.val("");
						window.location.href="admin.jsp?admin_name="+admin_name.val();
					}else{
						layer.msg('密码或者账号错误');
					}
				})
			} else {
				admin_name.css("border", "3px solid #e87e7e");
				admin_password.css("border", "3px solid #e87e7e");
			}
		});

	});
})();