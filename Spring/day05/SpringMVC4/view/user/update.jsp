<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center_page">
<h1>User Update Page</h1><br>
<form action="userupdateimpl.mc" method="POST">
ID <input readonly="readonly" type="text" name="id" value="${userupdate.id }"><br>
PWD <input type="text" name="pwd" value="${userupdate.pwd }"><br>
NAME <input type="text" name="name" value="${userupdate.name }"><br>
<input type="submit" value="UPDATE"><br>
</form>
</div>