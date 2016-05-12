var oldTab;
// 当页面已初始化，并且在 jQuery Mobile 已完成页面增强之后，触发该事件。
$(document).on("pageinit", "#p-mygrade", function() {
	//alert("pageinit");
	hideAllNavTab();
	$("#navbar").on("click", function(e) { 
		var newTab = $(e.target).attr("href");
		if (oldTab != newTab) {
			$(newTab).show();
			if (oldTab) {
				$(oldTab).hide();
			}
		} else {
			$(newTab).toggle();
		} 
		oldTab = newTab;
		$("#cset-specialty").trigger("updatelayout"); 
	}); 
	// 点击复选框(暂时修改为周次复选框选择，这样可以省一个复选框)，隐藏条件选择窗口，刷新列表
	$("[name='cbox-week']:checkbox").bind("click", function() {
		$("#tab-date").hide();
		// $("#cset-specialty").trigger("updatelayout");
		getSpecialtyOfOpengrade();
	});
	// 周次条件 变化了，则取消对应复选框的的选择
	// 时间段条件 变化了，，则取消对应复选框的的选择
	$("input[type=radio]").on("change", function(e) {
		// alert($(e.target).attr("name"));
		var ids = $(e.target).attr("name").split("-");
		if (ids.length > 1) {
			$("#a-" + ids[1]).text($(e.target).val());
			hideAllNavTab();
			// TODO 根据条件刷新
			getSpecialtyOfOpengrade();
		}
		// alert($(e.target).val());
		// hideAllNavTab();
	});
	// 时间段选择变化后隐藏菜单，且刷新列表
	$("[name='sel-stime']").bind("change", function(e, u) {
		// alert($(e.target).attr("id"));//显示的是change后的值
		// 如果新值等于“不限”，才判断
		if ($(e.target).val() == "不限") {
			// 如果小时或分钟有一个选择“不限”那么另一个也自动调整为“不限”
			if ($("#sel-shour-1").val() == "不限") {
				$("#sel-smini-1").val("不限");// 只选中，但是没有刷新显示
				$('#sel-smini-1').selectmenu('refresh');
			}
			if ($("#sel-smini-1").val() == "不限") {
				$("#sel-shour-1").val("不限");// 只选中，但是没有刷新显示
				$('#sel-shour-1').selectmenu('refresh');
			}
		}
		// $("#sel-smini-1 option[text='不限']").attr("selected", true);
		// $("#sel-smini-1").find("option[text='不限']").attr("selected",true);
		hideAllNavTab();
		// TODO 根据条件刷新
		getSpecialtyOfOpengrade();
	});

	$("[name='sel-etime']").bind("change", function(e, u) {
		// alert($(e.target).attr("id"));//显示的是change后的值
		// 如果新值等于“不限”，才判断
		if ($(e.target).val() == "不限") {
			// 如果小时或分钟有一个选择“不限”那么另一个也自动调整为“不限”
			if ($("#sel-ehour-1").val() == "不限") {
				$("#sel-emini-1").val("不限");// 只选中，但是没有刷新显示
				$('#sel-emini-1').selectmenu('refresh');
			}
			if ($("#sel-emini-1").val() == "不限") {
				$("#sel-ehour-1").val("不限");// 只选中，但是没有刷新显示
				$('#sel-ehour-1').selectmenu('refresh');
			}
		}
		hideAllNavTab();
		// TODO 根据条件刷新
		getSpecialtyOfOpengrade();
	});
});
$(document).on("scrollstart", function() {
	//alert("scrollstart");
	hideAllNavTab();
	// $("#cset-specialty").trigger("updatelayout");
});
$(document).on("pagebeforecreate", "#p-mygrade", function() {
	updateRefreshdate();
	//alert("pagebeforecreate");
	getSpecialtyOfOpengrade();
});

