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
<title>勘察邮件详情信息页</title>

<style type="text/css">
.transmitedletter_column {
	color: #EC4949;
	font-family: Helvetica;
}
</style>

</head>
<body>
	<div style="text-align: center; font-size: 22px; margin-top: 38px;">
		<div>
			<a href="transmittedLetterBoxList">返回发件箱列表页</a>
		</div>

		<div style="margin-top: 36px;">
			<p>
				<span class="transmitedletter_column">发信者: </span>
				${transmitedletter.transmitter }
			</p>
			<p>
				<span class="transmitedletter_column">收信者: </span>
				${transmitedletter.receiver }
			</p>
			<p>
				<span class="transmitedletter_column">主题: </span>
				${transmitedletter.title }
			</p>
			<p>
				<span class="transmitedletter_column">上下文内容</span><br>
				<textarea rows="10" cols="30" readonly="readonly">
				${transmitedletter.content }
				</textarea>
			</p>
			<p>
				<span class="transmitedletter_column">发送之时:</span><br>
				${transmitedletter.launchTime }
			</p>
			<p>
				<span class="transmitedletter_column">附件名:</span><br>
				${transmitedletter.attachmentFileName }
			</p>
			<p>
				<span class="transmitedletter_column">发送状态: </span> <br>
				<c:choose>

					<c:when test="${transmitedletter.status==1}">
						<span>已发送</span>
					</c:when>

					<c:otherwise>
						<c:if test="${transmitedletter.status==0 }">
							<span>其在回收站</span>
						</c:if>
						<c:if test="${transmitedletter.status==2 }">
							<span>其犹为草稿</span>
						</c:if>
					</c:otherwise>

				</c:choose>
			</p>
		</div>

		<br>
		<div>
			<button>
				<a
					href="transmiterLetter_dispatch_revamp?tlid=${transmitedletter.id }">编辑此信</a>
			</button>

			<button>
				<a>彻底删除</a>
			</button>

			<button>
				<a href="javascript:removeInGarbage(${transmitedletter.id })">删除-移入垃圾箱</a>
			</button>
		</div>
	</div>

</body>

<script type="text/javascript">
	function removeInGarbage(id) {
		var data = "ids=" + id + "&forwardWhichBoxs=garbage";

		console.log(data);

		sendRequest('post', 'MoveLettersToBoxHandler', true, data, function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				alert("移入回收站成功");
				location.href = "transmittedLetterBoxList";
			} else if (xhr.readyState != 4 && xhr.status != 200) {
				alert("Defeated");
			}

		});
	}
</script>
</html>