<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<div class="center_page">
<h1>Product List Page</h1>
<c:forEach var="p" items="${plist }">
<h5><a href="product.do?cmd=productdetail&id=${p.id }">${p.id } ${p.name } ${p.price }</a></h5>
</c:forEach>
</div>