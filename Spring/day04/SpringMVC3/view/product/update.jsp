<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<div class="center_page">
<h1>Product Update Page</h1>

<form action="pupdateimpl.mc" method="POST">
ID : ${pudate.id }<input type="hidden" name="id" value="${pupdate.id }">
NAME<input type="text" name="name" value="${pupdate.name }"><br>
PRICE<input type="text" name="pwd" value="${pupdate.price }"><br>
IMG<input type="file" name="mf"><br>
<input type="hidden" name="imgname" value="${pupdate.imgname }">
<input type="submit" value="UPDATE"><br>
</form>
</div>