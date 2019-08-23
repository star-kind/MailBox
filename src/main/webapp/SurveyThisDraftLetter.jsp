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
<title>勘察草稿箱里的１封邮件</title>

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
			<a href="transmit_letter_show_draft_list">返回草稿箱邮件列表</a>
		</div>

		<div style="margin-top: 36px;">

			<form action="transmit_letter_send_revamp" method="post"
				enctype="multipart/form-data">
				<p>
					<input type="text" name="id" value="${letter.id }"
						readonly="readonly" style="visibility: hidden;">
				</p>
				<p>
					信件原主: <input placeholder="${letter.transmitter }"
						readonly="readonly" type="text" name="transmitter" width="150px"
						height="40px" value="${letter.transmitter }"
						style="font-size: 35px; background: yellow; color: blue;">
				</p>
				<p>
					收信者: <input placeholder="${letter.receiver }" readonly="readonly"
						type="text" name="receiver" width="150px" height="40px"
						value="${letter.receiver }"
						style="font-size: 35px; background: yellow; color: blue;">
				</p>
				<p>
					信件主题: <input placeholder="${letter.title }" readonly="readonly"
						type="text" name="title" width="200px" height="40px"
						value="${letter.title }"
						style="font-size: 35px; background: yellow; color: blue;">
				</p>
				<p>
					信件内容:
					<textarea name="content" rows="10" cols="44"
						value="${letter.content }" readonly="readonly"
						style="background-color: yellow; color: blue;">${letter.content }</textarea>
				</p>
				<p>
					<span style="background: orange; font-size: 28px;">信件附件名: </span>
					<!--  -->
					<input value="${letter.attachmentFileName }" type="file"
						name="attachmentFileName" width="150px" height="40px"
						style="font-size: 35px; background: orange; color: ##EC4949">
				</p>
				<p>
					信件被删除时间: <input value="${letter.lastEditTime }" readonly="readonly"
						name="moveInRecycleTime" width="150px" type="text" height="40px"
						style="font-size: 35px; background: yellow; color: blue;">
				</p>
				<br>
				<div>
					<button>
						<a
							href="TransmiterLetterExhibitRevampHandler?letter_id=${letter.id }">编辑此信</a>
					</button>

					<input type="submit" value="马上发送给收信者邮箱"
						style="color: #ed0591; background-color: #f1e9ee;" width="154px"
						height="66px">

				</div>
			</form>

		</div>

	</div>

</body>
<script type="text/javascript">
	/*  */
</script>
</html>