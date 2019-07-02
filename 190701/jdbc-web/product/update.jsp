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
</script>
</head>
<body>
<div class="center_page">
<h1>Product Update Page</h1>
<form action="product.do?cmd=productupdateimpl" method="POST" enctype="multipart/form-data">
ID : ${pu.id }<br>
<input type="hidden" name="id" value="${pu.id }">
NAME<input type="text" name="name" value="${pu.name }"><br>
PRICE<input type="number" name="price" value="${pu.price }"><br>
IMG<input type="file" name="newimgname"><br>
<input type="hidden" name="imgname" value="${pu.imgname }">
<input type="submit" value="REGISTER"><br>
</form>
</div>
</body>
</html>