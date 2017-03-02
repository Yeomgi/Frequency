function go_sell() {
	document.frm.action = "FlasicServlet?command=sell";
	document.frm.submit();
}

function go_cart() {
	document.frm.action = "FlasicServlet?command=cart";
	document.frm.submit();
}