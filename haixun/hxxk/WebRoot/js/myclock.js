var currYear = (new Date()).getFullYear();
var opt_data = {
	preset : 'datetime', // 日期格式 date（日期）|datetime（日期加时间）
	theme : 'jqm', // 皮肤样式
	display : 'modal', // 显示方式
	/*
	 * modal —— 显示在中间 inline —— 直接显示在页面中 bubble —— 类似于tip弹出显示 top —— 显示在顶部
	 * bottom —— 显示在底部
	 */
	mode : 'mixed', // 日期选择模式
	/*
	 * scroller —— 以wheel滑动方式选择 clickpick —— 显示 - + 按钮选择 mixed —— 兼容以上两种方式
	 */
	dateFormat : 'yy-mm-dd', // 日期格式
	setText : '确定', // 确认按钮名称
	cancelText : '取消',// 取消按钮名籍我
	dateOrder : 'yymmdd', // 面板中日期排列格式
	dayText : '日',
	monthText : '月',
	yearText : '年', // 面板中年月日文字
	// yearText : '年',
	monthText : '月',
	dayText : '日', // 面板中年月日文字
	startYear : currYear - 30, // 开始年份
	endYear : currYear + 5, // 结束年份
	// lang: 'zh',
	showNow : true,
	nowText : '今天',
	hourText : '小时',
	minuteText : '分'
};
// 使用定义插件
$(document).on("pageinit", "#p-clockdetail", function() {
	$("#starttime").mobiscroll(opt_data);
	var mobi = $("#endtime").mobiscroll({
		preset : 'datetime', // 日期格式 date（日期）|datetime（日期加时间）
		theme : 'jqm', // 皮肤样式
		display : 'modal', // 显示方式
		/*
		 * modal —— 显示在中间 inline —— 直接显示在页面中 bubble —— 类似于tip弹出显示 top —— 显示在顶部
		 * bottom —— 显示在底部
		 */
		mode : 'mixed', // 日期选择模式
		/*
		 * scroller —— 以wheel滑动方式选择 clickpick —— 显示 - + 按钮选择 mixed —— 兼容以上两种方式
		 */
		dateFormat : 'yy-mm-dd', // 日期格式
		setText : '确定', // 确认按钮名称
		cancelText : '取消',// 取消按钮名籍我
		dateOrder : 'yymmdd', // 面板中日期排列格式
		dayText : '日',
		monthText : '月',
		yearText : '年', // 面板中年月日文字
		// yearText : '年',
		monthText : '月',
		dayText : '日', // 面板中年月日文字
		startYear : currYear - 30, // 开始年份
		endYear : currYear + 5, // 结束年份
		// lang: 'zh',
		showNow : true,
		nowText : '今天',
		hourText : '小时',
		minuteText : '分',
		onBeforeShow : function(inst) {
			alert($('#starttime').val());
			return false;
		},
		stepMinute : 5

	});
	$("#submit").on('click', function(e) {
		// var d = new Date($("#starttime").val());
		// alert(d.getFullYear());

	});
});

$(document).on("pageinit", "#p-myclock", function() {
	$.ajax({
		type : 'get',
		url : "/hxxk/page/retclocks.do",
		dataType : 'json', // 接受数据格式
		// (这里有很多,常用的有html,xml,js,json)
		traditional : true,
		// cache: false,
		// data : datas,
		success : function(msg) {
			// alert("1"+msg.result);
			var result = msg.result;
			// alert("code="+msg.code);
			if (msg.code == RESULT_CODE_SUCCESS) {
				alert(" succeed::" + result);
				refreshMyclocks(msg.result)
			} else {
				alert(" failure::" + result);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { // 失败
			alert(JSON.stringify(XMLHttpRequest));
			alert(textStatus);
			alert(errorThrown);
		}
	});
});
function refreshMyclocks(clocks) {
	var lies = [];
	for (var i = 0; i < clocks.length; i++) {
		var cid = clocks[i].myclockid;
		lies.push('<li id="li-'+cid+'"><a><img src="../image/clock03.jpg" />' + '<h3>'
				+ clocks[i].name + '</h3><p>' + clocks[i].starttime + '---'
				+ clocks[i].endtime + '</p></a></li>');
		// $("#lv-"+clocks[i].specialtyid).listview("refresh");

	}
	// alert(lies);
	$("#ul-clock").empty();
	$("#ul-clock").append(lies);
	$("#ul-clock").listview("refresh");

}
/*
 * // 使用定义插件 $(document).on("pageinit", "#p-clockdetail", function() {
 * $('#txtBirthday').datetimepicker({ step : 5, inline : true, mask :
 * '9999-19-39 29:59' }); });
 */
function testclock() {
	// var datas = {
	// "aname" : $("#aname").val(),
	// "pwd" : $("#pwd").val()
	//
	// };

	$.ajax({
		type : 'get',
		url : "/hxxk/page/retclock.do",
		dataType : 'json', // 接受数据格式
		// (这里有很多,常用的有html,xml,js,json)
		traditional : true,
		// cache: false,
		// data : datas,
		success : function(msg) {
			// alert("1"+msg.result);
			var result = msg.result;
			// alert("code="+msg.code);
			if (msg.code == RESULT_CODE_SUCCESS) {
				alert(" succeed::" + result);
			} else {
				alert(" failure::" + result);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { // 失败
			alert(JSON.stringify(XMLHttpRequest));
			alert(textStatus);
			alert(errorThrown);
		}
	});
	return false;// 如果没有这句，则会重新进入pagebeforecreate

}