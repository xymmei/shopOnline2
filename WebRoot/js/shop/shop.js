/**
 * 
 */
$(document).ready(function() {
	layui.use('layer', function() {
		function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null) return unescape(r[2]);
			return null;
		}
		var layer = layui.layer;
		var shop_goods_list = $("#shop_goods_list");
		var tpl="";
		$.ajax({
			type : "POST",
			url : "business/Goods_chsp.action",
			async : true,
			dataType : "json",
			data : {
				"shop_id" : GetQueryString("shop_id")
			},
			success : function(data) {
				layer.closeAll('loading');
				console.log(data);
				for(var i=0;i<data.length;i++){
					tpl+="<div class=\"col-lg-3 col-md-4 col-sm-6 col-xs-12\">\
						<a href=\"../single.jsp?goods_id="+data[i].goods_id+"\"><div class=\"thumbnail\">\
							<img src=\"../img/case/2.jpg\">\
						</div>\
						<div class=\"caption\">\
							<h4>"+data[i].goods_name+"</h4>\
							<p>"+data[i].goods_describe+"</p>\
						</div></a>\
					</div>";
					
				}
				shop_goods_list.empty().append(tpl);
			},
			beforeSend : function(index) {
				layer.load();
			}
		});
	});
})