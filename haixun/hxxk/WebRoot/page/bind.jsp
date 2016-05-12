<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<title>绑定帐号</title>
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>

<link rel="stylesheet" href="../css/qsn.css?t=<%=new Date().getTime()%>">
<script type="text/javascript"
	src="../js/public.js?t=<%=new Date().getTime()%>"></script>
<script type="text/javascript"
	src="../js/bind.js?t=<%=new Date().getTime()%>"></script>
<script type="text/javascript"
	src="../js/myinfo.js?t=<%=new Date().getTime()%>"></script>

</head>
<body>
	<div id="pg-bind" data-role="page">
		<div data-role="header">
			<h1>账户绑定</h1>
		</div>
		<div data-role="main" class="ui-content">
			<form id="f-bind">
				<div data-role="fieldcontain">
					<input type="text" name="aname" id="aname" placeholder="用户名(孩子的身份证）)">
					<!--  -->
					<input type="text" name="pwd" id="pwd" placeholder="密码（手机号）">
					<button data-theme="b" id="pg-bind-submit" > 绑定</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>