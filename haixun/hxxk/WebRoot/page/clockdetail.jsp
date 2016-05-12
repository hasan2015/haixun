<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>我的闹钟</title>
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.touchSwipe.min.js"></script>
<link rel="stylesheet" type="text/css" href="../jquery/css/jquery.datetimepicker.css"/>
<script src="../jq/jquery.datetimepicker.js"></script>
<link rel="stylesheet" href="../css/qsn.css?t=<%=new Date().getTime()%>">
<link rel="stylesheet" href="../css/t.css?t=<%=new Date().getTime()%>">
<script type="text/javascript"
	src="../js/public.js?t=<%=new Date().getTime()%>"></script>
<script type="text/javascript"
	src="../js/myclock.js?t=<%=new Date().getTime()%>"></script>

</head>
<body>
	<div id="p-clockdetail" data-role="page">
		<form>
			<label for="fname" class="ui-hidden-accessible">姓名：</label>
			<!--  -->
			<input type="text" name="fname" id="fname" placeholder="姓名...">
			<label for="pwd" class="ui-hidden-accessible">密码：</label>
			<!--  -->
			<input type="text" name="pwd" id="pwd" placeholder="密码...">
		</form>
	</div>
</body>
</html>