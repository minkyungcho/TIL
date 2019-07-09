<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.center_page > h3 > a > img{
	width: 60px;
}
</style>
<div class="center_page">
<h1>Product List Page</h1><br>
<c:forEach var="p" items="${plist }">
	<h3><a href="pdetail.mc?id=${p.id }"><img src="img/${p.imgname }"> ${p.id } ${p.name } ${p.price }원 ${p.imgname }</a></h3><br>
</c:forEach>
</div>