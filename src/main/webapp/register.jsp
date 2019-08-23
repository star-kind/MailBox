<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
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
<title>注册</title>
</head>
<body>
	<div style="text-align: center;">
		<h3>填写账户信息</h3>

		<form id="reg_form">
			<p>
				<label>名字：</label> <input type="text" name="acname"
					placeholder="请输入名字" maxlength="250">
			</p>

			<p>
				<label>副邮箱：</label> <input type="text" name="email"
					placeholder="请输入副邮箱，如无可空" maxlength="240">
			</p>

			<p>
				<label>性别：</label> <span><input type="radio" name="gender"
					value="0">女</span> <span><input type="radio" name="gender"
					value="1">男</span> <span><input type="radio" name="gender"
					value="2">保密<span>
			</p>

			<p>
				<label>密码：</label> <input type="password" name="password"
					placeholder="请输入密码" maxlength="16">
			</p>

			<p>
				<label>出生年月日：</label> <input type="date" name="birth">
			</p>

			<p>
				<input type="button" id="input_submits" value="提交">
			</p>

		</form>
		<br> <span> <a href="login.jsp">返回登录页</a>
		</span> <br>
		<div>
			<p id="error_message" style="color: red; font-size: 25px;"></p>
		</div>
	</div>

</body>
<script type="text/javascript">
	$("#input_submits").click(function() {
		/*禁止用户名包含特定字符*/
		var form = document.getElementById("reg_form");
		var formData = new FormData(form);

		var acname = formData.get('acname');
		var index = acname.indexOf(':');
		var at = acname.indexOf('@');
		console.log(index);

		if (!(index < 0)) {
			alert('invaild character')
			return false;
		} else if (!(at !== -1)) {
			alert('lack of @domain')
			return false;
		}

		var data = $('#reg_form').serialize();
		//console.log(data);
		var url = 'reg';

		$.ajax({
			'contentType' : "application/x-www-form-urlencoded; charset=UTF-8",
			'url' : url,
			'data' : {
				'account' : data
			},
			'type' : 'POST',
			'dataType' : 'text',
			'success' : function(r) {
				if (r == '200') {
					alert('注册成功')
					location.href = "login.jsp";
				} else if (r == '600') {
					$('#error_message').text('该账号名已被占用,请另行更换')
				} else if (r == '601') {
					$('#error_message').text('应填之信息尚未补全')
				}

			}

		});

	});
</script>

</html>