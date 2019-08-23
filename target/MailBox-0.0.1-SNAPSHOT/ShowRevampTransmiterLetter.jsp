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
<title>编改邮件之页</title>

</head>
<body
	style="font-family: 'Courier New', Courier, monospace; font-size: 28px;">

	<div style="text-align: center;">
		<br> <a href="survey_this_letter?letter_id=${transmitLetter.id }">返至勘察邮件详情信息页</a>
		<br>

		<!-- 编辑信件区域 -->
		<div style="display: block; position: relative;">
			<h3>信件编辑之面板</h3>
			<br>
			<form enctype="multipart/form-data"
				action="transmit_letter_send_revamp" method="post">
				<p style="visibility: hidden;">
					<span>发信人: </span> <input type="text" value=${transmitLetter.id }
						readonly="readonly" name="id" style="background-color: white;">
				</p>
				<p>
					<span>发信人: </span> <input type="text"
						value=${transmitLetter.transmitter } readonly="readonly"
						name="transmitter" style="background-color: #78dceb;">
				</p>
				<p>
					<span>收信人: </span> <input type="text" name="receiver"
						value=${transmitLetter.receiver } maxlength="100"
						required="required">
				</p>
				<p>
					<span>主题: </span> <input type="text" name="title"
						value=${transmitLetter.title } maxlength="200" required="required">
				</p>

				<div>
					<span>内容: </span>
					<textarea name="content" cols="40" rows="5" maxlength="255"
						style="font-size: 25px;" required="required">${transmitLetter.content }</textarea>
				</div>
				<p>
					<span style="padding-right: 12px;">添加附件</span>
				<h3>
					<input type="file" name="attach" size="100" value="上传文件"
						style="margin-left: 30%; color: purple;">
				</h3>
				</p>
				<!--  -->
				<br>
				<!--  -->
				<input type="submit" value="即时发送" style="margin-right: 30px;"
					id="send_btn">

				<!--  -->
				<button style="margin-right: 30px;">
					<a
						href="transmit_letter_conversion_draft?tlid=${transmitLetter.id }">存至草稿</a>
				</button>

			</form>
		</div>

	</div>
</body>
</html>
