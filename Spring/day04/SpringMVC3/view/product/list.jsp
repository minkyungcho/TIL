<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<style>
.center_page > img{
	width: 80px;
	
}
</style>

<div class="center_page">
<h1>Product List Page</h1>
<c:forEach var="p" items="${plist }">
	<h3><img src="img/${p.imgname }"></h3>
	<h3><a href="productdetail.mc?id=${p.id }">${p.id } ${p.name } ${p.price } ${p.imgname }</a></h3><br>
</c:forEach>
</div>