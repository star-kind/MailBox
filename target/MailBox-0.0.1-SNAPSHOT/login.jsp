<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<html>
<head>

<script type="text/javascript" src="${basePath}/Jquery/jq.js"></script>

<script type="text/javascript"
	src="${basePath}/JavaScript/vue.min.2.6.10.js"></script>

<link href="${basePath}/ExternalCSSFrame/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="${basePath}/ExternalCSSFrame/bootstrap-theme.css"
	rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
</head>
<body>
	<div style="text-align: center;">
		<h3>请输入用户名和密码</h3>
		<form name="own_form" method="post" action="login"
			onsubmit="return verifyValidateCode()">

			<p>
				<input type="text" name="acname" maxlength="250"
					placeholder="请输入账户名" height="50px" required>
			</p>
			<p>
				<input type="password" name="password" maxlength="20"
					placeholder="请输入密码" height="50px" required>
			</p>

			<div>
				<p style="font-size: 12px;">点击下图可刷新验证码</p>
				<canvas id="canvas"
					style="margin-top: 0%; background-color: aquamarine;">
                </canvas>
				<br>
				<p>
					<input type="text" name="validateCode" placeholder="输入图中的验证码"
						maxlength="5" height="50px" style="margin-top: 25px;" required>
				</p>
			</div>

			<br>

			<p>
				<input type="submit" value="Sign in" id="own_button"
					style="font-size: 28px;" width="140px" height="55px">
			</p>

		</form>

		<br> <span> <a href="register.jsp">还没有账号?点此注册</a>
		</span> <br>

		<div>
			<p id="err_tip" style="color: red; font-size: 25px;">${tip}</p>
		</div>

	</div>

</body>

<script type="text/javascript">
	/**
	 * 验证码生成并校验
	 */
	var show_num = new Array();
	$(function() {
		//var show_num = new Array();
		draw(show_num);

		console.log('validate code:' + show_num);

		$('#canvas').on('click', function() {
			draw(show_num);
		})

		function draw(show_num) {
			var canvas_width = $('#canvas').width();
			var canvas_height = $('#canvas').height();

			var canvas = document.getElementById('canvas');

			var context = canvas.getContext("2d");// 获取canvas画图的环境

			canvas.width = canvas_width;
			canvas.height = canvas_height;

			var srcCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z"
					+ ",a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z"
					+ ",1,2,3,4,5,6,7,8,9,0";

			var aloneCode = srcCode.split(',');
			// console.log('aloneCode- '+aloneCode);
			var aloneCodeLen = aloneCode.length;

			for (var index = 0; index <= 4; index++) {
				var j = Math.floor(Math.random() * aloneCodeLen);// 得到随机的索引
				var txt = aloneCode[j];
				// console.log("txt- "+txt);

				show_num[index] = txt;

				//产生0~30° random radian
				var deg = Math.random() * 30 * Math.PI / 180;
				var x = 10 + index * 36;// 字符在canvas上的x坐标
				var y = 20 + Math.random() * 8;// 字符在canvas上的y坐标

				context.font = "bold 33px 微软雅黑";
				context.translate(x + 5, y + 5);
				context.rotate(deg);
				context.fillStyle = randomColor();
				context.fillText(txt, 0, 0);
				context.translate(-x + 10 * 1.5, -y + 10 * 2.5);
				context.rotate(-deg);
			}

			// 验证码上显示线条
			for (var index = 0; index <= 5; index++) {
				context.strokeStyle = randomColor(); // stroke 一击,轻触,抚摸
				context.beginPath();
				context.moveTo(Math.random() * canvas_width, Math.random()
						* canvas_height);
				context.lineTo(Math.random() * canvas_width, Math.random()
						* canvas_height);
				context.stroke();
			}

			// 验证码上显示小点
			for (var index = 0; index < 60; index++) {
				context.strokeStyle = randomColor();
				context.beginPath();
				var x = Math.random() * canvas_width;
				var y = Math.random() * canvas_height;

				context.moveTo(x, y);
				context.lineTo(x + 1, y + 1);
				context.stroke();
			}

			// 得到随机的颜色值
			function randomColor() {
				var r = Math.floor(Math.random() * 256);
				var g = Math.floor(Math.random() * 256);
				var b = Math.floor(Math.random() * 256);

				return 'rgb(' + r + ',' + g + ',' + b + ',' + ')';
			}

		}

		// 检视变量类型
		console.log('typeof(show_num): ' + typeof (show_num + ''));

	});

	/* 校验验证码 */
	function verifyValidateCode() {
		var v = document.forms['own_form']['validateCode'].value;
		if (v == '' || v == null) {
			alert('禁止为空');
			return;
		}
		v = v.toLowerCase();

		var vcStr = show_num + '';
		vcStr = vcStr.toLowerCase();
		vcStr = vcStr.replace(/,/g, '');// 去除字符串中的所有逗号

		console.log('typeof(vcStr):' + typeof (vcStr))
		console.log('commitsForm vcStr: ' + vcStr);
		console.log(v === vcStr);

		if (v === vcStr) {
			console.log('correspond');
			return true;
		} else {
			alert('Not correspond');
			return false;
		}

	}
</script>

</html>