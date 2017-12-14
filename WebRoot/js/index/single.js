/**
 * 
 */

$(document).ready(function() {
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]);
		return null;
	}
	//alert(GetQueryString("goods_id"));
	layui.use('layer', function() {
		var layer = layui.layer;
		var goods_name=$("#goods_name");
		var goods_price=$("#goods_price");
		var goods_describe=$("#goods_describe");
		var shop_name=$("#shop_name");
		$.ajax({
			type : "POST",
			url : "index/showGoods.action",
			async : true,
			dataType : "json",
			data : {
				"goods_id" : GetQueryString("goods_id")
			},
			success : function(data) {
				layer.closeAll('loading');
				console.log(data);
				if(data==10000){
					window.location.href="user/login.jsp";
				}else{
					goods_describe.html(data[0].goods_describe);
					goods_price.html(data[0].goods_price);
					goods_name.html(data[0].goods_name);
					shop_name.html(data[0].shop_name);
				}
			},
			beforeSend : function(index) {
				layer.load();
			}
		});
		var buy_num=0;
		$("#buy_add").click(function(){
			buy_num++;
			$("#buy_sum").html(buy_num);
			$("#buy_des").removeAttr("disabled");
		});
		$("#buy_des").click(function(){
			buy_num--;
			$("#buy_sum").html(buy_num);
			if(buy_num == 0){
				$("#buy_des").attr("disabled","disabled");
			}
		});
		$("#buy").click(function(){
			//alert(buy_num);
			$.ajax({
				type : "POST",
				url : "user/QueryOrder_Create.action",
				async : true,
				dataType : "json",
				data : {
					"goods_id" : GetQueryString("goods_id"),
					"orders_goods_sum":buy_num,
					"goods_price":goods_price.html()
				},
				success : function(data) {
					layer.closeAll('loading');
					console.log(data);
					if(data==10000){
						window.location.href="user/login.jsp";
					}else if(data==0){
						layer.msg("购买失败，请重新购买");
					}else{
						layer.msg("购买成功！");
					}
				},
				beforeSend : function(index) {
					layer.load();
				}
			});
		});
	});
})