/* sendRequest函数 */
function sendRequest(method, url, isAyns, params, action) {
	xhr = new XMLHttpRequest();

	xhr.open(method, url, isAyns);

	// 将字符串参数序列化为表单类型数据
	xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded');// 很关键

	xhr.send(params);

	xhr.onreadystatechange = action;

}