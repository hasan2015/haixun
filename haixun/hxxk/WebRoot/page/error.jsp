<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<title>错误页</title>
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.touchSwipe.min.js"></script>

</head>
<body>
	<div id="p-myinfo" data-role="page">
		<!-- 解决header会fixed失效 data-tap-toggle="false" style="position: fixed;"-->
		<div id="header-p-myinfo" data-role="header" data-type="horizontal"
			data-tap-toggle="false" style="position: fixed;"
			data-position="fixed"></div>

		<div data-role="content">
			<p>登录失败。。</p>
		</div>
	</div>
</body>
</html>