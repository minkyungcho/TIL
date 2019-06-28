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
		var c = confirm('수정을 완료하시겠습니까?');
		if(c == true){
			location.href='req?type=user&cmd=udateimpl&id=${uu.id }';	
		}
	});
});
</script>
</head>
<body>
<h1>User Update Page</h1>
<form action="req?type=user&cmd=updateimpl" method="POST">
ID<input readonly = "readonly" type="text" name="id" value="${uu.id }"><br>
PWD<input type="text" name="pwd" value="${uu.pwd }"><br>
NAME<input type="text" name="name" value="${uu.name }"><br>
<input type="submit" value="UPDATE"><br>
</body>
</html>