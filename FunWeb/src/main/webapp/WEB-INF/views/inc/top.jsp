<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
<%
//세션값 가져오기
String loginID=(String)session.getAttribute("loginID");
if(loginID==null){
	//로그인 안한 상태 => 세션값 (loginID) 없음
	%>
<div id="login"><a href="${pageContext.request.contextPath }/member/login">login</a> | 
                <a href="${pageContext.request.contextPath }/member//insert">join</a></div>	
	<%		
}else{
	//로그인 성공 => 세션값 (loginID) 있음 
			%>
<div id="login"><%=loginID %> 님 | <a href="${pageContext.request.contextPath }/member/logout">logout</a> | 
                <a href="${pageContext.request.contextPath }/member/update">update</a></div>				
			<%
}
%>


<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><img src="${pageContext.request.contextPath }/resources/images/logo.gif" width="265" height="62" alt="Fun Web"></div>
<!-- ${pageContext.request.contextPath }/resource : 프로젝트 경로/resources밑에 images파일 -->

<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="${pageContext.request.contextPath }/main/main">HOME</a></li>
	<li><a href="${pageContext.request.contextPath }/company/welcome">COMPANY</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href="${pageContext.request.contextPath }/board/List">CUSTOMER CENTER</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>