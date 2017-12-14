/**
 * 
 */

layui.use('element', function() {
	var element = layui.element();
});
layui.use('form', function() {
	var form = layui.form();

});
$("#cancel").click(function() {
	$('#myModal').modal('hide');
})
$("#cancel").click(function() {
	$('#myModal1').modal('hide');
})
//layui.use('upload', function() {
//	layui.upload({
//		url : 'user/User_update.action',
//		elem: '#test',
//		success : function(res) {
//			console.log(res);
//		}
//	});
//});
$("#right").click(function() {
	layui.use('layer', function() {
		$('#myModal1').modal('hide');
		var user_name = $("#update_user_name").val();
		var user_password = $("#update_user_password").val();
		var user_phone = $("#update_user_phone").val();
		var user_address = $("#update_user_address").val();
		console.log("用户名：" + user_name);
		$.ajax({
			type : "POST",
			url : "user/User_update.action",
			async : true,
			data : {
				"user_name" : user_name,
				"user_password" : user_password,
				"user_phone" : user_phone,
				"user_address" : user_address
			},
			dataType : "json",
			success : function(data) {
				layer.closeAll('loading');
				console.log(data);
				if (data == 1) {
					layer.msg("更新成功");
					request(function(data) {
						console.log(data);
						$("#user_name").html(data[0].user_name);
						$("#user_sex").html(data[0].user_sex);
						$("#user_address").html(data[0].user_address);
						$("#user_phone").html(data[0].user_phone);
					}, "User_Query");
				} else {
					layer.msg("更新失败");
				}
			},
			beforeSend : function(index) {
				layer.load();
			}
		});
	});
})
function request(callback, url) {
	layui.use('layer', function() {
		var layer = layui.layer;
		$.ajax({
			type : "POST",
			url : "user/" + url + ".action",
			async : true,
			dataType : "json",
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
$(document).ready(function() {
	request(function(data) {
		var tpl = "";
		//console.log(data);
		if (data == 10000) {
			window.location.href = "login.jsp";
		} else {
			for (var i = 0; i < data.length; i++) {
				tpl += "<div class=\"row info-content\">\
						<p style=\"text-align: center;height: 20px;line-height: 30px;\">订单号：" + data[i].orders_id + "</p>\
						<hr style=\"width: 100%;height: 3px;margin: 10 0 10 0\">\
						<div id=\"order_list\" class=\"col-md-12 col-sm-12 col-xs-12\">\
							<div class=\"col-md-5 col-sm-5 col-xs-12\">\
								<img src=\"../img/info/8.jpg\" class=\"img-responsive\">\
							</div>\
							<div class=\"col-md-5 col-sm-5 col-xs-8\">\
								<h4>" + data[i].goods_name + "</h4>\
								<p>价格：￥" + data[i].orders_price + "</p>\
								<p>日期：" + data[i].orders_date + "</p>\
							</div>\
							<div class=\"col-md-2 col-sm-2 col-xs-4\">\
								<button class=\"btn\">未发货</button>\
							</div>\
						</div>\
				</div>";
				$("#order_wait_list").empty().append(tpl);
			}
		}


	}, "QueryOrder_NoExpress");
	request(function(data) {
		var tpl = "";
		//console.log(data);
		if (data == 10000) {
			window.location.href = "login.jsp";
		} else {
			for (var i = 0; i < data.length; i++) {
				tpl += "<div class=\"row info-content\">\
					<p style=\"text-align: center;height: 20px;line-height: 30px;\">订单号：" + data[i].orders_id + "</p>\
					<hr style=\"width: 100%;height: 3px;margin: 10 0 10 0\">\
					<div id=\"order_list\" class=\"col-md-12 col-sm-12 col-xs-12\">\
						<div class=\"col-md-5 col-sm-5 col-xs-12\">\
							<img src=\"../img/info/8.jpg\" class=\"img-responsive\">\
						</div>\
						<div class=\"col-md-5 col-sm-5 col-xs-8\">\
							<h4>" + data[i].goods_name + "</h4>\
							<p>价格：￥" + data[i].orders_price + "</p>\
							<p>日期：" + data[i].orders_date + "</p>\
						</div>\
						<div class=\"col-md-2 col-sm-2 col-xs-4\">\
							<button class=\"btn\" onclick=\"get(" + data[i].orders_id + ")\">确认收货</button>\
						</div>\
					</div>\
			</div>";
				$("#order_sign_list").empty().append(tpl);
			}
		}

	}, "QueryOrder_NoSign");
	request(function(data) {
		var tpl = "";
		//console.log(data);
		if (data == 10000) {
			window.location.href = "login.jsp";
		} else {
			for (var i = 0; i < data.length; i++) {
				tpl += "<div class=\"row info-content\">\
					<p style=\"text-align: center;height: 20px;line-height: 30px;\">订单号：" + data[i].orders_id + "</p>\
					<hr style=\"width: 100%;height: 3px;margin: 10 0 10 0\">\
					<div id=\"order_list\" class=\"col-md-12 col-sm-12 col-xs-12\">\
						<div class=\"col-md-5 col-sm-5 col-xs-12\">\
							<img src=\"../img/info/8.jpg\" class=\"img-responsive\">\
						</div>\
						<div class=\"col-md-5 col-sm-5 col-xs-8\">\
							<h4>" + data[i].goods_name + "</h4>\
							<p>价格：￥" + data[i].orders_price + "</p>\
							<p>日期：" + data[i].orders_date + "</p>\
						</div>\
						<div class=\"col-md-2 col-sm-2 col-xs-4\">\
							<button class=\"btn\">已签收</button>\
						</div>\
					</div>\
			</div>";
				$("#order_finish_list").empty().append(tpl);
			}
		}

	}, "QueryOrder_Finish");
	request(function(data) {
		var tpl = "";
		//console.log(data);
		if (data == 10000) {
			window.location.href = "login.jsp";
		} else {
			for (var i = 0; i < data.length; i++) {
				var status = "1";
				if (data[i].orders_status == 0) {
					status = "未发货"
				} else if (data[i].orders_status == 1) {
					status = "确认收货";
				} else if (data[i].orders_status == 2) {
					status = "已签收";
				}
				//console.log(status);
				tpl += "<div class=\"row info-content\">\
					<p style=\"text-align: center;height: 20px;line-height: 30px;\">订单号：" + data[i].orders_id + "</p>\
					<hr style=\"width: 100%;height: 3px;margin: 10 0 10 0\">\
					<div id=\"order_list\" class=\"col-md-12 col-sm-12 col-xs-12\">\
						<div class=\"col-md-5 col-sm-5 col-xs-12\">\
							<img src=\"../img/info/8.jpg\" class=\"img-responsive\">\
						</div>\
						<div class=\"col-md-5 col-sm-5 col-xs-7\">\
							<h4>" + data[i].goods_name + "</h4>\
							<p>价格：￥" + data[i].orders_price + "</p>\
							<p>日期：" + data[i].orders_date + "</p>\
						</div>\
						<div class=\"col-md-2 col-sm-2 col-xs-5\">\
							<button class=\"btn\"";
				//console.log(data[i].orders_status)
				if (data[i].orders_status == 1)
					tpl += " onclick=\"get(" + data[i].orders_id + ")\">" + status + "</button>";else {
					tpl += ">" + status + "</button>";
				}
				tpl += "</div>\
					</div>\
			</div>";
				$("#order_all_list").empty().append(tpl);
			}
		}

	}, "QueryOrder_All");
	request(function(data) {
		//console.log(data);
		if (data == 10000) {
			window.location.href = "login.jsp";
		} else {
			$("#user_name").html(data[0].user_name);
			$("#user_sex").html(data[0].user_sex);
			$("#user_address").html(data[0].user_address);
			$("#user_phone").html(data[0].user_phone);
		}
	}, "User_Query");
})
function get(id) {
	layui.use('layer', function() {
		var layer = layui.layer;
		$.ajax({
			type : "POST",
			url : "user/QueryOrder_Signing.action",
			async : true,
			data : {
				"orders_id" : id
			},
			dataType : "json",
			success : function(data) {
				layer.closeAll('loading');
				console.log(data);
				if (data == 1) {
					layer.msg("签收成功");
					request(function(data) {
						var tpl = "";
						//console.log(data);
						console.log(typeof (data));
						if (data[0] != null)
							for (var i = 0; i < data.length; i++) {
								tpl += "<div class=\"row info-content\">\
									<p style=\"text-align: center;height: 20px;line-height: 30px;\">订单号：" + data[i].orders_id + "</p>\
									<hr style=\"width: 100%;height: 3px;margin: 10 0 10 0\">\
									<div id=\"order_list\" class=\"col-md-12 col-sm-12 col-xs-12\">\
										<div class=\"col-md-5 col-sm-5 col-xs-12\">\
											<img src=\"../img/info/8.jpg\" class=\"img-responsive\">\
										</div>\
										<div class=\"col-md-5 col-sm-5 col-xs-8\">\
											<h4>" + data[i].goods_name + "</h4>\
											<p>价格：￥" + data[i].orders_price + "</p>\
											<p>日期：" + data[i].orders_date + "</p>\
										</div>\
										<div class=\"col-md-2 col-sm-2 col-xs-4\">\
											<button class=\"btn\" onclick=\"get(" + data[i].orders_id + ")\">确认收货</button>\
										</div>\
									</div>\
							</div>";
								$("#order_sign_list").empty().append(tpl);
						}
						else $("#order_sign_list").empty();

					}, "QueryOrder_NoSign");
					request(function(data) {
						var tpl = "";
						//console.log(data);
						for (var i = 0; i < data.length; i++) {
							var status = "1";
							if (data[i].orders_status == 0) {
								status = "未发货"
							} else if (data[i].orders_status == 1) {
								status = "确认收货";
							} else if (data[i].orders_status == 2) {
								status = "已签收";
							}
							//console.log(status);
							tpl += "<div class=\"row info-content\">\
									<p style=\"text-align: center;height: 20px;line-height: 30px;\">订单号：" + data[i].orders_id + "</p>\
									<hr style=\"width: 100%;height: 3px;margin: 10 0 10 0\">\
									<div id=\"order_list\" class=\"col-md-12 col-sm-12 col-xs-12\">\
										<div class=\"col-md-5 col-sm-5 col-xs-12\">\
											<img src=\"../img/info/8.jpg\" class=\"img-responsive\">\
										</div>\
										<div class=\"col-md-5 col-sm-5 col-xs-7\">\
											<h4>" + data[i].goods_name + "</h4>\
											<p>价格：￥" + data[i].orders_price + "</p>\
											<p>日期：" + data[i].orders_date + "</p>\
										</div>\
										<div class=\"col-md-2 col-sm-2 col-xs-5\">\
											<button class=\"btn\"";
							//console.log(data[i].orders_status)
							if (data[i].orders_status == 1)
								tpl += " onclick=\"get(" + data[i].orders_id + ")\">" + status + "</button>";else {
								tpl += ">" + status + "</button>";
							}
							tpl += "</div>\
									</div>\
							</div>";
							$("#order_all_list").empty().append(tpl);
						}

					}, "QueryOrder_All");
					request(function(data) {
						var tpl = "";
						//console.log(data);
						if (data == 10000) {
							window.location.href = "login.jsp";
						} else {
							for (var i = 0; i < data.length; i++) {
								tpl += "<div class=\"row info-content\">\
									<p style=\"text-align: center;height: 20px;line-height: 30px;\">订单号：" + data[i].orders_id + "</p>\
									<hr style=\"width: 100%;height: 3px;margin: 10 0 10 0\">\
									<div id=\"order_list\" class=\"col-md-12 col-sm-12 col-xs-12\">\
										<div class=\"col-md-5 col-sm-5 col-xs-12\">\
											<img src=\"../img/info/8.jpg\" class=\"img-responsive\">\
										</div>\
										<div class=\"col-md-5 col-sm-5 col-xs-8\">\
											<h4>" + data[i].goods_name + "</h4>\
											<p>价格：￥" + data[i].orders_price + "</p>\
											<p>日期：" + data[i].orders_date + "</p>\
										</div>\
										<div class=\"col-md-2 col-sm-2 col-xs-4\">\
											<button class=\"btn\">已签收</button>\
										</div>\
									</div>\
							</div>";
								$("#order_finish_list").empty().append(tpl);
							}
						}

					}, "QueryOrder_Finish");
				}
			},
			beforeSend : function(index) {
				layer.load();
			}
		});
	});
}