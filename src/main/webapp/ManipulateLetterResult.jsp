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
<title>操作邮件结果信息页</title>

</head>
<body>
	<div style="text-align: center; font-size: 24px; margin-top: 38px;">
		<div>
			<span> <a href="transmittedLetterBoxList">返回发件箱列表</a> <!--  -->
				<span> | </span> <!--  --> <a href="transmitMailPanel">返回面板主页</a> <!--  -->
				<span> | </span> <!--  --> <a href="transmit_letter_show_draft_list">返回草稿箱列表</a>
				<!--  -->
				<span> | </span> 
				<!--  --> 
				<a href="RecycleLettersStoreListHandler">返回回收站列表</a>
			</span>
		</div>

		<div>
			<h1>
				倒计时
				<h2 id="watch"></h2>
				秒
			</h1>
			<br> <br>
			<h2 id="intervalClock"></h2>

			<div>
				<p>${hint }</p>
			</div>
		</div>
</body>

<script type="text/javascript">
	var t = 30;
	var time = document.getElementById('intervalClock');
	time.innerText = t;

	var watch = document.getElementById('watch');
	watch.innerText = t;

	function intervalTrigger() {
		t--;
		time.innerHTML = t;

		if (t <= 0) {
			location.href = 'transmittedLetterBoxList';
			clearInterval(inter);
		}
	}

	//interval (时间上的)间隔,间隙,间歇
	var inter = setInterval('intervalTrigger()', t * 100);
</script>
</html>