<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/update.jsp</title>
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/script/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#join').submit(function(){
// 			alert("전송");
// 			if($('.id').val()==""){
// 				alert('아이디 입력하세요');
// 				$('.id').focus();
// 				return false;
// 			}
// 			if($('.pw').val()==""){
// 				alert('비밀번호 입력하세요');
// 				$('.pw').focus();
// 				return false;
// 			}
// 			if($('.name').val()==""){
// 				alert('이름 입력하세요');
// 				$('.name').focus();
// 				return false;
// 			}
// 			if($('.email').val()==""){
// 				alert('이메일 입력하세요');
// 				$('.email').focus();
// 				return false;
// 			}
		});//
		
		
	});//
</script> 
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="#">Join us</a></li>
<li><a href="#">Privacy policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<h1>Update Us</h1>
<form action="${pageContext.request.contextPath }/member/updatePro" id="join" method="post">
<fieldset>
<legend>Basic Info</legend>
<label>User ID</label>
<input type="text" name="userid" class="id" readonly value="${vo.userid }"><br>
<label>Password</label>
<input type="password" name="userpw" class="pw"><br>
<label>Name</label>
<input type="text" name="username" class="username" value="${vo.username }"><br>
<label>E-Mail</label>
<input type="email" name="useremail" class="useremail" value="${vo.useremail }"><br>
</fieldset>

<fieldset>
<legend>Optional</legend>
<label>Address</label>
<input type="text" name="address"><br>
<label>Phone Number</label>
<input type="text" name="phone"><br>
<label>Mobile Phone Number</label>
<input type="text" name="mobile"><br>
</fieldset>
<div class="clear"></div>
<div id="buttons">
<input type="submit" value="Submit" class="submit">
<input type="reset" value="Cancel" class="cancel">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>