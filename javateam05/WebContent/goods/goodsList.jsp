<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>상품 리스트</title>
</head>
<body>
	<table align="center" border="1" width="500">
		<tr>
			<td colspan="5">상품 리스트</td>
		</tr>
		<tr>
			<td>코드</td>
			<td>상품명</td>
			<td>단가</td>
			<td>색상</td>
			<td>수정/삭제</td>
		</tr>	
		<c:choose>
			<c:when test="${empty goodsList }">
				<tr>
					<td>상품이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${goodsList }" var="goodsVO"> 
				<tr> 
					<td>${goodsVO.code }</td>
					<td>${goodsVO.name }</td>
					<td><fmt:formatNumber value="${goodsVO.price }" type="number"/></td>
					<td>${goodsVO.color }</td>
					<td>수정/삭제</td>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
<a href="goodsInsForm.jsp">상품등록</a>	
</body>
</html>