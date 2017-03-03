function NumFormat(t) 
{
	s = t.value;
	s = s.replace(/\D/g, '');
	l = s.length - 3;
	while (l > 0) {
		s = s.substr(0, l) + ',' + s.substr(l);
		l -= 3;
	}
	t.value = s;
	return t;
}

function go_ab()
{
	var theForm = document.frm;
	var a = theForm.price2.value.replace(/,/g, '');
	var b = theForm.price1.value.replace(/,/g, '');
	var ab = parseInt(a) - parseInt(b);
	theForm.price3.value = ab;
}


function go_save() 
{
	var theForm = document.frm;
	
	if (theForm.kind.value == '') {
		alert('상품분류를 선택하세요.');
		theForm.kind.focus();
	} else if (theForm.name.value == '') {
		alert('상품명을 입력하세요.');
		theForm.name.focus();
	} else if (theForm.price1.value == '') {
		alert('원가를 입력하세요.');
		theForm.price1.focus();
	} else if (theForm.price2.value == '') {
		alert('판매가를 입력하세요.');
		theForm.price2.focus();
	} else if (theForm.color.value == '') {
		alert('색상을입력하세요.');
		theForm.color.focus();
	} else if (theForm.productsize.value == '') {
		alert('사이즈를 선택하세요.');
		theForm.productsize.focus();
	} else if (theForm.suply.value == '') {
		alert('물량을 입력하세요.');
		theForm.suply.focus();
	} else if (theForm.content.value == '') {
		alert('상품상세를 입력하세요.');
		theForm.content.focus();
	} else if (theForm.image.value == '') {
		alert('상품이미지들 선택하세요.');
		theForm.image.focus();
	} else {
		theForm.encoding = "multipart/form-data";
		theForm.price1.value = removeComma(theForm.price1);
		theForm.price2.value = removeComma(theForm.price2);
		theForm.price3.value = removeComma(theForm.price3);
		
		theForm.action = "FlasicServlet?command=admin_product_write";
		theForm.submit();
	}
}

function removeComma(input)
{
	return input.value.replace(/,/gi, "");
}

function go_mov()
{
	var theForm = document.frm;
	theForm.action = "FlasicServlet?command=adminproductlist";
	theForm.submit();
}
//projectList.jsp
function go_search() {
	var theForm = document.frm;
	theForm.action =  "FlasicServlet?command=adminproductlist";
	theForm.submit();
}

function go_total() {
	var theForm = document.frm;
	theForm.key.value = "";
	theForm.action =  "FlasicServlet?command=adminproductlist";
	theForm.submit();
}

function go_detail(tpage, num) {
	var theForm = document.frm;

	theForm.action =  "FlasicServlet?command=admin_product_detail&tpage="+tpage+"&num="+num;
	theForm.submit();
}

function go_wrt() {
	var theForm = document.frm;
	theForm.action = "FlasicServlet?command=admin_product_write_form";
	theForm.submit();
}
function go_list(tpage) {
	var theForm = document.frm;

	theForm.action = "FlasicServlet?command=admin_product_list&tpage=" + tpage;
	theForm.submit();
}

function go_mod(tpage, num) {
	var theForm = document.frm;

	theForm.action = "FlasicServlet?command=admin_product_update_form&tpage=" + 
		              tpage+"&num="+num;
	theForm.submit();
}

function go_mod_save(tpage, num) {
	var theForm = document.frm;

	if (theForm.kind.value == '') {
		alert('상품분류를 선택하세요');
		theForm.kind.focus();
	} else if (theForm.name.value == '') {
		alert('상품명을 입력하세요');
		theForm.name.focus();
	} else if (theForm.price1.value == '') {
		alert('원가를 입력하세요');
		theForm.price1.focus();
	} else if (theForm.price2.value == '') {
		alert('판매가를 입력하세요');
		theForm.price2.focus();
	}  else if (theForm.color.value == '') {
		alert('색상을 입력하세요');
		theForm.color.focus();
	} else if (theForm.productsize.value == '') {
		alert('사이즈를 선택하세요');
		theForm.productsize.focus();
	} else if (theForm.suply.value == '') {
		alert('물량을 입력하세요');
		theForm.suply.focus();
	} else if (theForm.content.value == '') {
		alert('상품상세를 입력하세요');
		theForm.content.focus();
	} else {
		if (confirm('수정하시겠습니까?')) {
			if(theForm.best.checked == true) {
				theForm.best.value = "y";
			}
			theForm.encoding = "multipart/form-data";
			theForm.price1.value = removeComma(theForm.price1);
			theForm.price2.value = removeComma(theForm.price2);
			theForm.price3.value = removeComma(theForm.price3);
						
			theForm.action = "FlasicServlet?command=admin_product_update";
			theForm.submit();
		}
	}
}

function go_mod_mov(tpage, pseq) {
	var theForm = document.frm;
	theForm.action = 'FlasicServlet?command=admin_product_detail&tpage=' + tpage + "&pseq=" + pseq;
	theForm.submit();
}
