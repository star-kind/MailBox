<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<html>
<head>

<script type="text/javascript"
	src="${basePath}/JavaScript/vue.min.2.6.10.js"></script>
<script type="text/javascript" src="${basePath}/Jquery/jq.js"></script>

<link href="${basePath}/ExternalCSSFrame/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="${basePath}/ExternalCSSFrame/bootstrap-theme.css"
	rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>错误信息页</title>
</head>
<body>
	<div style="text-align: center;">
		<b>${errMsg}</b> <br> <br>
		<h1>
			倒计时
			<h2 id="watch"></h2>
			秒
		</h1>
		<br> <br>
		<h2 id="intervalClock"></h2>
		<br> <br>
		<div>
			<a href="login.jsp">返回登录页</a>
		</div>
	</div>

</body>

<script type="text/javascript">
	var t = 10;
	var time = document.getElementById('intervalClock');
	time.innerText = t;

	var watch = document.getElementById('watch');
	watch.innerText = t;

	function intervalTrigger() {
		t--;
		time.innerHTML = t;

		if (t <= 0) {
			location.href = 'login.jsp';
			clearInterval(inter);
		}
	}

	//interval (时间上的)间隔,间隙,间歇
	var inter = setInterval('intervalTrigger()', t * 100);
</script>

</html>