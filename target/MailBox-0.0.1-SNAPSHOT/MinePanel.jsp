<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript"
	src="${basePath}/JavaScript/vue.min.2.6.10.js"></script>
<script type="text/javascript"
	src="${basePath}/JavaScript/SwitchDivide.js"></script>

<script type="text/javascript" src="${basePath}/Jquery/jq.js"></script>

<link href="${basePath}/ExternalCSSFrame/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="${basePath}/ExternalCSSFrame/bootstrap-theme.css"
	rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>邮件系统主面板</title>

<style>
li {
	display: inline;
	/* float:left;  */
	list-style: none;
}
/* CSS注释：加list-style:none去掉li默认产生"点" */
</style>

</head>
<body
	style="font-family: 'Courier New', Courier, monospace; font-size: 28px;">

	<!-- 顶栏 -->
	<jsp:include page="Top.jsp"></jsp:include>

	<div style="text-align: center;">
		<div style="text-align: center;">
			<h3>${account.acname}的邮箱</h3>
			<div>
				<a href="RevampKeyword.jsp">点此修改密码</a>
			</div>
			<div>
				<h3>${defated }</h3>
				<h3>${success }</h3>
			</div>
		</div>
		<br>

		<div>
			<ul>
				<li><a href="#" onclick="opens(1)"> 写信 </a></li>
				<span> | </span>

				<!-- <li><a href="#" onclick="opens(2)"> 收信箱[已读] </a></li>
				<span> | </span> -->

				<li><a href="exhibit_transmit_list" onclick="opens(3)"> 发件箱
				</a></li>
				<span> | </span>

				<li><a href="transmit_letter_show_draft_list"
					onclick="opens(4)"> 草稿箱 </a></li>
				<span> | </span>

				<li><a href="RecycleLettersStoreListHandler"
					onclick="opens(5)"> 垃圾箱 </a></li>
				
				<!-- <span> | </span>
				<li><a href="#" onclick="opens(6)"> 未读之箱 </a></li> -->
			</ul>
		</div>

		<!-- 书写信件区域 -->
		<div style="display: none;" id="room1" class="div_module">
			<jsp:include page="WriteNewTransmiterLetter.jsp"></jsp:include>
		</div>

		<!-- 收件箱:他者至吾 -->
		<div style="display: none;" id="room2" class="div_module">
			<h3>收件箱(已读)</h3>
			<table border="1"
				style="border-collapse: collapse; margin-left: 10%; width: 80%; text-align: center;">
				<thead>
					<tr>
						<th>多选<input type="checkbox"></th>
						<th>发件予某之人</th>
						<th>标题</th>
						<th>收信时间</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<br>
			<div>
				<button>删除-移入垃圾箱</button>
				<button>彻底删除</button>
			</div>
		</div>

		<!-- 发件箱 -->
		<div style="display: none;" id="room3" class="div_module">
			<jsp:include page="TransmittedLetterBox.jsp"></jsp:include>
		</div>

		<!-- 草稿箱:吾至他者 -->
		<div style="display: none;" id="room4" class="div_module">
			<jsp:include page="DraftBox.jsp"></jsp:include>
		</div>

		<!-- 垃圾箱 -->
		<div style="display: none;" id="room5" class="div_module">
			<jsp:include page="RecycleBox.jsp"></jsp:include>
		</div>
		
		<!-- 未读之箱 -->
		<div style="display: none;" id="room6" class="div_module">
			
		</div>

	</div>
</body>

</html>
