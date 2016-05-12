<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>可报班级</title> 
<link rel="stylesheet"
	href="../jquery/css/themes/default/jquery.mobile-1.4.5.min.css">
<script type="text/javascript" src="../jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript"
	src="../jquery/js/jquery.touchSwipe.min.js"></script>

<link rel="stylesheet" href="../css/qsn.css?t=<%=new Date().getTime()%>">
<script type="text/javascript"
	src="../js/public.js?t=<%=new Date().getTime()%>"></script>
<script type="text/javascript"
	src="../js/opengrade.js?t=<%=new Date().getTime()%>"></script>
<script type="text/javascript"
	src="../js/gradeinfo.js?t=<%=new Date().getTime()%>"></script>

</head>
<body>
	<div id="p-opengrade"  data-role="page">
		<!-- 解决header会fixed失效 data-tap-toggle="false" style="position: fixed;"-->
		<div id="header-p-opengrade" data-role="header" data-type="horizontal"
			data-tap-toggle="false" style="position: fixed;"
			data-position="fixed">
			<div data-role="tabs" id="nav-tabs">
				<div id="navbar" data-role="navbar">
					<ul>
						<li><a id="a-area" href="#tab-area" data-ajax="false">区域</a></li>
						<li><a id="a-term" href="#tab-term" data-ajax="false">学期</a></li>
						<li><a id="a-date" href="#tab-date" data-ajax="false">时间段</a></li>
					</ul>
				</div>
				<div id="tab-area">
					<fieldset data-role="controlgroup" data-type="horizontal">
						<input type="radio" name="radio-area" id="radio-area-0" value="区域" />
						<label for="radio-area-0">不限</label> <input type="radio"
							name="radio-area" id="radio-area-1" value="活动中心" /> <label
							for="radio-area-1">活动中心</label> <input type="radio"
							name="radio-area" id="radio-area-2" value="发展中心" /> <label
							for="radio-area-2">发展中心</label> <input type="radio"
							name="radio-area" id="radio-area-3" value="城西分中心" /> <label
							for="radio-area-3">城西分中心</label> <input type="radio"
							name="radio-area" id="radio-area-4" value="城北分中心" /> <label
							for="radio-area-4">城北分中心</label>
					</fieldset>
				</div>
				<div id="tab-term">
					<fieldset data-role="controlgroup" data-type="horizontal">
						<input type="radio" name="radio-term" id="radio-term-0" value="学期" />
						<label for="radio-term-0">不限</label><input type="radio"
							name="radio-term" id="radio-term-1" value="春季" /> <label
							for="radio-term-1">春季</label> <input type="radio"
							name="radio-term" id="radio-term-2" value="暑期" /> <label
							for="radio-term-2">暑期</label> <input type="radio"
							name="radio-term" id="radio-term-3" value="秋季" /> <label
							for="radio-term-3">秋季</label> <input type="radio"
							name="radio-term" id="radio-term-4" value="寒假" /> <label
							for="radio-term-4">寒假</label>
					</fieldset>
				</div>
				<div id="tab-date">
					<!-- <label for="fset-week">哪一天？</label>-->
					<!-- <label><input type="checkbox" name="cbox-time" id="cbox-time-1"/> 哪一天？</label> -->
					<fieldset name="fset-week" data-role="controlgroup"
						data-type="horizontal">
						<label><input type="checkbox" name="cbox-week" value="周一" />
							周一</label> <label><input type="checkbox" name="cbox-week"
							value="周二" /> 周二</label><label><input type="checkbox"
							name="cbox-week" value="周三" /> 周三</label><label><input
							type="checkbox" name="cbox-week" value="周四" /> 周四</label><label><input
							type="checkbox" name="cbox-week" value="周五" /> 周五</label><label><input
							type="checkbox" name="cbox-week" value="周六" /> 周六</label><label><input
							type="checkbox" name="cbox-week" value="周日" /> 周日</label><label><input
							type="checkbox" name="cbox-week" value="每天" /> 每天</label><label><input
							type="checkbox" name="cbox-week" value="单号" /> 单号</label><label><input
							type="checkbox" name="cbox-week" value="双号" /> 双号</label>
					</fieldset>
					<!-- <label for="fset-time">哪个时间段？</label> -->
					<!-- <label><input type="checkbox" name="cbox-time"
						id="cbox-time-2" /> 哪个时间段？</label> -->
					<fieldset name="fset-time" data-role="controlgroup"
						data-type="horizontal">
						<button>时间：</button>
						<select name="sel-stime" id="sel-shour-1" data-native-menu="true">
							<!---->
							<option value="不限" data-placeholder="true" selected="selected">不限</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
						</select><select name="sel-stime" id="sel-smini-1" data-native-menu="true">
							<option value="不限" data-placeholder="true" selected="selected">不限</option>
							<option value="00">00</option>
							<option value="05">05</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="20">20</option>
							<option value="25">25</option>
							<option value="30">30</option>
							<option value="35">35</option>
							<option value="40">40</option>
							<option value="45">45</option>
							<option value="50">50</option>
							<option value="55">55</option>
						</select>
						<button>至</button>
						<select name="sel-etime" id="sel-ehour-1" data-native-menu="true">
							<option value="不限" data-placeholder="true" selected="selected">不限</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
						</select><select name="sel-etime" id="sel-emini-1" data-native-menu="true">
							<option value="不限" data-placeholder="true" selected="selected">不限</option>
							<option value="00">00</option>
							<option value="05">05</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="20">20</option>
							<option value="25">25</option>
							<option value="30">30</option>
							<option value="35">35</option>
							<option value="40">40</option>
							<option value="45">45</option>
							<option value="50">50</option>
							<option value="55">55</option>
						</select>
					</fieldset>
					<!-- <span data-role="button" id="btn-date">确定</span> -->
				</div>
			</div>
		</div>

		<!-- 
				
				<div data-role="controlgroup">
					<select id="sel-date">
						<option vlaue="时间段" data-placeholder="true">时间段</option>
						<option value="周六">周六</option>
						<option value="周日">周日</option>
						<option value="周一">周一</option>
						<option value="周二">周二</option>
						<option value="周三">周三</option>
						<option value="周四">周四</option>
						<option value="周五">周五</option>
					</select>
				</div> -->
		<div data-role="content"
			style="background: none; border: none; margin: 0em; padding: 0.2em;">
			<div id="cset-specialty" data-role="collapsible-set">
				<!-- 
			<section data-role="collapsible" data-collapsed="true">
				<h3>测试专业</h3>
				<ul id="lv-test" data-role="listview" data-theme="g">
					
					<li><a href="#">外教口语特色班</a>
						<p class="hx-ui-li-aside">Q3D02-1（幼儿体适能）</p></li>
						 
				</ul>
			</section>-->
			</div>
		</div>
		<p name="p-updatetime" class="hx-footer-updatetime">最后更新时间：2015年10月20日
			11:22:33</p>
	</div>
</body>
</html>