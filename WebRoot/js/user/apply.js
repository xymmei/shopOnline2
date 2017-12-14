/**
 * 
 */
layui.use('layer', function() {
	$('#myModal').modal('hide');
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