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
<title>发件箱</title>

<style type="text/css">
th {
	text-align: center;
}
</style>

</head>
<body>
	<div style="text-align: center; font-size: 22px; margin-top: 38px;">
		<div>
			<a href="transmitMailPanel">返回我的面板页</a>
		</div>

		<table border="1"
			style="border-collapse: collapse; margin-left: 2%; width: 95%; text-align: center; margin-top: 2%;">
			<caption>发件箱(已发送)</caption>
			<br>
			<thead style="text-align: center;">
				<tr>
					<th>多选<input type="checkbox" id="select_check"></th>
					<th>收吾信者</th>
					<th>标题</th>
					<th>附件名</th>
					<th>发信时间</th>
					<th>查看此信</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list }" var="t">
					<tr>
						<td><input type="checkbox" value="${t.id }" name="ids"></td>
						<td>${t.receiver }</td>
						<td>${t.title }</td>
						<td>${t.attachmentFileName }</td>
						<td>${t.launchTime }</td>
						<td><a href="survey_this_letter?letter_id=${t.id }">点击查看</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

		<br>
		<div>
			<button style="margin-right: 50px;" id="delete_btn">彻底删除</button>
			<button>
				<a href="javascript:removeInGarbage()">删除-移入垃圾箱</a>
			</button>
		</div>
	</div>

</body>
<script type="text/javascript">
	/*全局变量*/
	var checkboxArr = new Array();
	var isAll = false;

	/** 全选or全不选之效果 */
	$("#select_check").click(function() {
		if ($("#select_check").prop("checked") == true) {
			// 上面的复选框已被选中
			$(":checkbox[name='ids']").prop("checked", true);

			// 获取name=ids的已勾选之复选框的值,压入数组checkArr
			$("input[name='ids']:checked").each(function() {
				checkboxArr.push(this.value);
			});

			// 取值结束后重新初始化
			checkboxArr = [];
		} else {

			// 上面的复选框没被选中
			$(":checkbox[name='ids']").prop("checked", false);
		}
	});

	/* 
	-------------------------
	缓冲区
	-------------------------
	 */

	//XMLHttpRequest准对象
	var xhr;

	/* 删除-移入垃圾箱 */
	function removeInGarbage() {
		// 多选/已选的
		var items = $("input[name='ids']:checked");

		items.each(function() {
			checkboxArr.push(this.value);
		});

		if (checkboxArr.length < 1) {
			alert('还没选中任何一封信件');
			return;
		}

		var ids = checkboxArr.join(',');
		var forwardWhichBoxs = 'garbage';

		var data = "ids=" + ids + "&forwardWhichBoxs=" + forwardWhichBoxs;

		console.log(data);

		sendRequest('POST', 'MoveLettersToBoxHandler', true, data, function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				console.log('移至垃圾箱');
				alert("全部移动成功");
				location.reload();
			} else if (xhr.readyState != 4 && xhr.status != 200) {
				alert("Defeated");
			}
		});
		/*  */
	}

	/*sendRequest函数*/
	function sendRequest(method, url, isAyns, params, action) {
		xhr = new XMLHttpRequest();

		xhr.open(method, url, isAyns);

		xhr.setRequestHeader('content-type',
				'application/x-www-form-urlencoded');//很关键

		xhr.send(params);

		xhr.onreadystatechange = action;

	}

	/* 
	-------------------------
	缓冲区
	-------------------------
	 */
	$("#delete_btn").on("click", function() {
		var items = $("input[name='ids']:checked");

		if (items.length < 1) {
			alert("请先选择信件");
			return;
		}

		items.each(function() {
			checkboxArr.push(this.value);
		});
		console.log(checkboxArr);

		var ids = checkboxArr.join(',');
		var data = "ids=" + ids

		sendRequest('post', 'LetterDeleteHandler', true, data, function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				alert("删除成功");
				location.reload();
			}
			/*  */
		});
		/*  */
	})
</script>
</html>