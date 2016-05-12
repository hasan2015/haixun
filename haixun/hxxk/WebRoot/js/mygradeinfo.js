//在"去的"页面触发，在过渡动画开始前。
$(document).on("pagebeforeshow", "#p-mygradeinfo", function() {
	updateRefreshdate();
	// alert("第二个界面的参数：" + getParameterByName('g-gradeid'));
//	alert("g_mygradeid="+g_mygradeid);
	getGradeinfoByGradeid(g_mygradeid);

});
// 当页面已初始化，并且在 jQuery Mobile 已完成页面增强之后，触发该事件。
$(document).on(
		"pageinit",
		"#p-mygradeinfo",
		function() {
			// click事件是sec内部
			$("[name='p2-section']").bind(
					'collapsibleexpand',
					function(e, u) {
						// alert(e);
						// 动画展示选中区域
						$("html,body").animate(
								{
									scrollTop : $(e.target).offset().top
											- $("#header-p-gradeinfo").height()

								}, 1000);
					});

		});

// 根据条件（区域+学期+时间段）获取包含 可报班级的 专业列表
function getGradeinfoByGradeid(mygradeid) {
	var datas = {
		"mygradeid" : mygradeid
	};
//	alert(mygradeid);
	$.ajax({
		url : '/hxxk/page/getMygradeinfoByid.do',
		type : 'get', // 数据发送方式
		dataType : 'json', // 接受数据格式 (这里有很多,常用的有html,xml,js,json)
		traditional : true,
		data : datas, // 要传递的数据
		error : function(XMLHttpRequest, textStatus, errorThrown) { // 失败
			alert(JSON.stringify(XMLHttpRequest));
			alert(textStatus);
			alert(errorThrown);
		},
		success : function(msg) {
			//alert(msg.result);
			//alert("成功.msg=" + msg.result.code);
			var mygrade = msg.result;
			var grade = msg.result.ygrade;
			if (mygrade&&grade) {
				$("#p-reservedcode").text(mygrade.reservedcode);
				$("#p-reservedcode").attr("style","color:blue;");
				$("#p-overtime").text(mygrade.overtime);
				$("#p-gradeApplystatus").text(mygrade.applystatus);
				
				$("#p-code").text(grade.code);
				$("#p-area").text(grade.area);
				$("#p-year").text(grade.year);
				$("#p-term").text(grade.term);
				$("#p-degree").text(grade.degree);
				$("#p-startdate").text(grade.startdate);
				$("#p-enddate").text(grade.enddate);
				$("#p-times").text(grade.times);
				$("#p-type").text(grade.type);
				$("#p-phone").text(grade.phone);
				$("#p-costdetail").text(grade.costdetail);
				$("#p-cost").text(grade.cost);
				$("#p-cost").attr("style","color:red;");
				$("#p-description").text(grade.description);
				$("#p-agelimit").text(grade.agelimit);
				$("#p-genderlimit").text(grade.genderlimit);
				$("#p-ability").text(grade.ability);
				$("#p-manner").text(grade.manner);
				$("#p-specialty").text(grade.yspecialty.name);
				yschedules = grade.yschedules;
				// alert(yschedules);
				if (yschedules) {
					var lies = [];
					// alert(yschedules.length);
					for ( var i in yschedules) {
						// alert(yschedules[i].week);
						lies.push('<li data-icon="false"><a href="#">'
								+ yschedules[i].week
								+ '</a><p class="hx-ui-li-aside">'
								+ yschedules[i].starttime + '-'
								+ yschedules[i].endtime + " "
								+ yschedules[i].address + '</p></li>');

					}
					// alert(lies);
					$("#lv-schedule").empty();
					$("#lv-schedule").append(lies);
					$("#lv-schedule").listview("refresh");
				}
			}
		}
	});
}