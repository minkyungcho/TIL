<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.center_page > img{
	width: 100px;
}
</style>
<div class="center_page">
<h1>Product Detail Page</h1><br>
<img src="img/${pdetail.imgname }">
<h3>ID : ${pdetail.id }</h3>
<h3>NAME : ${pdetail.name }</h3>
<h3>PRICE : ${pdetail.price }원</h3>
<h3>DATE : ${pdetail.regdate }</h3>
<h3>IMGNAME : ${pdetail.imgname }</h3>
<button type="button" onclick="location.href='pdelete.mc?id=${pdetail.id }'">DELETE</button>
<button tupe="button" onclick="location.href='pupdate.mc?id=${pdetail.id }'">UPDATE</button>
</div>