<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>我的课程表</title>
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.touchSwipe.min.js"></script>

<link rel="stylesheet" href="../css/qsn.css?t=<%=new Date().getTime()%>">
<link rel="stylesheet" href="../css/t.css?t=<%=new Date().getTime()%>">
<script type="text/javascript"
	src="../js/public.js?t=<%=new Date().getTime()%>"></script>

</head>
<body>
	<div id="p-myschedule" data-role="page">
		<!-- 解决header会fixed失效 data-tap-toggle="false" style="position: fixed;"-->
		<div id="header-p-myschedule" data-role="header">
			<h2>Highlighted rows, borders</h2>
			<table class="bordered">
				<thead>

					<tr>
						<th>#</th>
						<th>IMDB Top 10 Movies</th>
						<th>Year</th>
					</tr>
				</thead>
				<tr>
					<td>1</td>
					<td>The Shawshank Redemption</td>

					<td>1994</td>
				</tr>
				<tr>
					<td>2</td>
					<td>The Godfather</td>
					<td>1972</td>
				</tr>
				<tr>

					<td>3</td>
					<td>The Godfather: Part II</td>
					<td>1974</td>
				</tr>
				<tr>
					<td>4</td>
					<td>The Good, the Bad and the Ugly</td>
					<td>1966</td>

				</tr>
				<tr>
					<td>5</td>
					<td>Pulp Fiction</td>
					<td>1994</td>
				</tr>
				<tr>
					<td>6</td>
					<td>12 Angry Men</td>

					<td>1957</td>
				</tr>
				<tr>
					<td>7</td>
					<td>Schindler's List</td>
					<td>1993</td>
				</tr>
				<tr>

					<td>8</td>
					<td>One Flew Over the Cuckoo's Nest</td>
					<td>1975</td>
				</tr>
				<tr>
					<td>9</td>
					<td>The Dark Knight</td>

					<td>2008</td>
				</tr>
				<tr>
					<td>10</td>
					<td>The Lord of the Rings: The Return of the King</td>
					<td>2003</td>
				</tr>

			</table>

			<br>
			<br>

			<h2>Zebra stripes, footer</h2>
			<table class="zebra">
				<thead>
					<tr>
						<th>#</th>
						<th>IMDB Top 10 Movies</th>
						<th>Year</th>

					</tr>
				</thead>
				<tfoot>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
					</tr>
				</tfoot>
				<tr>

					<td>1</td>
					<td>The Shawshank Redemption</td>
					<td>1994</td>
				</tr>
				<tr>
					<td>2</td>
					<td>The Godfather</td>
					<td>1972</td>

				</tr>
				<tr>
					<td>3</td>
					<td>The Godfather: Part II</td>
					<td>1974</td>
				</tr>
				<tr>
					<td>4</td>
					<td>The Good, the Bad and the Ugly</td>

					<td>1966</td>
				</tr>
				<tr>
					<td>5</td>
					<td>Pulp Fiction</td>
					<td>1994</td>
				</tr>

				<tr>
					<td>6</td>
					<td>12 Angry Men</td>
					<td>1957</td>
				</tr>
				<tr>
					<td>7</td>
					<td>Schindler's List</td>

					<td>1993</td>
				</tr>
				<tr>
					<td>8</td>
					<td>One Flew Over the Cuckoo's Nest</td>
					<td>1975</td>
				</tr>
				<tr>

					<td>9</td>
					<td>The Dark Knight</td>
					<td>2008</td>
				</tr>
				<tr>
					<td>10</td>
					<td>The Lord of the Rings: The Return of the King</td>

					<td>2003</td>
				</tr>
			</table>
		</div>
		<div data-role="content"
			style="background: none; border: none; margin: 0em; padding: 0.2em;">
			<div id="cset-specialty" data-role="collapsible-set"></div>

		</div>
		<span id="p-myschedule-updatetime" class="hx-footer-updatetime">最后更新时间：2015年10月20日
			11:22:33</span>
	</div>
</body>
</html>