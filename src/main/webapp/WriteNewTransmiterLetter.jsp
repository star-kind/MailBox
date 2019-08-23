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
<title>新撰一封邮件之页</title>

</head>
<body
	style="font-family: 'Courier New', Courier, monospace; font-size: 28px;">

	<div style="text-align: center;">

		<!-- 书写信件区域 -->
		<h3>信件编辑之面板</h3>
		<br>
		<form action="sendMail" method="post" enctype="multipart/form-data">

			<p>
				<input type="text" value=${account.acname } readonly="readonly"
					name="transmitter" placeholder=${account.acname
						}
					style="background-color: #78dceb;">
			</p>
			<p>
				<input type="text" name="receiver" placeholder="请输入收件人"
					maxlength="50" required="required">
			</p>
			<p>
				<input type="text" name="title" placeholder="请输入标题" maxlength="100"
					required="required">
			</p>

			<div>
				<textarea name="content" cols="70" rows="5" placeholder="正文内容"
					maxlength="255" style="font-size: 25px;" required="required"></textarea>
			</div>
			<p>
			<p>添加附件</p>
			<input type="file" name="attach" size="100"
				style="margin-left: 30%; color: purple;">
			</p>
			<br> <input type="submit" value="Submitted"
				style="margin-right: 30px;" id="own_write_btn"> <input
				type="reset" value="Restore" style="margin-left: 30px;">

		</form>
	</div>

</body>
</html>
