<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<div class="center_page">
<h1>Product Add Page</h1>
<form action="product.do?cmd=productaddimpl" method="POST" 
enctype="multipart/form-data">
NAME<input type="text" name="name"><br>
PRICE<input type="number" name="price"><br>
IMG<input type="file" name="imgname"><br>
<input type="submit" value="REGISTER">
</form>
</div>