function hideAllNavTab(tab) {
	if ($("#tab-area"))
		$("#tab-area").hide();
	if ($("#tab-term"))
		$("#tab-term").hide();
	// $("#tab-specialty").hide();
	if ($("#tab-date"))
		$("#tab-date").hide();

	if ($("#cset-specialty"))
		$("#cset-specialty").trigger("updatelayout");
}
// 组合查询条件
function getSearchParams() {
	// 哪一天
	var selWeek = [];
	// if ($("#cbox-time-1").is(":checked")) {
	$.each($("[name='cbox-week']:checked"), function(i, n) {
		selWeek[i] = n.value;
	});
	// }
	// 哪个时间段
	var stime = null, etime = null;
	// if ($("#cbox-time-2").is(":checked")) {
	if ($("#sel-shour-1").val() != '不限') {// 小时选中，则条件有效
		stime = $("#sel-shour-1").val() + ":";// + $("#sel-smini-1").val();
		if ($("#sel-smini-1").val() != '不限') {
			stime += $("#sel-smini-1").val();
		} else {
			stime += "00";// 不限制时，开始时间选择最小
		}
	}
	if ($("#sel-ehour-1").val() != '不限') {// 小时选中，则条件有效
		etime = $("#sel-ehour-1").val() + ":";// + $("#sel-smini-1").val();
		if ($("#sel-emini-1").val() != '不限') {
			etime += $("#sel-emini-1").val();
		} else {
			etime += "59";// 不限制时，结束时间选择最大
		}
	}

	return {
		"pageNow" : 0,
		"pageSize" : 1000,
		"total" : 0,
		"specialtyid" : null,
		"area" : $("#a-area").text() != "区域" ? $("#a-area").text() : null,
		"term" : $("#a-term").text() != "学期" ? $("#a-term").text() : null,
		"weeks" : selWeek,
		"starttime" : stime,
		"endtime" : etime
	};
}
// 根据条件（区域+学期+时间段）获取包含 可报班级的 专业列表
function getSpecialtyOfOpengrade() {
	// alert($("#a-area").text());
	/*
	 * var selWeek = []; $.each($("[name='cbox-week']:checked"), function(i, n) {
	 * selWeek[i] = n.value; }); var datas = { "pageNow" : 0, "pageSize" : 1000,
	 * "total" : 0, "area" : $("#a-area").text() != "区域" ? $("#a-area").text() :
	 * null, "term" : $("#a-term").text() != "学期" ? $("#a-term").text() : null,
	 * "weeks" : selWeek, "starttime" : $("#sel-shour-1").val() + ":" +
	 * $("#sel-smini-1").val(), "endtime" : $("#sel-ehour-1").val() + ":" +
	 * $("#sel-emini-1").val() };
	 */
	// alert(datas.area);
	// if ($("#a-area") && $("#a-area").val() != "区域") {
	// eval("datas.area=" + $("#a-area").val());
	// }
	$
			.ajax({
				url : '/hxxk/page/getSpecialtyOfOpengrade.do',
				type : 'get', // 数据发送方式
				dataType : 'json', // 接受数据格式 (这里有很多,常用的有html,xml,js,json)
				traditional : true,
				data : getSearchParams(), // 要传递的数据
				error : function(XMLHttpRequest, textStatus, errorThrown) { // 失败
					alert(JSON.stringify(XMLHttpRequest));
					alert(textStatus);
					alert(errorThrown);
				},
				success : function(msg) {
					// alert("成功.msg=" + msg.result.length);
					var sections = [];
					var specialties = msg.result;
					for (var i = 0; i < specialties.length; i++) {
						var sid = specialties[i].specialtyid;
						sections
								.push('<section name="p1-section" data-role="collapsible" id="sec-'
										+ sid
										+ '"><h3 name="p1-h3" id="h3-'
										+ sid
										+ '">'
										+ specialties[i].name
										+ '('
										+ specialties[i].gradecount
										+ ')'
										+ '</h3>'
										+ '<ul id="lv-'
										+ sid
										+ '" data-role="listview" data-theme="g">'
										// + '<li><a href="#">外教口语特色班</a>'
										// + '<p
										// class="hx-ui-li-aside">Q3D02-1（幼儿体适能）</p></li>'
										+ '</ul></section>');
						// $("#lv-"+specialties[i].specialtyid).listview("refresh");

					}
					// alert(sections);
					$("#cset-specialty").empty();
					$("#cset-specialty").append(sections);
					$("#cset-specialty").trigger("create");
					// $("#cset-specialty").trigger("updatelayout");
					$("[name='p1-section']").bind('collapsibleexpand',
							function(e, u) {
								// $(this).hide();
								// alert("section.click.sid=" +
								// $(this).attr("id"));
								var ids = $(this).attr("id").split("-");
								if (ids.length > 1) {
									// alert(ids[1]);
									getOpengradesBySpecialtyid(ids[1]);
								}
								// alert("section.collapsibleexpand");
							});
					$("[name='p1-h3'],[name='p1-section']").bind('click',
							function(e, u) {
								// alert("h3&section.click.e.target.id="+$(e.target).attr("id"));
								// 隐藏导航的展开选项::bug,点击其他空白处，header会自动解除固定
								hideAllNavTab();
							});
					// TODO 为何没有效果呢？答：jqm不支持上下滑动，需要用其他插件
					// $("h3,section").bind('tap', function() {
					// alert("Touch...");
					// });
					// TODO 这是屏幕滚动，不是滑动
					/*
					 * $("h3,section").swipe( { //Generic swipe handler for all
					 * directions swipe : function(event, direction, distance,
					 * duration, fingerCount, fingerData) { //$(this).text("You
					 * swiped " + direction ); alert(direction); }, //Default is
					 * 75px, set to 0 for demo so any distance triggers swipe
					 * threshold : 0 });
					 */
				}
			});
}
// 根据专业id，查找该专业的 可报班级
function getOpengradesBySpecialtyid(specialtyid) {
	var datas = getSearchParams();
	datas.specialtyid = specialtyid;
	/*
	 * { "specialtyid" : specialtyid, "pageNow" : 0, "pageSize" : 10, "total" :
	 * 0 };
	 */
	// alert(datas.endtime);
	// $("#lv-mygrade").append('<li><a href="#">和平</a><p
	// class="hx-ui-li-aside">adsfdsa</p></li>');
	$.ajax({
		url : '/hxxk/page/getOpengradesBySpecialtyid.do',
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
			// alert("成功.msg=" + msg.result.length);
			var li = [];
			var mygrades = msg.result;
			if (mygrades.length > 0) {
				for (var i = 0; i < mygrades.length; i++) {
					// var applystatus=mygrades[i].applystatus;
					var gid = mygrades[i].gradeid;

					li.push('<li><a href="#" onclick="toPageGradeinfo(' + gid
							+ ')" data-transition="slide">' + mygrades[i].code
							+ '</a><p id="p2-' + gid
							+ '" class="hx-ui-li-aside2">'
							+ mygrades[i].degree + '</p><p id="p-' + gid
							+ '" class="hx-ui-li-aside">'
							+ mygrades[i].applystatus + '</p></li>');
					// li.push('<li><a href="#">和平</a><p
					// class="hx-ui-li-aside">adsfdsa</p></li>');

				}
				// alert(li);
				$("#lv-" + specialtyid).empty();
				$("#lv-" + specialtyid).append(li);
				$("#lv-" + specialtyid).listview("refresh");
				// $("#lv-" + specialtyid).trigger("updatelayout");
				for (var i = 0; i < mygrades.length; i++) {
					var gid = mygrades[i].gradeid;
					// alert(mygrades[i].applystatus);
					// 状态
					if (mygrades[i].applystatus == '报名进行中')
						$("#p-" + gid).css("color", "#006600");// 绿色
					// 级别
					if (mygrades[i].degree == '高级')
						$("#p2-" + gid).css("color", "#FF0000");// 纯红
					else if (mygrades[i].degree == '中级')
						$("#p2-" + gid).css("color", "#FF1493");// 深粉
					else if (mygrades[i].degree == '初级')
						$("#p2-" + gid).css("color", "#FF00FF");// 紫红
					else if (mygrades[i].degree == '启蒙')
						$("#p2-" + gid).css("color", "#FFA500");// 橙色
				}
				// 动画展示选中区域
				$("html,body").animate(
						{
							scrollTop : $("#sec-" + specialtyid).offset().top
									- $("#header-p-mygrade").height()

						}, 1000);
			}
		}
	});
}

