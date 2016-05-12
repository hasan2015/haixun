$(function() {
	// alert("pg-bind.pageinit");
	$("#pg-bind-submit").bind(
			"click",
			function() {
				// alert("submit.click");
				// var formData = $("#pg-bind").serialize();
				var datas = {
					"aname" : $("#aname").val(),
					"pwd" : $("#pwd").val()

				};

				$.ajax({
					type : 'post',
					url : "/hxxk/page/bindOpenid.do",
					dataType : 'json', // 接受数据格式
					// (这里有很多,常用的有html,xml,js,json)
					traditional : true,
					// cache: false,
					data : datas,
					success : function(msg) {
						// alert("1"+msg.result);
						var wxaccount = msg.result;
						// alert("code="+msg.code);
						if (msg.code == RESULT_CODE_SUCCESS) {
							// alert("success=" + wxaccount);
							// TODO 如何返回上个页面
							$.mobile.changePage("myinfo.jsp",
									"{transition:'slideup'}");
						} else {
							alert("" + wxaccount);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) { // 失败
						alert(JSON.stringify(XMLHttpRequest));
						alert(textStatus);
						alert(errorThrown);
					}
				});
				return false;// 如果没有这句，则会重新进入pagebeforecreate
			});
});