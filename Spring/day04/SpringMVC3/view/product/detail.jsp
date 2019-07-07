<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<style>
.center_page > img{
	width: 120px;
	
}
</style>


<div class="center_page">
<h1>Product Detail Page</h1>

<a href="pdelete.mc?id=${pdetail.id }">DELETE</a>
<a href="pupdate.mc?id=${pdetail.id }">UPDATE</a>
<img src="img/${p.imgname }">
<h3>ID : ${pdetail.id }</h3>
<h3>NAME : ${pdetail.name }</h3>
<h3>PRICE : ${pdetail.price }</h3>
<h3>DATE : ${pdetail.date }</h3>
<h3>IMGNAME : ${pdetail.imgname }</h3>

</div>