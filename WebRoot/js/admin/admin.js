layui.use('element', function() {
	var element = layui.element();
});
$(document).ready(function() {
	layui.use('layer', function() {
		var selectType = $("#selectType");
		selectType.change(function() {
			var start = $("#myDate1").val();
			var end = $("#myDate2").val();
			var select = selectType.val();
			var status = 0;
			var select1_tbody = $("#select1_tbody");
			select1_tbody.empty();
			var tpl = "";
			if (select == "商户数量") {
				status = 0;
			} else {
				status = 1;
			}
			console.log(start);
			console.log(end);
			$.ajax({
				type : "POST",
				url : "screen.action",
				async : true,
				dataType : "json",
				data : {
					"single" : status,
					"startMonth" : start,
					"endMonth" : end
				},
				success : function(data) {
					console.log(data);
					layer.closeAll('loading');
					var i = 0;
					for (i = 0; i < data.length; i++) {
						tpl = "<tr>" +
							"<td>" + parseInt(i+1) + "</td>" +
							"<td>" + data[i].year + "年" + data[i].month + "月" + "</td>" +
							"<td>" + data[i].price + "</td>" +
							+"</tr>";
						select1_tbody.append(tpl);
					}

				},
				beforeSend : function(index) {
					layer.load();
				}
			});
		});
		var selectType2 = $("#selectType2");
		selectType2.change(function() {
			var check = 0;
			var tpl = "";
			var select2_tbody = $("#select2_tbody");
			var select2 = selectType2.val();
			if (select2 == "待审核") {
				check = 0;
			} else if (select2 == "已通过") {
				check = 1;
			} else {
				check = 2;
			}
			select2_tbody.empty();
			//console.log(selectType2.val()+check);
			$.ajax({
				type : "POST",
				url : "findPend.action",
				async : true,
				dataType : "json",
				data : {
					"status" : check
				},
				success : function(data) {
					console.log(data);
					layer.closeAll('loading');
					for (var i = 0; i < data.length; i++) {
						var status = "";
						if (data[i].shop_status == 2) {
							status = "未通过";
						} else if (data[i].shop_status == 1) {
							status = "已通过";
						} else {
							status = "待审核";
						}
						tpl = "<tr>" +
							"<td>" + parseInt(i+1)+ "</td>" +
							"<td>" + data[i].shop_date + "</td>" +
							"<td>" + data[i].shop_name + "</td>" +
							"<td>" + data[i].user_name + "</td>" +
							"<td>" + status + "</td>" +
							"<td><a onclick=\"check(" + data[i].shop_id + "," + data[i].shop_status + ")\">审核</a><a>详细信息</a></td>" +
							+"</tr>";
						select2_tbody.append(tpl);
					}

				},
				beforeSend : function(index) {
					layer.load();
				}
			});
		});

		var sales = $("#sales");
		var shopNum = $("#shopNum");
		var userNum = $("#userNum");
		$.ajax({
			type : "POST",
			url : "load.action",
			async : true,
			dataType : "json",
			success : function(data) {
				console.log(data);
				layer.closeAll('loading');
				if(data == 10000) window.location.href="login.jsp";
				else{
					sales.html(data.saleprice_count);
					shopNum.html(data.shop_count);
					userNum.html(data.user_count);
				}
			},
			beforeSend : function(index) {
				layer.load();
			}
		});
		/**
		 * 拼接字符串 onclick="check(id)"
		 */

	});
})
function check(id, check_status) {
	if (check_status == 1 || check_status == 2) {
		layer.msg("本店已审核，不再需要审核");
	} else {
		
		layer.open({
			content : '店铺审核是否通过？',
			btn : [ 'yes', 'no' ],
			yes : function(index, layero) {
				check_request(function(data) {
					console.log(data);
				}, 1, id)
				layer.closeAll();
				refresh();
			},
			btn2 : function(index, layero) {
				check_request(function(data) {
					console.log(data);
				}, 2, id)
				layer.closeAll();
				refresh();
			}
		});
	}
}
function check_request(callback, status, id) {
	layui.use('layer', function() {
		var layer = layui.layer;
		$.ajax({
			type : "POST",
			url : "auditPend.action",
			async : true,
			dataType : "json",
			data : {
				"status" : status,
				"shop_id" : id
			},
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
function refresh() {
	var selectType2 = $("#selectType2");
	var check = 0;
	var tpl = "";
	var select2_tbody = $("#select2_tbody");
	var select2 = selectType2.val();
	if (select2 == "待审核") {
		check = 0;
	} else if (select2 == "已通过") {
		check = 1;
	} else {
		check = 2;
	}
	console.log(select2);
	select2_tbody.empty();
	//console.log(selectType2.val()+check);
	$.ajax({
		type : "POST",
		url : "findPend.action",
		async : true,
		dataType : "json",
		data : {
			"status" : check
		},
		success : function(data) {
			console.log(data);
			layer.closeAll('loading');
			for (var i = 0; i < data.length; i++) {
				var status = "";
				if (data[i].shop_status == 2) {
					status = "未通过";
				} else if (data[i].shop_status == 1) {
					status = "已通过";
				} else {
					status = "待审核";
				}
				tpl = "<tr>" +
					"<td>" + parseInt(i+1)+ "</td>" +
					"<td>" + data[i].shop_date + "</td>" +
					"<td>" + data[i].shop_name + "</td>" +
					"<td>" + data[i].user_name + "</td>" +
					"<td>" + status + "</td>" +
					"<td><a onclick=\"check(" + data[i].shop_id + "," + data[i].shop_status + ")\">审核</a><a>详细信息</a></td>" +
					+"</tr>";
				select2_tbody.append(tpl);
			}

		},
		beforeSend : function(index) {
			layer.load();
		}
	});
}