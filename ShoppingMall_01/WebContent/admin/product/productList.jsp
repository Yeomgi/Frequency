<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>
<article>
	<h1>상품 리스트</h1>
	<form name="frm" method="post">
	<table>
		<tr>
			<td width="650">
				상품명
				<input type="text" name="key">
     			<input class="btn" type="button" name="btn_search" value="검색" onclick="go_search()">
     			<input class="btn" type="button" name="btn_total" value="전체보기 " onclick="go_total()">
     			<input class="btn" type="button" name="btn_write" value="상품등록" onclick="go_wrt()">
			</td>
		</tr>
	</table>
	<table id="productList">
		 <tr>
        	<th>번호</th><th>상품명</th><th>원가</th><th>판매가</th><th>등록일</th><th>베스트상품</th>
    	</tr>
    	<c:choose>
    	<c:when test="${productListSize<=0}">
    	<tr>
    		<td width="100%" colspan="7" align="center" height="23">
    		등록된상품이없습니다.
    		</td>
    	</tr>	
    	</c:when>
    	<c:otherwise>
    	<c:forEach  items="${productList}" var="productDTO">
     	<tr>
      		<td height="23" align="center" >${productDTO.num}</td>
      		<td style="text-align: left; padding-left: 50px; padding-right: 0px;">   
        		<a href="#" onClick="go_detail('${tpage}', '${productDTO.num}')">
    	 			${productDTO.name}     
   				</a>
   	  		</td>
      		<td><fmt:formatNumber value="${productDTO.price1}"/></td>
      		<td><fmt:formatNumber value="${productDTO.price2}"/></td>
      		<td><fmt:formatDate value="${productDTO.productdate}"/></td>
      		<td>
      		<c:choose>
   	 		<c:when test='${productDTO.best=="n"}'>일반</c:when>
   	 		<c:otherwise>베스트</c:otherwise>   	 		
   	 		</c:choose>	 
   	  		</td> 
    	</tr>
    	</c:forEach>
    	<tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr>
    	</c:otherwise>
    	</c:choose>
	</table>
	</form>
</article>
<%@ include file="/admin/footer.jsp"%>
</body>
</html>