function toPageGradeinfo(gradeid) {
	// alert('topage2..')	
	g_gradeid = gradeid;
	//$.mobile.changePage("#p-gradeinfo","{transition:'slide'}");
	$.mobile.changePage("gradeinfo.jsp","{transition:'slideup'}");
	
}
function getAllOpengrades() {
	var datas = {
		"pageNow" : 0,
		"pageSize" : 10,
		"total" : 0
	};
	// alert($("#lv-mygrade"));
	// $("#lv-mygrade").append('<li><a href="#">和平</a><p
	// class="hx-ui-li-aside">adsfdsa</p></li>');
	$.ajax({
		url : '/hxxk/page/retAllGrades.do',
		type : 'get', // 数据发送方式
		dataType : 'json', // 接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data : datas, // 要传递的数据
		error : function(XMLHttpRequest, textStatus, errorThrown) { // 失败
			alert(JSON.stringify(XMLHttpRequest));
			alert(textStatus);
			alert(errorThrown);
		},
		success : function(msg) {
			// alert("成功.msg=" + msg.result.length);
			var li = [];
			var mygrades = msg.result;
			for (var i = 0; i < mygrades.length; i++) {
				li.push('<li><a href="#">' + mygrades[i].specialtyName
						+ '</a><p class="hx-ui-li-aside">'
						+ mygrades[i].applystatus + '</p></li>');
				// li.push('<li><a href="#">和平</a><p
				// class="hx-ui-li-aside">adsfdsa</p></li>');
			}
			// alert(li);
			$("#lv-mygrade").append(li);
			$("#lv-mygrade").listview("refresh");
			$("#lv-mygrade").trigger("updatelayout");

		}
	});

}