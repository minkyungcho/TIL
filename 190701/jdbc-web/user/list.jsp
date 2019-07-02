<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<div class="center_page">
<h1>User List Page</h1>
<c:forEach var="u" items="${ulist }">
<h4><a href="user.do?cmd=userdetail&id=${u.id }">${u.id } ${u.name }</a></h4>
</c:forEach>
</div>