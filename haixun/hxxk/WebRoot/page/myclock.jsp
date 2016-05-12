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
<!--
<script type="text/javascript"
	src="../jquery/js/jquery1.8.3.js"></script>	 
   
    
    <script type="text/javascript"  src="../jquery/js/jquery-1.9.1.js"></script> -->

<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.touchSwipe.min.js"></script>
<!--
<link rel="stylesheet" type="text/css"
	href="../jquery/css/jquery.datetimepicker.css" />
<script type="text/javascript"
	src="../jquery/js/jquery.datetimepicker.js"></script>
-->
<link rel="stylesheet" href="../css/qsn.css?t=<%=new Date().getTime()%>">
<link rel="stylesheet" href="../css/t.css?t=<%=new Date().getTime()%>">
<!--
		<link rel="stylesheet" href="../jquery/css/jqueryMobile.css">
		<script type="text/javascript" src="../jquery/js/jqueryMobile.js"></script>
		<link href="../jquery/css/mobiscroll.css" rel="stylesheet"  >
		<script src="../jquery/js/mobiscroll.js" type="text/javascript"></script>
 -->
<script type="text/javascript" src="../jquery/js/mobiscroll.js"></script>
<link href="../jquery/css/mobiscroll.css" rel="stylesheet" />

<script type="text/javascript"
	src="../js/public.js?t=<%=new Date().getTime()%>"></script>
<script type="text/javascript"
	src="../js/myclock.js?t=<%=new Date().getTime()%>"></script>

</head>
<body>
	<div id="p-myclock" data-role="page">
		<div data-role="content" class="hx-content">
			<ul id="ul-clock" data-role="listview" data-inset="true">
				<li><a><img src="../image/clock03.jpg" />
						<h3>Broken Bells</h3>
						<p>Broken Bells</p></a></li>
				<li><a><img src="../image/clock04.jpg" />
						<h3>Broken Bells</h3>
						<p>Broken Bells</p></a><a href="#p-clockdetail" data-rel="opens"
					data-position-to="window" data-transition="pop">Pur</a></li>
				<li><a><img src="../image/clock06.jpg" />
						<h3>Broken Bells</h3>
						<p>Broken Bells</p></a></li>
				<li><a><img src="../image/clock09.jpg" />
						<h3>Broken Bells</h3>
						<p>Broken Bells</p></a></li>
				<li><img src="../image/clock14.jpg" />
					<h3>Broken Bells</h3>
					<p>Broken Bells</p></li>
				<li><img src="../image/clock16.jpg" />
					<h3>Broken Bells</h3>
					<p>Broken Bells</p></li>
				<li><img src="../image/clock18.jpg" />
					<h3>Broken Bells</h3>
					<p>Broken Bells</p></li>
				<li><img src="../image/clock21.jpg" />
					<h3>Broken Bells</h3>
					<p>Broken Bells</p></li>
			</ul>
		</div>
	</div>
	<div id="p-clockdetail" data-role="page">
		<div data-role="content">
			<form>
				<label for="cname">名称：</label> <input type="text" name="cname"
					id="cname" placeholder="闹钟名称... ...">
				<!--  -->
				<label for="starttime">开始时间：</label> <input type="text"
					data-role="datebox" id="starttime" name="starttime" />
				<!--  -->
				<label for="endtime">结束时间：</label> <input type="text"
					data-role="datebox" id="endtime" name="endtime" />
				<!--  -->
				<label for="intervalm">间隔时间（/分）：</label> <input type="number"
					name="intervalm" id="intervalm" value="5">
				<!--  -->
				<label for="duration">持续时间（/秒）：</label> <input type="number"
					name="duration" id="duration" value="10"> <input
					id="number" />
				<!--  -->
				<input type="submit" id="submit" value="确定">

			</form>
		</div> 
	</div>
</body>
</html>