<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center_page">
<h1>User Detail Page</h1><br>
<h3>ID : ${userdetail.id }</h3>
<h3>PWD : ${userdetail.pwd }</h3>
<h3>NAME : ${userdetail.name }</h3>
<button type="button" onclick="location.href='userdelete.mc?id=${userdetail.id }'">DELETE</button>
<button type="button" onclick="location.href='userupdate.mc?id=${userdetail.id }'">UPDATE</button>
</div>