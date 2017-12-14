/**
 * 
 */
layui.use('element', function() {
	var element = layui.element();
});
$(document).ready(function() {
	layui.use('form', function() {
		function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null) return unescape(r[2]);
			return null;
		}
		var form = layui.form();
		var exchange_num = $("#exchange_num");
		var sale_sum = $("#sale_sum");

		var shop_name1 = $("#shop_name1");
		/*var shop_name1 =$("#shop_name1");*/
		var shop_type1 = $("#shop_type1");
		var shop_address1 = $("#shop_address1");
		var shop_describe1 = $("#shop_describe1");
		function request(callback, url) {
			//console.log(GetQueryString("shop_id"));
			layui.use('layer', function() {
				var layer = layui.layer;
				$.ajax({
					type : "POST",
					url : url,
					async : true,
					data : {
						"shop_id" : GetQueryString("shop_id")
					},
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
		request(function(data) {
			//console.log(data);
			shop_name1.val(data[0].shop_name);
			shop_type1.val(data[0].shop_address);
			shop_address1.val(data[0].shop_type);
			shop_describe1.val(data[0].shop_describe);
		}, "shop/Ms_chms.action"); //请求商店信息
		request(function(data) {
			//console.log(data);
			exchange_num.html(data[0].countOrders);
			sale_sum.html(data[0].ordersPrice);
		}, "shop/Od_ordersPrice.action"); //请求两个数据
		request(function(data) {
			console.log(data);
			//exchange_num.html(data.exchange_num);
			//sale_sum.html(data.sale_sum);
			var tpl = "";
			$("#good_list").empty();
			var goods_string = "";
			var goods_status = 0;
			var color = "";
			for (var i = 0; i < data.length; i++) {
				if (data[i].goods_status == 1) {
					goods_string = "下架";
					color = "btn-success";
				} else {
					goods_string = "上架";
					color = "layui-btn-normal";
				}
				tpl += "<tr>\
						<td>" + parseInt(i + 1) + "</td>\
						<td>" + data[i].goods_name + "</td>\
						<td>" + data[i].goods_price + "</td>\
						<td>" + data[i].goods_describe + "</td>\
						<td>" + data[i].goods_sum + "</td>\
						<td>" + data[i].goods_sale + "</td>\
						<td>" + data[i].goods_sale * data[i].goods_price + "</td>\
						<td><button class=\"layui-btn " + color + "\" onclick=\"goods_change(" + data[i].goods_status + "," + data[i].goods_id + ")\">" + goods_string + "</button></td>\
					</tr>";
			}
			$("#good_list").empty().append(tpl);
		}, "shop/Goods_chsp.action") //请求所有商品

		$("#myDate2").change(function() {
			var start = $("#myDate1").val();
			var end = $("#myDate2").val();
			//console.log("开始日期：" + start);
			//console.log("结束日期：" + end);
			$.ajax({
				type : "POST",
				url : "shop/Od_ordersCount.action",
				async : true,
				dataType : "json",
				data : {
					"sMonth" : start,
					"eMonth" : end,
					"shop_id" : GetQueryString("shop_id")
				},
				success : function(data) {
					layer.closeAll('loading');
					//console.log(data);
					var tpl = "";
					$("#shop_tongji").empty();
					for (var i = 0; i < data.length; i++) {

						tpl += "<tr>\
								<td>" + parseInt(i + 1) + "</td>\
								<td>" + data[i].year + "年" + data[i].month + "月" + "</td>\
								<td>" + data[i].orders_sum + "</td>\
							  </tr>";
					}
					$("#shop_tongji").empty().append(tpl);
				},
				beforeSend : function(index) {
					layer.load();
				}
			});
		});
		$("#shop_cancel").click(function() {
			$('#myModal1').modal('hide');
		})
		$("#goods_cancel").click(function() {
			$('#myModal').modal('hide');
		})

		var shop_right = $("#shop_right");
		var goods_right = $("#goods_right");
		shop_right.click(function() {
			var shop_name = $("#shop_name").val();
			var shop_type = $("#shop_type").val();
			var shop_describe = $("#shop_describe").val();
			var shop_address = $("#shop_address").val();
			//console.log("商店名称：" + shop_name);
			$.ajax({
				type : "POST",
				url : "shop/Ms_edit.action",
				async : true,
				dataType : "json",
				data : {
					"shop_id" : GetQueryString("shop_id"),
					"shop_name" : shop_name,
					"shop_type" : shop_type,
					"shop_describe" : shop_describe,
					"shop_address" : shop_address
				},
				success : function(data) {
					layer.closeAll('loading');
					//console.log(data[0]);
					if (data[0] == 1) {
						layer.msg("更新成功");
						request(function(data) {
							//console.log(data);
							shop_name1.val(data[0].shop_name);
							shop_type1.val(data[0].shop_address);
							shop_address1.val(data[0].shop_type);
							shop_describe1.val(data[0].shop_describe);
						}, "shop/Ms_chms.action");
					} else {
						layer.msg("更新失败");
					}
				},
				beforeSend : function(index) {
					layer.load();
				}
			});
		});
		var order_search = $("#order_search");
		order_search.change(function() {
			var type = 0;
			if ($("#order_search").val() == "待发货") {
				type = 0;
			} else if ($("#order_search").val() == "待签收") {
				type = 1;
			} else if ($("#order_search").val() == "已签收") {
				type = 2;
			} else {
				type = 3;
			}
			console.log(type);
			$.ajax({
				type : "POST",
				url : "shop/Od_chod.action",
				async : true,
				dataType : "json",
				data : {
					"orders_status" : type,
					"shop_id" : GetQueryString("shop_id")
				},
				success : function(data) {
					layer.closeAll('loading');
					console.log(data);
					var tpl="";
					$("#orders_select").empty();
					var status="";
					var fun="";
					for(var i=0;i<data.length;i++){
						if(data[i].orders_status==0){
							status="发货";
							fun="onclick=\"send("+data[i].orders_id+")\"";
						}else if(data[i].orders_status==1){
							status="待签收";
						}else{
							status="已签收";
						}
						tpl+="<tr>\
									<td>"+parseInt(i+1)+"</td>\
									<td>"+data[i].user_name+"</td>\
									<td>"+data[i].orders_id+"</td>\
									<td>"+data[i].goods_name+"</td>\
									<td>"+data[i].orders_goods_sum+"</td>\
									<td>"+data[i].goods_price+"</td>\
									<td>"+data[i].orders_price+"</td>\
									<td><button class=\"layui-btn layui-btn-normal\" "+fun+">"+status+"</button></td>\
								</tr>";
					}
					$("#orders_select").empty().append(tpl);
				},
				beforeSend : function(index) {
					layer.load();
				}
			});
		});
		goods_right.click(function() {
			var goods_name = $("#goods_name").val();
			var goods_type = $("#goods_type").val();
			var goods_price = $("#goods_price").val();
			var goods_describe = $("#goods_describe").val();
			var goods_sum = $("#goods_sum").val();
			console.log(goods_name + goods_type + goods_price + goods_describe + goods_sum);
			$.ajax({
				type : "POST",
				url : "shop/Goods_insert.action",
				async : true,
				dataType : "json",
				data : {
					"shop_id":GetQueryString("shop_id"),
					"goods_name" : goods_name,
					"goods_type" : goods_type,
					"goods_price" : goods_price,
					"goods_describe" : goods_describe,
					"goods_sum" : goods_sum,
					"shop_id" : GetQueryString("shop_id")
				},
				success : function(data) {
					layer.closeAll('loading');
					console.log(data);
					if (data[0] == 0) {
						layer.msg("添加物品失败");
					} else {
						request(function(data) {
							console.log(data);
							//exchange_num.html(data.exchange_num);
							//sale_sum.html(data.sale_sum);
							var tpl = "";
							$("#good_list").empty();
							var goods_string = "";
							var goods_status = 0;
							var color = "";
							for (var i = 0; i < data.length; i++) {
								if (data[i].goods_status == 1) {
									goods_string = "下架";
									color = "btn-success";
								} else {
									goods_string = "上架";
									color = "layui-btn-normal";
								}
								tpl += "<tr>\
										<td>" + parseInt(i + 1) + "</td>\
										<td>" + data[i].goods_name + "</td>\
										<td>" + data[i].goods_price + "</td>\
										<td>" + data[i].goods_describe + "</td>\
										<td>" + data[i].goods_sum + "</td>\
										<td>" + data[i].goods_sale + "</td>\
										<td>" + data[i].goods_sale * data[i].goods_price + "</td>\
										<td><button class=\"layui-btn " + color + "\" onclick=\"goods_change(" + data[i].goods_status + "," + data[i].goods_id + ")\">" + goods_string + "</button></td>\
									</tr>";
							}
							$("#good_list").empty().append(tpl);
						}, "shop/Goods_chsp.action")
					}
				},
				beforeSend : function(index) {
					layer.load();
				}
			});
		});

	});
})
function goods_change(status, goods_id) {
	//console.log(status,goods_id);
	$.ajax({
		type : "POST",
		url : "shop/Goods_Del.action",
		async : true,
		dataType : "json",
		data : {
			"goods_status" : status,
			"goods_id" : goods_id
		},
		success : function(data) {
			layer.closeAll('loading');
			//console.log(data);
			request(function(data) {
				//console.log(data);
				var tpl = "";
				$("#good_list").empty();
				var goods_string = "";
				var goods_status = 0;
				var color = "";
				for (var i = 0; i < data.length; i++) {
					if (data[i].goods_status == 1) {
						goods_string = "下架";
						color = "btn-success";
					} else {
						goods_string = "上架";
						color = "layui-btn-normal";
					}
					tpl += "<tr>\
							<td>" + parseInt(i + 1) + "</td>\
							<td>" + data[i].goods_name + "</td>\
							<td>" + data[i].goods_price + "</td>\
							<td>" + data[i].goods_describe + "</td>\
							<td>" + data[i].goods_sum + "</td>\
							<td>" + data[i].goods_sale + "</td>\
							<td>" + data[i].goods_sale * data[i].goods_price + "</td>\
							<td><button class=\"layui-btn " + color + "\" onclick=\"goods_change(" + data[i].goods_status + "," + data[i].goods_id + ")\">" + goods_string + "</button></td>\
						</tr>";
				}
				$("#good_list").empty().append(tpl);
			}, "shop/Goods_chsp.action")
		},
		beforeSend : function(index) {
			layer.load();
		}
	});
}
function request(callback, url) {
	//console.log(GetQueryString("shop_id"));
	layui.use('layer', function() {
		var layer = layui.layer;
		$.ajax({
			type : "POST",
			url : url,
			async : true,
			data : {
				"shop_id" : GetQueryString("shop_id")
			},
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
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}
function send(orders_id){
	var tag=$("#order_search").val();
	console.log(tag);
	var type=0;
	if(tag=="待发货"){
		type=0;
	}else if(tag=="待签收"){
		type=1;
	}else if(tag=="已签收"){
		type=2;
	}else if(tag=="全部订单"){
		type=3;
	}
	$.ajax({
		type : "POST",
		url : "shop/Od_upod.action",
		async : true,
		data : {
			"orders_id" : orders_id
		},
		dataType : "json",
		success : function(data) {
			layer.closeAll('loading');
			console.log(data);
			if(data[0]==0){
				layer.msg("发货失败");
			}else
			$.ajax({
				type : "POST",
				url : "shop/Od_chod.action",
				async : true,
				dataType : "json",
				data : {
					"orders_status" : type,
					"shop_id" : GetQueryString("shop_id")
				},
				success : function(data) {
					layer.closeAll('loading');
					//console.log(data);
					var tpl="";
					$("#orders_select").empty();
					var status="";
					var fun="";
					for(var i=0;i<data.length;i++){
						if(data[i].orders_status==0){
							status="发货";
							fun="onclick=\"send("+data[i].orders_id+")\"";
						}else if(data[i].orders_status==1){
							status="待签收";
						}else{
							status="已签收";
						}
						tpl+="<tr>\
									<td>"+parseInt(i+1)+"</td>\
									<td>"+data[i].user_name+"</td>\
									<td>"+data[i].orders_id+"</td>\
									<td>"+data[i].goods_name+"</td>\
									<td>"+data[i].orders_goods_sum+"</td>\
									<td>"+data[i].goods_price+"</td>\
									<td>"+data[i].orders_price+"</td>\
									<td><button class=\"layui-btn layui-btn-normal\" "+fun+">"+status+"</button></td>\
								</tr>";
					}
					$("#orders_select").empty().append(tpl);
				},
				beforeSend : function(index) {
					layer.load();
				}
			});
		},
		beforeSend : function(index) {
			layer.load();
		}
	});
}