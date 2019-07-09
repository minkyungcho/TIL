<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center_page">
<h1>Product Update Page</h1><br>
<form action="pupdateimpl.mc" method="POST" enctype="multipart/form-data">
ID <input readonly="readonly" type="text" name="id" value="${pupdate.id }"><br>
NAME <input type="text" name="name" value="${pupdate.name }"><br>
PRICE <input type="text" name="price" value="${pupdate.price }"><br>
IMG <input type="file" name="mf"><br>
<input type="hidden" name="imgname" value="${pupdate.imgname }">
<input type="submit" value="UPDATE"><br>
</form>
</div>