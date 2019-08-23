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
<title>修改密码</title>
</head>
<body>
	<div style="text-align: center;">
		<h3>修改密码</h3>
		<form name="revamp_kword_form" method="post"
			onsubmit="return submitValidate()" action="revamp">
			<p>
				请输入原密码 <input type="password" name="oldPassword" maxlength="16"
					placeholder="请输入原密码" required="required">
			</p>
			<p>
				请输入新密码 <input type="password" name="newPassword" maxlength="16"
					placeholder="请输入新密码" id="new_password" required="required">
			</p>
			<p>
				确认新密码 <input type="password" name="reNewPassword" maxlength="16"
					placeholder="请输入新密码" id="repeat_new_password" required="required">
			</p>
			<p>
				<input type="submit" value="confirm">
			</p>
		</form>
		<br>

		<div>
			<p style="color: red; font-size: 24px;">${fatalTip}</p>
		</div>

		<br> <br>
		<div>
			<a href="MinePanel.jsp">返回我的邮箱板块</a>
		</div>
	</div>

</body>

<script type="text/javascript">
	function submitValidate() {
		var oldPassword = document.forms['revamp_kword_form']['oldPassword'].value;
		var np = document.forms['revamp_kword_form']['newPassword'].value;
		var renp = document.forms['revamp_kword_form']['reNewPassword'].value;

		if (oldPassword === np) {
			alert('新旧密码不可再次一致');
			return false;
		}

		if (np !== renp) {
			alert('确认新密码出错');
			return false;
		}

	}
</script>
</html>