<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('button:eq(0)').click(function(){
		var c = confirm('삭제 하시겠습니까?');
		if(c == true){
			location.href='user.do?cmd=userdelete&id=${ud.id }';	
		}
		
	});
	$('button:eq(1)').click(function(){
		var c = confirm('수정 하시겠습니까?');
		if(c == true){
			location.href='user.do?cmd=userupdate&id=${ud.id }';	
		}
	});
});
</script>
</head>
<body>
<div class="center_page">
<h1>User Detail Page</h1>
<button>DELETE</button>
<button>UPDATE</button><br>
<!-- <img src="img/${ud.id }.jpg"> -->
<h5>ID ${ud.id }</h5>
<h5>PWD ${ud.pwd }</h5>
<h5>NAME ${ud.name }</h5>
</div>
</body>
</html>