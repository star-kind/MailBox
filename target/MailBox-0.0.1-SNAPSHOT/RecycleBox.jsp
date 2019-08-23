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

<script type="text/javascript"
	src="${basePath}/JavaScript/SendRequest.js"></script>

<script type="text/javascript" src="${basePath}/Jquery/jq.js"></script>

<link href="${basePath}/ExternalCSSFrame/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="${basePath}/ExternalCSSFrame/bootstrap-theme.css"
	rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<style type="text/css">
th, td {
	text-align: center;
}
</style>

<title>邮件垃圾箱</title>

</head>
<body
	style="font-family: 'Courier New', Courier, monospace; font-size: 30px;">

	<div style="text-align: center; margin-top: 40px;">
		<div>
			<a href="transmitMailPanel">返回我的面板页</a>
		</div>

		<table border="1"
			style="border-collapse: collapse; width: 90%; text-align: center;">
			<thead>
				<tr>
					<th>多选<input type="checkbox" id="select_check"></th>
					<th>发件者</th>
					<th>收信者</th>
					<th>标题</th>
					<th>附件名</th>
					<th>移至垃圾箱时间</th>
					<th>查看本信</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list }" var="t">
					<tr>
						<td><input type="checkbox" value="${t.id }" name="ids"></td>
						<td>${t.transmitter }</td>
						<td>${t.receiver }</td>
						<td>${t.title }</td>
						<td>${t.attachmentFileName }</td>
						<td>${t.moveInRecycleTime }</td>
						<td><a
							href="LetterSurveyHandler?letter_id=${t.id }&sourceBox=garbage">查看本信</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<div>
			<button id="delete_btn">彻底删除</button>
			<div>

				<select name="" id="recovery_select"
					style="margin-top: 20px; height: 80px;">
					<option value="received">恢复至收件箱</option>
					<option value="transmited">恢复至发件箱</option>
					<option value="draft">恢复至草稿箱</option>
				</select>
				<button style="margin-left: 30px;" id="confirms">确定</button>

			</div>
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
	/*  */
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
		var data = "ids=" + ids;

		sendRequest('post', 'LetterDeleteHandler', true, data, function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				alert("删除成功");
				location.reload();
			}
			/*  */
		});
		/*  */
	});
	/* 
	-------------------------
			 缓冲区
	-------------------------
	 */
	$('#confirms').on('click', function() {
		var recovery = $('#recovery_select').val();

		var items = $("input[name='ids']:checked");

		if (items.length < 1) {
			alert("请先选择信件");
			return;
		}

		items.each(function() {
			checkboxArr.push(this.value);
		});

		var ids = checkboxArr.join(',');
		var data = "ids=" + ids + "&forwardWhichBoxs=" + recovery;
		console.log(data);

		sendRequest('post', 'LetterDeleteHandler', true, data, function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				alert('<span style="background:#34dded;">恢复成功</span>');
				location.reload();
			} else if (xhr.readyState != 4 && xhr.status != 200) {
				alert('<span style="background:#9887f3;">恢复失败</span>');
			}
			/*  */
		});
		/*  */
	});
</script>
</html>
