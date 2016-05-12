<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<title>可报班级</title>
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>
<script>
$('#secondView').on('pagecreate',function(event){
	alert('secondView.pagecreate');
});
</script>

	<section data-role="page" id="firstView" data-title="view1"
		data-theme="a">
		<header data-role="header" data-position="fixed"
			data-type="horizontal">
			<div data-role="controlgroup">
				<label for="select1" class="select">专业</label> <select
					name="select1" id="select">
					<option value="速写">速写</option>
					<option vlaue="卡通">卡通</option>>
				</select>
			</div>
			<div data-role="controlgroup">
				<label for="select2" class="select">专业啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊</label> <select
					name="select2" id="select">
				<option vlaue="请选择" data-placeholder="true">请选择</option>
					<option value="周六">周六</option>
					<option value="周日">周日</option>
					<option value="周一">周一</option>
					<option value="周二">周二</option>
					<option value="周三">周三</option>
					<option value="周四">周四</option>
					<option value="周五">周五</option>
				</select>
			</div>
		</header>
		<div datarole="controlgroup">
			<label for="select">专业</label> <select name="select3" id="select3"
				data-native-menu="true">
				<optgroup label="综合类"></optgroup>
				<option value="珠心算">珠心算</option>
				<option value="少年领袖">少年领袖</option>
				<optgroup label="科技类"></optgroup>
				<option value="模型">模型</option>
				<option value="电脑">电脑</option>
			</select>
		</div>
		<article data-role="collapsible" data-collapsed="false">
			<h3>可折叠区域</h3>
			<a href="#secondView" data-transition="pop">to 2</a> <a href="#"
				data-role="button" data-icon="home" data-inline="true">home</a>
		</article>
		<article data-role="collapsible" data-collapsed="false">
			<h3>可折叠区域</h3>
			<a href="#secondView" data-transition="pop">to 2</a> <a href="#"
				data-role="button" data-icon="home" data-inline="true">home</a>
		</article>
		<article data-role="content">
			<h3>列表区域</h3>
			<a href="#secondView" data-transition="pop">to 2</a> <a href="#"
				data-role="button" data-icon="home" data-inline="true">home</a>
		</article>
		<footer data-role="footer" data-position="fixed">页脚</footer>
	</section>
	<section data-role="page" id="secondView" data-title="view2">
		<header data-role="header">页头2</header>
		<article data-role="content">
			<a href="#firstView" data-transition="flip">to 1</a>
		</article>
		<footer data-role="footer">页脚</footer>
	</section>

</body>

</html>