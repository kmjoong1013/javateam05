<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsputil.mvc.mem.*" %>    
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	String doro_name = request.getParameter("doro_name");
	if(doro_name == null) doro_name = "";
	String sido = request.getParameter("sido_name");
	List<ZipCodeVO> zipcodeList = null;
	
	int totCnt = 0; // select된 데이터 건수
	int pageNum = 1; // 현재 페이지
	int pageSet = 0; // 현재 페이지 셋
	
	int totPage = 0;// 전체 페이지 
	int stPageSet = 0;
	int edPageSet = 0;
	
	String pageStr = request.getParameter("page");
	if(pageStr != null && !pageStr.equals("")) { pageNum = Integer.parseInt(pageStr); }
	if(doro_name != null){
		ZipCodeDAO zipCodeDao = ZipCodeDAO.getInstance();
		zipcodeList = zipCodeDao.search(sido, doro_name, pageNum);
		totCnt = zipCodeDao.getZipCount(sido, doro_name);
		pageSet = (int)Math.ceil((double)pageNum / ZipCodeDAO.PAGE_SET_SIZE);
		totPage = (int)Math.ceil((double)totCnt / ZipCodeDAO.PAGE_LIST_SIZE);
 
		stPageSet = ( (pageSet-1)*ZipCodeDAO.PAGE_SET_SIZE)+1;
		edPageSet =   (pageSet * ZipCodeDAO.PAGE_SET_SIZE) > totPage ? totPage : pageSet*(ZipCodeDAO.PAGE_SET_SIZE);   
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>주소검색</title>
<script type="text/javascript">
	function searchSubmit(page){
		document.zipSearchFrm.page.value = page;
		document.zipSearchFrm.submit();
	}
	function selectZipcode(zipcode, address){
		opener.memberForm.zipcode.value = zipcode;
		opener.memberForm.addr1.value = address;
		self.close();
	}
</script>
</head>
<body>
	<form action="zip.jsp" method="post" name="zipSearchFrm">
	<input type="hidden" name="page">
	도로명주소로 찾기<br>
		시도선택<select name="sido_name">
			<option>서울특별시
			<option>부산광역시
			<option>대구광역시
			<option>인천광역시
			<option>광주광역시
			<option>대전광역시
			<option>울산광역시
			<option>세종특별자치시
			<option>강원도
			<option>경기도
			<option>경상남북도
			<option>전라남북도
			<option>제주특별자치도
			<option>충청남북도
		</select> <br>
		<input type="text" name="doro_name" value="<%=doro_name%>"><br>
		<input type="submit" value="검색">
	</form>
	
	<%
		int size=0;
		if(zipcodeList == null) {
	%>
		검색된 데이터가 없습니다.
	<%
		}else {
			size = zipcodeList.size();
		for(int i = 0; i < size; i++){
			ZipCodeVO zipcode = zipcodeList.get(i);
			String address = zipcode.getSido() + " "  + zipcode.getGugun() + " " + zipcode.getDoro_name() 
					 + " ";// + zipcode.getBuild_num1() + (zipcode.getBuild_num2().equals("0") ? "":"-"+zipcode.getBuild_num2());  
	%>
		<a href="javascript:selectZipcode('<%=zipcode.getZip_code() %>','<%=address%>')"><%=zipcode.getZip_code() %></a>
		<%=address %><br>
	<%
		}
	%>
	<% if(stPageSet>ZipCodeDAO.PAGE_SET_SIZE){%><a href="javascript:searchSubmit('<%=stPageSet-1 %>')">[이전]</a> <%} %>
	<% for(int i =stPageSet ; i <= edPageSet; i++ ){ %>
		<a href="javascript:searchSubmit('<%=i %>')">[<%=i %>] </a>
	<%} %>
	<% if(edPageSet<totPage){%> <a href="javascript:searchSubmit('<%=edPageSet+1 %>')">[이후]</a> <%} 
		}
	%>
		
</body>
</html>
