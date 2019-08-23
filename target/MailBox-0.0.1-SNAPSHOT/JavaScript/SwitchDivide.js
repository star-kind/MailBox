// 点击链接切换division
function opens(obj) {
	var divModuleLen = $('.div_module').length;
//	console.log(divModuleLen)
	for (var i = 1; i <= divModuleLen; i++) {
		if (obj === i) {
			document.getElementById('room' + i).style.display = "block";
		} else {
			document.getElementById('room' + i).style.display = "none";
		}
	}
}
