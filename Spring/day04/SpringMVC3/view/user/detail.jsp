<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<div class="center_page">
<h1>User Detail Page</h1>
<h3>${userdetail.id }</h3>
<h3>${userdetail.pwd }</h3>
<h3>${userdetail.name }</h3>
<a href="userdelete.mc?id=${userdetail.id }">DELETE</a>
<a href="userupdate.mc?id=${userdetail.id }">UPDATE</a>

</div>