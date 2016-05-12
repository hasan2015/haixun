$(document).on("pagebeforeshow", "#p-myinfo", function() {
	// alert("未绑定！");

	$.ajax({
		url : '/hxxk/page/getMyinfo.do',
		type : 'get', // 数据发送方式
		dataType : 'json', // 接受数据格式 (这里有很多,常用的有html,xml,js,json)
		traditional : true,
		data : null, // 要传递的数据
		error : function(XMLHttpRequest, textStatus, errorThrown) { // 失败
			alert(JSON.stringify(XMLHttpRequest));
			alert(textStatus);
			alert(errorThrown);
		},
		success : function(msg) {
			var wxaccount = msg.result;
			var user = wxaccount.user;
			// alert("user="+user);
			$("#nickname").text(wxaccount.nickname);
			$("#gender").text(wxaccount.sex);

			if (!user) {
				// alert("未绑定无法查看“个人信息”和“已报班级”！首先绑定帐号");
				promptBind("未绑定无法查看“个人信息”，首先绑定帐号。");
				// TODO 如果没有绑定，则先提示绑定才能访问该页面。
				// 必须加载bind.jsp的所有资源，如引入bind.js
				// $.mobile.changePage("bind.jsp", "{transition:'slideup'}");
			} else {
				$("#p-myinfo-name").text(wxaccount.user.name);
				$("#p-myinfo-id").text(wxaccount.user.id);
				$("#p-myinfo-mobile").text(wxaccount.user.mobile);
				$("#p-myinfo-gender").text(wxaccount.user.gender);
				$("#p-myinfo-birthday").text(wxaccount.user.birthday);
				$("#p-myinfo-school").text(wxaccount.user.school);
				$("#p-myinfo-grade").text(wxaccount.user.grade);
				$("#p-myinfo-homephone").text(wxaccount.user.homephone);
				$("#p-myinfo-patriarch").text(wxaccount.user.patriarch);
				$("#p-myinfo-description").text(wxaccount.user.description);
				$("#p-myinfo-nickname").text(wxaccount.nickname);
				$("#p-myinfo-country").text(wxaccount.country);
				$("#p-myinfo-city").text(wxaccount.city);
				$("#p-myinfo-province").text(wxaccount.province);
			}
		}
	});

});