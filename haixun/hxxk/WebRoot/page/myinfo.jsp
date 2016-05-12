<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<title>我的资料</title>
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>

<link rel="stylesheet" href="../css/qsn.css?t=<%=new Date().getTime()%>">
<script type="text/javascript"
	src="../js/public.js?t=<%=new Date().getTime()%>"></script>
<script type="text/javascript"
	src="../js/myinfo.js?t=<%=new Date().getTime()%>"></script>
	<!-- 
<script type="text/javascript"
	src="../js/bind.js?t=<%=new Date().getTime()%>"></script>
 -->
</head>
<body>
	<div id="p-myinfo" data-role="page">
		<!-- 解决header会fixed失效 data-tap-toggle="false" style="position: fixed;"-->
		<div id="header-p-myinfo" data-role="header">
			<img id="img-head" class="aligncenter" src="../image/p32-01.png" />
		</div>

		<div data-role="content" class="hx-content">
			<ul data-role="listview" data-inset="true">
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly01.png" alt="a-name"
						class="ui-li-icon ui-corner-none">真实姓名</a>
				<p class="hx-ui-li-aside" id="p-myinfo-name">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-id" class="ui-li-icon">身份证</a>
					<p class="hx-ui-li-aside" id="p-myinfo-id">
						<font color='gray'>330106201411020040</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-mobile" class="ui-li-icon">家长手机</a>
					<p class="hx-ui-li-aside" id="p-myinfo-mobile">
						<font color='gray'>未知</font>
					</p></li>
				<li data-role="list-divider"></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly01.png" alt="a-gender" class="ui-li-icon">性别</a>
					<p class="hx-ui-li-aside" id="p-myinfo-gender">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-birthday" class="ui-li-icon">生日</a>
					<p class="hx-ui-li-aside" id="p-myinfo-birthday">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-school" class="ui-li-icon">学校</a>
					<p class="hx-ui-li-aside" id="p-myinfo-school">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-grade" class="ui-li-icon">就读年级</a>
					<p class="hx-ui-li-aside" id="p-myinfo-grade">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-homephone"
						class="ui-li-icon">家庭电话</a>
					<p class="hx-ui-li-aside" id="p-myinfo-homephone">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-patriarch"
						class="ui-li-icon">家长姓名</a>
					<p class="hx-ui-li-aside" id="p-myinfo-patriarch">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-description"
						class="ui-li-icon">备注</a>
					<p class="hx-ui-li-aside" id="p-myinfo-description">
						<font color='gray'>未知</font>
					</p></li>
				<li data-role="list-divider"></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-nickname" class="ui-li-icon">昵称</a>
					<p class="hx-ui-li-aside" id="p-myinfo-nickname">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-country" class="ui-li-icon">国家</a>
					<p class="hx-ui-li-aside" id="p-myinfo-country">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly01.png" alt="a-province" class="ui-li-icon">省市</a>
					<p class="hx-ui-li-aside" id="p-myinfo-province">
						<font color='gray'>未知</font>
					</p></li>
				<li data-icon="false"><a href="#"><img
						src="../image/butterfly02.png" alt="a-city" class="ui-li-icon">城市</a>
					<p class="hx-ui-li-aside" id="p-myinfo-city">
						<font color='gray'>未知</font>
					</p></li>
			</ul>
		</div> 
	</div>
</body>
</html>