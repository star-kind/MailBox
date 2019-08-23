<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />

<html>
<head>

<script type="text/javascript"
	src="${basePath}/JavaScript/vue.min.2.6.10.js"></script>

<script type="text/javascript"
	src="${basePath}/JavaScript/SendRequest.js"></script>

<script type="text/javascript" src="${basePath}/Jquery/jq.js"></script>

<link href="${basePath}/ExternalCSSFrame/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="${basePath}/ExternalCSSFrame/bootstrap-theme.css"
	rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>勘察垃圾箱里的一封邮件</title>

<style type="text/css">
.transmitedletter_column {
	color: #EC4949;
	font-family: Helvetica;
}
</style>

</head>
<body>
	<div
		style="text-align: center; font-size: 22px; margin-top: 38px; margin-left: 5%;">
		<div>
			<a href="RecycleLettersStoreListHandler">返回回收站邮件列表</a>
		</div>

		<div style="margin-top: 36px;">

			<form action="transmit_letter_send_revamp" method="post"
				enctype="multipart/form-data">
				<p>
					<input value="${letter.id }" readonly="readonly" type="text"
						name="id" width="150px" height="40px"
						style="font-size: 35px; visibility: hidden;">
				</p>
				<p>
					信件原主: <input value="${letter.transmitter }" readonly="readonly"
						type="text" name="transmitter" width="150px" height="40px"
						style="font-size: 35px; background: yellow; color: blue;">
				</p>
				<p>
					收信者: <input value="${letter.receiver }" readonly="readonly"
						type="text" name="receiver" width="150px" height="40px"
						style="font-size: 35px; background: yellow; color: blue;">
				</p>
				<p>
					信件主题: <input value="${letter.title }" readonly="readonly"
						type="text" name="title" width="150px" height="40px"
						style="font-size: 35px; background: yellow; color: blue;">
				</p>
				<p>
					信件内容:
					<textarea name="content" rows="10" cols="32"
						value="${letter.content }" readonly="readonly"
						style="background-color: yellow; color: blue;">${letter.content }</textarea>
				</p>
				<p>
					信件附件名: <input value="${letter.attachmentFileName }" type="text"
						readonly="readonly" name="attachmentFileName" width="150px"
						height="40px"
						style="font-size: 35px; background: yellow; color: ##EC4949">
				</p>
				<p>
					信件被删除时间: <input value="${letter.moveInRecycleTime }"
						readonly="readonly" name="moveInRecycleTime" width="150px"
						type="text" height="40px"
						style="font-size: 35px; background: yellow; color: blue;">
				</p>
				<p>
					<input type="submit" value="马上发送" style="margin-right: 30px;"
						id="own_write_btn">
				</p>
			</form>

		</div>

		<br>
		<div>
			<button>
				<a>彻底删除</a>
			</button>
		</div>
	</div>

</body>
<script type="text/javascript">
	/*  */
</script>
</html>