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
			location.href='product.do?cmd=productdelete&id=${pd.id }';	
		}
		
	});
	$('button:eq(1)').click(function(){
		var c = confirm('수정 하시겠습니까?');
		if(c == true){
			location.href='product.do?cmd=productupdate&id=${pd.id }';	
		}
	});
});
</script>
</head>
<body>
<div class="center_page">
<h1>Product Detail Page</h1>
<button>DELETE</button>
<button>UPDATE</button><br>
<img src="img/${pd.imgname }">
<h5>ID : ${pd.id }</h5>
<h5>NAME : ${pd.name }</h5>
<h5>PRICE : ${pd.price }</h5>
<h5>DATE : ${pd.regdate }</h5>
<h5>IMGNAME : ${pd.imgname }</h5>
</div>
</body>
</html>