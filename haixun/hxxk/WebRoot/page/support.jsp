<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<title>平台客服</title>
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>

<link rel="stylesheet" href="../css/qsn.css?t=<%=new Date().getTime()%>">
<script type="text/javascript"
	src="../js/public.js?t=<%=new Date().getTime()%>"></script>

</head>
<body>
	<div id="page-bind" data-role="page">
		<div data-role="main" class="ui-content">
			<ul data-role="listview">
				<li data-role="list-divider">联系方式:</li>
				<li><a href="#">QQ：<span class="ui-li-aside-hx-support">139 0538 853</span></a></li>
				<li><a href="#">手机：<span class="ui-li-aside-hx-support">136 8576 1215</span></a></li>
				<li><a href="#">邮箱：<span class="ui-li-aside-hx-support">13685761215@163.com</span></a></li>
				<li data-role="list-divider">打赏/鼓励:</li>
				<li><a href="#">支付宝<span class="ui-li-aside-hx-support">13685761215</span></a></li>
			</ul>
		</div>
	</div>
</body>
</html>