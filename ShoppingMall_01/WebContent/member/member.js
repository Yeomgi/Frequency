function join() {
  if (document.formm.id.value == "") {
    alert("아이디를 입력하여 주세요.");
    document.formm.id.focus();
/*  } else if (document.formm.id.value != document.formm.reid.value) {
    alert("중복확인을 클릭하여 주세요.");
    document.formm.id.focus();*/
  } else if (document.formm.pw.value == "") {
    alert("비밀번호를 입력해 주세요.");
    document.formm.pw.focus();
  } else if ((document.formm.rpw.value != document.formm.pw.value)) {
    alert("비밀번호가 일치하지 않습니다.");
    document.formm.rpw.focus();
  } else if (document.formm.name.value == "") {
    alert("이름을 입력해 주세요.");
    document.formm.name.focus();
  } else if (document.formm.birth.value == "") {
    alert("생년월일을 입력해 주세요.");
    document.formm.birth.focus();
  }else if (document.formm.pnum.value == "") {
	    alert("전화번호를 입력해 주세요.");
	    document.formm.pnum.focus();
  }else if (document.formm.email.value == "") {
	    alert("이메일을 입력해 주세요.");
	    document.formm.email.focus();
  }else if (document.formm.addr.value == "") {
	    alert("주소를 입력해 주세요.");
	    document.formm.addr.focus();
  }else {
    document.formm.action = "FlasicServlet?command=join";
    alert("회원가입 해주셔서 감사합니다.");
    document.formm.submit();
  }
}

function idcheck() {
	  if (document.formm.id.value == "") {
		    alert("아이디를 입력하여 주세요.");
		    document.formm.id.focus();
		    return;
	  }
	  var url = "FlasicServlet?command=idcheck&id=" 
		  + document.formm.id.value;
		    window.open( url, "_blank_1",
		  "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
		  
}