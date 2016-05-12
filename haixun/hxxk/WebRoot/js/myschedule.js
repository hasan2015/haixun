// JavaScript Document
$(document).on("pageinit", "#p-myschedule", function() {
	var date = new Date();
	// alert(date.getDay());//输出当前日期的星期数 0（周日） 到 6（周六）
	// alert("pageinit");
	// var tds=$("#tb_data td");
	// tds.get(0).innerHTML=date.getFullYear();
	// alert(tds.size());
	// alert($("#tr_week").find("td").size());
	$("#tr_week").find("td").each(function(index) {
		if (index == 0) {
			$(this).text(months[date.getMonth()]);
		} else {
			$(this).text(weeks[(date.getDay() + index - 1) % 7]);
		}
	});
	$("#tr_day").find("td").each(function(index) {
		$(this).text(date.getDate() + index);

	});
	// alert(date.Format("yyyy-MM-dd"));
	// alert(new Date(date.valueOf()+6*24*60*60*1000).Format("yyyy-MM-dd"));
	retMyschedules()
});
function getColByWeek(week) {
	var w = $("#tr_week").find("td");
	var i = 0;
	for (; i < 7; i++) {
		// alert($(w.get(i)).text());
		if ($(w.get(i)).text() == week)
			return i;
	}
}
function getRowByTime(sTime) {
	var hm = sTime.split(":");
	var h = hm[0];
	if (hm[1] >= 30)
		h = parseInt(h) + 1;
	return h - 8;
}
function retMyschedules() {
	var date = new Date();
	$
			.ajax({
				url : '/hxxk/page/retMyschedules.do',
				type : 'get', // 数据发送方式
				dataType : 'json', // 接受数据格式 (这里有很多,常用的有html,xml,js,json)
				traditional : true,
				data : {
					"startdate" : date.Format("yyyy-MM-dd"),
					"enddate" : new Date(date.valueOf() + 6 * 24 * 60 * 60
							* 1000).Format("yyyy-MM-dd")
				}, // 要传递的数据
				error : function(XMLHttpRequest, textStatus, errorThrown) { // 失败
					alert(JSON.stringify(XMLHttpRequest));
					alert(textStatus);
					alert(errorThrown);
				},
				success : function(msg) {
					// alert(msg.code+" "+msg.result.result+"
					// "+msg.result.code);
					if (msg.code == RESULT_CODE_SUCCESS) {
						// alert("成功.msg=" + msg.result);
						var schedules = msg.result;
						for (var i = 0; i < schedules.length; i++) {
							var w = getColByWeek(schedules[i].week);
							var h = getRowByTime(schedules[i].starttime);
							// alert(h);
							// alert(schedules[i].starttime);
							var cell = $("#tb_class tr:eq(" + h + ") td:eq("
									+ w + ")");
							// alert(cell);
							var text = schedules[i].specialtyName + "<br/>"
									+ schedules[i].starttime + "<br/>~"
									+ schedules[i].endtime + "<br/>"
									+ schedules[i].address;
							cell.html('<a href="#" onclick="toPageGradeinfo('
									+ schedules[i].gradeid
									+ ')" data-transition="slide">' + text
									+ '</a>');
							cell.css({
								"color" : "blue",
								"font-size" : "10px"
							});
						}
					} else {
						alert("获取数据失败：code=" + msg.code + " result="
								+ msg.result);
					}
				}
			});
}
function toPageGradeinfo(gradeid) {
	// alert('topage2..')
	g_gradeid = gradeid;
	// $.mobile.changePage("#p-gradeinfo","{transition:'slide'}");
	$.mobile.changePage("gradeinfo.jsp", "{transition:'slideup'}");

}