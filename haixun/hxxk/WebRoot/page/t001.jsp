<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<link rel="apple-touch-startup-image" href="../image/t100.jpg">
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "//hm.baidu.com/hm.js?88db0dd8e3a7d518f09e0523e71ecb72";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>
<style>
figure, figcaption, header {
	display: block;
	margin: 0 auto;
	text-align: center;
}

html {
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
	text-size-adjust: 100%;
}
</style>
<style>
#square {
	width: 100px;
	height: 100px;
	background: #cc;
	position: absolute;
}
</style>
</head>
<body>
	<header> 主要导航 </header>
	<a href="http://www.baidu.com">Directions<</a> 网页内容
	<figure>
		<img alt="HTML5 badge" src="../image/t001.jpg">
	</figure>
	<figcaption>Chinadaily.com.cn is the largest English
		portal in China, providing news, business information, BBS, learning
		materials. The Website has channels as China
		英语（English），属于印欧语系中日耳曼语族下的西日耳曼语支，是由古代从德国、
		荷兰及丹麦等斯堪的纳维亚半岛周边移民至不列颠群岛的盎格鲁、撒克逊和朱特部落的日耳曼人所说</figcaption>
	<form>
		<label>First name:</label><input type="text" name="fname" /><br /> <label>Last
			name:</label><input type="text" name="lname" /><br />
	</form>
	<div id="targetarea">
		<a href="http://www.baidu.com" target="_blank">Baidu</a>
	</div>
	<div id="main">
		<div id="square"></div>
	</div>
	<script>
		$('#square').bind(
				'touchmove',
				function(e) {
					e.preventDefault();
					var touch = e.originalEvent.touches[0]
							|| e.originalEvent.changedTouches[0];
					var elm = $(this).offset();
					var x = touch.pageXelm.left / 2;
					var y = touch.pageY - elm.top / 2;
					$(this).css('left', x + 'px');
					$(this).css('top', y + 'px');
				});
	</script>
	<footer> 页脚链接 </footer>
</body>

</html>