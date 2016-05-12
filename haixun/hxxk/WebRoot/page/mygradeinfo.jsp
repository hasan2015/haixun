<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
<title>班级信息</title>
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
	src="../js/mygradeinfo.js?t=<%=new Date().getTime()%>"></script>

</head>
<body>
	<div id="p-mygradeinfo" data-role="page">
		<!-- 解决header会fixed失效 data-tap-toggle="false" style="position: fixed;"-->
		<div data-role="content" class="hx-content">
			<!--<div id="cset-info" data-role="collapsible-set">
				 -->
			<section name="p2-section" data-role="collapsible"
				data-collapsed="false">
				<h3>报名信息</h3>
				<ul id="lv-apply" data-role="listview" data-theme="g">
					<li data-icon="false"><a href="#">预约号</a>
						<p class="hx-ui-li-aside" id="p-reservedcode">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">过期时间</a>
						<p class="hx-ui-li-aside" id="p-overtime">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">报名状态</a>
						<p class="hx-ui-li-aside" id="p-gradeApplystatus">
							<font color='gray'>未知</font>
						</p></li>
				</ul>
			</section>
			<section name="p2-section" data-role="collapsible"
				data-collapsed="false">
				<h3>基本信息</h3>
				<ul id="lv-base" data-role="listview" data-theme="g">					
					<li data-icon="false"><a href="#">班级代码</a>
						<p class="hx-ui-li-aside" id="p-code">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">开课时间</a>
						<p class="hx-ui-li-aside" id="p-startdate">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">结课时间</a>
						<p class="hx-ui-li-aside" id="p-enddate">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">总费用</a>
						<p class="hx-ui-li-aside" id="p-cost">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">费用明细</a>
						<p class="hx-ui-li-aside" id="p-costdetail">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">学年</a>
						<p class="hx-ui-li-aside" id="p-year">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">学期</a>
						<p class="hx-ui-li-aside" id="p-term">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">专业</a>
						<p class="hx-ui-li-aside" id="p-specialty">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">专业程度</a>
						<p class="hx-ui-li-aside" id="p-degree">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">课次</a>
						<p class="hx-ui-li-aside" id="p-times">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">班级类型</a>
						<p class="hx-ui-li-aside" id="p-type">
							<font color='gray'>未知</font>
						</p></li>
					<li data-icon="false"><a href="#">咨询电话</a>
						<p class="hx-ui-li-aside" id="p-phone">
							<font color='gray'>未知</font>
						</p></li>
				</ul>
				<div name="p2-section" data-role="collapsible"
					data-collapsed="false" data-inset="false"
					data-collapsed-icon="arrow-d" data-expanded-icon="arrow-u"
					data-iconpos="right">
					<h3>区域</h3>
					<p id="p-area"></p>
				</div>
				<div name="p2-section" data-role="collapsible"
					data-collapsed="false" data-inset="false"
					data-collapsed-icon="arrow-d" data-expanded-icon="arrow-u"
					data-iconpos="right">
					<h3>班级描述</h3>
					<p id="p-description">
						<font color='gray'>未知</font>
					</p>
				</div>

			</section>
			<section name="p2-section" data-role="collapsible"
				data-collapsed="false" data-icon="false">
				<h3>课表信息</h3>
				<ul id="lv-schedule" data-role="listview" data-theme="g">
					<li data-icon="false"><a href="#">周六</a>
						<p class="hx-ui-li-aside">14:20-15:20 发展中心217室</p></li>
				</ul>
			</section>
			<section name="p2-section" name="sec-age&gender"
				data-role="collapsible" data-collapsed="false" data-icon="false">
				<h3>年级或年龄、性别要求</h3>
				<ul id="lv-agelimit" data-role="listview" data-theme="g">
					<div name="p2-section" data-role="collapsible"
						data-collapsed="false" data-collapsed-icon="arrow-d"
						data-expanded-icon="arrow-u" data-iconpos="right">
						<h3>学员年级范围</h3>
						<p id="p-agelimit">
							<font color='gray'>未知</font>
						</p>
					</div>
					<li data-icon="false"><a href="#">性别要求</a>
						<p class="hx-ui-li-aside" id="p-genderlimit">
							<font color='gray'>未知</font>
						</p></li>

				</ul>
			</section>
			<section name="p2-section" data-role="collapsible"
				data-collapsed="false" data-icon="false">
				<h3>班级特征</h3>
				<div name="p2-section" data-role="collapsible"
					data-collapsed="false" data-inset="false"
					data-collapsed-icon="arrow-d" data-expanded-icon="arrow-u"
					data-iconpos="right">
					<h3>能力特征</h3>
					<p id="p-ability">
						<font color='gray'>未知</font>
					</p>
				</div>
				<div name="p2-section" data-role="collapsible"
					data-collapsed="false" data-inset="false"
					data-collapsed-icon="arrow-d" data-expanded-icon="arrow-u"
					data-iconpos="right">
					<h3>态度特征</h3>
					<p id="p-manner">
						<font color='gray'>未知</font>
					</p>
				</div>
			</section>
			<!--</div>-->
		</div>
		<div data-role="footer" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a href="#" data-rel="back" data-icon="back">后退</a></li>
					<li><a href="#" data-icon="arrow-r">调课</a></li>
					<li><a href="#" data-icon="info">竞拍</a></li>
					<li><a href="#" data-icon="check">收藏</a></li>
				</ul>
			</div>
		</div>
		<p name="p-updatetime" class="hx-footer-updatetime">最后更新时间：2015年10月20日
			11:22:33</p>
	</div>
</body>
</html>