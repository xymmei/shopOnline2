/**
 * 
 */
$(document).ready(function() {
	function request(callback, url, type) {
		layui.use('layer', function() {
			var layer = layui.layer;
			$.ajax({
				type : "POST",
				url : url,
				async : true,
				dataType : "json",
				data : {
					"goods_type" : type
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
	request(function(data) {
		console.log(data);
		//alert(1);
		var hotGoods = $("#hotGoods");
		hotGoods.empty();
		for (var i = 0; i < data.length; i++) {
			var tpl = "<div class=\"row\"><a href=\"single.jsp?goods_id=" + data[i].goods_id + "\"><div class=\"col-md-5 col-sm-5 col-xs-5\" style=\"padding:0px;margin:12px 0;\">" +
				"<img src=\"img/info/3.jpg\" alt=\"\" class=\"img-responsive\">" +
				"</div>" +
				"<div class=\"col-md-7 col-sm-7 col-xs-7\" style=\"padding-right: 0;padding-top:10px;\">" +
				"<h5>" + data[i].goods_name + "</h5>" +
				"<p>店铺：" + data[i].shop_name + "</p>" +
				"<p>价格：￥" + data[i].goods_price + "</p>" +
				"<p>销量：" + data[i].goods_sale + "</p>"
				+ "</div></a></div>";
			hotGoods.append(tpl);
		}
	}, "index/topGoods.action", "");
	request(function(data) {
		//alert(1);
		var hotShop = $("#hotShop");
		hotShop.empty();
		for (var i = 0; i < data.length; i++) {
			var tpl = "<div class=\"row\"><a href=\"shop/shopinfo.jsp?shop_id=" + data[i].shop_id + "\"><div class=\"col-md-5 col-sm-5 col-xs-5\" style=\"padding:0px;margin:12px 0;\">" +
				"<img src=\"img/info/3.jpg\" alt=\"\" class=\"img-responsive\">" +
				"</div>" +
				"<div class=\"col-md-7 col-sm-7 col-xs-7\" style=\"padding-right: 0;padding-top:10px;\">" +
				"<h5>" + data[i].shop_name + "</h5>" +
				"<p>销量：" + data[i].shop_sale + "</p>" +
				"<p>简介：" + data[i].shop_describe + "</p>"
				+ "</div></a></div>";
			hotShop.append(tpl);
		}
	}, "index/topShops.action", "");
	for (var i = 0; i < 6; i++) {
		type_array = [ '服饰鞋帽', '美妆个护', '休闲零食', '箱包奢品', '生鲜水果', '钟表首饰' ];
		(function(i) {
			request(function(data) {
				console.log(data);
				var tpl = "";
				for (var k = 0; k < data.length; k++) {
					tpl += "<div class=\"row info-content\"><a href=\"single.jsp?goods_id=" + data[k].goods_id + "\">\
											<div class=\"col-md-5 col-sm-5 col-xs-5\">\
												<img src=\"img/info/8.jpg\" class=\"img-responsive\">\
											</div>\
											<div class=\"col-md-7 col-sm-7 col-xs-7\">\
												<h4>" + data[k].goods_name + "</h4>\
												<p class=\"hidden-xs\">" + data[k].goods_describe + "</p>\
												<p><span>价格：￥</span>" + data[k].goods_price + "</p>\
											</div></a>\
										</div>";

				}
				//console.log(tpl);
				$(".item").eq(i).empty().append(tpl);
			}, "index/classify.action", type_array[i])
		})(i);
	}
	layui.use('element', function() {
		var element = layui.element();
	});
	$("#loadMore").click(function() {
		//alert($(".layui-this").html());
		request(function(data) {
			var tpl = "";
			for (var k = 0; k < data.length; k++) {
				tpl += "<div class=\"row info-content\"><a href=\"single.jsp?goods_id=" + data[k].goods_id + "\">\
										<div class=\"col-md-5 col-sm-5 col-xs-5\">\
											<img src=\"img/info/8.jpg\" class=\"img-responsive\">\
										</div>\
										<div class=\"col-md-7 col-sm-7 col-xs-7\">\
											<h4>" + data[k].goods_name + "</h4>\
											<p class=\"hidden-xs\">" + data[k].goods_describe + "</p>\
											<p>价格：￥" + data[k].goods_price + "</p>\
										</div></a>\
									</div>";

			}
			console.log(data);
			console.log($(".tab[class='tab layui-this']").index());
			var i = $(".tab[class='tab layui-this']").index();
			$(".item").eq(i).empty().append(tpl);
		}, "index/classify.action", $(".layui-this").html())
	})
});
$("#ser").click(function() {
	var ser_type = $("#ser_type").val();
	var keywords = $("#keywords").val();
	var status = 0;
	if (ser_type == "店铺") {
		status = 0;
	} else if (ser_type == "商品") {
		status = 1;
	}
	console.log(ser_type);
	console.log(status);
	$.ajax({
		type : "POST",
		url : "index/fuzzySearch.action",
		async : true,
		dataType : "json",
		data : {
			"status" : status,
			"keywords" : keywords
		},
		success : function(data) {
			layer.closeAll('loading');
			console.log(data);
			var tpl="";
			$("#result").empty();
			for(var i=0;i<data.length;i++){
				tpl+="<li style=\"height: 20px;cursor: pointer;\"><a href=\"single.jsp?goods_id="+data[i].goods_id+"\">"+data[i].goods_name+"</a></li>";
			}
			$("#result").empty().append(tpl);
		},
		beforeSend : function(index) {
			layer.load();
		}
	});
})