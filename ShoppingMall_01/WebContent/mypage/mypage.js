function dg_check(id) {
	var type = document.getElementById("memberType");
	if(type.innerText=='디자이너'){
		alert("이미 디자이너 입니다.");
		return;
	}
	document.frm.action = "FlasicServlet?command=rqDesigner&id="+id;
	document.frm.submit();
}

function mem_up(id) {
	document.frm.action = "FlasicServlet?command=memberupdate&id="+id;
	document.frm.submit();
}

function mem_up_save(id) {
	document.frm.action = "FlasicServlet?command=memberupdatesave&id="+id;
	document.frm.submit();
}

function dg_no() {
	document.frm.action = "FlasicServlet?command=mypage";
	document.frm.submit();
}

function dg_ok(id) {
	if (confirm("동의하시겠습니까?")) {
		if (document.frm.designer.checked == true) {
			document.frm.designer.value = "y";
		}
		document.frm.action = "FlasicServlet?command=designerupdate&id="+id;
		document.frm.submit();
	}
	
}