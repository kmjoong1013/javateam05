<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mode = request.getParameter("mode");
	String title = "상품등록";
	String actionPage = "goodsInsProc2.jsp";

%>
<html>
<head>
<title><%=title%></title>
<script>
	function openDup(){
		var win = window.open("codeDup.jsp","","width=400,height=500");
	}
</script>
</head>
<body>
	<form name="goodsInsFrm" action="/goods/goodsIns.do" method="post">
		<table align="center" border="1">
			<tr>
				<td>
					코드
				</td>
				<td>
					<input type="text" name="code" value="" >
					<input type="hidden" name="codeDupchk" >
				</td>
			</tr>
			<tr>
				<td>
					상품명
				</td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>			
			<tr>
				<td>
					단가
				</td>
				<td>
					<input type="text" name="price">
				</td>
			</tr>
			<tr>
				<td>
					색상
				</td>
				<td>
					<select name="color">
						<option value="">--선택--</option>
						<option value="red" >붉은색</option>
						<option value="white" >흰색</option>
						<option value="green" >녹색</option>
						<option value="blue" >파란색</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="5"><input type="submit" value="등록"></td>
			</tr>	
		</table>
	</form>
<a href="goodsList.do">상품리스트</a>	
</body>
</html>