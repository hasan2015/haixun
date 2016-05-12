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
<script type="text/javascript"
	src="../js/public.js?t=<%=new Date().getTime()%>"></script>
<script type="text/javascript"
	src="../js/myschedule.js?t=<%=new Date().getTime()%>"></script> 
<script type="text/javascript"
	src="../js/gradeinfo.js?t=<%=new Date().getTime()%>"></script>
<style>
h1 {
	font-size: 16px;
	font-family: Arial;
}

.auto {
	table-layout: auto;
	width: 350px;
}

.fixed {
	table-layout: fixed;
	width: 350px;
}
body {
	margin-left: 1px;
	margin-top: 1px;
	margin-right: 1px;
	margin-bottom: 1px;
}
table { 
table-layout:fixed; 
word-break: break-all; 
word-wrap: break-word; 
}
</style>
<meta charset="utf-8">
</head>
<body>
<div id="p-myschedule" data-role="page">
<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table id="tb_date" width="100%" border="1" align="center" cellpadding="0" cellspacing="0">
      <tr id="tr_week">
        <td width="20" rowspan="2" align="center" valign="middle">十二月</td>
        <td height="50" align="center" valign="middle">周一</td>
        <td height="50" align="center" valign="middle">周二</td>
        <td height="50" align="center" valign="middle">周三</td>
        <td height="50" align="center" valign="middle">周四</td>
        <td height="50" align="center" valign="middle">周五</td>
        <td height="50" align="center" valign="middle">周六</td>
        <td height="50" align="center" valign="middle">周日</td>
      </tr>
      <tr id="tr_day">
        <td height="20" align="center" valign="middle">7</td>
        <td height="20" align="center" valign="middle">8</td>
        <td height="20" align="center" valign="middle">9</td>
        <td height="20" align="center" valign="middle">10</td>
        <td height="20" align="center" valign="middle">11</td>
        <td height="20" align="center" valign="middle">12</td>
        <td height="20" align="center" valign="middle">13</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table id="tb_class"width="100%" border="1" cellpadding="0" cellspacing="0" >
      <tr>
        <td width="20" height="80" align="center" valign="middle">8</td>
        <td height="80" align="center" valign="middle" >&nbsp;</td>
        <td height="80" align="center" valign="middle" >&nbsp;</td>
        <td height="80" align="center" valign="middle" >&nbsp;</td>
        <td height="80" align="center" valign="middle" >&nbsp;</td>
        <td height="80" align="center" valign="middle" >&nbsp;</td>
        <td height="80" align="center" valign="middle" >&nbsp;</td>
        <td height="80" align="center" valign="middle" >&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">9</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">10</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">11</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">12</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">13</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">14</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">15</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">16</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">17</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">18</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">19</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">20</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="20" height="80" align="center" valign="middle">21</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td height="80" align="center" valign="middle">22</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
        <td height="80" align="center" valign="middle">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</div>

</body>
</html>