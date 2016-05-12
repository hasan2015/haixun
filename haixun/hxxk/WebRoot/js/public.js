var g_gradeid = 0;
var g_mygradeid = 0;
var RESULT_CODE_SUCCESS = "000000";
var RESULT_CODE_USER_NOT_LOGIN = "-3";
var weeks=["周日","周一","周二","周三","周四","周五","周六"];
var months=["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];
function promptBind(msg){
	alert(msg);
	$.mobile.changePage("bind.jsp", "{transition:'slideup'}");
	
}
function updateRefreshdate() {
	$.ajax({
		url : '/hxxk/page/getInstitution.do',
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
			//alert("成功.msg=" + msg.result);
			var institution = msg.result;
			var updatetime = new Date(institution.refreshdate)
					.Format("yyyy年MM月dd日");
			// alert("text="+$("#sp-updatetime").text());
			$("[name='p-updatetime']").text("更新时间："+updatetime);
		}
	});
}
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
function getDayOfWeek(week){
	var i=0;
	for(;i<7;i++){
		if(weeks[i]==week)
		  return i;
	}
}
