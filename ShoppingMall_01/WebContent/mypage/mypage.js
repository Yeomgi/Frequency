function dg_check() {
	var type = document.getElementById("memberType");
	if(type.innerText=='디자이너'){
		alert("이미 디자이너 입니다.");
		return;
	}
	location.href = "mypage/designerlicense.jsp";
}

function dg_no() {
	
}

function dg_ok() {
	
}